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
    private boolean wasPinned;
    private final TextGuiOverlayComponent overlay;
    private StringValueTextInputComponent textInput;
    private BooleanToggleComponent toggle7;
    private SliderComponentBase expandSlider;
    private BooleanToggleComponent toggleM;
    private BooleanToggleComponent toggleN;
    private DropdownSelectComponent<ModeSelection> dropdown8;
    private DropdownSelectComponent<ModeSelection> dropdownP;
    private TextGuiSettings settings = Vape.INSTANCE.getModManager().getMod(TextGuiSettings.class);
    private BooleanToggleComponent toggleQ;
    private double targetX;
    private double savedHeight;
    private BooleanToggleComponent toggleU;
    private BooleanToggleComponent toggleZ;
    private DropdownSelectComponent<ModeSelection> dropdownJ;
    private ListValueComponent listComponent;
    private BooleanToggleComponent toggleY;
    private ColorValueEditorComponent colorEditorG;
    private ColorValueEditorComponent colorEditorL;
    private boolean rightAnchored;
    private boolean expanded;
    private BooleanToggleComponent toggleF;
    private BooleanToggleComponent toggleQ2;
    private double savedWidth;

    @Override
    public double x() {
        if (this.L$src$Z$1v7qi9z()) {
            return this.expanded ? this.savedWidth : this.overlay.x();
        }
        return super.x();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void H() {
        this.updateAnchor();
        super.H();
    }

    @Override
    public void Y() {
        this.updateAnchor();
        if (this.expandSlider.W$src$Z$38isfa()) {
            if (!this.expanded) {
                this.expanded = true;
                this.savedWidth = this.overlay.x();
                this.savedHeight = Math.max(26.0, this.overlay.C());
            }
        } else if (this.expanded) {
            this.expanded = false;
            this.recomputeAnchor();
            this.H(true);
        }
    }

    private void updateAnchor() {
        if (!this.L$src$Z$1v7qi9z()) {
            this.wasPinned = this.IU;
            this.rightAnchored = false;
            this.targetX = Double.NaN;
            return;
        }
        if (this.IU) {
            this.wasPinned = true;
            return;
        }
        if (this.wasPinned) {
            this.wasPinned = false;
            this.recomputeAnchor();
        }
        if (Double.isNaN(this.targetX) && this.isPastMidpoint()) {
            this.recomputeAnchor();
        }
        if (this.rightAnchored) {
            double anchoredX;
            double deltaX;
            if (Double.isNaN(this.targetX)) {
                this.targetX = this.G$src$D$1b2f02a() + this.A();
            }
            if ((deltaX = (anchoredX = Math.floor(this.targetX - this.A())) - this.G$src$D$1b2f02a()) != 0.0) {
                this.T(deltaX, 0.0);
            }
        }
    }

    public TextGuiSettingsFrame() {
        super("newtextgui", "Text GUI");
        this.dropdown8 = new DropdownSelectComponent(this.settings.s);
        this.dropdownP = new DropdownSelectComponent(this.settings.v);
        this.colorEditorG = new ColorValueEditorComponent(this.settings.k);
        this.dropdownJ = new DropdownSelectComponent(this.settings.V);
        this.toggleQ2 = new BooleanToggleComponent(this.settings.J);
        this.toggleQ = new BooleanToggleComponent(this.settings.j);
        this.toggleU = new BooleanToggleComponent(this.settings.I);
        this.toggleM = new BooleanToggleComponent(this.settings.Z);
        this.toggle7 = new BooleanToggleComponent(this.settings.a);
        this.toggleY = new BooleanToggleComponent(this.settings.c);
        this.listComponent = new ListValueComponent(this.settings.O);
        this.expandSlider = new NumberSliderComponent(this.settings.A);
        this.toggleF = new BooleanToggleComponent(this.settings.C);
        this.textInput = new StringValueTextInputComponent(this.settings.t);
        this.toggleZ = new BooleanToggleComponent(this.settings.o);
        this.colorEditorL = new ColorValueEditorComponent(this.settings.Y);
        this.toggleN = new BooleanToggleComponent(this.settings.S);
        this.targetX = Double.NaN;
        this.M(this.dropdown8, this.dropdownJ, this.dropdownP, this.colorEditorG, this.expandSlider, this.toggleQ2, this.toggleQ, this.toggleU, this.toggleN, this.toggleM, this.toggle7, this.toggleY, this.listComponent, this.toggleF, this.textInput, this.toggleZ, this.colorEditorL);
        this.overlay = new TextGuiOverlayComponent(this);
        this.h(this.overlay, new Object[0]);
    }

    @Override
    public void t(JsonObject jsonObject) {
        super.t(jsonObject);
        this.recomputeAnchor();
        ClientSettings.g(QuickActionsFrame.class).m$src$Lgg_vape_ui_click_frame_impl_quickactions_QuickA$1kmfigl().h(this.V$src$Z$1xhop3l());
    }

    @Override
    protected void o$src$V$7f79jo() {
    }

    @Override
    public void v() {
    }

    public TextGuiOverlayComponent K$src$Lgg_vape_ui_click_frame_impl_TextGuiOverlayCompo$1shgn4i() {
        return this.overlay;
    }

    private void recomputeAnchor() {
        double threshold;
        if (!this.L$src$Z$1v7qi9z()) {
            this.rightAnchored = false;
            this.targetX = Double.NaN;
            return;
        }
        double centerX = this.G$src$D$1b2f02a() + this.A() / 2.0;
        this.rightAnchored = centerX >= (threshold = (double)Minecraft.J() / 4.0 / Vape.INSTANCE.getClientSettings().s());
        this.targetX = this.rightAnchored ? this.G$src$D$1b2f02a() + this.A() : Double.NaN;
    }

    private boolean isPastMidpoint() {
        double threshold;
        double centerX = this.G$src$D$1b2f02a() + this.A() / 2.0;
        return centerX >= (threshold = (double)Minecraft.J() / 4.0 / Vape.INSTANCE.getClientSettings().s());
    }

    @Override
    public String getName() {
        return "Text GUI";
    }

    @Override
    public double L() {
        if (this.L$src$Z$1v7qi9z()) {
            return this.expanded ? this.savedHeight : Math.max(26.0, this.overlay.C());
        }
        return super.L();
    }

    @Override
    public double A() {
        if (this.L$src$Z$1v7qi9z()) {
            return this.expanded ? this.savedWidth : this.overlay.x();
        }
        return this.x();
    }
}

