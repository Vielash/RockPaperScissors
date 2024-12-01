package org.example.rpsdemo;

/*Factory ile Entityleri yaratıcağım ve yarattığım entitylerin kordinatlarını belirleyip bir List içerisine alacagim çünkü targeti determine
etmek için kordinat bilgilerine ihtiyacım var */

import java.util.ArrayList;
import java.util.List;

public class EntityFactory {
    private List<Entity> entityList;

    public EntityFactory() {
        entityList = new ArrayList<>();
    }
    String paperImagePath = getClass().getResource("/Images/Paper.png").toExternalForm();
    String scissorsImagePath = getClass().getResource("/Images/Scissors.png").toExternalForm();
    String rockImagePath = getClass().getResource("/Images/Rock.png").toExternalForm();

    public void createEntities() {      //(kendi tercihim paperi rocku scissorsu farklı farklı listelere kaydedip oluşturmaktı performans kaybı yaşanmasın diye ama nesneler etkileşime girdiğinde ekleyip çıkarmak zordu
        for (int i = 0; i < 15; i++) {  // ve az nesne oldugu icin tek arraylist e koydum böylelikle çıkartma değişme işlerini daha kolay halledebilirim.

            entityList.add(new Paper(paperImagePath));
            entityList.add(new Rock(rockImagePath));
            entityList.add(new Scissors(scissorsImagePath));
        }
    }

    public List<Entity> getEntityList(){
        return entityList;
    }

    }