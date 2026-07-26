package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.render.EntityModelFramebufferRenderer;
import gg.vape.utils.render.EntityModelRenderBackend;
import gg.vape.utils.render.EntityModelRenderCacheKey;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.Post117EntityModelFramebufferRenderer;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ResourceLocation;
import java.awt.Color;
import java.util.HashMap;

public class EntityModelRenderCache {
    private static boolean I;
    static HashMap<EntityModelRenderCacheKey, EntityModelRenderBackend> B;

    public static void E(boolean bl) {
        I = bl;
    }

    public static void d(EntityLivingBase entityLivingBase, float f, float f2, int n, int n2, Color color, float f3) {
        try {
            EntityModelRenderCacheKey entityModelRenderCacheKey = new EntityModelRenderCacheKey(entityLivingBase);
            EntityModelRenderCache.m(entityLivingBase);
            B.get(entityModelRenderCacheKey).g(f, f2, n, n2, color, f3);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public static void s(ResourceLocation resourceLocation, String string) {
        EntityModelRenderCacheKey entityModelRenderCacheKey = new EntityModelRenderCacheKey(string);
        if (!B.containsKey(entityModelRenderCacheKey)) {
            EntityModelRenderCache.F(resourceLocation, entityModelRenderCacheKey);
        }
    }

    private static void F(ResourceLocation resourceLocation, EntityModelRenderCacheKey entityModelRenderCacheKey) {
        EntityModelRenderBackend entityModelRenderBackend = GuiRenderPrimitives.d() ? new Post117EntityModelFramebufferRenderer() : new EntityModelFramebufferRenderer();
        entityModelRenderBackend.y(resourceLocation);
        B.put(entityModelRenderCacheKey, entityModelRenderBackend);
    }

    public static void m(EntityLivingBase entityLivingBase) {
        EntityModelRenderCacheKey entityModelRenderCacheKey = new EntityModelRenderCacheKey(entityLivingBase);
        if (!B.containsKey(entityModelRenderCacheKey)) {
            EntityModelRenderCache.y(entityLivingBase, entityModelRenderCacheKey);
        }
    }

    public static void F() {
        for (EntityModelRenderBackend entityModelRenderBackend : B.values()) {
            entityModelRenderBackend.F();
        }
        B.clear();
    }

    public static boolean W() {
        return I;
    }

    public static boolean Q() {
        boolean bl = EntityModelRenderCache.W();
        return true;
    }

    public static ResourceLocation M() {
        String string = ForgeVersion.MC_1_21_4.d() ? "textures/entity/player/wide/steve.png" : "textures/entity/steve.png";
        return ResourceLocation.create(string);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static void y(EntityLivingBase entityLivingBase, EntityModelRenderCacheKey entityModelRenderCacheKey) {
        EntityModelRenderBackend entityModelRenderBackend = GuiRenderPrimitives.d() ? new Post117EntityModelFramebufferRenderer() : new EntityModelFramebufferRenderer();
        entityModelRenderBackend.y(entityLivingBase);
        B.put(entityModelRenderCacheKey, entityModelRenderBackend);
    }

    public static void N(ResourceLocation resourceLocation, String string, float f, float f2, int n, int n2, Color color, float f3) {
        try {
            EntityModelRenderCacheKey entityModelRenderCacheKey = new EntityModelRenderCacheKey(string);
            EntityModelRenderCache.s(resourceLocation, string);
            B.get(entityModelRenderCacheKey).g(f, f2, n, n2, color, f3);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    static {
        EntityModelRenderCache.E(false);
        B = new HashMap();
    }
}

