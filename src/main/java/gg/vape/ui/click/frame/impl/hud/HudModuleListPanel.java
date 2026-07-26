package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.InsetFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrame;
import gg.vape.ui.click.frame.impl.hud.HudModuleListEntry;
import gg.vape.ui.click.frame.impl.hud.HudModuleSelectorFrame;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class HudModuleListPanel
extends InsetFrameBase {
    private HudModuleSelectorFrame Ny;
    private final ConcurrentHashMap<HudModule, HudModuleListEntry> Ns = new ConcurrentHashMap();

    @Override
    public String getName() {
        return "LegitModuleFrame";
    }

    public void N$src$V$wrn2a4() {
        this.S();
        int n = 0;
        ArrayList<Mod> arrayList = Vape.INSTANCE.getModManager().l();
        this.Z(true);
        for (Mod mod : arrayList) {
            if (!(mod instanceof HudModule)) continue;
            HudModule hudModule = (HudModule)mod;
            if (this.Ny.S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a() == HudModuleGroup.r && !hudModule.f$src$Z$148d2ux() || this.Ny.S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a() != null && this.Ny.S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a() != HudModuleGroup.J && this.Ny.S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a() != HudModuleGroup.r && this.Ny.S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a() != hudModule.F$src$Lgg_vape_module_render_hud_HudModuleGroup_$1x5d82w() || this.Ny.D$src$Ljava_lang_String_$18bm3e4() != null && this.Ny.D$src$Ljava_lang_String_$18bm3e4().length() > 0 && !hudModule.getName().toLowerCase().contains(this.Ny.D$src$Ljava_lang_String_$18bm3e4().toLowerCase())) continue;
            if (!this.Ns.containsKey(hudModule)) {
                this.Ns.put(hudModule, new HudModuleListEntry(hudModule));
            }
            HudModuleListEntry hudModuleListEntry = this.Ns.get(hudModule);
            if (hudModule.j$src$Ljava_lang_Class_$wxgaiy() != null) {
                hudModuleListEntry.U((Frame)ClientSettings.g(hudModule.j$src$Ljava_lang_Class_$wxgaiy()));
            }
            hudModuleListEntry.O$src$V$1sb8gqj();
            this.h(hudModuleListEntry, n > 0 && (n + 1) % 4 == 0 ? "wrap" : "");
            ++n;
        }
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void Y() {
    }

    @Override
    public void c() {
        super.c();
        if (this.Ny.S$src$Lgg_vape_module_render_hud_HudModuleGroup_$8wvu6a() == HudModuleGroup.r && !this.D$src$Z$wm54fy()) {
            SmoothFontRenderer smoothFontRenderer = this.A$src$Lgg_vape_ui_font_SmoothFontRenderer_$jrhwp3();
            GuiRenderPrimitives.F("empty", this.G$src$D$1b2f02a() + this.A() / 2.0 - 4.0, this.n() + this.L() / 2.0 - 15.0, 20.0, 20.0, HudModuleListPanel.J.A);
            smoothFontRenderer.d("No Favorites", this.G$src$D$1b2f02a() + this.A() / 2.0 - smoothFontRenderer.N("No Favorites") / 2.0, this.n() + this.L() / 2.0 + 5.0, HudModuleListPanel.J.h);
        }
    }

    @Override
    public void v() {
    }

    @Override
    public void J() {
        if (ClientSettings.g(HudModuleConfigFrame.class).V$src$Z$1xhop3l()) {
            return;
        }
        super.J();
    }

    public HudModuleListPanel(HudModuleSelectorFrame hudModuleSelectorFrame) {
        this.Ny = hudModuleSelectorFrame;
        this.I2 = false;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().t(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().I(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().U(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().u(false);
        this.L(false, true);
        this.Z(true);
        this.t(150.0);
        this.N$src$V$wrn2a4();
    }

    @Override
    public void U() {
        this.Ny.d$src$V$b5ssve();
    }

    @Override
    public boolean d$src$Z$1lx9d06() {
        return false;
    }

    @Override
    public void t(boolean bl, boolean bl2) {
        super.t(bl, bl2);
    }

    private boolean D$src$Z$wm54fy() {
        for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
            HudModule hudModule;
            if (!(mod instanceof HudModule) || !(hudModule = (HudModule)mod).f$src$Z$148d2ux()) continue;
            return true;
        }
        return false;
    }

    @Override
    public void D(GuiMouseEvent guiMouseEvent) {
        if (ClientSettings.g(HudModuleConfigFrame.class).V$src$Z$1xhop3l()) {
            ClientSettings.g(HudModuleConfigFrame.class).g(guiMouseEvent);
            return;
        }
        super.D(guiMouseEvent);
    }
}

