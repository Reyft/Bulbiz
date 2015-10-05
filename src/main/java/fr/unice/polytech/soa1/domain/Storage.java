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
                Order last = list.peek();
                if (!last.isBillingStatus()){
                    return last.getId();
                } else {
                    return -1;
                }
            }
        }
        return -2;
    }

    /**
     *  supprimé la commande en cours
     *  param (id commande)
     */
    public static int deleteInProgress(int userId){
        Set cles = orders.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            User u = (User)it.next();
            if (u.getId() == userId){
                Stack<Order> list = orders.get(u);
                Order last = list.peek();
                if (!last.isBillingStatus()){
                   list.pop();
                    return 0;
                } else {
                    return -1;
                }
            }
        }
        return -2;
    }

    /**
     * ajouter une commande -> storage
     * param (int userId, Order o)
     *
     */
    public static boolean addAnOrder(int userId, Order o){
        User user = null;
        for (User u : users){
            if (u.getId() == userId){
                user = u;
            }
        }
        if (user == null){
            return false;
        } else {
            Stack<Order> stack = orders.get(user);
            if (stack == null){
                stack = new Stack<Order>();
                stack.push(o);
                orders.put(user, stack);
            } else {
                stack.push(o);
            }
            return true;
        }
    }

    /**
     *  userId + Bulb + number -> mise a jour
     *
     */
    public static boolean addBulbToOrder(int userId, Bulb b, int nb){
        Set cles = orders.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            User u = (User)it.next();
            if (u.getId() == userId){
                Order order = orders.get(u).peek();
                Map<Bulb, Integer> col = new HashMap<Bulb, Integer>();
                Set cle = col.keySet();
                Iterator it2 = cle.iterator();
                while(it2.hasNext()){
                    Bulb bulb = (Bulb) it2.next();
                    if (bulb.equals(b)){
                        col.put(bulb, col.get(bulb) + nb);
                    } else {
                        col.put(bulb, nb);
                    }
                    return true;
                }
            }
            break;
        }
        return false;
    }

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
