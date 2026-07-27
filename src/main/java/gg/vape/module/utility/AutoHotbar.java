package gg.vape.module.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.autohotbar.AutoHotbarSlotGroup;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.DamageSource;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionEntry;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AutoHotbar
extends Mod
implements InventoryActionModule {
    private final RandomValue delay;
    private final LimitValue blacklisted;
    private final BooleanValue removeFood;
    private final BooleanValue openInventory;
    private final ModeValue activation;
    private final ModeOption toggleOption;
    private final TimerUtil clickTimer;
    private final BooleanValue bestItems;
    private final BooleanValue inventoryOnly;
    private final BooleanValue removeNegativePotions;
    private ItemStack bestItemC;
    private ItemStack bestItemA;
    private final ModeOption onKeyOption;
    private ItemStack[] bestArmorPieces;
    private ItemStack bestItemB;
    private boolean active;
    private ItemStack bestItemD;
    private final Queue<Integer> clickQueue = new ArrayDeque<Integer>();
    private static final long MAGIC_ID = -3117147329120510770L;

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && this.active && (this.openInventory.L() != false || Minecraft.currentScreen().isNull());
    }

    public AutoHotbar() {
        super("InvCleaner", (int)MAGIC_ID, Category.M, "Cleans blacklisted items from your inventory");
        this.clickTimer = new TimerUtil();
        this.delay = RandomValue.C(this, "Delay", "#", "", 1.0, 100.0, 120.0, 200.0, 1.0);
        this.bestItems = BooleanValue.create(this, "Best Items", true, "Keeps the best set of armor, sword, axe, pickaxe and bow");
        this.removeNegativePotions = BooleanValue.create(this, "Remove Negative Potions", true, "Will always throw out negative potions");
        this.removeFood = BooleanValue.create(this, "Remove Food", true, "Remove Food except for Golden Apples");
        this.openInventory = BooleanValue.create(this, "Open Inventory", true, "Opens your inventory when cleaning.");
        this.inventoryOnly = BooleanValue.create(this, "Inventory Only", true, "Only cleans while your inventory is open.");
        this.blacklisted = LimitValue.n(this, "invcleaner-blacklisted", "Blacklisted", LimitValue.G, Collections.emptyList());
        this.onKeyOption = new ModeOption("On Key");
        this.toggleOption = new ModeOption("Toggle");
        this.activation = ModeValue.create((Object)this, "Activation", this.onKeyOption, this.onKeyOption, this.toggleOption);
        this.R(false);
        this.activation.K(this.openInventory, this.inventoryOnly);
        this.activation.L(this.openInventory, this.onKeyOption);
        this.activation.L(this.inventoryOnly, this.toggleOption);
        this.addValue(this.activation, this.delay, this.openInventory, this.inventoryOnly, this.bestItems, this.removeNegativePotions, this.removeFood, this.blacklisted);
    }

    private boolean isNegativeSplashPotion(ItemStack itemStack) {
        if (!itemStack.getItem().isInstance(MappedClasses.Di)) {
            return false;
        }
        ItemSplashPotion itemSplashPotion = new ItemSplashPotion(itemStack.getItem());
        List<PotionEffect> list = itemSplashPotion.getPotionEffects(itemStack);
        for (PotionEffect potionEffect : list) {
            PotionEntry potionEntry = PotionRegistry.R(potionEffect);
            if (!potionEntry.L()) continue;
            return true;
        }
        return false;
    }

    private ItemStack findBestByComparator(List<Slot> list, Class<?> clazz, Comparator<ItemStack> comparator) {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        for (Slot slot : list) {
            ItemStack itemStack;
            if (!slot.v() || (itemStack = slot.I()).isNull() || !itemStack.getItem().isInstance(clazz)) continue;
            arrayList.add(itemStack);
        }
        Collections.reverse(arrayList);
        arrayList.sort(comparator);
        Collections.reverse(arrayList);
        return arrayList.isEmpty() ? null : (ItemStack)arrayList.get(0);
    }

    private boolean shouldRemove(ItemStack itemStack) {
        int armorType;
        Item item = itemStack.getItem();
        if (ItemStackScoreUtil.R(item) && this.bestArmorPieces[armorType = ItemStackScoreUtil.t(itemStack)] != null && !this.bestArmorPieces[armorType].equals(itemStack)) {
            return true;
        }
        int notBest = 1;
        notBest = this.bestItems.L() != false ? (item.isInstance(MappedClasses.Vl) && !this.bestItemC.equals(itemStack) || item.isInstance(MappedClasses.DU) && !this.bestItemB.equals(itemStack) || ItemStackScoreUtil.h(item) && !this.bestItemA.equals(itemStack) || item.isInstance(MappedClasses.YP) && !this.bestItemD.equals(itemStack) ? 1 : 0) : 0;
        return this.blacklisted.isValid(itemStack, true) || notBest != 0 || this.removeFood.L() != false && item.isInstance(MappedClasses.DL) && !item.isInstance(MappedClasses.q3) || this.removeNegativePotions.L() != false && item.isInstance(MappedClasses.Di) && this.isNegativeSplashPotion(itemStack);
    }

    @Override
    public void loadJson(JsonObject jsonObject) {
        super.loadJson(jsonObject);
        if (jsonObject.get("blacklisted-items") != null) {
            JsonArray jsonArray = jsonObject.get("blacklisted-items").getAsJsonArray();
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("id", this.blacklisted.P$src$Ljava_lang_String_$1ijjhmj());
            jsonObject2.add("value", (JsonElement)jsonArray);
            this.blacklisted.loadJson(jsonObject2);
        }
    }

    @Override
    public void I() {
        this.blacklisted.Z("280", -1);
        this.blacklisted.Z("287", -1);
        this.blacklisted.Z("318", -1);
        this.blacklisted.Z("345", -1);
        this.blacklisted.Z("288", -1);
        this.blacklisted.Z("374", -1);
        this.blacklisted.Z("116", -1);
        this.blacklisted.Z("54", -1);
        this.blacklisted.Z("145", -1);
    }

    @Override
    public boolean X() {
        return this.activation.K() == this.onKeyOption;
    }

    private void closeInventoryIfOpen(EntityPlayerSP entityPlayerSP) {
        if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            entityPlayerSP.Z$src$V$1ie832h();
        }
    }

    public static double I(ItemStack itemStack) {
        double d = AutoHotbar.armorScore(itemStack);
        d += (double)EnchantmentHelper.q(32, itemStack);
        d += (double)EnchantmentHelper.q(16, itemStack);
        d += (double)EnchantmentHelper.q(19, itemStack);
        d += (double)EnchantmentHelper.q(20, itemStack);
        d += (double)EnchantmentHelper.q(48, itemStack);
        return d += (double)EnchantmentHelper.q(34, itemStack);
    }

    public LimitValue A() {
        return this.blacklisted;
    }

    private boolean g$src$Z$1qmj2fq() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!Minecraft.currentScreen().isInstance(MappedClasses.Ft) && this.openInventory.L().booleanValue() && this.activation.K() == this.onKeyOption) {
            KeyBinding keyBinding = Minecraft.gameSettings().j();
            if (ForgeVersion.MC_1_16_5.d()) {
                KeyBindingHelper.a(keyBinding);
            } else {
                KeyBindingHelper.d(keyBinding, true);
                KeyBindingHelper.v(keyBinding, false, false);
            }
            return false;
        }
        if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().isNull()) {
            return false;
        }
        List<Slot> list = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getInventorySlots();
        list.sort(Comparator.comparingInt(this::hotbarRegionOf));
        this.bestArmorPieces = this.collectBestArmor();
        this.bestItemA = this.findBestByComparator(list, MappedClasses.V5, Comparator.comparingDouble(ClientSettings::U));
        this.bestItemB = this.findBestByComparator(list, MappedClasses.DU, Comparator.comparingDouble(ClientSettings::X));
        this.bestItemC = this.findBestByComparator(list, MappedClasses.Vl, Comparator.comparingDouble(ClientSettings::c));
        this.bestItemD = this.findBestByComparator(list, MappedClasses.YP, Comparator.comparingDouble(ClientSettings::U));
        Map<Object, AutoHotbarSlotGroup> hashMap = new HashMap<Object, AutoHotbarSlotGroup>();
        block4: for (Slot object : entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getInventorySlots()) {
            try {
                if (!object.v() || object.I().isNull()) continue;
                for (Object object2 : entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().i()) {
                    if (object2 != null && object2.equals(object.I())) continue block4;
                }
                if (!this.shouldRemove(object.I())) continue;
                this.queueSlot(object.g());
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        try {
            for (Map.Entry<Object, AutoHotbarSlotGroup> entry : hashMap.entrySet()) {
                AutoHotbarSlotGroup autoHotbarSlotGroup = entry.getValue();
                autoHotbarSlotGroup.W();
                List<Integer> list2 = autoHotbarSlotGroup.r();
                if (list2.size() <= 0) continue;
                for (int i = AutoHotbarSlotGroup.l(autoHotbarSlotGroup); i < list2.size(); ++i) {
                    if (i <= 0) continue;
                    this.queueSlot(list2.get(i));
                }
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
        return true;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private int hotbarRegionOf(Slot slot) {
        int slotIndex = slot.g();
        if (slotIndex >= 36 && slotIndex <= 44) {
            return 0;
        }
        if (slotIndex >= 9 && slotIndex <= 17) {
            return 1;
        }
        if (slotIndex >= 18 && slotIndex <= 26) {
            return 2;
        }
        return 3;
    }

    private ItemStack[] collectBestArmor() {
        ItemStack[] itemStackArray = new ItemStack[4];
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        List<Slot> list = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getInventorySlots();
        for (Slot wrapper : list) {
            if (!wrapper.v() || !ItemStackScoreUtil.R(wrapper.I().getItem())) continue;
            arrayList.add(wrapper.I());
        }
        for (ItemStack itemStack : arrayList) {
            int armorType = ItemStackScoreUtil.t(itemStack);
            ItemStack existing = itemStackArray[armorType];
            if (existing != null && !(AutoHotbar.armorScore(itemStack) > AutoHotbar.armorScore(existing))) continue;
            itemStackArray[armorType] = itemStack;
        }
        return itemStackArray;
    }

    private static double armorScore(ItemStack itemStack) {
        int score = 0;
        if (itemStack.isNull()) {
            return score;
        }
        if (itemStack.getItem().isNotNull() && ItemStackScoreUtil.R(itemStack.getItem())) {
            score = (int)ItemStackScoreUtil.P(itemStack);
        }
        return score += EnchantmentHelper.B(new ItemStack[]{itemStack}, DamageSource.C(Minecraft.thePlayer()));
    }

    @Override
    public void onEnable() {
        this.clickQueue.clear();
        this.active = false;
    }

    private void queueSlot(int slotIndex) {
        if (this.clickQueue.contains(slotIndex)) {
            return;
        }
        this.clickQueue.add(slotIndex);
        this.clickQueue.add(-999);
        this.active = true;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (Vape.INSTANCE.getModManager().N(AutoHotbar.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.active = false;
            return;
        }
        EntityPlayerSP entityPlayerSP = eventPrePlayerTick.getThePlayer();
        if (!this.active) {
            if (this.g$src$Z$1qmj2fq() && !this.active && this.activation.K() == this.onKeyOption) {
                this.Y(false);
                if (this.openInventory.L().booleanValue()) {
                    this.closeInventoryIfOpen(entityPlayerSP);
                }
            }
            return;
        }
        if (this.activation.K() == this.toggleOption && this.inventoryOnly.L().booleanValue() && (!Minecraft.currentScreen().isInstance(MappedClasses.Ft) || entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode())) {
            return;
        }
        if (this.active && this.openInventory.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            this.F();
            return;
        }
        if (!this.clickQueue.isEmpty()) {
            if (this.clickTimer.hasTimeElapsed((long)this.delay.B())) {
                this.clickTimer.reset();
                int slotIndex = this.clickQueue.poll();
                Minecraft.playerController().O(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), slotIndex, 0, 0, entityPlayerSP);
            }
            return;
        }
        if (this.activation.K() == this.onKeyOption) {
            this.Y(false);
            if (this.openInventory.L().booleanValue()) {
                this.closeInventoryIfOpen(entityPlayerSP);
            }
        } else {
            this.active = false;
        }
        if (entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode() && Minecraft.currentScreen().isInstance(MappedClasses.Ft) && this.activation.K() == this.onKeyOption) {
            this.Y(false);
        }
    }
}
