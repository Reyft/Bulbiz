package fr.unice.polytech.soa1.domain;

public class Payement {

    public static boolean paye(String userId, String orderId, String numero, String date, String sc) {
        Order o = Storage.getOrderInProgress(Integer.parseInt(userId));
        if (o.getPrice() == 0) return false;
        try {
            Integer.parseInt(numero);
            Integer.parseInt(sc);
            date.split("/");
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
