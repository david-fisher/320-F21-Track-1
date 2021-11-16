import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

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

// Testing createRectangle ******************************************************
public Rectangle createRectangle() {
    Rectangle rectangle = new Rectangle(50,50);
    dragNDrop(rectangle);
    
//    rightClick(rectangle, root);
    return rectangle;
}
// ******************************************************************************************
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

// Testing  *********************************************************************************
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
    
    shape.setOnMouseReleased((t) -> {       
        Shape c = (Shape) (t.getSource());
       

        double snapX = (c.getTranslateX()) % 50;
        if (snapX > 25.0){
        	snapX = (c.getTranslateX()) - snapX + 50;
        }
        else {
        	snapX = (c.getTranslateX()) - snapX;
        }
        if (t.getSceneX() >= 100 && t.getSceneX() < 650) {
            c.setTranslateX(snapX);
        }
        else {
        	System.out.println(t.getSceneX());
        	System.out.println(c.getTranslateX());
        	
//        	c.setTranslateX(c.getTranslateX() - t.getSceneX());
//        	c.setTranslateX(100);
        }
        
        double snapY = (c.getTranslateY()) % 50;
        if (snapY > 25.0){
        	snapY = (c.getTranslateY()) - snapY + 50;
        }
        else {
        	snapY = (c.getTranslateY()) - snapY;
        }
//        System.out.println();
        c.setTranslateY(snapY);
        });
}
// **************************************************************************************************************

public void rightClick(Shape shape, Group root){
    //*****Color Picker function */
    ContextMenu contextMenu = new ContextMenu();
    //Intial a colorpicker
    ColorPicker colorssPicker = new ColorPicker();
    //Setting the background of the tab white
    colorssPicker.setStyle("-fx-background-color:White;");
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

// Testing Grid *************************************************************************************************
public static class boardGrid {
    // grid pane
    private GridPane board = new GridPane();
    private int height = 10, width = 10;
    
    public void createBoard(){
    	board.setGridLinesVisible(true);

        // Scale of client
        int widthPercentage = 100 / width;
        int heightPercentage = 100 / height;

        // column constraints
        for (int i = 0; i <= width; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(widthPercentage);
            board.getColumnConstraints().add(column);
        }

        // row constraints
        for (int j  = 0; j <= height; j++){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(heightPercentage);
            board.getRowConstraints().add(row);
        }
    }
    
    public GridPane getBoard() {
    	return board;
    }
}
// *************************************************************************************************************
public void start(Stage stage){
    Group root = new Group();
    boardGrid board = new boardGrid();
    board.createBoard();
    board.getBoard().setTranslateX(100);
    board.getBoard().setTranslateY(100);
    board.getBoard().add(createRectangle(), 0, 0);
    board.getBoard().add(createRectangle(), 1, 1);
    board.getBoard().add(createRectangle(), 9, 9);
    board.getBoard().add(createRectangle(), 10, 10);
    board.getBoard().add(createRectangle(), 10, 0);
    board.getBoard().add(createRectangle(), 0, 10);
    root.getChildren().add(board.getBoard());
    // Initial Setup
    tabs(root);
//    createRectangle(root);
//    createCircle(root);
//    createTriangle(root);
//    createPentagon(root);
//    createHexagon(root);
   
    Scene scene = new Scene(root, 700, 700, Color.rgb(105, 162, 255));
    stage.setTitle("Board Editor");
    stage.setScene(scene);
    stage.show();
}

public static void main(String[] args) {
    launch(args);
    }
}