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

    public static User getUser(int userId){
        for (User u : users){
            if (u.getId() == userId){
                return u;
            }
        }
        return null;
    }

    /**
     *
     * @param userId : identifiant (id) du client
     * @param name
     */
    public static void editUser(int userId, String name){
       for (User u : users){
           if (u.getId() == userId){
               u.setName(name);
               break;
           }
       }
    }

    public static void delUser(int userId){
        for (User u : users){
            if (u.getId() == userId){
                users.remove(u);
                break;
            }
        }
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
    public static int getStateOrderInProgress(int userId){
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
     * id user -> id commande impayé / -1 si rien
     *
     */
    public static Order getOrderInProgress(int userId){
        Set cles = orders.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()){
            User u = (User)it.next();
            if (u.getId() == userId){
                Stack<Order> list = orders.get(u);
                Order last = list.peek();
                if (!last.isBillingStatus()){
                    return last;
                } else {
                    break;
                }
            }
        }
        return null;
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

    /**
     * Get an ORDER
     * @param orderId : Order Id
     * @param clientId : Client Id
     * @return Order o
     */
    public static Order getOrder(int orderId, int clientId) {
        return null;
    }
    //TODO Complete the action

    /**
     * Return list of order for a specific client
     * @param clientId : client identifiant to select order
     * @return a list of order
     */
    public static List<Order> getListOrder(int clientId) {
        return null;
        //TODO Complete the action
    }

    /**
     * Add an address for the last order (order in final progress)
     * @param clientId : client ID for check the order
     * @param address : the address of delivery
     * @return 0 if everything is ok, -1 if the order doesn't exists, -2 if the client doesn't exists.
     */
    public static int addAddress(int clientId, String address) {
        return 0;
        //TODO Complete the action
    }

    /**
     * Return the status of the orderId
     * @param clientId
     * @param orderId
     * @return
     */
    public static String getStatus(int clientId, int orderId) {
        return null;
        //TODO Complete the action
    }
}
