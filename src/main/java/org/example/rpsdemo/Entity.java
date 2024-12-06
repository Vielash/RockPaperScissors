package org.example.rpsdemo;

// Şimdilik planladığım tüm olay kordinat girdilerine göre gerçeklşiyor şöyleki: Targetlardan en yakınını bul ve yakala

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

    public abstract void moveTarget(Entity target);


}
