package gg.vape.friend.ui;

import com.google.gson.JsonObject;
import gg.vape.friend.ui.OnlineRadarPreviewComponent;
import gg.vape.friend.ui.OnlineRadarSettings;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.ColorValueEditorComponent;
import gg.vape.ui.click.component.value.NumberSliderComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.quickactions.QuickActionsFrame;
import gg.vape.unmap.ModeOption;

public class OnlineRadarSettingsFrame
extends HudSettingsFrameBase {
    private final ColorValueEditorComponent kN;
    private final ColorValueEditorComponent kE;
    private final DropdownSelectComponent<ModeOption> kg;
    private final DropdownSelectComponent<ModeOption> kp;
    private final NumberSliderComponent kG;
    private final ColorValueEditorComponent kS;
    private final DropdownSelectComponent<ModeOption> kJ;
    private final NumberSliderComponent kY;
    private final DropdownSelectComponent<ModeOption> kh;
    private final OnlineRadarSettings kv = new OnlineRadarSettings();
    private final BooleanToggleComponent kQ;
    private boolean km;
    private final NumberSliderComponent k_;
    private final NumberSliderComponent kC;
    private final NumberSliderComponent kF;
    private double kj;
    private final BooleanToggleComponent kw;
    private final BooleanToggleComponent k4;

    public OnlineRadarSettings H$src$Lgg_vape_friend_ui_OnlineRadarSettings_$q24dh8() {
        return this.kv;
    }

    @Override
    public void v() {
    }

    public OnlineRadarSettingsFrame() {
        super("newradar", "Radar");
        this.kh = new DropdownSelectComponent(this.kv.n);
        this.kp = new DropdownSelectComponent(this.kv.I);
        this.kN = new ColorValueEditorComponent(this.kv.v);
        this.kE = new ColorValueEditorComponent(this.kv.j);
        this.kS = new ColorValueEditorComponent(this.kv.F);
        this.kg = new DropdownSelectComponent(this.kv.L);
        this.kJ = new DropdownSelectComponent(this.kv.p);
        this.kY = new NumberSliderComponent(this.kv.s);
        this.kC = new NumberSliderComponent(this.kv.h);
        this.k_ = new NumberSliderComponent(this.kv.V);
        this.kw = new BooleanToggleComponent(this.kv.y);
        this.kF = new NumberSliderComponent(this.kv.e);
        this.kG = new NumberSliderComponent(this.kv.C);
        this.k4 = new BooleanToggleComponent(this.kv.U);
        this.kQ = new BooleanToggleComponent(this.kv.r);
        this.kj = (Double)this.kv.h.K();
        this.M(this.kh, this.kp, this.kN, this.kE, this.kS, this.kg, this.kJ, this.kY, this.kC, this.k_, this.kw, this.kF, this.kG, this.k4, this.kQ);
        this.h(new OnlineRadarPreviewComponent(this), new Object[0]);
    }

    @Override
    public double A() {
        if (this.L$src$Z$1v7qi9z() && this.m$src$Z$1ty1uhu()) {
            return this.kC.W$src$Z$38isfa() ? this.kj : (Double)this.kv.h.K();
        }
        return super.A();
    }

    @Override
    public String getName() {
        return "Radar";
    }

    private boolean m$src$Z$1ty1uhu() {
        return this.kv.n.K() == this.kv.W;
    }

    @Override
    public double L() {
        if (this.L$src$Z$1v7qi9z() && this.m$src$Z$1ty1uhu()) {
            double d = this.kC.W$src$Z$38isfa() ? this.kj : (Double)this.kv.h.K();
            return d + 2.0;
        }
        if (this.L$src$Z$1v7qi9z() && !this.m$src$Z$1ty1uhu()) {
            boolean bl;
            boolean bl2 = bl = !ClientSettings.fW.P && HudModuleConfigFrameBase.h$src$Z$1tlh1co();
            if (bl) {
                return Math.max(26, 32);
            }
        }
        return super.L();
    }


    @Override
    public void t(JsonObject jsonObject) {
        super.t(jsonObject);
        ClientSettings.g(QuickActionsFrame.class).E$src$Lgg_vape_ui_click_frame_impl_quickactions_QuickA$1snij4t().h(this.V$src$Z$1xhop3l());
    }

    @Override
    public void Y() {
        if (this.L$src$Z$1v7qi9z() && this.m$src$Z$1ty1uhu()) {
            if (this.kC.W$src$Z$38isfa()) {
                if (!this.km) {
                    this.km = true;
                }
            } else {
                if (this.km) {
                    this.km = false;
                    this.H(true);
                }
                this.kj = (Double)this.kv.h.K();
            }
            return;
        }
        this.km = false;
        this.kj = (Double)this.kv.h.K();
    }

    @Override
    protected void o$src$V$7f79jo() {
    }
}

