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
    private final NumberValue delayMs = NumberValue.create(this, "Delay", "#", "", 0.0, 110.0, 300.0);
    private final List<Integer> touchedSlots;
    private boolean pendingClose;
    private final TimerUtil clickTimer;
    private final ModeOption toggleMode;
    private final ModeValue activationMode;
    private final HashMap<Class, Comparator> comparators;
    private boolean pendingOpen = true;
    private final ModeOption onKeyMode;
    private final HotbarSlotRuleValue hotbarRules;
    private boolean didClick = false;
    private final BooleanValue openInventoryOption;
    private final Queue<InventoryClick> clickQueue = new ConcurrentLinkedQueue<InventoryClick>();

    private boolean hasItem(Slot slot) {
        ItemStack itemStack = slot.I();
        if (itemStack.isNotNull()) {
            Item item = itemStack.getItem();
            return item.isNotNull();
        }
        return false;
    }

    private void registerComparators() {
        InventoryManagerPrimaryItemScoreComparator inventoryManagerPrimaryItemScoreComparator = new InventoryManagerPrimaryItemScoreComparator(this);
        BowSlotComparator bowSlotComparator = new BowSlotComparator(this);
        InventoryManagerSecondaryItemScoreComparator inventoryManagerSecondaryItemScoreComparator = new InventoryManagerSecondaryItemScoreComparator(this);
        ArmorSlotComparator armorSlotComparator = new ArmorSlotComparator(this);
        PotionSlotComparator potionSlotComparator = new PotionSlotComparator(this);
        this.comparators.put(MappedClasses.V5, inventoryManagerPrimaryItemScoreComparator);
        this.comparators.put(MappedClasses.YP, inventoryManagerPrimaryItemScoreComparator);
        this.comparators.put(MappedClasses.DU, bowSlotComparator);
        this.comparators.put(MappedClasses.FM, bowSlotComparator);
        this.comparators.put(MappedClasses.Vl, inventoryManagerSecondaryItemScoreComparator);
        this.comparators.put(MappedClasses.Di, armorSlotComparator);
        this.comparators.put(MappedClasses.DL, potionSlotComparator);
    }

    private boolean queueEmptyHotbarClick() {
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
            new InventoryClickQueue(InventoryClickAction.CLICK, i, 0).h(container.getWindowId(), this.clickQueue);
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.pendingOpen = false;
        this.pendingClose = false;
        this.clickQueue.clear();
        this.clickTimer.reset();
        this.touchedSlots.clear();
    }

    @Override
    public boolean X() {
        return this.activationMode.K() == this.onKeyMode;
    }

    public InventoryManager() {
        super("AutoHotbar", -6656, Category.M, "Automatically arranges hotbar to your liking.\nDoes not work in creative.");
        this.clickTimer = new TimerUtil();
        this.comparators = new HashMap();
        this.touchedSlots = new ArrayList<Integer>();
        this.onKeyMode = new ModeOption("On Key");
        this.toggleMode = new ModeOption("Toggle");
        this.activationMode = ModeValue.create((Object)this, "Activation", this.onKeyMode, this.onKeyMode, this.toggleMode);
        this.openInventoryOption = BooleanValue.create(this, "Open Inventory", true);
        this.R(false);
        this.hotbarRules = HotbarSlotRuleValue.m(this, "hotbar");
        this.activationMode.L(this.openInventoryOption, this.toggleMode);
        this.addValue(this.activationMode, this.openInventoryOption, this.delayMs, this.hotbarRules);
        this.registerComparators();
    }

    private Slot findBestMatchingSlot(Slot slot, HotbarSlotRule hotbarSlotRule) {
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
            if (!hotbarSlotRule.y(clazz.I()) || this.touchedSlots.contains(clazz.g()) || matchingSlots.contains(clazz)) continue;
            matchingSlots.add(clazz);
        }
        if (!matchingSlots.isEmpty()) {
            if (hotbarSlotRule.C$src$Z$deeqpc()) {
                Item item = hotbarSlotRule.i();
                Class<?> clazz = item.getObject().getClass();
                if (this.comparators.containsKey(clazz)) {
                    Comparator comparator = this.comparators.get(clazz);
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

    private boolean isFirstEmptyHotbarSlot(int n) {
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
        return this.r$src$Z$14eylz9() && this.clickQueue.size() > 0 && (this.openInventoryOption.L() != false || Minecraft.currentScreen().isNull());
    }

    @Override
    public void onEnable() {
        if (this.activationMode.K() == this.onKeyMode) {
            this.pendingOpen = true;
            this.pendingClose = false;
        } else {
            this.pendingOpen = false;
            this.pendingClose = false;
        }
        this.clickQueue.clear();
        this.clickTimer.reset();
        this.touchedSlots.clear();
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        Container container;
        Object object;
        if (Vape.INSTANCE.getModManager().N(InventoryManager.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.clickQueue.clear();
            this.clickTimer.reset();
            this.touchedSlots.clear();
            return;
        }
        if (Minecraft.thePlayer().C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode()) {
            return;
        }
        if (this.pendingClose) {
            this.handleClosing();
            return;
        }
        if (Minecraft.currentScreen().isInstance(MappedClasses.Ft) && this.activationMode.K() == this.toggleMode && !this.openInventoryOption.L().booleanValue()) {
            return;
        }
        if (!Minecraft.currentScreen().isInstance(MappedClasses.YS) && (this.activationMode.K() == this.onKeyMode || this.openInventoryOption.L().booleanValue() && this.pendingOpen)) {
            if (this.pendingOpen) {
                KeyBinding keyBinding = Minecraft.gameSettings().j();
                if (ForgeVersion.MC_1_16_5.d()) {
                    KeyBindingHelper.a(keyBinding);
                } else {
                    KeyBindingHelper.d(keyBinding, true);
                    KeyBindingHelper.v(keyBinding, false, false);
                }
            } else if (this.activationMode.K() == this.onKeyMode) {
                this.Y(false);
            }
            return;
        }
        this.pendingOpen = false;
        this.pendingClose = false;
        if (this.clickQueue.size() > 0) {
            if (this.clickTimer.hasTimeElapsed(((Double)this.delayMs.K()).intValue())) {
                this.clickQueue.poll().k();
                this.clickTimer.reset();
                this.didClick = true;
            }
            return;
        }
        if (Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
            object = new GuiContainer(Minecraft.currentScreen());
            container = ((GuiContainer)object).getInventorySlots();
        } else {
            container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        }
        List<HotbarSlotRule> hotbarSlotRules = this.hotbarRules.f$src$Ljava_util_List_$5if89l();
        boolean bl = false;
        if (hotbarSlotRules.size() == 9) {
            for (int i = 0; i < 9; ++i) {
                boolean bl2;
                HotbarSlotRule hotbarSlotRule = hotbarSlotRules.get(i);
                int n = 36 + i;
                Slot slot = container.getSlot(n);
                Slot slot2 = this.findBestMatchingSlot(slot, hotbarSlotRule);
                if (slot2 == null) continue;
                ItemStack itemStack = slot.I();
                ItemStack itemStack2 = slot2.I();
                if (slot2.equals(slot)) {
                    List<Slot> list;
                    this.touchedSlots.add(n);
                    if (itemStack.isNotNull() && itemStack.t() < itemStack.P() && !(list = this.findOverflowSlots(container, slot, hotbarSlotRule)).isEmpty()) {
                        slot2 = list.get(0);
                    }
                }
                if (slot2.equals(slot)) continue;
                if (this.openInventoryOption.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
                    this.pendingOpen = true;
                    this.pendingClose = false;
                    return;
                }
                this.touchedSlots.add(n);
                this.touchedSlots.add(slot2.g());
                int n2 = 0;
                if (itemStack.isNotNull()) {
                    n2 += itemStack.P();
                }
                boolean bl3 = !this.hasItem(slot);
                boolean bl4 = bl2 = this.isFirstEmptyHotbarSlot(i) && slot2.g() < 36;
                new InventoryClickQueue(bl2 ? InventoryClickAction.SHIFTCLICK : (bl3 ? InventoryClickAction.SWAP : InventoryClickAction.MOVE), slot2.g(), n).h(container.getWindowId(), this.clickQueue);
                if ((n2 += itemStack2.t()) > itemStack2.P()) {
                    new InventoryClickQueue(InventoryClickAction.CLICK, slot2.g(), n).h(container.getWindowId(), this.clickQueue);
                }
                bl = true;
                break;
            }
        }
        if (!bl && this.activationMode.K() == this.onKeyMode) {
            this.pendingClose = true;
            this.clickTimer.reset();
        }
        if (this.didClick && this.activationMode.K() == this.toggleMode && this.openInventoryOption.L().booleanValue() && this.clickQueue.isEmpty()) {
            this.pendingClose = true;
        }
    }

    private void handleClosing() {
        ItemStack itemStack = RotationUtil.Z();
        if (itemStack.isNotNull() && this.queueEmptyHotbarClick()) {
            this.pendingClose = false;
            return;
        }
        if (this.activationMode.K() == this.onKeyMode) {
            this.Y(false);
        }
        if (!Minecraft.currentScreen().isNull()) {
            Minecraft.thePlayer().Z$src$V$1ie832h();
            this.pendingClose = false;
        }
        this.clickQueue.clear();
        this.clickTimer.reset();
        this.touchedSlots.clear();
        this.pendingClose = false;
        this.didClick = false;
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private List<Slot> findOverflowSlots(Container container, Slot slot, HotbarSlotRule hotbarSlotRule) {
        List<Slot> list = container.getInventorySlots();
        ArrayList<Slot> arrayList = new ArrayList<Slot>();
        ItemStack itemStack = slot.I();
        if (itemStack.isNotNull()) {
            for (int i = 9; i < list.size(); ++i) {
                Slot slot2 = container.getSlot(i);
                ItemStack itemStack2 = slot2.I();
                if (!itemStack2.isNotNull() || !hotbarSlotRule.y(itemStack2) || this.touchedSlots.contains(i)) continue;
                arrayList.add(slot2);
            }
        }
        return arrayList;
    }
}
