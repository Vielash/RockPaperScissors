package org.example.rpsdemo;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;


public class MainController {
    @FXML
    private Group root;

    private static MainController instance;

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    private List<Entity> rocks = new ArrayList<>();
    private List<Entity> papers = new ArrayList<>();
    private List<Entity> scissorsList = new ArrayList<>();
    private List<Entity> allEntities = new ArrayList<>();

    public void initialize() { //başta yaratılan 15er tane kağıt,makas,taş nesnesini sceneye ekliyor fxml dosyasına. başlar başlamaz çalışıyor bu metot
        String rockImagePath = getClass().getResource("/Images/Rock.png").toExternalForm();
        String scissorsImagePath = getClass().getResource("/Images/Scissors.png").toExternalForm();
        String paperImagePath = getClass().getResource("/Images/Paper.png").toExternalForm();

        for (int i = 0; i < 15; i++) {
            Paper paper = new Paper(paperImagePath);
            papers.add(paper);
            paper.paperView.setId("paper-" + i);
            root.getChildren().add(paper.paperView);

            Rock rock = new Rock(rockImagePath);
            rocks.add(rock);
            rock.rockView.setId("rock-" + i);
            root.getChildren().add(rock.rockView);

            Scissors scissors = new Scissors(scissorsImagePath);
            scissorsList.add(scissors);
            scissors.scissorsView.setId("scissors-" + i);
            root.getChildren().add(scissors.scissorsView);

            System.out.println("Added Rock: " + rock.xCoordinate + ", " + rock.yCoordinate);
            System.out.println("Added Paper: " + paper.xCoordinate + ", " + paper.yCoordinate);
            System.out.println("Added Scissors: " + scissors.xCoordinate + ", " + scissors.yCoordinate);
            System.out.println();
        }

        allEntities.addAll(rocks);
        allEntities.addAll(papers);
        allEntities.addAll(scissorsList);
    }





    public List<Entity> getEntityList() {
        return allEntities;
    }
}


