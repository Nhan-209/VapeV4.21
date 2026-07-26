package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.inventory.InventoryClickAction;
import gg.vape.inventory.InventoryClickQueue;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.inventory.ArmorSlotComparator;
import gg.vape.module.utility.inventory.BowSlotComparator;
import gg.vape.module.utility.inventory.HotbarSlotRule;
import gg.vape.module.utility.inventory.HotbarSlotRuleValue;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.module.utility.inventory.InventoryManagerPrimaryItemScoreComparator;
import gg.vape.module.utility.inventory.InventoryManagerSecondaryItemScoreComparator;
import gg.vape.module.utility.inventory.PotionSlotComparator;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InventoryManager
extends Mod
implements InventoryActionModule {
    private final NumberValue Z = NumberValue.create(this, "Delay", "#", "", 0.0, 110.0, 300.0);
    private final List<Integer> k;
    private boolean V;
    private final TimerUtil H;
    private final ModeOption K;
    private final ModeValue Y;
    private final HashMap<Class, Comparator> s;
    private boolean I = true;
    private final ModeOption A;
    private final HotbarSlotRuleValue U;
    private boolean a = false;
    private final BooleanValue t;
    private final Queue<InventoryClick> r = new ConcurrentLinkedQueue<InventoryClick>();

    private boolean Z(Slot slot) {
        ItemStack itemStack = slot.I();
        if (itemStack.isNotNull()) {
            Item item = itemStack.getItem();
            return item.isNotNull();
        }
        return false;
    }

    private void S$src$V$175i26a() {
        InventoryManagerPrimaryItemScoreComparator inventoryManagerPrimaryItemScoreComparator = new InventoryManagerPrimaryItemScoreComparator(this);
        BowSlotComparator bowSlotComparator = new BowSlotComparator(this);
        InventoryManagerSecondaryItemScoreComparator inventoryManagerSecondaryItemScoreComparator = new InventoryManagerSecondaryItemScoreComparator(this);
        ArmorSlotComparator armorSlotComparator = new ArmorSlotComparator(this);
        PotionSlotComparator potionSlotComparator = new PotionSlotComparator(this);
        this.s.put(MappedClasses.V5, inventoryManagerPrimaryItemScoreComparator);
        this.s.put(MappedClasses.YP, inventoryManagerPrimaryItemScoreComparator);
        this.s.put(MappedClasses.DU, bowSlotComparator);
        this.s.put(MappedClasses.FM, bowSlotComparator);
        this.s.put(MappedClasses.Vl, inventoryManagerSecondaryItemScoreComparator);
        this.s.put(MappedClasses.Di, armorSlotComparator);
        this.s.put(MappedClasses.DL, potionSlotComparator);
    }

    private boolean m() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (!guiScreen.isInstance(MappedClasses.YS)) {
            return false;
        }
        GuiContainer guiContainer = new GuiContainer(guiScreen);
        Container container = guiContainer.getInventorySlots();
        for (int i = 9; i < 36; ++i) {
            Slot slot = container.getSlot(i);
            ItemStack itemStack = slot.I();
            if (!itemStack.isNull()) continue;
            new InventoryClickQueue(InventoryClickAction.CLICK, i, 0).h(container.getWindowId(), this.r);
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.I = false;
        this.V = false;
        this.r.clear();
        this.H.reset();
        this.k.clear();
    }

    @Override
    public boolean X() {
        return this.Y.K() == this.A;
    }

    public InventoryManager() {
        super("AutoHotbar", -6656, Category.M, "Automatically arranges hotbar to your liking.\nDoes not work in creative.");
        this.H = new TimerUtil();
        this.s = new HashMap();
        this.k = new ArrayList<Integer>();
        this.A = new ModeOption("On Key");
        this.K = new ModeOption("Toggle");
        this.Y = ModeValue.create((Object)this, "Activation", this.A, this.A, this.K);
        this.t = BooleanValue.create(this, "Open Inventory", true);
        this.R(false);
        this.U = HotbarSlotRuleValue.m(this, "hotbar");
        this.Y.L(this.t, this.K);
        this.addValue(this.Y, this.t, this.Z, this.U);
        this.S$src$V$175i26a();
    }

    private Slot o(Slot slot, HotbarSlotRule hotbarSlotRule) {
        Container container;
        Object object;
        if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            object = new GuiContainer(Minecraft.currentScreen());
            container = ((GuiContainer)object).getInventorySlots();
        } else {
            container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        }
        List<Slot> matchingSlots = new ArrayList<Slot>();
        if (hotbarSlotRule.y(slot.I())) {
            matchingSlots.add(slot);
        }
        for (Slot clazz : container.getInventorySlots()) {
            if (!hotbarSlotRule.y(clazz.I()) || this.k.contains(clazz.g()) || matchingSlots.contains(clazz)) continue;
            matchingSlots.add(clazz);
        }
        if (!matchingSlots.isEmpty()) {
            if (hotbarSlotRule.C$src$Z$deeqpc()) {
                Item item = hotbarSlotRule.i();
                Class<?> clazz = item.getObject().getClass();
                if (this.s.containsKey(clazz)) {
                    Comparator comparator = this.s.get(clazz);
                    matchingSlots.sort(comparator);
                    Collections.reverse(matchingSlots);
                    if (hotbarSlotRule.y(slot.I()) && comparator.compare(matchingSlots.get(0), slot) == 0) {
                        return null;
                    }
                }
                return matchingSlots.get(0);
            }
            return matchingSlots.get(0);
        }
        return null;
    }

    private boolean q(int n) {
        Container container;
        if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            GuiContainer guiContainer = new GuiContainer(Minecraft.currentScreen());
            container = guiContainer.getInventorySlots();
        } else {
            container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        }
        for (int i = 0; i < 9; ++i) {
            Slot slot = container.getSlot(36 + i);
            if (!slot.I().isNull()) continue;
            return i == n;
        }
        return false;
    }

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && this.r.size() > 0 && (this.t.L() != false || Minecraft.currentScreen().isNull());
    }

    @Override
    public void onEnable() {
        if (this.Y.K() == this.A) {
            this.I = true;
            this.V = false;
        } else {
            this.I = false;
            this.V = false;
        }
        this.r.clear();
        this.H.reset();
        this.k.clear();
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        Container container;
        Object object;
        if (Vape.INSTANCE.getModManager().N(InventoryManager.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.r.clear();
            this.H.reset();
            this.k.clear();
            return;
        }
        if (Minecraft.thePlayer().C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode()) {
            return;
        }
        if (this.V) {
            this.Q$src$V$174egzk();
            return;
        }
        if (Minecraft.currentScreen().isInstance(MappedClasses.Ft) && this.Y.K() == this.K && !this.t.L().booleanValue()) {
            return;
        }
        if (!Minecraft.currentScreen().isInstance(MappedClasses.YS) && (this.Y.K() == this.A || this.t.L().booleanValue() && this.I)) {
            if (this.I) {
                KeyBinding keyBinding = Minecraft.gameSettings().j();
                if (ForgeVersion.MC_1_16_5.d()) {
                    KeyBindingHelper.a(keyBinding);
                } else {
                    KeyBindingHelper.d(keyBinding, true);
                    KeyBindingHelper.v(keyBinding, false, false);
                }
            } else if (this.Y.K() == this.A) {
                this.Y(false);
            }
            return;
        }
        this.I = false;
        this.V = false;
        if (this.r.size() > 0) {
            if (this.H.hasTimeElapsed(((Double)this.Z.K()).intValue())) {
                this.r.poll().k();
                this.H.reset();
                this.a = true;
            }
            return;
        }
        if (Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
            object = new GuiContainer(Minecraft.currentScreen());
            container = ((GuiContainer)object).getInventorySlots();
        } else {
            container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        }
        List<HotbarSlotRule> hotbarSlotRules = this.U.f$src$Ljava_util_List_$5if89l();
        boolean bl = false;
        if (hotbarSlotRules.size() == 9) {
            for (int i = 0; i < 9; ++i) {
                boolean bl2;
                HotbarSlotRule hotbarSlotRule = hotbarSlotRules.get(i);
                int n = 36 + i;
                Slot slot = container.getSlot(n);
                Slot slot2 = this.o(slot, hotbarSlotRule);
                if (slot2 == null) continue;
                ItemStack itemStack = slot.I();
                ItemStack itemStack2 = slot2.I();
                if (slot2.equals(slot)) {
                    List<Slot> list;
                    this.k.add(n);
                    if (itemStack.isNotNull() && itemStack.t() < itemStack.P() && !(list = this.a(container, slot, hotbarSlotRule)).isEmpty()) {
                        slot2 = list.get(0);
                    }
                }
                if (slot2.equals(slot)) continue;
                if (this.t.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
                    this.I = true;
                    this.V = false;
                    return;
                }
                this.k.add(n);
                this.k.add(slot2.g());
                int n2 = 0;
                if (itemStack.isNotNull()) {
                    n2 += itemStack.P();
                }
                boolean bl3 = !this.Z(slot);
                boolean bl4 = bl2 = this.q(i) && slot2.g() < 36;
                new InventoryClickQueue(bl2 ? InventoryClickAction.SHIFTCLICK : (bl3 ? InventoryClickAction.SWAP : InventoryClickAction.MOVE), slot2.g(), n).h(container.getWindowId(), this.r);
                if ((n2 += itemStack2.t()) > itemStack2.P()) {
                    new InventoryClickQueue(InventoryClickAction.CLICK, slot2.g(), n).h(container.getWindowId(), this.r);
                }
                bl = true;
                break;
            }
        }
        if (!bl && this.Y.K() == this.A) {
            this.V = true;
            this.H.reset();
        }
        if (this.a && this.Y.K() == this.K && this.t.L().booleanValue() && this.r.isEmpty()) {
            this.V = true;
        }
    }

    private void Q$src$V$174egzk() {
        ItemStack itemStack = RotationUtil.Z();
        if (itemStack.isNotNull() && this.m()) {
            this.V = false;
            return;
        }
        if (this.Y.K() == this.A) {
            this.Y(false);
        }
        if (!Minecraft.currentScreen().isNull()) {
            Minecraft.thePlayer().Z$src$V$1ie832h();
            this.V = false;
        }
        this.r.clear();
        this.H.reset();
        this.k.clear();
        this.V = false;
        this.a = false;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private List<Slot> a(Container container, Slot slot, HotbarSlotRule hotbarSlotRule) {
        List<Slot> list = container.getInventorySlots();
        ArrayList<Slot> arrayList = new ArrayList<Slot>();
        ItemStack itemStack = slot.I();
        if (itemStack.isNotNull()) {
            for (int i = 9; i < list.size(); ++i) {
                Slot slot2 = container.getSlot(i);
                ItemStack itemStack2 = slot2.I();
                if (!itemStack2.isNotNull() || !hotbarSlotRule.y(itemStack2) || this.k.contains(i)) continue;
                arrayList.add(slot2);
            }
        }
        return arrayList;
    }
}
