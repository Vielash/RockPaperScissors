package org.example.rpsdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class ImageSpawn extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/rpsdemo/MainController.fxml"));
        Parent root = loader.load();

        double sceneWidth = 1440;
        double sceneHeight = 1080;

        MainController mainController = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();

        for (Entity entity : mainController.getEntityList()) {
            if (entity.getEntityType() == EntityType.PAPER) {
                Paper paper = (Paper) entity;
                Entity closestHasim = paper.determineTarget(entity, mainController.getEntityList());
                System.out.println("Kağıt " + entity.getxCoordinate() + "," + entity.getyCoordinate() +
                        " en yakın taşı buldu: " + closestHasim.getxCoordinate() + "," + closestHasim.getyCoordinate());

            }
        }
    }
    public static void main (String[]args){
        launch(args);
    }
}
