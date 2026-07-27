package gg.vape.module;

import java.awt.Color;

public class ModDisplayInfo {
    private final String suffix;
    private final String label;
    private final String description;
    private final Color color;

    public ModDisplayInfo(String string, Color color, String string2, String string3) {
        this.label = string;
        this.color = color;
        this.description = string2;
        this.suffix = string3;
    }

    public String P() {
        return this.label;
    }

    public String z() {
        return this.description;
    }

    public Color g() {
        return this.color;
    }

    public ModDisplayInfo(String string, Color color) {
        this(string, color, null, null);
    }

    public ModDisplayInfo(String string, Color color, String string2) {
        this(string, color, null, string2);
    }

    public String u() {
        return this.suffix;
    }
}

