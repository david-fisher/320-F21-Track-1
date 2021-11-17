package GameEditor.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class GameEditorMainController {

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
    
    private String[] transitions = {"\u2192", "\u2190", "\u2194"};
    private int currentTransition = 0;
    
    @FXML
    private ComboBox<String>[] dropdowns = new ComboBox[5];
    private int numVisible = 1;
    private int[] visible = { 1, 0,0,0,0};
    private String[] deleteButtons = { "d0", "d1", "d2", "d3", "d4" };
    private double orgSceneX, orgSceneY;

    @FXML
    private URL location;

    @FXML
    void initializeTurnRule() {
        dropdowns[0] = dropdown0;
        dropdowns[1] = dropdown1;
        dropdowns[2] = dropdown2;
        dropdowns[3] = dropdown3;
        dropdowns[4] = dropdown4;
        dropdowns[1].getParent().setManaged(false);;
        dropdowns[2].getParent().setManaged(false);;
        dropdowns[3].getParent().setManaged(false);;
        dropdowns[4].getParent().setManaged(false);;
    }

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

    @FXML
    void deleteDropDown(MouseEvent event) {
        // make the appropriate nodes not visible and clear and selection for that dropdown
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

    @FXML
    public void changeAnchorScene(Event event) throws IOException {
        Node node = (Node) event.getSource();
        String tokenType = (String) node.getUserData();
        Stage app_stage = (Stage) node.getScene().getWindow();

        if (tokenType.equals("gameToken")) {
            Parent addMovementPiece = FXMLLoader.load(getClass().getResource("Views/customToken.fxml"));
            app_stage.setScene(new Scene(addMovementPiece));
        }
        else if (tokenType == ""){}
        else {}
    }

    @FXML
    //controller method to add BoardEditor node to corresponding tab in GameEditorMain
    private Tab boardEditor;






}