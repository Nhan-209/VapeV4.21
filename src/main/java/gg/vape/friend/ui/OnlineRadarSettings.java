package gg.vape.friend.ui;

import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import java.awt.Color;

public class OnlineRadarSettings {
    public final ModeOption x;
    public final ColorValue F;
    public final ModeValue I;
    public final ModeValue n;
    public final NumberValue s;
    public final ModeOption W = new ModeOption("2D Radar");
    public final BooleanValue r;
    public final ColorValue j;
    public final ModeOption Q;
    public final NumberValue h;
    public final BooleanValue y;
    public final ModeOption f;
    public final NumberValue V;
    public final ModeValue p;
    public final ModeOption t;
    public final ModeOption a;
    public final NumberValue e;
    public final ModeValue L;
    public final NumberValue C;
    public final ColorValue v;
    public final ModeOption H = new ModeOption("Text Radar");
    public final BooleanValue U;
    public final ModeOption O;
    public final ModeOption q;

    public OnlineRadarSettings() {
        this.n = ModeValue.create((Object)this, "Mode", this.W, this.W, this.H);
        this.h = NumberValue.E(this, "Radar Size", "#.#", "", 25.0, 110.0, 500.0, "The size of the radar.");
        this.s = NumberValue.create(this, "Dot Size", "#.#", "", 0.5, 3.0, 10.0, 0.1, "The size of the radar.");
        this.V = NumberValue.create(this, "Radar Scale", "#.##", "", 0.1, 0.5, 5.0, 0.01, "The size of the radar.");
        this.e = NumberValue.create(this, "Max Distance", "#", "m", 0.0, 0.0, 100.0, 5.0, "Maximum distance to show.\nUse 0 to ignore distance requirement.");
        this.C = NumberValue.create(this, "Max Shown", "#", "", 0.0, 25.0, 50.0, 1.0, "Maximum players to show at once.\nUse 0 to show all players.");
        this.t = new ModeOption("Team");
        this.x = new ModeOption("Custom");
        this.f = new ModeOption("Relationship");
        this.I = ModeValue.create((Object)this, "ColorMode1", "Color Mode", "", (ModeSelection)this.t, this.t, this.x, this.f);
        this.q = new ModeOption("Circles");
        this.a = new ModeOption("Squares");
        this.L = ModeValue.create((Object)this, "DotStyle1", "Dot Style", "", (ModeSelection)this.q, this.q, this.a);
        this.O = new ModeOption("Square");
        this.Q = new ModeOption("Circle");
        this.p = ModeValue.create((Object)this, "Radar Style", this.O, this.O, this.Q);
        this.v = ColorValue.L(this, "Custom Color", Color.WHITE);
        this.j = ColorValue.L(this, "Friendly Color", Color.GREEN);
        this.F = ColorValue.L(this, "Enemy Color", Color.RED);
        this.U = BooleanValue.create(this, "Show Cross", true, "Render a center cross on the radar.");
        this.y = BooleanValue.create(this, "Show Background", true, "Render a background behind the radar.");
        this.r = BooleanValue.create(this, "Clamp Radar", true, "Clamps dots to the given size and shape.");
        this.n.f(this.W, this.h, this.V, this.I, this.p, this.L, this.s, this.U, this.r);
        this.n.f(this.H, this.e, this.C);
        this.I.f(this.x, this.v);
        this.I.f(this.f, this.j, this.F);
    }
}

