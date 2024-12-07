package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Comparator;
import java.util.List;

public class Scissors extends Entity{
    public static final double SPEED = 2.0;

    ImageView scissorsView;

    public Scissors(String scissorsPath) {
        super(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, EntityType.SCISSORS);
        this.scissorsView = new ImageView(new Image(scissorsPath));

        this.scissorsView.setFitWidth(40);
        this.scissorsView.setFitHeight(40);

        this.scissorsView.setX(this.xCoordinate);
        this.scissorsView.setY(this.yCoordinate);
    }

    @Override
    public void handleCollision(
            Entity target,
            MainController mainController,
            AnchorPane anchorPane,
            List<Entity> toAdd,
            List<Entity> toRemove) {

        if (target instanceof Paper) {
            Paper paper = (Paper) target;

            paper.paperView.setVisible(false);
            anchorPane.getChildren().remove(paper.paperView);
            toRemove.add(paper);

            Scissors newScissors = new Scissors(getClass().getResource("/Images/Scissors.png").toExternalForm());
            toAdd.add(newScissors);
            anchorPane.getChildren().add(newScissors.scissorsView);
        }
    }


    @Override
    public ImageView getView() {
        return this.scissorsView;
    }
}
