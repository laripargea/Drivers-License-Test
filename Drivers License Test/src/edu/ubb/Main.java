package edu.ubb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

     @Override
     public void start(Stage primaryStage) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
         primaryStage.setTitle("FÜHRERSCHEIN THEORIEPRÜFUNG");
         primaryStage.setScene(new Scene(root, 700, 500));
         primaryStage.show();
    }

    public static void main(String[] args){
         launch(args);
    }
}