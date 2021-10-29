import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;

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
	}
	
	public void createCircle(Group root) {
		Circle circle = new Circle(25);
        circle.setCenterY(275);
        circle.setCenterX(50);
        root.getChildren().add(circle);
        dragNDrop(circle);
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
	
    public void start(Stage stage){
        Group root = new Group();
        
        // Initial Setup
        tabs(root);
        createRectangle(root);
        createCircle(root);
        createTriangle(root);
        createPentagon(root);
        createHexagon(root);
        
        Scene scene = new Scene(root, 800, 600, Color.rgb(105, 162, 255));
        stage.setTitle("Board Editor");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}