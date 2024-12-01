package org.example.rpsdemo;

//genişletilmeye açık eğer aynı boş metotlar olursa buraya atarım

import java.util.List;

public interface EntityInterface   {

    double getxCoordinate();
    double getyCoordinate();
    EntityType getEntityType();
    double distanceTo(Entity alternates);
    Entity determineTarget(Entity self, List<Entity> entityList);
}
