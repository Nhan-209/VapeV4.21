package gg.vape.module.render.hud;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventPostEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPostRenderWorldPass;
import gg.vape.event.impl.EventPostTick;
import gg.vape.event.impl.EventPreEntityRendererMouseUpdate;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreRenderWorldPass;
import gg.vape.event.impl.EventRenderPlayerPost;
import gg.vape.event.impl.EventRenderPlayerPre;
import gg.vape.event.impl.EventTickBase;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.render.hud.HudModule;
import gg.vape.module.render.hud.HudModuleGroup;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MathUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;

public class FreeLookHudModule
extends HudModule {
    private static float savedPitch;
    private final ModeOption holdMode = new ModeOption("Hold");
    private final ModeOption forwardMode;
    private static float renderPitch;
    private static float originalYaw;
    private final ModeOption thirdPersonMode;
    private static float originalPrevYaw;
    private final NumberValue sensitivity;
    private static boolean active;
    private double mouseDY;
    private EntityLivingBase renderPlayer;
    private final ModeOption toggleMode = new ModeOption("Toggle");
    private static float savedYaw;
    private double mouseLastY;
    private final BooleanValue customSensitivity;
    private static float originalPrevPitch;
    private final ModeOption firstPersonMode;
    private static float renderYaw;
    private boolean enabled = false;
    public final ModeValue oO;
    private static float originalPitch;
    private static float pitchOffset;
    private static float yawOffset;
    private double mouseLastX;
    public final ModeValue t = ModeValue.create((Object)this, "Activate Freelook", this.holdMode, this.holdMode, this.toggleMode);
    public final ModeValue p;
    private final ModeOption backwardMode;
    private int savedPerspective = -1;
    private double mouseDX;

    private static ObfuscatedRuntimeException passException(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static float c() {
        return renderYaw;
    }

    @EventHandler
    public void Q(EventPostRenderTick eventPostRenderTick) {
        if (!this.enabled) {
            return;
        }
        this.restorePlayerView();
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void m(EventPreRenderWorldPass eventPreRenderWorldPass) {
        this.applyWorldRotation();
    }

    private double getSensitivity() {
        if (!this.customSensitivity.L().booleanValue()) {
            return (double)Minecraft.gameSettings().y() * 0.6 * 0.2 * 8.0;
        }
        return (Double)this.sensitivity.K();
    }

    private void applyRenderRotation() {
        if (!this.enabled) {
            return;
        }
        if (RotationManager.b.u() && Vape.INSTANCE.getClientSettings().c.L().booleanValue()) {
            return;
        }
        this.applyCameraRotation();
    }

    @Override
    public void y() {
        if (!this.r$src$Z$14eylz9() || Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            this.mouseLastX = Minecraft.s().R();
            this.mouseLastY = Minecraft.s().b();
        }
        if (this.t.K() == this.toggleMode) {
            EventTickBase.p.execute(this::toggleFreelook);
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void L(EventRenderPlayerPost eventRenderPlayerPost) {
        this.applyRenderRotation();
    }

    @EventHandler
    public void G(EventPreEntityRendererMouseUpdate eventPreEntityRendererMouseUpdate) {
        if (!active) {
            return;
        }
        if (!this.enabled) {
            return;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        this.applyCameraRotation();
    }

    @EventHandler
    public void o(EventPostTick eventPostTick) {
        if (this.t.K() != this.holdMode) {
            return;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        if (Minecraft.thePlayer().isNull()) {
            return;
        }
        this.enabled = this.a().K();
        if (!this.enabled) {
            active = false;
            if (this.savedPerspective == -1) {
                return;
            }
            Minecraft.gameSettings().I(this.savedPerspective);
            this.savedPerspective = -1;
            this.restorePlayerView();
        }
    }

    @EventHandler
    public void h(EventPostEntityRendererMouseUpdate eventPostEntityRendererMouseUpdate) {
        if (!this.enabled) {
            return;
        }
        this.restorePlayerView();
    }

    public static boolean z() {
        return active;
    }

    private void toggleFreelook() {
        boolean bl = this.enabled = !this.enabled;
        if (!this.enabled) {
            active = false;
            if (this.savedPerspective == -1) {
                return;
            }
            Minecraft.gameSettings().I(this.savedPerspective);
            this.savedPerspective = -1;
            this.restorePlayerView();
        }
    }

    static {
        active = false;
    }

    private void restorePlayerView() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull() || Minecraft.theWorld().isNull()) {
            return;
        }
        entityPlayerSP.H(savedPitch);
        entityPlayerSP.D(savedPitch);
        entityPlayerSP.z(savedPitch);
        entityPlayerSP.o(savedPitch);
        entityPlayerSP.C(savedYaw);
        entityPlayerSP.l(savedYaw);
    }

    public void G(float f, float f2, float f3, float f4) {
        savedPitch = f;
        savedYaw = f2;
    }

    public static float w$src$F$1kb9hl5() {
        return renderPitch;
    }

    private void applyWorldRotation() {
        if (!this.enabled) {
            return;
        }
        if (RotationManager.b.u() && Vape.INSTANCE.getClientSettings().c.L().booleanValue()) {
            return;
        }
        this.restorePlayerView();
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void w(EventRenderPlayerPre eventRenderPlayerPre) {
        this.applyWorldRotation();
    }

    private void applyCameraRotation() {
        if (SharedModuleControlClaims.p.I()) {
            return;
        }
        this.renderPlayer.H(renderPitch);
        this.renderPlayer.D(renderPitch);
        this.renderPlayer.C(renderYaw);
        this.renderPlayer.l(renderYaw);
    }

    public static float L$src$F$1jnmc2m() {
        return savedPitch;
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void d(EventPostRenderWorldPass eventPostRenderWorldPass) {
        this.applyRenderRotation();
    }

    public FreeLookHudModule() {
        super("Freelook", HudModuleGroup.T, "freelook2");
        this.customSensitivity = BooleanValue.create(this, "Use Custom Sensitivity", false, "Enable to set a separate sensitivity from Minecraft using a slider");
        this.sensitivity = NumberValue.create(this, "Sensitivity", "#.#", "", 0.001, 0.5, 1.0);
        this.thirdPersonMode = new ModeOption("3rd Person");
        this.firstPersonMode = new ModeOption("1st Person");
        this.p = ModeValue.create((Object)this, "Perspective", this.thirdPersonMode, this.thirdPersonMode, this.firstPersonMode);
        this.forwardMode = new ModeOption("Forward");
        this.backwardMode = new ModeOption("Backward");
        this.oO = ModeValue.create((Object)this, "Starting Position", this.forwardMode, this.forwardMode, this.backwardMode);
        this.setSuffix("Freely rotates your perspective");
        this.q$src$V$1apmftw(true);
        this.addValue(this.t, this.oO, this.customSensitivity, this.sensitivity);
        this.customSensitivity.K(this.sensitivity);
    }

    public static float U() {
        return savedYaw;
    }

    private void updateMouseDelta() {
        if (ForgeVersion.MC_1_16_5.d()) {
            this.mouseDX = this.mouseLastX - Minecraft.s().R();
            this.mouseDY = this.mouseLastY - Minecraft.s().b();
            this.mouseLastX = Minecraft.s().R();
            this.mouseLastY = Minecraft.s().b();
            return;
        }
        this.mouseDX = -Minecraft.s().d();
        this.mouseDY = Minecraft.s().z();
    }

    @EventHandler
    public void u(EventPreRenderTick eventPreRenderTick) {
        int n;
        this.renderPlayer = eventPreRenderTick.getThePlayer();
        if (!this.enabled) {
            return;
        }
        if (!active) {
            originalYaw = this.renderPlayer.V();
            originalPitch = this.renderPlayer.J();
            originalPrevYaw = this.renderPlayer.D();
            originalPrevPitch = this.renderPlayer.j();
            this.savedPerspective = Minecraft.gameSettings().x();
            yawOffset = ((ModeSelection)this.oO.K()).equals(this.forwardMode) ? 0.0f : 180.0f;
            pitchOffset = 0.0f;
            this.mouseDX = 0.0;
            this.mouseDY = 0.0;
            renderPitch = originalPitch;
            renderYaw = originalYaw;
            savedYaw = originalYaw;
            savedPitch = originalPitch;
            active = true;
            return;
        }
        int n2 = n = ((ModeSelection)this.p.K()).equals(this.firstPersonMode) ? 0 : 1;
        if (Minecraft.gameSettings().x() != n) {
            Minecraft.gameSettings().I(n);
        }
        if (Minecraft.currentScreen().isNotNull()) {
            yawOffset = 0.0f;
            pitchOffset = 0.0f;
            this.mouseDX = this.mouseDY = (double)0.0f;
        }
        this.updateMouseDelta();
        double d = this.mouseDX * this.getSensitivity() * 0.15;
        double d2 = this.mouseDY * this.getSensitivity() * 0.15;
        renderPitch = (float)(d - (double)yawOffset + (double)originalPitch);
        renderYaw = (float)(d2 - (double)pitchOffset + (double)originalYaw);
        renderYaw = MathUtil.clamp(renderYaw, -90.0f, 90.0f);
        yawOffset = (float)(d + (double)yawOffset);
        pitchOffset = (float)(d2 + (double)pitchOffset);
        pitchOffset = MathUtil.clamp(pitchOffset, -(90.0f - originalYaw), 90.0f + originalYaw);
    }
}

