package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RuleEditorMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			// adding left side tabs for rule editor
			TabPane sideTabs = new TabPane();
			sideTabs.setTabMinHeight(130);
			sideTabs.setTabMaxHeight(130);
			sideTabs.setTabMinWidth(70);
			sideTabs.setTabMaxWidth(70);
			sideTabs.setRotateGraphic(true);   
			
			Tab tileTab = new Tab();
			Label tr = new Label("Tile Rule");
			tr.setRotate(90);
			StackPane trStp = new StackPane(new Group(tr));
			trStp.setRotate(90);
			tileTab.setGraphic(trStp);
			tileTab.setClosable(false);
			sideTabs.getTabs().add(tileTab);
			
			Tab movementTab = new Tab();
			Label mr = new Label("Movement Rule");
			mr.setRotate(90);
			StackPane mrStp = new StackPane(new Group(mr));
			mrStp.setRotate(90);
			movementTab.setGraphic(mrStp);
			sideTabs.getTabs().add(movementTab);
			movementTab.setClosable(false);
			
			Tab turnTab = new Tab();
			Label tur = new Label("Turn Rule");
			tur.setRotate(90);
			StackPane turStp = new StackPane(new Group(tur));
			turStp.setRotate(90);
			turnTab.setGraphic(turStp);
			sideTabs.getTabs().add(turnTab);
			turnTab.setClosable(false);
			
			Tab winTab = new Tab();
			Label wc = new Label("Win Condition");
			wc.setRotate(90);
			StackPane wcStp = new StackPane(new Group(wc));
			wcStp.setRotate(90);
			winTab.setGraphic(wcStp);
			sideTabs.getTabs().add(winTab);
			root.setLeft(sideTabs);
			winTab.setClosable(false);
			
			sideTabs.setSide(Side.LEFT);
			
			// win condition editor
			VBox wcEditor = new VBox();
			// points to win condition
			HBox pointsToWin = new HBox();
			CheckBox selectPointsToWin = new CheckBox();
			TextField pts = new TextField();
			pts.setMaxWidth(50);
			Label ptwLabel = new Label("points are needed for a player to win");
			pointsToWin.getChildren().addAll(selectPointsToWin, pts, ptwLabel);
			pointsToWin.setSpacing(5);
			// reach tile win condition
			HBox reachTile = new HBox();
			CheckBox selectReachTile = new CheckBox();
			Label rtLabel = new Label("Player can win by reaching tile");
			TextField tile = new TextField();
			tile.setMaxWidth(50);
			reachTile.getChildren().addAll(selectReachTile, rtLabel, tile);
			reachTile.setSpacing(5);
			// others points condition
			HBox othersPoints = new HBox();
			CheckBox selectOthersPoints = new CheckBox();
			Label opLabel1 = new Label("Player wins when all other players have");
			TextField otherspts = new TextField();
			otherspts.setMaxWidth(50);
			Label opLabel2 = new Label("points remaining");
			othersPoints.getChildren().addAll(selectOthersPoints, opLabel1, otherspts, opLabel2);
			othersPoints.setSpacing(5);
			// add all content to win condition tab
			wcEditor.getChildren().addAll(pointsToWin, reachTile, othersPoints);
			wcEditor.setSpacing(5);
			wcEditor.setPadding(new Insets(50, 50, 50, 50));
			winTab.setContent(wcEditor);
			
			
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
