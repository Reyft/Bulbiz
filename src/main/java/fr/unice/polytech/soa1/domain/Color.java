package fr.unice.polytech.soa1.domain;

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
