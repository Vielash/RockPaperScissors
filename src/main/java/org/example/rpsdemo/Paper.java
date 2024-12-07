package org.example.rpsdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public class Paper extends Entity {
    public ImageView paperView;
    private static final double SPEED = 2.0;

    public Paper(double xCoordinate, double yCoordinate, String paperPath,EntityType type) {
        super(xCoordinate, yCoordinate, EntityType.PAPER);
        this.paperView = new ImageView(new Image(paperPath));

        this.paperView.setFitWidth(45);
        this.paperView.setFitHeight(45);

        this.paperView.setX(this.xCoordinate);
        this.paperView.setY(this.yCoordinate);
    }

    public Paper(String paperPath) {
        this(Math.random() * MAX_VALUE_X, Math.random() * MAX_VALUE_Y, paperPath, EntityType.PAPER);
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
                .min(Comparator.comparingDouble(e -> self.distanceTo(e)))
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

            this.xCoordinate = this.paperView.getX();
            this.yCoordinate = this.paperView.getY();


        }

    }

    public ImageView getView() {
        return this.paperView;
    }



}
