package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Comparator;
import java.util.List;

public class Rock extends Entity {
    public static final double SPEED = 2.0;
    ImageView rockView;

    public Rock(String rockPath) {
        super(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, EntityType.ROCK);
        this.rockView = new ImageView(new Image(rockPath));

        this.rockView.setFitWidth(40);
        this.rockView.setFitHeight(40);

        this.rockView.setX(this.xCoordinate);
        this.rockView.setY(this.yCoordinate);

    }
    @Override
    public void handleCollision(
            Entity target,
            MainController mainController,
            AnchorPane anchorPane,
            List<Entity> toAdd,
            List<Entity> toRemove) {

        if (target instanceof Scissors) {
            Scissors scissors = (Scissors) target;

            scissors.scissorsView.setVisible(false);
            anchorPane.getChildren().remove(scissors.scissorsView);
            toRemove.add(scissors);

            Rock newRock = new Rock(getClass().getResource("/Images/Rock.png").toExternalForm());
            toAdd.add(newRock);
            anchorPane.getChildren().add(newRock.rockView);
        }
    }

    public ImageView getView() {
        return this.rockView;
    }

}

