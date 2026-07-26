package gg.vape.ui.click.frame.impl.main;

import gg.vape.Vape;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;
import gg.vape.ui.click.frame.impl.hud.HudOverlaySelectorFrame;
import gg.vape.ui.click.frame.impl.main.ClickGuiLayer;
import gg.vape.ui.click.frame.impl.main.ClickGuiMainFrame;
import gg.vape.wrapper.impl.Minecraft;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public class ClickGuiFrameManager
extends FrameStackManager {
    private final Set<HudModuleFrameBase> G = new HashSet<HudModuleFrameBase>();
    private static int w;
    private Frame V;
    private final Set<HudModuleFrameBase> J = new HashSet<HudModuleFrameBase>();
    private HudOverlaySelectorFrame z;
    private ClickGuiMainFrame g;

    public static void W(int n) {
        w = n;
    }

    private void a() {
        if (this.J.isEmpty()) {
            return;
        }
        for (HudModuleFrameBase hudModuleFrameBase : this.J) {
            hudModuleFrameBase.U(false);
            this.m(hudModuleFrameBase);
        }
        this.J.clear();
    }

    public static int p() {
        return w;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Frame L() {
        return this.V;
    }

    public HudOverlaySelectorFrame Y$src$Lgg_vape_ui_click_frame_impl_hud_HudOverlaySelec$z60fv4() {
        return this.z;
    }

    public void K(@Nullable Frame frame) {
        if (frame != null) {
            if (this.V != null && this.V != frame) {
                this.m(this.V);
            }
            this.V = frame;
            if (!this.Y().contains(frame)) {
                this.q(frame);
            }
            this.R(this.g, frame);
            frame.t(true, false);
            this.g.K$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiMainFr$2ph4q().H(true);
        } else {
            if (this.V != null) {
                this.m(this.V);
            }
            this.V = null;
            this.g.K$src$Lgg_vape_ui_click_frame_impl_main_ClickGuiMainFr$2ph4q().H(false);
        }
    }

    @Override
    public void A() {
        if (this.g == null) {
            this.g = new ClickGuiMainFrame();
            this.q(this.g);
            this.z = new HudOverlaySelectorFrame();
            this.q(this.z);
            this.G(ClickGuiLayer.MAIN);
        }
        if (this.z.V$src$Z$1xhop3l()) {
            this.a();
            this.z.K((double)Minecraft.J() / 4.0 / Vape.INSTANCE.getClientSettings().s() - this.z.A() / 2.0);
            this.z.S((double)Minecraft.h() / 2.0 / Vape.INSTANCE.getClientSettings().s() - this.z.L() - 5.0);
            this.m();
        } else {
            this.K();
            this.T();
        }
    }

    public ClickGuiMainFrame l() {
        return this.g;
    }

    public static int I() {
        int n = ClickGuiFrameManager.p();
        if (n == 0) {
            return 70;
        }
        return 0;
    }

    private void K() {
        if (this.G.isEmpty()) {
            return;
        }
        for (HudModuleFrameBase hudModuleFrameBase : this.G) {
            this.m(hudModuleFrameBase);
        }
        this.G.clear();
    }

    public void G(ClickGuiLayer clickGuiLayer) {
        switch (clickGuiLayer) {
            case MAIN: {
                this.g.Z(true);
                this.z.Z(false);
                HudModuleConfigFrameBase.w$src$V$1ttpy5n();
                break;
            }
            case OVERLAYS: {
                this.z.Z(true);
                this.g.Z(false);
            }
        }
    }

    private void T() {
        HashSet<HudModuleFrameBase> hashSet = new HashSet<HudModuleFrameBase>();
        for (Frame frame : ClientSettings.G()) {
            HudModuleFrameBase hudModuleFrameBase;
            if (!(frame instanceof HudModuleFrameBase) || !(hudModuleFrameBase = (HudModuleFrameBase)frame).V$src$Z$1xhop3l()) continue;
            hashSet.add(hudModuleFrameBase);
            if (this.Y().contains(hudModuleFrameBase)) continue;
            int n = this.Y().indexOf(this.g);
            if (n >= 0) {
                this.Y().add(n, hudModuleFrameBase);
                continue;
            }
            this.q(hudModuleFrameBase);
        }
        Iterator<HudModuleFrameBase> iterator = this.J.iterator();
        while (iterator.hasNext()) {
            HudModuleFrameBase frame = iterator.next();
            if (hashSet.contains(frame) && frame.V$src$Z$1xhop3l()) continue;
            this.m(frame);
            iterator.remove();
        }
        this.J.addAll(hashSet);
    }

    public void G() {
        this.K(null);
    }

    public boolean i(Frame frame) {
        return this.J.contains(frame);
    }

    private void m() {
        HashSet<HudModuleFrameBase> hashSet = new HashSet<HudModuleFrameBase>();
        for (Frame frame : ClientSettings.G()) {
            HudModuleFrameBase hudModuleFrameBase;
            if (!(frame instanceof HudModuleFrameBase) || !(hudModuleFrameBase = (HudModuleFrameBase)frame).V$src$Z$1xhop3l()) continue;
            hashSet.add(hudModuleFrameBase);
            if (this.Y().contains(hudModuleFrameBase)) continue;
            this.q(hudModuleFrameBase);
        }
        Iterator<HudModuleFrameBase> iterator = this.G.iterator();
        while (iterator.hasNext()) {
            HudModuleFrameBase frame = iterator.next();
            if (hashSet.contains(frame) && frame.V$src$Z$1xhop3l()) continue;
            this.m(frame);
            iterator.remove();
        }
        this.G.addAll(hashSet);
        this.v(this.z);
    }

    static {
        if (ClickGuiFrameManager.I() == 0) {
            ClickGuiFrameManager.W(45);
        }
    }
}
