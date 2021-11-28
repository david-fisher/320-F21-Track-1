package preGame;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class selectSwitch extends HBox{
    private final Rectangle rectangle = new Rectangle(50, 28);
    private final Button button = new Button();
    private StackPane theSwitch = new StackPane();
    private Label name = new Label("");

    private final String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.2), 0.1, 0.0, 0.0, 0.1); -fx-background-color: WHITE;";
    private final String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(86,112,240,0.2), 0.1, 0.0, 0.0, 0.1); -fx-background-color: #e8edf0;";

    private final String rectColorOff = "#37393d";
    private final String rectColorOn = "#32ca56";

    private final String nameStyleOn = "-fx-text-fill: rgba(255, 255, 255); -fx-font: normal 20pt \"Helvetica\"";
    private final String nameStyleOff = "-fx-text-fill: #545963; -fx-font: normal 20pt \"Helvetica\"";

    private boolean state;

    private void createLayout() {

        theSwitch.getChildren().addAll(rectangle, button);
        theSwitch.setMinSize(50, 30);

        getChildren().addAll(theSwitch, name);

        setSpacing(20);
        setMinSize(100, 15);

        rectangle.setArcHeight(rectangle.getHeight());
        rectangle.setArcWidth(rectangle.getHeight());
        rectangle.setFill(Color.web(rectColorOff));
        Double r = 1.0;
        button.setShape(new Circle(r));

        StackPane.setAlignment(button, Pos.CENTER_LEFT);
        StackPane.setAlignment(rectangle, Pos.CENTER);
        button.setMaxSize(25, 25);
        button.setMinSize(25, 25);
        button.setStyle(buttonStyleOff);

        name.setStyle(nameStyleOff);
        setAlignment(Pos.CENTER);
    }

    public void setSpace(int number) { setSpacing(number); }
    public void setText(String n) { name.setText(n);}

    public boolean switchState(){ return state;}

    public selectSwitch(String labelName) {
        setText(labelName);
        createLayout();
        EventHandler<Event> click = new EventHandler<Event>() {

            @Override
            public void handle(Event e) {
                if (state) {
                    button.setStyle(buttonStyleOff);
                    rectangle.setFill(Color.web(rectColorOff));
                    name.setStyle(nameStyleOff);
                    StackPane.setAlignment(button, Pos.CENTER_LEFT);
                    state = false;
                } else {
                    button.setStyle(buttonStyleOn);
                    rectangle.setFill(Color.web(rectColorOn));
                    name.setStyle(nameStyleOn);
                    StackPane.setAlignment(button, Pos.CENTER_RIGHT);
                    state = true;
                }
            }
        };

        button.setFocusTraversable(false);

        setOnMouseClicked(click);
        button.setOnMouseClicked(click);

    }

}
