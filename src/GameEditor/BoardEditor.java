package GameEditor;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;

public class BoardEditor {
    double orgSceneX, orgSceneY;

    public void tabs(Group root) {
        // Side bar *****************************************************************
        Rectangle sideBar = new Rectangle();
        sideBar.setY(150);
        sideBar.setWidth(100);
        sideBar.setHeight(410);
        sideBar.setArcWidth(20);
        sideBar.setArcHeight(20);
        sideBar.setFill(Color.rgb(255, 255, 255));
        root.getChildren().add(sideBar);
    }

    public void createRectangle(Group root) {
        Rectangle rectangle = new Rectangle(50,50);
        rectangle.setY(175);
        rectangle.setX(25);
        root.getChildren().add(rectangle);
        dragNDrop(rectangle);
        rightClick(rectangle, root);
    }

    public void createCircle(Group root) {
        Circle circle = new Circle(25);
        circle.setCenterY(275);
        circle.setCenterX(50);
        root.getChildren().add(circle);
        dragNDrop(circle);
        rightClick(circle, root);
    }

    public void createTriangle(Group root) {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                50.0, 325.0,
                25.0, 375.0,
                75.0, 375.0
                });
        root.getChildren().add(triangle);
        dragNDrop(triangle);
        rightClick(triangle, root);
    }

    public void createPentagon(Group root) {
        Polygon pentagon = new Polygon();
        pentagon.getPoints().addAll(new Double[]{
                50.0, 400.0,
                25.0, 425.0,
                35.0, 455.0,
                65.0, 455.0,
                75.0, 425.0
                });
        root.getChildren().add(pentagon);
        dragNDrop(pentagon);
        rightClick(pentagon, root);
    }

    public void createHexagon(Group root) {
        Polygon hexagon = new Polygon();
        hexagon.getPoints().addAll(new Double[]{
                62.5, 485.0,
                37.5, 485.0,
                25.0, 510.0,
                37.5, 535.0,
                62.5, 535.0,
                75.0, 510.0
                });
        root.getChildren().add(hexagon);
        dragNDrop(hexagon);
        rightClick(hexagon, root);
    }

    public void dragNDrop(Shape shape) {
    // Drag n' Drop Interaction *******************************************************
        shape.setCursor(Cursor.HAND);

        shape.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();

            Shape c = (Shape) (t.getSource());
            c.toFront();
            });

        shape.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;

            Shape c = (Shape) (t.getSource());

            c.setTranslateX(c.getTranslateX() + offsetX);
            c.setTranslateY(c.getTranslateY() + offsetY);

            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            });

    }

    public void rightClick(Shape shape, Group root){
        //*****Color Picker function */
        ContextMenu contextMenu = new ContextMenu();
        //Intial a colorpicker, display the current color on shape
        ColorPicker colorssPicker = new ColorPicker(Color.web(shape.getFill().toString()));
        //*****Backgraound Uploader function */
        MenuItem backgrounduploader_item = new MenuItem(null, new Label("Upload image"));
        //TODO
        MenuItem colorpicker_item = new MenuItem(null,colorssPicker);
        MenuItem deleter_item = new MenuItem(null, new Label("Delete Shape"));

        //Handle right click
        colorpicker_item.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                shape.setFill(colorssPicker.getValue());
            }
        });

        deleter_item.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                root.getChildren().remove(shape);
            }
        });

        contextMenu.getItems().add(colorpicker_item);
        contextMenu.getItems().add(backgrounduploader_item);
        contextMenu.getItems().add(deleter_item);

        //Display menu beside shape
        shape.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
                @Override
                public void handle(ContextMenuEvent event) {
                    contextMenu.show(shape, event.getScreenX(), event.getScreenY());
                }
        });
    }

    //returns the Group node of the BoardEditor piece
    public Group startBoardEditor(){
        Group root = new Group();
        tabs(root);
        createRectangle(root);
        createCircle(root);
        createTriangle(root);
        createPentagon(root);
        createHexagon(root);
        return root;

    }
}