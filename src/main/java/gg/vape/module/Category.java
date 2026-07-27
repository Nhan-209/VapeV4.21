package gg.vape.module;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.INamed;
import java.util.ArrayList;
import java.util.List;

public class Category
implements INamed {
    public static Category g;
    public static Category k;
    public static Category b;
    private final String name;
    public static Category m;
    private static List<Category> values;
    private final int color;
    private final String description;
    public static Category M;
    public static Category A;
    private static GuiComponent[] components;
    public static Category Y;
    private final String iconKey;
    public static Category w;
    public static Category L;
    public static Category v;

    public static GuiComponent[] N$src$ALgg_vape_ui_click_component_GuiComponent_$lect5v() {
        return components;
    }

    public int getColor() {
        return this.color;
    }

    private Category(String string, String string2, String string3, int n) {
        this.name = string;
        this.iconKey = string2;
        this.description = string3;
        this.color = n;
    }

    static {
        if (Category.N$src$ALgg_vape_ui_click_component_GuiComponent_$lect5v() == null) {
            Category.q(new GuiComponent[4]);
        }
        String[] stringArray = new String[]{"other", "Network", "utility", "combat", "Other", "network", "Combat", "newfavorites", "None", "newfavorites", "inventory", "Combat advantage modules", "Inventory", "Hidden", "All kinds of visual goodies", "World", "Favorites", "Utility", "render", "favorites", "Render", "world"};
        long[] lArray = new long[]{963278542204507525L, -6863823725787672923L, 5523565669093088006L, -1209571427471355580L, -3432970327529852768L, 7953830955999004703L, -2569924983285672571L, -6593822668313179888L, -8696848907968820976L};
        b = new Category(stringArray[8], stringArray[9], (int)lArray[5]);
        g = new Category(stringArray[6], stringArray[3], stringArray[11], (int)lArray[2]);
        Y = new Category(stringArray[17], stringArray[2], (int)lArray[3]);
        k = new Category(stringArray[20], stringArray[18], stringArray[14], (int)lArray[1]);
        m = new Category(stringArray[15], stringArray[21], (int)lArray[8]);
        M = new Category(stringArray[12], stringArray[10], (int)lArray[7]);
        v = new Category(stringArray[1], stringArray[5], (int)lArray[4]);
        w = new Category(stringArray[4], stringArray[0], (int)lArray[0]);
        L = new Category(stringArray[16], stringArray[7], "", 0);
        A = new Category(stringArray[13], stringArray[19], (int)lArray[6]);
        values = new ArrayList<Category>();
        values.add(L);
        values.add(g);
        values.add(k);
        values.add(Y);
        values.add(m);
        values.add(M);
        values.add(w);
        values.add(b);
    }

    private Category(String string, String string2, int n) {
        this(string, string2, "", n);
    }

    public String toString() {
        return this.name;
    }

    public static void q(GuiComponent[] guiComponentArray) {
        components = guiComponentArray;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static List<Category> values() {
        return values;
    }

    public static void destruct() {
        values.clear();
        values = null;
        b = null;
        g = null;
        Y = null;
        k = null;
        w = null;
        m = null;
        L = null;
    }

    public String O() {
        return this.description;
    }

    public String N() {
        return this.iconKey;
    }
}

