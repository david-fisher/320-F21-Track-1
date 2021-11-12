import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    double orgSceneX, orgSceneY;

public void tabs(Group root) {
    // Top tabs ***************************************************************
    Button boardEditor = new Button("Board Editor");
    boardEditor.setPrefSize(100,100);
    
    Button ruleEditor = new Button("Rule Editor");
    ruleEditor.setPrefSize(100, 100);
    ruleEditor.setTranslateX(100);
    
    Button RNG = new Button("RNG");
    RNG.setPrefSize(100, 100);
    RNG.setTranslateX(200);
    
    Button gameOptions = new Button("Game Options");
    gameOptions.setPrefSize(100, 100);
    gameOptions.setTranslateX(300);
    
    Button gameTokens = new Button("Game Tokens");
    gameTokens.setPrefSize(100, 100);
    gameTokens.setTranslateX(400);
    
    TextField gameName = new TextField();
    gameName.setPrefSize(300, 100);
    gameName.setTranslateX(500);
    gameName.setPromptText("Input the name of the board");
    
    root.getChildren().addAll(boardEditor, ruleEditor, RNG, gameOptions, gameTokens, gameName);
    
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
    //root.getChildren().add(hexagon);
    // dragNDrop(hexagon);
    //rightClick(hexagon, root);
    TextField text = new TextField ("AAA");
    text.setStyle("-fx-background-color:transparent;-fx-text-fill: white;");
    text.setAlignment(Pos.CENTER);
    StackPane layout = new StackPane();
    layout.getChildren().addAll(
            hexagon,
            text
    );
    root.getChildren().add(layout);
    // layout.setLayoutX(5);
    layout.setLayoutX(-30);
    layout.setLayoutY(490);
    dragNDrop_stack(layout);      
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

public void dragNDrop_stack(StackPane sp) {
    // Drag n' Drop Interaction *******************************************************
        sp.setCursor(Cursor.HAND);
    
        sp.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            
            StackPane c = (StackPane) (t.getSource());
            c.toFront();
            });
    
        sp.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
    
            StackPane c = (StackPane) (t.getSource());
    
            c.setTranslateX(c.getTranslateX() + offsetX);
            c.setTranslateY(c.getTranslateY() + offsetY);
    
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            // System.out.println(orgSceneX);
            // System.out.println(orgSceneY);
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

public void start(Stage stage){
    Group root = new Group();
    
    // Initial Setup
    tabs(root);
    createRectangle(root);
    createCircle(root);
    createTriangle(root);
    createPentagon(root);
    createHexagon(root);
    
    // System.out.print(root.);
    
    Scene scene = new Scene(root, 800, 600, Color.rgb(105, 162, 255));
    stage.setTitle("Board Editor");
    stage.setScene(scene);
    //Using external file to hide the broken 'Custom color' section on ColorPicker
    scene.getStylesheets().add(getClass().getResource("ColorPickerMod.css").toExternalForm());
    stage.show();

    
}

public static void main(String[] args) {
    launch(args);
    }
}