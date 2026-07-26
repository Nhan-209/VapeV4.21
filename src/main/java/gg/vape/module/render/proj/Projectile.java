package gg.vape.module.render.proj;

import gg.vape.module.render.proj.IProjectile;
import gg.vape.wrapper.impl.EntityEnderPearl;
import java.awt.Color;
import java.util.Set;

public class Projectile
implements IProjectile {
    private final Color S;
    private static String[] p;
    private final Set<Class> V;

    public static void W(String[] stringArray) {
        p = stringArray;
    }

    public static String[] O() {
        return p;
    }

    public Projectile(Set<Class> set) {
        this(set, new Color(255, 255, 255));
    }

    public Projectile(Set<Class> set, Color color) {
        this.V = set;
        this.S = color;
    }

    @Override
    public boolean matches(EntityEnderPearl entityEnderPearl) {
        for (Class clazz : this.V) {
            if (!entityEnderPearl.isInstance(clazz)) continue;
            return true;
        }
        return false;
    }

    @Override
    public Color getColor(Object object) {
        return this.S;
    }

    static {
        if (Projectile.O() != null) {
            Projectile.W(new String[2]);
        }
    }
}

