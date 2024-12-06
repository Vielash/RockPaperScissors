package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Paper extends Entity {
    public ImageView paperView;
    private static final double SPEED = 2.0;
    double[] paperTargetCoordinateX = new double[15];
    double[] paperTargetCoordinateY = new double[15];

    public Paper(String paperPath) {
        super(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, EntityType.PAPER);
        this.paperView = new ImageView(new Image(paperPath));

        this.paperView.setFitWidth(40);
        this.paperView.setFitHeight(40);

        this.paperView.setX(this.xCoordinate);
        this.paperView.setY(this.yCoordinate);
    }

    public Entity determineTargetPaper(Paper self, List<Entity> entityList) {
        return entityList.stream()
                .filter(e -> e.getEntityType() == EntityType.ROCK)
                .filter(e -> e != self)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e))) //araştırdığım bir yapı o yüzden kendime hatırlatma yapıyorum kenidisini alıp her e ile kıyaslıyo ve minimumunu alıyo
                .orElse(null);
    }

    @Override
    public void moveTarget(Entity rock) {
        if (rock == null){
            return;
        }
        double targetX = rock.getxCoordinate();
        double targetY = rock.getyCoordinate();

        double currentX = this.paperView.getX();
        double currentY = this.paperView.getY();


        double deltaX = targetX - currentX;
        double deltaY = targetY - currentY;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (distance > 0) {
            double birimX = SPEED * (deltaX / distance);
            double birimY = SPEED * (deltaY / distance);

            this.paperView.setX(currentX + birimX);
            this.paperView.setY(currentY + birimY);
        }
    }



}
