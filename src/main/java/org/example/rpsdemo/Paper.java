package org.example.rpsdemo;

import java.util.Comparator;
import java.util.List;

public class Paper extends Entity {

    public Paper() {
        super(Math.random() * MAX_VALUE, Math.random() * MAX_VALUE, EntityType.PAPER);
    }

    public DetermineTarget papersTarget = (self, allEntities) -> {
        return allEntities.stream()
                .filter(e -> e.getEntityType() == EntityType.ROCK)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e))) //araştırdığım bir yapı o yüzden kendime hatırlatma yapıyorum kenidisini alıp her e ile kıyaslıyo ve minimumunu alıyo
                .orElse(null);
    };



}
