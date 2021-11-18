package GameEditor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javafx.application.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.*;

public class BoardEditor {
    double orgSceneX, orgSceneY, xTemp;

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

public StackPane createRectangle(boardGrid root) {
    Rectangle rectangle = new Rectangle(50,50);
    TextField text = new TextField ("+1");
    //Making the TextField transparent, so we dont see the whole input box
    text.setStyle("-fx-background-color:transparent;-fx-text-fill: white;-fx-focus-color: transparent;");
    text.setMaxWidth(30);
    text.setAlignment(Pos.CENTER);
    
    //Adding shadow for text, for better readability
    DropShadow shadow = new DropShadow();
    shadow.setSpread(0.6);
    shadow.setHeight(5);
    shadow.setWidth(5);
    text.setEffect(shadow);
    
    //Create a stackPane for TextField and Shape
    StackPane layout = new StackPane();
    layout.getChildren().addAll(
            rectangle,
            text
    		);
    layout.setMaxHeight(50);
    layout.setMaxWidth(50);
    layout.setLayoutX(10);
    layout.setLayoutY(180);
    dragNDrop_StackPane(layout,root);
    rightClick_StackPane(layout,root);
    return layout;
}

public StackPane createCircle(boardGrid root) {
    Circle circle = new Circle(25);
    TextField text = new TextField ("Circle");
    //Making the TextField transparent, so we dont see the whole input box
    text.setStyle("-fx-background-color:transparent;-fx-text-fill: white;-fx-focus-color: transparent;");
    text.setMaxWidth(30);
    text.setAlignment(Pos.CENTER);
    //Adding shadow for text, for better readability
    DropShadow shadow = new DropShadow();
    shadow.setSpread(0.6);
    shadow.setHeight(5);
    shadow.setWidth(5);
    text.setEffect(shadow);
    
    //Create a stackPane for TextField and Shape
    StackPane layout = new StackPane();
    layout.getChildren().addAll(
            circle,
            text
    		);
    layout.setLayoutX(10);
    layout.setLayoutY(250);
    dragNDrop_StackPane(layout,root);
    rightClick_StackPane(layout,root);
    return layout;
}

public StackPane createTriangle(boardGrid root) {
    Polygon triangle = new Polygon();
    triangle.getPoints().addAll(new Double[]{
            50.0, 325.0,
            25.0, 375.0,
            75.0, 375.0
            });
    
    TextField text = new TextField ("Triangle");
    //Making the TextField transparent, so we dont see the whole input box
    text.setStyle("-fx-background-color:transparent;-fx-text-fill: white;-fx-focus-color: transparent;");
    text.setMaxWidth(30);
    text.setAlignment(Pos.CENTER);
    
    //Adding shadow for text, for better readability
    DropShadow shadow = new DropShadow();
    shadow.setSpread(0.6);
    shadow.setHeight(5);
    shadow.setWidth(5);
    text.setEffect(shadow);
    
    //Create a stackPane for TextField and Shape
    StackPane layout = new StackPane();
    layout.getChildren().addAll(
            triangle,
            text
    		);
    layout.setLayoutX(10);
    layout.setLayoutY(320);
    dragNDrop_StackPane(layout,root);
    rightClick_StackPane(layout,root);
    return layout;
}

public StackPane createPentagon(boardGrid root) {
    Polygon pentagon = new Polygon();
    pentagon.getPoints().addAll(new Double[]{
            50.0, 400.0,
            25.0, 425.0,
            35.0, 455.0,
            65.0, 455.0,
            75.0, 425.0
            });
    TextField text = new TextField ("Pentagon");
    //Making the TextField transparent, so we dont see the whole input box
    text.setStyle("-fx-background-color:transparent;-fx-text-fill: white;-fx-focus-color: transparent;");
    text.setMaxWidth(30);
    text.setAlignment(Pos.CENTER);
    
    //Adding shadow for text, for better readability
    DropShadow shadow = new DropShadow();
    shadow.setSpread(0.6);
    shadow.setHeight(5);
    shadow.setWidth(5);
    text.setEffect(shadow);
    
    //Create a stackPane for TextField and Shape
    StackPane layout = new StackPane();
    layout.getChildren().addAll(
            pentagon,
            text
    		);
    layout.setLayoutX(10);
    layout.setLayoutY(400);
    dragNDrop_StackPane(layout,root);
    rightClick_StackPane(layout,root);
    return layout;
}

public StackPane createHexagon(boardGrid root) {
    Polygon hexagon = new Polygon();
    hexagon.getPoints().addAll(new Double[]{
            62.5, 485.0,
            37.5, 485.0,
            25.0, 510.0,
            37.5, 535.0,
            62.5, 535.0,
            75.0, 510.0
            });
    TextField text = new TextField ("Hexagon");
    //Making the TextField transparent, so we dont see the whole input box
    text.setStyle("-fx-background-color:transparent;-fx-text-fill: white;");
    text.setMaxWidth(30);
    text.setAlignment(Pos.CENTER);
    
    //Adding shadow for text, for better readability
    DropShadow shadow = new DropShadow();
    shadow.setSpread(0.6);
    shadow.setHeight(5);
    shadow.setWidth(5);
    text.setEffect(shadow);
    
    //Create a stackPane for TextField and Shape
    StackPane layout = new StackPane();
    layout.getChildren().addAll(
            hexagon,
            text
    		);
    layout.setLayoutX(10);
    layout.setLayoutY(490);
    dragNDrop_StackPane(layout,root);
    rightClick_StackPane(layout,root);
    return layout;
}

public void dragNDrop_StackPane(StackPane sp, boardGrid root) {
    // Drag n' Drop Interaction *******************************************************
        sp.setCursor(Cursor.HAND);
    
        sp.setOnMousePressed((t) -> {
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            xTemp = orgSceneX;
            
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
            });  
    
	    sp.setOnMouseReleased((t) -> {       
	        StackPane c = (StackPane) (t.getSource());
	       
	
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
	        	c.setTranslateX(0);
	        }
	             
	        double snapY = (c.getTranslateY()) % 50;
	        if (snapY > 25.0){
	        	snapY = (c.getTranslateY()) - snapY + 50;
	        }
	        else {
	        	snapY = (c.getTranslateY()) - snapY;
	        }
	        
	        if (t.getSceneY() >= 100 && t.getSceneY() < 650) {
	            c.setTranslateY(snapY);
	        }
	        else {
	        	c.setTranslateY(0);
	        }
	        
	        //checking if we are moving a piece from spawn
	        //don't ask why value is 100, it just works
	        if (xTemp < 100) {
	        	for (int i = 0; i < c.getChildren().size(); i++) {
	        		if (c.getChildren().toArray()[i] instanceof Rectangle) {
	        			StackPane temp = createRectangle(root);
	        			root.getBoard().add(temp, 0, 0);
	        			temp.setTranslateX(-75);
	        	    	temp.setTranslateY(75);
	        			break;
	        		}
	        		else if (c.getChildren().toArray()[i] instanceof Circle) {
	        			StackPane temp = createCircle(root);
	        			root.getBoard().add(temp, 0, 0);
	        			temp.setTranslateX(-75);
	        	    	temp.setTranslateY(150);
	        			break;
	        		}
	        		else if (c.getChildren().toArray()[i] instanceof Polygon) {
	        			if (((Polygon)(c.getChildren().toArray()[i])).getPoints().size() == 6) {
	        				StackPane temp = createTriangle(root);
		        			root.getBoard().add(temp, 0, 0);
		        			temp.setTranslateX(-75);
		        	    	temp.setTranslateY(225);
	            			}
	            		else if (((Polygon)(c.getChildren().toArray()[i])).getPoints().size() == 10) {
	        				StackPane temp = createPentagon(root);
		        			root.getBoard().add(temp, 0, 0);
		        			temp.setTranslateX(-75);
		        	    	temp.setTranslateY(300);
	            			}
	            		else {
	            			StackPane temp = createHexagon(root);
		        			root.getBoard().add(temp, 0, 0);
		        			temp.setTranslateX(-75);
		        	    	temp.setTranslateY(375);
	            			}
	            		}
	        		}
	        	}
	    });
}

public void rightClick_StackPane(StackPane sp, boardGrid root){
    //*****Color Picker function */
    ContextMenu contextMenu = new ContextMenu();
    //Intial a colorpicker, display the current color on shape
    ColorPicker colorssPicker = new ColorPicker(Color.web(((Shape) sp.getChildren().get(0)).getFill().toString()));
    //*****Backgraound Uploader function */  
    MenuItem backgrounduploader_item = new MenuItem(null, new Label("Upload image"));
    
    //Intialize UploadHandler
    EventHandler<ActionEvent> UploadEventHandler
    = new EventHandler<ActionEvent>(){

    @Override
    public void handle(ActionEvent t) {
        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png","*.jpeg");
        fileChooser.getExtensionFilters().addAll(imageFilter);
         
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
                  
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ((Shape)sp.getChildren().get(0)).setFill(new ImagePattern(image));
        } catch (IOException ex) {
            System.out.print("ERROR IN IMAGE UPLOADER");
        }

    }
};
//Add the Upload function to the menuitem

backgrounduploader_item.setOnAction(UploadEventHandler);

    MenuItem colorpicker_item = new MenuItem(null,colorssPicker);
    MenuItem deleter_item = new MenuItem(null, new Label("Delete Shape"));
  
    //Handle right click
    colorpicker_item.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event)
        {
            ((Shape) sp.getChildren().get(0)).setFill(colorssPicker.getValue());
        }
    });

    deleter_item.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event)
        {
            root.getBoard().getChildren().remove(sp);
        }
    });

    contextMenu.getItems().add(colorpicker_item);
    contextMenu.getItems().add(backgrounduploader_item);
    contextMenu.getItems().add(deleter_item);
   
    //Display menu beside shape
    sp.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(sp, event.getScreenX(), event.getScreenY());
            }
    });
}

public class boardGrid {
    // grid pane
    private GridPane board = new GridPane();
    private int height = 10, width = 10;
    
    public void createBoard(){
    	board.setGridLinesVisible(true);

        // column constraints
        for (int i = 0; i <= width; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(50);
            column.setMaxWidth(50);
            board.getColumnConstraints().add(column);
        }

        // row constraints
        for (int j  = 0; j <= height; j++){
            RowConstraints row = new RowConstraints();
            row.setMinHeight(50);
            row.setMaxHeight(50);
            board.getRowConstraints().add(row);
        }
    }
    
    public void addInitialPieces() {
    	StackPane tmp1 = createRectangle(this);
    	board.add(tmp1, 0, 0);
    	tmp1.setTranslateX(-75);
    	tmp1.setTranslateY(75);
    	
    	StackPane tmp2 = createCircle(this);
    	board.add(tmp2, 0, 0);
    	tmp2.setTranslateX(-75);
    	tmp2.setTranslateY(150);
    	
    	StackPane tmp3 = createTriangle(this);
    	board.add(tmp3, 0, 0);
    	tmp3.setTranslateX(-75);
    	tmp3.setTranslateY(225);
    	
    	StackPane tmp4 = createPentagon(this);
    	board.add(tmp4, 0, 0);
    	tmp4.setTranslateX(-75);
    	tmp4.setTranslateY(300);
    	
    	StackPane tmp5 = createHexagon(this);
    	board.add(tmp5, 0, 0);
    	tmp5.setTranslateX(-75);
    	tmp5.setTranslateY(375);
    }
    
    public GridPane getBoard() {
    	return board;
    }
}

public Group startBoardEditor(Stage stage){
    Group root = new Group();
    boardGrid board = new boardGrid();
    board.createBoard();
    board.getBoard().setTranslateX(100);
    board.getBoard().setTranslateY(100);
    
    // Testing 
    tabs(root);
    board.addInitialPieces();
//    board.getBoard().add(createRectangle(board), 0, 0);
//    board.getBoard().add(createRectangle(board), 1, 1);
//    board.getBoard().add(createTriangle(board), 10, 10);
//    board.getBoard().add(createPentagon(board), 0, 10);
//    board.getBoard().add(createHexagon(board), 10, 0);
    root.getChildren().add(board.getBoard());
    return root;
    // Initial Setup
//    tabs(root);

//    createRectangle(root);
//    createCircle(root);
//    createTriangle(root);
//    createPentagon(root);
//    createHexagon(root);
    
    //Scene scene = new Scene(root, 700, 700, Color.rgb(105, 162, 255));

    //stage.setTitle("Board Editor");
    //stage.setScene(scene);
    
    // Using external file to hide the broken 'Custom color' section on ColorPicker
    //scene.getStylesheets().add(getClass().getResource("ColorPickerMod.css").toExternalForm());
    //stage.show();

    
}

}