package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Comparator;
import java.util.List;

public class Paper extends Entity {
    public ImageView paperView;
    private static final double SPEED = 2.0;

    public Paper(String paperPath) {
        super(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, EntityType.PAPER);
        this.paperView = new ImageView(new Image(paperPath));

        this.paperView.setFitWidth(40);
        this.paperView.setFitHeight(40);

        this.paperView.setX(this.xCoordinate);
        this.paperView.setY(this.yCoordinate);
    }

    @Override
    public void handleCollision(
            Entity target,
            MainController mainController,
            AnchorPane anchorPane,
            List<Entity> toAdd,
            List<Entity> toRemove) {

        if (target instanceof Rock) {
            Rock rock = (Rock) target;

            rock.rockView.setVisible(false);
            anchorPane.getChildren().remove(rock.rockView);
            toRemove.add(rock);

            Paper newPaper = new Paper(getClass().getResource("/Images/Paper.png").toExternalForm());
            toAdd.add(newPaper);
            anchorPane.getChildren().add(newPaper.paperView);
        }
    }



    public ImageView getView() {
        return this.paperView;
    }



}
