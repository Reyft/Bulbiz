package fr.unice.polytech.soa1.domain;

import java.lang.Object;

/**
 * Created by remy on 01/10/15.
 */
public class User {
    private static int compt = 1;
    private String name;
    private int id;

    public User(){
        name = "";
        id = compt++;
    }

    public User(String s){
        this();
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof User)) return false;
        User u = (User) o;
        if (u.getId() != this.getId()) return false;
        return (!u.getName().equals(this.getName()));
    }
}