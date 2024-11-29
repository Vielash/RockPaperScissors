package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Paper extends Entity {
    public ImageView paperView;

    public Paper(String imagePath) {
        super(Math.random() * MAX_VALUE, Math.random() * MAX_VALUE, EntityType.PAPER);
        this.paperView = new ImageView(new Image(imagePath));

        this.paperView.setFitWidth(40);
        this.paperView.setFitHeight(40);

        this.paperView.setX(this.xCoordinate);
        this.paperView.setY(this.yCoordinate);
    }

    public DetermineTarget papersTarget = (self, allEntities) -> {
        return allEntities.stream()
                .filter(e -> e.getEntityType() == EntityType.ROCK)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e))) //araştırdığım bir yapı o yüzden kendime hatırlatma yapıyorum kenidisini alıp her e ile kıyaslıyo ve minimumunu alıyo
                .orElse(null);
    };



}
