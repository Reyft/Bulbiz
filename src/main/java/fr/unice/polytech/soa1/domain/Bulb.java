package fr.unice.polytech.soa1.domain;

import java.awt.*;

/**
 * Created by remy on 21/09/15.
 */
public class Bulb {
    private String name;
    private Color color;
    private Form form;
    private int nb = 0;

    public Bulb(String name, Color color, Form form) {
        this.name = name;
        this.color = color;
        this.form = Form.ROUND;
    }

    public Bulb(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String run(){
        nb++;
        return name+" "+nb;
    }

    @Override
    public String toString() {
        return "Bulb{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", form=" + form +
                '}';
    }
}
