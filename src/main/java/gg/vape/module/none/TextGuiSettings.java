package gg.vape.module.none;

import gg.vape.Vape;
import gg.vape.module.MinecraftVersionConstraint;
import gg.vape.module.Mod;
import gg.vape.module.none.ConfigSettingsModule;
import gg.vape.module.none.textgui.TextGuiModuleWidthComparator;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.NameComparator;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.ModeValue;
import gg.vape.value.ModuleNameSuggestionProvider;
import gg.vape.value.NumberValue;
import gg.vape.value.OptionalLimitValue;
import gg.vape.value.StringValue;
import java.awt.Color;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class TextGuiSettings
extends ConfigSettingsModule {
    public final BooleanValue S;
    public final ModeOption P;
    public BooleanValue p;
    public final ModeValue v;
    public final ModeValue V;
    public final ModeOption b;
    public final ModeValue s;
    public final BooleanValue C;
    public final ModeOption r;
    public final ModeOption K;
    public final NumberValue A;
    public final BooleanValue J;
    public final ModeOption D;
    public final ColorValue k;
    public final StringValue t;
    public final ModeOption F;
    public final BooleanValue j;
    public final BooleanValue c;
    public final ModeOption L;
    public static TextGuiSettings U;
    public final OptionalLimitValue O;
    public final BooleanValue o;
    public final ModeOption H = new ModeOption("Alphabetical");
    public final BooleanValue Z;
    public final ColorValue Y;
    public final BooleanValue a;
    public final BooleanValue I;

    public int v() {
        return this.V.K() == this.P ? 0 : (this.V.K() == this.K ? 1 : 2);
    }

    public String z() {
        CopyOnWriteArrayList<Mod> copyOnWriteArrayList = new CopyOnWriteArrayList<Mod>(Vape.INSTANCE.getModManager().collectMods());
        if (this.s.K() == this.H) {
            copyOnWriteArrayList.sort(new NameComparator());
        } else if (this.s.K() == this.b) {
            copyOnWriteArrayList.sort(new TextGuiModuleWidthComparator(null));
        }
        String string = "  ";
        for (Mod mod : copyOnWriteArrayList) {
            if (!mod.r$src$Z$14eylz9() || mod.h() == 0) continue;
            string = string + mod.getName() + ", ";
        }
        if (string.length() > 2) {
            string = string.substring(0, string.length() - 2);
        }
        return string;
    }

    public TextGuiSettings() {
        super("Text GUI");
        this.b = new ModeOption("Length");
        this.s = ModeValue.create((Object)this, "Sort", this.b, this.b, this.H);
        this.D = new ModeOption("Module color");
        this.r = new ModeOption("Match GUI color");
        this.F = new ModeOption("Custom color");
        this.v = ModeValue.create((Object)this, "Color Mode", this.D, this.D, this.r, this.F);
        this.k = ColorValue.L(this, "Text GUI color", new Color(206, 7, 7));
        this.J = BooleanValue.create(this, "Click disable", false, "Click modules in text gui to toggle them");
        this.j = BooleanValue.create(this, "Shadow", true, "Renders shadowed text");
        this.I = BooleanValue.create(this, "Animations", true, "Use animations on text gui");
        this.Z = BooleanValue.create(this, "Watermark", false, "Renders a vape watermark");
        this.a = BooleanValue.create(this, "Render background", true);
        this.c = BooleanValue.create(this, "Hide modules", false, "Allows you to blacklist certain modules from being shown");
        this.O = (OptionalLimitValue)OptionalLimitValue.Q(this, "module-show-blacklist", "Hidden Modules", "Name of module to hide", OptionalLimitValue.G, Arrays.asList("ESP", "NameTags", "StorageESP")).i(new ModuleNameSuggestionProvider());
        this.p = BooleanValue.create(this, "Rescale", true, "Rescales text GUI");
        this.A = NumberValue.create((Object)this, "Scale", "#.#", "", 0.1, 1.0, 2.0, 0.1);
        this.C = BooleanValue.create(this, "Add custom text", false);
        this.t = StringValue.Z(this, "Custom text", "");
        this.o = BooleanValue.create(this, "Set custom text color", false);
        this.Y = ColorValue.L(this, "Color of custom text", new Color(206, 7, 7));
        this.S = BooleanValue.create(this, "Smooth font", true);
        this.P = new ModeOption("Basic");
        this.K = new ModeOption("Extended");
        this.L = new ModeOption("None");
        this.V = ModeValue.create((Object)this, "Suffix mode", this.P, this.P, this.K, this.L);
        U = this;
        this.addValue(this.s, this.v, this.k, this.V, this.J, this.j, this.I, this.Z, this.a, this.c, this.O, this.p, this.A, this.C, this.t, this.o, this.Y);
        this.U(this.S, new MinecraftVersionConstraint[0]);
        this.t.W(true);
        this.v.L(this.k, this.F);
        this.c.K(this.O);
        this.C.K(this.t);
        this.C.K(this.o);
        this.o.K(this.Y);
    }

}

