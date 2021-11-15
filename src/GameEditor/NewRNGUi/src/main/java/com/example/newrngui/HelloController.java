package com.example.newrngui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class HelloController {

    private ArrayList<RNG> values = new ArrayList<RNG>();

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