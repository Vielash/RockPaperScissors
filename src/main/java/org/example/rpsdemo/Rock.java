package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Rock extends Entity {
    public static final double SPEED = 2.0;
    ImageView rockView;
    double[] rockTargetCoordinateX = new double[15];
    double[] rockTargetCoordinateY = new double[15];

    public Rock(String rockPath) {
        super(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, EntityType.ROCK);
        this.rockView = new ImageView(new Image(rockPath));

        this.rockView.setFitWidth(40);
        this.rockView.setFitHeight(40);

        this.rockView.setX(this.xCoordinate);
        this.rockView.setY(this.yCoordinate);

    }


    public Entity determineTargetRock(Rock self, List<Entity> entityList) {
        return entityList.stream()
                .filter(e -> e.getEntityType() == EntityType.SCISSORS)
                .filter(e -> e != self)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e))) //araştırdığım bir yapı o yüzden kendime hatırlatma yapıyorum kenidisini alıp her e ile kıyaslıyo ve minimumunu alıyo
                .orElse(null);
    }

    @Override
    public void moveTarget(Entity scissors) {
        if (scissors == null){
            return;
        }
        double targetX = scissors.getxCoordinate();
        double targetY = scissors.getyCoordinate();

        double currentX = this.rockView.getX();
        double currentY = this.rockView.getY();

        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (distance > 0) {
            double birimX = SPEED * (deltaX / distance);
            double birimY = SPEED * (deltaY / distance);

            this.rockView.setX(currentX + birimX);
            this.rockView.setY(currentY + birimY);
            System.out.println("Rock yeni pozisyon: (" + (currentX + birimX) + ", " + (currentY + birimY) + ")");
        }
    }

}

