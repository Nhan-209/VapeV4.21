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
    private final CopyOnWriteArrayList<ItemStackData> I;
    private final NumberValue b;
    private final ModeValue Y;
    private int P;
    private final ModeValue v;
    private final ModeOption H;
    private final ModeOption t;
    private final BooleanValue S;
    private final ModeOption o = new ModeOption("Both");
    private final ModeOption U;
    private final BooleanValue j;
    private boolean L;
    private final BooleanValue s;
    private final RandomValue J;
    private final ModeOption Z;

    @Override
    public void onEnable() {
        if (this.L) {
            this.Y(false);
            return;
        }
        if (Minecraft.thePlayer().isNull() || Minecraft.currentScreen().isNotNull()) {
            this.Y(false);
            return;
        }
        if (!this.L && this.collectHealingItems()) {
            InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
            this.P = inventoryPlayer.v();
            this.L = true;
            this.v(0L, false);
        } else {
            this.I.clear();
            this.Y(false);
        }
    }

    private boolean collectHealingItems() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 9; ++i) {
            arrayList.add(i);
        }
        if (this.s.L().booleanValue()) {
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
            boolean bl2 = ((ModeSelection)this.v.K()).equals(this.Z) || ((ModeSelection)this.v.K()).equals(this.o);
            boolean bl3 = bl = ((ModeSelection)this.v.K()).equals(this.t) || ((ModeSelection)this.v.K()).equals(this.o);
            if (ItemStackScoreUtil.v(item) && bl2) {
                if (((ModeSelection)this.Y.K()).equals(this.H)) {
                    this.I.add(new ItemStackData(n2, itemStack));
                    break;
                }
                int n4 = 8;
                if ((double)(n + n4) + Math.floor(Minecraft.thePlayer().w$src$F$15l9epb()) > (double)Minecraft.thePlayer().I$src$F$14vyvep()) continue;
                n += n4;
                this.I.add(new ItemStackData(n2, itemStack));
            }
            if (!MappedClasses.Di.isInstance(item.getObject()) || !bl || !ItemSplashPotion.isSplashPotion(itemStack)) continue;
            if (((ModeSelection)this.Y.K()).equals(this.H) && ItemStackScoreUtil.i(itemStack)) {
                this.I.add(new ItemStackData(n2, itemStack));
                break;
            }
            ItemSplashPotion itemSplashPotion = new ItemSplashPotion(item.getObject());
            if (!ItemStackScoreUtil.i(itemStack) || (double)(n + (n3 = 4 * ((potionEffect = new PotionEffect(itemSplashPotion.getRawPotionEffects(itemStack).get(0))).L() + 1))) + Math.floor(Minecraft.thePlayer().w$src$F$15l9epb()) > (double)Minecraft.thePlayer().I$src$F$14vyvep()) continue;
            n += n3;
            this.I.add(new ItemStackData(n2, itemStack));
        }
        return !this.I.isEmpty();
    }

    @Override
    public void q() {
        if (!this.L) {
            return;
        }
        try {
            GameSettings gameSettings = Minecraft.gameSettings();
            KeyBinding keyBinding = gameSettings.b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
            KeyBinding keyBinding2 = gameSettings.b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
            KeyBinding keyBinding3 = gameSettings.v$src$Lgg_vape_wrapper_impl_KeyBinding_$11ijh0e();
            this.I.sort(new ItemDataComparator(this.P));
            boolean bl = false;
            for (ItemStackData itemStackData : this.I) {
                this.selectHotbarSlot(itemStackData.Y());
                if (keyBinding.isKeyDown() && ItemStackScoreUtil.v(itemStackData.w().getItem())) {
                    KeyBindingHelper.v(keyBinding2, false, false);
                    Thread.sleep(51L);
                    bl = true;
                }
                KeyBindingHelper.d(keyBinding2, true);
                Thread.sleep(51L);
                KeyBindingHelper.v(keyBinding2, false, false);
                if (this.j.L().booleanValue() && ItemStackScoreUtil.v(itemStackData.w().getItem())) {
                    KeyBindingHelper.d(keyBinding3, true);
                    Thread.sleep(51L);
                    KeyBindingHelper.v(keyBinding3, false, false);
                }
                Thread.sleep((long)this.J.B());
            }
            this.selectHotbarSlot(this.P);
            if (bl) {
                KeyBindingHelper.d(keyBinding2, true);
            }
        }
        catch (Exception exception) {
            this.L = false;
            exception.printStackTrace();
        }
        this.L = false;
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (!this.L && this.r$src$Z$14eylz9()) {
            this.Y(false);
        }
    }

    private void selectHotbarSlot(int n) {
        if (!this.S.L().booleanValue()) {
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            return;
        }
        int n2 = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
        while (true) {
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n2);
            try {
                Thread.sleep(((Double)this.b.K()).longValue());
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

    private static Exception a(Exception exception) {
        return exception;
    }

    public Throwpot() {
        super("Throwpot", Category.M, "Throws or consumes healing items automatically or upon pressing keybind");
        this.t = new ModeOption("Pots");
        this.Z = new ModeOption("Soup");
        this.v = ModeValue.create((Object)this, "Type", this.o, this.o, this.t, this.Z);
        this.U = new ModeOption("Dynamic");
        this.H = new ModeOption("Single");
        this.Y = ModeValue.create((Object)this, "Mode", "Dynamic - uses only as many items as needed to heal as much as possible without over-healing\nSingle - Always uses one item, regardless of health", (ModeSelection)this.U, this.U, this.H);
        this.b = NumberValue.create(this, "Scroll delay", "#", "ms", 0.0, 100.0, 200.0);
        this.J = RandomValue.C(this, "Delay", "#", "ms", 0.0, 80.0, 115.0, 200.0, 1.0);
        this.S = BooleanValue.create(this, "Scroll", false);
        this.s = BooleanValue.create(this, "Random", false);
        this.j = BooleanValue.create(this, "Throw bowls", true, "Throws soup bowls after consuming");
        this.I = new CopyOnWriteArrayList();
        this.S.K(this.b);
        this.addValue(this.v, this.Y, this.J, this.S, this.b, this.s, this.j);
    }

    @Override
    public void onDisable() {
        this.I.clear();
    }
}

