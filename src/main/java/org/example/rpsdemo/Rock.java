package org.example.rpsdemo;

import java.util.Comparator;

public class Rock extends Entity{
    public Rock() {
        super(Math.random()*MAX_VALUE, Math.random() * MAX_VALUE, EntityType.ROCK);
    }

    public DetermineTarget rocksTarget = (self, EntityList) -> {
        return EntityList.stream()
                         .filter(e -> e.getEntityType() == EntityType.PAPER)
                         .min(Comparator.comparingDouble(e -> self.distanceTo(e)))
                         .orElse(null);
    };








}
