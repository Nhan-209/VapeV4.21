package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.PotionEntry;
import java.util.LinkedHashMap;
import java.util.Map;

public class PotionEntryBuilder {
    private Short T;
    private final Map<ForgeVersion, Integer> h = new LinkedHashMap<ForgeVersion, Integer>();
    private ForgeVersion C;
    private String r;

    public PotionEntry H() {
        return new PotionEntry(this);
    }

    public static Map X(PotionEntryBuilder cw_12) {
        return cw_12.h;
    }

    public PotionEntryBuilder s(short s) {
        this.T = s;
        return this;
    }

    public static ForgeVersion l(PotionEntryBuilder cw_12) {
        return cw_12.C;
    }

    public PotionEntryBuilder w(String string) {
        this.r = string;
        return this;
    }

    public PotionEntryBuilder X(ForgeVersion y7) {
        this.C = y7;
        return this;
    }

    public PotionEntryBuilder J(ForgeVersion y7, int n) {
        this.h.put(y7, n);
        return this;
    }

    public static String y(PotionEntryBuilder cw_12) {
        return cw_12.r;
    }

    public static Short g(PotionEntryBuilder cw_12) {
        return cw_12.T;
    }
}

