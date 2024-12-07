package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Scissors extends Entity{
    public static final double SPEED = 2.0;

    ImageView scissorsView;

    public Scissors(double xCoordinate, double yCoordinate, String scissorsPath,EntityType type) {
        super(xCoordinate, yCoordinate, EntityType.SCISSORS);
        this.scissorsView = new ImageView(new Image(scissorsPath));

        this.scissorsView.setFitWidth(20);
        this.scissorsView.setFitHeight(20);

        this.scissorsView.setX(this.xCoordinate);
        this.scissorsView.setY(this.yCoordinate);

    }

    public Scissors(String scissorsPath) {
        this(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, scissorsPath, EntityType.SCISSORS);
        this.scissorsView = new ImageView(new Image(scissorsPath));

        this.scissorsView.setFitWidth(40);
        this.scissorsView.setFitHeight(40);

        this.scissorsView.setX(this.xCoordinate);
        this.scissorsView.setY(this.yCoordinate);
    }

    public Entity determineTargetScissors(Scissors self, List<Entity> entityList) {
        return entityList.stream()
                .filter(e -> e.getEntityType() == EntityType.PAPER)
                .filter(e -> e != self)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e))) //araştırdığım bir yapı o yüzden kendime hatırlatma yapıyorum kenidisini alıp her e ile kıyaslıyo ve minimumunu alıyo
                .orElse(null);
    }

    @Override
    public void moveTarget(Entity paper) {
        if (paper == null){
            return;
        }
        double targetX = paper.getxCoordinate();
        double targetY = paper.getyCoordinate();

        double currentX = this.scissorsView.getX();
        double currentY = this.scissorsView.getY();


        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (distance > 0) {
            double birimX = SPEED * (deltaX / distance);
            double birimY = SPEED * (deltaY / distance);

            this.scissorsView.setX(currentX + birimX);
            this.scissorsView.setY(currentY + birimY);

            this.xCoordinate = this.scissorsView.getX();
            this.yCoordinate = this.scissorsView.getY();

       }

    }

    @Override
    public ImageView getView() {
        return this.scissorsView;
    }
}
