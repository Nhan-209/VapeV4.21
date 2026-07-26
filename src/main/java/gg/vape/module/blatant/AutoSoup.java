package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventMotion;
import gg.vape.event.impl.EventPostMotion;
import gg.vape.event.impl.EventPostPlayerTick;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.CPacketHeldItemChange;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PlayerControllerMP;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionRegistry;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AutoSoup
extends Mod {
    private final ModeOption a;
    private final BooleanValue O;
    private final NumberValue p;
    private boolean F;
    private final ModeOption L;
    private final ModeOption Ac;
    private int s;
    private int V;
    private final LimitValue K;
    private final BooleanValue Y;
    private int o = -1;
    private final NumberValue J;
    private final ModeOption AS;
    private int A;
    private final BooleanValue Aj;
    private final TimerUtil b;
    private final ModeValue U;
    private final BooleanValue c;
    private final ModeOption r;
    private final NumberValue Ah;
    private boolean S;
    private int j;
    private final ModeValue D;
    private int Z;
    private final BooleanValue Aa = BooleanValue.create(this, "Soup", false, "Uses soups to heal.");
    private final RandomValue I;
    private final BooleanValue v;
    private final Queue<InventoryClick> A0;
    private boolean C;
    private boolean k;
    private final BooleanValue P;
    private int A9;
    private final BooleanValue H;
    private int t;

    private int I$src$I$1qpanj0() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int n = -1;
        for (int i = 0; i < 45; ++i) {
            ItemStack itemStack;
            if (!entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).v() || !this.L(itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I())) continue;
            if (i < 8) {
                n = i;
                break;
            }
            if (!this.O.L().booleanValue()) break;
            this.u(i, 36 + ((Double)this.Ah.K()).intValue() - 1);
            n = ((Double)this.Ah.K()).intValue() - 1;
            break;
        }
        return n;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        boolean bl;
        Object object;
        if (!eventPrePlayerTick.getPlayer().isInstance(MappedClasses.z5) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l() || Minecraft.currentScreen().isNotNull()) {
            return;
        }
        boolean bl2 = false;
        if (this.U.K() == this.a) {
            if (this.S) {
                if (!Minecraft.currentScreen().isNull()) {
                    Minecraft.thePlayer().Z$src$V$1ie832h();
                }
                this.S = false;
            } else {
                while (!this.A0.isEmpty()) {
                    object = this.A0.poll();
                    ((InventoryClick)object).k();
                    bl2 = true;
                }
            }
        }
        if (this.k || this.C) {
            return;
        }
        object = Minecraft.thePlayer();
        int n = ((Double)this.p.K()).intValue();
        boolean bl3 = bl = ((EntityLivingBase)object).w$src$F$15l9epb() / Math.max(((EntityLivingBase)object).I$src$F$14vyvep(), 1.0f) <= (float)n / 20.0f && this.b.hasTimeElapsed(((Double)this.J.K()).intValue());
        if (bl) {
            for (int i = 36; i < 45; ++i) {
                ItemStack itemStack;
                if (!((EntityPlayer)object).F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).v() || !this.u(itemStack = ((EntityPlayer)object).F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I())) continue;
                if (this.U.K() == this.a) {
                    this.k = true;
                    this.t = i - 36;
                    this.j = ((EntityPlayer)object).V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                    this.v(bl2 ? 51L : 0L, false);
                } else {
                    this.t = -1;
                    this.j = -1;
                    ((EntityPlayerSP)object).sendQueue().addToSendQueue(CPacketHeldItemChange.create(i - 36));
                    Minecraft.playerController().sendUseItem((EntityPlayer)object, ((Entity)object).getWorld(), ((EntityPlayer)object).F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I());
                    ((EntityPlayerSP)object).sendQueue().addToSendQueue(CPacketHeldItemChange.create(((EntityPlayer)object).V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v()));
                }
                this.b.reset();
                return;
            }
        }
        this.t = -1;
    }

    @EventHandler
    public void onMotionUpdate(EventPostMotion eventPostMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!this.H.L().booleanValue()) {
            return;
        }
        if (this.F && this.o != -1 && this.b.hasTimeElapsed(((Double)this.J.K()).intValue())) {
            ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(36 + this.o).I();
            if (itemStack.isNotNull()) {
                int n = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.o);
                entityPlayerSP.sendQueue().addToSendQueue(CPacketHeldItemChange.create(this.o));
                Minecraft.playerController().sendUseItem(entityPlayerSP, entityPlayerSP.getWorld(), itemStack);
                entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
                entityPlayerSP.sendQueue().addToSendQueue(CPacketHeldItemChange.create(n));
            }
            this.b.reset();
            this.F = false;
            this.o = -1;
        }
    }

    private void z(int n, int n2, int n3, int n4) {
        this.A0.add(new InventoryClick(n, n2, n3, n4));
    }

    private boolean L(ItemStack itemStack) {
        if (itemStack.isNull()) {
            return false;
        }
        if (itemStack.getItem().isInstance(MappedClasses.Di) && ItemSplashPotion.isSplashPotion(itemStack)) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            ItemSplashPotion itemSplashPotion = new ItemSplashPotion(itemStack.getItem().getObject());
            if (itemSplashPotion.getRawPotionEffects(itemStack) != null) {
                for (Object e : itemSplashPotion.getRawPotionEffects(itemStack)) {
                    boolean bl;
                    PotionEffect potionEffect = new PotionEffect(e);
                    boolean bl2 = bl = (double)entityPlayerSP.w$src$F$15l9epb() <= (Double)this.p.K();
                    if (potionEffect.C() == PotionRegistry.z.D() && bl) {
                        return true;
                    }
                    if (potionEffect.C() == PotionRegistry.U.D() && this.P.L().booleanValue() && !entityPlayerSP.i(PotionRegistry.U)) {
                        return true;
                    }
                    if (potionEffect.C() != PotionRegistry.i.D() || !this.v.L().booleanValue() || !bl || entityPlayerSP.i(PotionRegistry.i)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void q() {
        if (!this.k) {
            return;
        }
        try {
            boolean bl;
            KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
            int n = keyBinding.getKeyCode();
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.t);
            boolean bl2 = bl = Minecraft.thePlayer().l$src$Z$1io4duf() && ClientSettings.l(n);
            if (bl) {
                KeyBindingHelper.d(keyBinding, false);
                Thread.sleep(51L);
            }
            KeyBindingHelper.d(keyBinding, true);
            Thread.sleep(51L);
            KeyBindingHelper.v(keyBinding, false, false);
            long l = Math.max((long)this.I.B() + (long)(bl ? -35 : 0), 0L);
            Thread.sleep(l);
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.j);
            if (ClientSettings.l(n)) {
                KeyBindingHelper.d(keyBinding, true);
            }
        }
        catch (Exception exception) {
            this.k = false;
            this.t = -1;
            this.j = -1;
        }
        this.t = -1;
        this.j = -1;
        this.k = false;
    }

    private void u(int n, int n2) {
        n2 -= 36;
        if (this.U.K() == this.a) {
            this.z(Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), n, n2, 2);
        } else {
            Minecraft.playerController().O(Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), n, n2, 2, Minecraft.thePlayer());
        }
    }

    @Override
    public String E() {
        return this.U.c();
    }

    public AutoSoup() {
        super("AutoHeal", 0, 16711819, Category.A, "Automatically heals for you when health is under threshold.");
        this.O = BooleanValue.create(this, "Replace", false, "Refills empty slots with healing items.");
        this.c = BooleanValue.create(this, "Inventory Only", false, "Only refills/crafts when your inventory is open.");
        this.H = BooleanValue.create(this, "Potions", false, "Uses splash healing potions to heal.");
        this.v = BooleanValue.create(this, "Regen", true, "Uses regeneration potions when available.");
        this.P = BooleanValue.create(this, "Speed", false, "Uses speed potions when available.");
        this.Aj = BooleanValue.create(this, "Resistance", false, "Uses resistance potions when available.");
        this.Y = BooleanValue.create(this, "Use Custom Items", false, "Uses other custom healing items.\nUses the same delay as soup/potion.");
        this.K = LimitValue.N(this, "autoheal-customitems", "Healing Items", LimitValue.r, new ItemLimitData("397:3"));
        this.AS = new ModeOption("Throw");
        this.Ac = new ModeOption("Stack");
        this.L = new ModeOption("None");
        this.D = ModeValue.create((Object)this, "Bowl Mode", this.Ac, this.Ac, this.AS, this.L);
        this.r = new ModeOption("Silent");
        this.a = new ModeOption("Legit");
        this.U = ModeValue.create((Object)this, "Mode", this.a, this.r, this.a);
        this.I = RandomValue.G(this, "Swap Delay", "#", "ms", 0.0, 75.0, 125.0, 200.0, 5.0, "The delay between using the item and swapping back.");
        this.J = NumberValue.create(this, "Delay", "#", "ms", 50.0, 500.0, 1000.0, 50.0, "The delay to wait before healing again.");
        this.p = NumberValue.create((Object)this, "Health", "#", "HP", 1.0, 17.0, 20.0, 1.0);
        this.Ah = NumberValue.create(this, "Slot", "#", "", 1.0, 6.0, 9.0, 1.0, "The slot to fill a potion for autopot.");
        this.b = new TimerUtil();
        this.A0 = new ConcurrentLinkedQueue<InventoryClick>();
        this.U.f(this.r, this.H, this.D);
        this.U.f(this.a, this.I);
        this.Y.K(this.K);
        this.Aa.K(this.D);
        this.H.K(this.v, this.P, this.Aj, this.Ah);
        this.addValue(this.U, this.Aa, this.D, this.H, this.v, this.P, this.Aj, this.Ah, this.Y, this.K, this.O, this.p, this.J, this.I);
        if (ForgeVersion.MC_1_17.d()) {
            this.A9 = 730;
            this.Z = 731;
            this.V = 187;
            this.A = 188;
            this.s = 955;
        } else {
            this.A9 = 281;
            this.Z = 282;
            this.V = 39;
            this.A = 40;
            this.s = 397;
        }
    }

    @EventHandler
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!this.H.L().booleanValue()) {
            return;
        }
        int n = ((Double)this.p.K()).intValue();
        boolean bl = (entityPlayerSP.w$src$F$15l9epb() <= (float)n && (this.v.L() == false || !entityPlayerSP.i(PotionRegistry.i)) || this.P.L() != false && !entityPlayerSP.i(PotionRegistry.U) || this.Aj.L() != false && !entityPlayerSP.i(PotionRegistry.P)) && entityPlayerSP.b$src$Z$fqlxe4() && entityPlayerSP.u$src$Z$g120nz();
        int n2 = this.I$src$I$1qpanj0();
        if (n2 != -1 && bl && this.b.hasTimeElapsed(((Double)this.J.K()).intValue())) {
            if (this.o == -1) {
                this.F = true;
                this.o = n2;
                EventMotion.setRotationPitch(88.99f);
            }
        } else {
            this.F = false;
        }
    }

    private boolean u(ItemStack itemStack) {
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (this.Aa.L().booleanValue() && item.P() == this.Z) {
            return true;
        }
        return this.Y.L() != false && this.K.A(itemStack);
    }

    private void T(EntityPlayerSP entityPlayerSP) {
        Wrapper wrapper;
        Wrapper wrapper2;
        int n;
        if (this.c.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
            return;
        }
        if (this.k) {
            return;
        }
        if (!this.C && this.Aa.L().booleanValue() && this.D.K() != this.L) {
            for (n = 9; n < 45; ++n) {
                boolean bl;
                ItemStack itemStack;
                boolean bl2;
                boolean bl3 = bl2 = n >= 36;
                if (!entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).v() || !(wrapper2 = (itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I()).getItem()).isNotNull() || ((Item)wrapper2).P() != this.A9) continue;
                wrapper = Minecraft.playerController();
                if (this.D.K() == this.AS) {
                    ((PlayerControllerMP)wrapper).O(0, n, 1, 4, entityPlayerSP);
                    return;
                }
                if (this.D.K() != this.Ac || n == 17) continue;
                boolean bl4 = bl = !entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(17).v();
                if (bl || entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(17).I().getItem().P() != this.A9) {
                    if (bl2) {
                        this.u(17, n);
                    } else {
                        ((PlayerControllerMP)wrapper).O(0, n, 0, 0, entityPlayerSP);
                        ((PlayerControllerMP)wrapper).O(0, 17, 0, 0, entityPlayerSP);
                        ((PlayerControllerMP)wrapper).O(0, n, 0, 0, entityPlayerSP);
                    }
                } else if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(17).I().getItem().P() == this.A9) {
                    if (bl2) {
                        ((PlayerControllerMP)wrapper).O(0, n, 0, 1, entityPlayerSP);
                    } else {
                        ((PlayerControllerMP)wrapper).O(0, n, 0, 0, entityPlayerSP);
                        ((PlayerControllerMP)wrapper).O(0, 17, 0, 0, entityPlayerSP);
                    }
                }
                return;
            }
        }
        if (this.O.L().booleanValue()) {
            for (n = 9; n < 36; ++n) {
                ItemStack itemStack;
                if (!entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).v() || !this.u(itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I())) continue;
                for (int i = 36; i < 45; ++i) {
                    wrapper2 = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I();
                    if (!((ItemStack)wrapper2).isNull() && !((ItemStack)wrapper2).getItem().isNull()) continue;
                    if (!this.C) {
                        wrapper = Minecraft.gameSettings().j();
                        if (ForgeVersion.MC_1_16_5.d()) {
                            KeyBindingHelper.a((KeyBinding)wrapper);
                        } else {
                            KeyBindingHelper.d((KeyBinding)wrapper, true);
                            KeyBindingHelper.v((KeyBinding)wrapper, false, false);
                        }
                        this.C = true;
                        return;
                    }
                    this.S = false;
                    this.u(n, i);
                    return;
                }
            }
            if (this.C) {
                this.C = false;
                this.S = true;
            }
        }
    }

    @EventHandler
    public void onPlayerTick(EventPostPlayerTick eventPostPlayerTick) {
        if (!eventPostPlayerTick.getPlayer().isInstance(MappedClasses.z5) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            return;
        }
        this.T(eventPostPlayerTick.getThePlayer());
    }
}

