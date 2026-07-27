package gg.vape.module.combat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.VelocityPacketMode;
import gg.vape.notification.NotificationType;
import gg.vape.unmap.NumberFormat;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.Vec3d;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.PacketVelocityBridge;
import gg.vape.wrapper.impl.SPacketEntityVelocity;
import java.util.Random;

public class Velocity
extends Mod {
    private final BooleanValue kiteMode;
    private Vec3d pendingVelocity = null;
    private final BooleanValue waterCheck;
    private static final long COLOR_ID = 2393379545127256196L;
    private final NumberValue kiteVertical;
    private final NumberValue vertical;
    private final BooleanValue onlyWhenTargeting = BooleanValue.create(this, "Only when targeting", false, "Only reduce knockback while being face to face with opponent");
    private final NumberValue chance;
    private final NumberValue ticks;
    private int ticksRemaining = 0;
    private final NumberValue horizontal;
    private final NumberValue kiteHorizontal;
    private final BooleanValue alwaysKite;

    private double[] computeReducedPercents() {
        double d;
        double d2 = (Double)this.horizontal.K();
        double d3 = (Double)this.vertical.K();
        Random random = new Random();
        double d4 = random.nextDouble();
        if (d2 > 0.0) {
            d = d2 + (d2 + 5.0 - d2) * d4;
            if (d >= 100.0) {
                d = 100.0;
            }
            d2 = d;
        }
        if (d3 > 0.0) {
            d = d3 + (d3 + 5.0 - d3) * d4;
            if (d >= 90.0) {
                d = 100.0;
            }
            d3 = d;
        }
        return new double[]{d2, d3};
    }

    private boolean shouldSkip() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return true;
        }
        return this.waterCheck.L() != false && entityPlayerSP.h$src$Z$ftwoya();
    }

    @Override
    public String r() {
        return this.horizontal.c() + "h " + this.vertical.c() + "v";
    }

    public Velocity() {
        super("Velocity", (int)COLOR_ID, Category.Y, "Reduces knockback taken");
        this.waterCheck = BooleanValue.create(this, "Water check", false, "Won't reduce knockback if in water");
        this.chance = NumberValue.E(this, "Chance", "#", "%", 0.0, 40.0, 100.0, "Chance of reducing knockback");
        this.kiteMode = BooleanValue.create(this, "Kite mode", false, "Increases knockback while not facing opponent");
        this.alwaysKite = BooleanValue.create(this, "Always Kite", false, "Increase knockback regardless if not facing opponent");
        this.horizontal = NumberValue.create(this, "Horizontal", "#", "%", 0.0, 90.0, 100.0);
        this.vertical = NumberValue.create(this, "Vertical", "#", "%", 0.0, 100.0, 100.0);
        this.ticks = NumberValue.create(this, "Ticks", "#", "", 0.0, 1.0, 10.0, 1.0, "How many ticks to wait before activating\nDoes not delay Kite");
        this.kiteHorizontal = NumberValue.create(this, "Kite horizontal", "#", "%", 100.0, 120.0, 300.0);
        this.kiteVertical = NumberValue.create(this, "Kite vertical", "#", "%", 100.0, 120.0, 300.0);
        this.kiteMode.K(this.kiteHorizontal, this.kiteVertical, this.alwaysKite);
        this.addValue(this.chance, this.horizontal, this.vertical, this.ticks, this.kiteMode, this.kiteHorizontal, this.kiteVertical, this.alwaysKite, this.onlyWhenTargeting, this.waterCheck);
        this.chance.C(0);
        this.vertical.C(0);
        this.horizontal.C(0);
    }

    private SPacketEntityVelocity buildVelocityPacket(SPacketEntityVelocity sPacketEntityVelocity, double d, double d2, double d3) {
        Object object = Vape.INSTANCE.getMappings().s.Y(sPacketEntityVelocity.getEntityId(), d, d2, d3);
        return new SPacketEntityVelocity(object);
    }

    private boolean rollChance() {
        int n = MathUtil.randomExclusiveUpper(new Random(), 0, 100);
        return (double)n >= 100.0 - (Double)this.chance.K();
    }

    private void applyVelocity(Packet packet, EventPacketReceive eventPacketReceive) {
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        double[] dArray;
        double d6;
        double d7;
        double d8;
        boolean bl;
        boolean bl2;
        boolean bl3;
        EntityPlayerSP entityPlayerSP;
        Packet packet2;
        if (packet.isInstance(MappedClasses.qe)) {
            packet2 = new PacketVelocityBridge(packet);
            entityPlayerSP = Minecraft.thePlayer();
            bl3 = RotationUtil.H(entityPlayerSP);
            bl2 = RotationUtil.F(entityPlayerSP);
            if (this.kiteMode.L().booleanValue() && this.alwaysKite.L().booleanValue()) {
                bl2 = false;
            }
            if (bl3 && !bl2 && !this.kiteMode.L().booleanValue() && this.onlyWhenTargeting.L().booleanValue()) {
                return;
            }
            bl = this.rollChance();
            if (bl) {
                d8 = ((PacketVelocityBridge)packet2).getMotionX();
                d7 = ((PacketVelocityBridge)packet2).getMotionY();
                d6 = ((PacketVelocityBridge)packet2).getMotionZ();
                if (bl3 && !bl2 && this.kiteMode.L().booleanValue()) {
                    double d9 = (Double)this.kiteHorizontal.K() / 100.0;
                    double d10 = (Double)this.kiteVertical.K() / 100.0;
                    double d11 = this.scaleMotion(d8, d9);
                    double d12 = this.scaleMotion(d7, d10);
                    double d13 = this.scaleMotion(d6, d9);
                    ((PacketVelocityBridge)packet2).setMotionX((float)d11);
                    ((PacketVelocityBridge)packet2).setMotionY((float)d12);
                    ((PacketVelocityBridge)packet2).setMotionZ((float)d13);
                    return;
                }
                if ((Double)this.ticks.K() > 0.0) {
                    boolean bl4;
                    boolean bl5 = bl4 = (double)Math.abs(((PacketVelocityBridge)packet2).getMotionX()) >= 0.005 || (double)Math.abs(((PacketVelocityBridge)packet2).getMotionY()) >= 0.005 || (double)Math.abs(((PacketVelocityBridge)packet2).getMotionZ()) >= 0.005;
                    if (bl4) {
                        this.ticksRemaining = ((Double)this.ticks.K()).intValue();
                        this.pendingVelocity = new Vec3d(((PacketVelocityBridge)packet2).getMotionX(), ((PacketVelocityBridge)packet2).getMotionY(), ((PacketVelocityBridge)packet2).getMotionZ());
                    }
                    return;
                }
                dArray = this.computeReducedPercents();
                d5 = dArray[0] / 100.0;
                d4 = dArray[1] / 100.0;
                d3 = this.scaleMotion(d8, d5);
                d2 = this.scaleMotion(d7, d4);
                d = this.scaleMotion(d6, d5);
                ((PacketVelocityBridge)packet2).setMotionX((float)d3);
                ((PacketVelocityBridge)packet2).setMotionY((float)d2);
                ((PacketVelocityBridge)packet2).setMotionZ((float)d);
                if (d5 == 0.0 && d4 == 0.0) {
                    ((PacketVelocityBridge)packet2).setMotionX(0.0f);
                    ((PacketVelocityBridge)packet2).setMotionY(0.0f);
                    ((PacketVelocityBridge)packet2).setMotionZ(0.0f);
                }
            }
        }
        if (packet.isInstance(MappedClasses.YX)) {
            packet2 = new SPacketEntityVelocity(packet);
            entityPlayerSP = Minecraft.thePlayer();
            if (entityPlayerSP.isNull()) {
                return;
            }
            if (((SPacketEntityVelocity)packet2).getEntityId() == entityPlayerSP.S()) {
                bl3 = RotationUtil.H(entityPlayerSP);
                bl2 = RotationUtil.F(entityPlayerSP);
                if (this.kiteMode.L().booleanValue() && this.alwaysKite.L().booleanValue()) {
                    bl2 = false;
                }
                if (!bl3 && !bl2 && !this.kiteMode.L().booleanValue() && this.onlyWhenTargeting.L().booleanValue()) {
                    return;
                }
                bl = this.rollChance();
                if (bl) {
                    d8 = ((SPacketEntityVelocity)packet2).getMotionX();
                    d7 = ((SPacketEntityVelocity)packet2).getMotionY();
                    d6 = ((SPacketEntityVelocity)packet2).getMotionZ();
                    if (ForgeVersion.MC_1_21_10.d()) {
                        d8 /= 8000.0;
                        d7 /= 8000.0;
                        d6 /= 8000.0;
                    }
                    if (bl3 && !bl2 && this.kiteMode.L().booleanValue()) {
                        double d14 = (Double)this.kiteHorizontal.K() / 100.0;
                        double d15 = (Double)this.kiteVertical.K() / 100.0;
                        double d16 = d8 * d14;
                        double d17 = d7 * d15;
                        double d18 = d6 * d14;
                        if (ForgeVersion.MC_26_1.d()) {
                            packet2 = this.buildVelocityPacket((SPacketEntityVelocity)packet2, d16, d17, d18);
                            this.replacePacket(eventPacketReceive, (SPacketEntityVelocity)packet2);
                        } else {
                            ((SPacketEntityVelocity)packet2).setMotionX(d16);
                            ((SPacketEntityVelocity)packet2).setMotionY(d17);
                            ((SPacketEntityVelocity)packet2).setMotionZ(d18);
                        }
                        return;
                    }
                    if ((Double)this.ticks.K() > 0.0) {
                        this.ticksRemaining = ((Double)this.ticks.K()).intValue();
                        this.pendingVelocity = new Vec3d((double)((SPacketEntityVelocity)packet2).getMotionX() / 8000.0, (double)((SPacketEntityVelocity)packet2).getMotionY() / 8000.0, (double)((SPacketEntityVelocity)packet2).getMotionZ() / 8000.0);
                        return;
                    }
                    dArray = this.computeReducedPercents();
                    d5 = dArray[0] / 100.0;
                    d4 = dArray[1] / 100.0;
                    d3 = d8 * d5;
                    d2 = d7 * d4;
                    d = d6 * d5;
                    if (ForgeVersion.MC_26_1.d()) {
                        packet2 = this.buildVelocityPacket((SPacketEntityVelocity)packet2, d3, d2, d);
                        this.replacePacket(eventPacketReceive, (SPacketEntityVelocity)packet2);
                    } else {
                        ((SPacketEntityVelocity)packet2).setMotionX(d3);
                        ((SPacketEntityVelocity)packet2).setMotionY(d2);
                        ((SPacketEntityVelocity)packet2).setMotionZ(d);
                    }
                    if (d5 == 0.0 && d4 == 0.0) {
                        if (ForgeVersion.MC_26_1.d()) {
                            packet2 = this.buildVelocityPacket((SPacketEntityVelocity)packet2, 0.0, 0.0, 0.0);
                            this.replacePacket(eventPacketReceive, (SPacketEntityVelocity)packet2);
                        } else {
                            ((SPacketEntityVelocity)packet2).setMotionX(0.0);
                            ((SPacketEntityVelocity)packet2).setMotionY(0.0);
                            ((SPacketEntityVelocity)packet2).setMotionZ(0.0);
                        }
                    }
                }
            }
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private double scaleMotion(double d, double d2) {
        String string = Double.toString(Math.abs(d));
        String string2 = string.contains(",") ? "," : ".";
        int n = string.indexOf(string2);
        int n2 = string.length() - n - 1;
        NumberFormat numberFormat = new NumberFormat(n2);
        boolean bl = d2 < 0.0;
        double d3 = Math.abs(d2);
        double d4 = d * d3;
        if (bl) {
            d4 = -d4;
        }
        return numberFormat.truncate(d4);
    }

    @Override
    public void loadJson(JsonObject jsonObject) {
        JsonArray jsonArray;
        JsonObject jsonObject2 = Vape.INSTANCE.getProfilesManager().M().V();
        if (jsonObject2 != null && jsonObject2.has("Velocity") && (jsonArray = jsonObject.getAsJsonArray("values")) != null) {
            for (JsonElement jsonElement : jsonArray) {
                VelocityPacketMode velocityPacketMode;
                JsonObject jsonObject3 = jsonElement.getAsJsonObject();
                String string = ConfigJsonUtils.P(jsonObject3, "id");
                if (string == null || !string.equals("Mode")) continue;
                String string2 = ConfigJsonUtils.P(jsonObject3, "value");
                if (string2 != null && string2.contains("Jump")) {
                    jsonObject2.remove("Velocity");
                    if (this.r$src$Z$14eylz9()) {
                        this.Y(false);
                    }
                    Vape.INSTANCE.getNotificationManager().t("Velocity disabled", "Velocity turned off since JumpReset mode is now a standalone module.", NotificationType.WARNING, 10000L);
                }
                if (string2 == null || !string2.contains("Lag")) continue;
                jsonObject2.remove("Velocity");
                if (this.r$src$Z$14eylz9()) {
                    this.Y(false);
                }
                if ((velocityPacketMode = Vape.INSTANCE.getModManager().getMod(VelocityPacketMode.class)) != null) {
                    velocityPacketMode.Y(true);
                }
                Vape.INSTANCE.getNotificationManager().t("Velocity disabled", "Velocity Lag mode is now KnockbackDelay under Network.\nKnockbackDelay has been enabled.", NotificationType.WARNING, 10000L);
            }
        }
        super.loadJson(jsonObject);
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        if (this.shouldSkip()) {
            return;
        }
        try {
            if (eventPacketReceive.getPacketInstance() == null) {
                return;
            }
            Packet packet = eventPacketReceive.getPacket();
            Packet.n(packet, arg_0 -> this.handlePacket(eventPacketReceive, arg_0));
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    private void handlePacket(EventPacketReceive eventPacketReceive, Packet packet) {
        this.applyVelocity(packet, eventPacketReceive);
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.shouldSkip()) {
            this.pendingVelocity = null;
            this.ticksRemaining = 0;
            return;
        }
        if (this.pendingVelocity != null) {
            if (this.ticksRemaining <= 0) {
                EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
                double[] dArray = this.computeReducedPercents();
                double d = dArray[0] / 100.0;
                double d2 = dArray[1] / 100.0;
                double d3 = entityPlayerSP.q();
                if (this.pendingVelocity.B != 0.0 && d3 > 0.0) {
                    entityPlayerSP.k(this.scaleMotion(entityPlayerSP.q(), d2));
                }
                entityPlayerSP.r(this.scaleMotion(entityPlayerSP.t(), d));
                entityPlayerSP.i(this.scaleMotion(entityPlayerSP.T(), d));
                this.pendingVelocity = null;
            }
            --this.ticksRemaining;
        }
    }

    private Packet replacePacket(EventPacketReceive eventPacketReceive, SPacketEntityVelocity sPacketEntityVelocity) {
        Packet packet = new Packet(sPacketEntityVelocity.getObject());
        eventPacketReceive.setPacket(packet);
        return packet;
    }
}

