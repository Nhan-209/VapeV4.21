package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.PotionEffectsHudModule;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.font.FontFamily;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ColorUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.PotionEffectIconRenderer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.I18n;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Potion;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.StatusEffect;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PotionEffectsHudFrame
extends HudModuleConfigFrameBase {
    private static final String tb = "PotionStatusFrame";
    int Ww;
    private PotionEffectsHudModule Wo = (PotionEffectsHudModule)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0();
    private static Map<Integer, Integer> Wa = new HashMap<Integer, Integer>();

    public PotionEffectsHudFrame() {
        super(PotionEffectsHudModule.class);
    }

    private void g(float f, float f2, PotionEffect potionEffect) {
        float f3 = this.X(potionEffect.k(), (float)Wa.get(potionEffect.C()).intValue());
        Color color = PotionEffectsHudFrame.J.B;
        if (f3 > 25.0f && f3 <= 50.0f) {
            color = PotionEffectsHudFrame.J.I;
        } else if (f3 <= 25.0f) {
            color = PotionEffectsHudFrame.J.d;
        }
        float f4 = 360.0f * (f3 / 100.0f);
        GuiRenderPrimitives.m(f - 0.5f, f2 - 0.5f, 21.25f, 1.8f, 1.0f, this.l(new Color(0, 0, 0, 200)));
        if (f4 == 360.0f) {
            GuiRenderPrimitives.m(f - 1.0f, f2 - 1.0f, 22.0f, 2.5f, 1.0f, this.l(color));
        } else {
            GuiRenderPrimitives.p(f - 1.0f, f2 - 1.0f, 22.0f, 2.0f, 0.5f, 270.0f, -f4, this.l(color));
        }
    }

    @Override
    public double A() {
        return 40 + this.Ww;
    }

    private String j(int n) {
        int n2 = n / 20;
        int n3 = n2 / 60;
        String string = String.valueOf(n3);
        String string2 = String.valueOf(n2 -= n3 * 60);
        if (n3 < 10) {
            string = "0" + string;
        }
        if (n2 < 10) {
            string2 = "0" + string2;
        }
        return string + ":" + string2;
    }

    @Override
    public void o() {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        ArrayList<PotionEffect> arrayList = this.T$src$Ljava_util_ArrayList_$1jq8y6s();
        if (arrayList.isEmpty()) {
            if (!Wa.isEmpty()) {
                Wa.clear();
            }
            return;
        }
        double d = this.n();
        double d2 = 30.0;
        this.Ww = 0;
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(0.85, true);
        if (I18n.w().N()) {
            smoothFontRenderer = Vape.INSTANCE.getFontManager().b(FontFamily.NOTO, 0.85f, false);
        }
        for (PotionEffect potionEffect : arrayList) {
            int n;
            Object object;
            boolean bl = false;
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            if (!this.o(potionEffect) && !this.Wo.Y.L().booleanValue()) {
                linkedList.add(this.I(potionEffect));
                bl = true;
            }
            if (this.o(potionEffect) && !this.Wo.v.L().booleanValue()) {
                linkedList.add(this.I(potionEffect));
                bl = true;
            }
            if (!Wa.containsKey(potionEffect.C()) || Wa.get(potionEffect.C()) < potionEffect.k()) {
                Wa.put(potionEffect.C(), potionEffect.k());
            }
            ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
            for (PotionEffect potionEffect2 : arrayList) {
                object = new PotionEffect(potionEffect2);
                arrayList2.add(((PotionEffect)object).C());
            }
            for (Integer n2 : Wa.keySet()) {
                if (arrayList2.contains(n2)) continue;
                linkedList.add(n2);
            }
            for (Integer n3 : linkedList) {
                Wa.remove(n3);
            }
            if (bl) continue;
            float f = this.X(potionEffect.k(), (float)Wa.get(potionEffect.C()).intValue());
            Color color = PotionEffectsHudFrame.J.A;
            if (f > 50.0f && f <= 100.0f) {
                color = PotionEffectsHudFrame.J.B;
            } else if (f > 25.0f && f <= 50.0f) {
                color = PotionEffectsHudFrame.J.I;
            } else if (f <= 25.0f) {
                color = PotionEffectsHudFrame.J.d;
            }
            this.g((float)(this.G$src$D$1b2f02a() + 6.0), (float)(d + 6.0), potionEffect);
            object = ColorUtil.W(Color.WHITE, 51);
            GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 30.0, d + 10.0, 11.5, 2.0f, (Color)object);
            String string = ForgeVersion.MC_1_16_5.d() ? potionEffect.i().d() : I18n.f(Potion.getPotionById(potionEffect.C()).y$src$Ljava_lang_String_$yl6pfj(), new Object[0]);
            String string2 = this.j(potionEffect.k());
            int n4 = (int)smoothFontRenderer.N(string);
            if (n4 > this.Ww) {
                this.Ww = n4;
            }
            if ((n = (int)smoothFontRenderer.N(string2)) > this.Ww) {
                this.Ww = n;
            }
            smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + 35.0, d + 9.0, this.m$src$Ljava_awt_Color_$ppsp8z());
            smoothFontRenderer.T(string2, this.G$src$D$1b2f02a() + 35.0, d + 17.0, this.l(color), this.l(new Color(50, 50, 50, 150)));
            PotionEffectIconRenderer.V(potionEffect, (int)(this.G$src$D$1b2f02a() + 9.0), (int)(d + 10.0), 14, 14, this.r$src$F$35g3yx());
            d += d2;
        }
    }

    private int I(PotionEffect potionEffect) {
        if (ForgeVersion.MC_1_16_5.d()) {
            return StatusEffect.v(potionEffect.i());
        }
        return Potion.getPotionById(potionEffect.C()).getId();
    }

    private ArrayList<PotionEffect> T$src$Ljava_util_ArrayList_$1jq8y6s() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        Collection collection = entityPlayerSP.B$src$Ljava_util_Collection_$1uxz2f9();
        ArrayList<PotionEffect> arrayList = new ArrayList<PotionEffect>();
        for (Object e : collection) {
            PotionEffect potionEffect = new PotionEffect(e);
            arrayList.add(potionEffect);
        }
        if (arrayList.isEmpty() && !ClientSettings.fW.P) {
            arrayList.add(PotionEffect.o(1, 6500, 0));
            arrayList.add(PotionEffect.o(2, 5000, 0));
            arrayList.add(PotionEffect.o(12, 1000, 0));
            if (!(Wa.containsKey(1) && Wa.containsKey(2) && Wa.containsKey(12))) {
                Wa.put(1, 10000);
                Wa.put(2, 10000);
                Wa.put(12, 10000);
            }
        }
        return arrayList;
    }


    @Override
    public double L() {
        int n = Wa.size();
        if (n == 0) {
            return ClientSettings.fW.P ? 0.0 : 20.0;
        }
        return 2 + n * 30;
    }

    @Override
    public String getName() {
        return tb;
    }

    private float X(float f, float f2) {
        return f / f2 * 100.0f;
    }

    private boolean o(PotionEffect potionEffect) {
        if (ForgeVersion.MC_1_16_5.d()) {
            return !potionEffect.i().p();
        }
        return Potion.getPotionById(potionEffect.C()).n();
    }
}
