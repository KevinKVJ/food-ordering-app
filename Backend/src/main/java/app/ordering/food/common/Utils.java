package app.ordering.food.common;

import java.util.ArrayList;
import java.util.HashSet;

public class Utils {
    public static ArrayList<ArrayList<String>> getDifference(ArrayList<String> a, ArrayList<String> b) {
        HashSet<String>   hashSetA      = new HashSet<>(a);
        HashSet<String>   hashSetB      = new HashSet<>(b);
        ArrayList<String> elementsAdded = new ArrayList<>();
        for (String i : hashSetB) {
            if (!hashSetA.contains(i)) {
                elementsAdded.add(i);
            }
        }
        ArrayList<String> elementsRemoved = new ArrayList<>();
        for (String i : hashSetA) {
            if (!hashSetB.contains(i)) {
                elementsRemoved.add(i);
            }
        }
        return new ArrayList<ArrayList<String>>() {{
            add(elementsAdded);
            add(elementsRemoved);
        }};
    }
}
