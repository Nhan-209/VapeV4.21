package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.inventory.InventoryClickQueue;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.inventory.InventoryActionGuard;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.module.utility.inventory.cleaner.ArmorSlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.HiddenInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterAction;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryRegistry;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class InvCleaner
extends Mod
implements InventoryActionModule {
    private boolean pendingClose;
    private final ModeValue activationMode;
    private boolean keyPressed;
    private final TimerUtil openTimer;
    private final TimerUtil clickTimer;
    private String[] helmetKeywords;
    private GuiScreen lastScreen = null;
    private static final long MODULE_ID = 6780151590972480718L;
    private String[] leggingsKeywords;
    private final InventoryActionGuard combatGuard;
    private final TimerUtil closeTimer;
    private final RandomValue clickDelayValue = RandomValue.create(this, "Click delay", "#", "", 50.0, 100.0, 150.0, 300.0);
    private final ModeOption onKeyMode = new ModeOption("On Key");
    private final ModeOption toggleMode = new ModeOption("Toggle");
    private final List<Integer> touchedSlots;
    private String[] chestplateKeywords;
    private boolean needsScan = false;
    private boolean forceClose = false;
    private final BooleanValue combatCheckValue;
    private final InventoryCleanerProfileValue profileValue;
    private boolean idle = false;
    private String[] bootsKeywords;
    private final BooleanValue openInventoryValue;
    private final Queue<InventoryClick> clickQueue;

    private boolean processClickQueue() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (guiScreen.isNotNull() && guiScreen.isInstance(MappedClasses.YS) && !this.clickQueue.isEmpty()) {
            if (!this.openTimer.hasTimeElapsed(200 + ThreadLocalRandom.current().nextInt(200))) {
                return true;
            }
            if (this.clickTimer.hasTimeElapsed((long)this.clickDelayValue.B())) {
                InventoryClick inventoryClick = this.clickQueue.poll();
                if (inventoryClick != null) {
                    inventoryClick.k();
                }
                this.clickTimer.reset();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && !this.clickQueue.isEmpty() && (this.openInventoryValue.L() != false || Minecraft.currentScreen().isNull());
    }

    @Override
    public boolean X() {
        return this.activationMode.K() == this.onKeyMode;
    }

    private Collection<SlotInventoryFilterRule> buildSlotRules() {
        ArrayList<SlotInventoryFilterRule> arrayList = new ArrayList<SlotInventoryFilterRule>(((InventoryCleanerProfile)this.profileValue.K()).P());
        if (((InventoryCleanerProfile)this.profileValue.K()).U.o()) {
            arrayList.add(new ArmorSlotInventoryFilterRule(0));
            arrayList.add(new ArmorSlotInventoryFilterRule(1));
            arrayList.add(new ArmorSlotInventoryFilterRule(2));
            arrayList.add(new ArmorSlotInventoryFilterRule(3));
        }
        return arrayList;
    }

    private boolean isSlotEmpty(Slot slot) {
        ItemStack itemStack = slot.I();
        return itemStack.isNull();
    }

    private boolean isTargetSlotEmpty(SlotInventoryFilterRule slotInventoryFilterRule) {
        Container container;
        Wrapper wrapper;
        if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            wrapper = new GuiContainer(Minecraft.currentScreen());
            container = ((GuiContainer)wrapper).getInventorySlots();
        } else {
            container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        }
        if (slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule) {
            wrapper = container.getSlot(slotInventoryFilterRule.b());
            return ((Slot)wrapper).I().isNull();
        }
        for (int i = 0; i < 9; ++i) {
            Slot slot = container.getSlot(36 + i);
            if (!slot.I().isNull()) continue;
            return i == slotInventoryFilterRule.m();
        }
        return false;
    }

    private boolean C$src$Z$1v3h936() {
        if (Vape.INSTANCE.getModManager().N(InvCleaner.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.clickQueue.clear();
            this.clickTimer.reset();
            this.touchedSlots.clear();
            return true;
        }
        if (Minecraft.thePlayer().C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode()) {
            return true;
        }
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.profileValue.K();
        return inventoryCleanerProfile == null;
    }

    private void h$src$V$1vntmy3() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (RotationUtil.Z().isNotNull()) {
            this.clickQueue.clear();
            InventoryClickQueue.Q(0, this.clickQueue);
            return;
        }
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.profileValue.K();
        Container container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        if (inventoryCleanerProfile.Q().isEmpty()) {
            return;
        }
        ArrayList<Slot> arrayList = new ArrayList<Slot>();
        for (Slot object : container.getInventorySlots()) {
            arrayList.add(object);
        }
        LinkedHashMap<ItemInventoryFilterRule, List<Slot>> linkedHashMap = new LinkedHashMap<>();
        for (Slot slot : arrayList) {
            SlotInventoryFilterRule slotInventoryFilterRule;
            ItemInventoryFilterRule itemInventoryFilterRule;
            ItemStack itemStack = slot.I();
            if (itemStack.isNull() || itemStack.getItem().isNull() || (itemInventoryFilterRule = inventoryCleanerProfile.e(itemStack)) == null || !itemInventoryFilterRule.q(itemStack) || slot.g() >= 36 && slot.g() <= 44 && (slotInventoryFilterRule = inventoryCleanerProfile.I(slot.g() - 36)).q().h(itemStack) && slotInventoryFilterRule.q(itemStack) || this.touchedSlots.contains(slot.g())) continue;
            if (itemInventoryFilterRule.K() == InventoryFilterAction.REMOVE) {
                InventoryClickQueue.a(slot.g(), container.getWindowId(), this.clickQueue);
                continue;
            }
            if (itemInventoryFilterRule.K() != InventoryFilterAction.CONDENSE) continue;
            linkedHashMap.computeIfAbsent(itemInventoryFilterRule, InvCleaner::lambda$getFilterClicks$1).add(slot);
        }
        this.condenseInventory(linkedHashMap, container.getWindowId());
    }

    private boolean A$src$Z$1v2dnwg() {
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
            return true;
        }
        return false;
    }

    private int getSlotItemValue(int n) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().isNotNull() ? entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().L() : 999;
    }

    private boolean handlePendingClose() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (!this.clickQueue.isEmpty() && this.keyPressed && (guiScreen.isNull() || !guiScreen.isInstance(MappedClasses.YS))) {
            this.forceClose = true;
            this.finishManaging(false);
            return true;
        }
        return false;
    }

    public InvCleaner() {
        super("InventoryManager", (int)MODULE_ID, Category.M, "Manage your inventory");
        this.activationMode = ModeValue.create((Object)this, "Activation", this.onKeyMode, this.onKeyMode, this.toggleMode);
        this.openInventoryValue = BooleanValue.create(this, "Open inventory", true, "If on, inventory will automatically be opened when inventory needs to be managed\nIf off, inventory will only be managed after inventory is manually opened");
        this.combatCheckValue = BooleanValue.create(this, "Combat check", false);
        this.profileValue = InventoryCleanerProfileValue.Q(this, "Inventory");
        this.clickQueue = new ConcurrentLinkedQueue<InventoryClick>();
        this.touchedSlots = new ArrayList<Integer>();
        this.clickTimer = new TimerUtil();
        this.openTimer = new TimerUtil();
        this.closeTimer = new TimerUtil();
        this.combatGuard = new InventoryActionGuard(20);
        this.activationMode.L(this.openInventoryValue, this.toggleMode);
        this.activationMode.L(this.combatCheckValue, this.toggleMode);
        this.addValue(this.activationMode, this.openInventoryValue, this.combatCheckValue, this.clickDelayValue, this.profileValue);
        this.helmetKeywords = new String[]{"cap", "helmet"};
        this.chestplateKeywords = new String[]{"tunic", "chestplate"};
        this.leggingsKeywords = new String[]{"pants", "leggings"};
        this.bootsKeywords = new String[]{"boots"};
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static List<Slot> lambda$getFilterClicks$1(ItemInventoryFilterRule itemInventoryFilterRule) {
        return new ArrayList<>();
    }

    @Override
    public void onEnable() {
        this.reset();
        if (this.activationMode.K() == this.onKeyMode) {
            this.needsScan = true;
        }
    }

    private void validateRotationItem() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (RotationUtil.Z().isNull()) {
            return;
        }
        if (this.A$src$Z$1v2dnwg()) {
            return;
        }
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (guiScreen.isNull()) {
            return;
        }
        GuiContainer guiContainer = new GuiContainer(guiScreen);
        Container container = guiContainer.getInventorySlots();
        ItemStack itemStack = RotationUtil.Z();
        boolean foundBetter = false;
        for (SlotInventoryFilterRule slotInventoryFilterRule : this.buildSlotRules()) {
            int comparison;
            if (!slotInventoryFilterRule.q().h(itemStack) || !slotInventoryFilterRule.q(itemStack)) continue;
            Slot slot = container.getSlot(slotInventoryFilterRule.b());
            Comparator<ItemStack> comparator = slotInventoryFilterRule.o().j();
            if (slotInventoryFilterRule.o().equals(InventoryItemCategoryRegistry.m) || comparator != null && (comparison = comparator.compare(slot.I(), itemStack)) >= 0) continue;
            foundBetter = true;
            break;
        }
        if (!foundBetter) {
            this.clickQueue.clear();
            InventoryClickQueue.Q(0, this.clickQueue);
            return;
        }
    }

    private void condenseInventory(Map<ItemInventoryFilterRule, List<Slot>> map, int n) {
        for (Map.Entry<ItemInventoryFilterRule, List<Slot>> entry : map.entrySet()) {
            ItemInventoryFilterRule itemInventoryFilterRule = entry.getKey();
            List<Slot> list = entry.getValue();
            list.removeIf(InvCleaner::lambda$condenseInventory$2);
            if (list.size() <= 1) continue;
            list.sort(InvCleaner::lambda$condenseInventory$3);
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            block1: for (int i = 0; i < list.size(); ++i) {
                Slot slot = list.get(i);
                ItemStack itemStack = slot.I();
                int count1 = itemStack.t();
                if (arrayList.contains(slot.g())) continue;
                for (int j = i + 1; j < list.size(); ++j) {
                    Slot slot2 = list.get(j);
                    if (slot.g() == slot2.g()) continue;
                    ItemStack itemStack2 = slot2.I();
                    int count2 = itemStack2.t();
                    if (!itemStack.getItem().equals(itemStack2.getItem())) continue;
                    int combinedCount = count1 + count2;
                    int maxStackSize = itemStack.P();
                    if (combinedCount <= maxStackSize) {
                        arrayList.add(slot2.g());
                        InventoryClickQueue.V(slot2.g(), slot.g(), n, this.clickQueue);
                        continue block1;
                    }
                    int remainder = maxStackSize - count1;
                    InventoryClickQueue.e(slot2.g(), slot.g(), n, this.clickQueue);
                    InventoryClickQueue.e(slot.g(), slot2.g(), n, this.clickQueue);
                    InventoryClickQueue.e(slot2.g(), slot.g(), n, this.clickQueue);
                    if (remainder == 0) continue block1;
                }
            }
        }
    }

    @Override
    public void onDisable() {
        this.reset();
    }

    private int findBestSourceSlot(int targetSlot, boolean tieBreak) {
        int bestSlot = -1;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(targetSlot).I();
        double score = 0.0;
        double itemValue = 999.0;
        if (itemStack.isNotNull()) {
            score = ItemStackScoreUtil.L(itemStack);
            itemValue = this.getSlotItemValue(targetSlot);
        }
        double bestScore = score;
        double bestValue = itemValue;
        for (int i = 9; i < 45; ++i) {
            ItemStack itemStack2;
            if (!entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I().isNotNull() || this.getArmorSlotForItem(itemStack2 = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I()) == -1 || this.getArmorSlotForItem(itemStack2) != targetSlot) continue;
            double candidateScore = ItemStackScoreUtil.L(itemStack2);
            double candidateValue = this.getSlotItemValue(i);
            if (candidateScore > bestScore) {
                bestScore = candidateScore;
                bestSlot = i;
                bestValue = candidateValue;
                continue;
            }
            if (!tieBreak || candidateScore != bestScore || !(candidateValue < bestValue)) continue;
            bestValue = candidateValue;
            bestSlot = i;
        }
        return bestSlot;
    }

    private List<Slot> getOtherValid(Container container, Slot slot, SlotInventoryFilterRule slotInventoryFilterRule) {
        Item item;
        Object object;
        int targetSlot;
        int bestSlot;
        List<Slot> list = container.getInventorySlots();
        if (slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule && (bestSlot = this.findBestSourceSlot(targetSlot = slotInventoryFilterRule.b(), false)) != -1 && !this.touchedSlots.contains(bestSlot)) {
            return Collections.singletonList(list.get(bestSlot));
        }
        ArrayList<Slot> arrayList = new ArrayList<Slot>();
        ItemStack itemStack = null;
        for (int i = 9; i < list.size(); ++i) {
            ItemStack itemStack2;
            if (this.touchedSlots.contains(i) || !(itemStack2 = ((Slot)(object = container.getSlot(i))).I()).isNotNull() || !slotInventoryFilterRule.q().h(itemStack2) || !slotInventoryFilterRule.q(itemStack2)) continue;
            itemStack = itemStack2;
            arrayList.add((Slot)object);
        }
        if (itemStack != null && (item = itemStack.getItem()).isNotNull() && (object = slotInventoryFilterRule.o().j()) != null) {
            Comparator itemComparator = (Comparator)object;
            arrayList.sort((arg_0, arg_1) -> InvCleaner.compareSlotsByItemComparator(itemComparator, arg_0, arg_1));
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.C$src$Z$1v3h936()) {
            return;
        }
        if (this.handlePendingClose()) {
            return;
        }
        if (this.shouldSkipToggle()) {
            return;
        }
        if (this.pendingClose) {
            if (this.closeTimer.hasTimeElapsed(200 + ThreadLocalRandom.current().nextInt(200))) {
                this.finishManaging(true);
            }
            return;
        }
        if (this.activationMode.K() == this.toggleMode && this.combatCheckValue.L().booleanValue()) {
            this.combatGuard.i(eventPrePlayerTick.getPlayer());
            if (this.combatGuard.l()) {
                this.needsScan = false;
                this.beginClose();
                this.openTimer.reset();
                return;
            }
        }
        if (this.activationMode.K() == this.toggleMode && this.clickQueue.isEmpty()) {
            this.needsScan = true;
        }
        if (this.processClickQueue()) {
            if (this.clickQueue.isEmpty()) {
                this.computeInventoryClicks();
                this.needsScan = false;
                if (this.clickQueue.isEmpty()) {
                    this.h$src$V$1vntmy3();
                }
                if (this.clickQueue.isEmpty()) {
                    this.beginClose();
                }
            }
        } else if (!this.needsScan) {
            this.validateRotationItem();
            if (this.clickQueue.isEmpty()) {
                this.beginClose();
                return;
            }
        }
        if (this.needsScan) {
            this.computeInventoryClicks();
            this.needsScan = false;
            if (this.clickQueue.isEmpty()) {
                this.h$src$V$1vntmy3();
            }
            if (this.clickQueue.isEmpty()) {
                this.idle = true;
            }
            if (this.activationMode.K() != this.toggleMode && this.clickQueue.isEmpty()) {
                this.beginClose();
                Vape.INSTANCE.getNotificationManager().k("Inventory Manager", "No work available", 4000L);
                return;
            }
        }
        if ((this.activationMode.K() == this.onKeyMode || this.openInventoryValue.L().booleanValue()) && this.I$src$Z$1v6s0nc()) {
            return;
        }
    }

    private static int compareSlotsByItemComparator(Comparator comparator, Slot slot, Slot slot2) {
        return comparator.compare(slot.I(), slot2.I());
    }

    private void beginClose() {
        this.pendingClose = true;
        this.closeTimer.reset();
    }

    private static int lambda$condenseInventory$3(Slot slot, Slot slot2) {
        ItemStack itemStack = slot.I();
        ItemStack itemStack2 = slot2.I();
        return Integer.compare(itemStack.isNull() ? 0 : itemStack.t(), itemStack2.isNull() ? 0 : itemStack2.t());
    }

    private void reset() {
        this.clickTimer.reset();
        this.clickQueue.clear();
        this.touchedSlots.clear();
        this.pendingClose = false;
        this.keyPressed = false;
        this.forceClose = false;
    }

    @Override
    public boolean q$src$Z$12h8h4c() {
        if (this.onKeyMode.o()) {
            return false;
        }
        return super.q$src$Z$12h8h4c();
    }

    private static boolean lambda$condenseInventory$2(Slot slot) {
        ItemStack itemStack = slot.I();
        return itemStack.isNull() || itemStack.t() >= itemStack.P();
    }

    private boolean F$src$Z$1v54mv9() {
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
            InventoryClickQueue.e(i, 0, container.getWindowId(), this.clickQueue);
            return true;
        }
        return false;
    }

    @Override
    public String r() {
        if (this.combatCheckValue.L().booleanValue() && this.combatGuard.l()) {
            return ClientSettings.F + "c[In Combat]";
        }
        return super.r();
    }

    private boolean shouldSkipToggle() {
        if (this.activationMode.K() != this.toggleMode) {
            return false;
        }
        if (this.openInventoryValue.L().booleanValue()) {
            return false;
        }
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (this.lastScreen == null) {
            this.lastScreen = guiScreen;
        }
        if (!this.lastScreen.equals(guiScreen)) {
            this.idle = false;
        }
        if (this.idle) {
            return true;
        }
        this.lastScreen = guiScreen;
        return false;
    }

    private int getArmorSlotForItem(ItemStack itemStack) {
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        for (String string : this.bootsKeywords) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 8;
        }
        for (String string : this.leggingsKeywords) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 7;
        }
        for (String string : this.chestplateKeywords) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 6;
        }
        for (String string : this.helmetKeywords) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 5;
        }
        return -1;
    }

    private boolean I$src$Z$1v6s0nc() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (guiScreen.isNotNull()) {
            return false;
        }
        if (!this.clickQueue.isEmpty() && guiScreen.isNull()) {
            KeyBinding keyBinding = Minecraft.gameSettings().j();
            if (ForgeVersion.MC_1_16_5.d()) {
                KeyBindingHelper.a(keyBinding);
            } else {
                KeyBindingHelper.d(keyBinding, true);
                KeyBindingHelper.v(keyBinding, false, false);
            }
            this.keyPressed = true;
            this.openTimer.reset();
            return true;
        }
        return false;
    }

    private void computeInventoryClicks() {
        List<Slot> list;
        Wrapper wrapper;
        this.validateRotationItem();
        this.touchedSlots.clear();
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.profileValue.K();
        Container container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        for (SlotInventoryFilterRule slotInventoryFilterRule : this.buildSlotRules()) {
            Slot slot;
            if (HiddenInventoryItemMatchers.R.equals(slotInventoryFilterRule.q().c())) {
                this.touchedSlots.add(slotInventoryFilterRule.b());
            }
            if (((ItemStack)(wrapper = (slot = container.getSlot(slotInventoryFilterRule.b())).I())).isNull() || !(slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule) && (!slotInventoryFilterRule.q().h((ItemStack)wrapper) || !slotInventoryFilterRule.q((ItemStack)wrapper))) continue;
            list = this.getOtherValid(container, slot, slotInventoryFilterRule);
            if (!list.isEmpty()) {
                Slot slot2 = (Slot)list.get(0);
                if (slot2.g() != slot.g()) continue;
                this.touchedSlots.add(slotInventoryFilterRule.b());
                continue;
            }
            if (!(slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule)) continue;
            this.touchedSlots.add(slotInventoryFilterRule.b());
        }
        for (SlotInventoryFilterRule slotInventoryFilterRule : this.buildSlotRules()) {
            boolean shiftClick;
            int comparison;
            int targetSlotIndex = slotInventoryFilterRule.b();
            Slot targetSlot = container.getSlot(targetSlotIndex);
            ItemStack currentStack = targetSlot.I();
            boolean unused = false;
            List<Slot> list2 = this.getOtherValid(container, targetSlot, slotInventoryFilterRule);
            if (currentStack.isNotNull() && currentStack.getItem().isNotNull()) {
                if (slotInventoryFilterRule.q().h(currentStack) && slotInventoryFilterRule.q(currentStack)) {
                    if (list2.size() <= 1 || targetSlot.g() == list2.get(0).g()) continue;
                    Slot bestSlot = list2.get(0);
                    Comparator<ItemStack> comparator = slotInventoryFilterRule.o().j();
                    if (slotInventoryFilterRule.o().equals(InventoryItemCategoryRegistry.m)) continue;
                    if (comparator != null && (comparison = comparator.compare(targetSlot.I(), bestSlot.I())) >= 0) {
                        this.touchedSlots.add(slotInventoryFilterRule.b());
                        continue;
                    }
                }
                ItemInventoryFilterRule itemRule = inventoryCleanerProfile.e(targetSlot.I());
                if (!(slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule) && itemRule != null && itemRule.q(targetSlot.I()) && itemRule.K() == InventoryFilterAction.MOVE) {
                    InventoryClickQueue.Q(targetSlot.g(), container.getWindowId(), this.clickQueue);
                }
            }
            if (list2.isEmpty()) continue;
            Slot selectedSlot = list2.get(0);
            if (selectedSlot.equals(targetSlot)) continue;
            this.touchedSlots.add(slotInventoryFilterRule.b());
            int totalCount = 0;
            if (currentStack.isNotNull()) {
                totalCount += currentStack.P();
            }
            int swap = !this.isSlotEmpty(targetSlot) ? 1 : 0;
            boolean shiftClickFlag = shiftClick = this.isTargetSlotEmpty(slotInventoryFilterRule) && (selectedSlot.g() < 36 || slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule);
            if (shiftClick) {
                InventoryClickQueue.Q(selectedSlot.g(), container.getWindowId(), this.clickQueue);
            } else if (swap != 0) {
                InventoryClickQueue.H(selectedSlot.g(), targetSlotIndex, container.getWindowId(), this.clickQueue);
            } else {
                InventoryClickQueue.V(selectedSlot.g(), targetSlotIndex, container.getWindowId(), this.clickQueue);
            }
            this.touchedSlots.add(selectedSlot.g());
        }
    }

    public InventoryCleanerProfileValue E$src$Lgg_vape_module_utility_inventory_cleaner_Invent$199cpgr() {
        return this.profileValue;
    }

    private void finishManaging(boolean closeScreen) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ItemStack itemStack = RotationUtil.Z();
        if (!this.forceClose && itemStack.isNotNull() && this.F$src$Z$1v54mv9()) {
            this.pendingClose = false;
            return;
        }
        if (this.activationMode.K() == this.onKeyMode) {
            this.Y(false);
        }
        if (closeScreen && !Minecraft.currentScreen().isNull() && (this.activationMode.K() == this.onKeyMode || this.openInventoryValue.L().booleanValue())) {
            entityPlayerSP.Z$src$V$1ie832h();
            this.pendingClose = false;
        }
        this.clickQueue.clear();
        this.clickTimer.reset();
        this.touchedSlots.clear();
        this.pendingClose = false;
    }
}
