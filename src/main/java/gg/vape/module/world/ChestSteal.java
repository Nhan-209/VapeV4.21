package gg.vape.module.world;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventWindowClick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.module.world.ChestStealInventoryState;
import gg.vape.module.world.cheststeal.ChestStealBestSlotTracker;
import gg.vape.module.world.cheststeal.ChestStealSlotDistanceComparator;
import gg.vape.utils.RandomUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiChest;
import gg.vape.wrapper.impl.Inventory;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayList;
import java.util.Collections;

public class ChestSteal
extends Mod
implements InventoryActionModule {
    private final int[] S;
    private final TimerUtil r;
    private final int[] c;
    private boolean p;
    private final BooleanValue U;
    private TimerUtil H;
    private final LimitValue o;
    private final BooleanValue k;
    private final TimerUtil D;
    private final int[] j;
    private int P = -1;
    private final RandomValue s;
    private ArrayList<Integer> t;
    private boolean K = false;
    boolean b = false;
    private final BooleanValue O = BooleanValue.create(this, "Check in menu", false, "This will attempt to ignore Menus such as\nServer selectors, and settings inventory menus\nThis may not work 100% on all servers");
    private final BooleanValue I = BooleanValue.create(this, "Best only", false, "Only takes an item if it is better than what you have equipped.");
    boolean L = false;
    private final int[] J;
    private TimerUtil F;
    private ChestStealBestSlotTracker C;

    private int I(ItemStack itemStack) {
        int n = itemStack.getItem().P();
        for (int n2 : this.c) {
            if (n != n2) continue;
            return 8;
        }
        for (int n2 : this.S) {
            if (n != n2) continue;
            return 7;
        }
        for (int n2 : this.J) {
            if (n != n2) continue;
            return 6;
        }
        for (int n2 : this.j) {
            if (n != n2) continue;
            return 5;
        }
        return -1;
    }

    private void C$src$V$1buey7d() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        for (int i = 0; i < 45; ++i) {
            ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I();
            if (itemStack.isNull() || itemStack.getItem().isNull()) continue;
            this.C.v(itemStack, -1);
        }
    }

    private int e(int n, int n2) {
        int n3 = n2 % 9;
        int n4 = (n2 - n3) / 9;
        int n5 = n % 9;
        int n6 = (n - n5) / 9;
        double d = RotationUtil.r(n3, n4, n5, n6);
        if (d < 3.0) {
            d = 0.0;
        }
        return (int)(d * 30.0);
    }

    public static int G(ChestSteal chestSteal, ItemStack itemStack) {
        return chestSteal.I(itemStack);
    }

    private void C(GuiChest guiChest) {
        Inventory inventory = guiChest.getLowerChestInventory();
        for (int i = 0; i < inventory.getSizeInventory(); ++i) {
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (itemStack.isNull() || ForgeVersion.MC_26_1.v() && itemStack.toString().contains("tile.air") || this.o.isValid(itemStack, true) || itemStack.getItem().isInstance(MappedClasses.Di) && this.c(itemStack)) continue;
            this.t.add(i);
        }
    }

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && this.L;
    }

    private void p() {
        this.D.reset();
        this.r.reset();
        this.t.clear();
        this.C.F();
        GuiChest guiChest = new GuiChest(Minecraft.currentScreen());
        if (this.I.L().booleanValue()) {
            Inventory inventory = guiChest.getLowerChestInventory();
            for (int i = 0; i < inventory.getSizeInventory(); ++i) {
                ItemStack itemStack = inventory.getStackInSlot(i);
                if (itemStack.isNull() || this.o.isValid(itemStack, true) || this.C.v(itemStack, i) || !itemStack.getItem().isNotNull() || itemStack.getItem().isInstance(MappedClasses.Di) && this.c(itemStack)) continue;
                this.t.add(i);
            }
        } else {
            this.C(guiChest);
        }
        if (this.I.L().booleanValue()) {
            this.C$src$V$1buey7d();
            this.t.addAll(this.C.Y());
        }
        if (this.U.L().booleanValue() && this.U.L().booleanValue()) {
            Collections.shuffle(this.t);
            try {
                this.t.sort(new ChestStealSlotDistanceComparator(this.P));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        this.K = true;
    }

    private boolean c(ItemStack itemStack) {
        if (itemStack.getItem().isNotNull() && itemStack.getItem().isInstance(MappedClasses.Di)) {
            ItemSplashPotion itemSplashPotion = new ItemSplashPotion(itemStack.getItem().getObject());
            for (Object e : itemSplashPotion.getRawPotionEffects(itemStack)) {
                PotionEffect potionEffect = new PotionEffect(e);
                if (potionEffect.C() != 19 && potionEffect.C() != 7 && potionEffect.C() != 2 && potionEffect.C() != 18 && potionEffect.C() != 15) continue;
                return true;
            }
        }
        return false;
    }

    public ChestSteal() {
        super("ChestSteal", -208, Category.m, "Take items upon opening a chest");
        this.k = BooleanValue.create(this, "Keep open", false, "Keep chest open after clearing");
        this.U = BooleanValue.create(this, "Shuffle", false, "Take items in a random order");
        this.s = RandomValue.C(this, "Click delay", "#", "", 50.0, 150.0, 200.0, 300.0, 5.0);
        this.o = LimitValue.n(this, "cheatsteal-blacklisted", "Blacklisted", LimitValue.G, Collections.emptyList());
        this.r = new TimerUtil();
        this.t = new ArrayList();
        this.C = new ChestStealBestSlotTracker(this);
        this.D = new TimerUtil();
        this.H = new TimerUtil();
        this.F = new TimerUtil();
        this.addValue(this.O, this.I, this.k, this.U, this.s, this.o);
        if (ForgeVersion.MC_1_16_5.d()) {
            this.c = new int[]{633, 629, 625, 641, 645, 637};
            this.J = new int[]{623, 627, 631, 635, 639, 643};
            this.j = new int[]{622, 630, 626, 634, 638, 642};
            this.S = new int[]{624, 632, 628, 636, 640, 644};
        } else {
            this.c = new int[]{313, 309, 317, 305, 301};
            this.J = new int[]{311, 307, 315, 303, 299};
            this.j = new int[]{310, 306, 314, 302, 298};
            this.S = new int[]{312, 308, 316, 304, 300};
        }
    }

    @EventHandler
    public void b(EventWindowClick eventWindowClick) {
        if (!this.p) {
            if (!this.F.hasTimeElapsed(100L)) {
                eventWindowClick.setCancelled(true);
            }
            this.H.reset();
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (Vape.INSTANCE.getModManager().N(ChestSteal.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.L = false;
            return;
        }
        if (Minecraft.currentScreen().isNull() || !Minecraft.currentScreen().isInstance(MappedClasses.qs)) {
            this.G();
            return;
        }
        if (Minecraft.thePlayer().isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        if (RotationUtil.Z().isNotNull()) {
            return;
        }
        if (!this.H.hasTimeElapsed(150L)) {
            return;
        }
        if (this.b) {
            if (this.r.hasTimeElapsed(RandomUtil.i(this.s) + 200) && this.D.hasTimeElapsed(RandomUtil.i(this.s) + 200)) {
                if (Minecraft.currentScreen().isNotNull()) {
                    Minecraft.thePlayer().Z$src$V$1ie832h();
                }
                this.G();
            }
            return;
        }
        GuiChest guiChest = new GuiChest(Minecraft.currentScreen());
        if (this.O.L().booleanValue() && !this.p(guiChest)) {
            this.G();
            return;
        }
        if (!this.k.L().booleanValue() && this.e()) {
            this.b = true;
            this.r.reset();
            this.D.reset();
            this.L = false;
            return;
        }
        if (!this.K) {
            this.p();
            return;
        }
        if (!this.t.isEmpty()) {
            int n = this.t.get(0);
            int n2 = this.e(this.P, n);
            if (this.r.hasTimeElapsed(RandomUtil.i(this.s) + n2) && this.D.hasTimeElapsed(RandomUtil.i(this.s) + 100)) {
                this.L = true;
                this.P = n;
                Slot slot = guiChest.getInventorySlots().getSlot(n);
                if (slot.v()) {
                    this.p = true;
                    this.F.reset();
                    Minecraft.playerController().O(guiChest.getInventorySlots().getWindowId(), n, 0, 1, Minecraft.thePlayer());
                    this.p = false;
                }
                this.r.reset();
                this.t.remove(0);
            }
        } else {
            this.L = false;
            if (!this.k.L().booleanValue()) {
                this.b = true;
                this.r.reset();
            }
            this.P = -1;
        }
    }

    private boolean e() {
        Container container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        for (int i = 9; i <= 44; ++i) {
            ItemStack itemStack = container.getSlot(i).I();
            if (!itemStack.isNull() && (!ForgeVersion.MC_26_1.v() || !itemStack.toString().contains("tile.air"))) continue;
            return false;
        }
        return true;
    }

    private boolean p(GuiChest guiChest) {
        Inventory inventory = guiChest.getLowerChestInventory();
        String string = guiChest.z();
        if (ForgeVersion.MC_1_16_5.d()) {
            String string2 = ChestStealInventoryState.v("container.chest", new Object[0]).C().toLowerCase();
            String string3 = ChestStealInventoryState.v("container.chestDouble", new Object[0]).C().toLowerCase();
            return string.equalsIgnoreCase(string2) || string.equalsIgnoreCase(string3);
        }
        String string4 = ChestStealInventoryState.v("container.chest", new Object[0]).a().toLowerCase();
        String string5 = ChestStealInventoryState.v("container.chestDouble", new Object[0]).a().toLowerCase();
        return !inventory.hasCustomInventoryName() || string.equalsIgnoreCase(string4) || string.equalsIgnoreCase(string5);
    }

    private void G() {
        this.D.reset();
        this.r.reset();
        this.t.clear();
        this.b = false;
        this.P = -1;
        this.L = false;
        this.K = false;
    }
}

