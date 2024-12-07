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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ImageSpawn extends Application {

    private static final double epsilon = 1.5;

//    private <T extends Entity, U extends Entity> void processEntity(       Chatgpt ile aldığım bir model kod tekrarını azaltsın diye çünkü ben başaramadım açıkçası
//            T entity,                                                  o yüzden çok fazla kod tekrarı var belki ilerideki zamanlarda döndüğümde düzeltebilirim.
//            Class<T> currentType,
//            Class<U> targetType,
//            double epsilon,
//            String imagePath,
//            Function<T, Entity> determineTargetFunction,
//            BiConsumer<T, U> moveTargetFunction,
//            Supplier<Entity> newEntitySupplier,
//            AnchorPane anchorPane,
//            MainController mainController) {
//
//        // Geçici listeler
//        List<Entity> toAdd = new ArrayList<>();
//        List<Entity> toRemove = new ArrayList<>();
//
//        // Hedef nesneyi bul
//        Entity closestTarget = determineTargetFunction.apply(entity);
//        if (closestTarget != null) {
//            moveTargetFunction.accept(entity, (U) closestTarget);
//
//            // Eğer hedef ile mesafe küçükse, hedefi gizle ve yeni bir nesne ekle
//            if (Math.abs(entity.xCoordinate - closestTarget.xCoordinate) < epsilon &&
//                    Math.abs(entity.yCoordinate - closestTarget.yCoordinate) < epsilon) {
//
//                closestTarget.getView().setVisible(false);  // Görünürlüğü kaldır
//                anchorPane.getChildren().remove(closestTarget.getView());  // AnchorPane'den çıkar
//                toRemove.add(closestTarget);  // Geçici listeye ekle
//
//                // Yeni nesneyi oluştur ve ekle
//                Entity newEntity = newEntitySupplier.get();
//                anchorPane.getChildren().add(newEntity.getView());
//                toAdd.add(newEntity);  // Geçici listeye ekle
//            }
//        }
//
//        // İşlem bittiğinde listeyi güncelle
//        mainController.getEntityList().removeAll(toRemove);  // Silinecek nesneleri ana listeden çıkar
//        mainController.getEntityList().addAll(toAdd);  // Yeni nesneleri ana listeye ekle
//    }


    private void winScene(String winner,Stage primaryStage) {
        AnchorPane newAnchorPane = new AnchorPane();
        Text winMessage = new Text(String.format("%s Kazandı!", winner)); // Dinamik yazı
        winMessage.setFont(Font.font(50));
        winMessage.setFill(Color.WHITE);
        winMessage.setX(200);
        winMessage.setY(250);
        newAnchorPane.getChildren().add(winMessage);

        Scene newScene = new Scene(newAnchorPane, 800, 600);
        primaryStage.setScene(newScene);
    }



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



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {

            List<Entity> toRemove = new ArrayList<>();
            List<Entity> toAdd = new ArrayList<>();


            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.ROCK) {
                    Rock rock = (Rock) entity;
                    Entity closestScissors = rock.determineTarget((Rock) entity, mainController.getEntityList(),EntityType.SCISSORS);
                    rock.moveTarget((Scissors) closestScissors);

                    if (closestScissors != null &&
                            Math.abs(entity.xCoordinate - closestScissors.xCoordinate) < epsilon &&
                            Math.abs(entity.yCoordinate - closestScissors.yCoordinate) < epsilon) {

                        ((Scissors) closestScissors).scissorsView.setVisible(false);
                        anchorPane.getChildren().remove(((Scissors) closestScissors).scissorsView);
                        toRemove.add(closestScissors);

                        Rock newRock = new Rock(getClass().getResource("/Images/Rock.png").toExternalForm());
                        anchorPane.getChildren().add(newRock.rockView);
                        toAdd.add(newRock);
                    }
                }
            }

            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.PAPER) {
                    Paper paper = (Paper) entity;
                    Entity closestRock = paper.determineTarget((Paper) entity, mainController.getEntityList(),EntityType.ROCK);
                    paper.moveTarget((Rock) closestRock);

                    if (closestRock != null &&
                            Math.abs(entity.xCoordinate - closestRock.xCoordinate) < epsilon &&
                            Math.abs(entity.yCoordinate - closestRock.yCoordinate) < epsilon) {

                        ((Rock) closestRock).rockView.setVisible(false);
                        anchorPane.getChildren().remove(((Rock) closestRock).rockView);
                        toRemove.add(closestRock);

                        Paper newPaper = new Paper(getClass().getResource("/Images/Paper.png").toExternalForm());
                        toAdd.add(newPaper);
                        anchorPane.getChildren().add(newPaper.paperView);
                    }
                }
            }

            for (Entity entity : mainController.getEntityList()) {
                if (entity.getEntityType() == EntityType.SCISSORS) {
                    Scissors scissors = (Scissors) entity;
                    Entity closestPaper = scissors.determineTarget((Scissors) entity, mainController.getEntityList(), EntityType.PAPER);
                    scissors.moveTarget((Paper) closestPaper);

                    if (closestPaper != null &&
                            Math.abs(entity.xCoordinate - closestPaper.xCoordinate) < epsilon &&
                            Math.abs(entity.yCoordinate - closestPaper.yCoordinate) < epsilon) {

                        ((Paper) closestPaper).paperView.setVisible(false);
                        anchorPane.getChildren().remove(((Paper) closestPaper).paperView);
                        toRemove.add(closestPaper);

                        Scissors newScissors = new Scissors(getClass().getResource("/Images/Scissors.png").toExternalForm());
                        toAdd.add(newScissors);
                        anchorPane.getChildren().add(newScissors.scissorsView);
                    }
                }
            }

            mainController.getEntityList().removeAll(toRemove);
            mainController.getEntityList().addAll(toAdd);


//            Iterator<Entity> iterator = mainController.getEntityList().iterator();
//            while (iterator.hasNext()) {
//                Entity entity = iterator.next();
//                if (entity.getEntityType() == EntityType.ROCK) {
//                    processEntity(
//                            (Rock) entity,
//                            Rock.class,
//                            Scissors.class,
//                            1.5,
//                            "/Images/Rock.png",
//                            rock -> rock.determineTargetRock(rock, mainController.getEntityList()),
//                            (rock, scissors) -> rock.moveTarget((Scissors) scissors),
//                            () -> new Rock(getClass().getResource("/Images/Rock.png").toExternalForm()),
//                            anchorPane,
//                            mainController
//                    );
//                }
//            }
//
//            // Paper için işlem
//            iterator = mainController.getEntityList().iterator();
//            while (iterator.hasNext()) {
//                Entity entity = iterator.next();
//                if (entity.getEntityType() == EntityType.PAPER) {
//                    processEntity(
//                            (Paper) entity,
//                            Paper.class,
//                            Rock.class,
//                            1.5,
//                            "/Images/Paper.png",
//                            paper -> paper.determineTargetPaper(paper, mainController.getEntityList()),
//                            (paper, rock) -> paper.moveTarget((Rock) rock),
//                            () -> new Paper(getClass().getResource("/Images/Paper.png").toExternalForm()),
//                            anchorPane,
//                            mainController
//                    );
//                }
//            }
//
//            // Scissors için işlem
//            iterator = mainController.getEntityList().iterator();
//            while (iterator.hasNext()) {
//                Entity entity = iterator.next();
//                if (entity.getEntityType() == EntityType.SCISSORS) {
//                    processEntity(
//                            (Scissors) entity,
//                            Scissors.class,
//                            Paper.class,
//                            1.5,
//                            "/Images/Scissors.png",
//                            scissors -> scissors.determineTargetScissors(scissors, mainController.getEntityList()),
//                            (scissors, paper) -> scissors.moveTarget((Paper) paper),
//                            () -> new Scissors(getClass().getResource("/Images/Scissors.png").toExternalForm()),
//                            anchorPane,
//                            mainController
//                    );
//                }
//            }


            if (mainController.getEntityList().size() == 1) {
                Entity lastEntity = mainController.getEntityList().get(0);
                switch (lastEntity.getEntityType()) {
                    case ROCK:
                        winScene("Taş", primaryStage);
                        break;
                    case PAPER:
                        winScene("Kağıt",primaryStage);
                        break;
                    case SCISSORS:
                        winScene("Makas",primaryStage);
                        break;
                    default:
                        break;
                }
            }



        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    public static void main (String[]args){
        launch(args);
    }
}
