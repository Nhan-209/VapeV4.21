package gg.vape.ui.click.frame.impl.hud;

import gg.vape.Vape;
import gg.vape.friend.ui.OnlineActivitySettingsFrame;
import gg.vape.friend.ui.OnlineCombatStatsSettingsFrame;
import gg.vape.friend.ui.OnlinePlayerPreviewSettingsFrame;
import gg.vape.friend.ui.OnlineRadarSettingsFrame;
import gg.vape.manager.ModManager;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.none.TextGuiSettingsFrame;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.impl.hud.HudOverlayEntryInteractiveComponent;
import gg.vape.ui.click.frame.impl.hud.HudOverlayEntryPanel;
import gg.vape.ui.click.frame.impl.hud.HudOverlayEntrySpec;
import gg.vape.ui.click.frame.impl.main.ClickGuiLayer;
import gg.vape.ui.click.frame.impl.target.TargetInfoSettingsFrame;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HudOverlaySelectorFrame
extends Frame {
    private static final double BZ = 6.0;
    private final List<HudOverlayEntryInteractiveComponent> BS = new ArrayList<HudOverlayEntryInteractiveComponent>();
    private static final double Bh = 6.0;
    private static final double Bd = 2.0;
    private static final double BR = 370.0;
    private static final double By = 18.0;
    private final HudOverlayEntryPanel Bc;
    private static final float Bt = 4.0f;
    private static final double B0 = 2.0;
    private static GuiComponent[] B4;
    private static final Color Bk;
    private final List<HudOverlayEntrySpec> Bi = new ArrayList<HudOverlayEntrySpec>();
    private static final int BU;

    private static double j(int n) {
        int n2 = Math.min(n, BU);
        if (n2 <= 0) {
            n2 = 1;
        }
        return 12.0 + (double)n2 * 22.0 + (double)(n2 - 1) * 2.0;
    }

    public HudOverlayEntryPanel t$src$Lgg_vape_ui_click_frame_impl_hud_HudOverlayEntry$1tdwl41() {
        return this.Bc;
    }

    public List<HudOverlayEntrySpec> S$src$Ljava_util_List_$11hop4i() {
        return Collections.unmodifiableList(this.Bi);
    }

    public static GuiComponent[] v$src$ALgg_vape_ui_click_component_GuiComponent_$14bodu2() {
        return B4;
    }

    @Override
    public void z(boolean bl) {
        double d = bl ? this.K : this.L();
        double d2 = this.G$src$D$1b2f02a();
        double d3 = this.n();
        double d4 = this.A();
        GuiRenderPrimitives.I(d2, d3, d4, d, HudOverlaySelectorFrame.J.m, true, 4.0f, 1.0f, 8.0f, HudOverlaySelectorFrame.J.u);
        GuiRenderPrimitives.g(d2, d3, d4, d, 12.0f, 4.0f, new Color(0, 0, 0, 70));
    }

    private int p() {
        if (this.BS.isEmpty()) {
            return 0;
        }
        int n = Math.min(this.BS.size(), BU);
        return Math.max(1, (int)Math.ceil((double)this.BS.size() / (double)n));
    }

    private static void lambda$getLegitModuleOverlays$2(ModManager modManager, HudModule hudModule, Class clazz) {
        Object obj = modManager.getMod(hudModule.getClass());
        if (obj != null) {
            ((Mod)obj).F();
            Object t = ClientSettings.g(clazz);
            if (t != null) {
                ((Frame)t).Z(((Mod)obj).r$src$Z$14eylz9());
            }
        }
    }

    private void s$src$V$1omanr1() {
        if (this.BS.isEmpty()) {
            return;
        }
        int n = this.BS.size();
        int n2 = Math.min(n, BU);
        int n3 = Math.max(1, (int)Math.ceil((double)n / (double)n2));
        double d = HudOverlaySelectorFrame.j(n);
        this.o(d);
        this.e(n3);
        double d2 = 22.0;
        double d3 = this.n() + 18.0;
        double d4 = this.G$src$D$1b2f02a() + 6.0;
        for (int i = 0; i < n; ++i) {
            HudOverlayEntryInteractiveComponent hudOverlayEntryInteractiveComponent = this.BS.get(i);
            int n4 = i % n2;
            int n5 = i / n2;
            double d5 = d4 + (double)n4 * (d2 + 2.0);
            double d6 = d3 + (double)n5 * (d2 + 2.0);
            hudOverlayEntryInteractiveComponent.o(d2);
            hudOverlayEntryInteractiveComponent.Y(d2);
            hudOverlayEntryInteractiveComponent.K(d5);
            hudOverlayEntryInteractiveComponent.S(d6);
        }
    }

    public void x(HudOverlayEntrySpec hudOverlayEntrySpec) {
        ArrayList<HudOverlayEntrySpec> arrayList = new ArrayList<HudOverlayEntrySpec>(this.Bi);
        arrayList.add(hudOverlayEntrySpec);
        this.q(arrayList);
    }

    private void e(int n) {
        if (n <= 0) {
            n = 1;
        }
        double d = 18.0 + (double)n * 22.0 + (double)(n - 1) * 2.0 + 6.0;
        this.Y(d);
        this.K = d;
    }

    private void w$src$V$1oohu4h() {
        this.Bc.o(this.A() - 12.0);
        this.Bc.Y(18.0);
        this.Bc.K(this.G$src$D$1b2f02a() + 6.0);
        this.Bc.S(this.n());
    }

    static {
        HudOverlaySelectorFrame.R(null);
        Bk = new Color(0, 0, 0, 72);
        BU = HudOverlaySelectorFrame.V$src$I$1o6cm8d();
    }

    private static void lambda$new$0() {
        ClientSettings.f5.G(ClickGuiLayer.MAIN);
    }

    private static List<HudOverlayEntrySpec> t$src$Ljava_util_List_$rgpmy9() {
        ArrayList<HudOverlayEntrySpec> arrayList = new ArrayList<HudOverlayEntrySpec>();
        try {
            ModManager modManager = Vape.INSTANCE.getModManager();
            for (Mod mod : new ArrayList<Mod>(modManager.l())) {
                Class clazz;
                HudModule hudModule;
                if (!(mod instanceof HudModule) || (hudModule = (HudModule)mod).F$src$Lgg_vape_module_render_hud_HudModuleGroup_$1x5d82w() != HudModuleGroup.f || (clazz = hudModule.j$src$Ljava_lang_Class_$wxgaiy()) == null) continue;
                HudOverlayEntrySpec hudOverlayEntrySpec = HudOverlayEntrySpec.O(hudModule.getName(), hudModule.s$src$Ljava_lang_String_$pdppcm(), clazz).R(() -> HudOverlaySelectorFrame.lambda$getLegitModuleOverlays$1(modManager, hudModule)).s(() -> HudOverlaySelectorFrame.lambda$getLegitModuleOverlays$2(modManager, hudModule, clazz));
                arrayList.add(hudOverlayEntrySpec);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
    }

    public HudOverlaySelectorFrame() {
        this.o(370.0);
        this.Y(46.0);
        this.T(HudOverlaySelectorFrame.J.m);
        this.d(true);
        this.D(false);
        this.Y(false);
        this.L(false, false);
        this.Bc = new HudOverlayEntryPanel(HudOverlaySelectorFrame::lambda$new$0);
        this.Bc.q("Overlays");
        this.h(this.Bc, new Object[0]);
        this.q(this.d$src$Ljava_util_List_$1sm1xmp());
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public void Y() {
        this.w$src$V$1oohu4h();
        this.s$src$V$1omanr1();
    }

    public static void R(GuiComponent[] guiComponentArray) {
        B4 = guiComponentArray;
    }

    public void q(List<HudOverlayEntrySpec> list) {
        for (HudOverlayEntryInteractiveComponent object : this.BS) {
            this.I(object);
        }
        this.BS.clear();
        this.Bi.clear();
        if (list == null || list.isEmpty()) {
            this.Bi.clear();
            this.e(0);
            return;
        }
        for (HudOverlayEntrySpec hudOverlayEntrySpec : list) {
            HudOverlayEntryInteractiveComponent hudOverlayEntryInteractiveComponent = new HudOverlayEntryInteractiveComponent(hudOverlayEntrySpec.E(), hudOverlayEntrySpec.o());
            if (hudOverlayEntrySpec.u() != null) {
                hudOverlayEntryInteractiveComponent.K(hudOverlayEntrySpec.u());
            }
            if (hudOverlayEntrySpec.q() != null) {
                hudOverlayEntryInteractiveComponent.A(hudOverlayEntrySpec.q());
            }
            if (hudOverlayEntrySpec.x() != null) {
                hudOverlayEntryInteractiveComponent.V(hudOverlayEntrySpec.x());
            }
            this.BS.add(hudOverlayEntryInteractiveComponent);
            this.h(hudOverlayEntryInteractiveComponent, new Object[0]);
        }
        this.Bi.addAll(list);
        this.o(HudOverlaySelectorFrame.j(this.BS.size()));
        this.e(this.p());
    }

    @Override
    public String getName() {
        return "Overlays";
    }

    private List<HudOverlayEntrySpec> d$src$Ljava_util_List_$1sm1xmp() {
        ArrayList<HudOverlayEntrySpec> arrayList = new ArrayList<HudOverlayEntrySpec>();
        arrayList.addAll(Arrays.asList(HudOverlayEntrySpec.O("Text GUI", "newtextgui", TextGuiSettingsFrame.class), HudOverlayEntrySpec.O("Rearview", "newrearview", OnlinePlayerPreviewSettingsFrame.class), HudOverlayEntrySpec.O("Duel Info", "newduelinfo", OnlineCombatStatsSettingsFrame.class), HudOverlayEntrySpec.O("Target Info", "newtargetinfo", TargetInfoSettingsFrame.class), HudOverlayEntrySpec.O("Radar", "newradar", OnlineRadarSettingsFrame.class), HudOverlayEntrySpec.O("Party Overlay", "party hover@2x", OnlineActivitySettingsFrame.class)));
        arrayList.addAll(HudOverlaySelectorFrame.t$src$Ljava_util_List_$rgpmy9());
        return arrayList;
    }

    private static boolean lambda$getLegitModuleOverlays$1(ModManager modManager, HudModule hudModule) {
        return ((HudModule)modManager.getMod(hudModule.getClass())).r$src$Z$14eylz9();
    }

    private static int V$src$I$1o6cm8d() {
        double d = 358.0;
        return Math.max(1, (int)Math.floor((d + 2.0) / 24.0));
    }
}

