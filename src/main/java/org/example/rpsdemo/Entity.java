package org.example.rpsdemo;

// Şimdilik planladığım tüm olay kordinat girdilerine göre gerçeklşiyor şöyleki: Targetlardan en yakınını bul ve yakala

public  class Entity implements EntityInterface{

    public static final int MAX_VALUE = 1000;

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
}
