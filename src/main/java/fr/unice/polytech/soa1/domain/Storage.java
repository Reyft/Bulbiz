package fr.unice.polytech.soa1.domain;

import java.awt.Color;
import java.util.*;
import java.util.List;

/**
 * Created by remy on 27/09/15.
 */
public class Storage {
    // this mocks a database.
    private static List<User> users = new ArrayList();
    private static Map<Bulb, Integer> stock = new HashMap();
    private static Map<User, List<Order>> orders = new HashMap();

    public static void create(User user, Color color, Form form) {

    }

    public static void addUser(User u){
        if (!users.contains(u)) {
            users.add(u);
        }
    }

    public static void editUser(User u, String name){
        if (users.contains(u)){
            users.get(users.indexOf(u)).setName(name);
        }
    }

    public static void delUser(User u){
        users.remove(u);
    }

    public static void addInStock(Bulb b, int number){
        if (stock.containsKey(b)){
            stock.put(b, stock.get(b)+number);
        } else {
            stock.put(b, number);
        }
    }

    public static int checkQuantity(Bulb b){
        if (stock.containsKey(b)){
            return stock.get(b);
        }
        return 0;
    }

    public static void createOrder(User u){

    }

    static {
        Storage.create(new User(), Color.YELLOW, Form.OVAL);
    }
}
