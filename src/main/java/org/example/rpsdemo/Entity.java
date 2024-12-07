package org.example.rpsdemo;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Comparator;
import java.util.List;

public  abstract class Entity implements EntityInterface{

    private static final double SPEED = 2.0;
    public static final int MAX_VALUE_X = 1900;
    public static final int MAX_VALUE_Y = 1070;

    protected double xCoordinate;
    protected double yCoordinate;

    protected EntityType type;

    public Entity(double xCoordinate, double yCoordinate, EntityType type) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.type = type;
    }
    public  double getxCoordinate() {
        return xCoordinate;
    }
    public  double getyCoordinate() {
        return yCoordinate;
    }

    @Override
    public EntityType getEntityType() {
        return type;
    }

    @Override
    public double distanceTo(Entity alternates) {
        double x = this.getxCoordinate() - alternates.getxCoordinate();
        double y = this.getyCoordinate() - alternates.getyCoordinate();
        return Math.pow(x,2) + Math.pow(y,2);
    }


    @Override
    public  void moveTarget(Entity target){
        if (target == null) {
            return;
        }
    double targetX = target.getxCoordinate();
    double targetY = target.getyCoordinate();

    double currentX = this.getView().getX();
    double currentY = this.getView().getY();

    double deltaX = targetX - currentX;
    double deltaY = targetY - currentY;
    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (distance > 0) {
        double birimX = SPEED * (deltaX / distance);
        double birimY = SPEED * (deltaY / distance);

        this.getView().setX(currentX + birimX);
        this.getView().setY(currentY + birimY);

        this.xCoordinate = this.getView().getX();
        this.yCoordinate = this.getView().getY();


        }
    }

    public Entity determineTarget(Entity self, List<Entity> entityList, EntityType enemyType) {
        return entityList.stream()
                .filter(e -> e.getEntityType() == enemyType)
                .filter(e -> e != self)
                .min(Comparator.comparingDouble(e -> self.distanceTo(e)))
                .orElse(null);
    }

    public abstract ImageView getView();


}
