package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Scissors extends Entity{
    public static final double SPEED = 2.0;

    ImageView scissorsView;

    public Scissors(double xCoordinate, double yCoordinate, String scissorsPath,EntityType type) {
        super(xCoordinate, yCoordinate, EntityType.SCISSORS);
        this.scissorsView = new ImageView(new Image(scissorsPath));

        this.scissorsView.setFitWidth(20);
        this.scissorsView.setFitHeight(20);

        this.scissorsView.setX(this.xCoordinate);
        this.scissorsView.setY(this.yCoordinate);

    }

    public Scissors(String scissorsPath) {
        this(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, scissorsPath, EntityType.SCISSORS);
        this.scissorsView = new ImageView(new Image(scissorsPath));

        this.scissorsView.setFitWidth(40);
        this.scissorsView.setFitHeight(40);

        this.scissorsView.setX(this.xCoordinate);
        this.scissorsView.setY(this.yCoordinate);
    }




    @Override
    public ImageView getView() {
        return this.scissorsView;
    }
}
