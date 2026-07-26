package gg.vape.module;

import java.awt.Color;

public class ModDisplayInfo {
    private final String K;
    private final String F;
    private final String f;
    private final Color M;

    public ModDisplayInfo(String string, Color color, String string2, String string3) {
        this.F = string;
        this.M = color;
        this.f = string2;
        this.K = string3;
    }

    public String P() {
        return this.F;
    }

    public String z() {
        return this.f;
    }

    public Color g() {
        return this.M;
    }

    public ModDisplayInfo(String string, Color color) {
        this(string, color, null, null);
    }

    public ModDisplayInfo(String string, Color color, String string2) {
        this(string, color, null, string2);
    }

    public String u() {
        return this.K;
    }
}

