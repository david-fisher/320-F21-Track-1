import Helpers.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.util.Collections;

public class saveGameViewController {
    private static TableView<Helper.Games> saveTable = new TableView<Helper.Games>();
    protected static Scene makeScene(Stage primaryStage){

        // TODO update game structure
        Helper.Games[] data = Helper.makeGames();
        final ObservableList<Helper.Games> existingGame = FXCollections.observableArrayList();
        Collections.addAll(existingGame, data); // add games

        // title
        final Label title = new Label("Saved Games");
        title.setFont(new Font("Arial", 20));

        // saving-game pop-out interface
//        TableView<Helper.Games> saveTable = new TableView<Helper.Games>();
        saveTable.setId("saveTable");
//        saveTable.setEditable(true);
        // game name column
        TableColumn<Helper.Games, String> gameColumn = new TableColumn<>("game");
        gameColumn.setMinWidth(200);
        gameColumn.setCellValueFactory(
                cellData -> (cellData.getValue().getNameString()));
        gameColumn.setStyle("-fx-alignment: CENTER;");
        // game time column
        TableColumn<Helper.Games, String> timeColumn = new TableColumn<>("time");
        timeColumn.setMinWidth(300);
        timeColumn.setCellValueFactory(
                cellData -> (cellData.getValue().getDateString()));
        timeColumn.setStyle("-fx-alignment: CENTER;");
//        timeColumn.setStyle("-fx-background-color: transparent;");

        saveTable.setItems(existingGame);
        saveTable.getColumns().addAll(gameColumn, timeColumn);
        addButton();
        saveTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        saveTable.setMaxSize(700, 300);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 50, 50, 60));
        vbox.getChildren().addAll(title, saveTable);

        // generate scene
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(vbox);
        return new Scene(stackPane,
                Screen.getPrimary().getBounds().getWidth()
                        *0.5
                ,
                Screen.getPrimary().getBounds().getHeight()
                        *0.64814814814
        );
    }

    private static void addButton() {
        TableColumn<Helper.Games, Void> buttonColumn = new TableColumn<>("");

        Callback<TableColumn<Helper.Games, Void>, TableCell<Helper.Games, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Helper.Games, Void> call(final TableColumn<Helper.Games, Void> param) {
                return new TableCell<>() {

                    private final Button button = new Button("Load");
                    {
                        button.setOnAction((ActionEvent event) -> {
                            Helper.Games data = getTableView().getItems().get(getIndex());
                            System.out.println("Load " + data.getName());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button);
                        }
                    }
                };
            }
        };
        buttonColumn.setCellFactory(cellFactory);
        saveTable.getColumns().add(buttonColumn);

    }
}
