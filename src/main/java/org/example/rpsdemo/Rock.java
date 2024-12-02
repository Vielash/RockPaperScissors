package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Rock extends Entity{
    ImageView rockView;
    public double xCoordinateRock;
    public Rock(String rockPath) {
        super(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, EntityType.ROCK);
        this.rockView = new ImageView(new Image(rockPath));

        this.rockView.setFitWidth(40);
        this.rockView.setFitHeight(40);

        this.rockView.setX(this.xCoordinate);
        this.rockView.setY(this.yCoordinate);

    }

    @Override
    public Entity determineTarget(Entity self, List<Entity> entityList) {
        return entityList.stream()
                .filter(e -> e.getEntityType() == EntityType.PAPER)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e))) //araştırdığım bir yapı o yüzden kendime hatırlatma yapıyorum kenidisini alıp her e ile kıyaslıyo ve minimumunu alıyo
                .orElse(null);
    }
}
