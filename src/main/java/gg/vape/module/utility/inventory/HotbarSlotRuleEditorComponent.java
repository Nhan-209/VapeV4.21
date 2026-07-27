package gg.vape.module.utility.inventory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.module.utility.inventory.HotbarSlotRule;
import gg.vape.module.utility.inventory.HotbarSlotRuleAddButton;
import gg.vape.module.utility.inventory.HotbarSlotRuleGroupComponent;
import gg.vape.module.utility.inventory.HotbarSlotRuleGroupSelectClickHandler;
import gg.vape.module.utility.inventory.HotbarSlotRuleValue;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import java.util.ArrayList;
import java.util.List;

public class HotbarSlotRuleEditorComponent
extends GuiComponent {
    private HotbarSlotRuleGroupComponent O;
    private List<HotbarSlotRuleGroupComponent> Q;
    private boolean G;
    private HotbarSlotRuleAddButton v = new HotbarSlotRuleAddButton();
    private static String[] R;
    private HotbarSlotRuleValue I;

    public HotbarSlotRuleValue F$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$1fu2xro() {
        return this.I;
    }

    static List N(HotbarSlotRuleEditorComponent hotbarSlotRuleEditorComponent) {
        return hotbarSlotRuleEditorComponent.Q;
    }

    public JsonObject A$src$Lcom_google_gson_JsonObject_$167pnb8() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent : this.Q) {
            jsonArray.add((JsonElement)hotbarSlotRuleGroupComponent.b$src$Lcom_google_gson_JsonObject_$1jm30j());
        }
        if (this.O == null) {
            jsonObject.addProperty("selected", (Number)0);
        } else {
            jsonObject.addProperty("selected", (Number)this.Q.indexOf(this.O));
        }
        jsonObject.add("panels", (JsonElement)jsonArray);
        return jsonObject;
    }

    public List<HotbarSlotRuleGroupComponent> U$src$Ljava_util_List_$1g9oi4r() {
        return this.Q;
    }


    @Override
    public void u() {
    }

    public void f(JsonObject jsonObject) {
        this.Q.clear();
        if (!jsonObject.has("panels")) {
            return;
        }
        JsonArray jsonArray = jsonObject.get("panels").getAsJsonArray();
        int n = jsonObject.get("selected").getAsInt();
        int n2 = jsonArray.size();
        for (int i = 0; i < n2; ++i) {
            HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent = new HotbarSlotRuleGroupComponent(this, this.B$src$Ljava_util_List_$12o4b7i());
            hotbarSlotRuleGroupComponent.W(jsonArray.get(i).getAsJsonObject());
            this.Q.add(hotbarSlotRuleGroupComponent);
        }
        if (this.Q.size() > 0) {
            this.O = this.U$src$Ljava_util_List_$1g9oi4r().get(n);
        }
        this.w$src$V$j701ty();
    }

    @Override
    public void I() {
    }

    public void w$src$V$j701ty() {
        this.f().clear();
        this.H(this.v);
        for (HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent : this.U$src$Ljava_util_List_$1g9oi4r()) {
            HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent2 = hotbarSlotRuleGroupComponent.Q(new HotbarSlotRuleGroupSelectClickHandler(this, hotbarSlotRuleGroupComponent));
            this.H(hotbarSlotRuleGroupComponent2);
        }
        this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().l$src$V$1mibm4x();
    }

    static {
        HotbarSlotRuleEditorComponent.v(new String[1]);
    }

    private List<HotbarSlotRule> B$src$Ljava_util_List_$12o4b7i() {
        ArrayList<HotbarSlotRule> arrayList = new ArrayList<HotbarSlotRule>();
        for (int i = 0; i < 9; ++i) {
            HotbarSlotRule hotbarSlotRule = new HotbarSlotRule(0);
            arrayList.add(hotbarSlotRule);
        }
        return arrayList;
    }

    public HotbarSlotRuleEditorComponent(HotbarSlotRuleValue hotbarSlotRuleValue) {
        this.Q = new ArrayList<HotbarSlotRuleGroupComponent>();
        this.I = hotbarSlotRuleValue;
        hotbarSlotRuleValue.u(this);
        HotbarSlotRuleEditorComponent hotbarSlotRuleEditorComponent = this;
        this.v.r(() -> {
            HotbarSlotRuleGroupComponent groupComponent = new HotbarSlotRuleGroupComponent(hotbarSlotRuleEditorComponent, this.B$src$Ljava_util_List_$12o4b7i());
            this.Q.add(groupComponent);
            if (this.O == null) {
                this.O = groupComponent;
            }
            this.w$src$V$j701ty();
        });
        this.H(this.v);
    }

    static HotbarSlotRuleGroupComponent H(HotbarSlotRuleEditorComponent hotbarSlotRuleEditorComponent) {
        return hotbarSlotRuleEditorComponent.O;
    }

    public void f(HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent) {
        this.O = hotbarSlotRuleGroupComponent;
    }

    @Override
    public double x() {
        return 110.0;
    }

    @Override
    public void H() {
        this.onDisable();
        this.v.K(this.G$src$D$1b2f02a());
        this.v.S(this.n());
        double d = this.v.n() + this.v.L();
        for (HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent : this.U$src$Ljava_util_List_$1g9oi4r()) {
            hotbarSlotRuleGroupComponent.K(this.G$src$D$1b2f02a());
            hotbarSlotRuleGroupComponent.S(d);
            d += hotbarSlotRuleGroupComponent.L();
        }
    }

    @Override
    public void F() {
    }

    public static String[] u$src$ALjava_lang_String_$1im86xh() {
        return R;
    }

    @Override
    public double C() {
        double d = this.v.L();
        for (HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent : this.U$src$Ljava_util_List_$1g9oi4r()) {
            d += hotbarSlotRuleGroupComponent.L();
        }
        return d;
    }

    public static void v(String[] stringArray) {
        R = stringArray;
    }

    public void o(HotbarSlotRuleGroupComponent hotbarSlotRuleGroupComponent) {
        this.U$src$Ljava_util_List_$1g9oi4r().remove(hotbarSlotRuleGroupComponent);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    public HotbarSlotRuleGroupComponent q$src$Lgg_vape_module_utility_inventory_HotbarSlotRule$1uq9d6u() {
        return this.O;
    }
}
