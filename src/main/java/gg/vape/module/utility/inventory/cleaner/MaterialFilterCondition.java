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
        return this.copy();
    }
    private final List<ItemFilterSelection> selections = new ArrayList<ItemFilterSelection>();
    private static GuiComponent[] cachedComponents;
    private MembershipMode membershipMode = MembershipMode.IS_IN;

    public MembershipMode x() {
        return this.membershipMode;
    }

    public void clear() {
        this.selections.clear();
    }

    @Override
    public boolean g(ItemStack itemStack) {
        boolean isIn = this.membershipMode.equals(MembershipMode.IS_IN);
        for (ItemFilterSelection itemFilterSelection : this.selections) {
            if (!itemFilterSelection.h(itemStack)) continue;
            return isIn;
        }
        return !isIn;
    }

    public MaterialFilterCondition(JsonObject jsonObject) {
        this.membershipMode = MembershipMode.N(jsonObject.get("operator").getAsString());
        JsonArray jsonArray = ConfigJsonUtils.q(jsonObject, "items");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); ++i) {
                JsonElement jsonElement = jsonArray.get(i);
                ItemFilterSelection itemFilterSelection = new ItemFilterSelection(jsonElement);
                this.selections.add(itemFilterSelection);
            }
        }
    }

    public void A(ItemFilterSelection itemFilterSelection) {
        this.selections.remove(itemFilterSelection);
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
        return cachedComponents;
    }

    public static void C(GuiComponent[] guiComponentArray) {
        cachedComponents = guiComponentArray;
    }

    public MaterialFilterCondition(List<ItemFilterSelection> list, MembershipMode membershipMode) {
        for (ItemFilterSelection itemFilterSelection : list) {
            this.selections.add(itemFilterSelection.y());
        }
        this.membershipMode = membershipMode;
    }

    static {
        MaterialFilterCondition.C(null);
    }

    public void j(MembershipMode membershipMode) {
        this.membershipMode = membershipMode;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void b(ItemFilterSelection itemFilterSelection) {
        this.selections.add(itemFilterSelection);
    }

    public MaterialFilterCondition copy() {
        return new MaterialFilterCondition(this.selections, this.membershipMode);
    }

    public @UnmodifiableView List<ItemFilterSelection> U() {
        return this.selections;
    }

    @Override
    public JsonObject L() {
        JsonObject jsonObject = InventoryFilterCondition.super.L();
        JsonArray jsonArray = new JsonArray();
        for (ItemFilterSelection itemFilterSelection : this.selections) {
            jsonArray.add(itemFilterSelection.Q());
        }
        jsonObject.addProperty("operator", this.membershipMode.getName());
        if (jsonArray.size() > 0) {
            jsonObject.add("items", (JsonElement)jsonArray);
        }
        return jsonObject;
    }

    @Nullable
    public ItemFilterSelection t(String string) {
        for (ItemFilterSelection itemFilterSelection : this.selections) {
            if (itemFilterSelection.J() == null || !itemFilterSelection.J().equalsIgnoreCase(string)) continue;
            return itemFilterSelection;
        }
        return null;
    }
}
