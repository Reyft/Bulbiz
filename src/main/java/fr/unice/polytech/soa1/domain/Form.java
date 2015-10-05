package fr.unice.polytech.soa1.domain;

public enum Form {
    ROUND ("3"),
    TRIANGLE ("5"),
    OVAL ("4");

    private String price = "";

    Form(String price){
        this.price = price;
    }

    public String getPrice(){
        return price;
    }
}
