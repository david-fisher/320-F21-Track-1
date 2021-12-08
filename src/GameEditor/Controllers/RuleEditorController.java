package GameEditor.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class RuleEditorController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private ComboBox<String> dropdown0, dropdown1, dropdown2, dropdown3, dropdown4;
    @FXML
    private Rectangle selectedTile1 = null;
    @FXML
    private Rectangle selectedTile2 = null;
    @FXML
    private StackPane board;
    @FXML
    private Button addTransition;
    @FXML
    private Label addMessage;
    
    private boolean addTransitionSelected = false;
    @FXML
    private TextFlow takeFromPlayer, givePlayer, movePlayer, tileNum, numCards, numPoints;
    
    private String[] transitions = {"\u2192", "\u2190", "\u2194"};
    private int currentTransition = 0;
    
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
    
    @FXML
    private TextFlow tileNum1, tileNum2;
    @FXML
    private CheckBox tile1, tile2;
    @FXML
    private Label tileName, rule1;


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
        checked(tileNum1);
        checked(tileNum2);
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
    		    	ruleToAdd = rule.getChildren().get(0).getAccessibleText() + ruleToAdd;
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
    	dialogContent.getItems().add(ruleToAdd);
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
    
    //save the tile rules, currently only prints
    @FXML
    void saveTileRule()
    {
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
    	Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Current Tile Rules");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.getDialogPane().setContent(dialogContent);
        dialog.showAndWait();
    }
    
    @FXML
    void checked(TextFlow action)
    {
    	action.getChildren().forEach((tile) -> tile.setOnMouseClicked((t) -> {
    		CheckBox c = (CheckBox) (t.getSource());
    		if(c.isSelected())
    		{
    			tileName.setText("Tile Selected: tile 1");
    		}
    		else
    		{
    			tileName.setText("");
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
    
    @FXML 
    void intializeMovementRule () {
    	addMessage.setVisible(false);
    }
    
    @FXML
    void clickAddTransition(MouseEvent event) {
    	addTransitionSelected = !addTransitionSelected;
    	if (addTransitionSelected) {
    		addMessage.setVisible(true);
    		addTransition.setText("Cancel");
    	} else {
    		// cancel selected
    		addTransition.setText("Add Transition");
    		addMessage.setVisible(false);
    		selectedTile1.setFill(Color.BLACK);
    		selectedTile2.setFill(Color.BLACK);
    		selectedTile1 = null;
    		selectedTile2 = null;
    	}
    }
    
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
	    		Line line = new Line(selectedTile1.getTranslateX(), selectedTile1.getTranslateY(), selectedTile2.getTranslateX(), selectedTile2.getTranslateY());
	    		
	    		line.setTranslateX(tile.getWidth() / 2);
	    		
	    		line.setTranslateY(tile.getHeight() / 2);
	    		line.setStroke(Color.WHITE);
	    		board.getChildren().add(line);
	    	} 
    	}
    }

}