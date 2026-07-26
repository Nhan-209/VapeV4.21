package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MScreen;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Holder;
import gg.vape.wrapper.impl.ResourceLocation;

public class Screen
extends Wrapper {
    private static int[] s;

    public static void S(int[] nArray) {
        s = nArray;
    }

    public static int[] m$src$AI$1181csu() {
        return s;
    }

    public Screen(Object object) {
        super(object);
    }

    static {
        if (Screen.m$src$AI$1181csu() != null) {
            Screen.S(new int[1]);
        }
    }

    public static ResourceLocation k(Holder holder) {
        return new ResourceLocation(MScreen.i(Screen.c.getMappings().RT, holder.getObject()));
    }
}

