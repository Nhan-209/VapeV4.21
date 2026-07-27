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
    private final RandomValue accuracy;
    public final NumberValue chance;
    private static final long MODULE_ID = 4814624859532464040L;
    private boolean jumping;
    private boolean shouldJump;
    private final BooleanValue waterCheck;
    private double motionZ;
    private double motionX;
    private boolean waitingForReset;
    private double motionY;
    public final BooleanValue onlyWhenTargeting;
    private final Random random = new Random();

    private boolean shouldReduce() {
        int roll;
        if (this.jumping) {
            return false;
        }
        if (Minecraft.thePlayer().i(PotionRegistry.Z)) {
            return false;
        }
        if (this.isInWater()) {
            return false;
        }
        if (this.onlyWhenTargeting.L().booleanValue()) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            boolean facingYaw = RotationUtil.H(entityPlayerSP);
            boolean facingPitch = RotationUtil.F(entityPlayerSP);
            if (!facingYaw || !facingPitch) {
                return false;
            }
        }
        return (double)(roll = MathUtil.randomExclusiveUpper(this.random, 0, 100)) >= 100.0 - (Double)this.chance.K();
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        Packet packet = eventPacketReceive.getPacket();
        Packet.n(packet, this::handleVelocityPacket);
    }

    @EventHandler
    public void onPlayerTick(EventPostPlayerTick eventPostPlayerTick) {
        if (this.jumping) {
            KeyBinding keyBinding = Minecraft.gameSettings().O();
            ClientSettings.b(keyBinding, false);
            Minecraft.gameSettings().O().setPressed(false);
            if (ForgeVersion.MC_1_21_4.v()) {
                Minecraft.thePlayer().movementInput().V(false);
            }
            this.jumping = false;
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int[] nArray = World.a();
        if (entityPlayerSP.isNull()) {
            return;
        }
        if (!this.waitingForReset) {
            return;
        }
        double posX = MathUtil.roundToScale(entityPlayerSP.t(), 3);
        double posY = MathUtil.roundToScale(entityPlayerSP.q(), 3);
        double posZ = MathUtil.roundToScale(entityPlayerSP.T(), 3);
        double expectedX = MathUtil.roundToScale(this.motionX, 3);
        double expectedY = MathUtil.roundToScale(this.motionY, 3);
        double expectedZ = MathUtil.roundToScale(this.motionZ, 3);
        if (posX == expectedX && posY == expectedY && posZ == expectedZ) {
            this.motionZ = 0.0;
            this.motionY = 0.0;
            this.motionX = 0.0;
            this.waitingForReset = false;
            if (!Minecraft.gameSettings().O().isKeyDown()) {
                this.shouldJump = true;
            }
        }
    }

    public boolean isInWater() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return true;
        }
        return this.waterCheck.L() != false && entityPlayerSP.h$src$Z$ftwoya();
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.shouldJump) {
            double accuracyChance;
            float roll = MathUtil.randomExclusiveUpper(this.random, 0, 100);
            if ((double)roll < 100.0 - (accuracyChance = this.accuracy.B())) {
                return;
            }
            KeyBinding keyBinding = Minecraft.gameSettings().O();
            ClientSettings.b(keyBinding, true);
            keyBinding.setPressed(true);
            if (ForgeVersion.MC_1_21_4.v()) {
                Minecraft.thePlayer().movementInput().V(true);
            }
            this.jumping = true;
            this.shouldJump = false;
        }
    }

    private void handleVelocityPacket(Packet packet) {
        SPacketEntityVelocity sPacketEntityVelocity;
        if (packet.isInstance(MappedClasses.YX) && (sPacketEntityVelocity = new SPacketEntityVelocity(packet)).getEntityId() == Minecraft.thePlayer().S()) {
            boolean upward;
            boolean upwardFlag = upward = sPacketEntityVelocity.getMotionX() == 0 && sPacketEntityVelocity.getMotionZ() == 0 || sPacketEntityVelocity.getMotionY() < 0;
            if (!upward && this.shouldReduce()) {
                this.motionX = (double)sPacketEntityVelocity.getMotionX() / 8000.0;
                this.motionY = (double)sPacketEntityVelocity.getMotionY() / 8000.0;
                this.motionZ = (double)sPacketEntityVelocity.getMotionZ() / 8000.0;
                this.waitingForReset = true;
            }
        }
    }

    @Override
    public String r() {
        return this.chance.c() + "%";
    }

    public VelocityPacketReceiveMode() {
        super("JumpReset", (int)MODULE_ID, Category.g, "Reduces knockback taken by jumping when hit");
        this.onlyWhenTargeting = BooleanValue.create(this, "Only when targeting", false, "Only reduce knockback while being face to face with opponent");
        this.accuracy = RandomValue.G(this, "Accuracy", "#", "%", 0.0, 40.0, 60.0, 100.0, 1.0, "If you will jump, this is the chance that you will actually land a perfect jump reset on time");
        this.chance = NumberValue.E(this, "Chance", "#", "%", 0.0, 40.0, 100.0, "Chance of reducing knockback");
        this.waterCheck = BooleanValue.create(this, "Water check", false, "Won't reduce knockback if in water");
        this.addValue(this.chance, this.accuracy, this.onlyWhenTargeting, this.waterCheck);
        this.chance.C(0);
    }

}

