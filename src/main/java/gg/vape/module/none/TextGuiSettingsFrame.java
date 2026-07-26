package gg.vape.module.none;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.TextGuiSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.ColorValueEditorComponent;
import gg.vape.ui.click.component.value.ListValueComponent;
import gg.vape.ui.click.component.value.NumberSliderComponent;
import gg.vape.ui.click.component.value.SliderComponentBase;
import gg.vape.ui.click.component.value.StringValueTextInputComponent;
import gg.vape.ui.click.frame.impl.TextGuiOverlayComponent;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionsFrame;
import gg.vape.unmap.ModeSelection;
import gg.vape.wrapper.impl.Minecraft;

public class TextGuiSettingsFrame
extends HudSettingsFrameBase {
    private boolean WD;
    private final TextGuiOverlayComponent W2;
    private StringValueTextInputComponent W1;
    private BooleanToggleComponent W7;
    private SliderComponentBase WW;
    private BooleanToggleComponent Wm;
    private BooleanToggleComponent Wn;
    private DropdownSelectComponent<ModeSelection> W8;
    private DropdownSelectComponent<ModeSelection> Wp;
    private TextGuiSettings WK = Vape.INSTANCE.getModManager().getMod(TextGuiSettings.class);
    private BooleanToggleComponent Wq;
    private double WA;
    private double Wz;
    private BooleanToggleComponent WU;
    private BooleanToggleComponent WZ;
    private DropdownSelectComponent<ModeSelection> WJ;
    private ListValueComponent Wb;
    private BooleanToggleComponent WY;
    private ColorValueEditorComponent WG;
    private ColorValueEditorComponent WL;
    private boolean WH;
    private boolean WE;
    private BooleanToggleComponent WF;
    private BooleanToggleComponent WQ;
    private double WC;

    @Override
    public double x() {
        if (this.L$src$Z$1v7qi9z()) {
            return this.WE ? this.WC : this.W2.x();
        }
        return super.x();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void H() {
        this.Pf();
        super.H();
    }

    @Override
    public void Y() {
        this.Pf();
        if (this.WW.W$src$Z$38isfa()) {
            if (!this.WE) {
                this.WE = true;
                this.WC = this.W2.x();
                this.Wz = Math.max(26.0, this.W2.C());
            }
        } else if (this.WE) {
            this.WE = false;
            this.Pe();
            this.H(true);
        }
    }

    private void Pf() {
        if (!this.L$src$Z$1v7qi9z()) {
            this.WD = this.IU;
            this.WH = false;
            this.WA = Double.NaN;
            return;
        }
        if (this.IU) {
            this.WD = true;
            return;
        }
        if (this.WD) {
            this.WD = false;
            this.Pe();
        }
        if (Double.isNaN(this.WA) && this.e()) {
            this.Pe();
        }
        if (this.WH) {
            double d;
            double d2;
            if (Double.isNaN(this.WA)) {
                this.WA = this.G$src$D$1b2f02a() + this.A();
            }
            if ((d2 = (d = Math.floor(this.WA - this.A())) - this.G$src$D$1b2f02a()) != 0.0) {
                this.T(d2, 0.0);
            }
        }
    }

    public TextGuiSettingsFrame() {
        super("newtextgui", "Text GUI");
        this.W8 = new DropdownSelectComponent(this.WK.s);
        this.Wp = new DropdownSelectComponent(this.WK.v);
        this.WG = new ColorValueEditorComponent(this.WK.k);
        this.WJ = new DropdownSelectComponent(this.WK.V);
        this.WQ = new BooleanToggleComponent(this.WK.J);
        this.Wq = new BooleanToggleComponent(this.WK.j);
        this.WU = new BooleanToggleComponent(this.WK.I);
        this.Wm = new BooleanToggleComponent(this.WK.Z);
        this.W7 = new BooleanToggleComponent(this.WK.a);
        this.WY = new BooleanToggleComponent(this.WK.c);
        this.Wb = new ListValueComponent(this.WK.O);
        this.WW = new NumberSliderComponent(this.WK.A);
        this.WF = new BooleanToggleComponent(this.WK.C);
        this.W1 = new StringValueTextInputComponent(this.WK.t);
        this.WZ = new BooleanToggleComponent(this.WK.o);
        this.WL = new ColorValueEditorComponent(this.WK.Y);
        this.Wn = new BooleanToggleComponent(this.WK.S);
        this.WA = Double.NaN;
        this.M(this.W8, this.WJ, this.Wp, this.WG, this.WW, this.WQ, this.Wq, this.WU, this.Wn, this.Wm, this.W7, this.WY, this.Wb, this.WF, this.W1, this.WZ, this.WL);
        this.W2 = new TextGuiOverlayComponent(this);
        this.h(this.W2, new Object[0]);
    }

    @Override
    public void t(JsonObject jsonObject) {
        super.t(jsonObject);
        this.Pe();
        ClientSettings.g(QuickActionsFrame.class).m$src$Lgg_vape_ui_click_frame_impl_quickactions_QuickA$1kmfigl().h(this.V$src$Z$1xhop3l());
    }

    @Override
    protected void o$src$V$7f79jo() {
    }

    @Override
    public void v() {
    }

    public TextGuiOverlayComponent K$src$Lgg_vape_ui_click_frame_impl_TextGuiOverlayCompo$1shgn4i() {
        return this.W2;
    }

    private void Pe() {
        double d;
        if (!this.L$src$Z$1v7qi9z()) {
            this.WH = false;
            this.WA = Double.NaN;
            return;
        }
        double d2 = this.G$src$D$1b2f02a() + this.A() / 2.0;
        this.WH = d2 >= (d = (double)Minecraft.J() / 4.0 / Vape.INSTANCE.getClientSettings().s());
        this.WA = this.WH ? this.G$src$D$1b2f02a() + this.A() : Double.NaN;
    }

    private boolean e() {
        double d;
        double d2 = this.G$src$D$1b2f02a() + this.A() / 2.0;
        return d2 >= (d = (double)Minecraft.J() / 4.0 / Vape.INSTANCE.getClientSettings().s());
    }

    @Override
    public String getName() {
        return "Text GUI";
    }

    @Override
    public double L() {
        if (this.L$src$Z$1v7qi9z()) {
            return this.WE ? this.Wz : Math.max(26.0, this.W2.C());
        }
        return super.L();
    }

    @Override
    public double A() {
        if (this.L$src$Z$1v7qi9z()) {
            return this.WE ? this.WC : this.W2.x();
        }
        return this.x();
    }
}

