package fr.unice.polytech.soa1.domain;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by remy on 27/09/15.
 */
public class Storage {
    // this mocks a database.
    private static HashMap<String, Bulb> contents = new HashMap<String, Bulb>();

    public static void create(String name, Color color) {
        contents.put(name, new Bulb(name, color));
    }

    public static Bulb read(String name) {
        return contents.get(name);
    }

    public static void delete(String name) {
        contents.remove(name);
    }

    public static Collection<Bulb> findAll() {
        return contents.values();
    }

    static {
        Storage.create("demobulb", Color.YELLOW);
    }
}
