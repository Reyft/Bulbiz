package fr.unice.polytech.soa1.domain;

/**
 * Created by remy on 01/10/15.
 */
public class User {
    private String name;
    private int id;

    public User(){
        name = "";
        id = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}