package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventEntityJoinWorld;
import gg.vape.event.impl.EventPlayerUseItem;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.utility.autopearl.AutoPearlAimLock;
import gg.vape.module.utility.autopearl.AutoPearlPointRotationController;
import gg.vape.module.utility.autopearl.AutoPearlRotationController;
import gg.vape.module.utility.autopearl.AutoPearlState;
import gg.vape.module.utility.autopearl.AutoPearlTrackedPearl;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MathUtil;
import gg.vape.utils.ProjectilePitchUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityEnderPearl;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import gg.vape.wrapper.impl.Vec3;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AutoPearl
extends Mod {
    private final TimerUtil J;
    private final Set<Integer> k;
    private final BooleanValue F;
    private AutoPearlState v;
    private final ModeValue t;
    private static final float s = 10.0f;
    private final TimerUtil c;
    private final BooleanValue p;
    private static final long hb = -5394869744341811456L;
    private final ModeOption r = new ModeOption("Aggro");
    private int b;
    private static final boolean C = false;
    private final TimerUtil K;
    private final Map<Integer, AutoPearlTrackedPearl> a;
    private final TimerUtil O;
    private final NumberValue A;
    private final NumberValue L = NumberValue.create(this, "Aim speed", "#.#", "", 0.1, 5.0, 15.0, 0.5, "How quickly your aim moves towards the pearl");
    private final BooleanValue Y;
    private final ModeOption Z;
    private final RotationControlClaim I = SharedModuleControlClaims.I;
    private final NumberValue D;
    @Nullable
    private AutoPearlAimLock U = null;
    private final LimitValue H = LimitValue.N(this, "autopearl-alloweditems", "Allowed items", LimitValue.r, new ItemLimitData("swords"), new ItemLimitData("ender pearl"), new ItemLimitData("hand"));
    private final NumberValue P;
    private AutoPearlTrackedPearl j;
    private boolean o = false;
    private final NumberValue S;

    private boolean A(AutoPearlTrackedPearl autoPearlTrackedPearl) {
        EntityEnderPearl entityEnderPearl = autoPearlTrackedPearl.P();
        EntityPlayer entityPlayer = autoPearlTrackedPearl.A();
        if (!this.h$src$Z$ub31pp()) {
            return false;
        }
        if (!this.z()) {
            return false;
        }
        if (!this.j$src$Z$uc6mwf()) {
            return false;
        }
        if (this.d()) {
            return false;
        }
        if (this.e()) {
            return false;
        }
        if (!this.A$src$Z$jivob(entityEnderPearl)) {
            return false;
        }
        if (!this.L(entityPlayer)) {
            return false;
        }
        Vec3 vec3 = autoPearlTrackedPearl.a();
        if (!this.K(vec3, entityPlayer)) {
            return false;
        }
        if (!this.K$src$Z$1prbjwv(vec3)) {
            return false;
        }
        return this.S(entityEnderPearl);
    }

    private void V$src$V$u16qxr() {
        if (this.U != null && RotationManager.b.w() == this.U.r()) {
            RotationManager.b.v(this.U.r());
        }
        this.I.X(this);
        this.U = null;
        this.j = null;
        this.v = AutoPearlState.ACQUIRING_PEARL;
        this.K.reset();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean L(EntityPlayer entityPlayer) {
        return !entityPlayer.isInstance(MappedClasses.z5);
    }

    private boolean r(Vec3 vec3) {
        double d;
        double d2;
        double d3 = vec3.getX();
        double d4 = vec3.getY();
        double d5 = vec3.getZ();
        float f = Math.abs(this.t(d3, d5));
        if ((double)f > (d2 = (Double)this.S.K() / 2.0)) {
            return false;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        double d6 = entityPlayerSP.i(d3, d4, d5);
        if (d6 <= (d = ((Double)this.P.K()).doubleValue())) {
            return false;
        }
        if (!this.F.L().booleanValue()) {
            return true;
        }
        double d7 = entityPlayerSP.N();
        double d8 = d4 - d7;
        return d8 < 7.0;
    }

    @Override
    public void onDisable() {
        this.V$src$V$u16qxr();
    }

    private void w(AutoPearlTrackedPearl autoPearlTrackedPearl) {
        this.K.reset();
        Vec3 vec3 = autoPearlTrackedPearl.a();
        FixedRotationController fixedRotationController = this.Q(vec3);
        this.U = new AutoPearlAimLock(autoPearlTrackedPearl.P(), autoPearlTrackedPearl.A(), fixedRotationController, vec3, null);
        this.v = AutoPearlState.ACQUIRING_AIMLOCK;
    }

    @EventHandler
    public void m(EventWorldChange eventWorldChange) {
        this.V$src$V$u16qxr();
    }

    private boolean d() {
        long l = ((Double)this.A.K()).longValue();
        if ((double)l <= 0.0) {
            return false;
        }
        long l2 = this.J.getLastMS();
        long l3 = l2 / 1000L;
        return l3 < l;
    }

    private boolean A$src$Z$jivob(EntityEnderPearl entityEnderPearl) {
        if (entityEnderPearl.b$src$Z$fqlxe4()) {
            return false;
        }
        return !entityEnderPearl.M$src$Z$ff28xj();
    }

    private boolean z() {
        Item item;
        if (!this.p.L().booleanValue()) {
            return true;
        }
        ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
        if (!itemStack.isNull() && (item = itemStack.getItem()).isInstance(MappedClasses.ZH)) {
            return true;
        }
        return this.H.isValid(itemStack, false);
    }

    @EventHandler
    public void C(EventWorldChange eventWorldChange) {
        this.V$src$V$u16qxr();
    }

    @Override
    public void onEnable() {
        this.K.reset();
        if (this.Z.o()) {
            this.V$src$V$u16qxr();
        }
    }

    private boolean j$src$Z$uc6mwf() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || entityPlayerSP.M$src$Z$ff28xj()) {
            return false;
        }
        float f = ((Double)this.D.K()).floatValue();
        float f2 = entityPlayerSP.w$src$F$15l9epb();
        return f2 >= f;
    }

    @Nullable
    private FixedRotationController Q(Vec3 vec3) {
        FixedRotationController fixedRotationController;
        Float f = this.K(vec3);
        if (f == null) {
            return null;
        }
        FixedRotationController fixedRotationController2 = fixedRotationController = this.Y.L() != false ? new AutoPearlRotationController(this, f) : new AutoPearlPointRotationController(this, vec3, f);
        if (fixedRotationController instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)fixedRotationController).J(vec3);
        }
        fixedRotationController.k(true);
        fixedRotationController.t(0.5f);
        fixedRotationController.A(false);
        fixedRotationController.U(true);
        fixedRotationController.w(false);
        fixedRotationController.z(true);
        fixedRotationController.s(true);
        fixedRotationController.Y(((Double)this.L.K()).floatValue() * 0.2f);
        fixedRotationController.D(true);
        if (fixedRotationController instanceof AdaptiveRotationController) {
            ((AdaptiveRotationController)fixedRotationController).b(false);
        }
        return fixedRotationController;
    }

    @EventHandler
    public void J(EventPlayerUseItem eventPlayerUseItem) {
        if (((Double)this.A.K()).longValue() <= 0L) {
            return;
        }
        ItemStack itemStack = eventPlayerUseItem.getItemStack();
        if (itemStack.isNull()) {
            return;
        }
        Item item = itemStack.getItem();
        if (item.isNull() || !item.isInstance(MappedClasses.ZH)) {
            return;
        }
        this.O.reset();
    }

    private boolean K(@NotNull Vec3 vec3, @NotNull EntityPlayer entityPlayer) {
        double d;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        double d2 = entityPlayerSP.i(vec3.getX(), vec3.getY(), vec3.getZ());
        return d2 > (d = (double)entityPlayerSP.getDistanceToEntity(entityPlayer));
    }

    @Nullable
    private EntityPlayer A(EntityEnderPearl entityEnderPearl) {
        List list = Minecraft.theWorld().S();
        EntityPlayer entityPlayer = null;
        float f = Float.MAX_VALUE;
        for (Object e : list) {
            EntityPlayer entityPlayer2;
            float f2;
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.Yl) || !((f2 = (entityPlayer2 = new EntityPlayer(entity)).getDistanceToEntity(entityEnderPearl)) < f) || !(f2 <= 10.0f)) continue;
            entityPlayer = entityPlayer2;
            f = f2;
        }
        return entityPlayer;
    }

    private boolean e() {
        if (this.U == null) {
            return false;
        }
        return !AutoPearlAimLock.E(this.U).V$src$Z$lb4tvc();
    }

    private float t(double d, double d2) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d3 = d - entityPlayerSP.z();
        double d4 = d2 - entityPlayerSP.h();
        double d5 = d4 < 0.0 && d3 < 0.0 ? 90.0 + Math.toDegrees(Math.atan(d4 / d3)) : (d4 < 0.0 && d3 > 0.0 ? -90.0 + Math.toDegrees(Math.atan(d4 / d3)) : Math.toDegrees(-Math.atan(d3 / d4)));
        return MathUtil.wrapAngleTo180(-(entityPlayerSP.J() - (float)d5));
    }

    private boolean S(EntityEnderPearl entityEnderPearl) {
        return !this.k.contains(entityEnderPearl.S());
    }

    @Nullable
    private AutoPearlTrackedPearl X(EntityEnderPearl entityEnderPearl) {
        AutoPearlTrackedPearl autoPearlTrackedPearl = this.a.get(entityEnderPearl.S());
        if (autoPearlTrackedPearl != null) {
            return autoPearlTrackedPearl;
        }
        EntityPlayer entityPlayer = this.A(entityEnderPearl);
        if (entityPlayer == null || entityPlayer.isNull()) {
            return null;
        }
        autoPearlTrackedPearl = new AutoPearlTrackedPearl(entityEnderPearl, entityPlayer, null);
        this.a.put(entityEnderPearl.S(), autoPearlTrackedPearl);
        return autoPearlTrackedPearl;
    }

    private int u$src$I$ui8d0r() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return -1;
        }
        Container container = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        if (container.isNull()) {
            return -1;
        }
        InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (inventoryPlayer.isNull()) {
            return -1;
        }
        for (int i = 36; i < 45; ++i) {
            Item item;
            Slot slot = container.getSlot(i);
            if (!slot.v() || !(item = slot.I().getItem()).isInstance(MappedClasses.ZH)) continue;
            return i;
        }
        return -1;
    }

    public void J(AutoPearlTrackedPearl autoPearlTrackedPearl) {
        if (!this.A(autoPearlTrackedPearl)) {
            return;
        }
        this.w(autoPearlTrackedPearl);
    }

    @Nullable
    private Float K(Vec3 vec3) {
        float f;
        double d;
        double d2;
        double d3;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP == null || entityPlayerSP.isNull()) {
            return null;
        }
        float f2 = entityPlayerSP.J();
        float f3 = entityPlayerSP.V();
        double d4 = entityPlayerSP.z();
        double d5 = entityPlayerSP.U();
        double d6 = entityPlayerSP.h();
        float f4 = ProjectilePitchUtil.calculatePitch(d4 -= (double)(MathUtil.cos(f2 / 180.0f * (float)Math.PI) * 0.16f), d5 -= (double)0.1f, d6 -= (double)(MathUtil.sin(f2 / 180.0f * (float)Math.PI) * 0.16f), d3 = vec3.getX(), d2 = vec3.getY(), d = vec3.getZ());
        if (Float.isNaN(f4)) {
            return null;
        }
        float f5 = -90.0f;
        float f6 = 90.0f;
        float f7 = f = f4 < 0.0f ? f4 - 5.0f : (f4 > 0.0f ? f4 + 5.0f : f4);
        if (f < f5) {
            f = f5;
        } else if (f > f6) {
            f = f6;
        }
        return Float.valueOf(f);
    }

    @Nullable
    private List<AutoPearlTrackedPearl> o$src$Ljava_util_List_$xf97uf() {
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return null;
        }
        ArrayList<EntityEnderPearl> arrayList = new ArrayList<EntityEnderPearl>();
        ArrayList<EntityPlayer> arrayList2 = new ArrayList<EntityPlayer>();
        for (Object object : worldClient.S()) {
            if (MappedClasses.Zg.isInstance(object)) {
                arrayList.add(new EntityEnderPearl(object));
                continue;
            }
            if (!MappedClasses.Yl.isInstance(object)) continue;
            arrayList2.add(new EntityPlayer(object));
        }
        ArrayList arrayList3 = new ArrayList();
        for (EntityEnderPearl entityEnderPearl : arrayList) {
            AutoPearlTrackedPearl autoPearlTrackedPearl = this.a.get(entityEnderPearl.S());
            if (autoPearlTrackedPearl != null) {
                arrayList3.add(autoPearlTrackedPearl);
                continue;
            }
            float f = Float.MAX_VALUE;
            EntityPlayer entityPlayer = null;
            for (EntityPlayer entityPlayer2 : arrayList2) {
                float f2 = entityPlayer2.getDistanceToEntity(entityEnderPearl);
                if (!(f2 < f) || !(f2 <= 10.0f)) continue;
                f = f2;
                entityPlayer = entityPlayer2;
            }
            if (entityPlayer == null) continue;
            autoPearlTrackedPearl = new AutoPearlTrackedPearl(entityEnderPearl, entityPlayer, null);
            this.a.put(entityEnderPearl.S(), autoPearlTrackedPearl);
            arrayList3.add(autoPearlTrackedPearl);
        }
        Collections.reverse(arrayList3);
        return arrayList3;
    }

    @EventHandler
    public void x(EventEntityJoinWorld eventEntityJoinWorld) {
        Entity entity = eventEntityJoinWorld.getEntity();
        if (!entity.isInstance(MappedClasses.Zg)) {
            return;
        }
        EntityEnderPearl entityEnderPearl = new EntityEnderPearl(entity.getObject());
        AutoPearlTrackedPearl autoPearlTrackedPearl = this.X(entityEnderPearl);
        if (autoPearlTrackedPearl == null) {
            return;
        }
        EntityPlayer entityPlayer = autoPearlTrackedPearl.A();
        if (!entityPlayer.isNull()) {
            if (entityPlayer.isInstance(MappedClasses.z5)) {
                if (((Double)this.A.K()).longValue() > 0L) {
                    long l = 1000L;
                    long l2 = this.O.getLastMS();
                    if (l2 <= 1000L) {
                        this.J.reset();
                    }
                }
            } else {
                this.j = autoPearlTrackedPearl;
            }
        }
    }

    private boolean h$src$Z$ub31pp() {
        int n = this.u$src$I$ui8d0r();
        return n != -1;
    }

    private boolean B$src$Z$tq6v5z() {
        if (!this.z()) {
            return false;
        }
        if (!this.j$src$Z$uc6mwf()) {
            return false;
        }
        if (this.U != null) {
            Vec3 vec3 = this.U.b();
            return vec3 == null || vec3.isNull() || this.K$src$Z$1prbjwv(vec3);
        }
        return true;
    }

    private void k(String string) {
    }

    private boolean K$src$Z$1prbjwv(Vec3 vec3) {
        if (vec3 == null || vec3.isNull()) {
            return false;
        }
        if (!this.r(vec3)) {
            return false;
        }
        Float f = this.K(vec3);
        return f != null;
    }

    public AutoPearl() {
        super("AutoPearl", (int)hb, Category.Y, "Aims and throws a pearl at an enemies pearl trajectory.");
        this.a = new HashMap<Integer, AutoPearlTrackedPearl>();
        this.k = new HashSet<Integer>();
        this.c = new TimerUtil();
        this.A = NumberValue.E(this, "Pearl cooldown", "#.#", "sec", 0.0, 1.0, 15.0, "Minimum delay between pearl throws");
        this.P = NumberValue.create(this, "Distance limit", "#.#", "m", 0.0, 6.0, 10.0, 0.1, "The minimum distance a pearl needs to land away from you\nin order to pearl towards it.");
        this.S = NumberValue.create(this, "Angle limit", "#", "", 30.0, 180.0, 360.0, 5.0, "Maximum angle from your crosshair a pearl can be\nin order to be chased");
        this.p = BooleanValue.create(this, "Limit to items", true, "AutoPearl only functions while holding selected items");
        this.D = NumberValue.create(this, "Min health", "#", "HP", 1.0, 5.0, 20.0, 1.0, "Minimum amount of health you must have\nin order to throw a pearl");
        this.Z = new ModeOption("On bind");
        this.t = ModeValue.create((Object)this, "Mode", "On bind - searches for thrown pearls and throws upon pressing bind\nAggro - Throws pearl as soon as enemy throws theirs", (ModeSelection)this.Z, this.Z, this.r);
        this.Y = BooleanValue.create(this, "Silent aim", false, "Uses Silent Aim system");
        this.K = new TimerUtil();
        this.J = new TimerUtil();
        this.O = new TimerUtil();
        this.F = BooleanValue.create(this, "Vertical check", false, "Doesn't attempt to chase pearls that are landing a certain amount above your current Y position.");
        this.v = AutoPearlState.ACQUIRING_PEARL;
        this.p.K(this.H);
        this.p.l(this.H);
        this.addValue(this.t, this.L, this.S, this.D, this.P, this.F, this.A, this.Y, this.p, this.H);
        this.I.l(this, 7);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        AutoPearlState autoPearlState = this.v;
        boolean bl = false;
        if (!this.v.equals((Object)AutoPearlState.ACQUIRING_PEARL) && this.K.hasTimeElapsed(5000L)) {
            this.V$src$V$u16qxr();
        }
        block7: do {
            autoPearlState = this.v;
            if (!(this.v.equals((Object)AutoPearlState.ACQUIRING_PEARL) || this.v.equals((Object)AutoPearlState.PENDING_RESET) || this.B$src$Z$tq6v5z())) {
                this.V$src$V$u16qxr();
                bl = true;
                break;
            }
            switch (this.v) {
                case ACQUIRING_PEARL: {
                    if (this.U != null) {
                        this.v = AutoPearlState.ACQUIRING_AIMLOCK;
                        break;
                    }
                    if (this.Z.o()) {
                        List<AutoPearlTrackedPearl> trackedPearls = this.o$src$Ljava_util_List_$xf97uf();
                        for (AutoPearlTrackedPearl trackedPearl : trackedPearls) {
                            if (!this.A(trackedPearl)) continue;
                            this.J(trackedPearl);
                            break;
                        }
                        if (!this.v.equals((Object)autoPearlState)) continue block7;
                        this.Y(false);
                        break;
                    }
                    if (this.j == null) break;
                    if (this.A(this.j)) {
                        this.J(this.j);
                    }
                    this.j = null;
                    break;
                }
                case ACQUIRING_AIMLOCK: {
                    boolean bl2;
                    boolean bl3 = bl2 = this.I.U(this) || this.I.h(this, this.Y.L());
                    if (!bl2) continue block7;
                    RotationManager.b.S(this.U.r());
                    this.k.add(this.U.P().S());
                    this.v = AutoPearlState.PENDING_AIMJOB;
                    break;
                }
                case PENDING_AIMJOB: {
                    if (!this.U.r().V$src$Z$lb4tvc()) break;
                    this.v = AutoPearlState.PENDING_THROW;
                    this.c.reset();
                    break;
                }
                case PENDING_THROW: {
                    if (!this.c.hasTimeElapsed(100L)) break;
                    Object object3 = Minecraft.thePlayer();
                    Object object2 = ((EntityPlayer)object3).V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
                    if (((Wrapper)object3).isNull() || ((Wrapper)object2).isNull()) continue block7;
                    int n = this.u$src$I$ui8d0r();
                    if (n == -1) {
                        this.Y(false);
                        break;
                    }
                    int n2 = n - 36;
                    this.b = ((InventoryPlayer)object2).v();
                    ((InventoryPlayer)object2).g(n2);
                    KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBindingHelper.v(keyBinding, true, true);
                    this.o = true;
                    this.J.reset();
                    this.v = AutoPearlState.PENDING_RESET;
                    bl = true;
                    break;
                }
                case PENDING_RESET: {
                    Object object3 = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
                    KeyBindingHelper.v((KeyBinding)object3, false, false);
                    Object object2 = Minecraft.thePlayer();
                    Object object = ((EntityPlayer)object2).V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
                    if (((Wrapper)object2).isNull() || ((Wrapper)object).isNull()) continue block7;
                    ((InventoryPlayer)object).g(this.b);
                    this.V$src$V$u16qxr();
                    if (!this.Z.o()) break;
                    this.Y(false);
                    bl = true;
                }
            }
        } while (!autoPearlState.equals((Object)this.v) && !bl);
    }
}
