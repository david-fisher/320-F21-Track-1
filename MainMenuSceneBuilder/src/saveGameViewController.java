import Helpers.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Collections;


public class saveGameViewController {
    private static TableView<Helper.Games> saveTable = new TableView<Helper.Games>();
    private static Label title = new Label("Saved Games");
    private static Button cancelButton = Helper.ButtonMaker("Cancel", Font.font("Helvetica",
            FontWeight.NORMAL,
            FontPosture.REGULAR,
            20)
    );


    private static void setTitle(){
        title.setFont(new Font("Helvetica", 20));
    }

    private static void createTable(){
        // TODO update game structure
        Helper.Games[] data = Helper.makeGames();
        final ObservableList<Helper.Games> existingGame =
                FXCollections.observableArrayList();
        Collections.addAll(existingGame, data); // add games

        // saving-game pop-out interface
        saveTable.setId("saveTable");

        // game name column
        TableColumn<Helper.Games, String> gameColumn = new TableColumn<>("game");
        gameColumn.setMinWidth(200);
        gameColumn.setCellValueFactory(
                cellData -> (cellData.getValue().getNameString()));

        gameColumn.setStyle("-fx-alignment: CENTER;");

        // time column
        TableColumn<Helper.Games, String> timeColumn = new TableColumn<>("time");
        timeColumn.setMinWidth(300);
        timeColumn.setCellValueFactory(
                cellData -> (cellData.getValue().getDateString()));

        timeColumn.setStyle("-fx-alignment: CENTER;");
//        timeColumn.setStyle("-fx-background-color: transparent;");

        saveTable.setItems(existingGame);
        saveTable.getColumns().addAll(
                gameColumn,
                timeColumn
        );
        addTableButton();
        saveTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        saveTable.setMaxSize(700, 300);
    }


    private static void setCancel(){
        cancelButton.setOnAction((ActionEvent event) -> {
            System.out.println("cancel from saved game interface.");
        });
        Helper.adjustButtonSize(cancelButton, 20, 100);
    }


    // create tableview buttons for each column
    private static void addTableButton() {
        TableColumn<Helper.Games, Void> buttonColumn = new TableColumn<>("");

        Callback<TableColumn<Helper.Games, Void>, TableCell<Helper.Games, Void>> cellFactory =
                new Callback<>() {  // callback function
                    @Override
                    public TableCell<Helper.Games, Void> call(final TableColumn<Helper.Games, Void> param) {
                        return new TableCell<>() {

                            private final Button button = new Button("Load");   // TODO: load button style
                            {
                                button.setOnAction((ActionEvent event) -> {
                                    Helper.Games data = getTableView().
                                            getItems().
                                            get(getIndex());
                                    // TODO: load game function
                                    System.out.println("Load " + data.getName());
                                });
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                setGraphic(empty? null : button);
                            }
                        };
                    }
                };
        buttonColumn.setCellFactory(cellFactory);
        saveTable.getColumns().add(buttonColumn);

    }

    protected static Scene makeScene(Stage primaryStage){

        setTitle();
        createTable();
        setCancel();

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(
                35,
                100,
                50,
                50
        ));

        // fill out the vbox
        vbox.getChildren().addAll(title,
                saveTable,
                cancelButton
        );
        cancelButton.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("PLayGameFXML.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
        });
        // generate scene
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(vbox);
        return new Scene(stackPane,
                Screen.getPrimary().getBounds().getWidth()
                        *0.5    // factors
                ,
                Screen.getPrimary().getBounds().getHeight()
                        *0.5
        );
    }
}
