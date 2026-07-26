package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventRender3D;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.EntityAngleComparator;
import gg.vape.utils.EntityArmorValueComparator;
import gg.vape.utils.EntityDistanceComparator;
import gg.vape.utils.EntityEquipmentValueComparator;
import gg.vape.utils.EntityHealthComparator;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.RenderUtil;
import gg.vape.utils.render.RenderUtils;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomClickDelayValue;
import gg.vape.value.Value;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import gg.vape.wrapper.impl.RenderWorldLastEvent;
import gg.vape.wrapper.impl.Screen;
import gg.vape.wrapper.impl.TitledScreen;
import gg.vape.wrapper.impl.WorldClient;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class KillAura
extends Mod {
    public int O;
    public final LimitValue H;
    public final BooleanValue Y;
    public final ModeOption a;
    public final NumberValue t;
    public final BooleanValue A;
    public final NumberValue Z;
    public final BooleanValue F;
    public final NumberValue V;
    public final NumberValue K;
    public final ModeOption v;
    public TimerUtil p;
    private final ColorValue P;
    public final BooleanValue j;
    public final RandomClickDelayValue J;
    public final ModeOption I;
    private static final long hb = 3138688411160482102L;
    private boolean b = false;
    public int r;
    public final EntityTargetFilterValue k = EntityTargetFilterValue.W(this);
    public ModeValue S;
    public final BooleanValue s;
    public final ModeOption U;
    private final ColorValue C;
    private final ModeOption c;
    public final BooleanValue o;
    public List<EntityLivingBase> D;

    public boolean D() {
        return this.r$src$Z$14eylz9() && !this.D.isEmpty();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private float[] getRotations(double d, double d2, double d3) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d4 = d - entityPlayerSP.z();
        double d5 = d2 - entityPlayerSP.h();
        double d6 = d3 - entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() - 1.2;
        double d7 = MathUtil.sqrt(d4 * d4 + d5 * d5);
        float f = (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d6, d7) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

    public EntityLivingBase b$src$Lgg_vape_wrapper_impl_EntityLivingBase_$1nar0oy() {
        if (this.D.isEmpty() || this.D.size() - 1 < this.O) {
            return null;
        }
        return this.D.get(this.O);
    }

    private boolean M(EntityLivingBase entityLivingBase) {
        if (this.o.L().booleanValue()) {
            ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
            if (itemStack.isNull()) {
                return false;
            }
            Item item = itemStack.getItem();
            if (item.isNull()) {
                return false;
            }
            return this.H.isValid(itemStack, false) && this.k.c(entityLivingBase);
        }
        return this.k.c(entityLivingBase);
    }

    @EventHandler(A=EventPriority.LOW)
    public void w(EventPrePlayerTick eventPrePlayerTick) {
        if ((Double)this.t.K() < (Double)this.Z.K()) {
            this.t.A((Double)this.Z.K() + 0.1);
        }
        if (this.r > 0) {
            --this.r;
            return;
        }
        if (this.Y.L().booleanValue() && Minecraft.currentScreen().isNotNull()) {
            this.r = 1;
            return;
        }
        boolean bl = ForgeVersion.MC_1_12_2.v();
        if (bl) {
            EntityPlayerSP entityPlayerSP = eventPrePlayerTick.getThePlayer();
            if (this.F.L().booleanValue()) {
                Screen screen;
                if (entityPlayerSP.M$src$Z$ff28xj() || entityPlayerSP.w$src$F$15l9epb() <= 0.0f) {
                    this.F();
                    return;
                }
                if (ForgeVersion.MC_1_16_5.d()) {
                    screen = Minecraft.currentScreen();
                    if (screen.isNotNull()) {
                        if (!this.b && screen.isInstance(MappedClasses.D2)) {
                            this.b = true;
                            this.F();
                            return;
                        }
                        this.b = false;
                    }
                } else {
                    screen = Minecraft.k();
                    if (screen.isNotNull()) {
                        String string = ((TitledScreen)screen).E();
                        if (!this.b && string != null && (string.toLowerCase().contains("died") || string.toLowerCase().contains("dead"))) {
                            this.b = true;
                            this.F();
                            return;
                        }
                        if (string == null || string.equals("")) {
                            this.b = false;
                        }
                    }
                }
            }
            if (this.s.L().booleanValue() && !ClientSettings.M()) {
                this.D.clear();
                return;
            }
            this.w(entityPlayerSP, eventPrePlayerTick.getWorld());
            if (!this.v()) {
                return;
            }
            boolean bl2 = false;
            for (EntityLivingBase entityLivingBase : this.D) {
                double d;
                if (!this.m(entityPlayerSP, entityLivingBase) || !((d = (double)entityPlayerSP.getDistanceToEntity(entityLivingBase)) <= (Double)this.t.K())) continue;
                bl2 = true;
                break;
            }
            if (!bl2) {
                return;
            }
            boolean bl3 = false;
            boolean bl4 = ClientSettings.V();
            KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
            if (bl4 && keyBinding.isKeyDown()) {
                keyBinding.setPressed(false);
                return;
            }
            for (EntityLivingBase entityLivingBase : this.D) {
                double d;
                if (!this.m(entityPlayerSP, entityLivingBase) || !((d = (double)entityPlayerSP.getDistanceToEntity(entityLivingBase)) <= (Double)this.t.K())) continue;
                entityPlayerSP.m$src$V$15frh5h();
                if (d <= (Double)this.Z.K()) {
                    Minecraft.playerController().attackEntity(entityPlayerSP, entityLivingBase);
                }
                if (!bl4) continue;
                bl3 = true;
                Minecraft.S();
            }
            if (!bl3 && bl4) {
                keyBinding.setPressed(true);
            }
            this.J.s();
            return;
        }
        EntityPlayerSP entityPlayerSP = eventPrePlayerTick.getThePlayer();
        if (this.F.L().booleanValue()) {
            Screen screen;
            if (entityPlayerSP.M$src$Z$ff28xj() || entityPlayerSP.w$src$F$15l9epb() <= 0.0f) {
                this.F();
                return;
            }
            if (ForgeVersion.MC_1_16_5.d()) {
                screen = Minecraft.currentScreen();
                if (screen.isNotNull()) {
                    if (!this.b && screen.isInstance(MappedClasses.D2)) {
                        this.b = true;
                        this.F();
                        return;
                    }
                    this.b = false;
                }
            } else {
                screen = Minecraft.k();
                if (screen.isNotNull()) {
                    String string = ((TitledScreen)screen).E();
                    if (!this.b && string != null && (string.toLowerCase().contains("died") || string.toLowerCase().contains("dead"))) {
                        this.b = true;
                        this.F();
                        return;
                    }
                    if (string == null || string.equals("")) {
                        this.b = false;
                    }
                }
            }
        }
        if (this.s.L().booleanValue() && !ClientSettings.M()) {
            this.D.clear();
            return;
        }
        this.w(entityPlayerSP, eventPrePlayerTick.getWorld());
        if (!this.v()) {
            return;
        }
        boolean bl5 = false;
        for (EntityLivingBase entityLivingBase : this.D) {
            double d;
            if (!this.m(entityPlayerSP, entityLivingBase) || !((d = (double)entityPlayerSP.getDistanceToEntity(entityLivingBase)) <= (Double)this.t.K())) continue;
            bl5 = true;
            break;
        }
        if (!bl5) {
            return;
        }
        boolean bl6 = false;
        boolean bl7 = ClientSettings.V();
        KeyBinding keyBinding = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362();
        if (bl7 && keyBinding.isKeyDown()) {
            keyBinding.setPressed(false);
            return;
        }
        for (EntityLivingBase entityLivingBase : this.D) {
            double d;
            if (!this.m(entityPlayerSP, entityLivingBase) || !((d = (double)entityPlayerSP.getDistanceToEntity(entityLivingBase)) <= (Double)this.t.K())) continue;
            if (d <= (Double)this.Z.K()) {
                Minecraft.playerController().attackEntity(entityPlayerSP, entityLivingBase);
                entityPlayerSP.m$src$V$15frh5h();
            } else {
                entityPlayerSP.m$src$V$15frh5h();
                entityPlayerSP.i$src$V$1imgzyw();
            }
            if (!bl7) continue;
            bl6 = true;
            Minecraft.S();
        }
        if (!bl6 && bl7) {
            keyBinding.setPressed(true);
        }
        this.J.s();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.D.clear();
    }

    public void w(EntityPlayerSP entityPlayerSP, WorldClient worldClient) {
        this.D.clear();
        ArrayList arrayList = new ArrayList(worldClient.z());
        for (Object e : arrayList) {
            EntityLivingBase entityLivingBase;
            Entity entity = new Entity(e);
            if (ClientSettings.H && entity.isInstance(MappedClasses.FT) || !entity.isInstance(MappedClasses.zm) || !this.m(entityPlayerSP, entityLivingBase = new EntityLivingBase(e))) continue;
            this.D.add(entityLivingBase);
        }
        if (this.S.K() == this.a) {
            this.D.sort(new EntityAngleComparator());
        } else if (this.S.K() == this.c) {
            this.D.sort(new EntityDistanceComparator());
        } else if (this.S.K() == this.U) {
            this.D.sort(new EntityArmorValueComparator());
        } else if (this.S.K() == this.v) {
            this.D.sort(new EntityEquipmentValueComparator());
        } else if (this.S.K() == this.I) {
            this.D.sort(new EntityHealthComparator());
        }
        ArrayList<EntityLivingBase> arrayList2 = new ArrayList<EntityLivingBase>(this.D);
        this.D.clear();
        int n = ((Double)this.K.K()).intValue();
        for (int i = 0; i < n && i < arrayList2.size(); ++i) {
            this.D.add((EntityLivingBase)arrayList2.get(i));
        }
    }

    public void x(double d, double d2, double d3, double d4) {
        double d5 = d3 / 2.0;
        double d6 = d + d5;
        double d7 = d2 + d5;
        double d8 = Math.toRadians(d4 -= 90.0);
        double d9 = d6 + (d5 + 0.0) * Math.cos(d8);
        double d10 = d7 + (d5 + 0.0) * Math.sin(d8);
        double d11 = d6 + (d5 - 4.0) * Math.cos(d8);
        double d12 = d7 + (d5 - 4.0) * Math.sin(d8);
        GuiRenderPrimitives.u(d9, d10, d11, d12, 2.0f, Color.RED);
    }

    private void w(Entity entity, double d, double d2, double d3) {
        double d4 = entity.M() + (entity.z() - entity.M()) - RenderManager.getInterpolatedRenderPosX();
        double d5 = entity.W() + (entity.N() + (double)entity.Y() * 0.75 - entity.W()) - RenderManager.getInterpolatedRenderPosY();
        double d6 = entity.m$src$D$fwnne5() + (entity.h() - entity.m$src$D$fwnne5()) - RenderManager.getInterpolatedRenderPosZ();
        double[] dArray = RenderUtil.W(d4, d5, d6);
        double d7 = dArray[2];
        float f = Minecraft.G().e();
        float f2 = RenderWorldLastEvent.getPartialTicks();
        float f3 = Minecraft.h();
        double d8 = dArray[0] / (double)f / (double)f2;
        double d9 = ((double)f3 - dArray[1] / (double)f2) / (double)f;
        if (d7 >= 1.0) {
            d8 = (double)(Minecraft.J() / 2) - d8;
            d9 = (double)(Minecraft.h() / 2) - d9;
        }
        GuiRenderPrimitives.Y();
        double d10 = 0.0;
        double d11 = d - d8;
        double d12 = d2 - d9;
        if (d12 > 0.0 && d11 > 0.0) {
            d10 = Math.toDegrees(-Math.atan(d11 / d12));
        } else if (d12 > 0.0 && d11 < 0.0) {
            d10 = Math.toDegrees(-Math.atan(d11 / d12));
        } else if (d12 < 0.0 && d11 > 0.0) {
            d10 = -90.0 + Math.toDegrees(Math.atan(d12 / d11));
        } else if (d12 < 0.0 && d11 < 0.0) {
            d10 = 90.0 + Math.toDegrees(Math.atan(d12 / d11));
        }
        GuiRenderPrimitives.C(d8, d9, 2.0, 2.0, Color.RED);
        this.x(d, d2, d3, d10);
        GuiRenderPrimitives.D();
    }

    @EventHandler(A=EventPriority.HIGH)
    public void s(EventPrePlayerTick eventPrePlayerTick) {
        if (this.F.L().booleanValue() && (eventPrePlayerTick.getPlayer().M$src$Z$ff28xj() || eventPrePlayerTick.getPlayer().w$src$F$15l9epb() <= 0.0f) && this.r$src$Z$14eylz9()) {
            this.F();
            return;
        }
    }

    public float[] getRotationsToEntity(EntityLivingBase entityLivingBase) {
        double d = entityLivingBase.z();
        double d2 = entityLivingBase.h();
        double d3 = entityLivingBase.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl().getMinY() + (double)(entityLivingBase.X() / 2.0f);
        return this.getRotations(d, d2, d3);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.D.clear();
    }

    @EventHandler(A=EventPriority.LOW)
    public void onRender3D(EventRender3D eventRender3D) {
        RenderUtils.g();
        if (this.A.L().booleanValue() && !this.D.isEmpty()) {
            EntityPlayerSP entityPlayerSP = eventRender3D.getThePlayer();
            for (EntityLivingBase entityLivingBase : this.D) {
                if ((double)entityPlayerSP.getDistanceToEntity(entityLivingBase) <= (Double)this.Z.K()) {
                    RenderUtil.k(entityLivingBase, 0.0, null, this.P.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), eventRender3D.getTicks());
                    continue;
                }
                RenderUtil.k(entityLivingBase, 0.0, null, this.C.q$src$Lgg_vape_utils_MutableColor_$1dowyd3(), eventRender3D.getTicks());
            }
        }
        RenderUtils.f();
    }

    public float degrees(double d, double d2, float f) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d3 = d - entityPlayerSP.z();
        double d4 = d2 - entityPlayerSP.h();
        double d5 = d4 < 0.0 && d3 < 0.0 ? 90.0 + Math.toDegrees(Math.atan(d4 / d3)) : (d4 < 0.0 && d3 > 0.0 ? -90.0 + Math.toDegrees(Math.atan(d4 / d3)) : Math.toDegrees(-Math.atan(d3 / d4)));
        return MathUtil.wrapAngleTo180(-(f - (float)d5));
    }

    public boolean v() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        boolean bl = ForgeVersion.MC_1_8_9.Y() && this.j.L().booleanValue() ? entityPlayerSP.getCooledAttackStrength(0.5f) == 1.0f : this.J.R();
        return bl;
    }

    public BooleanValue y$src$Lgg_vape_value_BooleanValue_$umhvzy() {
        return this.Y;
    }

    public boolean m(EntityPlayerSP entityPlayerSP, EntityLivingBase entityLivingBase) {
        if (entityLivingBase.isNull()) {
            return false;
        }
        if (entityLivingBase.equals(entityPlayerSP)) {
            return false;
        }
        if (!this.M(entityLivingBase)) {
            return false;
        }
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f || entityLivingBase.M$src$Z$ff28xj()) {
            return false;
        }
        if ((double)entityPlayerSP.getDistanceToEntity(entityLivingBase) >= (Double)this.t.K()) {
            return false;
        }
        if (RotationUtil.a(entityPlayerSP, entityLivingBase) > ((Double)this.V.K()).intValue() / 2) {
            return false;
        }
        if (Vape.INSTANCE.getFriendManager().isFriend(entityLivingBase)) {
            return false;
        }
        return !entityLivingBase.equals(entityPlayerSP.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12());
    }

    public KillAura() {
        super("Killaura", (int)hb, Category.w, "Attack players around you\nwithout aiming at them.");
        this.t = NumberValue.create(this, "Swing range", "#.#", "", 0.0, 4.0, 6.0);
        this.Z = NumberValue.create(this, "Attack range", "#.#", "", 0.0, 3.5, 6.0);
        this.s = BooleanValue.create(this, "Require mouse down", false);
        this.F = BooleanValue.create(this, "Disable on death", false);
        this.A = BooleanValue.create(this, "Show target", false);
        this.o = BooleanValue.create(this, "Limit to items", false, "Killaura functions only while holding selected items");
        this.Y = BooleanValue.create(this, "GUI check", true, "Does not attack when inside of a GUI.");
        this.j = BooleanValue.create(this, "Perfect swing", false, "Attacks perfectly on 1.9+ combat servers.\n(1.12.2 Only)");
        this.J = RandomClickDelayValue.M(this, "Attacks per Second", "#.#", "", 1.0, 6.0, 13.0, 20.0);
        this.V = NumberValue.create((Object)this, "Max angle", "#", "", 1.0, 90.0, 360.0, 5.0);
        this.K = NumberValue.create((Object)this, "Max targets", "#", "", 1.0, 1.0, 6.0, 1.0);
        this.C = ColorValue.b(this, "Target Color", new Color(255, 200, 112), 50);
        this.P = ColorValue.L(this, "Attack Color", new Color(255, 0, 0, 100));
        this.c = new ModeOption("Distance");
        this.a = new ModeOption("Yaw");
        this.v = new ModeOption("Armor");
        this.U = new ModeOption("Threat");
        this.I = new ModeOption("Health");
        this.H = LimitValue.n(this, "killaura-alloweditems", "Allowed Items", LimitValue.r, Collections.emptyList());
        this.p = new TimerUtil();
        this.D = new CopyOnWriteArrayList<EntityLivingBase>();
        this.S = ModeValue.create((Object)this, "Target Mode", "How Killaura should prioritize targets.\nArmor/Threat will default to Distance for non player targets.", (ModeSelection)this.c, this.c, this.a, this.v, this.U, this.I);
        this.addValue(this.k, this.J, this.t, this.Z, this.V, this.K, this.S);
        this.U(this.j, ForgeVersion.MC_1_8_9.N());
        this.A.K(this.C, this.P);
        this.addValue(new Value[]{this.F, this.s, this.Y, this.A, this.C, this.P, this.o.K(this.H), this.H});
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.t.B(numberValue -> {
            if (!atomicBoolean.get() && (Double)numberValue.K() < (Double)this.Z.K()) {
                atomicBoolean.set(true);
                numberValue.A((Double)this.Z.K() + numberValue.K$src$D$10kvp27());
                atomicBoolean.set(false);
            }
        });
        this.Z.B(numberValue -> {
            if (!atomicBoolean.get() && (Double)numberValue.K() > (Double)this.t.K()) {
                atomicBoolean.set(true);
                numberValue.A((Double)this.t.K() - numberValue.K$src$D$10kvp27());
                atomicBoolean.set(false);
            }
        });
    }
}
