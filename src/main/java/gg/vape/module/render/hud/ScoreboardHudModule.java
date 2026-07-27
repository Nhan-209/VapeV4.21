package gg.vape.module.render.hud;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import gg.vape.event.impl.EventScoreboardScores;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.module.render.hud.ScoreboardVisibleScorePredicate;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.hud.ScoreboardHudFrame;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.Vec3d;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.value.BooleanValue;
import gg.vape.value.StringMapValue;
import gg.vape.wrapper.impl.FontRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GlStateManager;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Score;
import gg.vape.wrapper.impl.ScoreObjective;
import gg.vape.wrapper.impl.ScorePlayerTeam;
import gg.vape.wrapper.impl.Scoreboard;
import gg.vape.wrapper.impl.TextComponent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.lwjgl.opengl.GL11;

public class ScoreboardHudModule
extends HudModule {
    TimerUtil F = new TimerUtil();
    public final BooleanValue t = BooleanValue.create(this, "Show score numbers", false);
    public final StringMapValue c = (StringMapValue)StringMapValue.R(this, "Replace scoreboard text", "Find text", "Replace with").W(true);
    ScoreObjective H = null;

    @Override
    public void onEnable() {
        EventScoreboardScores.setLocked(true);
    }

    public void Y(ScoreObjective scoreObjective) {
        this.H = scoreObjective;
        this.F.reset();
    }

    @Override
    public void onDisable() {
        EventScoreboardScores.setLocked(false);
    }

    public ScoreboardHudModule() {
        super("Scoreboard", HudModuleGroup.f, "scoreboard", ScoreboardHudFrame.class);
        this.addValue(this.t, this.c);
        this.setSuffix("Allows you to edit the Minecraft scoreboard");
    }

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private String replaceScoreText(String string, String string2, String string3) {
        String string4 = string2;
        char[] cArray = string2.toCharArray();
        String string5 = "";
        for (int i = 0; i < cArray.length; ++i) {
            char c = cArray[i];
            if (c == '\u00a7') {
                ++i;
                continue;
            }
            if (Integer.valueOf(c) > 1000) continue;
            string5 = string5 + String.valueOf(c);
        }
        string5 = string5.toLowerCase();
        string2 = string2.toLowerCase();
        if (string5.contains(string = string.toLowerCase())) {
            int n;
            char[] cArray2 = string.toCharArray();
            char[] cArray3 = string2.toCharArray();
            int n2 = 0;
            int n3 = -1;
            int n4 = -1;
            int n5 = cArray2.length;
            for (int i = 0; i < cArray3.length; ++i) {
                if (n2 > cArray2.length - 1) continue;
                n = cArray3[i];
                if (n == cArray2[n2]) {
                    if (n3 == -1) {
                        n3 = i;
                    }
                    if (++n2 != n5) continue;
                    n4 = i;
                    continue;
                }
                if (n3 == -1 || n != 167) continue;
                ++i;
            }
            if (n3 != -1 && n4 != -1 && n4 > n3) {
                String string6 = "";
                for (n = 0; n < cArray3.length; ++n) {
                    if (n < n3 || n > n4) {
                        string6 = string6 + cArray3[n];
                    }
                    if (n != n3) continue;
                    string6 = string6 + string3;
                }
                string4 = string6;
            }
        }
        return string4;
    }

    public Vec3d K(double d, double d2, boolean bl) {
        boolean bl2 = false;
        if (Minecraft.i() != null) {
            bl2 = Minecraft.V();
        }
        if (this.H == null) {
            return new Vec3d(0.0, 0.0, 0.0);
        }
        if (bl2 || this.F.hasTimeElapsed(10000L)) {
            this.H = null;
            return new Vec3d(0.0, 0.0, 0.0);
        }
        boolean bl3 = GL11.glIsEnabled((int)3042);
        if (bl3) {
            GlStateManager.disableBlend();
        }
        boolean bl4 = this.t.L();
        FontRenderer fontRenderer = Minecraft.getFontRenderer();
        Scoreboard scoreboard = this.H.P();
        EventScoreboardScores.setLocked(false);
        Collection<Score> arrayList = scoreboard.p(this.H);
        EventScoreboardScores.setLocked(true);
        ArrayList<Score> arrayList2 = Lists.newArrayList(Iterables.filter(arrayList, new ScoreboardVisibleScorePredicate(this)));
        arrayList = arrayList2.size() > 15 ? Lists.newArrayList(Iterables.skip(arrayList2, arrayList.size() - 15)) : arrayList2;
        int n = fontRenderer.getStringWidth(this.H.h());
        for (Score score : arrayList) {
            ScorePlayerTeam scorePlayerTeam = scoreboard.l(score.P());
            String string = ScorePlayerTeam.o(scorePlayerTeam, score.P()) + ":";
            if (bl4) {
                string = string + " \u00a7c" + score.j();
            }
            n = Math.max(n, fontRenderer.getStringWidth(string));
        }
        int n2 = arrayList.size() * fontRenderer.getFontHeight();
        int n3 = (int)(d2 + (double)n2) + 8;
        int n4 = 3;
        int n5 = (int)d + 1;
        int n6 = 0;
        double d3 = n;
        double d4 = 0.0;
        Map<String, String> map = this.c.K();
        for (Score score : arrayList) {
            ++n6;
            ScorePlayerTeam scorePlayerTeam = scoreboard.l(score.P());
            String string = ScorePlayerTeam.o(scorePlayerTeam, score.P());
            for (String string2 : map.keySet()) {
                String string3 = map.get(string2);
                string = this.replaceScoreText(string2, string, string3);
            }
            String string4 = "\u00a7c" + score.j();
            int n7 = n3 - n6 * fontRenderer.getFontHeight();
            if (bl) {
                float f = n5 - 2;
                float f2 = n7;
                float f3 = (float)((double)n5 + d3);
                float f4 = n7 + fontRenderer.getFontHeight();
                float f5 = f3 - f;
                float f6 = f4 - f2;
                GuiRenderPrimitives.y(f, f2, f5, f6, new Color(0x50000000, true));
            }
            d4 += (double)fontRenderer.getFontHeight();
            if (ForgeVersion.MC_1_16_5.d()) {
                fontRenderer.J(MatrixStack.A(), new TextComponent(scorePlayerTeam, score.P()), n5, n7, -1);
            } else {
                fontRenderer.drawString(string, (double)n5, (double)n7, 0x20FFFFFF);
            }
            if (bl4) {
                fontRenderer.drawString(string4, (double)n5 + d3 - (double)fontRenderer.getStringWidth(string4), (double)n7, 3648127);
            }
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            if (n6 == arrayList.size()) {
                String string5 = this.H.h();
                for (String string6 : map.keySet()) {
                    String string7 = map.get(string6);
                    string5 = this.replaceScoreText(string6, string5, string7);
                }
                if (bl) {
                    GuiRenderPrimitives.C(n5 - 2, n7 - fontRenderer.getFontHeight() - 1, d3 + 2.0, fontRenderer.getFontHeight(), new Color(0x60000000, true));
                    GuiRenderPrimitives.C(n5 - 2, n7 - 1, d3 + 2.0, 1.0, new Color(0x50000000, true));
                }
                if (ForgeVersion.MC_1_16_5.d()) {
                    fontRenderer.J(MatrixStack.A(), this.H.i(), n5 + n / 2 - fontRenderer.getStringWidth(string5) / 2, n7 - fontRenderer.getFontHeight(), -1);
                } else {
                    fontRenderer.drawString(string5, (double)(n5 + n / 2 - fontRenderer.getStringWidth(string5) / 2), (double)(n7 - fontRenderer.getFontHeight()), 0x20FFFFFF);
                }
            }
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
        if (bl3) {
            GlStateManager.enableBlend();
        }
        return new Vec3d(d3, d4 + 5.0, 0.0);
    }
}
