package org.example.rpsdemo;

/*burda bir functional olarak determineTarget interfacesini oluşturuyorum çünkü eğer bir taş kullanıcak olursa bu metodu makası bulması icin başka
bir implementation lazım. Ana hedef:  kordinatları girilen entitynin , tüm kordinatları hashset içine alınmış kordinatları arasında en kısa mesafelesi olanı
bulup onu chase yaratılacak olan chaseTarget methodu içinde kullanmaktır.

 */
@FunctionalInterface
public interface DetermineTarget {

    public double[] determine(Entity type, double xCoordinate, double yCoordinate);

}
