package org.example.rpsdemo;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
//senle de işim yok daha
public class HelloController {
    @FXML
    private Group root;

    public void init() {
        String paperImagePath = getClass().getResource("C:\\Users\\engin\\IdeaProjects\\RPSdemo\\src\\main\\resources\\org\\example\\rpsdemo\\Paper.png").toExternalForm();
        Paper paper = new Paper(paperImagePath);

        root.getChildren().add(paper.paperView);
    }




}