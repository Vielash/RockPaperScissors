package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Paper extends Entity {
    public ImageView paperView;
    private static final double SPEED = 2.0;

    public Paper(double xCoordinate, double yCoordinate, String paperPath,EntityType type) {
        super(xCoordinate, yCoordinate, EntityType.PAPER);
        this.paperView = new ImageView(new Image(paperPath));

        this.paperView.setFitWidth(45);
        this.paperView.setFitHeight(45);

        this.paperView.setX(this.xCoordinate);
        this.paperView.setY(this.yCoordinate);
    }

    public Paper(String paperPath) {
        this(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, paperPath, EntityType.PAPER);
        this.paperView = new ImageView(new Image(paperPath));

        this.paperView.setFitWidth(40);
        this.paperView.setFitHeight(40);

        this.paperView.setX(this.xCoordinate);
        this.paperView.setY(this.yCoordinate);
    }





    public ImageView getView() {
        return this.paperView;
    }



}
