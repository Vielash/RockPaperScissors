package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public interface EntityInterface   {

    double getxCoordinate();
    double getyCoordinate();
    EntityType getEntityType();
    double distanceTo(Entity alternates);
    ImageView getView();
    void moveTarget(Entity e);

}
