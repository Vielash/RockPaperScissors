package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;

public class Rock extends Entity{
    ImageView rockView;
    public Rock(String rockPath) {
        super(Math.random()*MAX_VALUE, Math.random() * MAX_VALUE, EntityType.ROCK);
        this.rockView = new ImageView(new Image(rockPath));
    }

    public DetermineTarget rocksTarget = (self, EntityList) -> {
        return EntityList.stream()
                         .filter(e -> e.getEntityType() == EntityType.PAPER)
                         .min(Comparator.comparingDouble(e -> self.distanceTo(e)))
                         .orElse(null);
    };








}
