package org.example.rpsdemo;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

//senle de işim yok daha
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

    public void initialize() {
        String rockImagePath = getClass().getResource("/Images/Rock.png").toExternalForm();
        String scissorsImagePath = getClass().getResource("/Images/Scissors.png").toExternalForm();
        String paperImagePath = getClass().getResource("/Images/Paper.png").toExternalForm();

        if (rockImagePath == null || scissorsImagePath == null || paperImagePath == null) {
            System.out.println("Görüntü bulunamadı! Yolu kontrol edin.");
            return;
        }

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

        System.out.println("Rocks: " + rocks.size());
        System.out.println("Papers: " + papers.size());
        System.out.println("Scissors: " + scissorsList.size());
    }

    public List<Entity> getEntityList() {
        return allEntities;
    }
}


