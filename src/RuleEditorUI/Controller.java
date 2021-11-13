package application;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ComboBox<String> dropdown0;
    @FXML
    private ComboBox<String> dropdown1;
    @FXML
    private ComboBox<String> dropdown2;
    @FXML
    private ComboBox<String> dropdown3;
    @FXML
    private ComboBox<String> dropdown4;

    @FXML
    private ComboBox<String>[] dropdowns = new ComboBox[5];
    private int numVisible = 5;
    private int[] visible = { 1, 1, 1, 1, 1 };
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
    void mousePressed(MouseEvent event) {
        ((Node) event.getSource()).setCursor(Cursor.HAND);
        ((Node) event.getSource()).setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            TextFlow c = (TextFlow) (t.getSource());

            c.setTranslateX(c.getTranslateX() + offsetX);
            c.setTranslateY(c.getTranslateY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
        });
    }

    @FXML
    void mouseReleased(MouseEvent event) {
        ((Node) event.getSource()).setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            TextFlow c = (TextFlow) (t.getSource());
            c.toFront();

        });
    }

    public static void main(String[] args) {

    }

}
