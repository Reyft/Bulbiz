package fr.unice.polytech.soa1.domain;

/**
 * Created by jeremy on 06/10/15.
 */
public enum Form {
    ROUND (3), TRIANGLE (5), OVAL (4);

    private int price = 0;

    Form(int price){
        this.price = price;
    }

    public int getPrice(){ return price;}
}
