package fr.unice.polytech.soa1.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by remy on 01/10/15.
 */
public class Order {
    private static int compt = 1;

    private int id;
    private Map<Bulb, Integer> list = new HashMap();
    private double price;
    private String address;
    private boolean billingStatus;
    private String manufacturingState;
    private String deliveryState;

    public Order(){
        id = compt++;
        price = 0;
        billingStatus = false;
    }

    public Order(HashMap<Bulb, Integer> list, String address){
        this();
        this.list = list;
        this.address = address;
        Set cles = list.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            Bulb cle = (Bulb) it.next();
            int coef = list.get(cle);
            price += coef * cle.getPrice();
        }
    }

    public static int getCompt() {
        return compt;
    }

    public static void setCompt(int compt) {
        Order.compt = compt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Bulb, Integer> getList() {
        return list;
    }

    public void setList(Map<Bulb, Integer> list) {
        this.list = list;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public boolean isBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(boolean billingStatus) {
        this.billingStatus = billingStatus;
    }

    public String getManufacturingState() {
        return manufacturingState;
    }

    public void setManufacturingState(String manufacturingState) {
        this.manufacturingState = manufacturingState;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    /**
     * ajouter UN bulb avec une quantité
     */

}