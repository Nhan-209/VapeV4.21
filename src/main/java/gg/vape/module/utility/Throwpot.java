package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.UtilityMod;
import gg.vape.module.utility.invcleaner.ItemDataComparator;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.datas.ItemStackData;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class Throwpot
extends UtilityMod {
    private final CopyOnWriteArrayList<ItemStackData> itemsToThrow;
    private final NumberValue scrollDelay;
    private final ModeValue modeValue;
    private int savedSlot;
    private final ModeValue typeValue;
    private final ModeOption singleOption;
    private final ModeOption potsOption;
    private final BooleanValue scrollValue;
    private final ModeOption bothOption = new ModeOption("Both");
    private final ModeOption dynamicOption;
    private final BooleanValue throwBowls;
    private boolean active;
    private final BooleanValue randomValue;
    private final RandomValue delayValue;
    private final ModeOption soupOption;

    @Override
    public void onEnable() {
        if (this.active) {
            this.Y(false);
            return;
        }
        if (Minecraft.thePlayer().isNull() || Minecraft.currentScreen().isNotNull()) {
            this.Y(false);
            return;
        }
        if (!this.active && this.collectHealingItems()) {
            InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
            this.savedSlot = inventoryPlayer.v();
            this.active = true;
            this.v(0L, false);
        } else {
            this.itemsToThrow.clear();
            this.Y(false);
        }
    }

    private boolean collectHealingItems() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 9; ++i) {
            arrayList.add(i);
        }
        if (this.randomValue.L().booleanValue()) {
            Collections.shuffle(arrayList);
        }
        Object[] objectArray = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().M();
        int n = 0;
        for (Integer n2 : arrayList) {
            PotionEffect potionEffect;
            int n3;
            boolean bl;
            Item item;
            ItemStack itemStack = new ItemStack(objectArray[n2]);
            if (itemStack.isNull() || (item = itemStack.getItem()).isNull()) continue;
            boolean soupEnabled = ((ModeSelection)this.typeValue.K()).equals(this.soupOption) || ((ModeSelection)this.typeValue.K()).equals(this.bothOption);
            boolean potsEnabled = bl = ((ModeSelection)this.typeValue.K()).equals(this.potsOption) || ((ModeSelection)this.typeValue.K()).equals(this.bothOption);
            if (ItemStackScoreUtil.v(item) && soupEnabled) {
                if (((ModeSelection)this.modeValue.K()).equals(this.singleOption)) {
                    this.itemsToThrow.add(new ItemStackData(n2, itemStack));
                    break;
                }
                int n4 = 8;
                if ((double)(n + n4) + Math.floor(Minecraft.thePlayer().w$src$F$15l9epb()) > (double)Minecraft.thePlayer().I$src$F$14vyvep()) continue;
                n += n4;
                this.itemsToThrow.add(new ItemStackData(n2, itemStack));
            }
            if (!MappedClasses.Di.isInstance(item.getObject()) || !bl || !ItemSplashPotion.isSplashPotion(itemStack)) continue;
            if (((ModeSelection)this.modeValue.K()).equals(this.singleOption) && ItemStackScoreUtil.i(itemStack)) {
                this.itemsToThrow.add(new ItemStackData(n2, itemStack));
                break;
            }
            ItemSplashPotion itemSplashPotion = new ItemSplashPotion(item.getObject());
            if (!ItemStackScoreUtil.i(itemStack) || (double)(n + (n3 = 4 * ((potionEffect = new PotionEffect(itemSplashPotion.getRawPotionEffects(itemStack).get(0))).L() + 1))) + Math.floor(Minecraft.thePlayer().w$src$F$15l9epb()) > (double)Minecraft.thePlayer().I$src$F$14vyvep()) continue;
            n += n3;
            this.itemsToThrow.add(new ItemStackData(n2, itemStack));
        }
        return !this.itemsToThrow.isEmpty();
    }

    @Override
    public void q() {
        if (!this.active) {
            return;
        }
        try {
            GameSettings gameSettings = Minecraft.gameSettings();
            KeyBinding keyBinding = gameSettings.b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
            KeyBinding keyBinding2 = gameSettings.b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
            KeyBinding keyBinding3 = gameSettings.v$src$Lgg_vape_wrapper_impl_KeyBinding_$11ijh0e();
            this.itemsToThrow.sort(new ItemDataComparator(this.savedSlot));
            boolean bl = false;
            for (ItemStackData itemStackData : this.itemsToThrow) {
                this.selectHotbarSlot(itemStackData.Y());
                if (keyBinding.isKeyDown() && ItemStackScoreUtil.v(itemStackData.w().getItem())) {
                    KeyBindingHelper.v(keyBinding2, false, false);
                    Thread.sleep(51L);
                    bl = true;
                }
                KeyBindingHelper.d(keyBinding2, true);
                Thread.sleep(51L);
                KeyBindingHelper.v(keyBinding2, false, false);
                if (this.throwBowls.L().booleanValue() && ItemStackScoreUtil.v(itemStackData.w().getItem())) {
                    KeyBindingHelper.d(keyBinding3, true);
                    Thread.sleep(51L);
                    KeyBindingHelper.v(keyBinding3, false, false);
                }
                Thread.sleep((long)this.delayValue.B());
            }
            this.selectHotbarSlot(this.savedSlot);
            if (bl) {
                KeyBindingHelper.d(keyBinding2, true);
            }
        }
        catch (Exception exception) {
            this.active = false;
            exception.printStackTrace();
        }
        this.active = false;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (!this.active && this.r$src$Z$14eylz9()) {
            this.Y(false);
        }
    }

    private void selectHotbarSlot(int n) {
        if (!this.scrollValue.L().booleanValue()) {
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            return;
        }
        int n2 = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        while (true) {
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n2);
            try {
                Thread.sleep(((Double)this.scrollDelay.K()).longValue());
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            if (n > n2) {
                ++n2;
                continue;
            }
            if (n >= n2) break;
            --n2;
        }
    }

    private static Exception passThrough(Exception exception) {
        return exception;
    }

    public Throwpot() {
        super("Throwpot", Category.M, "Throws or consumes healing items automatically or upon pressing keybind");
        this.potsOption = new ModeOption("Pots");
        this.soupOption = new ModeOption("Soup");
        this.typeValue = ModeValue.create((Object)this, "Type", this.bothOption, this.bothOption, this.potsOption, this.soupOption);
        this.dynamicOption = new ModeOption("Dynamic");
        this.singleOption = new ModeOption("Single");
        this.modeValue = ModeValue.create((Object)this, "Mode", "Dynamic - uses only as many items as needed to heal as much as possible without over-healing\nSingle - Always uses one item, regardless of health", (ModeSelection)this.dynamicOption, this.dynamicOption, this.singleOption);
        this.scrollDelay = NumberValue.create(this, "Scroll delay", "#", "ms", 0.0, 100.0, 200.0);
        this.delayValue = RandomValue.C(this, "Delay", "#", "ms", 0.0, 80.0, 115.0, 200.0, 1.0);
        this.scrollValue = BooleanValue.create(this, "Scroll", false);
        this.randomValue = BooleanValue.create(this, "Random", false);
        this.throwBowls = BooleanValue.create(this, "Throw bowls", true, "Throws soup bowls after consuming");
        this.itemsToThrow = new CopyOnWriteArrayList();
        this.scrollValue.K(this.scrollDelay);
        this.addValue(this.typeValue, this.modeValue, this.delayValue, this.scrollValue, this.scrollDelay, this.randomValue, this.throwBowls);
    }

    @Override
    public void onDisable() {
        this.itemsToThrow.clear();
    }
}

