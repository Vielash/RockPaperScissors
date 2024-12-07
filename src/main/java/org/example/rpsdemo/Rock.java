package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Rock extends Entity {
    public static final double SPEED = 2.0;
    ImageView rockView;


    public Rock(double xCoordinate, double yCoordinate, String rockPath,EntityType type) {
        super(xCoordinate,yCoordinate,EntityType.ROCK);
        this.rockView = new ImageView(new Image(rockPath));

        this.rockView.setFitWidth(40);
        this.rockView.setFitHeight(40);

        this.rockView.setX(this.xCoordinate);
        this.rockView.setY(this.yCoordinate);
    }

    public Rock(String rockPath) {
        this(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, rockPath, EntityType.ROCK);
        this.rockView = new ImageView(new Image(rockPath));

        this.rockView.setFitWidth(40);
        this.rockView.setFitHeight(40);

        this.rockView.setX(this.xCoordinate);
        this.rockView.setY(this.yCoordinate);

    }


    public ImageView getView() {
        return this.rockView;
    }

}

