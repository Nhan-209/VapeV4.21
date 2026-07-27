package gg.vape.module.render.freecam;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventEntityRendererMouseUpdate;
import gg.vape.event.impl.EventKeyBindingState;
import gg.vape.event.impl.EventPostEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPreLivingTravel;
import gg.vape.event.impl.EventPreLocalPlayerTick;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.KeyboardInput;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.render.Freecam;
import gg.vape.module.render.freecam.FreecamController;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.MovementInput;
import gg.vape.wrapper.impl.Vec3;
import java.util.UUID;

public class FreecamModernController
extends FreecamController<Freecam> {
    private float cameraPitch;
    private float savedPrevRenderYaw;
    private float savedRenderYaw;
    private float bodyPitch;
    private double posX;
    private double lastMouseY;
    private float savedPitch;
    private float cameraYaw;
    private double posY;
    private double prevPosZ;
    private double prevPosY;
    private float savedYaw;
    private float savedPrevPitch;
    private double prevPosX;
    private float savedPrevRenderPitch;
    private float bodyYaw;
    private double lastMouseX;
    private boolean viewBobSaved;
    private boolean savedViewBobbing;
    private boolean active;
    private float savedRenderPitch;
    private double posZ;
    private boolean rotationSaved;
    private float savedPrevYaw;

    private void syncMouseHelper() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        entityPlayerSP.E().W();
    }

    private void handleMovement() {
        double speed;
        double magnitude;
        double vertical;
        double strafe;
        double forward;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        double d6 = forward = ClientSettings.B(Minecraft.gameSettings().Y()) ? 1.0 : 0.0;
        if (ClientSettings.B(Minecraft.gameSettings().s())) {
            forward -= 1.0;
        }
        double d7 = strafe = ClientSettings.B(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()) ? 1.0 : 0.0;
        if (ClientSettings.B(Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3())) {
            strafe -= 1.0;
        }
        double d8 = vertical = ClientSettings.B(Minecraft.gameSettings().O()) ? 1.0 : 0.0;
        if (ClientSettings.B(Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0())) {
            vertical -= 1.0;
        }
        if ((magnitude = Math.sqrt(forward * forward + strafe * strafe)) > 0.0) {
            forward /= magnitude;
            strafe /= magnitude;
            speed = (Double)((Freecam)this.n).Y.K() / 5.0;
            if (ClientSettings.B(Minecraft.gameSettings().r())) {
                speed *= 2.0;
            }
            double yawRadians = Math.toRadians(this.cameraYaw + 90.0f);
            this.posX += (forward * Math.cos(yawRadians) + strafe * Math.sin(yawRadians)) * speed;
            this.posZ += (forward * Math.sin(yawRadians) - strafe * Math.cos(yawRadians)) * speed;
        }
        if (vertical != 0.0) {
            speed = (Double)((Freecam)this.n).v.K() / 5.0 * 0.42;
            if (ClientSettings.B(Minecraft.gameSettings().r())) {
                speed *= 2.0;
            }
            this.posY += vertical * speed;
        }
    }

    public FreecamModernController(Freecam freecam) {
        super(freecam);
    }

    private void updateFakePlayer() {
        EntityPlayerSP entityPlayerSP;
        if (!((Freecam)this.n).A.L().booleanValue()) {
            ((Freecam)this.n).Z();
            return;
        }
        if (((Freecam)this.n).Z != null && (((Freecam)this.n).Z.M$src$Z$ff28xj() || ((Freecam)this.n).H == 0 || Minecraft.theWorld().V(((Freecam)this.n).H).isNull())) {
            ClientSettings.I(((Freecam)this.n).H);
            ((Freecam)this.n).H = 0;
            ((Freecam)this.n).Z = null;
        }
        if (((Freecam)this.n).Z == null) {
            this.spawnFakePlayer();
        }
        if ((entityPlayerSP = Minecraft.thePlayer()).isNull() || ((Freecam)this.n).Z == null) {
            return;
        }
        ((Freecam)this.n).Z.t(entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
        ((Freecam)this.n).Z.n(entityPlayerSP.f());
        ((Freecam)this.n).Z.w(entityPlayerSP.H());
        ((Freecam)this.n).Z.A(entityPlayerSP.R());
        ((Freecam)this.n).Z.C(entityPlayerSP.M());
        ((Freecam)this.n).Z.L(entityPlayerSP.W());
        ((Freecam)this.n).Z.s(entityPlayerSP.m$src$D$fwnne5());
        ((Freecam)this.n).Z.H(entityPlayerSP.J());
        ((Freecam)this.n).Z.D(entityPlayerSP.j());
        ((Freecam)this.n).Z.z(entityPlayerSP.s());
        ((Freecam)this.n).Z.o(entityPlayerSP.P$src$F$14ztfk8());
        ((Freecam)this.n).Z.C(entityPlayerSP.V());
        ((Freecam)this.n).Z.l(entityPlayerSP.D());
        ((Freecam)this.n).Z.U(entityPlayerSP.b$src$Z$fqlxe4());
        ((Freecam)this.n).Z.E(entityPlayerSP.t(), entityPlayerSP.q(), entityPlayerSP.T());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void applyFreecamRotation(EntityPlayerSP entityPlayerSP) {
        if (!this.rotationSaved) {
            this.savedRenderYaw = entityPlayerSP.q$src$F$1u6qsjx();
            this.savedRenderPitch = entityPlayerSP.t$src$F$1u8e6c0();
            this.savedPrevRenderYaw = entityPlayerSP.x$src$F$1ualcpg();
            this.savedPrevRenderPitch = entityPlayerSP.n$src$F$1u53eru();
            this.rotationSaved = true;
        }
        entityPlayerSP.F(this.cameraYaw);
        entityPlayerSP.d(this.cameraPitch);
        entityPlayerSP.E(this.cameraYaw);
        entityPlayerSP.a(this.cameraPitch);
    }

    private void suppressViewBobbing() {
        if (!this.isActiveAndEnabled()) {
            this.restoreViewBobbing();
            return;
        }
        this.syncMouseHelper();
        if (!this.viewBobSaved) {
            this.savedViewBobbing = Minecraft.gameSettings().k();
            this.viewBobSaved = true;
        }
        Minecraft.gameSettings().O(false);
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (Minecraft.theWorld().isNull() || Minecraft.thePlayer().isNull() || Minecraft.thePlayer().w$src$F$15l9epb() <= 0.0f) {
            ((Freecam)this.n).s(false, false);
        }
        if (((Freecam)this.n).C) {
            this.restorePlayerView();
            ((Freecam)this.n).Z();
            ((Freecam)this.n).M$src$V$nre1v6();
            return;
        }
        ((Freecam)this.n).c.d(this.n);
        if (((Freecam)this.n).S || !this.active) {
            this.captureState();
        }
        this.handleMovement();
        this.updateFakePlayer();
        this.applyMovementInput();
    }

    private double mouseSensitivityFactor() {
        double sensitivity = (double)Minecraft.gameSettings().y() * 0.6 + 0.2;
        return sensitivity * sensitivity * sensitivity * 8.0;
    }

    @EventHandler
    public void t(EventPreRenderTick eventPreRenderTick) {
        if (!this.active) {
            return;
        }
        this.suppressViewBobbing();
        this.restorePlayerRotation();
        if (Minecraft.currentScreen().isNotNull()) {
            this.lastMouseX = Minecraft.s().R();
            this.lastMouseY = Minecraft.s().b();
            return;
        }
        this.updateMouseLook();
    }

    @EventHandler
    public void w(EventEntityRendererMouseUpdate eventEntityRendererMouseUpdate) {
        if (!this.active) {
            return;
        }
        this.updateRenderViewPosition(eventEntityRendererMouseUpdate.getPartialTicks());
    }

    private void spawnFakePlayer() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        GameProfile gameProfile = GameProfile.create(UUID.randomUUID(), entityPlayerSP.getName());
        EntityOtherPlayerMP entityOtherPlayerMP = EntityOtherPlayerMP.create(Minecraft.theWorld(), gameProfile);
        entityOtherPlayerMP.M(entityPlayerSP, true);
        ((Freecam)this.n).H = ClientSettings.f();
        entityOtherPlayerMP.Q(((Freecam)this.n).H);
        entityOtherPlayerMP.y(UUID.randomUUID());
        entityOtherPlayerMP.t(entityPlayerSP.z(), entityPlayerSP.N(), entityPlayerSP.h(), entityPlayerSP.J(), entityPlayerSP.V());
        entityOtherPlayerMP.z(entityPlayerSP.s());
        entityOtherPlayerMP.o(entityPlayerSP.P$src$F$14ztfk8());
        ((Freecam)this.n).Z = entityOtherPlayerMP;
        Minecraft.theWorld().D(((Freecam)this.n).H, entityOtherPlayerMP);
    }

    @Override
    public void B() {
        this.active = false;
        ((Freecam)this.n).S = true;
        this.captureState();
    }

    private void updateMouseLook() {
        double mouseX = Minecraft.s().R();
        double mouseY = Minecraft.s().b();
        double deltaX = mouseX - this.lastMouseX;
        double deltaY = mouseY - this.lastMouseY;
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
        double yawDelta = deltaX * this.mouseSensitivityFactor() * 0.15;
        double pitchDelta = deltaY * this.mouseSensitivityFactor() * 0.15;
        this.cameraYaw += (float)yawDelta;
        this.cameraPitch += (float)pitchDelta;
        this.cameraPitch = MathUtil.clamp(this.cameraPitch, -90.0f, 90.0f);
    }

    @EventHandler
    public void T(EventPostEntityRendererMouseUpdate eventPostEntityRendererMouseUpdate) {
        if (!this.active) {
            return;
        }
        this.restorePlayerRotation();
    }

    @EventHandler
    public void t(EventPreLocalPlayerTick eventPreLocalPlayerTick) {
        if (!this.active) {
            return;
        }
        this.applyMovementInput();
    }

    private void updateRenderViewPosition(float partialTicks) {
        ActiveRenderInfo activeRenderInfo = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().l();
        if (activeRenderInfo.isNull()) {
            return;
        }
        double renderX = this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks;
        double renderY = this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks;
        double renderZ = this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks;
        activeRenderInfo.g(Vec3.create(renderX, renderY, renderZ));
    }

    @EventHandler
    public void r(EventKeyBindingState eventKeyBindingState) {
        if (!this.active || !eventKeyBindingState.isPressed() || eventKeyBindingState.getKeyBinding() == null) {
            return;
        }
        if (this.isMovementKey(eventKeyBindingState.getKeyBinding())) {
            eventKeyBindingState.setCancelled(true);
        }
    }

    @EventHandler
    public void D(EventPreLivingTravel eventPreLivingTravel) {
        if (!this.active) {
            return;
        }
        this.applyMovementInput();
    }

    @EventHandler
    public void Z(EventPreEntityRendererMouseUpdate eventPreEntityRendererMouseUpdate) {
        if (!this.active) {
            return;
        }
        this.applyCameraToPlayer();
    }

    private boolean isArrowKeyMovement() {
        return ((Freecam)this.n).A.L() != false && ((Freecam)this.n).r.L() != false && Minecraft.currentScreen().isNull() && (KeyboardInput.isKeyDown(38) || KeyboardInput.isKeyDown(40) || KeyboardInput.isKeyDown(37) || KeyboardInput.isKeyDown(39));
    }

    private void applyCameraToPlayer() {
        if (SharedModuleControlClaims.p.I()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        entityPlayerSP.H(this.cameraYaw);
        entityPlayerSP.D(this.cameraYaw);
        entityPlayerSP.C(this.cameraPitch);
        entityPlayerSP.l(this.cameraPitch);
        this.applyFreecamRotation(entityPlayerSP);
    }

    private void restorePlayerRotation() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        entityPlayerSP.H(this.bodyYaw);
        entityPlayerSP.D(this.bodyYaw);
        entityPlayerSP.z(this.bodyYaw);
        entityPlayerSP.o(this.bodyYaw);
        entityPlayerSP.C(this.bodyPitch);
        entityPlayerSP.l(this.bodyPitch);
        this.restoreRenderRotation(entityPlayerSP);
    }

    private void captureState() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        this.savedYaw = entityPlayerSP.J();
        this.savedPitch = entityPlayerSP.V();
        this.savedPrevYaw = entityPlayerSP.j();
        this.savedPrevPitch = entityPlayerSP.D();
        this.bodyYaw = this.savedYaw;
        this.bodyPitch = this.savedPitch;
        this.cameraYaw = this.savedYaw;
        this.cameraPitch = this.savedPitch;
        this.posX = entityPlayerSP.z();
        this.posY = entityPlayerSP.N() + (double)entityPlayerSP.X();
        this.posZ = entityPlayerSP.h();
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.lastMouseX = Minecraft.s().R();
        this.lastMouseY = Minecraft.s().b();
        this.active = true;
        ((Freecam)this.n).S = false;
    }

    private void restoreViewBobbing() {
        if (!this.viewBobSaved) {
            return;
        }
        Minecraft.gameSettings().O(this.savedViewBobbing);
        this.viewBobSaved = false;
    }

    private void restorePlayerView() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        entityPlayerSP.H(this.savedYaw);
        entityPlayerSP.D(this.savedPrevYaw);
        entityPlayerSP.z(this.savedYaw);
        entityPlayerSP.o(this.savedPrevYaw);
        entityPlayerSP.C(this.savedPitch);
        entityPlayerSP.l(this.savedPrevPitch);
        this.restoreRenderRotation(entityPlayerSP);
    }

    @Override
    public void I() {
        this.restoreViewBobbing();
        this.restorePlayerRotation();
        ((Freecam)this.n).Z();
        this.active = false;
        ((Freecam)this.n).S = false;
        ((Freecam)this.n).C = false;
    }

    private boolean isMovementKey(Object object) {
        KeyBinding keyBinding = new KeyBinding(object);
        return keyBinding.equals(Minecraft.gameSettings().Y()) || keyBinding.equals(Minecraft.gameSettings().s()) || keyBinding.equals(Minecraft.gameSettings().x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()) || keyBinding.equals(Minecraft.gameSettings().g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3()) || keyBinding.equals(Minecraft.gameSettings().O()) || keyBinding.equals(Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0()) || keyBinding.equals(Minecraft.gameSettings().r());
    }

    private void applyMovementInput() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        float forward = 0.0f;
        float strafe = 0.0f;
        if (((Freecam)this.n).A.L().booleanValue() && ((Freecam)this.n).r.L().booleanValue() && Minecraft.currentScreen().isNull()) {
            if (KeyboardInput.isKeyDown(38)) {
                forward += 1.0f;
            }
            if (KeyboardInput.isKeyDown(40)) {
                forward -= 1.0f;
            }
            if (KeyboardInput.isKeyDown(37)) {
                strafe += 1.0f;
            }
            if (KeyboardInput.isKeyDown(39)) {
                strafe -= 1.0f;
            }
        }
        MovementInput movementInput = entityPlayerSP.movementInput();
        movementInput.B(forward);
        movementInput.M(strafe);
        movementInput.V(false);
        movementInput.setCancelled(false);
        movementInput.b().N(false);
        entityPlayerSP.M(forward);
        entityPlayerSP.k$src$V$5315b7(strafe);
        entityPlayerSP.R(false);
    }

    @EventHandler
    public void w(EventPostRenderTick eventPostRenderTick) {
        if (!this.active) {
            return;
        }
        this.restorePlayerRotation();
        this.restoreViewBobbing();
    }

    private void restoreRenderRotation(EntityPlayerSP entityPlayerSP) {
        if (!this.rotationSaved) {
            return;
        }
        entityPlayerSP.F(this.savedRenderYaw);
        entityPlayerSP.d(this.savedRenderPitch);
        entityPlayerSP.E(this.savedPrevRenderYaw);
        entityPlayerSP.a(this.savedPrevRenderPitch);
        this.rotationSaved = false;
    }

    private boolean isActiveAndEnabled() {
        return this.active && ((Freecam)this.n).A.L() != false && ((Freecam)this.n).r.L() != false;
    }

    @EventHandler
    public void r(EventPostTick eventPostTick) {
        if (!this.active) {
            return;
        }
        this.applyMovementInput();
    }
}

