package fr.unice.polytech.soa1.domain;

/**
 * Created by jeremy on 06/10/15.
 */
public enum Form {
    ROUND ("round",3), TRIANGLE ("triangle",5), OVAL ("oval",4);

    private int price = 0;
    private String form;

    Form(String form, int price){
        this.form = form;
        this.price = price;
    }

    public int getPrice(){ return price;}

    public String getForm(){ return form;}
}
