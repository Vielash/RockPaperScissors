package org.example.rpsdemo;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Comparator;
import java.util.List;

public  abstract class Entity implements EntityInterface{

    private static final double SPEED = 2.0;
    public static final int MAX_VALUE_X = 1900;  // full screenin yatay pixel boyutu
    public static final int MAX_VALUE_Y = 1070;   // dikey pixel sayısı

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
    public double distanceTo(Entity alternates) {       //2 nesnenin arasındaki mesafeyi ölçüyor
        double x = this.getxCoordinate() - alternates.getxCoordinate();
        double y = this.getyCoordinate() - alternates.getyCoordinate();
        return Math.pow(x,2) + Math.pow(y,2);
    }


    @Override
    public  void moveTarget(Entity target){ //polymorfik olsun diye burda oluşturdum çağırılan nesnenin hedefine doğru ilerlemesine sağlıyor hem png sinin hem kendisinin
        if (target == null) {               // kordinatlarını değiştirerek
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
        double birimX = SPEED * (deltaX / distance);    //birim vektörü ile çarpan çarpılıyor
        double birimY = SPEED * (deltaY / distance);

        this.getView().setX(currentX + birimX);     //png lerin yeri değiştiriliyor
        this.getView().setY(currentY + birimY);

        this.xCoordinate = this.getView().getX();   //arkatarafta nesnenin kordinat bilgilerini değiştiriyo bunu yapmasam çalışmıyo çünkü
        this.yCoordinate = this.getView().getY();

        }
    }

    public Entity determineTarget(Entity self, List<Entity> entityList, EntityType enemyType) { //girdiği listenin kendisine göre en yakın hedefini buluyo
        return entityList.stream()
                .filter(e -> e.getEntityType() == enemyType) //düşmanın tipini filtreliyo
                .filter(e -> e != self)         //kendisi olmamasına dikkat ediyo bug çıktı böyle yapmayınca kendi türlerine falan gitti uzun hikaye
                .min(Comparator.comparingDouble(e -> self.distanceTo(e))) //liste içinde kendisinin ve düşman tipindeki nesneler ile hepsinin arasındaki farkın min olanı seçiyo
                .orElse(null);
    }

    public abstract ImageView getView();

    public abstract void handleCollision(   //burda mesela bir nesne düşman tipine temas ettiginde düsmanı siliyor onun yerine random biryerde değen nesne tipinde bir png ve
            Entity target,                  //nesnesini oluşturuyor  ve bunları entityeliste exception almamak için 2 farklı listeyi ekleme çıkarma yaparak manipüle ediyor.
            MainController mainController,
            AnchorPane anchorPane,
            List<Entity> toAdd,
            List<Entity> toRemove
    );


}
