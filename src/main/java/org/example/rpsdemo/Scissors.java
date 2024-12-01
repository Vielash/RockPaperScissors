package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Scissors extends Entity{

    ImageView scissorsView;

    public Scissors(String scissorsPath) {
        super(Math.random() * MAX_VALUE, Math.random() * MAX_VALUE, EntityType.SCISSORS);
        this.scissorsView = new ImageView(new Image(scissorsPath));

        this.scissorsView.setFitWidth(40);
        this.scissorsView.setFitHeight(40);

        this.scissorsView.setX(this.xCoordinate);
        this.scissorsView.setY(this.yCoordinate);
    }

    @Override
    public Entity determineTarget(Entity self, List<Entity> entityList) {
        return entityList.stream()
                .filter(e -> e.getEntityType() == EntityType.PAPER)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e))) //araştırdığım bir yapı o yüzden kendime hatırlatma yapıyorum kenidisini alıp her e ile kıyaslıyo ve minimumunu alıyo
                .orElse(null);
    };


}
