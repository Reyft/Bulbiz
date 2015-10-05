package fr.unice.polytech.soa1.domain;

import java.awt.*;

/**
 * Created by remy on 21/09/15.
 */
public class Bulb {
    private Color color;
    private Form form;
    private int nb = 0;
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

    @Override
    public String toString() {
        return "Bulb{" +
                ", color=" + color +
                ", form=" + form +
                '}';
    }
}
