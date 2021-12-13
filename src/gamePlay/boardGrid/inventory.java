package gamePlay.boardGrid;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;

public class inventory {

    private static final HBox contents = new HBox(10);
    private static final ScrollPane scrollView = new ScrollPane();
    private static final VBox canvas = new VBox(10);

    private static double xCoordinate;
    private static double yCoordinate;

    private static final Effect blurEffect =
            new BoxBlur(20, 20, 3);

    private static final ImageView background = new ImageView();
    private static Button closeButton = new Button("Close");

    private static boolean contentsUpdated = true;

    // get a duplicated background
    private static Image getBackground(Stage stage) {
        // get coordinates
        int x = (int) stage.getX() - 160;   // configure the starting coordinate if needed
        int y = (int) stage.getY();
        int width = (int) stage.getWidth();
        int height = (int) stage.getHeight();

        try {
            java.awt.Robot robot = new java.awt.Robot();
            // get a screenshot
            java.awt.image.BufferedImage image = robot.createScreenCapture(new java.awt.Rectangle(x, y, width, height));

            return SwingFXUtils.toFXImage(image, null);
        } catch (java.awt.AWTException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void draggable(final Stage stage, final Node node) {

        node.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            xCoordinate = stage.getX() - mouseEvent.getScreenX();
            yCoordinate = stage.getY() - mouseEvent.getScreenY();
            node.setCursor(Cursor.MOVE);
        });
        BooleanProperty drag = new SimpleBooleanProperty(false);

        node.setOnMouseReleased(mouseEvent -> {
            node.setCursor(Cursor.HAND);

            if (drag.get()) {
                stage.hide();

                Timeline pause = new Timeline(new KeyFrame(Duration.millis(50), event -> {
                    background.setImage(getBackground(stage));
                    if (node instanceof StackPane) {
                        ((StackPane) node).getChildren().set(
                                0,
                                background
                        );
                    }
                    stage.show();
                }));
                pause.play();
            }

            drag.set(false);
        });
        node.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() + xCoordinate);
            stage.setY(mouseEvent.getScreenY() + yCoordinate);

            if (node instanceof StackPane){
                ((StackPane)node).getChildren().set(
                        0,
                        smokeRectangle(stage)
                );
            }

            drag.set(true);
        });
        node.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                node.setCursor(Cursor.HAND);
            }
        });
        node.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                node.setCursor(Cursor.DEFAULT);
            }
        });
    }

    // return a rectangle with smoke effect
    private static Rectangle smokeRectangle(Stage stage) {
        return new Rectangle(
                stage.getWidth(),
                stage.getHeight(),
                Color.WHITESMOKE.deriveColor(
                        0, 1, 1, 0.08
                )
        );
    }

    private static void setupContentClose(Stage stage, StackPane blurBackground){

        closeButton.setOnAction(event -> {
            System.out.println("You closed inventory");
            blurBackground.getChildren().clear();
            stage.close();

        });
    }


    //TODO: Get an array list and loop through that to add all images and add scroll bar
    //highlight selected card
    //different method to load and different to show so it doesn't load from scratch every time
    protected static void showCards(){

        if (!contentsUpdated) { return; }   // don't duplicate contents

        try {
            ArrayList<ImageView> cardTest = getDummyData();
            AnchorPane cards = new AnchorPane();
            ArrayList<HBox> hboxes = new ArrayList<>();
            ArrayList<Boolean> selectedList = new ArrayList<>();

            double spacing = 200.0;
            for(ImageView card : cardTest){
                HBox hBox_card = new HBox();
                selectedList.add(false);
                hBox_card.setStyle("-fx-border-color: black;" + "-fx-border-width: 5;");
                hBox_card.getChildren().add(card);

                // TODO: Varsha, I replaced the events handler here
                hBox_card.setOnMouseClicked(event -> {
                    int index = getCardIndex(hBox_card, hboxes);

                    boolean currentMode = selectedList.get(index);

                    hBox_card.setStyle((currentMode?
                            "-fx-border-color: black;"
                            : "-fx-border-color: red;")
                            + "-fx-border-width: 5;"
                    );

                    selectedList.set(index, !currentMode);

                    System.out.println("INDEX: " + index);
                });

                AnchorPane.setRightAnchor(hBox_card, spacing);
                spacing += 100.0;
                hboxes.add(hBox_card);
                cards.getChildren().addAll(hBox_card);
            }

            contents.getChildren().add(cards);
            contents.setStyle("-fx-background-color: transparent");

            scrollView.setContent(contents);
            scrollView.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            canvas.getChildren().add(scrollView);
            contentsUpdated = false;

            // TODO: onclick method --> get the index in array list as well
//            cards.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    System.out.println("mouse click detected! "+event.getSource());
//                    System.out.println(event.getX());
//
//                    if (event.getTarget() != cards) {
//
//                        if (event.getTarget() instanceof ImageView){
//
//                            return;
//                        }
//
//                        Node target = (Node) event.getTarget();
//                        target.setStyle("-fx-border-color: red;" + "-fx-border-width: 5;");
//                        int index = getCardIndex(target, hboxes);
//                        System.out.println("INDEX: " + index);
//                    }
//
//                }
//            });
            // TODO: Add cavas.getChildren.addAll --> button for playing the card

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static int getCardIndex(Node hbox_target, ArrayList<HBox> hBoxes){
        int index = -1;
        for(int i = 0; i < hBoxes.size(); i++){
            if(hBoxes.get(i).equals(hbox_target)) {
                index = i;
                break;
            }
        }
        return index;
    }

    // take the primary stage and contents
    private static void showInventory(Stage primaryStage){
        Stage popStage = new Stage();

        StackPane blurBackground = new StackPane();

        setupContentClose(popStage, blurBackground);

        showCards();

        blurBackground.getChildren().setAll(background, canvas);
        blurBackground.setStyle("-fx-background-color: null");

        double width = 700, height = 300;

        Scene popoutScene = new Scene(
                blurBackground,
                width, height,
                Color.TRANSPARENT
        );
        popoutScene.getStylesheets().add("gamePlay/boardGrid/style.css");

        Platform.setImplicitExit(false);

        background.setImage(getBackground(primaryStage));
        background.setEffect(blurEffect);

//        draggable(primaryStage, blurBackground);


        popStage.setScene(popoutScene);
        popStage.initModality(Modality.APPLICATION_MODAL);
        popStage.initOwner(primaryStage);
//        popStage.setResizable(false);
        popStage.initStyle(StageStyle.TRANSPARENT);

        popStage.setX( primaryStage.getX() + 80);
        popStage.setY( primaryStage.getY() + (primaryStage.getHeight() / 2) - (height / 2));

        popStage.show();
    }

    public Button getInventoryButton(Stage primaryStage){
        Button inventoryButton = new Button("Inventory");
        inventoryButton.setRotate(-90);
        inventoryButton.setId("inventory_button");

        inventoryButton.setOnAction(event -> {
            showInventory(primaryStage);
        });

        closeButton.setId("close_popout");
        HBox temp = new HBox(closeButton);
        temp.setAlignment(Pos.TOP_LEFT);

        if (canvas.getChildren().size() == 0){
            canvas.getChildren().add(temp);
        }

        return inventoryButton;
    }

    public static ArrayList<ImageView> getDummyData() throws FileNotFoundException {
        ImageView cardTest = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Dice.png", 70, 70);
        ImageView cardTest2 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Deck.png", 70, 70);
        ImageView cardTest3 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Deck.png", 70, 70);
        ImageView cardTest4 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Deck.png", 70, 70);
        ImageView cardTest5 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Deck.png", 70, 70);
        ImageView cardTest6 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Deck.png", 70, 70);
        ImageView cardTest7 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Deck.png", 70, 70);
        ImageView cardTest8 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Deck.png", 70, 70);
        ImageView cardTest9 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Deck.png", 70, 70);
        ImageView cardTest10 = gamePlay.mainMenu.Helpers.Helper.imageMaker("src/gamePlay/images/Dice.png", 70, 70);

        ArrayList<ImageView> cardList = new ArrayList<>();
        cardList.add(cardTest);
        cardList.add(cardTest2);
        cardList.add(cardTest3);
        cardList.add(cardTest4);
        cardList.add(cardTest5);
        cardList.add(cardTest6);
        cardList.add(cardTest7);
        cardList.add(cardTest8);
        cardList.add(cardTest9);
        cardList.add(cardTest10);
        return cardList;
    }


}
