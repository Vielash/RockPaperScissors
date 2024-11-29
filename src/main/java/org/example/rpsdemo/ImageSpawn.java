package org.example.rpsdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ImageSpawn extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("C:\\Users\\engin\\IdeaProjects\\RPSdemo\\src\\main\\resources\\org\\example\\rpsdemo\\MainController.fxml"));
        Parent root = loader.load();

        double sceneWidth = 1440;
        double sceneHeight = 1080;

        Scene scene = new Scene(root,sceneWidth,sceneHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
