package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.UtilityMod;
import gg.vape.module.utility.RefillInventoryState;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Refill
extends UtilityMod
implements RefillInventoryState {
    private final Random random;
    private final TimerUtil delayTimer;
    private final ModeValue typeMode;
    private final ModeOption bothMode = new ModeOption("Both");
    private final BooleanValue hotbarClear;
    private boolean opened;
    private final BooleanValue vertical;
    private final RandomValue delay;
    private boolean finished;
    private final LimitValue allowedItems;
    private final ModeOption soupMode;
    private final Queue<InventoryClick> clickQueue;
    private final BooleanValue scatter;
    private final ModeOption potsMode = new ModeOption("Pots");

    private boolean hasHealingInInventory() {
        boolean bl = false;
        for (int i = 9; i < 36; ++i) {
            Item item;
            ItemStack itemStack = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
            if (itemStack.isNull() || (item = itemStack.getItem()).isNull() || !ItemStackScoreUtil.o(itemStack, ((ModeSelection)this.typeMode.K()).equals(this.soupMode) || ((ModeSelection)this.typeMode.K()).equals(this.bothMode))) continue;
            bl = true;
        }
        return bl;
    }

    private void processClickQueue() {
        if (this.delay.M() == 0.0) {
            while (!this.clickQueue.isEmpty()) {
                InventoryClick inventoryClick = this.clickQueue.poll();
                inventoryClick.k();
            }
            return;
        }
        if (this.delayTimer.hasTimeElapsed((long)this.delay.B())) {
            InventoryClick inventoryClick = this.clickQueue.poll();
            inventoryClick.k();
            this.delayTimer.reset();
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (!this.opened) {
            if (!Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
                KeyBinding keyBinding = Minecraft.gameSettings().j();
                if (ForgeVersion.MC_1_16_5.d()) {
                    KeyBindingHelper.a(keyBinding);
                } else {
                    KeyBindingHelper.d(keyBinding, true);
                    KeyBindingHelper.v(keyBinding, false, false);
                }
            } else {
                this.opened = true;
            }
            return;
        }
        if (this.finished) {
            if (!Minecraft.currentScreen().isNull()) {
                Minecraft.thePlayer().Z$src$V$1ie832h();
            }
            this.finished = false;
            this.clickQueue.clear();
            this.Y(false);
            return;
        }
        if (!Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            this.finished = true;
            return;
        }
        if (!this.clickQueue.isEmpty()) {
            this.processClickQueue();
            return;
        }
        this.buildRefillClicks();
        if (this.clickQueue.isEmpty()) {
            this.finished = true;
        }
    }

    private boolean isJunk(Item item, ItemStack itemStack, List<ItemStack> list) {
        if (ItemStackScoreUtil.o(itemStack, ((ModeSelection)this.typeMode.K()).equals(this.soupMode) || ((ModeSelection)this.typeMode.K()).equals(this.bothMode))) {
            return false;
        }
        if (this.allowedItems.isValid(itemStack, true)) {
            if (!this.hasDuplicate(itemStack, list)) {
                list.add(itemStack);
                return false;
            }
            return true;
        }
        return true;
    }

    private void queueClick(int n, int n2, int n3, int n4) {
        this.clickQueue.add(new InventoryClick(n, n2, n3, n4));
    }

    public Refill() {
        super("Refill", Category.M, "Refills your hotbar with healing items.");
        this.soupMode = new ModeOption("Soup");
        this.typeMode = ModeValue.create((Object)this, "Type", this.bothMode, this.bothMode, this.potsMode, this.soupMode);
        this.allowedItems = LimitValue.n(this, "refill-alloweditems", "Non Junk Items", LimitValue.r, Collections.emptyList());
        this.vertical = BooleanValue.create(this, "Vertical", false);
        this.scatter = BooleanValue.create(this, "Scatter", false);
        this.hotbarClear = BooleanValue.create(this, "Hotbar clear", false, "Clears junk from your hotbar to refill.\nWhitelisted items will not be considered junk\nOnly one stack of each non-junk item is kept");
        this.delay = RandomValue.C(this, "Delay", "#", "ms", 50.0, 75.0, 125.0, 200.0, 5.0);
        this.clickQueue = new ConcurrentLinkedQueue<InventoryClick>();
        this.random = new Random();
        this.delayTimer = new TimerUtil();
        this.hotbarClear.K(this.allowedItems);
        this.addValue(this.vertical, this.scatter, this.hotbarClear, this.allowedItems, this.delay, this.typeMode);
    }

    private boolean shouldDoubleClick() {
        return this.chance(0.2);
    }

    private boolean hasDuplicate(ItemStack itemStack, List<ItemStack> list) {
        for (ItemStack itemStack2 : list) {
            if (itemStack.equals(itemStack2) || !itemStack.f().equals(itemStack2.f())) continue;
            return true;
        }
        return false;
    }

    private boolean chance(double d) {
        double d2 = Math.max(Math.min(d, 1.0), 0.0);
        return Math.random() <= d2;
    }

    private void buildRefillClicks() {
        GuiContainer guiContainer = new GuiContainer(Minecraft.currentScreen().getObject());
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ArrayList arrayList2 = new ArrayList();
        int n = 0;
        List<Integer> list = this.findJunkHotbarSlots();
        int n2 = 9;
        while (n2 < 36) {
            Item item;
            Slot slot = guiContainer.getInventorySlots().getInventorySlots().get(n2);
            Object object = slot.I();
            if (!((ItemStack)object).isNull() && !(item = ((ItemStack)object).getItem()).isNull() && ItemStackScoreUtil.o((ItemStack)object, ((ModeSelection)this.typeMode.K()).equals(this.soupMode) || ((ModeSelection)this.typeMode.K()).equals(this.bothMode))) {
                arrayList.add(n2);
            }
            if (this.vertical.L().booleanValue()) {
                n2 += 9;
                if (++n != 3) continue;
                ++n2;
                n2 -= 27;
                n = 0;
                continue;
            }
            ++n2;
        }
        if (arrayList.isEmpty()) {
            this.finished = true;
            return;
        }
        if (this.scatter.L().booleanValue()) {
            Collections.shuffle(arrayList);
        }
        for (n2 = 0; n2 < list.size() && n2 < arrayList.size(); ++n2) {
            arrayList2.add(arrayList.get(n2));
        }
        n2 = 0;
        for (Object object : arrayList2) {
            boolean bl = false;
            int n3 = 0;
            if (this.hotbarClear.L().booleanValue()) {
                Item item;
                n3 = list.get(n2);
                ItemStack itemStack = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n3);
                if (itemStack.isNotNull() && (item = itemStack.getItem()).isNotNull()) {
                    bl = true;
                }
            }
            if (bl) {
                this.queueClick(guiContainer.getInventorySlots().getWindowId(), (Integer)object, 0, 0);
                this.queueClick(guiContainer.getInventorySlots().getWindowId(), 36 + n3, 0, 0);
                this.queueClick(guiContainer.getInventorySlots().getWindowId(), (Integer)object, 0, 0);
            } else {
                this.queueClick(guiContainer.getInventorySlots().getWindowId(), (Integer)object, 0, 1);
                if (this.shouldDoubleClick()) {
                    this.queueClick(guiContainer.getInventorySlots().getWindowId(), (Integer)object, 0, 1);
                }
            }
            ++n2;
        }
    }

    private List<Integer> findJunkHotbarSlots() {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        Object[] objectArray = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().M();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = new ItemStack(objectArray[i]);
            if (itemStack.isNull()) {
                arrayList2.add(i);
                continue;
            }
            if (this.hotbarClear.L().booleanValue()) {
                if (!this.isJunk(itemStack.getItem(), itemStack, arrayList)) continue;
                arrayList2.add(i);
                continue;
            }
            if (!itemStack.toString().contains("tile.air")) continue;
            arrayList2.add(i);
        }
        return arrayList2;
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void onEnable() {
        if (Minecraft.thePlayer().isNull()) {
            this.Y(false);
            return;
        }
        if (this.findJunkHotbarSlots().size() == 0) {
            this.Y(false);
            return;
        }
        if (!this.hasHealingInInventory()) {
            this.Y(false);
        }
    }

    @Override
    public void onDisable() {
        this.opened = false;
    }
}

