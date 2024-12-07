package org.example.rpsdemo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.control.Labeled;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ImageSpawn extends Application {

    //iyi programcı commentline kullanılmadan kodu okunabilir olan programcıdır ilkesi olsa da kendime kodun okunulabilirliği hakkında çok güvenmediğim için eklicem.

    private static final double epsilon = 1.5;
    public static final int SPEED_OF_GAME = 25;

    private void findAndMove(MainController mainController,AnchorPane anchorPane, EntityType type,EntityType targetType) throws NullPointerException {
        List<Entity> toAdd = new ArrayList<>();     /*Burda entitylisti for loopun içerisinden kendinden değiştirdiğim için concurrentmodification exception alıyodum
                                                       o yüzden entityliste eklemek ve çıkarmak için 2 liste oluşturdum  */
        List<Entity> toRemove = new ArrayList<>();

        for (Entity entity : mainController.getEntityList()) {
            if (entity.getEntityType() == type) {
                Entity closestTarget = entity.determineTarget(entity, mainController.getEntityList(), targetType); /* Burda determinetargetin argümanlarına girerek for loopun
                                                                                                                       içindeki entitynin en yakınındaki düsman tipini buluyor*/
                entity.moveTarget(closestTarget);   // en yakın düşman nesnesine vektörel olarak hareket ediyor.

                try {
                    if (closestTarget != null &&
                            Math.abs(entity.getxCoordinate() - closestTarget.getxCoordinate()) < epsilon && // belirtilen eşik değere göre temas kontrolü yapılıyor
                            Math.abs(entity.getyCoordinate() - closestTarget.getyCoordinate()) < epsilon) {

                        entity.handleCollision(closestTarget, mainController, anchorPane, toAdd, toRemove); // burda handleCollasion yapılıyor açıklaması entitynin içinde.
                    }
                } catch (Exception e) {
                    System.err.println("Hata: nullpointer Exception yaşandı" + e.getMessage()); //eskiden atıyodur exception onun için yaptım belli olmaz
                }

                }
            }
            mainController.getEntityList().removeAll(toRemove); // geçici olarak eklediğim listelere ana listeye ekleme çıkarma yapıyorum.
            mainController.getEntityList().addAll(toAdd);
    }

    private void restartGame(Stage primaryStage) {  //tekrar oyna butonu için start metodunun aynısını yazıyorum ki tekrar aynı senaryo yaşansın ve başlasın(scene stage yaratımı)
        try {                                       //chat gpt yaptı bunu da(sondaki düğmelerin mantığını chatgptye yaptırdım javafx e o kadar hakim olmadığım için)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/rpsdemo/MainController.fxml"));
            Parent root = loader.load();


            AnchorPane anchorPane = (AnchorPane) root;
            MainController mainController = loader.getController();


            Scene gameScene = new Scene(root);
            gameScene.setFill(Color.BLACK);
            primaryStage.setScene(gameScene);
            primaryStage.setFullScreen(true);

            startGame(mainController, anchorPane,primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showWinScene(Stage primaryStage, String message) {             //chatgpt yaptı  biri kazandığında o son ekranda çıkan yazının metodu

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);


        Label winLabel = new Label(message);
        winLabel.setFont(new Font("Arial", 36));
        winLabel.setTextFill(Color.GREEN);

        Button exitButton = new Button("Çıkış"); //cıkıs butonu için callback
        exitButton.setOnAction(e -> {
            timeline.stop();
            System.exit(0);
            Platform.exit();
        });

        Button playAgainButton = new Button("Tekrar Oyna"); // tekrar oyna butonuna basıncaki callback metot lambda ile
        playAgainButton.setOnAction(e -> {
            timeline.stop();
            restartGame(primaryStage);
        });

        vbox.getChildren().addAll(winLabel, playAgainButton, exitButton);

        Scene winScene = new Scene(vbox, 800, 600);
        primaryStage.setScene(winScene);
    }



    private void checkWinCondition(Stage primaryStage,MainController mainController) { //birinin kazanıp kazanmadıgını kontrol ediyor.
        List<Entity> entities = mainController.getEntityList();

        if (entities.stream().allMatch(entity -> entity.getEntityType() == EntityType.ROCK)) {
            showWinScene(primaryStage, "Taş Kazandı!");
            timeline.stop();
        } else if (entities.stream().allMatch(entity -> entity.getEntityType() == EntityType.PAPER)) {
            showWinScene(primaryStage, "Kağıt Kazandı!");
            timeline.stop();
        } else if (entities.stream().allMatch(entity -> entity.getEntityType() == EntityType.SCISSORS)) {
            showWinScene(primaryStage, "Makas Kazandı!");
            timeline.stop();
        }
    }

    private Timeline timeline;
    private void startGame(MainController mainController, AnchorPane anchorPane, Stage primaryStage) { //timeline ile sonsuz döngüye almasını sağlıyor ve oyunu başlatıyor
        if (timeline != null) {
            timeline.stop();
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(SPEED_OF_GAME), event -> {

            findAndMove(mainController, anchorPane, EntityType.ROCK, EntityType.SCISSORS); //hareket etmesini sağlıyor 3 nesnenin de  ROCK için
            findAndMove(mainController, anchorPane, EntityType.PAPER, EntityType.ROCK); //her PAPER nesnesi için
            findAndMove(mainController, anchorPane, EntityType.SCISSORS, EntityType.PAPER); // her SCISSORS nesnesi için

            checkWinCondition(primaryStage, mainController); // kazanıp kazanmadıgını kontrol ediyor.
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // sonsuza kadar çalışmasını sağlıyor
        timeline.play();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/rpsdemo/MainController.fxml")); //oluşturduğum sceneyi ekliyor
        Parent root = loader.load();

        AnchorPane anchorPane = (AnchorPane) root;      //scenedeki anchorpane yi temsil ediyor

        MainController mainController = loader.getController(); //main controllerdan bilgi alarak maincontroller nesnesi oluşturuyor
        Scene scene = new Scene(root);   //rootu sceneye atıyor
        scene.setFill(Color.BLACK);
        primaryStage.setFullScreen(true);      //buraları anlarsınız
        primaryStage.setScene(scene);
        primaryStage.show();

        startGame(mainController,anchorPane,primaryStage); //oyunu başlatıyor fonksiyonlar çok bağımlı oldu birbirine wincondution için iyi mi kötü mü bilemedim

    }
    public static void main (String[]args){
        launch(args);
    }
}
