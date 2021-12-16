package GameEditor.Controllers;

import Objects.Board;
import Objects.DrawCardRule;
import Objects.JSONConverter;
import Objects.MoveRule;
import Objects.PlayCardRule;
import Objects.RNG;
import Objects.Tile;
import Objects.Token;
import Objects.Rule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RuleEditorController {

	@FXML
	private ResourceBundle resources;
	@FXML
	private ComboBox<String> dropdown0, dropdown1, dropdown2, dropdown3, dropdown4, tileOptions;
	@FXML
	private Rectangle selectedTile1 = null;
	@FXML
	private Rectangle selectedTile2 = null;
	@FXML
	private Pane board;
	@FXML
	private Button addTransition, changeTransition, deleteTransition, doneEditingTransition;
	@FXML
	private Label addMessage, transitionAlreadyExists;
	@FXML
	private HBox addButtonContainer, transitionButtonContainer;
	@FXML
	private TextField pointsToWin;
	@FXML
	private TextFlow takeFromPlayer, givePlayer, movePlayer, tileNum, numCards, numPoints;

	// hash table with array tile 1 tile 2 and value group
	private LinkedHashMap<List<Rectangle>, Polyline> tileMapping = new LinkedHashMap<List<Rectangle>, Polyline>();
	private Polyline currentTransition;
	private boolean addTransitionSelected = false;
	private HashMap<Rectangle, Tile> tileMap = new HashMap<Rectangle, Tile>();

	@FXML
	private ComboBox<String>[] dropdowns = new ComboBox[5];
	private List<String> turnList = new ArrayList<String>();
	private int numVisible = 1;
	private int[] visible = { 1, 0,0,0,0};
	private String[] deleteButtons = { "d0", "d1", "d2", "d3", "d4" };
	private double orgSceneX, orgSceneY;
	private boolean turnRuleClicked = false;
	private List<TextFlow> draggedRules = new ArrayList<TextFlow>();
	private ListView dialogContent = new ListView();
	private Boolean initialized = false;

	@FXML
	private Label tileName, rule1;
	@FXML
	private VBox tiles;
	private ArrayList<Tile> tileObjs;
	private String currTile;
	private ArrayList<Tile> currentTiles = new ArrayList<Tile>();
	private double startX, startY;
	private HashMap<String, Double[]> positions = new HashMap<>();

	private HashMap<Tile, ArrayList<Tile>> moveRules = new HashMap<>();
	private HashMap<Tile, ArrayList<Integer>> giveCards = new HashMap<>();
	private HashMap<Tile, ArrayList<Integer>> takeCards = new HashMap<>();
	private HashMap<Tile, ArrayList<Integer>> takePoints = new HashMap<>();
	private HashMap<Tile, ArrayList<Integer>> givePoints = new HashMap<>();

	private Board gameBoard;

	@FXML
	private URL location;

	//initializes the turn rule menu
	@FXML
	void initializeTurnRule() {
		dropdowns[0] = dropdown0;
		dropdowns[1] = dropdown1;
		dropdowns[2] = dropdown2;
		dropdowns[3] = dropdown3;
		dropdowns[4] = dropdown4;
		if(!turnRuleClicked)
		{
			dropdowns[1].getParent().setManaged(false);
			dropdowns[2].getParent().setManaged(false);
			dropdowns[3].getParent().setManaged(false);
			dropdowns[4].getParent().setManaged(false);
		}
		turnRuleClicked = true;
	}

	//initializes the drag and drop for each of the actions in the tile rule editor
	@FXML
	void initializeTileRule()
	{
		dragAndDrop(takeFromPlayer);
		dragAndDrop(givePlayer);
		dragAndDrop(movePlayer);
		dragAndDrop(numCards);
		dragAndDrop(tileNum);
		dragAndDrop(numPoints);
		ArrayList<Tile> boardTiles = new ArrayList<Tile>();
		LocalStorage localStorage = LocalStorage.getInstance();
		if(!initialized)
		{
			if(localStorage.storage.containsKey("board"))
			{
				gameBoard = (Board) localStorage.storage.get("board");
				boardTiles = gameBoard.get_tiles();
				tileOptions.getItems().clear();
				for(Tile tile: boardTiles)
				{
					tileOptions.getItems().add(tile.getAttributes().get("text"));
					TextFlow tf = new TextFlow();
					tf.setAccessibleText(tile.getAttributes().get("text"));
					CheckBox cb = new CheckBox();
					cb.setText(tile.getAttributes().get("text"));
					cb.setAccessibleText(tile.get_id());
					tf.getChildren().add(cb);
					tiles.getChildren().add(tf);
					checked(tf);
				}
			}
			initialized = true;
		}

	}

	//drag and drop for the actions in the tile rule editor
	@FXML
	void dragAndDrop(TextFlow action)
	{	
		positions.put(action.getAccessibleText(), new Double[] {action.getTranslateX(), action.getTranslateY()});
		action.setCursor(Cursor.HAND);

		//when a node is clicked on 
		action.setOnMousePressed((t) -> {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			Node c = (Node) (t.getSource());
			c.toFront();
		});

		//when the node is dragged
		action.setOnMouseDragged((t) -> {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			Node c = (Node) (t.getSource());
			c.setTranslateX(c.getTranslateX() + offsetX);
			c.setTranslateY(c.getTranslateY() + offsetY);
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();

		});

		//when the node is done dragging
		action.setOnMouseReleased((t) -> {
			if (orgSceneX >385 && orgSceneX<540 && orgSceneY > 155 && orgSceneY <260)
			{
				if(!draggedRules.contains(action))
				{
					draggedRules.add(action);
				}
			}
			else
			{
				draggedRules.remove(action);
			}
		});
	}

	//checks for valid rules and saves them to the list of rules
	@FXML
	void saveButton()
	{	
		String ruleType = "";
		//error checking for no tiles selected
		if(currentTiles.isEmpty())
		{
			invalidTileRule("Oops, please select one or more tiles");
			return;
		}
		//error checking for invalid number of nodes dragged
		if(draggedRules.size()!=2)
		{
			invalidTileRule("Please drag two options to the rectangle to save the rule");
			return;
		}
		//error checking for invalid combination of nodes dragged
		else
		{
			String[] rules = {draggedRules.get(0).getId().toString(), draggedRules.get(1).getId().toString()};
			Boolean valid = true;
			switch(rules[0])
			{
			case "takeFromPlayer": ruleType="takeFromPlayer";  if(!rules[1].equals("numCards") && !rules[1].equals("numPoints")) {valid = false;} break;
			case "tileNum": ruleType="movePlayer"; if(!rules[1].equals("movePlayer")) {valid = false; } break;
			case "givePlayer": ruleType="givePlayer"; if(!rules[1].equals("numCards") && !rules[1].equals("numPoints")) {valid = false; } break;
			case "numPoints": ruleType=rules[1]; if(!rules[1].equals("takeFromPlayer") && !rules[1].equals("givePlayer")) {valid = false;} break;
			case "numCards": ruleType=rules[1];if(!rules[1].equals("takeFromPlayer") && !rules[1].equals("givePlayer")) {valid = false;} break;
			case "movePlayer": ruleType="movePlayer"; if(!rules[1].equals("tileNum")) {valid = false;} break;
			}
			if(!valid)
			{
				invalidTileRule("Oops, that is an invalid combination for a tile rule. Please drag two valid options to the rectangle.");
				return;
			}
		}
		String ruleToAdd = "";
		for(TextFlow rule: draggedRules)
		{
			String[] rules = {draggedRules.get(0).getId().toString(), draggedRules.get(1).getId().toString()};
			if(!dialogContent.getItems().contains(rule.getId()))
			{
				//the node is a label or the tile drop down
				if (rule.getChildren().size()==1)
				{
					if(rule.getChildren().get(0) instanceof Label)
					{
						ruleToAdd = rule.getChildren().get(0).getAccessibleText() + ruleToAdd;
					}
					else
					{
						ComboBox tileOption = (ComboBox) rule.getChildren().get(0);
						if(tileOption.getValue()== null) {invalidTileRule("Please choose a tile from the dropdown."); return; }
						ruleToAdd = ruleToAdd + tileOption.getValue();
						for(Tile tile: currentTiles)
						{
							System.out.println(gameBoard.tile_findByID(tile.get_id()).get_id());
							System.out.println(tileOption.getValue().toString());
							ArrayList<Tile> value = moveRules.getOrDefault(gameBoard.tile_findByID(tile.get_id()), new ArrayList<Tile>());
							value.add(gameBoard.tile_findByID(tileOption.getValue().toString()));
							moveRules.put(gameBoard.tile_findByID(tile.get_id()), value);
						}
					}
				}
				//get the content of the text field
				else if(rule.getChildren().get(0) instanceof TextField)
				{
					TextField userInput = (TextField) rule.getChildren().get(0);
					if(userInput.getText()=="") {invalidTileRule("Please provide an input for the text field."); return; }
					ruleToAdd += userInput.getText() + " " + rule.getChildren().get(1).getAccessibleText();
					//taking or giving points
					if(rule.getChildren().get(1).getAccessibleText().toString().equals("points"))
					{
						for(Tile tile: currentTiles)
						{
							//take points
							if(ruleType.equals("takeFromPlayer"))
							{
								ArrayList<Integer> value = takePoints.getOrDefault(gameBoard.tile_findByID(tile.get_id()), new ArrayList<Integer>());
								value.add(Integer.valueOf(userInput.getText()));
								takePoints.put(gameBoard.tile_findByID(tile.get_id()), value);
							}
							//give points
							else
							{
								ArrayList<Integer> value = givePoints.getOrDefault(gameBoard.tile_findByID(tile.get_id()), new ArrayList<Integer>());
								value.add(Integer.valueOf(userInput.getText()));
								givePoints.put(gameBoard.tile_findByID(tile.get_id()), value);
							}
						}
					}
					//taking or giving cards
					else
					{
						for(Tile tile: currentTiles)
						{
							//take cards
							if(ruleType.equals("takeFromPlayer"))
							{
								ArrayList<Integer> value = takeCards.getOrDefault(gameBoard.tile_findByID(tile.get_id()), new ArrayList<Integer>());
								value.add(Integer.valueOf(userInput.getText()));
								takeCards.put(gameBoard.tile_findByID(tile.get_id()), value);
							}
							//give cards
							else
							{
								ArrayList<Integer> value = giveCards.getOrDefault(gameBoard.tile_findByID(tile.get_id()), new ArrayList<Integer>());
								value.add(Integer.valueOf(userInput.getText()));
								giveCards.put(gameBoard.tile_findByID(tile.get_id()), value);
							}
						}
					}
				}
			}
		}
		//add all the rules to the popup
		for(Tile tile: currentTiles)
		{
			if(!dialogContent.getItems().contains(tile.getAttributes().get("text") + ": " + ruleToAdd))
			{
				dialogContent.getItems().add(tile.getAttributes().get("text") + ": " + ruleToAdd);
			}
			else
			{
				invalidTileRule("Oops, \""+ tile.getAttributes().get("text") + ": " + ruleToAdd   +"\" already exists");
			}
		}
	}

	//shows the popup if there is an invalid input
	void invalidTileRule(String content)
	{
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Invalid Tile Rule");
		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(type);
		dialog.getDialogPane().setContentText(content);
		dialog.showAndWait();
	}

	private EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if (dialogContent.getSelectionModel().getSelectedIndices().size() != 0) {
				int index = (int) dialogContent.getSelectionModel().getSelectedIndices().get(0);
				dialogContent.getItems().remove(index);
			}
		}
	};

	//save the tile rules, currently only prints
	@FXML
	void saveTileRule() throws IOException
	{
		for(Tile tile: moveRules.keySet())
		{
			for(Tile dest: moveRules.get(tile))
			{
				gameBoard.tile_findByID(tile.get_id()).addRule(new MoveRule(dest));
			}
		}
		for(Tile tile: giveCards.keySet())
		{
			for(int number: giveCards.get(tile))
			{
				gameBoard.tile_findByID(tile.get_id()).addRule(new DrawCardRule(number));
			}
		}
		for(Tile tile: givePoints.keySet())
		{
			for(int number: givePoints.get(tile))
			{
				gameBoard.tile_findByID(tile.get_id()).setScore(new RNG(number));;
			}
		}
		for(Tile tile: takeCards.keySet())
		{
			gameBoard.tile_findByID(tile.get_id()).addRule(new PlayCardRule());
		}
		for(Tile tile: takePoints.keySet())
		{
			for(int number: takePoints.get(tile))
			{
				gameBoard.tile_findByID(tile.get_id()).setScore(new RNG(-number));;
				System.out.println(-number);
			}
		}
		moveRules.clear();
		giveCards.clear();
		givePoints.clear();
		takeCards.clear();
		takePoints.clear();
		Dialog<String> saved = new Dialog<String>();
		String content = "Rules have been saved!";
		saved.setTitle("Saving Tile Rules");
		saved.getDialogPane().setContentText(content);
		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		saved.getDialogPane().getButtonTypes().add(type);
		saved.showAndWait();
	}

	//save the order of turn rules
	@FXML
	void saveTurnRule() throws IOException
	{
		int index =0;
		turnList.removeAll(turnList);
		for(ComboBox dropdown: dropdowns)
		{
			if(dropdown.getValue() !=null)
			{
				turnList.add(dropdown.getValue().toString());
			}
		}
		Dialog<String> addTurnsList = new Dialog<String>();
		//error checking for need to select from the list
		if(turnList.size()==0)
		{
			addTurnsList.setTitle("Invalid Turn Rules");
			ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
			addTurnsList.getDialogPane().getButtonTypes().add(type);
			addTurnsList.getDialogPane().setContentText("The turn list must have at least one rule, please choose an option for at least one dropdown!");
			addTurnsList.showAndWait();
		}
		//show pop up
		else
		{
			String content = "Here is the order of rules the player will follow on their turn: ";
			String turns = String.join(", ", turnList);
			content+=turns;
			addTurnsList.setTitle("Saving Turn Rules");
			addTurnsList.getDialogPane().setContentText(content);
			ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
			addTurnsList.getDialogPane().getButtonTypes().add(type);
			addTurnsList.showAndWait();
		}
		for(String turn: turnList)
		{
			switch(turn)
			{
			case "Move": System.out.println("move"); gameBoard.add_rule(new MoveRule(1)); break;
			case "Play Card": System.out.println("play card"); gameBoard.add_rule(new PlayCardRule()); break;
			case "Draw Card": System.out.println("draw card"); gameBoard.add_rule(new DrawCardRule()); break;
			}
		}
	}


	//view the current tile rules in a new pop up
	@FXML
	void viewRules()
	{
		Button delete = new Button("delete");
		delete.setOnAction(event);
		dialogContent.getItems().add(delete);
		Dialog<String> dialog = new Dialog<String>();
		dialog.setTitle("Current Tile Rules");
		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(type);
		dialog.getDialogPane().setContent(dialogContent);
		dialog.showAndWait();
		dialogContent.getItems().remove(delete);
	}

	@FXML
	void checked(TextFlow action)
	{
		ArrayList<String> currTiles = new ArrayList<String>();
		action.getChildren().forEach((tile) -> tile.setOnMouseClicked((t) -> {
			CheckBox c = (CheckBox) (t.getSource());
			if(c.isSelected())
			{
				currentTiles.add(gameBoard.tile_findByID(c.getAccessibleText()));
				currTiles.add(gameBoard.tile_findByID(c.getAccessibleText()).getAttributes().get("text"));
				updateTiles();
			}
			else
			{
				currentTiles.remove(currentTiles.indexOf(gameBoard.tile_findByID(c.getAccessibleText())));
				currTiles.remove(gameBoard.tile_findByID(c.getAccessibleText()).getAttributes().get("text"));
				updateTiles();
			}
		}));
	}

	@FXML
	void updateTiles()
	{
		ArrayList<String> currTiles = new ArrayList<String>();
		for(Tile tile: currentTiles)
		{
			currTiles.add(tile.getAttributes().get("text"));
			tileName.setText("Tile(s) Selected: " + String.join(", ", currTiles));
		}
		if(currentTiles.size()==0)
		{
			tileName.setText("");

		}
	}

	//adding a drop down box for the turn rule editor
	@FXML
	void addDropDown(MouseEvent event) {
		if (numVisible < 5) {
			int hidden;
			for (int i = 0; i < visible.length; i++) {
				if (visible[i] == 0) {
					hidden = i;
					dropdowns[hidden].getParent().setVisible(true);
					dropdowns[hidden].getParent().setManaged(true);
					numVisible++;
					visible[i] = 1;
					return;
				}
			}
		}
	}

	//deleting a drop down box for the turn rule editor
	@FXML
	void deleteDropDown(MouseEvent event) {
		((Node) event.getSource()).getParent().setVisible(false);
		((Node) event.getSource()).getParent().setManaged(false);
		((ComboBox<String>) ((Node) event.getSource()).getParent().getChildrenUnmodifiable().get(0)).getSelectionModel()
		.clearSelection();
		numVisible--;
		for (int i = 0; i < deleteButtons.length; i++) {
			if ((((Node) event.getSource()).getId()).equals(deleteButtons[i])) {
				visible[i] = 0;
				return;
			}
		}
	}

	// initializes movementRule editor
	@FXML 
	void intializeMovementRule() {
		addMessage.setVisible(false);
		transitionAlreadyExists.setVisible(false);
		transitionButtonContainer.setVisible(false);
		doneEditingTransition.setVisible(false);
		
		// get current board from local storage and display tiles
		ArrayList<Tile> boardTiles = new ArrayList<Tile>();
		LocalStorage localStorage = LocalStorage.getInstance();
		if(localStorage.storage.containsKey("board")) {
			gameBoard = (Board) localStorage.storage.get("board");
			boardTiles = gameBoard.get_tiles();
			for(Tile tile: boardTiles) {
				Rectangle t = new Rectangle();
				t.setX(tile.getCoordinate().get(0)*40);
				t.setY(tile.getCoordinate().get(1)*40);
				t.setWidth(30);
				t.setHeight(30);
				EventHandler<MouseEvent> clickTile = new EventHandler<MouseEvent>() { 
					@Override 
					public void handle(MouseEvent e) { 
						selectTile(e);
					} 
				};
				tileMap.put(t, tile);
				t.setOnMousePressed(clickTile);
				board.getChildren().add(t);
			}
		}
		// TODO: get current board transitions and display them
		
	}

	// handles add transition and cancel buttons
	@FXML
	void addTransition() {
		addTransitionSelected = !addTransitionSelected;
		if (addTransitionSelected) {
			transitionAlreadyExists.setVisible(false);
			addMessage.setVisible(true);
			addMessage.toFront();
			addTransition.setText("Cancel");
		} else {
			// cancel selected
			addTransition.setText("Add Transition");
			addMessage.setVisible(false);
			if (selectedTile1 != null) {
				selectedTile1.setFill(Color.BLACK);
			}
			if (selectedTile2 != null) {
				selectedTile2.setFill(Color.BLACK);	
			}
			selectedTile1 = null;
			selectedTile2 = null;
		}
	}

	// highlighting selected tiles and drawing a transition arrow between them
	// also handles editing and deleting a transition
	@FXML
	void selectTile(MouseEvent event) {
		if(addTransitionSelected) {
			Rectangle tile = (Rectangle) event.getSource();
			if (selectedTile1 == null) {
				selectedTile1 = tile;
				tile.setFill(Color.RED);
			} else if (selectedTile2 == null) {
				selectedTile2 = tile;
				tile.setFill(Color.RED);

				//computing arrowhead points
				double arrowAngle = Math.toRadians(45.0);
				double arrowLength = 10.0;

				double lineAngle = Math.atan2(selectedTile1.getY() - selectedTile2.getY(), selectedTile1.getX() - selectedTile2.getX());

				double x1 = Math.cos(lineAngle + arrowAngle) * arrowLength + selectedTile2.getX();
				double y1 = Math.sin(lineAngle + arrowAngle) * arrowLength + selectedTile2.getY();

				double x2 = Math.cos(lineAngle - arrowAngle) * arrowLength + selectedTile2.getX();
				double y2 = Math.sin(lineAngle - arrowAngle) * arrowLength + selectedTile2.getY();

				Polyline arrow = new Polyline();
				arrow.getPoints().addAll(new Double[]{
						selectedTile1.getX(), selectedTile1.getY(), 
						selectedTile2.getX(), selectedTile2.getY(),
						selectedTile2.getX(), selectedTile2.getY(), 
						x1, y1,
						selectedTile2.getX(), selectedTile2.getY(), 
						x2, y2
				});
				arrow.setStroke(Color.GREEN);
				arrow.setStrokeWidth(2);
				arrow.setTranslateX(tile.getWidth() / 2);
				arrow.setTranslateY(tile.getHeight() / 2);

				// check if the selected tiles already have a transition
				Rectangle tiles[] = {selectedTile1, selectedTile2};
				Rectangle reverseTiles[] = {selectedTile2, selectedTile1};
				if (tileMapping.containsKey(Arrays.asList(tiles)) || tileMapping.containsKey(Arrays.asList(reverseTiles))) {
					addMessage.setVisible(false);
					transitionAlreadyExists.setVisible(true);
				} else {
					// Creating selectTransition handler
					EventHandler<MouseEvent> selectTransition = new EventHandler<MouseEvent>() { 
						@Override 
						public void handle(MouseEvent e) { 
							if (!addTransitionSelected) {
								Polyline transition = (Polyline) e.getSource();
								currentTransition = transition;
								transition.setStroke(Color.RED);
								transitionAlreadyExists.setVisible(false);
								addButtonContainer.setVisible(false);
								transitionButtonContainer.setVisible(true);
								doneEditingTransition.setVisible(true);
							}
						} 
					};
					arrow.setOnMousePressed(selectTransition);

					tileMapping.put(Arrays.asList(tiles), arrow);
					board.getChildren().add(arrow);	
				}

				// after arrow is drawn, revert button to Add Transition and revert to initial tiles and messages
				addTransition.setText("Add Transition");
				addMessage.setVisible(false);
				selectedTile1.setFill(Color.BLACK);
				selectedTile2.setFill(Color.BLACK);
				addTransitionSelected = !addTransitionSelected;
				selectedTile1 = null;
				selectedTile2 = null;
			} 
		}
	}

	// changes direction of the selected transition arrow 
	@FXML
	void changeTransition() {
		ArrayList<List<Rectangle>> directions = new ArrayList<List<Rectangle>>();
		for (Map.Entry<List<Rectangle>, Polyline> entry : tileMapping.entrySet()) {
			if (entry.getValue().equals(currentTransition)) {
				directions.add(entry.getKey());
			}
		}
		Rectangle newDirection[] = {directions.get(0).get(1), directions.get(0).get(0)};

		double arrowAngle = Math.toRadians(45.0);
		double arrowLength = 10.0;

		double lineAngle = Math.atan2(directions.get(0).get(1).getY() - directions.get(0).get(0).getY(), directions.get(0).get(1).getX() - directions.get(0).get(0).getX());
		double x1 = Math.cos(lineAngle + arrowAngle) * arrowLength + directions.get(0).get(0).getX();
		double y1 = Math.sin(lineAngle + arrowAngle) * arrowLength + directions.get(0).get(0).getY();

		double x2 = Math.cos(lineAngle - arrowAngle) * arrowLength + directions.get(0).get(0).getX();
		double y2 = Math.sin(lineAngle - arrowAngle) * arrowLength + directions.get(0).get(0).getY();
		if (directions.size() == 1) {
			// add second arrowHead
			currentTransition.getPoints().addAll(new Double[]{
					directions.get(0).get(1).getX(), directions.get(0).get(1).getY(),
					directions.get(0).get(0).getX(), directions.get(0).get(0).getY(),
					x1, y1,
					directions.get(0).get(0).getX(), directions.get(0).get(0).getY(),
					x2, y2
			});
			tileMapping.put(Arrays.asList(newDirection), currentTransition);
		} else {
			Rectangle toRemove[] = {directions.get(0).get(0), directions.get(0).get(1)};
			tileMapping.remove(Arrays.asList(toRemove));
			Polyline newTransition = new Polyline();
			newTransition.getPoints().addAll(new Double[]{
					directions.get(0).get(1).getX(), directions.get(0).get(1).getY(), 
					directions.get(0).get(0).getX(), directions.get(0).get(0).getY(),
					directions.get(0).get(0).getX(), directions.get(0).get(0).getY(), 
					x1, y1,
					directions.get(0).get(0).getX(), directions.get(0).get(0).getY(), 
					x2, y2
			});
			newTransition.setStroke(Color.RED);
			newTransition.setStrokeWidth(2);
			newTransition.setTranslateX(directions.get(0).get(0).getWidth() / 2);
			newTransition.setTranslateY(directions.get(0).get(0).getHeight() / 2);
			board.getChildren().remove(currentTransition);
			currentTransition = newTransition;
			board.getChildren().add(currentTransition);
			tileMapping.put(Arrays.asList(newDirection), currentTransition);
		}
	}

	// deletes the selected transition
	@FXML
	void deleteTransition() {
		for (List<Rectangle> tilePair : tileMapping.keySet()) {
			if (tileMapping.get(tilePair).equals(currentTransition)) {
				tileMapping.remove(tilePair);
			}
		}
		board.getChildren().remove(currentTransition);
		addButtonContainer.setVisible(true);
		transitionButtonContainer.setVisible(false);
		doneEditingTransition.setVisible(false);
	}

	// deletes the selected transition
	@FXML
	void doneEditingTransition() {
		addButtonContainer.setVisible(true);
		transitionButtonContainer.setVisible(false);
		doneEditingTransition.setVisible(false);
		currentTransition.setStroke(Color.GREEN);
	}	
	
	@FXML
	void saveMovementRule() {
		// remove all neighbors in local storage
		ArrayList<Tile> boardTiles = new ArrayList<Tile>();
		LocalStorage localStorage = LocalStorage.getInstance();
		if (localStorage.storage.containsKey("board")) {
			gameBoard = (Board) localStorage.storage.get("board");
			boardTiles = gameBoard.get_tiles();
			for (Tile tile: boardTiles) {
				tile.remove_neighbors();
			}
		}
		// re-add tile neighbors to local storage
		for (List<Rectangle> tiles: tileMapping.keySet()) {
			Tile tileToEdit = tileMap.get(tiles.get(0));
			tileToEdit.update_neighbor(tileMap.get(tiles.get(1)));
		}
		Dialog<String> saved = new Dialog<String>();
		String content = "Rules have been saved!";
		saved.setTitle("Saving Movement Rules");
		saved.getDialogPane().setContentText(content);
		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		saved.getDialogPane().getButtonTypes().add(type);
		saved.showAndWait();
	}
	
	@FXML
	void saveWinCondition() {
		int points = Integer.parseInt(pointsToWin.getText());
		LocalStorage localStorage = LocalStorage.getInstance();
		if(localStorage.storage.containsKey("board")) {
			gameBoard = (Board) localStorage.storage.get("board");
			gameBoard.setWinCondition(points);
		}
		Dialog<String> saved = new Dialog<String>();
		String content = "Rules have been saved!";
		saved.setTitle("Saving Movement Rules");
		saved.getDialogPane().setContentText(content);
		ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		saved.getDialogPane().getButtonTypes().add(type);
		saved.showAndWait();
	}


}