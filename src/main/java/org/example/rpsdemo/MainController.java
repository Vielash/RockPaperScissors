package org.example.rpsdemo;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

//senle de işim yok daha
public class MainController {
    @FXML
    private Group root;

    public void initialize() {
        String rockImagePath = getClass().getResource("/Images/Rock.png").toExternalForm();
        String scissorsImagePath = getClass().getResource("/Images/Scissors.png").toExternalForm();
        String paperImagePath = getClass().getResource("/Images/Paper.png").toExternalForm();

        if (rockImagePath == null || scissorsImagePath == null || paperImagePath == null) {
            System.out.println("Görüntü bulunamadı! Yolu kontrol edin.");
            return;
        }

        List<Rock> rocks = new ArrayList<>();
        List<Paper> papers = new ArrayList<>();
        List<Scissors> scissorsList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Rock rock = new Rock(rockImagePath);
            rocks.add(rock);
            rock.rockView.setId("rock-" + i);
            root.getChildren().add(rock.rockView);

            Paper paper = new Paper(paperImagePath);
            papers.add(paper);
            paper.paperView.setId("paper-" + i);
            root.getChildren().add(paper.paperView);

            Scissors scissors = new Scissors(scissorsImagePath);
            scissorsList.add(scissors);
            scissors.scissorsView.setId("scissors-" + i);
            root.getChildren().add(scissors.scissorsView);

            System.out.println("Added Rock: " + rock.xCoordinate + ", " + rock.yCoordinate);
            System.out.println("Added Paper: " + paper.xCoordinate + ", " + paper.yCoordinate);
            System.out.println("Added Scissors: " + scissors.xCoordinate + ", " + scissors.yCoordinate);
            System.out.println();

        }

        System.out.println("Rocks: " + rocks.size());
        System.out.println("Papers: " + papers.size());
        System.out.println("Scissors: " + scissorsList.size());


    }


    }




