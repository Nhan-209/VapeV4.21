package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.inventory.InventoryActionGuard;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AutoArmor
extends Mod
implements InventoryActionModule {
    private final BooleanValue inventoryOnly;
    private String[] bootsNames;
    private final TimerUtil openTimer;
    private final TimerUtil clickTimer;
    private InventoryPlayer inventory;
    private boolean closingInventory;
    private final BooleanValue dropEquipped;
    private final RandomValue delay;
    private Object lastScreen;
    private String[] helmetNames;
    private final BooleanValue checkDurability;
    private String[] leggingsNames;
    private final BooleanValue combatCheck;
    private static final long MAGIC_ID = -516952979702363148L;
    private final InventoryActionGuard combatGuard;
    private final BooleanValue openInventory = BooleanValue.create(this, "Open inventory", true, "Opens your inventory when you can equip armor");
    private boolean pressedInventoryKey;
    private final Queue<InventoryClick> clickQueue;
    private String[] chestplateNames;

    private void queueClick(int n, int n2, int n3, int n4) {
        this.clickQueue.add(new InventoryClick(n, n2, n3, n4));
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (Vape.INSTANCE.getModManager().N(AutoArmor.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.clickQueue.clear();
            return;
        }
        this.trackScreenChange();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!entityPlayerSP.p$src$Lgg_vape_wrapper_impl_Container_$1a6go00().isNull() && entityPlayerSP.p$src$Lgg_vape_wrapper_impl_Container_$1a6go00().getWindowId() != 0) {
            return;
        }
        if (this.openInventory.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS) && !Minecraft.currentScreen().isInstance(MappedClasses.n)) {
            this.clickQueue.clear();
        }
        if (this.inventoryOnly.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS) && !Minecraft.currentScreen().isInstance(MappedClasses.n)) {
            this.clickQueue.clear();
            return;
        }
        if (!this.openTimer.hasTimeElapsed(100L + (long)this.delay.B())) {
            return;
        }
        if (!this.clickQueue.isEmpty()) {
            if (this.clickTimer.hasTimeElapsed((long)this.delay.B())) {
                InventoryClick inventoryClick = this.clickQueue.poll();
                inventoryClick.k();
                this.clickTimer.reset();
            }
            return;
        }
        if (this.closingInventory) {
            if (Minecraft.currentScreen().isNotNull()) {
                entityPlayerSP.Z$src$V$1ie832h();
                this.pressedInventoryKey = false;
                this.closingInventory = false;
            }
            return;
        }
        if (this.combatCheck.L().booleanValue()) {
            this.combatGuard.i(entityPlayerSP);
            if (this.combatGuard.l()) {
                this.openTimer.reset();
                return;
            }
        }
        for (int i = 5; i < 9; ++i) {
            int n = this.findBestArmorSlot(i, this.checkDurability.L());
            if (n == -1) continue;
            if (this.openInventory.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
                if (this.openTimer.hasTimeElapsed(200L + (long)this.delay.B())) {
                    KeyBinding keyBinding = Minecraft.gameSettings().j();
                    if (ForgeVersion.MC_1_16_5.d()) {
                        KeyBindingHelper.a(keyBinding);
                    } else {
                        KeyBindingHelper.d(keyBinding, true);
                        KeyBindingHelper.v(keyBinding, false, false);
                    }
                    this.pressedInventoryKey = true;
                }
                return;
            }
            if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().isNotNull()) {
                if (this.dropEquipped.L().booleanValue()) {
                    this.queueClick(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), i, 0, 0);
                    this.queueClick(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), -999, 0, 0);
                } else {
                    this.queueClick(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), i, 0, 1);
                }
            }
            this.queueClick(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), n, 0, 1);
        }
        if (this.pressedInventoryKey && this.clickQueue.isEmpty()) {
            this.closingInventory = true;
            this.openTimer.reset();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.closingInventory = false;
        this.pressedInventoryKey = false;
    }

    private int findBestArmorSlot(int n, boolean bl) {
        int n2 = -1;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I();
        double d = 0.0;
        double d2 = 999.0;
        if (itemStack.isNotNull()) {
            d = this.scoreItem(itemStack);
            d2 = this.durabilityOf(n);
        }
        double d3 = d;
        double d4 = d2;
        for (int i = 9; i < 45; ++i) {
            ItemStack itemStack2;
            if (!entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I().isNotNull() || this.armorSlotFor(itemStack2 = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I()) == -1 || this.armorSlotFor(itemStack2) != n) continue;
            double d5 = this.scoreItem(itemStack2);
            double d6 = this.durabilityOf(i);
            if (d5 > d3) {
                d3 = d5;
                n2 = i;
                d4 = d6;
                continue;
            }
            if (!bl || d5 != d3 || !(d6 < d4)) continue;
            d4 = d6;
            n2 = i;
        }
        return n2;
    }

    private int durabilityOf(int n) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().isNotNull() ? entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().L() : 999;
    }


    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && this.clickQueue.size() > 0;
    }

    @Override
    public String r() {
        if (this.combatCheck.L().booleanValue() && this.combatGuard.l()) {
            return ClientSettings.F + "c[In Combat]";
        }
        return super.r();
    }

    private void trackScreenChange() {
        Object object = Minecraft.currentScreen().getObject();
        if (object != this.lastScreen) {
            this.openTimer.reset();
        }
        this.lastScreen = object;
    }

    private int armorSlotFor(ItemStack itemStack) {
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        if (itemMappingEntry == null || itemMappingEntry.M() == null) {
            return -1;
        }
        for (String string : this.bootsNames) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 8;
        }
        for (String string : this.leggingsNames) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 7;
        }
        for (String string : this.chestplateNames) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 6;
        }
        for (String string : this.helmetNames) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 5;
        }
        return -1;
    }

    public AutoArmor() {
        super("AutoArmor", (int)MAGIC_ID, Category.M, "Automatically equips armor when needed.");
        this.inventoryOnly = BooleanValue.create(this, "Inventory only", true, "Only equip armor when in inventory");
        this.checkDurability = BooleanValue.create(this, "Check durability", true, "Always puts on the armor with the highest durability");
        this.dropEquipped = BooleanValue.create(this, "Drop equipped", false, "Drops worse equipped armor for better armor when active");
        this.combatCheck = BooleanValue.create(this, "Combat check", false, "Won't equip armor while in combat");
        this.delay = RandomValue.C(this, "Delay", "#", "", 1.0, 100.0, 120.0, 200.0, 1.0);
        this.clickTimer = new TimerUtil();
        this.openTimer = new TimerUtil();
        this.combatGuard = new InventoryActionGuard(20);
        this.clickQueue = new ConcurrentLinkedQueue<InventoryClick>();
        this.addValue(this.openInventory, this.inventoryOnly, this.checkDurability, this.dropEquipped, this.combatCheck, this.delay);
        this.helmetNames = new String[]{"cap", "helmet"};
        this.chestplateNames = new String[]{"tunic", "chestplate"};
        this.leggingsNames = new String[]{"pants", "leggings"};
        this.bootsNames = new String[]{"boots"};
    }

    private double scoreItem(ItemStack itemStack) {
        Item item;
        double d = ItemStackScoreUtil.L(itemStack);
        if (ForgeVersion.MC_1_16_5.d()) {
            // empty if block
        }
        if (itemStack.isNotNull() && (item = itemStack.getItem()).isNotNull() && ItemStackScoreUtil.R(item) && ItemStackScoreUtil.T$src$Z$2fnsig(itemStack) && ItemStackScoreUtil.t(itemStack) == 0) {
            d -= 0.01;
        }
        return d;
    }
}

