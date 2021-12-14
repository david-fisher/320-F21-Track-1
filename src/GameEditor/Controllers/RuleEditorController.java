package GameEditor.Controllers;

import Objects.Board;
import Objects.JSONConverter;
import Objects.Tile;
import Objects.Token;
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
    private TextFlow takeFromPlayer, givePlayer, movePlayer, tileNum, numCards, numPoints;
    
    // hash table with array tile 1 tile 2 and value group
    private LinkedHashMap<List<Rectangle>, Polyline> tileMapping = new LinkedHashMap<List<Rectangle>, Polyline>();
    private Polyline currentTransition;
    private boolean addTransitionSelected = false;
    
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
    private Board newBoard;
    private ArrayList<Tile> tileObjs;
    private String currTile;
    private ArrayList<String> currentTiles = new ArrayList<String>();


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
		//TODO FIX backend call
//        if(!initialized)
//        {
//
//        	 Tile tile1 = new Tile("Tile 1", 1, 1, null, null, null);
//             Tile tile2 = new Tile("Tile 2", 1, 2, null, null, null);
//             Tile tile3 = new Tile("Tile 3", 2, 2, null, null, null);
//             Tile tile4 = new Tile("Tile 4", 2, 1, null, null, null);
//             tileObjs = new ArrayList<Tile>();
//             tileObjs.add(tile1);
//             tileObjs.add(tile2);
//             tileObjs.add(tile3);
//             tileObjs.add(tile4);
//
//        	newBoard = new Board("tempBoard", tileObjs);
//            for(Tile tile: tileObjs)
//            {
//                tileOptions.getItems().add(tile.ID);
//            	TextFlow tf = new TextFlow();
//            	tf.setAccessibleText(tile.ID);
//            	CheckBox cb = new CheckBox();
//            	cb.setText(tile.ID);
//            	cb.setAccessibleText(tile.ID);
//            	tf.getChildren().add(cb);
//            	tiles.getChildren().add(tf);
//            	checked(tf);
//            }
//            initialized = true;
//        }
    }

    //drag and drop for the actions in the tile rule editor
    @FXML
    void dragAndDrop(TextFlow action)
    {	
        action.setCursor(Cursor.HAND);
        action.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            Node c = (Node) (t.getSource());
            c.toFront();
        });
        action.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            Node c = (Node) (t.getSource());
            c.setTranslateX(c.getTranslateX() + offsetX);
            c.setTranslateY(c.getTranslateY() + offsetY);
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            
        });
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
    	if(currentTiles.isEmpty())
    	{
    		invalidTileRule("Oops, please select one or more tiles");
    		return;
    	}
    	if(draggedRules.size()!=2)
		{
    		invalidTileRule("Please drag two options to the rectangle to save the rule");
	        return;
		}
    	else
    	{
    		String[] rules = {draggedRules.get(0).getId().toString(), draggedRules.get(1).getId().toString()};
    		Boolean valid = true;
    		switch(rules[0])
    		{
    		case "takeFromPlayer": if(!rules[1].equals("numCards") && !rules[1].equals("numPoints")) {valid = false;} break;
    		case "tileNum": if(!rules[1].equals("movePlayer")) {valid = false;} break;
    		case "givePlayer": if(!rules[1].equals("numCards") && !rules[1].equals("numPoints")) {valid = false;} break;
    		case "numPoints": if(!rules[1].equals("takeFromPlayer") && !rules[1].equals("givePlayer")) {valid = false;} break;
    		case "numCards": if(!rules[1].equals("takeFromPlayer") && !rules[1].equals("givePlayer")) {valid = false;} break;
    		case "movePlayer": if(!rules[1].equals("tileNum")) {valid = false;} break;
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
    		if(!dialogContent.getItems().contains(rule.getId()))
    		{
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
    		    	}
    			}
    			else if(rule.getChildren().get(0) instanceof Label)
    			{
    				TextField userInput = (TextField) rule.getChildren().get(1);
    				if(userInput.getText()=="") {invalidTileRule("Please provide an input for the text field."); return; }
    				ruleToAdd += rule.getChildren().get(0).getAccessibleText() + " " + userInput.getText();
    			}
    			else if(rule.getChildren().get(0) instanceof TextField)
    			{
    				TextField userInput = (TextField) rule.getChildren().get(0);
    				if(userInput.getText()=="") {invalidTileRule("Please provide an input for the text field."); return; }
    				ruleToAdd += userInput.getText() + " " + rule.getChildren().get(1).getAccessibleText();
    			}
    		}
    	}
    	for(String tile: currentTiles)
    	{
    		if(!dialogContent.getItems().contains(tile + ": " + ruleToAdd))
    		{
            	dialogContent.getItems().add(tile + ": " + ruleToAdd);
    		}
    		else
    		{
            	invalidTileRule("Oops, \""+ tile + ": " + ruleToAdd   +"\" already exists");
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
    	Token newgame = new Token("game1");
    	newgame.update_gameboard(newBoard);
    	newgame.get_gameboard().update_tiles(tileObjs);
    	//TODO FIX backend call
    	//System.out.println(newgame.get_gameboard().get_tiles().get(0).ID);
        JSONConverter savedGames = new JSONConverter(newgame, "test.json");
        savedGames.To_JSON();
    	System.out.println(draggedRules);
    }
    
    //save the order of turn rules
    @FXML
    void saveTurnRule()
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
    	if(turnList.size()==0)
    	{
    		addTurnsList.setTitle("Invalid Turn Rules");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            addTurnsList.getDialogPane().getButtonTypes().add(type);
            addTurnsList.getDialogPane().setContentText("The turn list must have at least one rule, please choose an option for at least one dropdown!");
            addTurnsList.showAndWait();
    	}
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
    	action.getChildren().forEach((tile) -> tile.setOnMouseClicked((t) -> {
    		CheckBox c = (CheckBox) (t.getSource());
    		if(c.isSelected())
    		{
    			currentTiles.add(c.getAccessibleText());
    			tileName.setText("Tile(s) Selected: " + String.join(", ", currentTiles));

    		}
    		else
    		{
    			currentTiles.remove(currentTiles.indexOf(c.getAccessibleText()));
    			tileName.setText("Tile(s) Selected: " + String.join(", ", currentTiles));

    		}
    	}));

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

	            double lineAngle = Math.atan2(selectedTile1.getLayoutY() - selectedTile2.getLayoutY(), selectedTile1.getLayoutX() - selectedTile2.getLayoutX());

	            double x1 = Math.cos(lineAngle + arrowAngle) * arrowLength + selectedTile2.getLayoutX();
	            double y1 = Math.sin(lineAngle + arrowAngle) * arrowLength + selectedTile2.getLayoutY();

	            double x2 = Math.cos(lineAngle - arrowAngle) * arrowLength + selectedTile2.getLayoutX();
	            double y2 = Math.sin(lineAngle - arrowAngle) * arrowLength + selectedTile2.getLayoutY();
	      
	            Polyline arrow = new Polyline();
	            arrow.getPoints().addAll(new Double[]{
	            		selectedTile1.getLayoutX(), selectedTile1.getLayoutY(), 
	            		selectedTile2.getLayoutX(), selectedTile2.getLayoutY(),
	            		selectedTile2.getLayoutX(), selectedTile2.getLayoutY(), 
	            		x1, y1,
	            		selectedTile2.getLayoutX(), selectedTile2.getLayoutY(), 
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
	                    	Polyline transition = (Polyline) e.getSource();
	                    	currentTransition = transition;
	                    	transition.setStroke(Color.RED);
	                    	transitionAlreadyExists.setVisible(false);
	                    	addButtonContainer.setVisible(false);
	                    	transitionButtonContainer.setVisible(true);
	                    	doneEditingTransition.setVisible(true);
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

        double lineAngle = Math.atan2(directions.get(0).get(1).getLayoutY() - directions.get(0).get(0).getLayoutY(), directions.get(0).get(1).getLayoutX() - directions.get(0).get(0).getLayoutX());
		double x1 = Math.cos(lineAngle + arrowAngle) * arrowLength + directions.get(0).get(0).getLayoutX();
        double y1 = Math.sin(lineAngle + arrowAngle) * arrowLength + directions.get(0).get(0).getLayoutY();

        double x2 = Math.cos(lineAngle - arrowAngle) * arrowLength + directions.get(0).get(0).getLayoutX();
        double y2 = Math.sin(lineAngle - arrowAngle) * arrowLength + directions.get(0).get(0).getLayoutY();
		if (directions.size() == 1) {
            // add second arrowHead
            currentTransition.getPoints().addAll(new Double[]{
            		directions.get(0).get(1).getLayoutX(), directions.get(0).get(1).getLayoutY(),
            		directions.get(0).get(0).getLayoutX(), directions.get(0).get(0).getLayoutY(),
            		x1, y1,
            		directions.get(0).get(0).getLayoutX(), directions.get(0).get(0).getLayoutY(),
            		x2, y2
            });
			tileMapping.put(Arrays.asList(newDirection), currentTransition);
		} else {
			Rectangle toRemove[] = {directions.get(0).get(0), directions.get(0).get(1)};
			tileMapping.remove(Arrays.asList(toRemove));
			Polyline newTransition = new Polyline();
			newTransition.getPoints().addAll(new Double[]{
					directions.get(0).get(1).getLayoutX(), directions.get(0).get(1).getLayoutY(), 
            		directions.get(0).get(0).getLayoutX(), directions.get(0).get(0).getLayoutY(),
            		directions.get(0).get(0).getLayoutX(), directions.get(0).get(0).getLayoutY(), 
            		x1, y1,
            		directions.get(0).get(0).getLayoutX(), directions.get(0).get(0).getLayoutY(), 
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
		tileMapping.values().remove(currentTransition);
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


}