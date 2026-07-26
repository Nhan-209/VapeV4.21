package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.inventory.InventoryActionGuard;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AutoArmor
extends Mod
implements InventoryActionModule {
    private final BooleanValue v;
    private String[] F;
    private final TimerUtil j;
    private final TimerUtil b;
    private InventoryPlayer a;
    private boolean A;
    private final BooleanValue D;
    private final RandomValue c;
    private Object L;
    private String[] r;
    private final BooleanValue V;
    private String[] O;
    private final BooleanValue p;
    private static final long J = -516952979702363148L;
    private final InventoryActionGuard o;
    private final BooleanValue C = BooleanValue.create(this, "Open inventory", true, "Opens your inventory when you can equip armor");
    private boolean H;
    private final Queue<InventoryClick> U;
    private String[] K;

    private void C(int n, int n2, int n3, int n4) {
        this.U.add(new InventoryClick(n, n2, n3, n4));
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (Vape.INSTANCE.getModManager().N(AutoArmor.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.U.clear();
            return;
        }
        this.J$src$V$58j6do();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!entityPlayerSP.p$src$Lgg_vape_wrapper_impl_Container_$1a6go00().isNull() && entityPlayerSP.p$src$Lgg_vape_wrapper_impl_Container_$1a6go00().getWindowId() != 0) {
            return;
        }
        if (this.C.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS) && !Minecraft.currentScreen().isInstance(MappedClasses.n)) {
            this.U.clear();
        }
        if (this.v.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS) && !Minecraft.currentScreen().isInstance(MappedClasses.n)) {
            this.U.clear();
            return;
        }
        if (!this.j.hasTimeElapsed(100L + (long)this.c.B())) {
            return;
        }
        if (!this.U.isEmpty()) {
            if (this.b.hasTimeElapsed((long)this.c.B())) {
                InventoryClick inventoryClick = this.U.poll();
                inventoryClick.k();
                this.b.reset();
            }
            return;
        }
        if (this.A) {
            if (Minecraft.currentScreen().isNotNull()) {
                entityPlayerSP.Z$src$V$1ie832h();
                this.H = false;
                this.A = false;
            }
            return;
        }
        if (this.p.L().booleanValue()) {
            this.o.i(entityPlayerSP);
            if (this.o.l()) {
                this.j.reset();
                return;
            }
        }
        for (int i = 5; i < 9; ++i) {
            int n = this.W(i, this.V.L());
            if (n == -1) continue;
            if (this.C.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
                if (this.j.hasTimeElapsed(200L + (long)this.c.B())) {
                    KeyBinding keyBinding = Minecraft.gameSettings().j();
                    if (ForgeVersion.MC_1_16_5.d()) {
                        KeyBindingHelper.a(keyBinding);
                    } else {
                        KeyBindingHelper.d(keyBinding, true);
                        KeyBindingHelper.v(keyBinding, false, false);
                    }
                    this.H = true;
                }
                return;
            }
            if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().isNotNull()) {
                if (this.D.L().booleanValue()) {
                    this.C(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), i, 0, 0);
                    this.C(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), -999, 0, 0);
                } else {
                    this.C(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), i, 0, 1);
                }
            }
            this.C(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), n, 0, 1);
        }
        if (this.H && this.U.isEmpty()) {
            this.A = true;
            this.j.reset();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.A = false;
        this.H = false;
    }

    private int W(int n, boolean bl) {
        int n2 = -1;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I();
        double d = 0.0;
        double d2 = 999.0;
        if (itemStack.isNotNull()) {
            d = this.Q(itemStack);
            d2 = this.P(n);
        }
        double d3 = d;
        double d4 = d2;
        for (int i = 9; i < 45; ++i) {
            ItemStack itemStack2;
            if (!entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I().isNotNull() || this.a(itemStack2 = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I()) == -1 || this.a(itemStack2) != n) continue;
            double d5 = this.Q(itemStack2);
            double d6 = this.P(i);
            if (d5 > d3) {
                d3 = d5;
                n2 = i;
                d4 = d6;
                continue;
            }
            if (!bl || d5 != d3 || !(d6 < d4)) continue;
            d4 = d6;
            n2 = i;
        }
        return n2;
    }

    private int P(int n) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().isNotNull() ? entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().L() : 999;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && this.U.size() > 0;
    }

    @Override
    public String r() {
        if (this.p.L().booleanValue() && this.o.l()) {
            return ClientSettings.F + "c[In Combat]";
        }
        return super.r();
    }

    private void J$src$V$58j6do() {
        Object object = Minecraft.currentScreen().getObject();
        if (object != this.L) {
            this.j.reset();
        }
        this.L = object;
    }

    private int a(ItemStack itemStack) {
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        if (itemMappingEntry == null || itemMappingEntry.M() == null) {
            return -1;
        }
        for (String string : this.F) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 8;
        }
        for (String string : this.O) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 7;
        }
        for (String string : this.K) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 6;
        }
        for (String string : this.r) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 5;
        }
        return -1;
    }

    public AutoArmor() {
        super("AutoArmor", (int)J, Category.M, "Automatically equips armor when needed.");
        this.v = BooleanValue.create(this, "Inventory only", true, "Only equip armor when in inventory");
        this.V = BooleanValue.create(this, "Check durability", true, "Always puts on the armor with the highest durability");
        this.D = BooleanValue.create(this, "Drop equipped", false, "Drops worse equipped armor for better armor when active");
        this.p = BooleanValue.create(this, "Combat check", false, "Won't equip armor while in combat");
        this.c = RandomValue.C(this, "Delay", "#", "", 1.0, 100.0, 120.0, 200.0, 1.0);
        this.b = new TimerUtil();
        this.j = new TimerUtil();
        this.o = new InventoryActionGuard(20);
        this.U = new ConcurrentLinkedQueue<InventoryClick>();
        this.addValue(this.C, this.v, this.V, this.D, this.p, this.c);
        this.r = new String[]{"cap", "helmet"};
        this.K = new String[]{"tunic", "chestplate"};
        this.O = new String[]{"pants", "leggings"};
        this.F = new String[]{"boots"};
    }

    private double Q(ItemStack itemStack) {
        Item item;
        double d = ItemStackScoreUtil.L(itemStack);
        if (ForgeVersion.MC_1_16_5.d()) {
            // empty if block
        }
        if (itemStack.isNotNull() && (item = itemStack.getItem()).isNotNull() && ItemStackScoreUtil.R(item) && ItemStackScoreUtil.T$src$Z$2fnsig(itemStack) && ItemStackScoreUtil.t(itemStack) == 0) {
            d -= 0.01;
        }
        return d;
    }
}

