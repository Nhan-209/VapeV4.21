package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.UtilityMod;
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private TimerUtil s;
    private final BooleanValue Y;
    private final NumberValue F;
    private boolean I = false;
    private final BooleanValue[] V;
    private final RandomValue A;
    int P = 0;
    private final Queue<ItemStackData> k = new ArrayDeque<ItemStackData>();
    private TimerUtil Z;
    private final ModeOption v;
    private final ModeValue p;
    private final ModeOption H = new ModeOption("All");
    private final ModeOption D;

    @Override
    public void onDisable() {
        this.I = false;
        this.k.clear();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        ItemStackData itemStackData;
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        if (this.I) {
            KeyBindingHelper.v(keyBinding, false, false);
            this.I = false;
            return;
        }
        if (this.k.isEmpty()) {
            if (this.selectHotbarSlotIncrementally(this.P)) {
                this.Y(false);
            }
            return;
        }
        if (this.Z.hasTimeElapsed((long)this.A.B()) && this.selectHotbarSlotIncrementally((itemStackData = this.k.peek()).Y())) {
            KeyBindingHelper.v(keyBinding, true, true);
            this.I = true;
            this.Z.reset();
            this.k.poll();
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
            for (BooleanValue booleanValue : this.V) {
                if (((ModeSelection)this.p.K()).equals(this.v) && arrayList2.contains(booleanValue)) continue;
                String string = itemSplashPotion.getItemStackDisplayName(itemStack).toLowerCase();
                String string2 = booleanValue.getName().toLowerCase();
                if (!booleanValue.L().booleanValue() || !string.contains(string2)) continue;
                this.k.add(new ItemStackData(n, itemStack));
                arrayList2.add(booleanValue);
                if (!((ModeSelection)this.p.K()).equals(this.D)) continue block1;
                break block1;
            }
        }
        return !this.k.isEmpty();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void q() {
    }

    public ThrowDebuff() {
        super("ThrowDebuff", Category.M, "");
        this.v = new ModeOption("One of each");
        this.D = new ModeOption("First");
        this.p = ModeValue.create((Object)this, "Mode", "All - Throws all debuffs on hotbar\nOne of each - Throws one of each debuff\nFirst - Throws only first debuff on hotbar", (ModeSelection)this.v, this.H, this.v, this.D);
        this.V = new BooleanValue[]{BooleanValue.create(this, "Harming", true), BooleanValue.create(this, "Weakness", true), BooleanValue.create(this, "Poison", true), BooleanValue.create(this, "Slowness", true)};
        this.A = RandomValue.create(this, "Delay", "#.#", "", 0.0, 70.0, 120.0, 200.0);
        this.Y = BooleanValue.create(this, "Scroll", false);
        this.F = NumberValue.create(this, "Scroll delay", "#", "ms", 0.0, 100.0, 200.0);
        this.Z = new TimerUtil();
        this.s = new TimerUtil();
        this.R(false);
        this.addValue(this.p);
        for (BooleanValue booleanValue : this.V) {
            this.addValue(booleanValue);
        }
        this.addValue(this.A);
        this.Y.K(this.F);
        this.addValue(this.Y);
        this.addValue(this.F);
    }

    @Override
    public void onEnable() {
        if (this.collectDebuffs()) {
            this.P = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        } else {
            this.Y(false);
        }
    }

    private boolean selectHotbarSlotIncrementally(int n) {
        if (!this.Y.L().booleanValue()) {
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            return true;
        }
        if (!this.s.hasTimeElapsed(((Double)this.F.K()).longValue())) {
            return false;
        }
        int n2 = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        if (n > n2) {
            ++n2;
        } else if (n < n2) {
            --n2;
        } else {
            this.s.reset();
            return true;
        }
        Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n2);
        return false;
    }
}

