import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class ModeMenuController {
    @FXML
    private Spinner aiSpinner;

    @FXML
    private Spinner playerSpinner;

    private void initSpinner(int playerMax, int aiMax) {
        playerSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, playerMax));

        aiSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aiMax));

    }
    @FXML
    public void initialize() {
        initSpinner(10, 4);
    }
}
