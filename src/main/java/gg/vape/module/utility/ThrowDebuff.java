package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.UtilityMod;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.datas.ItemStackData;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class ThrowDebuff
extends UtilityMod {
    private TimerUtil scrollTimer;
    private final BooleanValue scroll;
    private final NumberValue scrollDelay;
    private boolean throwing = false;
    private final BooleanValue[] debuffValues;
    private final RandomValue delay;
    int savedSlot = 0;
    private final Queue<ItemStackData> itemsToThrow = new ArrayDeque<ItemStackData>();
    private TimerUtil delayTimer;
    private final ModeOption oneOfEachOption;
    private final ModeValue mode;
    private final ModeOption allOption = new ModeOption("All");
    private final ModeOption firstOption;

    @Override
    public void onDisable() {
        this.throwing = false;
        this.itemsToThrow.clear();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        ItemStackData itemStackData;
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        if (this.throwing) {
            KeyBindingHelper.v(keyBinding, false, false);
            this.throwing = false;
            return;
        }
        if (this.itemsToThrow.isEmpty()) {
            if (this.selectHotbarSlotIncrementally(this.savedSlot)) {
                this.Y(false);
            }
            return;
        }
        if (this.delayTimer.hasTimeElapsed((long)this.delay.B()) && this.selectHotbarSlotIncrementally((itemStackData = this.itemsToThrow.peek()).Y())) {
            KeyBindingHelper.v(keyBinding, true, true);
            this.throwing = true;
            this.delayTimer.reset();
            this.itemsToThrow.poll();
        }
    }

    private boolean collectDebuffs() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 9; ++i) {
            arrayList.add(i);
        }
        Object[] objectArray = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().M();
        ArrayList<BooleanValue> arrayList2 = new ArrayList<BooleanValue>();
        block1: for (Integer n : arrayList) {
            Item item;
            ItemStack itemStack = new ItemStack(objectArray[n]);
            if (itemStack.isNull() || (item = itemStack.getItem()).isNull() || !MappedClasses.Di.isInstance(item.getObject())) continue;
            ItemSplashPotion itemSplashPotion = new ItemSplashPotion(item.getObject());
            for (BooleanValue booleanValue : this.debuffValues) {
                if (((ModeSelection)this.mode.K()).equals(this.oneOfEachOption) && arrayList2.contains(booleanValue)) continue;
                String string = itemSplashPotion.getItemStackDisplayName(itemStack).toLowerCase();
                String string2 = booleanValue.getName().toLowerCase();
                if (!booleanValue.L().booleanValue() || !string.contains(string2)) continue;
                this.itemsToThrow.add(new ItemStackData(n, itemStack));
                arrayList2.add(booleanValue);
                if (!((ModeSelection)this.mode.K()).equals(this.firstOption)) continue block1;
                break block1;
            }
        }
        return !this.itemsToThrow.isEmpty();
    }


    @Override
    public void q() {
    }

    public ThrowDebuff() {
        super("ThrowDebuff", Category.M, "");
        this.oneOfEachOption = new ModeOption("One of each");
        this.firstOption = new ModeOption("First");
        this.mode = ModeValue.create((Object)this, "Mode", "All - Throws all debuffs on hotbar\nOne of each - Throws one of each debuff\nFirst - Throws only first debuff on hotbar", (ModeSelection)this.oneOfEachOption, this.allOption, this.oneOfEachOption, this.firstOption);
        this.debuffValues = new BooleanValue[]{BooleanValue.create(this, "Harming", true), BooleanValue.create(this, "Weakness", true), BooleanValue.create(this, "Poison", true), BooleanValue.create(this, "Slowness", true)};
        this.delay = RandomValue.create(this, "Delay", "#.#", "", 0.0, 70.0, 120.0, 200.0);
        this.scroll = BooleanValue.create(this, "Scroll", false);
        this.scrollDelay = NumberValue.create(this, "Scroll delay", "#", "ms", 0.0, 100.0, 200.0);
        this.delayTimer = new TimerUtil();
        this.scrollTimer = new TimerUtil();
        this.R(false);
        this.addValue(this.mode);
        for (BooleanValue booleanValue : this.debuffValues) {
            this.addValue(booleanValue);
        }
        this.addValue(this.delay);
        this.scroll.K(this.scrollDelay);
        this.addValue(this.scroll);
        this.addValue(this.scrollDelay);
    }

    @Override
    public void onEnable() {
        if (this.collectDebuffs()) {
            this.savedSlot = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        } else {
            this.Y(false);
        }
    }

    private boolean selectHotbarSlotIncrementally(int n) {
        if (!this.scroll.L().booleanValue()) {
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            return true;
        }
        if (!this.scrollTimer.hasTimeElapsed(((Double)this.scrollDelay.K()).longValue())) {
            return false;
        }
        int n2 = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        if (n > n2) {
            ++n2;
        } else if (n < n2) {
            --n2;
        } else {
            this.scrollTimer.reset();
            return true;
        }
        Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n2);
        return false;
    }
}

