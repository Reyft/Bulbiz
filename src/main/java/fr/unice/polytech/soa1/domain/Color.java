package fr.unice.polytech.soa1.domain;

public enum Color {
    WHITE("white",1), YELLOW("yeloow",2), RED("red",3), BLUE("blue",4), GREEN("green",5), PURPLE("pruple",6);

    private int price = 0;
    private String color;

    Color(String color, int price){
        this.price = price;
        this.color = color;
    }

    public int getPrice(){
        return price;
    }

    public String getColor(){ return color;}
}
