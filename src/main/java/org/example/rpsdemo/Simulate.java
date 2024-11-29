package org.example.rpsdemo;

import java.util.Comparator;
import java.util.List;
//fixlencek şeyler
// herşeyin adı entity oldu bunu düzelt
//lambda expression ifadelerini her RPS için altında olucak şekilde düzeltmeye çalış simülasyonun içinde olmasın ((yapıldı))
//herkes kendi en yakın nesnesinin kordinatlarını liste içine alsın ki veriler depolansın

public class Simulate {
    public static void main(String[] args) {
        EntityFactory factory = new EntityFactory();
        factory.createEntities();
        List<Entity> EntityList = factory.getEntityList();


//        for(Entity entity : EntityList) {
//            if (entity.getEntityType() == EntityType.PAPER) {
//                Paper paper = (Paper) entity;                                                                 //dursun burda
//                Entity closestHasım = paper.papersTarget.determine(entity,EntityList);
//                System.out.println("Kağıt " + entity.getxCoordinate() + "," + entity.getyCoordinate() +
//                        " en yakın taşı buldu: " + closestHasım.getxCoordinate() + "," + closestHasım.getyCoordinate());
//            }
//        }




        }


    }


