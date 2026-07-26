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
    private final Random v;
    private final TimerUtil H;
    private final ModeValue I;
    private final ModeOption A = new ModeOption("Both");
    private final BooleanValue K;
    private boolean D;
    private final BooleanValue F;
    private final RandomValue s;
    private boolean k;
    private final LimitValue U;
    private final ModeOption S;
    private final Queue<InventoryClick> O;
    private final BooleanValue V;
    private final ModeOption p = new ModeOption("Pots");

    private boolean c() {
        boolean bl = false;
        for (int i = 9; i < 36; ++i) {
            Item item;
            ItemStack itemStack = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
            if (itemStack.isNull() || (item = itemStack.getItem()).isNull() || !ItemStackScoreUtil.o(itemStack, ((ModeSelection)this.I.K()).equals(this.S) || ((ModeSelection)this.I.K()).equals(this.A))) continue;
            bl = true;
        }
        return bl;
    }

    private void G() {
        if (this.s.M() == 0.0) {
            while (!this.O.isEmpty()) {
                InventoryClick inventoryClick = this.O.poll();
                inventoryClick.k();
            }
            return;
        }
        if (this.H.hasTimeElapsed((long)this.s.B())) {
            InventoryClick inventoryClick = this.O.poll();
            inventoryClick.k();
            this.H.reset();
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (!this.D) {
            if (!Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
                KeyBinding keyBinding = Minecraft.gameSettings().j();
                if (ForgeVersion.MC_1_16_5.d()) {
                    KeyBindingHelper.a(keyBinding);
                } else {
                    KeyBindingHelper.d(keyBinding, true);
                    KeyBindingHelper.v(keyBinding, false, false);
                }
            } else {
                this.D = true;
            }
            return;
        }
        if (this.k) {
            if (!Minecraft.currentScreen().isNull()) {
                Minecraft.thePlayer().Z$src$V$1ie832h();
            }
            this.k = false;
            this.O.clear();
            this.Y(false);
            return;
        }
        if (!Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            this.k = true;
            return;
        }
        if (!this.O.isEmpty()) {
            this.G();
            return;
        }
        this.k$src$V$16kglb3();
        if (this.O.isEmpty()) {
            this.k = true;
        }
    }

    private boolean N(Item item, ItemStack itemStack, List<ItemStack> list) {
        if (ItemStackScoreUtil.o(itemStack, ((ModeSelection)this.I.K()).equals(this.S) || ((ModeSelection)this.I.K()).equals(this.A))) {
            return false;
        }
        if (this.U.isValid(itemStack, true)) {
            if (!this.e(itemStack, list)) {
                list.add(itemStack);
                return false;
            }
            return true;
        }
        return true;
    }

    private void d(int n, int n2, int n3, int n4) {
        this.O.add(new InventoryClick(n, n2, n3, n4));
    }

    public Refill() {
        super("Refill", Category.M, "Refills your hotbar with healing items.");
        this.S = new ModeOption("Soup");
        this.I = ModeValue.create((Object)this, "Type", this.A, this.A, this.p, this.S);
        this.U = LimitValue.n(this, "refill-alloweditems", "Non Junk Items", LimitValue.r, Collections.emptyList());
        this.F = BooleanValue.create(this, "Vertical", false);
        this.V = BooleanValue.create(this, "Scatter", false);
        this.K = BooleanValue.create(this, "Hotbar clear", false, "Clears junk from your hotbar to refill.\nWhitelisted items will not be considered junk\nOnly one stack of each non-junk item is kept");
        this.s = RandomValue.C(this, "Delay", "#", "ms", 50.0, 75.0, 125.0, 200.0, 5.0);
        this.O = new ConcurrentLinkedQueue<InventoryClick>();
        this.v = new Random();
        this.H = new TimerUtil();
        this.K.K(this.U);
        this.addValue(this.F, this.V, this.K, this.U, this.s, this.I);
    }

    private boolean l() {
        return this.p(0.2);
    }

    private boolean e(ItemStack itemStack, List<ItemStack> list) {
        for (ItemStack itemStack2 : list) {
            if (itemStack.equals(itemStack2) || !itemStack.f().equals(itemStack2.f())) continue;
            return true;
        }
        return false;
    }

    private boolean p(double d) {
        double d2 = Math.max(Math.min(d, 1.0), 0.0);
        return Math.random() <= d2;
    }

    private void k$src$V$16kglb3() {
        GuiContainer guiContainer = new GuiContainer(Minecraft.currentScreen().getObject());
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ArrayList arrayList2 = new ArrayList();
        int n = 0;
        List<Integer> list = this.g$src$Ljava_util_List_$vq0it0();
        int n2 = 9;
        while (n2 < 36) {
            Item item;
            Slot slot = guiContainer.getInventorySlots().getInventorySlots().get(n2);
            Object object = slot.I();
            if (!((ItemStack)object).isNull() && !(item = ((ItemStack)object).getItem()).isNull() && ItemStackScoreUtil.o((ItemStack)object, ((ModeSelection)this.I.K()).equals(this.S) || ((ModeSelection)this.I.K()).equals(this.A))) {
                arrayList.add(n2);
            }
            if (this.F.L().booleanValue()) {
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
            this.k = true;
            return;
        }
        if (this.V.L().booleanValue()) {
            Collections.shuffle(arrayList);
        }
        for (n2 = 0; n2 < list.size() && n2 < arrayList.size(); ++n2) {
            arrayList2.add(arrayList.get(n2));
        }
        n2 = 0;
        for (Object object : arrayList2) {
            boolean bl = false;
            int n3 = 0;
            if (this.K.L().booleanValue()) {
                Item item;
                n3 = list.get(n2);
                ItemStack itemStack = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(n3);
                if (itemStack.isNotNull() && (item = itemStack.getItem()).isNotNull()) {
                    bl = true;
                }
            }
            if (bl) {
                this.d(guiContainer.getInventorySlots().getWindowId(), (Integer)object, 0, 0);
                this.d(guiContainer.getInventorySlots().getWindowId(), 36 + n3, 0, 0);
                this.d(guiContainer.getInventorySlots().getWindowId(), (Integer)object, 0, 0);
            } else {
                this.d(guiContainer.getInventorySlots().getWindowId(), (Integer)object, 0, 1);
                if (this.l()) {
                    this.d(guiContainer.getInventorySlots().getWindowId(), (Integer)object, 0, 1);
                }
            }
            ++n2;
        }
    }

    private List<Integer> g$src$Ljava_util_List_$vq0it0() {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        Object[] objectArray = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().M();
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = new ItemStack(objectArray[i]);
            if (itemStack.isNull()) {
                arrayList2.add(i);
                continue;
            }
            if (this.K.L().booleanValue()) {
                if (!this.N(itemStack.getItem(), itemStack, arrayList)) continue;
                arrayList2.add(i);
                continue;
            }
            if (!itemStack.toString().contains("tile.air")) continue;
            arrayList2.add(i);
        }
        return arrayList2;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void onEnable() {
        if (Minecraft.thePlayer().isNull()) {
            this.Y(false);
            return;
        }
        if (this.g$src$Ljava_util_List_$vq0it0().size() == 0) {
            this.Y(false);
            return;
        }
        if (!this.c()) {
            this.Y(false);
        }
    }

    @Override
    public void onDisable() {
        this.D = false;
    }
}

