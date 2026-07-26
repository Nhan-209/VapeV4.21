package gg.vape.module.combat;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPostPlayerTick;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.SPacketEntityVelocity;
import gg.vape.wrapper.impl.World;
import java.util.Random;

public class VelocityPacketReceiveMode
extends Mod {
    private final RandomValue F;
    public final NumberValue A;
    private static final long r = 4814624859532464040L;
    private boolean Z;
    private boolean s;
    private final BooleanValue O;
    private double p;
    private double S;
    private boolean J;
    private double a;
    public final BooleanValue P;
    private final Random b = new Random();

    private boolean a$src$Z$a6pr1a() {
        int n;
        if (this.Z) {
            return false;
        }
        if (Minecraft.thePlayer().i(PotionRegistry.Z)) {
            return false;
        }
        if (this.H()) {
            return false;
        }
        if (this.P.L().booleanValue()) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            boolean bl = RotationUtil.H(entityPlayerSP);
            boolean bl2 = RotationUtil.F(entityPlayerSP);
            if (!bl || !bl2) {
                return false;
            }
        }
        return (double)(n = MathUtil.randomExclusiveUpper(this.b, 0, 100)) >= 100.0 - (Double)this.A.K();
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        Packet packet = eventPacketReceive.getPacket();
        Packet.n(packet, this::lambda$onPacketReceived$0);
    }

    @EventHandler
    public void onPlayerTick(EventPostPlayerTick eventPostPlayerTick) {
        if (this.Z) {
            KeyBinding keyBinding = Minecraft.gameSettings().O();
            ClientSettings.b(keyBinding, false);
            Minecraft.gameSettings().O().setPressed(false);
            if (ForgeVersion.MC_1_21_4.v()) {
                Minecraft.thePlayer().movementInput().V(false);
            }
            this.Z = false;
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int[] nArray = World.a();
        if (entityPlayerSP.isNull()) {
            return;
        }
        if (!this.J) {
            return;
        }
        double d = MathUtil.roundToScale(entityPlayerSP.t(), 3);
        double d2 = MathUtil.roundToScale(entityPlayerSP.q(), 3);
        double d3 = MathUtil.roundToScale(entityPlayerSP.T(), 3);
        double d4 = MathUtil.roundToScale(this.S, 3);
        double d5 = MathUtil.roundToScale(this.a, 3);
        double d6 = MathUtil.roundToScale(this.p, 3);
        if (d == d4 && d2 == d5 && d3 == d6) {
            this.p = 0.0;
            this.a = 0.0;
            this.S = 0.0;
            this.J = false;
            if (!Minecraft.gameSettings().O().isKeyDown()) {
                this.s = true;
            }
        }
    }

    public boolean H() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return true;
        }
        return this.O.L() != false && entityPlayerSP.h$src$Z$ftwoya();
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.s) {
            double d;
            float f = MathUtil.randomExclusiveUpper(this.b, 0, 100);
            if ((double)f < 100.0 - (d = this.F.B())) {
                return;
            }
            KeyBinding keyBinding = Minecraft.gameSettings().O();
            ClientSettings.b(keyBinding, true);
            keyBinding.setPressed(true);
            if (ForgeVersion.MC_1_21_4.v()) {
                Minecraft.thePlayer().movementInput().V(true);
            }
            this.Z = true;
            this.s = false;
        }
    }

    private void lambda$onPacketReceived$0(Packet packet) {
        SPacketEntityVelocity sPacketEntityVelocity;
        if (packet.isInstance(MappedClasses.YX) && (sPacketEntityVelocity = new SPacketEntityVelocity(packet)).getEntityId() == Minecraft.thePlayer().S()) {
            boolean bl;
            boolean bl2 = bl = sPacketEntityVelocity.getMotionX() == 0 && sPacketEntityVelocity.getMotionZ() == 0 || sPacketEntityVelocity.getMotionY() < 0;
            if (!bl && this.a$src$Z$a6pr1a()) {
                this.S = (double)sPacketEntityVelocity.getMotionX() / 8000.0;
                this.a = (double)sPacketEntityVelocity.getMotionY() / 8000.0;
                this.p = (double)sPacketEntityVelocity.getMotionZ() / 8000.0;
                this.J = true;
            }
        }
    }

    @Override
    public String r() {
        return this.A.c() + "%";
    }

    public VelocityPacketReceiveMode() {
        super("JumpReset", (int)r, Category.g, "Reduces knockback taken by jumping when hit");
        this.P = BooleanValue.create(this, "Only when targeting", false, "Only reduce knockback while being face to face with opponent");
        this.F = RandomValue.G(this, "Accuracy", "#", "%", 0.0, 40.0, 60.0, 100.0, 1.0, "If you will jump, this is the chance that you will actually land a perfect jump reset on time");
        this.A = NumberValue.E(this, "Chance", "#", "%", 0.0, 40.0, 100.0, "Chance of reducing knockback");
        this.O = BooleanValue.create(this, "Water check", false, "Won't reduce knockback if in water");
        this.addValue(this.A, this.F, this.P, this.O);
        this.A.C(0);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

