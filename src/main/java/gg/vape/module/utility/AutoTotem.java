package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventClickMouse;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventRightClickMouse;
import gg.vape.event.impl.EventSendClickBlockToController;
import gg.vape.event.impl.EventThreadBoundPostTick;
import gg.vape.event.impl.EventThreadBoundPreTick;
import gg.vape.event.impl.EventWindowClick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.Freecam;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.hud.ActiveModuleStackFrame;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AutoTotem
extends Mod
implements InventoryActionModule {
    private final Random k;
    private final TimerUtil H;
    private final TimerUtil F = new TimerUtil();
    private Object o;
    private final RotationControlClaim C;
    private static Freecam D;
    private boolean Y;
    private long Z = -1L;
    private final RandomValue t;
    private final RandomValue L;
    private boolean I;
    private boolean V;
    private AdaptiveRotationController J;
    private final BooleanValue b;
    private long c = -1L;
    private final BooleanValue oB;
    private final Queue<InventoryClick> O;
    private final TimerUtil v = new TimerUtil();
    private final BooleanValue j;
    private final BooleanValue a;
    private boolean p;
    private static final int U;
    private final BooleanValue r;
    private boolean K;
    private final RotationManager S;
    private final BooleanValue s;
    private static final int A;
    private final BooleanValue P;

    private double s() {
        int[] nArray = ModeSelection.q();
        if (!this.b.L().booleanValue()) {
            return this.L.B();
        }
        double d = this.L.q$src$D$vgz097();
        double d2 = this.L.M();
        double d3 = Math.max(1.0, d2 - d);
        double d4 = d + d3 * 0.5;
        double d5 = Math.max(1.0, d3 / 4.0);
        double d6 = d4 + this.k.nextGaussian() * d5;
        d6 = Math.max(d, Math.min(d2, d6));
        if (this.k.nextDouble() < 0.18) {
            double d7 = Math.max(120.0, d3 * 1.25);
            double d8 = Math.max(350.0, d3 * 3.25);
            d6 += d7 + (d8 - d7) * this.k.nextDouble();
        }
        return d6;
    }

    private int X(EntityPlayerSP entityPlayerSP) {
        int n = 0;
        for (int i = 9; i <= 45; ++i) {
            ItemMappingEntry itemMappingEntry;
            ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I();
            if (itemStack.isNull() || (itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack)) == null || !itemMappingEntry.M().toLowerCase().contains("totem_of_undying")) continue;
            n += itemStack.t();
        }
        return n;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void B(EventSendClickBlockToController eventSendClickBlockToController) {
        if (this.p) {
            eventSendClickBlockToController.setCancelled(true);
        }
    }

    private void W() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.V) {
            if (this.K) {
                this.K = false;
                if (this.J != null) {
                    this.J.Y(3.0f);
                    this.J.D(true);
                    this.J.s(true);
                    this.S.v(this.J);
                    this.J = null;
                }
                this.C.X(this);
                entityPlayerSP.Z$src$V$1ie832h();
            } else if (this.j.L().booleanValue() && guiScreen.isNotNull()) {
                entityPlayerSP.Z$src$V$1ie832h();
            }
        }
        this.V = false;
        this.I = false;
    }

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && !this.O.isEmpty();
    }

    private boolean Z() {
        if (this.Z <= 0L) {
            AutoTotem autoTotem = this;
            this.Z = Math.max(1L, (long)autoTotem.s());
        }
        return this.v.hasTimeElapsed(this.Z);
    }

    private void t(int n, int n2, int n3, int n4) {
        this.O.add(new InventoryClick(n, n2, n3, n4));
    }

    private void M$src$V$g0nukx() {
        if (this.r.L().booleanValue()) {
            if (this.M$src$Z$g0nuod()) {
                return;
            }
            this.K = true;
            this.p = true;
            this.H.reset();
            this.J = new AdaptiveRotationController();
            this.J.Y(0.0f);
            this.S.S(this.J);
        } else {
            KeyBinding keyBinding = Minecraft.gameSettings().j();
            if (ForgeVersion.MC_1_16_5.d()) {
                KeyBindingHelper.a(keyBinding);
            } else {
                KeyBindingHelper.d(keyBinding, true);
                KeyBindingHelper.v(keyBinding, false, false);
            }
        }
        this.V = true;
        this.G();
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void y(EventClickMouse eventClickMouse) {
        if (this.p) {
            eventClickMouse.setCancelled(true);
        }
    }

    private void b$src$V$gc7j1i() {
        Object object = Minecraft.currentScreen().getObject();
        if (object != this.o) {
            this.G();
        }
        this.o = object;
    }

    private void z(GameSettings gameSettings) {
        if (this.p) {
            KeyBindingHelper.v(gameSettings.Y(), gg.vape.config.ClientSettings.B(gameSettings.Y()), true);
            KeyBindingHelper.v(gameSettings.s(), gg.vape.config.ClientSettings.B(gameSettings.s()), true);
            KeyBindingHelper.v(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), gg.vape.config.ClientSettings.B(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()), true);
            KeyBindingHelper.v(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), gg.vape.config.ClientSettings.B(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3()), true);
            KeyBindingHelper.v(gameSettings.O(), gg.vape.config.ClientSettings.B(gameSettings.O()), true);
            KeyBindingHelper.v(gameSettings.r(), gg.vape.config.ClientSettings.B(gameSettings.r()), true);
        }
    }

    @Override
    public ModDisplayInfo J() {
        if (!this.oB.L().booleanValue()) {
            return null;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        int n = this.X(entityPlayerSP);
        Color color = new Color(255, 20, 20);
        if (n >= 4) {
            color = new Color(2, 190, 58);
        } else if (n >= 2) {
            color = new Color(255, 249, 18);
        }
        return new ModDisplayInfo(String.valueOf(n), color);
    }

    @Override
    public void onDisable() {
        ClientSettings.g(ActiveModuleStackFrame.class).w(this);
        if (this.V && this.K) {
            this.W();
        }
        this.I = false;
        this.V = false;
        this.Z = -1L;
        this.c = -1L;
    }

    @EventHandler
    public void w(EventWindowClick eventWindowClick) {
        boolean bl;
        GuiScreen guiScreen = eventWindowClick.getCurrentScreen();
        boolean bl2 = bl = this.V && this.K || guiScreen.isNotNull() && guiScreen.isInstance(MappedClasses.YS);
        if (bl && !this.Y && this.V) {
            eventWindowClick.setCancelled(true);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        U = 1215;
        A = 45;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        boolean bl;
        boolean bl2;
        if (this.p) {
            if (this.H.hasTimeElapsed((long)this.t.B())) {
                this.z(eventPrePlayerTick.getGameSettings());
                this.p = false;
            } else {
                this.b(eventPrePlayerTick.getGameSettings());
            }
        }
        if (Vape.INSTANCE.getModManager().N(AutoTotem.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.O.clear();
            return;
        }
        this.b$src$V$gc7j1i();
        EntityPlayerSP entityPlayerSP = eventPrePlayerTick.getThePlayer();
        if (entityPlayerSP.isNull() || entityPlayerSP.M$src$Z$ff28xj()) {
            if (this.V && this.K) {
                this.W();
            }
            this.Y = false;
            return;
        }
        if (!entityPlayerSP.p$src$Lgg_vape_wrapper_impl_Container_$1a6go00().isNull() && entityPlayerSP.p$src$Lgg_vape_wrapper_impl_Container_$1a6go00().getWindowId() != 0) {
            return;
        }
        GuiScreen guiScreen = Minecraft.currentScreen();
        boolean bl3 = bl2 = this.V && this.K;
        if (bl2) {
            this.H.reset();
        }
        boolean bl4 = bl = bl2 || guiScreen.isInstance(MappedClasses.YS) || guiScreen.isInstance(MappedClasses.n);
        if (this.s.L().booleanValue() && !bl) {
            this.O.clear();
        }
        if (this.a.L().booleanValue() && !bl) {
            this.O.clear();
            return;
        }
        if (!this.Z()) {
            return;
        }
        if (!this.O.isEmpty()) {
            if (this.I$src$Z$fygoax()) {
                InventoryClick inventoryClick = this.O.poll();
                if (inventoryClick != null) {
                    this.Y = true;
                    inventoryClick.k();
                    this.Y = false;
                }
                this.H.reset();
                this.d();
            }
            return;
        }
        if (this.I) {
            this.W();
            return;
        }
        ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(45).I();
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        if (itemMappingEntry != null && itemMappingEntry.M().toLowerCase().contains("totem_of_undying")) {
            if (this.V && this.O.isEmpty()) {
                this.I = true;
                this.G();
            }
            return;
        }
        int n = this.s$src$I$glk0tg();
        if (n != -1) {
            if (this.s.L().booleanValue() && !guiScreen.isInstance(MappedClasses.YS) && !bl2) {
                if (this.Z()) {
                    this.M$src$V$g0nukx();
                }
                return;
            }
            if (bl && !this.V) {
                this.V = true;
            }
            this.t(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), n, 40, 2);
            this.d();
        }
        if (this.V && this.O.isEmpty()) {
            this.I = true;
            this.G();
        }
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void r(EventRightClickMouse eventRightClickMouse) {
        if (this.p) {
            eventRightClickMouse.setCancelled(true);
        }
    }

    public AutoTotem() {
        super("AutoTotem", -43691, Category.M, "Automatically equips totems to your offhand");
        this.H = new TimerUtil();
        this.O = new ConcurrentLinkedQueue<InventoryClick>();
        this.s = BooleanValue.create(this, "Open inventory", true, "Opens your inventory to equip a totem");
        this.r = BooleanValue.create(this, "Silent open", false, "Silently opens your inventory to equip a totem");
        this.t = RandomValue.G(this, "Silent move delay", "#", "ms", 50.0, 100.0, 120.0, 200.0, 1.0, "Delay before preventing movement keys after silently opening the inventory");
        this.j = BooleanValue.create(this, "Close inventory", true, "Closes your inventory after equipping a totem");
        this.a = BooleanValue.create(this, "Inventory only", false, "Only equips a totem when in your inventory");
        this.P = BooleanValue.create(this, "Random slot", true, "Chooses a random totem slot from your inventory");
        this.L = RandomValue.G(this, "Delay", "#", "ms", 50.0, 100.0, 120.0, 200.0, 1.0, "How long to wait before equipping a totem");
        this.b = BooleanValue.create(this, "Extra randomization", true, "Adds human-like timing variance while equipping totems");
        this.oB = BooleanValue.create(this, "Show totem count", false, "Renders your totem count on the center of your screen");
        this.k = new Random();
        this.S = RotationManager.b;
        this.C = SharedModuleControlClaims.I;
        this.s.K(this.r, this.t, this.j);
        this.r.K(this.t);
        this.r.C().z(this.j);
        this.addValue(this.s, this.r, this.t, this.j, this.a, this.P, this.L, this.b, this.oB);
        this.C.l(this, 99);
    }

    private void d() {
        this.F.reset();
        AutoTotem autoTotem = this;
        this.c = Math.max(1L, (long)autoTotem.s());
    }

    private int s$src$I$glk0tg() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 9; i < 45; ++i) {
            ItemMappingEntry itemMappingEntry;
            ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I();
            if (itemStack.isNull() || (itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack)) == null || !itemMappingEntry.M().toLowerCase().contains("totem_of_undying")) continue;
            if (!this.P.L().booleanValue()) {
                return i;
            }
            arrayList.add(i);
        }
        if (arrayList.isEmpty()) {
            return -1;
        }
        return (Integer)arrayList.get(this.k.nextInt(arrayList.size()));
    }

    private int T() {
        int n;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        for (n = 36; n < 45; ++n) {
            if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().isNotNull()) continue;
            return n;
        }
        for (n = 9; n < 36; ++n) {
            if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().isNotNull()) continue;
            return n;
        }
        return -1;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void D(EventThreadBoundPostTick eventThreadBoundPostTick) {
        if (this.p) {
            eventThreadBoundPostTick.setCancelled(true);
        }
    }

    private boolean I$src$Z$fygoax() {
        if (this.c <= 0L) {
            AutoTotem autoTotem = this;
            this.c = Math.max(1L, (long)autoTotem.s());
        }
        return this.F.hasTimeElapsed(this.c);
    }

    private boolean M$src$Z$g0nuod() {
        if (D == null) {
            D = Vape.INSTANCE.getModManager().getMod(Freecam.class);
        }
        return D != null && D.r$src$Z$14eylz9() || this.C.e(this) && !this.C.h(this, true);
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void D(EventThreadBoundPreTick eventThreadBoundPreTick) {
        if (this.p) {
            eventThreadBoundPreTick.setCancelled(true);
        }
    }

    @Override
    public void onEnable() {
        ClientSettings.g(ActiveModuleStackFrame.class).c(this);
        this.G();
        this.d();
    }

    private void G() {
        this.v.reset();
        AutoTotem autoTotem = this;
        this.Z = Math.max(1L, (long)autoTotem.s());
    }

    private void b(GameSettings gameSettings) {
        if (this.p) {
            KeyBindingHelper.d(gameSettings.Y(), false);
            KeyBindingHelper.d(gameSettings.s(), false);
            KeyBindingHelper.d(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), false);
            KeyBindingHelper.d(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), false);
            KeyBindingHelper.d(gameSettings.O(), false);
            KeyBindingHelper.d(gameSettings.r(), false);
        }
    }
}

