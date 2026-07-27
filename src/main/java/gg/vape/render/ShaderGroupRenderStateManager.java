package gg.vape.render;

import gg.vape.Vape;
import gg.vape.runtime.NativeBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ResourceLocation;
import gg.vape.wrapper.impl.ShaderGroup;

public class ShaderGroupRenderStateManager {
    private boolean m;
    private ShaderGroup N;
    private static final ShaderGroupRenderStateManager e;
    public ShaderGroup P;
    public boolean I;
    private static final int X;

    public void K() {
        if (this.m) {
            return;
        }
        if (ForgeVersion.MC_1_16_5.d() && ForgeVersion.MC_1_16_5_ACTUAL.B() && Vape.INSTANCE.isNativeAvailable() && !Vape.INSTANCE.isVanillaMinecraftPresent()) {
            return;
        }
        GameSettings gameSettings = Minecraft.gameSettings();
        if (gameSettings.d() > 0 || !gameSettings.Y$src$Z$1rxemad()) {
            return;
        }
        boolean bl = gameSettings.M();
        if (bl && NativeBridge.iv() && !Vape.INSTANCE.isVanillaMinecraftPresent()) {
            return;
        }
        EntityRenderer entityRenderer = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf();
        if (entityRenderer.isNull()) {
            return;
        }
        this.N = entityRenderer.L();
        this.I = bl;
        int n = Minecraft.J();
        int n2 = Minecraft.h();
        if (this.I) {
            gameSettings.m(false);
            Minecraft.getFrameBuffer().createBindFramebuffer(n, n2);
            entityRenderer.updateShaderGroupSize(n, n2);
            Minecraft.O().loadRenderers();
        }
        this.n(n, n2);
        this.m = true;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean M() {
        return this.m;
    }

    public void f() {
        this.e();
    }

    public void e() {
        if (!this.m) {
            return;
        }
        GameSettings gameSettings = Minecraft.gameSettings();
        if (gameSettings.d() > 0 || !gameSettings.Y$src$Z$1rxemad()) {
            return;
        }
        EntityRenderer entityRenderer = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf();
        if (entityRenderer.isNull()) {
            return;
        }
        if (this.I) {
            int n = Minecraft.J();
            int n2 = Minecraft.h();
            Minecraft.getFrameBuffer().createBindFramebuffer(n, n2);
            entityRenderer.updateShaderGroupSize(n, n2);
            Minecraft.O().loadRenderers();
            gameSettings.m(true);
        }
        entityRenderer.setUseShader(false);
        entityRenderer.J(new ShaderGroup(null));
        this.P = null;
        this.N = null;
        this.m = false;
    }

    public static ShaderGroupRenderStateManager Q() {
        return e;
    }

    static {
        long l2 = 6018231015514832914L;
        X = (int)l2;
        e = new ShaderGroupRenderStateManager();
    }

    private void n(int n, int n2) {
        this.P = ShaderGroup.create(Minecraft.Z(), Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().K(), Minecraft.getFrameBuffer(), new ResourceLocation(EntityRenderer.getShaderResourceLocations()[18]));
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().J(this.P);
        this.P.resize(n, n2);
        Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf().setUseShader(true);
    }
}

