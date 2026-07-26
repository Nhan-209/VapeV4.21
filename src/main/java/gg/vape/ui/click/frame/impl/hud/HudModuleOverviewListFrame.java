package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.HudModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.HudModuleOverviewFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleToggleComponent;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.NameComparator;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.util.ArrayList;

public class HudModuleOverviewListFrame
extends Frame {
    private HudModuleOverviewFrame wt;

    @Override
    public void v() {
    }

    @Override
    public void Y() {
    }

    public void w$src$V$1pyk8v9() {
        this.S();
        int n = 0;
        ArrayList<Mod> arrayList = new ArrayList<Mod>(Vape.INSTANCE.getModManager().collectMods());
        arrayList.sort(new NameComparator());
        for (Mod mod : arrayList) {
            HudModule hudModule;
            if (!(mod instanceof HudModule) || !(hudModule = (HudModule)mod).f$src$Z$148d2ux()) continue;
            HudModuleToggleComponent hudModuleToggleComponent = new HudModuleToggleComponent(hudModule);
            if (hudModule.j$src$Ljava_lang_Class_$wxgaiy() != null) {
                hudModuleToggleComponent.y((Frame)ClientSettings.g(hudModule.j$src$Ljava_lang_Class_$wxgaiy()));
            }
            hudModuleToggleComponent.w$src$V$1ogudh4();
            this.h(hudModuleToggleComponent, n > 0 && (n + 1) % 5 == 0 ? "wrap" : "");
            ++n;
        }
    }

    public HudModuleOverviewListFrame(HudModuleOverviewFrame hudModuleOverviewFrame) {
        this.wt = hudModuleOverviewFrame;
        this.I2 = false;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().t(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().I(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().U(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().u(false);
        this.Z(false);
        this.L(false, false);
        this.t(50.0);
        this.w$src$V$1pyk8v9();
    }

    @Override
    public String getName() {
        return "LegitMinModuleFrame";
    }

    @Override
    public void t(boolean bl, boolean bl2) {
        super.t(bl, bl2);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean A$src$Z$1p4vcx7() {
        for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
            HudModule hudModule;
            if (!(mod instanceof HudModule) || !(hudModule = (HudModule)mod).f$src$Z$148d2ux()) continue;
            return true;
        }
        return false;
    }

    @Override
    public void c() {
        super.c();
        if (!this.A$src$Z$1p4vcx7()) {
            SmoothFontRenderer smoothFontRenderer = this.O(0.7);
            GuiRenderPrimitives.F("empty", this.G$src$D$1b2f02a() + this.A() / 2.0 - 2.0, this.n() + this.L() / 2.0 - 10.0, 9.6, 9.6, HudModuleOverviewListFrame.J.A);
            smoothFontRenderer.d("No Favorites", this.G$src$D$1b2f02a() + this.A() / 2.0 - smoothFontRenderer.N("No Favorites") / 2.0, this.n() + this.L() / 2.0 + 3.0, HudModuleOverviewListFrame.J.h);
        }
    }
}

