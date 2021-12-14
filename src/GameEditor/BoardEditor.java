package GameEditor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

//import javafx.application.*;
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
import javafx.scene.input.*;

import Objects.*;

public class BoardEditor {
    double orgSceneX, orgSceneY, xTemp;
    StackPane shapeToDelete;
    boardGrid theBoardGrid;
    Scene scene;
//    Board currBoard = new Board();
    ArrayList<Tile> tiles = new ArrayList<Tile>();

public void tabs(Group root) {
    // Top tabs ***************************************************************
    Button boardEditor = new Button("Board Editor");
    boardEditor.setPrefSize(100,100);

    // Side bar *****************************************************************
    Rectangle sideBar = new Rectangle();
    sideBar.setY(150);
    sideBar.setWidth(100);
    sideBar.setHeight(460);
    sideBar.setArcWidth(20);
    sideBar.setArcHeight(20);
    sideBar.setFill(Color.rgb(220, 220, 220));
    root.getChildren().add(sideBar);
    shapeToDelete = null;
    scene.setOnKeyPressed(k -> {
    if (k.getCode() == KeyCode.DELETE) {
        if(shapeToDelete != null) {
            theBoardGrid.getBoard().getChildren().remove(shapeToDelete);
        }
    }
    });
}

public StackPane createRectangle(boardGrid root, int x, int y) {
    Rectangle rectangle = new Rectangle(50,50);
    TextField text = new TextField ("+1");
    //Making the TextField transparent, so we dont see the whole input box
    text.setStyle("-fx-background-color:transparent;-fx-text-fill: white;-fx-focus-color: transparent;");
    text.setMaxWidth(60);
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
    rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY) ) { //&& mouseEvent.getClickCount() >= 2){ //enable to select with double-click instead
            System.out.println("shape clicked");
            shapeToDelete = layout;
        }
    }
    });
    layout.setMaxHeight(50);
    layout.setMaxWidth(50);
    layout.setLayoutX(10);
    layout.setLayoutY(180);
    dragNDrop_StackPane(layout,root, x, y);
    rightClick_StackPane(layout,root);
    return layout;
}

public StackPane createCircle(boardGrid root, int x, int y) {
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
    circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY) ) { //&& mouseEvent.getClickCount() >= 2){ //enable to select with double-click instead
            System.out.println("shape clicked");
            shapeToDelete = layout;
        }
    }
    });
    layout.setLayoutX(10);
    layout.setLayoutY(250);
    dragNDrop_StackPane(layout,root, x, y);
    rightClick_StackPane(layout,root);
    return layout;
}

public StackPane createTriangle(boardGrid root, int x, int y) {
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
    triangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY) ) { //&& mouseEvent.getClickCount() >= 2){ //enable to select with double-click instead
            System.out.println("shape clicked");
            shapeToDelete = layout;
        }
    }
    });
    layout.setLayoutX(10);
    layout.setLayoutY(320);
    dragNDrop_StackPane(layout,root, x, y);
    rightClick_StackPane(layout,root);
    return layout;
}

public StackPane createPentagon(boardGrid root, int x, int y) {
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
    pentagon.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY) ) { //&& mouseEvent.getClickCount() >= 2){ //enable to select with double-click instead
            System.out.println("shape clicked");
            shapeToDelete = layout;
        }
    }
    });
    layout.setLayoutX(10);
    layout.setLayoutY(400);
    dragNDrop_StackPane(layout,root, x, y);
    rightClick_StackPane(layout,root);
    return layout;
}

public StackPane createHexagon(boardGrid root, int x, int y) {
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
    hexagon.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY) ) { //&& mouseEvent.getClickCount() >= 2){ //enable to select with double-click instead
            System.out.println("shape clicked");
            shapeToDelete = layout;
        }
    }
    });
    layout.setLayoutX(10);
    layout.setLayoutY(490);
    dragNDrop_StackPane(layout,root, x, y);
    rightClick_StackPane(layout,root);
    return layout;
}

public void dragNDrop_StackPane(StackPane sp, boardGrid root, int x, int y) {
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
	       
            double tile_size = 50;
	        double snapX = (c.getTranslateX()) % tile_size;
	        if (snapX > tile_size/2){
	        	snapX = (c.getTranslateX()) - snapX + tile_size;
	        }
	        else {
	        	snapX = (c.getTranslateX()) - snapX;
	        }
	        if (t.getSceneX() >= 100 && t.getSceneX() < (100 + x*tile_size)) {
	            c.setTranslateX(snapX);
	        }
	        else {
	        	c.setTranslateX(0);
	        }
	             
	        double snapY = (c.getTranslateY()) % tile_size;
	        if (snapY > tile_size/2){
	        	snapY = (c.getTranslateY()) - snapY + tile_size;
	        }
	        else {
	        	snapY = (c.getTranslateY()) - snapY;
	        }
	        
	        if (t.getSceneY() >= 100 && t.getSceneY() < (100 + y*tile_size)) {
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
	        			StackPane temp = createRectangle(root, x, y);
	        			root.getBoard().add(temp, 0, 0);
	        			temp.setTranslateX(-75);
	        	    	temp.setTranslateY(75);
	        			break;
	        		}
	        		else if (c.getChildren().toArray()[i] instanceof Circle) {
	        			StackPane temp = createCircle(root, x, y);
	        			root.getBoard().add(temp, 0, 0);
	        			temp.setTranslateX(-75);
	        	    	temp.setTranslateY(150);
	        			break;
	        		}
	        		else if (c.getChildren().toArray()[i] instanceof Polygon) {
	        			if (((Polygon)(c.getChildren().toArray()[i])).getPoints().size() == 6) {
	        				StackPane temp = createTriangle(root, x, y);
		        			root.getBoard().add(temp, 0, 0);
		        			temp.setTranslateX(-75);
		        	    	temp.setTranslateY(225);
	            			}
	            		else if (((Polygon)(c.getChildren().toArray()[i])).getPoints().size() == 10) {
	        				StackPane temp = createPentagon(root, x, y);
		        			root.getBoard().add(temp, 0, 0);
		        			temp.setTranslateX(-75);
		        	    	temp.setTranslateY(300);
	            			}
	            		else {
	            			StackPane temp = createHexagon(root, x, y);
		        			root.getBoard().add(temp, 0, 0);
		        			temp.setTranslateX(-75);
		        	    	temp.setTranslateY(375);
	            			}
	            		}
	        		}
	        	}
	        updateTileArrayList(root);
	        // Work in progress, need to discuss
//	        Token token = new Token();
//			JSONConverter json = new JSONConverter(token, "Token.json");
//			try {
//				json.To_JSON();
//			} catch (IOException e) {}
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
        //Store image path into sp
        //Can be retrieve using sp.getUserData()
        String imgPath = file.getAbsolutePath();
        if(imgPath!=null){sp.setUserData(imgPath);}
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
    
    public void createBoard(int x, int y){
    	board.setGridLinesVisible(true);
        // column constraints
        for (int i = 0; i < x; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(50);
            column.setMaxWidth(50);
            board.getColumnConstraints().add(column);
        }

        // row constraints
        for (int j  = 0; j < y; j++){
            RowConstraints row = new RowConstraints();
            row.setMinHeight(50);
            row.setMaxHeight(50);
            board.getRowConstraints().add(row);
        }
    }
    
    public void addInitialPieces(int x, int y) {
    	StackPane tmp1 = createRectangle(this, x, y);
    	board.add(tmp1, 0, 0);
    	tmp1.setTranslateX(-75);
    	tmp1.setTranslateY(75);
    	
    	StackPane tmp2 = createCircle(this, x, y);
    	board.add(tmp2, 0, 0);
    	tmp2.setTranslateX(-75);
    	tmp2.setTranslateY(150);
    	
    	StackPane tmp3 = createTriangle(this, x, y);
    	board.add(tmp3, 0, 0);
    	tmp3.setTranslateX(-75);
    	tmp3.setTranslateY(225);
    	
    	StackPane tmp4 = createPentagon(this, x, y);
    	board.add(tmp4, 0, 0);
    	tmp4.setTranslateX(-75);
    	tmp4.setTranslateY(300);
    	
    	StackPane tmp5 = createHexagon(this, x, y);
    	board.add(tmp5, 0, 0);
    	tmp5.setTranslateX(-75);
    	tmp5.setTranslateY(375);

        Label l = new Label("Board Dimensions:");
        board.add(l, 0, 0);
        l.setMinWidth(100);
        l.setTranslateX(-100);
    	l.setTranslateY(425);

        ComboBox<Integer> x_value = new ComboBox<Integer>();
        x_value.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        x_value.setPromptText("X");
        x_value.setOnAction((event) -> {
            resize(x_value.getSelectionModel().getSelectedItem(), y);
        });
        board.add(x_value, 0, 0);
        x_value.setTranslateX(-100);
    	x_value.setTranslateY(450);

        ComboBox<Integer> y_value = new ComboBox<Integer>();
        y_value.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        y_value.setPromptText("Y");
        y_value.setOnAction((event) -> {
            resize(x, y_value.getSelectionModel().getSelectedItem());
        });
        board.add(y_value, 0, 0);
        y_value.setTranslateX(-50);
    	y_value.setTranslateY(450);
    }
    
    public GridPane getBoard() {
    	return board;
    }

    public void resize(int x, int y) {
        board.getChildren().remove(1, board.getChildren().size());
        this.addInitialPieces(x, y);
        board.getColumnConstraints().clear();
        board.getRowConstraints().clear();
        for (int i = 0; i < x; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setMinWidth(50);
            column.setMaxWidth(50);
            board.getColumnConstraints().add(column);
        }
        for (int j  = 0; j < y; j++){
            RowConstraints row = new RowConstraints();
            row.setMinHeight(50);
            row.setMaxHeight(50);
            board.getRowConstraints().add(row);
        }
        updateTileArrayList(this);
    }
}

public void updateTileArrayList(boardGrid root){
	int size = root.getBoard().getChildren().size();
	ArrayList<Tile> tmpTilesArr = new ArrayList<Tile>();
	for (int i = 0; i < size; i ++) {
		if (root.getBoard().getChildren().toArray()[i] instanceof StackPane) {
			StackPane tmp = (StackPane) root.getBoard().getChildren().toArray()[i];
			if (tmp.getTranslateX() >= 0 && tmp.getTranslateY() >= 0) {
				int tmpX = (int) (tmp.getTranslateX() / 50);
				int tmpY = (int) (tmp.getTranslateY() / 50);
				Hashtable<String,String> tmpAtr = new Hashtable<String,String>();
				
				for (int j = 0; j < tmp.getChildren().size(); j++) {
					if (tmp.getChildren().toArray()[j] instanceof Shape) {
						Shape tmpShape = (Shape) tmp.getChildren().toArray()[j];
						Paint tmpPaint = tmpShape.getFill();
						tmpAtr.put("color", tmpPaint.toString());
					}
					else if (tmp.getChildren().toArray()[j] instanceof TextField) {
						TextField tmpText = (TextField) tmp.getChildren().toArray()[j];
						tmpAtr.put("text", tmpText.getText());
					}
				}
				Tile tmpTile = new Tile(tmpX, tmpY, new ArrayList<Rule>(), tmpAtr);
				tmpTilesArr.add(tmpTile);
			}
		}
	}
	tiles = tmpTilesArr;
}

public Group startBoardEditor(Stage stage){
    scene = stage.getScene();
    Group root = new Group();
    boardGrid board = new boardGrid();
    board.createBoard(5, 5);
    theBoardGrid = board;
    board.getBoard().setTranslateX(100);
    board.getBoard().setTranslateY(100);
    tabs(root);
    board.addInitialPieces(5, 5);
    root.getChildren().add(board.getBoard());
    return root;
}
}