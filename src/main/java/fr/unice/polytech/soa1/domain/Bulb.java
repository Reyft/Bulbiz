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
