package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MVec3I;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;

public class Vec3i
extends Wrapper {
    private static GuiComponent[] g;

    public static Vec3i b(int n, int n2, int n3) {
        return new Vec3i(MVec3I.Y(Vec3i.c.getMappings().Dn).O(n, n2, n3));
    }

    public Vec3i(Object object) {
        super(object);
    }

    public static void n(GuiComponent[] guiComponentArray) {
        g = guiComponentArray;
    }

    public int o() {
        return Vec3i.c.getMappings().Dn.z(this.I);
    }

    public int d() {
        return Vec3i.c.getMappings().Dn.O(this.I);
    }

    public static GuiComponent[] J() {
        return g;
    }

    public int P() {
        return Vec3i.c.getMappings().Dn.u(this.I);
    }

    public int int_o() {
        return this.o();
    }

    public int int_d() {
        return this.d();
    }

    static {
        if (Vec3i.J() != null) {
            Vec3i.n(new GuiComponent[1]);
        }
    }
}

