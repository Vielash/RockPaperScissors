package org.example.rpsdemo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100 ), event -> {
            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.ROCK) {
                    Rock rock = (Rock) entity;
                    Entity closestScissors = rock.determineTargetRock((Rock) entity, mainController.getEntityList());
                        System.out.println("taşın hedefi: " + closestScissors.getEntityType() +
                                " [" + closestScissors.getxCoordinate() + ", " + closestScissors.getyCoordinate() + "]");
                    if (closestScissors instanceof Scissors) {
                        rock.moveTarget((Scissors) closestScissors);
                    }
                }
            }
            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.PAPER) {
                    Paper paper = (Paper) entity;
                    Entity closestRock = paper.determineTargetPaper((Paper) entity, mainController.getEntityList());
                    System.out.println("Kağıt hedefi: " + closestRock.getEntityType() +
                            " [" + closestRock.getxCoordinate() + ", " + closestRock.getyCoordinate() + "]");
                    if (closestRock instanceof Rock) {
                        paper.moveTarget((Rock) closestRock);
                    }
                }
            }
            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.SCISSORS) {
                    Scissors scissors = (Scissors) entity;
                    Entity closestPaper = scissors.determineTargetScissors((Scissors) entity, mainController.getEntityList());
                    System.out.println("makas hedefi: " + closestPaper.getEntityType() +
                            " [" + closestPaper.getxCoordinate() + ", " + closestPaper.getyCoordinate() + "]");
                    if (closestPaper instanceof Paper) {
                        scissors.moveTarget((Paper) closestPaper);
                    }
                }
            }
            System.out.println();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Sonsuz döngüde çalıştır
        timeline.play(); // Hareketi başlat

    }
    public static void main (String[]args){
        launch(args);
    }
}
