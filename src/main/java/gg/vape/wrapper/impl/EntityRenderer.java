package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MEntityRenderer;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ActiveRenderInfo;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.GlStateManager$FogState;
import gg.vape.wrapper.impl.Items;
import gg.vape.wrapper.impl.LightTexture;
import gg.vape.wrapper.impl.Matrix4f;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderBufferBridge;
import gg.vape.wrapper.impl.RenderSystem;
import gg.vape.wrapper.impl.ShaderGroup;
import gg.vape.wrapper.impl.ShaderGroupState;
import gg.vape.wrapper.impl.StatusEffect;
import gg.vape.wrapper.impl.Vector3f;
import gg.vape.wrapper.impl.WorldProvider;

public class EntityRenderer
extends Wrapper {
    public ActiveRenderInfo l() {
        return new ActiveRenderInfo(MEntityRenderer.Q(EntityRenderer.c.getMappings().RY, this.I));
    }

    public void Y(float f) {
        MEntityRenderer.i(EntityRenderer.c.getMappings().RY, this.I, f);
    }

    public RenderBufferBridge V() {
        return new RenderBufferBridge(MEntityRenderer.E(EntityRenderer.c.getMappings().RY, this.I));
    }

    public void setPointedEntity(Entity entity) {
        if (ForgeVersion.MC_1_16_5.d()) {
            MEntityRenderer.T(EntityRenderer.c.getMappings().RY, Minecraft.i(), entity.getObject());
            return;
        }
        MEntityRenderer.T(EntityRenderer.c.getMappings().RY, this.I, entity.getObject());
    }

    public float b() {
        return MEntityRenderer.A(EntityRenderer.c.getMappings().RY, this.I);
    }

    public void i(float f) {
        MEntityRenderer.u(EntityRenderer.c.getMappings().RY, this.I, f);
    }

    public void B(MatrixStack matrixStack, float f) {
        MEntityRenderer.M(EntityRenderer.c.getMappings().RY, this.I, matrixStack.getObject(), f);
    }

    public void J(ShaderGroup shaderGroup) {
        MEntityRenderer.D(EntityRenderer.c.getMappings().RY, this.I, shaderGroup.getObject());
    }

    public Entity getPointedEntity() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return new Entity(MEntityRenderer.Z(EntityRenderer.c.getMappings().RY, Minecraft.i()));
        }
        return new Entity(MEntityRenderer.Z(EntityRenderer.c.getMappings().RY, this.I));
    }

    public ShaderGroupState K() {
        return new ShaderGroupState(MEntityRenderer.P(EntityRenderer.c.getMappings().RY, this.I));
    }

    public void q(float f) {
        MEntityRenderer.N(EntityRenderer.c.getMappings().RY, this.I, f);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void m(float f, long l) {
        MEntityRenderer.X(EntityRenderer.c.getMappings().RY, this.I, f, l);
    }

    public void B(double d) {
        if (ForgeVersion.MC_1_16_5.d()) {
            this.l$src$Lgg_vape_wrapper_impl_LightTexture_$to78f8().V();
            return;
        }
        MEntityRenderer.f(EntityRenderer.c.getMappings().RY, this.I, d);
    }

    public WorldProvider h() {
        return new WorldProvider(MEntityRenderer.s(EntityRenderer.c.getMappings().RY, this.I));
    }

    public GlStateManager$FogState y() {
        return new GlStateManager$FogState(MEntityRenderer.b(EntityRenderer.c.getMappings().RY, this.I));
    }

    public void updateShaderGroupSize(int n, int n2) {
        MEntityRenderer.i(EntityRenderer.c.getMappings().RY, this.I, n, n2);
    }

    public ShaderGroup L() {
        return new ShaderGroup(MEntityRenderer.t(EntityRenderer.c.getMappings().RY, this.I));
    }

    public void s(float f, int n) {
        SharedModuleControlClaims.p.E();
        if (ForgeVersion.MC_1_16_5.d()) {
            EntityPlayerSP entityPlayerSP;
            float f2;
            GameSettings gameSettings = Minecraft.gameSettings();
            this.i((float)gameSettings.v() * 16.0f);
            MatrixStack matrixStack = MatrixStack.A();
            Matrix4f matrix4f = this.l(this.l(), f, true);
            matrixStack.F().u().a(matrix4f);
            this.B(matrixStack, f);
            if (gameSettings.k()) {
                this.Z(matrixStack, f);
            }
            if ((f2 = MathUtil.round(f, (entityPlayerSP = Minecraft.thePlayer()).k$src$F$1u3g0zr(), entityPlayerSP.O$src$F$1to1sdn()) * gameSettings.f() * gameSettings.f()) > 0.0f) {
                int n2 = entityPlayerSP.i(StatusEffect.V()) ? 7 : 20;
                float f3 = 5.0f / (f2 * f2 + 5.0f) - f2 * 0.04f;
                f3 *= f3;
                Vector3f vector3f = Vector3f.Z(0.0f, MathUtil.sqrt(2.0f) / 2.0f, MathUtil.sqrt(2.0f) / 2.0f);
                matrixStack.i(vector3f.I(((float)this.Q() + f) * (float)n2));
                matrixStack.S(1.0f / f3, 1.0f, 1.0f);
                float f4 = -((float)this.Q() + f) * (float)n2;
                matrixStack.i(vector3f.I(f4));
            }
            Matrix4f matrix4f2 = matrixStack.F().u();
            this.setShaderGroup(matrix4f2);
            SharedModuleControlClaims.p.Q();
            return;
        }
        MEntityRenderer.D(EntityRenderer.c.getMappings().RY, this.I, f, n);
        SharedModuleControlClaims.p.Q();
    }

    public LightTexture l$src$Lgg_vape_wrapper_impl_LightTexture_$to78f8() {
        return new LightTexture(MEntityRenderer.O(EntityRenderer.c.getMappings().RY, this.I));
    }

    public Matrix4f l(ActiveRenderInfo activeRenderInfo, float f, boolean bl) {
        Object object = MEntityRenderer.z(EntityRenderer.c.getMappings().RY, this.I, activeRenderInfo.getObject(), f, bl);
        if (object == null && ForgeVersion.MC_26_1.d()) {
            object = MEntityRenderer.o(EntityRenderer.c.getMappings().RY, this.I);
        }
        return new Matrix4f(object);
    }

    public void setUseShader(boolean bl) {
        if (ForgeVersion.MC_1_7_10.Y()) {
            MEntityRenderer.C(EntityRenderer.c.getMappings().RY, this.I, bl);
        }
    }

    public void setShaderGroup(Matrix4f matrix4f) {
        if (ForgeVersion.MC_1_21_0.d()) {
            RenderSystem.L(matrix4f, Items.I());
            return;
        }
        MEntityRenderer.o(EntityRenderer.c.getMappings().RY, this.I, matrix4f.getObject());
    }

    public int Q() {
        return MEntityRenderer.m(EntityRenderer.c.getMappings().RY, this.I);
    }

    public void Z(MatrixStack matrixStack, float f) {
        MEntityRenderer.z(EntityRenderer.c.getMappings().RY, this.I, matrixStack.getObject(), f);
    }

    public void V(float f) {
        MEntityRenderer.x(EntityRenderer.c.getMappings().RY, this.I, f);
    }

    public void D(float f, long l) {
        if (ForgeVersion.MC_1_21_0.d()) {
            if (ForgeVersion.MC_1_21_11.d()) {
                MEntityRenderer.a(EntityRenderer.c.getMappings().RY, this.I, Minecraft.getTimer().getObject());
            }
            MEntityRenderer.q(EntityRenderer.c.getMappings().RY, this.I, Minecraft.getTimer().getObject());
            return;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            MEntityRenderer.h(EntityRenderer.c.getMappings().RY, this.I, f, l, MatrixStack.A().getObject());
            return;
        }
        MEntityRenderer.F(EntityRenderer.c.getMappings().RY, this.I, f, l);
    }

    public boolean isUseShader() {
        if (ForgeVersion.MC_1_7_10.Y()) {
            return MEntityRenderer.c$src$Z$4ble0w(EntityRenderer.c.getMappings().RY, this.I);
        }
        return false;
    }

    public void r(float f) {
        MEntityRenderer.j(EntityRenderer.c.getMappings().RY, this.I, f);
    }

    public static Object[] getShaderResourceLocations() {
        return MEntityRenderer.k(EntityRenderer.c.getMappings().RY);
    }

    public void O(double d) {
        if (ForgeVersion.MC_1_16_5.d()) {
            this.l$src$Lgg_vape_wrapper_impl_LightTexture_$to78f8().X();
            return;
        }
        MEntityRenderer.V(EntityRenderer.c.getMappings().RY, this.I, d);
    }

    public static Object s$src$Ljava_lang_Object_$1ecvhy8() {
        return MEntityRenderer.G(EntityRenderer.c.getMappings().RY);
    }

    public EntityRenderer(Object object) {
        super(object);
    }

    public float s() {
        return MEntityRenderer.c(EntityRenderer.c.getMappings().RY, this.I);
    }
}

