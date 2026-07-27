package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.module.utility.inventory.cleaner.AbstractInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterPreset;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class SlotInventoryFilterRule
extends AbstractInventoryFilterRule {
    private static int[] slotStateArray;
    private final int slot;

    public SlotInventoryFilterRule(JsonObject jsonObject) {
        super(jsonObject);
        this.slot = jsonObject.get("slot").getAsInt();
    }

    static {
        SlotInventoryFilterRule.setSlotStateArray(new int[5]);
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void setSlotStateArray(int[] nArray) {
        slotStateArray = nArray;
    }

    @Override
    @Nullable
    public InventoryFilterPreset W() {
        UUID uUID = this.t();
        if (uUID != null) {
            return Vape.INSTANCE.getInventoryFilterPresetRegistry().g().l(uUID);
        }
        return this.J();
    }

    public int b() {
        return 36 + this.m();
    }

    @Override
    public JsonObject M(boolean bl) {
        JsonObject jsonObject = super.M(bl);
        jsonObject.addProperty("slot", (Number)this.slot);
        return jsonObject;
    }

    public SlotInventoryFilterRule(int n) {
        this.slot = n;
    }

    public static int[] getSlotStateArray() {
        return slotStateArray;
    }

    public int m() {
        return this.slot;
    }
}

