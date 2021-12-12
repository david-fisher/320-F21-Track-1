package GameEditor.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.HashMap;

import Objects.RNG;

public class RNGController {

    // private ArrayList<Pair<Integer, RNG>> vars = new ArrayList<Pair<Integer,
    // RNG>>();
    private ArrayList<RNG> vars = new ArrayList<RNG>();
    // private HashMap<String, Pair<Integer, RNG>> map = new HashMap<String,
    // Pair<Integer, RNG>>();
    private HashMap<String, RNG> map = new HashMap<String, RNG>();

    private ListView dialogContent = new ListView();
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

    @FXML
    private TextField QuantityField;

    @FXML
    private void viewRNG() {
        Dialog<String> dialog = new Dialog<String>();
        // Setting the title
        dialog.setTitle("RNG List");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        // Setting the content of the dialog
        dialog.setContentText("This is a sample dialog");
        // Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.getDialogPane().setContent(dialogContent);
        // Setting the label
        dialog.showAndWait();
    }

    @FXML
    private void addNewRNG() {
        System.out.println("hello");
        boolean hasMin = !MinField.getText().equalsIgnoreCase("min");
        boolean hasMax = !MaxField.getText().equalsIgnoreCase("max");
        // boolean hasQuantity = !QuantityField.getText().equalsIgnoreCase("enter
        // quantity");
        boolean SpinnerDice = hasMax && hasMin;
        if (SpinnerDice) {
            System.out.println(NameField.getText());
            System.out.println(MinField.getText());
            System.out.println(MaxField.getText());
            // System.out.println(QuantityField.getText());
            String name = NameField.getText();
            int[] temp = new int[2];
            temp[0] = Integer.parseInt(MinField.getText());
            temp[1] = Integer.parseInt(MaxField.getText());
            // Pair<Integer, RNG> rng = new Pair<Integer,
            // RNG>(Integer.parseInt(QuantityField.getText()), new RNG(temp));
            RNG rng = new RNG(temp);
            vars.add(rng);
            map.put(name, rng);
            String stringView = "";
            // Label label = new Label(name + " Amount: " + rng.getKey() + " \nMin: " +
            // temp[0] + " Max: " + temp[1]);
            Label label = new Label(name + " Type:  \nMin: " + temp[0] + " Max: " + temp[1]);
            dialogContent.getItems().add(0, label);
        } else {
            System.out.println("missing fields");
        }
    }

    @FXML
    private ComboBox<String> TypeSelection;

    @FXML
    private Label RangeSelection;

    // @FXML
    // private Label QuantitySelection;

    @FXML
    private Label DeckSelection;

    public void initialize() {
        Button b = new Button("Delete RNG");
        b.setOnAction(event);
        dialogContent.getItems().add(b);
        TypeSelection.getItems().setAll("Dice", "Spinner", "Cards");
        TypeSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldVal, String newVal) {
                if (oldVal != null) {
                    switch (oldVal) {
                        // case "Dice": RangeSelection.setVisible(false);
                        // QuantitySelection.setVisible(false); break;
                        // case "Spinner": RangeSelection.setVisible(false);
                        // QuantitySelection.setVisible(false); break;
                        case "Dice":
                            RangeSelection.setVisible(false);
                            break;
                        case "Spinner":
                            RangeSelection.setVisible(false);
                            break;
                        case "Cards":
                            DeckSelection.setVisible(false);
                            break;
                    }
                }
                if (newVal != null) {
                    switch (newVal) {
                        // case "Dice": RangeSelection.setVisible(true);
                        // QuantitySelection.setVisible(true); break;
                        // case "Spinner": RangeSelection.setVisible(true);
                        // QuantitySelection.setVisible(true); break;
                        case "Dice":
                            RangeSelection.setVisible(true);
                            break;
                        case "Spinner":
                            RangeSelection.setVisible(true);
                            break;
                        case "Cards":
                            DeckSelection.setVisible(true);
                            break;
                    }
                }
            }
        });
    }
}