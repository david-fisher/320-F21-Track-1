 package GameEditor.Controllers;

import GameEditor.DeckIds;
import Objects.RNG;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.HashMap;

/* class RNGController
    This is the JavaFX controller to the RNG.fxml file.
    The controller supports two types of inputs, a spinner/die or a deck of cards.
    The deck of cards is imported from ./TokenController
*/
public class RNGController {

    private ArrayList<RNG> vars = new ArrayList<RNG>();
    private HashMap<String, RNG> map = new HashMap<String, RNG>();

    // DeckIds is a final class that is also used in TokenController to
    // maintain a list of Deck objects between controllers
    private DeckIds ids = DeckIds.getInstance();

    // Alert is used to inform the user has given an improper input to the fields
    private Alert a = new Alert(Alert.AlertType.NONE);

    // dialogContent is the data displayed by viewRNG()
    private ListView dialogContent = new ListView();

    // setting the event handler for the remove button on the dialog
    private EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            if (dialogContent.getSelectionModel().getSelectedIndices().size() != 0) {
                int index = (int) dialogContent.getSelectionModel().getSelectedIndices().get(0);
                vars.remove(map.remove(dialogContent.getItems().get(index)));
                dialogContent.getItems().remove(index);
            }
        }
    };

    @FXML
    private TextField MinField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField MaxField;

//    @FXML
//    private TextField QuantityField;

    // viewRNG() is a method attached to a button that displays a dialog of the current RNG objects
    @FXML
    private void viewRNG() {

        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("RNG List");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText("This is a sample dialog");
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.getDialogPane().setContent(dialogContent);
        //Setting the label
        dialog.showAndWait();
    }

    @FXML
    private ComboBox<String> TypeSelection;

    // addNewRNG() checks the user inputs and adds a new RNG object
    @FXML
    private void addNewRNG() {
        // get the selected type from the fxml file
        String type = TypeSelection.getSelectionModel().getSelectedItem();
        switch (type) {
            case "Dice":
            case "Spinner":
                String name = NameField.getText();
                int[] temp = new int[2];
                // the try-catch is to make sure that the inputs are proper
                try {
                    temp[0] = Integer.parseInt(MinField.getText());
                    temp[1] = Integer.parseInt(MaxField.getText());
                } catch (NumberFormatException nfe) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Input Error");
                    a.show();
                    return;
                }

                // this object is from the Objects folder
                RNG rng = new RNG(temp);
                vars.add(rng);
                map.put(name, rng);
                String stringView = "";
                Label label = new Label(name + " Type:  \nMin: " + temp[0] + " Max: " + temp[1]);
                dialogContent.getItems().add(0, label);
                break;
            case "Cards":
                break;
            default:
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Input Error");
                a.show();
                return;
        }
    }

    @FXML
    private Label RangeSelection;

//    @FXML
//    private Label QuantitySelection;

    @FXML
    private Label DeckSelection;

    @FXML
    private ComboBox Decks;

    // updates the DeckIds from the ids variable
    public void updateDeckSelection() {
        Decks.getItems().setAll(ids.Values());
    }

    // initialize() is called at the startup for JavaFX applications
    public void initialize() {
        updateDeckSelection();
        Button b = new Button("Delete RNG");
        b.setOnAction(event);
        dialogContent.getItems().add(b);
        TypeSelection.getItems().setAll("Dice", "Spinner", "Cards");

        // add the event listerner for type changes
        TypeSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldVal, String newVal) {
                // remove old display
                if (oldVal != null) {
                    switch(oldVal) {
                        case "Dice":
                        case "Spinner":
                            RangeSelection.setVisible(false); break;
                        case "Cards": DeckSelection.setVisible(false); break;
                    }
                }
                // display new
                if (newVal != null) {
                    switch(newVal) {
                        case "Dice":
                        case "Spinner":
                            RangeSelection.setVisible(true); break;
                        case "Cards": DeckSelection.setVisible(true); updateDeckSelection(); break;
                    }
                }
            }
        });
    }
}