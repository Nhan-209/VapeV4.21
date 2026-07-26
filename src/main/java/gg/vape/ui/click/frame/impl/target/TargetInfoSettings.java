package gg.vape.ui.click.frame.impl.target;

import gg.vape.value.BooleanValue;

public class TargetInfoSettings {
    public final BooleanValue A;
    public final BooleanValue Q;
    public final BooleanValue l = BooleanValue.create(this, "Show Hovered", true, "Show information on a hovered entity if not attacking.");
    public final BooleanValue I;
    public final BooleanValue P;

    public TargetInfoSettings() {
        this.Q = BooleanValue.create(this, "Damage Comparator", true, "Measures strength of target compared to yourself\nConsiders armor and weapon damage");
        this.P = BooleanValue.create(this, "Combo Counter", true, "Shows how many hits in a direct row you've landed, or taken, from target");
        this.I = BooleanValue.create(this, "Hits Comparator", false, "Measures how many hits you've landed compared to target");
        this.A = BooleanValue.create(this, "Pots Used Comparator", false, "Measures how many heal pots you've used compared to target");
    }
}

