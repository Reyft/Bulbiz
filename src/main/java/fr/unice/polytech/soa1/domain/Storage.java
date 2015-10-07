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
    public static User editUser(int userId, String name){
       for (User u : users) {
           if (u.getId() == userId) {
               u.setName(name);
               return u;
           }
       }
       return null;
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

    public static int checkQuantity(Color color, Form form){
        Bulb b = new Bulb(color, form);
        if (stock.containsKey(b)){
            return stock.get(b);
        }
        return 0;
    }

    /**
     * id user -> id commande impayé / -1 si rien
     *
     */
    public static int getIdOrderInProgress(int userId){
        for (User u : orders.keySet()){
            if (u.getId() == userId){
                Order last = orders.get(u).peek();
                if (!last.isBillingStatus()){
                    return last.getId();
                }
            }
        }
        return -1;
    }

    public static Order getOrderInProgress(int userId){
        for (User u : orders.keySet()){
            if (u.getId() == userId){
                Order last = orders.get(u).peek();
                if (!last.isBillingStatus()){
                    return last;
                }
                break;
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
                    order.processThePrice();
                    return true;
                }
            }
            break;
        }
        return false;
    }

    /**
     * Get an ORDER
     * @param orderId : Order Id
     * @param clientId : Client Id
     * @return Order o
     */
    public static Order getOrder(int orderId, int clientId) {
        for (User u : orders.keySet()){
            if (u.getId() == clientId){
                Iterator it = orders.get(u).iterator();
                while (it.hasNext()){
                    Order order = (Order) it.next();
                    if (order.getId() == orderId) {
                        return order;
                    }
                }
                break;
            }
        }
        return null;
    }


    /**
     * Return list of order for a specific client
     * @param clientId : client identifiant to select order
     * @return a list of order
     */
    public static List<Order> getListOrder(int clientId) {
        for (User u : orders.keySet()){
            if (u.getId() == clientId){
                return orders.get(u);
            }
        }
        return null;
    }

    /**
     * Add an address for the last order (order in final progress)
     * @param clientId : client ID for check the order
     * @param address : the address of delivery
     * @return 0 if everything is ok, -1 if the order doesn't exists, -2 if the client doesn't exists.
     */
    public static int addAddress(int clientId, String address) {
        for (User u : orders.keySet()){
            if (u.getId() == clientId){
                orders.get(u).peek().setAddress(address);
                return 0;
            }
        }
        return -1;
    }

    /**
     * Return the status of the orderId
     * @param clientId
     * @param orderId
     * @return
     */
    public static String getDeliveryStatus(int clientId, int orderId) {
        Order o = Storage.getOrder(orderId, clientId);
        return o.getDeliveryState();
    }

    public static String getManufacturingState(int clientId, int orderId){
        Order o = Storage.getOrder(orderId, clientId);
        return o.getManufacturingState();
    }

    public static int getNbOfUsers(){
        return users.size();
    }

    public static int getTotalStock(){
        int sum = 0;
        for (Bulb b : stock.keySet()){
            sum += stock.get(b);
        }
        return sum;
    }

    public static String getAllOrders() {
        int compt = 1;
        String ret = "{\n";
        for (User u : orders.keySet()){
            Iterator it = orders.get(u).iterator();
            while(it.hasNext()){
                Order o = (Order)it.next();
                ret += "\"order"+ compt++ +"\"" + ":" + "\"/display/order/"+u.getId()+"/"+o.getId()+"\",\n";
            }
        }
        ret = ret.substring(0,ret.length()-2);
        ret += "\n}";
        return ret;
    }
}
