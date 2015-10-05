package fr.unice.polytech.soa1.domain;

/**
 * Created by remy on 05/10/15.
 */
public enum Color {
    WHITE(1), YELLOW(2), RED(3), BLUE(4), GREEN(5), PURPLE(6);

    private int price = 0;

    Color(int price){
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
