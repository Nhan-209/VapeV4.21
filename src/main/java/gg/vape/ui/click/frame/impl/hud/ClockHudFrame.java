package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.module.render.hud.ClockHudModule;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class ClockHudFrame
extends HudModuleConfigFrameBase {
    private ClockHudModule XJ = (ClockHudModule)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0();

    private void gY() {
        float f = (float)(this.G$src$D$1b2f02a() + this.A());
        float f2 = (float)(this.n() + this.L());
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(2.8, true);
        SmoothFontRenderer smoothFontRenderer2 = Vape.INSTANCE.getFontManager().K(0.85, true);
        this.e();
        float f3 = (float)(this.G$src$D$1b2f02a() + this.A() / 2.0);
        float f4 = (float)(this.n() + this.L() / 2.0);
        float f5 = (float)(Math.cos((double)((float)(Integer.parseInt(this.b$src$Ljava_lang_String_$182i6d7()) * 30) + Float.parseFloat(this.Q$src$Ljava_lang_String_$1cti9m4()) / 2.0f) * Math.PI / 180.0 - 1.5707963267948966) * 26.0 + (double)f3);
        float f6 = (float)(Math.sin((double)((float)(Integer.parseInt(this.b$src$Ljava_lang_String_$182i6d7()) * 30) + Float.parseFloat(this.Q$src$Ljava_lang_String_$1cti9m4()) / 2.0f) * Math.PI / 180.0 - 1.5707963267948966) * 26.0 + (double)f4);
        GuiRenderPrimitives.u((double)f3, (double)f4, (double)f5, (double)f6, 1.8f, this.l(ClockHudFrame.J.O));
        smoothFontRenderer.d(this.b$src$Ljava_lang_String_$182i6d7(), this.G$src$D$1b2f02a() - smoothFontRenderer.N(this.b$src$Ljava_lang_String_$182i6d7()) + 28.0, this.n() + 5.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        smoothFontRenderer.d(this.Q$src$Ljava_lang_String_$1cti9m4(), (double)f - smoothFontRenderer.N(this.Q$src$Ljava_lang_String_$1cti9m4()) - 5.0, (double)f2 - smoothFontRenderer.d(this.Q$src$Ljava_lang_String_$1cti9m4()) - 8.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        smoothFontRenderer2.d(this.U$src$Ljava_lang_String_$1k23wdk(), this.G$src$D$1b2f02a() + 10.0, (double)f2 - smoothFontRenderer2.d(this.U$src$Ljava_lang_String_$1k23wdk()) - 16.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        smoothFontRenderer2.d(this.z$src$Ljava_lang_String_$1vy69nr(false), this.G$src$D$1b2f02a() + 10.0, (double)f2 - smoothFontRenderer2.d(this.z$src$Ljava_lang_String_$1vy69nr(false)) - 8.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        if (!this.XJ.o.L().booleanValue()) {
            smoothFontRenderer2.d(this.r$src$Ljava_lang_String_$1zsnfv(), (double)f - smoothFontRenderer2.N(this.r$src$Ljava_lang_String_$1zsnfv()) - 5.0, this.n() + 5.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        }
    }

    @Override
    public double A() {
        if (this.P$src$Z$10488sl()) {
            double d = 70.0;
            if (this.XJ.k.L().booleanValue()) {
                d += 24.0;
            }
            if (!this.XJ.o.L().booleanValue()) {
                d += 12.0;
            }
            return d;
        }
        return 70.0;
    }

    private String Q$src$Ljava_lang_String_$1cti9m4() {
        int n = LocalTime.now().getMinute();
        String string = String.valueOf(n);
        if (n < 10) {
            string = "0" + string;
        }
        return string;
    }

    public ClockHudFrame() {
        super(ClockHudModule.class);
    }

    @Override
    public void o() {
        if (this.P$src$Z$10488sl()) {
            this.gc();
            return;
        }
        this.gY();
    }

    private boolean P$src$Z$10488sl() {
        if (this.XJ == null) {
            return false;
        }
        return ((ModeSelection)this.XJ.L.K()).getName().equalsIgnoreCase("digital");
    }

    private String r$src$Ljava_lang_String_$1zsnfv() {
        int n = LocalTime.now().getHour();
        if (n >= 12) {
            return "pm";
        }
        return "am";
    }

    private String U$src$Ljava_lang_String_$1k23wdk() {
        return LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ROOT).toLowerCase();
    }

    @Override
    public double L() {
        if (this.P$src$Z$10488sl()) {
            return 32.0;
        }
        return 65.0;
    }

    private String b$src$Ljava_lang_String_$182i6d7() {
        int n = LocalTime.now().getHour();
        if (!this.XJ.o.L().booleanValue()) {
            if (n > 12) {
                n -= 12;
            } else if (n == 0) {
                n = 12;
            }
        }
        String string = String.valueOf(n);
        if (n < 10) {
            string = "0" + string;
        }
        return string;
    }

    private void gc() {
        SmoothFontRenderer smoothFontRenderer = Vape.INSTANCE.getFontManager().K(3.0, true);
        SmoothFontRenderer smoothFontRenderer2 = Vape.INSTANCE.getFontManager().K(1.0, true);
        smoothFontRenderer.d(this.b$src$Ljava_lang_String_$182i6d7(), this.G$src$D$1b2f02a() - smoothFontRenderer.N(this.b$src$Ljava_lang_String_$182i6d7()) + 30.0, this.n() + 2.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        GuiRenderPrimitives.V(this.G$src$D$1b2f02a() + 34.0, this.n() + 15.0, 2.0, 0.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        smoothFontRenderer.d(this.Q$src$Ljava_lang_String_$1cti9m4(), this.G$src$D$1b2f02a() + 39.0, this.n() + 2.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        double d = this.G$src$D$1b2f02a() + 39.0;
        if (!this.XJ.o.L().booleanValue()) {
            smoothFontRenderer2.d(this.r$src$Ljava_lang_String_$1zsnfv(), d += smoothFontRenderer.N(this.Q$src$Ljava_lang_String_$1cti9m4()), this.n() + 18.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        }
        if (this.XJ.k.L().booleanValue()) {
            d = this.G$src$D$1b2f02a() + this.A() - smoothFontRenderer2.N(this.z$src$Ljava_lang_String_$1vy69nr(true)) - 6.0;
            String string = this.z$src$Ljava_lang_String_$1vy69nr(true);
            double d2 = smoothFontRenderer2.N(string);
            String string2 = this.U$src$Ljava_lang_String_$1k23wdk().toLowerCase();
            double d3 = smoothFontRenderer2.N(string2);
            double d4 = d + d2 - d3;
            smoothFontRenderer2.d(string, d, this.n() + 7.0, this.m$src$Ljava_awt_Color_$ppsp8z());
            smoothFontRenderer2.d(string2, d4, this.n() + 15.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        }
    }


    private void e() {
        float f = (float)(this.G$src$D$1b2f02a() + this.A() / 2.0);
        float f2 = (float)(this.n() + this.L() / 2.0);
        for (int i = 0; i < 24; ++i) {
            if (i == 8 || i == 9 || i == 10 || i == 14 || i == 15 || i == 16 || i == 20 || i == 21 || i == 22) continue;
            float f3 = (float)(Math.cos((double)(i * 15) * Math.PI / 180.0 - 1.5707963267948966) * 25.0 + (double)f);
            float f4 = (float)(Math.sin((double)(i * 15) * Math.PI / 180.0 - 1.5707963267948966) * 25.0 + (double)f2);
            GuiRenderPrimitives.V(f3 - 1.0f, f4, 0.5, 1.0, this.m$src$Ljava_awt_Color_$ppsp8z());
        }
    }

    private String z$src$Ljava_lang_String_$1vy69nr(boolean bl) {
        String string = ZonedDateTime.now().getZone().getId();
        String string2 = string != null && string.contains("America") ? "MM / dd" : "dd / MM";
        if (!bl) {
            string2 = string2.replace(" ", "");
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(string2));
    }

    @Override
    public String getName() {
        return "ClockFrame";
    }
}

