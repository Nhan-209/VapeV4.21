package gg.vape.module.utility.inventory;

import com.google.gson.JsonObject;
import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.PotionEffect;
import java.util.List;

public class HotbarSlotRule {
    private boolean M;
    private boolean b;
    private boolean W;
    private int q = 0;
    private int R;

    public int x() {
        return this.R;
    }

    public void W(JsonObject jsonObject) {
        if (jsonObject.get("item-id") != null) {
            this.R = jsonObject.get("item-id").getAsInt();
        }
        if (jsonObject.get("meta") != null) {
            this.q = jsonObject.get("meta").getAsInt();
        }
        if (jsonObject.get("anyMeta") != null) {
            this.M = jsonObject.get("anyMeta").getAsBoolean();
        }
        if (jsonObject.get("anyMaterial") != null) {
            this.W = jsonObject.get("anyMaterial").getAsBoolean();
        }
        if (jsonObject.get("onlyBest") != null) {
            this.b = jsonObject.get("onlyBest").getAsBoolean();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public HotbarSlotRule M(int n) {
        this.q = n;
        return this;
    }

    public boolean C$src$Z$deeqpc() {
        return this.b;
    }

    public static HotbarSlotRule c(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return new HotbarSlotRule(0);
        }
        HotbarSlotRule hotbarSlotRule = new HotbarSlotRule(itemStack.getItem().P());
        hotbarSlotRule.M(itemStack.L());
        return hotbarSlotRule;
    }

    public boolean y(ItemStack itemStack) {
        if (itemStack.isNotNull()) {
            Item item = itemStack.getItem();
            if (item.P() == this.x()) {
                if (this.M || itemStack.L() == this.q) {
                    return true;
                }
                if (item.isInstance(MappedClasses.Di)) {
                    ItemSplashPotion itemSplashPotion = new ItemSplashPotion(this.i());
                    ItemSplashPotion itemSplashPotion2 = new ItemSplashPotion(itemStack.getItem());
                    ItemStack itemStack2 = ItemStack.S(itemSplashPotion);
                    itemStack2.s(this.q);
                    List<PotionEffect> list = itemSplashPotion.getPotionEffects(itemStack2);
                    List<PotionEffect> list2 = itemSplashPotion2.getPotionEffects(itemStack);
                    if (Boolean.compare(ItemSplashPotion.isSplashPotion(itemStack2), ItemSplashPotion.isSplashPotion(itemStack)) == 0 && list.size() > 0) {
                        PotionEffect potionEffect = list.get(0);
                        for (PotionEffect potionEffect2 : list2) {
                            if (potionEffect2.C() != potionEffect.C()) continue;
                            return true;
                        }
                    }
                }
            }
            if (this.W) {
                return item.getObject().getClass().equals(this.i().getObject().getClass());
            }
        }
        return false;
    }

    public ItemStack c() {
        Item item = Item.T(this.R);
        if (item.isNull()) {
            return null;
        }
        ItemStack itemStack = ItemStack.S(item);
        if (itemStack.isNotNull()) {
            itemStack.s(this.P());
        }
        return itemStack;
    }

    public Item i() {
        return Item.T(this.x());
    }

    public int P() {
        return this.q;
    }

    public HotbarSlotRule(int n) {
        this.R = n;
    }

    public JsonObject C() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item-id", (Number)this.R);
        jsonObject.addProperty("meta", (Number)this.q);
        jsonObject.addProperty("anyMeta", Boolean.valueOf(this.M));
        jsonObject.addProperty("anyMaterial", Boolean.valueOf(this.W));
        jsonObject.addProperty("onlyBest", Boolean.valueOf(this.b));
        return jsonObject;
    }
}

