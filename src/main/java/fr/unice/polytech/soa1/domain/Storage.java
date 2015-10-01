package fr.unice.polytech.soa1.domain;

import java.awt.Color;
import java.util.*;
import java.util.List;

/**
 * Created by remy on 27/09/15.
 */
public class Storage {
    // this mocks a database.
    private static Map<User, List<Bulb>> contents = new HashMap();

    public static void create(User user, Color color, Form form) {
        Bulb b = new Bulb(color, form);
        if (contents.get(user) == null){
            contents.put(user, new ArrayList<Bulb>());
            contents.get(user).add(b);
        } else {
            contents.get(user).add(b);
        }
    }

    public static List<Bulb> read(User user) {
        return contents.get(user);
    }

    public static void delete(User user) {
        contents.remove(user);
    }

    public static Collection<List<Bulb>> findAll() {
        return contents.values();
    }

    static {
        Storage.create(new User(), Color.YELLOW, Form.OVAL);
    }
}
