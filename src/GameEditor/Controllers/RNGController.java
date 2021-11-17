 package GameEditor.Controllers;

import Objects.RNG;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.util.ArrayList;

public class RNGController {

    private ArrayList<Pair<Integer, RNG>> vars = new ArrayList<Pair<Integer, RNG>>();

    @FXML
    private TextField MinField;

    @FXML
    private TextField MaxField;

    @FXML
    private TextField QuantityField;

    @FXML
    private void addNewRNG() {
        System.out.println("hello");
        boolean hasMin = !MinField.getText().equalsIgnoreCase("min");
        boolean hasMax = !MaxField.getText().equalsIgnoreCase("max");
        boolean hasQuantity = !QuantityField.getText().equalsIgnoreCase("enter quantity");
        boolean SpinnerDice = hasQuantity && hasMax && hasMin;
        if (SpinnerDice) {
            System.out.println(MinField.getText());
            System.out.println(MaxField.getText());
            System.out.println(QuantityField.getText());
            double[] temp = new double[2];
            temp[0] = Double.parseDouble(MinField.getText());
            temp[1] = Double.parseDouble(MaxField.getText());
            vars.add(new Pair<Integer, RNG>(Integer.parseInt(QuantityField.getText()), new RNG(temp)));
        } else {
            System.out.println("missing fields");
        }
    }

    @FXML
    private ComboBox<String> TypeSelection;

    @FXML
    private Label RangeSelection;

    @FXML
    private Label QuantitySelection;

    @FXML
    private Label DeckSelection;

    public void initialize() {
        TypeSelection.getItems().setAll("Dice", "Spinner", "Cards");
        TypeSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldVal, String newVal) {
                if (oldVal != null) {
                    switch(oldVal) {
                        case "Dice": RangeSelection.setVisible(false); QuantitySelection.setVisible(false);; break;
                        case "Spinner": RangeSelection.setVisible(false); QuantitySelection.setVisible(false); break;
                        case "Cards": DeckSelection.setVisible(false); break;
                    }
                }
                if (newVal != null) {
                    switch(newVal) {
                        case "Dice": RangeSelection.setVisible(true); QuantitySelection.setVisible(true);; break;
                        case "Spinner": RangeSelection.setVisible(true); QuantitySelection.setVisible(true); break;
                        case "Cards": DeckSelection.setVisible(true); break;
                    }
                }
            }
        });
    }
}