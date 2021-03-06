package GameEditor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import GameEditor.Controllers.LocalStorage;
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
    double orgSceneX, orgSceneY, xTemp, moveDist;
    boolean moved; 
    StackPane shapeToDelete;
    boardGrid theBoardGrid;
    Scene scene;
    Board currBoard;
    LocalStorage localStorage;

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
            moved = false;
            });
    
        sp.setOnMouseDragged((t) -> {
            double offsetX = t.getSceneX() - orgSceneX;
            double offsetY = t.getSceneY() - orgSceneY;
            StackPane c = (StackPane) (t.getSource());
            moved = true;
            moveDist += Math.max(Math.abs(offsetX), Math.abs(offsetY));
            c.setTranslateX(c.getTranslateX() + offsetX);
            c.setTranslateY(c.getTranslateY() + offsetY);
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            });  
    
	    sp.setOnMouseReleased((t) -> {
	        StackPane c = (StackPane) (t.getSource());
	        //checking if we are just clicked on a piece in spawn, not moving it
	        boolean skipSnap = false;
	        boolean spawnMove = moved;
	        if (moveDist < 25) {
	        	spawnMove = false;
	        }
	        if (xTemp < 75.0 && !moved) {
	        	moveDist = 0;
	        	return;
        	} else if (xTemp < 75.0 && moveDist < 50) {
        		spawnMove = true;
        		skipSnap = true;
        	}
	        moveDist = 0;
            double tile_size = 50;

	        System.out.println(c.getTranslateX());
	        System.out.println(c.getTranslateY());
	        
	        double snapX = (c.getTranslateX()) % tile_size;
	        double snapY = (c.getTranslateY()) % tile_size;
	        if (snapX > tile_size/2){
	        	snapX = (c.getTranslateX()) - snapX + tile_size;
	        }
	        else {
	        	snapX = (c.getTranslateX()) - snapX;
	        }
	        if (snapY > tile_size/2){
	        	snapY = (c.getTranslateY()) - snapY + tile_size;
	        }
	        else {
	        	snapY = (c.getTranslateY()) - snapY;
	        }
	        
	        boolean deleted = false;
	        ArrayList<Tile> currTiles = currBoard.get_tiles();
	        for (int i = 0; i < currTiles.size(); i++) {
	        	if (skipSnap) {
	        		root.getBoard().getChildren().remove(c);
	        		deleted = true;
	        		break;
	        	}
	        	if (currTiles.get(i).getX() == (int) (snapX/50) && currTiles.get(i).getY() == (int) (snapY/50) && spawnMove) {
	        		root.getBoard().getChildren().remove(c);
	        		deleted = true;
	        	}
	        }
	        
	        if (!deleted && t.getSceneX() >= 100 && t.getSceneX() < (100 + x*tile_size)) {
	            c.setTranslateX(snapX);
	        }
	        else if (!deleted) {
	        	c.setTranslateX(0);
	        }
	        
	        if (!deleted && t.getSceneY() >= 100 && t.getSceneY() < (100 + y*tile_size)) {
	            c.setTranslateY(snapY);
	        }
	        else if (!deleted) {
	        	c.setTranslateY(0);
	        }
	        
	     
	        //checking if we are moving a piece from spawn
	        //don't ask why value is 100, it just works
	        
	        if (xTemp < 75.0) {
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
	    });
}

public void rightClick_StackPane(StackPane sp, boardGrid root){
    //*****Color Picker function */
    ContextMenu contextMenu = new ContextMenu();
    contextMenu.setStyle("-fx-background-color: black;");
    //Intial a colorpicker, display the current color on shape
    ColorPicker colorssPicker = new ColorPicker(Color.web(((Shape) sp.getChildren().get(0)).getFill().toString()));
    colorssPicker.setStyle("-fx-background-color: black;");
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
        Label tmpLabel = new Label(imgPath);
        tmpLabel.setVisible(false);
        sp.getChildren().add(tmpLabel);
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
    
    public void createBoard(ArrayList<Tile> tiles, int x, int y){
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
        
        StackPane tmp = null;
        for (Tile tmpTile: tiles) {
        	String shape = tmpTile.getAttributes().get("shape");
        	
        	switch (shape) {
        		case "circle":
        	    	tmp = createCircle(this, x, y);
        	    	tmp = createBoardHelper(tmp, tmpTile);
        			break;
        		case "rectangle":
        	    	tmp = createRectangle(this, x, y);
        	    	tmp = createBoardHelper(tmp, tmpTile);
        			break;
        		case "triangle":
        	    	tmp = createTriangle(this, x, y);
        	    	tmp = createBoardHelper(tmp, tmpTile);
        			break;
        		case "pentagon":
        	    	tmp = createPentagon(this, x, y);
        	    	tmp = createBoardHelper(tmp, tmpTile);
        			break;
        		case "hexagon":
        	    	tmp = createHexagon(this, x, y);
        	    	tmp = createBoardHelper(tmp, tmpTile);
        			break;
        		default:
        			break;
        	}
        	tmp.setTranslateX(tmpTile.getX() * 50);
        	tmp.setTranslateY(tmpTile.getY() * 50);
    		this.board.add(tmp, 0, 0);	
        }
    }
    
    public StackPane createBoardHelper(StackPane tmp, Tile tmpTile) {
    	String color = tmpTile.getAttributes().get("color");
    	String text = tmpTile.getAttributes().get("text");
    	
    	if (tmpTile.getAttributes().containsKey("file location")) {
    		String imgPath = tmpTile.getAttributes().get("file location");
    		
    		try {
				InputStream stream = new FileInputStream(imgPath);
				Image tmpImage = new Image(stream);
	    		((Shape) tmp.getChildren().get(0)).setFill(new ImagePattern(tmpImage));
	    		Label tmpLabel = new Label(imgPath);
	    		tmpLabel.setVisible(false);
	    		tmp.getChildren().add(tmpLabel);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File missing");
			}
    	} else {
    		((Shape) tmp.getChildren().get(0)).setFill(Paint.valueOf(color));
    	}
    	((TextField) tmp.getChildren().get(1)).setText(text);
    	
    	return tmp;
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

        Label l = new Label("Board Dimensions");
        l.setStyle("-fx-font-size: 10.0 pt;-fx-text-fill:black;");//make the text smaller and set the color
        board.add(l, 0, 0);
        l.setMinWidth(100);
        l.setTranslateX(-100);
    	l.setTranslateY(425);

        ComboBox<Integer> x_value = new ComboBox<Integer>();
        x_value.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        x_value.setPromptText("X");
        x_value.setUserData("x_value");//help us locate the node
        x_value.setMinWidth(55.0);//set width that allow us to see the text
        x_value.setStyle("-fx-font-size: 10.0 pt;");//make them smaller
        BackgroundFill bf = new BackgroundFill(Color.WHITE, null, null);
        Background bg = new Background(bf);
        x_value.setBackground(bg);
        x_value.setOnAction((event) -> {
            resize(x_value.getSelectionModel().getSelectedItem(), y);
            // Show the current selected value in the ComboBox
            // for(Node node : board.getChildren()){//find the y_value we want for setting this awesome feature
            //     if(node.getUserData()=="x_value"){
            //         ((ComboBox) node).setPromptText(Integer.toString(x));//change the PromptText to the current seleted value
            //     }else if(node.getUserData()=="y_value"){
            //         ((ComboBox) node).setPromptText(Integer.toString(y));//change the PromptText to the current seleted value
            //     } 
            // }
        });
        board.add(x_value, 0, 0);
        x_value.setTranslateX(-100);
    	x_value.setTranslateY(450);

        ComboBox<Integer> y_value = new ComboBox<Integer>();
        y_value.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        y_value.setPromptText("Y");
        y_value.setUserData("y_value");//help us locate the node
        y_value.setMinWidth(50.0);//set width that allow us to see the text
        y_value.setStyle("-fx-font-size: 10.0 pt;");//make them smaller
        y_value.setBackground(bg);
        y_value.setOnAction((event) -> {
            resize(x, y_value.getSelectionModel().getSelectedItem());
            // Show the current selected value in the ComboBox
            // for(Node node : board.getChildren()){//find the y_value we want for setting this awesome feature
            //     if(node.getUserData()=="y_value"){
            //         ((ComboBox) node).setPromptText(Integer.toString(y));//change the PromptText to the current seleted value
            //     }else if(node.getUserData()=="x_value"){
            //         ((ComboBox) node).setPromptText(Integer.toString(x));//change the PromptText to the current seleted value
            //     }
            // }
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
        currBoard.updateDimensions(x, y);
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
						
						// Checks to see what color
						Shape tmpShape = (Shape) tmp.getChildren().toArray()[j];
						Paint tmpPaint = tmpShape.getFill();
						tmpAtr.put("color", tmpPaint.toString());
						
						// Checks to see what shape
						if (tmp.getChildren().toArray()[j] instanceof Rectangle) {
		        			tmpAtr.put("shape", "rectangle");
		        		}
		        		else if (tmp.getChildren().toArray()[j] instanceof Circle) {
		        			tmpAtr.put("shape", "circle");
		        		}
		        		else if (tmp.getChildren().toArray()[j] instanceof Polygon) {
		        			if (((Polygon)(tmp.getChildren().toArray()[j])).getPoints().size() == 6) {
			        			tmpAtr.put("shape", "triangle");
		            		}
		            		else if (((Polygon)(tmp.getChildren().toArray()[j])).getPoints().size() == 10) {
			        			tmpAtr.put("shape", "pentagon");
		            		}
		            		else {
			        			tmpAtr.put("shape", "hexagon");
		            		}
		            	}
					}
					else if (tmp.getChildren().toArray()[j] instanceof TextField) {
						
						// Checks for text
						TextField tmpText = (TextField) tmp.getChildren().toArray()[j];
						tmpAtr.put("text", tmpText.getText());
					}
					else if (tmp.getChildren().toArray()[j] instanceof Label) {
						Label tmpLabel = (Label) tmp.getChildren().toArray()[j];
						tmpAtr.put("file location", tmpLabel.getText());
					}
				}
				Tile tmpTile = new Tile(tmpX, tmpY, new ArrayList<Rule>(), tmpAtr, 0);
				tmpTilesArr.add(tmpTile);
			}
		}
	}
	currBoard.update_tiles(tmpTilesArr);
	for (Tile tmpTiles: tmpTilesArr) {
		System.out.println(tmpTiles.getAttributes());
	}
}

public Group startBoardEditor(Stage stage){
    scene = stage.getScene();
    Group root = new Group();
    
    boardGrid board = new boardGrid();
    
    localStorage = LocalStorage.getInstance();
    
    if (localStorage.storage.containsKey("board")){
    	currBoard = (Board) localStorage.storage.get("board");
        board.addInitialPieces(currBoard.getDimensionX(), currBoard.getDimensionY());
        board.createBoard(currBoard.get_tiles(), currBoard.getDimensionX(), currBoard.getDimensionY());
    }
    else {
	    currBoard = new Board();
	    currBoard.updateDimensions(5, 5);
        board.createBoard(currBoard.get_tiles(),5, 5);
        board.addInitialPieces(5, 5);
	    localStorage.storage.put("board", currBoard);
    }

    theBoardGrid = board;
    board.getBoard().setTranslateX(100);
    board.getBoard().setTranslateY(100);
    tabs(root);
    
    Button saveButton = new Button("Save Board");
    saveButton.setTranslateX(15);
    saveButton.setTranslateY(625);
    saveButton.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
        	updateTileArrayList(board);
            localStorage.storage.replace("board", currBoard);
        }
    });
    root.getChildren().add(saveButton);
    
    root.getChildren().add(board.getBoard());
    return root;
}
}