package gg.vape.input;

import java.util.LinkedList;
import java.util.Queue;

public class MouseClickRateTracker {
    private static Queue<Long> m = new LinkedList<Long>();

    public static int m() {
        long l = System.currentTimeMillis();
        while (!m.isEmpty() && m.peek() < l) {
            m.remove();
        }
        return m.size();
    }

    public static String w(int n) {
        if (n < 0) {
            n += 100;
        }
        return "M" + (n + 1);
    }


    public static void j() {
        m.add(System.currentTimeMillis() + 1000L);
    }
}

