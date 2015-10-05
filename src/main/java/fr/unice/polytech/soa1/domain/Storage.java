package fr.unice.polytech.soa1.domain;

import java.util.*;
import java.util.List;

/**
 * Created by remy on 27/09/15.
 */
public class Storage {
    // this mocks a database.
    private static List<User> users = new ArrayList();
    private static Map<Bulb, Integer> stock = new HashMap();
    private static Map<User, Stack<Order>> orders = new HashMap();

/*    public static void create(User user, Color color, Form form) {

    }
*/

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

    public static int checkQuantity(String color, String form){
        Bulb b = toBulb(color, form);
        if (stock.containsKey(b)){
            return stock.get(b);
        }
        return 0;
    }

    /**
     * id user -> id commande impayé / -1 si rien
     *
     */
    public static int getOrderInProgress(int userId){
        Set cles = orders.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            User u = (User)it.next();
            if (u.getId() == userId){
                Stack<Order> list = orders.get(u);
                Order last = list.pop();
                if (!last.isBillingStatus()){
                    return last.getId();
                }
                break;
            }
        }
        return -1;
    }

    /**
     *  supprimé la commande en cours
     *  param (id commande)
     */

    private static Bulb toBulb(String color, String form){
        String c = color.toUpperCase();
        String f = form.toUpperCase();
        Color col = null;
        Form fo = null;
        try{
            col = Color.valueOf(c);
            fo = Form.valueOf(f);
        } catch (Exception e){return null;}
        if (col != null && fo != null){
            return new Bulb(col, fo);
        } else {
            return null;
        }
    }
}
