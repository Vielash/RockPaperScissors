package org.example.rpsdemo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ImageSpawn extends Application {

//    private <T extends Entity, U extends Entity> void processEntities(    chatgptden aldım benim değil
//            MainController mainController,
//            AnchorPane anchorPane,
//            Class<T> currentType,
//            Class<U> targetType,
//            double epsilon,
//            String newEntityImage,
//            Function<T, Entity> determineTarget,
//            Consumer<Entity> moveTarget,
//            Function<String, T> createNewEntity) {
//
//        List<Entity> toRemove = new ArrayList<>();
//        List<Entity> toAdd = new ArrayList<>();
//
//        for (Entity entity : mainController.getEntityList()) {
//            if (currentType.isInstance(entity)) {
//                T currentEntity = currentType.cast(entity);
//
//                Entity closestTarget = determineTarget.apply(currentEntity);
//                if (closestTarget != null) {
//                    moveTarget.accept(closestTarget);
//
//                    if (Math.abs(entity.xCoordinate - closestTarget.xCoordinate) < epsilon &&
//                            Math.abs(entity.yCoordinate - closestTarget.yCoordinate) < epsilon) {
//
//                        if (targetType.isInstance(closestTarget)) {
//                            U target = targetType.cast(closestTarget);
//                            target.getView().setVisible(false);
//                            anchorPane.getChildren().remove(target.getView());
//                            toRemove.add(target);
//                        }
//
//                        T newEntity = createNewEntity.apply(newEntityImage);
//                        toAdd.add(newEntity);
//                        anchorPane.getChildren().add(newEntity.getView());
//                    }
//                }
//            }
//        }
//
//        mainController.getEntityList().removeAll(toRemove);
//        mainController.getEntityList().addAll(toAdd);
//    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/rpsdemo/MainController.fxml"));
        Parent root = loader.load();

        AnchorPane anchorPane = (AnchorPane) root;

        MainController mainController = loader.getController();
        Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10 ), event -> {
            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.ROCK) {
                    Rock rock = (Rock) entity;
                    Entity closestScissors = rock.determineTargetRock((Rock) entity, mainController.getEntityList());
                    rock.moveTarget((Scissors) closestScissors);

                    double epsilon = 1.5;
                    if (closestScissors != null &&
                            Math.abs(entity.xCoordinate - closestScissors.xCoordinate) < epsilon &&
                            Math.abs(entity.yCoordinate - closestScissors.yCoordinate) < epsilon) {


                        ((Scissors) closestScissors).scissorsView.setVisible(false);
                        anchorPane.getChildren().remove(((Scissors) closestScissors).scissorsView);
                        mainController.getEntityList().remove(closestScissors);


                        Rock newRock = new Rock(getClass().getResource("/Images/Rock.png").toExternalForm());
                        anchorPane.getChildren().add(newRock.rockView);
                        mainController.getEntityList().add(newRock);

                    }
                }
            }



            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.PAPER) {
                    Paper paper = (Paper) entity;
                    Entity closestRock = paper.determineTargetPaper((Paper) entity, mainController.getEntityList());
                    paper.moveTarget((Rock) closestRock);

                    double epsilon = 1.5;
                    if (closestRock != null &&
                            Math.abs(entity.xCoordinate - closestRock.xCoordinate) < epsilon &&
                            Math.abs(entity.yCoordinate - closestRock.yCoordinate) < epsilon) {



                        ((Rock) closestRock).rockView.setVisible(false);
                        anchorPane.getChildren().remove(((Rock) closestRock).rockView);
                        mainController.getEntityList().remove(closestRock);

                        Paper newPaper = new Paper(getClass().getResource("/Images/Paper.png").toExternalForm());
                        mainController.getEntityList().add(newPaper);
                        anchorPane.getChildren().add(newPaper.paperView);

                    }
                }
            }



            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.SCISSORS) {
                    Scissors scissors = (Scissors) entity;
                    Entity closestPaper = scissors.determineTargetScissors((Scissors) entity, mainController.getEntityList());
                        scissors.moveTarget((Paper) closestPaper);

                    double epsilon = 1.5;
                    if (closestPaper != null &&
                            Math.abs(entity.xCoordinate - closestPaper.xCoordinate) < epsilon &&
                            Math.abs(entity.yCoordinate - closestPaper.yCoordinate) < epsilon) {



                        ((Paper) closestPaper).paperView.setVisible(false);
                        anchorPane.getChildren().remove(((Paper) closestPaper).paperView);
                        mainController.getEntityList().remove(closestPaper);

                        Scissors newScissors = new Scissors(getClass().getResource("/Images/Scissors.png").toExternalForm());
                        mainController.getEntityList().add(newScissors);
                        anchorPane.getChildren().add(newScissors.scissorsView);

                    }
                }
            }


            System.out.println();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    public static void main (String[]args){
        launch(args);
    }
}
