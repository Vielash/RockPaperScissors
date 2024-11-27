package org.example.rpsdemo;

import java.util.Comparator;

public class Scissors extends Entity{

    public Scissors() {
        super(Math.random() * MAX_VALUE, Math.random() * MAX_VALUE,EntityType.SCISSORS);

    }

    public DetermineTarget ScissorsTarget = (self, EntityList) -> {
        return EntityList.stream()
                .filter(e -> e.getEntityType() == EntityType.PAPER)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e)))
                .orElse(null);
    };


}
