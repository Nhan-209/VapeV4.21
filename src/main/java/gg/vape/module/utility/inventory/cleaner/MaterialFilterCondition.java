package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterConditionType;
import gg.vape.module.utility.inventory.cleaner.ItemFilterSelection;
import gg.vape.module.utility.inventory.cleaner.MembershipMode;
import gg.vape.module.utility.inventory.cleaner.ui.ItemPickerSelection;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ItemStack;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class MaterialFilterCondition
implements InventoryFilterCondition<MaterialFilterCondition> {
    @Override
    public MaterialFilterCondition w() {
        return this.Y();
    }
    private final List<ItemFilterSelection> i = new ArrayList<ItemFilterSelection>();
    private static GuiComponent[] E;
    private MembershipMode t = MembershipMode.IS_IN;

    public MembershipMode x() {
        return this.t;
    }

    public void g() {
        this.i.clear();
    }

    @Override
    public boolean g(ItemStack itemStack) {
        boolean bl = this.t.equals(MembershipMode.IS_IN);
        for (ItemFilterSelection itemFilterSelection : this.i) {
            if (!itemFilterSelection.h(itemStack)) continue;
            return bl;
        }
        return !bl;
    }

    public MaterialFilterCondition(JsonObject jsonObject) {
        this.t = MembershipMode.N(jsonObject.get("operator").getAsString());
        JsonArray jsonArray = ConfigJsonUtils.q(jsonObject, "items");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); ++i) {
                JsonElement jsonElement = jsonArray.get(i);
                ItemFilterSelection itemFilterSelection = new ItemFilterSelection(jsonElement);
                this.i.add(itemFilterSelection);
            }
        }
    }

    public void A(ItemFilterSelection itemFilterSelection) {
        this.i.remove(itemFilterSelection);
    }

    public MaterialFilterCondition() {
    }

    public void X(ItemPickerSelection<String, ItemMappingEntry> itemPickerSelection) {
        ItemFilterSelection itemFilterSelection = new ItemFilterSelection();
        itemFilterSelection.G(itemPickerSelection);
        this.b(itemFilterSelection);
    }

    @Override
    public InventoryFilterConditionType K() {
        return InventoryFilterConditionType.MATERIAL;
    }

    public static GuiComponent[] H() {
        return E;
    }

    public static void C(GuiComponent[] guiComponentArray) {
        E = guiComponentArray;
    }

    public MaterialFilterCondition(List<ItemFilterSelection> list, MembershipMode membershipMode) {
        for (ItemFilterSelection itemFilterSelection : list) {
            this.i.add(itemFilterSelection.y());
        }
        this.t = membershipMode;
    }

    static {
        MaterialFilterCondition.C(null);
    }

    public void j(MembershipMode membershipMode) {
        this.t = membershipMode;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void b(ItemFilterSelection itemFilterSelection) {
        this.i.add(itemFilterSelection);
    }

    public MaterialFilterCondition Y() {
        return new MaterialFilterCondition(this.i, this.t);
    }

    public @UnmodifiableView List<ItemFilterSelection> U() {
        return this.i;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = InventoryFilterCondition.super.L();
        JsonArray jsonArray = new JsonArray();
        for (ItemFilterSelection itemFilterSelection : this.i) {
            jsonArray.add(itemFilterSelection.Q());
        }
        jsonObject.addProperty("operator", this.t.getName());
        if (jsonArray.size() > 0) {
            jsonObject.add("items", (JsonElement)jsonArray);
        }
        return jsonObject;
    }

    @Nullable
    public ItemFilterSelection t(String string) {
        for (ItemFilterSelection itemFilterSelection : this.i) {
            if (itemFilterSelection.J() == null || !itemFilterSelection.J().equalsIgnoreCase(string)) continue;
            return itemFilterSelection;
        }
        return null;
    }
}
