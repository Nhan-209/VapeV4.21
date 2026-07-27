package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.ActiveModuleStackEntry;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ScaledResolution;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ActiveModuleStackFrame
extends Frame {
    private final LinkedHashSet<Mod> Ko = new LinkedHashSet();

    @Override
    public double C() {
        return 0.0;
    }

    @Override
    public String getName() {
        return "CenterScreenManager";
    }

    @Override
    public double x() {
        return 0.0;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public boolean y$src$Z$1f55jvh() {
        return true;
    }

    @Override
    public void u() {
        boolean bl;
        boolean bl2 = bl = (ClientSettings.fW.v() || ClientSettings.fW.l$src$Z$1gzcm82()) && !this.Ko.isEmpty();
        if (bl != this.V$src$Z$1xhop3l()) {
            this.Z(bl);
        }
    }

    public ActiveModuleStackFrame() {
        this.d(false);
        this.c(true);
    }

    public void c(Mod mod) {
        this.Ko.add(mod);
    }

    @Override
    public void I() {
        this.h();
    }

    @Override
    public void v() {
    }

    @Override
    public void H() {
        this.h();
    }


    @Override
    public void Y() {
    }

    public void w(Mod mod) {
        this.Ko.remove(mod);
    }

    public void h() {
        double d;
        double d2;
        ScaledResolution scaledResolution = Minecraft.G();
        SmoothFontRenderer smoothFontRenderer = null;
        FontRenderer fontRenderer = null;
        if (ForgeVersion.MC_26_1.d()) {
            smoothFontRenderer = Vape.INSTANCE.getFontManager().p(1.0);
        } else {
            fontRenderer = Minecraft.getFontRenderer();
        }
        if (ForgeVersion.MC_26_1.d() || ForgeVersion.MC_1_21_4.v()) {
            d2 = (float)Minecraft.J() / 4.0f;
            d = Minecraft.h() / 4;
            d2 /= Vape.INSTANCE.getClientSettings().s();
            d /= Vape.INSTANCE.getClientSettings().s();
            d += 10.0;
        } else {
            d2 = (float)scaledResolution.T() / 2.0f;
            d = (double)(scaledResolution.G() / 2) + 10.0;
        }
        ArrayList<ActiveModuleStackEntry> arrayList = new ArrayList<ActiveModuleStackEntry>();
        for (Mod object : this.Ko) {
            ModDisplayInfo modDisplayInfo = object.J();
            if (modDisplayInfo == null) continue;
            arrayList.add(new ActiveModuleStackEntry(object, modDisplayInfo));
        }
        boolean bl = arrayList.size() > 1;
        for (ActiveModuleStackEntry activeModuleStackEntry : arrayList) {
            String string = activeModuleStackEntry.E.P();
            String string2 = activeModuleStackEntry.E.z() != null ? activeModuleStackEntry.E.z() : string;
            double d3 = smoothFontRenderer != null ? smoothFontRenderer.N(string2) : (double)fontRenderer.getStringWidth(string2);
            double d4 = d2 - (double)MathUtil.ceil(d3 / 2.0);
            if (bl) {
                String string3 = activeModuleStackEntry.E.u();
                if (string3 == null) {
                    string3 = " \u00a77(" + activeModuleStackEntry.G.getName() + ")";
                }
                string = string + string3;
            }
            if (smoothFontRenderer != null) {
                smoothFontRenderer.v(string, d4 + 1.0, d, activeModuleStackEntry.E.g());
                d += smoothFontRenderer.d(string) + 4.0;
                continue;
            }
            fontRenderer.drawStringWithShadow(string, d4 + 1.0, d, activeModuleStackEntry.E.g());
            d += (double)(fontRenderer.FONT_HEIGHT(string) + 4);
        }
    }
}

