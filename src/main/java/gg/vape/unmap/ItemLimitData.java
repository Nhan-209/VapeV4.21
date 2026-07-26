package gg.vape.unmap;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.utils.Base64Util;
import gg.vape.value.ToggleableListEntry;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Arrays;
import java.util.List;

public class ItemLimitData
implements ToggleableListEntry {
    private int q = -1;
    private String b = "";
    private static String d;
    private int z;
    public static final List<ItemLimitData> g;
    private boolean x = true;
    public static ItemLimitData A;
    public static final List<ItemLimitData> P;

    private static Exception a(Exception exception) {
        return exception;
    }

    public void v(boolean bl) {
        this.x = bl;
    }

    @Override
    public boolean q() {
        return this.x;
    }

    private boolean j(ItemStack itemStack) {
        Item item;
        String string = this.getName().toLowerCase();
        if (string.equals(String.valueOf((item = itemStack.getItem()).P()))) {
            return true;
        }
        return string.equals(item.getItemStackDisplayName(itemStack).toLowerCase());
    }

    public void W(JsonObject jsonObject) {
        if (jsonObject.get("item-id") != null) {
            this.b = jsonObject.get("item-id").getAsString();
            if (this.b.startsWith("b64:")) {
                this.b = Base64Util.decodeUtf8Base64(this.b.split(":")[1]);
            }
            this.F();
        }
        if (jsonObject.get("total-stacks") != null) {
            this.z = jsonObject.get("total-stacks").getAsInt();
        }
        if (jsonObject.get("enabled") != null) {
            this.x = jsonObject.get("enabled").getAsBoolean();
        }
    }

    public boolean C$src$Z$ttiu0t() {
        return this.x;
    }

    public ItemLimitData d(int n) {
        this.q = n;
        return this;
    }

    public Item T() {
        return Item.L(this.getName());
    }

    public void m(int n) {
        this.z = n;
    }

    public boolean W(ItemStack itemStack) {
        try {
            int n;
            int n2;
            if (!this.C$src$Z$ttiu0t()) {
                return false;
            }
            if (this.getName().toLowerCase().startsWith("slot")) {
                n2 = 0;
                try {
                    n2 = Integer.parseInt(this.getName().substring(4));
                }
                catch (Exception exception) {
                    // empty catch block
                }
                if (n2 >= 1 && Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v() + 1 == n2) {
                    return true;
                }
            }
            if (this.g(itemStack)) {
                return true;
            }
            if (itemStack.isNull() || itemStack.getItem().isNull()) {
                return false;
            }
            boolean matches = this.j(itemStack);
            if (matches && this.q != -1 && this.q != (n = itemStack.L())) {
                matches = false;
            }
            return matches;
        }
        catch (Exception exception) {
            return false;
        }
    }

    static {
        ItemLimitData.B(null);
        A = new ItemLimitData("air", 0);
        P = Arrays.asList(new ItemLimitData("Dispenser"), new ItemLimitData("Note Block"), new ItemLimitData("Cobweb"), new ItemLimitData("TNT"), new ItemLimitData("Monster Spawner"), new ItemLimitData("Enchantment Table"), new ItemLimitData("Oak Fence"), new ItemLimitData("Jukebox"), new ItemLimitData("Melon"), new ItemLimitData("Command Block"), new ItemLimitData("Anvil"), new ItemLimitData("Glass Pane"), new ItemLimitData("White Stained Glass Pane"), new ItemLimitData("Iron Bars"), new ItemLimitData("Ice"), new ItemLimitData("Packed Ice"), new ItemLimitData("Anvil"), new ItemLimitData("Block of Redstone"), new ItemLimitData("Gold Ore"), new ItemLimitData("Iron Ore"), new ItemLimitData("Coal Ore"), new ItemLimitData("Lapis Lazuli Ore"), new ItemLimitData("Redstone Ore"), new ItemLimitData("Acacia Wood Stairs"), new ItemLimitData("Wooden Pressure Plate"), new ItemLimitData("Stone Pressure Plate"), new ItemLimitData("Beacon"), new ItemLimitData("Oak Sapling"), new ItemLimitData("Powered Rail"), new ItemLimitData("Detector Rail"), new ItemLimitData("Shrub"), new ItemLimitData("Dead Bush"), new ItemLimitData("Dandelion"), new ItemLimitData("Poppy"), new ItemLimitData("Mushroom"), new ItemLimitData("Ladder"), new ItemLimitData("Rail"), new ItemLimitData("Wooden Trapdoor"), new ItemLimitData("Lily Pad"), new ItemLimitData("Tripwire Hook"), new ItemLimitData("Carpet"), new ItemLimitData("Snow"), new ItemLimitData("Trapped Chest"), new ItemLimitData("Daylight Sensor"), new ItemLimitData("Hopper"), new ItemLimitData("Chest"), new ItemLimitData("Torch"), new ItemLimitData("Lever"), new ItemLimitData("Redstone Torch"), new ItemLimitData("Button"), new ItemLimitData("Cactus"));
        g = Arrays.asList(new ItemLimitData("Anvil"), new ItemLimitData("Barrier"), new ItemLimitData("Beacon"), new ItemLimitData("Bed"), new ItemLimitData("Brewing Stand"), new ItemLimitData("Button"), new ItemLimitData("Cake"), new ItemLimitData("Chest"), new ItemLimitData("Crafting Table"), new ItemLimitData("Trapped Chest"), new ItemLimitData("Ender Chest"), new ItemLimitData("Command Block"), new ItemLimitData("Dragon Egg"), new ItemLimitData("Daylight Detector"), new ItemLimitData("Door"), new ItemLimitData("Dispenser"), new ItemLimitData("Dropper"), new ItemLimitData("Enchanting Table"), new ItemLimitData("Fence Gate"), new ItemLimitData("Furnace"), new ItemLimitData("Hopper"), new ItemLimitData("Jukebox"), new ItemLimitData("Lever"), new ItemLimitData("Note Block"), new ItemLimitData("Sign"), new ItemLimitData("Redstone Comparator"), new ItemLimitData("Redstone Repeater"), new ItemLimitData("Trapdoor"));
    }

    private boolean g(ItemStack itemStack) {
        return Vape.INSTANCE.getItemHelper().matchesItem(this.getName().toLowerCase(), itemStack);
    }

    public String getName() {
        return this.b;
    }

    public int L() {
        return this.q;
    }

    public ItemLimitData(String string, int n) {
        this.b = string;
        this.z = n;
        this.F();
    }

    private void F() {
        if (this.b.contains(":")) {
            String[] stringArray = this.b.split(":");
            this.b = stringArray[0];
            String string = stringArray[1];
            if (string.isEmpty()) {
                return;
            }
            try {
                this.q = Integer.parseInt(string);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    @Override
    public void x(boolean bl) {
        this.x = bl;
    }

    public int C() {
        return this.z;
    }

    public ItemLimitData(int n) {
        this(String.valueOf(n));
    }

    public String toString() {
        return this.getName();
    }

    @Override
    public void z() {
        this.x = !this.x;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        String string = this.b + (this.q == -1 ? "" : ":" + this.q);
        string = "b64:" + Base64Util.encodeUtf8Base64(string);
        jsonObject.addProperty("item-id", string);
        jsonObject.addProperty("total-stacks", (Number)this.z);
        jsonObject.addProperty("enabled", Boolean.valueOf(this.x));
        return jsonObject;
    }

    public ItemLimitData(String string) {
        this(string, 1);
    }

    public void E(String string) {
        this.b = string;
    }

    public static String c() {
        return d;
    }

    public static void B(String string) {
        d = string;
    }
}
