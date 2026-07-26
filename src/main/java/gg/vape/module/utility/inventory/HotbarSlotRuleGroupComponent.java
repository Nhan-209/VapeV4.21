package gg.vape.module.utility.inventory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import func.skidline.RectData;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.utility.inventory.HotbarSlotRule;
import gg.vape.module.utility.inventory.HotbarSlotRuleEditorComponent;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerFrame;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import java.util.List;

public class HotbarSlotRuleGroupComponent
extends GuiComponent {
    private static String[] R;
    private HotbarSlotRuleEditorComponent K;
    private List<HotbarSlotRule> o;
    private SquareIconButtonComponent G = new SquareIconButtonComponent("newclose");

    public static void V(String[] stringArray) {
        R = stringArray;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        ClientSettings.g(HotbarSlotRuleItemPickerFrame.class).y(this);
        if (this.K.q$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$1uq9d6u().equals(this)) {
            HotbarSlotRuleItemPickerFrame hotbarSlotRuleItemPickerFrame = ClientSettings.g(HotbarSlotRuleItemPickerFrame.class);
            if (ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() instanceof ClickGuiFrameManager) {
                ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
                hotbarSlotRuleItemPickerFrame.O(clickGuiFrameManager);
                clickGuiFrameManager.K(hotbarSlotRuleItemPickerFrame);
                clickGuiFrameManager.q(hotbarSlotRuleItemPickerFrame.D$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$dqviyt());
                clickGuiFrameManager.R(hotbarSlotRuleItemPickerFrame, hotbarSlotRuleItemPickerFrame.D$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$dqviyt());
            } else {
                hotbarSlotRuleItemPickerFrame.O(ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v());
                hotbarSlotRuleItemPickerFrame.t(true, false);
                ClientSettings.fW.I(ClientSettings.L);
                RectData rectData = new RectData(0.0, 0.0, Minecraft.J(), Minecraft.h());
                ClientSettings.fW.M(rectData, rectData);
            }
        }
        this.K.f(this);
    }

    public List<HotbarSlotRule> u$src$Ljava_util_List_$1u5n2i3() {
        return this.o;
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public void H() {
        boolean bl = this.K.q$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$1uq9d6u() != null && this.u$src$Ljava_util_List_$1u5n2i3().equals(this.K.q$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$1uq9d6u().u$src$Ljava_util_List_$1u5n2i3());
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a() + 5.0, this.n() + 1.0, this.A() - 10.0, this.L() - 2.0, bl ? HotbarSlotRuleGroupComponent.J.K : HotbarSlotRuleGroupComponent.J.m);
        double d = this.G$src$D$1b2f02a() + 10.0;
        for (HotbarSlotRule hotbarSlotRule : this.o) {
            double d2 = 9.0;
            GuiRenderPrimitives.C(d, this.n() + this.L() / 2.0 - d2 / 2.0, 8.5, d2, HotbarSlotRuleGroupComponent.J.r);
            ItemStack itemStack = hotbarSlotRule.c();
            if (itemStack != null && itemStack.isNotNull()) {
                float f = (float)d;
                float f2 = (float)(this.n() + this.L() / 2.0 - 4.0);
                ItemIconRenderer.R(itemStack, f, f2, 8, 8);
            }
            d += 9.0;
        }
        this.G.K(this.G$src$D$1b2f02a() + this.A() - 10.0 - 8.0);
        this.G.S(this.n());
        this.G.Y(this.L());
    }

    static {
        HotbarSlotRuleGroupComponent.V(null);
    }

    public HotbarSlotRuleGroupComponent Q(GuiClickListener guiClickListener) {
        this.G.r(guiClickListener);
        return this;
    }

    public JsonObject b$src$Lcom_google_gson_JsonObject_$1jm30j() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (HotbarSlotRule hotbarSlotRule : this.o) {
            jsonArray.add((JsonElement)hotbarSlotRule.C());
        }
        jsonObject.add("hotbars", (JsonElement)jsonArray);
        return jsonObject;
    }

    @Override
    public void I() {
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static String[] e() {
        return R;
    }

    @Override
    public double C() {
        return 15.0;
    }

    @Override
    public void u() {
    }

    public void O(List<HotbarSlotRule> list) {
        this.o = list;
    }

    public HotbarSlotRuleGroupComponent(HotbarSlotRuleEditorComponent hotbarSlotRuleEditorComponent, List<HotbarSlotRule> list) {
        this.o = list;
        this.K = hotbarSlotRuleEditorComponent;
        this.H(this.G);
    }

    public void W(JsonObject jsonObject) {
        JsonArray jsonArray = jsonObject.getAsJsonArray("hotbars");
        int n = jsonArray.size();
        for (int i = 0; i < n; ++i) {
            JsonObject jsonObject2 = jsonArray.get(i).getAsJsonObject();
            this.u$src$Ljava_util_List_$1u5n2i3().get(i).W(jsonObject2);
        }
    }

    @Override
    public void F() {
    }
}

