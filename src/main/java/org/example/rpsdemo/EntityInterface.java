package org.example.rpsdemo;

//genişletilmeye açık eğer aynı boş metotlar olursa buraya atarım

public interface EntityInterface   {

    double getxCoordinate();
    double getyCoordinate();
    EntityType getEntityType();
    double distanceTo(Entity alternates);
}
