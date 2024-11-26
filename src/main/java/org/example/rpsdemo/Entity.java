package org.example.rpsdemo;

// Şimdilik planladığım tüm olay kordinat girdilerine göre gerçeklşiyor şöyleki: Targetlardan en yakınını bul ve yakala

public  class Entity implements EntityInterface{
    public static final int MAX_VALUE = 1000;

    protected double xCoordinate;
    protected double yCoordinate;


    public Entity(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;

    }
    public  double getxCoordinate() {
        return xCoordinate;
    }
    public  double getyCoordinate() {
        return yCoordinate;
    }
    
    


}
