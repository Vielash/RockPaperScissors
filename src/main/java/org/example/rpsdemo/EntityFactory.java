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

    public void createEntities() {      //(kendi tercihim paperi rocku scissorsu farklı farklı listelere kaydedip oluşturmaktı performans kaybı yaşanmasın diye ama nesneler etkileşime girdiğinde ekleyip çıkarmak zordu
        for (int i = 0; i < 15; i++) {  // ve az nesne oldugu icin tek arraylist e koydum böylelikle çıkartma değişme işlerini daha kolay halledebilirim.
            entityList.add(new Paper());
            entityList.add(new Rock());
            entityList.add(new Scissors());
        }
    }

    public List<Entity> getEntityList(){
        return entityList;
    }

    }