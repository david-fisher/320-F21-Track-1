package gamePlay.mainMenu;

import gamePlay.mainMenu.Helpers.Helper;
import gamePlay.boardGrid.boardScene;
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
import java.util.Objects;


public class saveGameViewController {
    private static TableView<Helper.Games> saveTable = new TableView<Helper.Games>();
    private static Label title = new Label("Saved Games");
    private static Button cancelButton = new Button("Cancel");
    private static boolean tableLoaded = false;
    private static Scene savedScene;


    private static void setTitle(){
        title.setId("saved_top_label");
    }

    private static void createTable(Stage primaryStage){
        // TODO update game structure
        Helper.Games[] data = Helper.makeGames();
        final ObservableList<Helper.Games> existingGame =
                FXCollections.observableArrayList();
        Collections.addAll(existingGame, data); // add games


        // game name column
        TableColumn<Helper.Games, String> gameColumn = new TableColumn<>("game");
        gameColumn.setMinWidth(400);
        gameColumn.setCellValueFactory(
                cellData -> (cellData.getValue().getNameString()));

        gameColumn.setStyle("-fx-alignment: CENTER;");

        // time column
        TableColumn<Helper.Games, String> timeColumn = new TableColumn<>("time");
        timeColumn.setMinWidth(150);
        timeColumn.setCellValueFactory(
                cellData -> (cellData.getValue().getDateString()));

        timeColumn.setStyle("-fx-alignment: CENTER;");
//        timeColumn.setStyle("-fx-background-color: transparent;");

        saveTable.setItems(existingGame);
        saveTable.getColumns().addAll(
                gameColumn,
                timeColumn
        );

        addTableButton(primaryStage);
        saveTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    private static void setCancel(){
        cancelButton.setId("saved_cancel_button");
    }


    // create tableview buttons for each column
    private static void addTableButton(Stage primaryStage) {
        TableColumn<Helper.Games, Void> buttonColumn = new TableColumn<>("");

        Callback<TableColumn<Helper.Games, Void>, TableCell<Helper.Games, Void>> cellFactory =
                new Callback<>() {  // callback function
                    @Override
                    public TableCell<Helper.Games, Void> call(final TableColumn<Helper.Games, Void> param) {
                        return new TableCell<>() {

                            private Button button = new Button("Load");

                            {
                                button.setOnAction((ActionEvent event) -> {
                                    Helper.Games data = getTableView().
                                            getItems().
                                            get(getIndex());
                                    // TODO: load game function
                                    System.out.println("Load " + data.getName());

                                    primaryStage.setScene(boardScene.makeScene(primaryStage));
                                });
                                button.setId("table_load_button");
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
        buttonColumn.setMinWidth(100);
        saveTable.getColumns().add(buttonColumn);
        saveTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    protected static Scene makeScene(Stage primaryStage){

        // don't need to reload again if user is coming back from the cancel button
        if (tableLoaded) { return savedScene; }

        setTitle();
        createTable(primaryStage);
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
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("PlayGameFXML.fxml")));
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
//        stackPane.setStyle("-fx-background-color: transparent");
        stackPane.setBackground(Helper.saveBackground());


        ScrollPane scrollView = new ScrollPane(stackPane);
        scrollView.setFitToHeight(true);
        scrollView.setFitToWidth(true);

        savedScene = new Scene(scrollView);
        savedScene.getStylesheets().add("boardGrid/style.css");

        tableLoaded = true;     // don't want to load twice

        return savedScene;
    }
}
