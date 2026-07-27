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
    private FrameStackManager parentStackManager;
    private HotbarSlotRuleGroupComponent groupComponent;
    private List<HotbarSlotRule> rules;
    private HotbarSlotRuleSelectedSlotPreviewComponent previewComponent = new HotbarSlotRuleSelectedSlotPreviewComponent(this);
    private HotbarSlotRuleSlotSelectorComponent slotSelectorComponent;
    private HotbarSlotRuleItemSearchComponent searchComponent = new HotbarSlotRuleItemSearchComponent(this);
    private int selectedSlot;
    private String searchText = "";
    private HotbarSlotRuleItemListFrame itemListFrame = new HotbarSlotRuleItemListFrame(this);

    public int X$src$I$7rbe5s() {
        return this.selectedSlot;
    }

    public HotbarSlotRuleItemListFrame D$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$dqviyt() {
        return this.itemListFrame;
    }

    @Override
    public void Y() {
    }

    public HotbarSlotRuleItemPickerFrame() {
        this.slotSelectorComponent = new HotbarSlotRuleSlotSelectorComponent(this);
        this.K(200.0);
        this.S(200.0);
        ComponentLayout componentLayout = this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij();
        componentLayout.t(false);
        componentLayout.M(false);
        componentLayout.U(false);
        componentLayout.I(false);
        componentLayout.u(false);
        this.Y(new HotbarSlotRuleItemPickerHeaderComponent(this, this, null, "AutoHotbar").Q(new HotbarSlotRuleItemPickerHeaderCloseClickHandler(this)));
        this.H(this.previewComponent);
        this.h(this.searchComponent, new Object[0]);
        this.h(this.slotSelectorComponent, new Object[0]);
        this.Z(true);
        this.g(true);
    }

    public void s(String string) {
        this.searchText = string;
        this.itemListFrame.p();
    }

    @Override
    public void t(boolean bl, boolean bl2) {
        super.t(bl, bl2);
        this.itemListFrame.Z(bl);
    }

    @Override
    public String getName() {
        return "hotbarshell";
    }

    @Override
    public void c() {
        this.W(true);
        this.previewComponent.K(this.G$src$D$1b2f02a());
        this.previewComponent.S(this.n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
        this.searchComponent.K(this.G$src$D$1b2f02a() + this.previewComponent.A());
        this.searchComponent.S(this.n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L());
        this.itemListFrame.M(this.G$src$D$1b2f02a() + this.previewComponent.A(), this.n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() + this.searchComponent.L());
        this.slotSelectorComponent.K(this.G$src$D$1b2f02a() + this.previewComponent.A());
        this.slotSelectorComponent.S(this.itemListFrame.n() + this.itemListFrame.L());
        super.c();
        GuiRenderPrimitives.C(this.G$src$D$1b2f02a(), this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().n() + this.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L(), this.A(), 0.5, HotbarSlotRuleItemPickerFrame.J.l);
    }

    public void O(@Nullable FrameStackManager frameStackManager) {
        this.parentStackManager = frameStackManager;
    }

    public HotbarSlotRuleGroupComponent N$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$xa58f() {
        return this.groupComponent;
    }

    public void Z$src$V$7seznp() {
    }

    @Override
    public double L() {
        return 215.0;
    }

    public void t(int n) {
        this.selectedSlot = n;
    }


    public void y(HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent) {
        this.groupComponent = hotbarSlotRuleGroupComponent;
        this.rules = new ArrayList<HotbarSlotRule>(hotbarSlotRuleGroupComponent.u$src$Ljava_util_List_$1u5n2i3());
    }

    @Override
    public void U() {
        super.U();
        this.itemListFrame.U();
    }

    @Nullable
    public FrameStackManager E() {
        return this.parentStackManager;
    }

    public String E$src$Ljava_lang_String_$ous8w6() {
        return this.searchText;
    }

    public void N$src$V$7ltgjd() {
        FrameStackManager frameStackManager = this.parentStackManager;
        if (frameStackManager != null) {
            if (frameStackManager instanceof ClickGuiFrameManager) {
                ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)frameStackManager;
                clickGuiFrameManager.m(this.itemListFrame);
                clickGuiFrameManager.G();
            } else {
                ClientSettings.fW.I(frameStackManager);
            }
            this.parentStackManager = null;
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

