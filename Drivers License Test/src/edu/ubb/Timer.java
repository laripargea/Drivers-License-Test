package edu.ubb;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Timer extends Application{
    private static final Integer zeit = 30;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty minuten = new SimpleIntegerProperty(zeit);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Timer");
        Group root = new Group();
        Scene scene = new Scene(root, 200, 150,Color.LIGHTGREEN);

        //Binden timerLabel mit minuten
        timerLabel.textProperty().bind(minuten.asString());
        timerLabel.setTextFill(Color.BLACK);
        timerLabel.setStyle("-fx-font-size: 4em;");

        if (timeline != null) {
            timeline.stop();
        }
        minuten.set(zeit);
        timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.minutes(zeit+1),
                        new KeyValue(minuten, 0)));
        timeline.playFromStart();

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefWidth(scene.getWidth());
        vBox.getChildren().addAll(timerLabel);
        vBox.setLayoutY(30);
        root.getChildren().add(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}