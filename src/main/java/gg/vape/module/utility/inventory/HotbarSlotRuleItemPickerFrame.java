package gg.vape.module.utility.inventory;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.HotbarSlotRule;
import gg.vape.module.utility.inventory.HotbarSlotRuleGroupComponent;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemListFrame;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerHeaderCloseClickHandler;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerHeaderComponent;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemSearchComponent;
import gg.vape.module.utility.inventory.HotbarSlotRuleSelectedSlotPreviewComponent;
import gg.vape.module.utility.inventory.HotbarSlotRuleSlotSelectorComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameStackManager;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.layout.ComponentLayout;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class HotbarSlotRuleItemPickerFrame
extends Frame {
    @Nullable
    private FrameStackManager qG;
    private HotbarSlotRuleGroupComponent qN;
    private List<HotbarSlotRule> qE;
    private HotbarSlotRuleSelectedSlotPreviewComponent qm = new HotbarSlotRuleSelectedSlotPreviewComponent(this);
    private HotbarSlotRuleSlotSelectorComponent qc;
    private HotbarSlotRuleItemSearchComponent qA = new HotbarSlotRuleItemSearchComponent(this);
    private int qx;
    private String qI = "";
    private HotbarSlotRuleItemListFrame qT = new HotbarSlotRuleItemListFrame(this);

    public int X$src$I$7rbe5s() {
        return this.qx;
    }

    public HotbarSlotRuleItemListFrame D$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$dqviyt() {
        return this.qT;
    }

    @Override
    public void Y() {
    }

    public HotbarSlotRuleItemPickerFrame() {
        this.qc = new HotbarSlotRuleSlotSelectorComponent(this);
        this.K(200.0);
        this.S(200.0);
        ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
        componentLayout.t(false);
        componentLayout.M(false);
        componentLayout.U(false);
        componentLayout.I(false);
        componentLayout.u(false);
        this.Y(new HotbarSlotRuleItemPickerHeaderComponent(this, this, null, "AutoHotbar").Q(new HotbarSlotRuleItemPickerHeaderCloseClickHandler(this)));
        this.H(this.qm);
        this.h(this.qA, new Object[0]);
        this.h(this.qc, new Object[0]);
        this.Z(true);
        this.g(true);
    }

    public void s(String string) {
        this.qI = string;
        this.qT.p();
    }

    @Override
    public void t(boolean bl, boolean bl2) {
        super.t(bl, bl2);
        this.qT.Z(bl);
    }

    @Override
    public String getName() {
        return "hotbarshell";
    }

    @Override
    public void c() {
        this.W(true);
        this.qm.K(this.G$src$D$1b2f02a());
        this.qm.S(this.n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
        this.qA.K(this.G$src$D$1b2f02a() + this.qm.A());
        this.qA.S(this.n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
        this.qT.M(this.G$src$D$1b2f02a() + this.qm.A(), this.n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() + this.qA.L());
        this.qc.K(this.G$src$D$1b2f02a() + this.qm.A());
        this.qc.S(this.qT.n() + this.qT.L());
        super.c();
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L(), this.A(), 0.5, HotbarSlotRuleItemPickerFrame.J.l);
    }

    public void O(@Nullable FrameStackManager frameStackManager) {
        this.qG = frameStackManager;
    }

    public HotbarSlotRuleGroupComponent N$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$xa58f() {
        return this.qN;
    }

    public void Z$src$V$7seznp() {
    }

    @Override
    public double L() {
        return 215.0;
    }

    public void t(int n) {
        this.qx = n;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void y(HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent) {
        this.qN = hotbarSlotRuleGroupComponent;
        this.qE = new ArrayList<HotbarSlotRule>(hotbarSlotRuleGroupComponent.u$src$Ljava_util_List_$1u5n2i3());
    }

    @Override
    public void U() {
        super.U();
        this.qT.U();
    }

    @Nullable
    public FrameStackManager E() {
        return this.qG;
    }

    public String E$src$Ljava_lang_String_$ous8w6() {
        return this.qI;
    }

    public void N$src$V$7ltgjd() {
        FrameStackManager frameStackManager = this.qG;
        if (frameStackManager != null) {
            if (frameStackManager instanceof ClickGuiFrameManager) {
                ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)frameStackManager;
                clickGuiFrameManager.m(this.qT);
                clickGuiFrameManager.G();
            } else {
                ClientSettings.fW.I(frameStackManager);
            }
            this.qG = null;
        } else {
            ClientSettings.fW.I(ClientSettings.a);
        }
    }

    @Override
    public void v() {
    }

    @Override
    public double A() {
        return 332.0;
    }
}

