package fr.unice.polytech.soa1.domain;

/**
 * Created by remy on 21/09/15.
 */
public class Bulb {
    private Color color;
    private Form form;
    private int price;

    public Bulb(Color color, Form form) {
        this.color = color;
        this.form = Form.ROUND;
        price = color.getPrice() + form.getPrice();
    }

    public Bulb(String color, String form){
        String c = color.toUpperCase();
        String f = form.toUpperCase();
        Color col = null;
        Form fo = null;
        try{
            col = Color.valueOf(c);
            fo = Form.valueOf(f);
        } catch (Exception e){}
        if (col != null && fo != null){
            new Bulb(col, fo);
        }
    }

    public Bulb(){
        super();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bulb{" +
                ", color=" + color +
                ", form=" + form +
                '}';
    }
}
