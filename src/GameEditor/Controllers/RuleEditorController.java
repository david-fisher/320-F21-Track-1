package GameEditor.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class RuleEditorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ComboBox<String> dropdown0, dropdown1, dropdown2, dropdown3, dropdown4;
    @FXML
    private Button transition;
    @FXML
    private Button addTransition;
    @FXML
    private HBox modifyTransitionButtons;
    @FXML
    private Button changeTransition;
    @FXML
    private Button deleteTransition;
    
    @FXML
    private TextFlow takeFromPlayer, givePlayer, movePlayer, tileNum, numCards, numPoints;
    
    private String[] transitions = {"\u2192", "\u2190", "\u2194"};
    private int currentTransition = 0;
    
    @FXML
    private ComboBox<String>[] dropdowns = new ComboBox[5];
    private int numVisible = 1;
    private int[] visible = { 1, 0,0,0,0};
    private String[] deleteButtons = { "d0", "d1", "d2", "d3", "d4" };
    private double orgSceneX, orgSceneY;
    private boolean turnRuleClicked = false;
    private Set<TextFlow> draggedRules = new HashSet<TextFlow>();
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
            	draggedRules.add(action);
    		}
        	else
        	{
        		draggedRules.remove(action);
        	}
        });
    }
    
    @FXML
    //prints the rules that are dragged into the box
    void saveButton()
    {		
		System.out.println(draggedRules);
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
    void intializeMovementRule(MouseEvent event) {
        modifyTransitionButtons.setVisible(false);
        addTransition.setVisible(true);
    }
    
    @FXML
    void selectTransition(MouseEvent event) {
        addTransition.setVisible(false);
        modifyTransitionButtons.setVisible(true);
    }
    
    @FXML
    void changeTransition(MouseEvent event) {
        currentTransition = (currentTransition+1) % 3;
        transition.setText(transitions[currentTransition]);
    }

    @FXML
    void deleteTransition(MouseEvent event) {
        transition.setVisible(false);
    }

}