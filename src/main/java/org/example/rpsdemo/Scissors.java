package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;

public class Scissors extends Entity{
    ImageView scissorsView;

    public Scissors(String scissorsPath) {
        super(Math.random() * MAX_VALUE, Math.random() * MAX_VALUE,EntityType.SCISSORS);
        this.scissorsView = new ImageView(new Image(scissorsPath));

    }

    public DetermineTarget ScissorsTarget = (self, EntityList) -> {
        return EntityList.stream()
                .filter(e -> e.getEntityType() == EntityType.PAPER)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e)))
                .orElse(null);
    };


}
