package gg.vape.module.render.entity;

import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class RenderEntityContextCache {
    private static final HashMap<Integer, ITextComponent> o;
    private static final Object W;
    private static final Map<Integer, RenderEntityContext> F;
    private static final HashMap<Integer, ITextComponent> y;
    private static final boolean T = false;

    static {
        W = new Object();
        F = new LinkedHashMap<Integer, RenderEntityContext>();
        y = new HashMap();
        o = new HashMap();
    }

    @Nullable
    public static RenderEntityContext T(EntityLivingBase entityLivingBase) {
        return RenderEntityContextCache.r(entityLivingBase.S());
    }

    public static void G(EntityPlayer entityPlayer, ITextComponent iTextComponent) {
        o.put(entityPlayer.S(), iTextComponent);
    }

    public static ITextComponent I(EntityPlayer entityPlayer) {
        if (o.containsKey(entityPlayer.S())) {
            return o.get(entityPlayer.S());
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void i(RenderEntityContext renderEntityContext) {
        Object object = W;
        synchronized (object) {
            F.put(renderEntityContext.U$src$I$1xrslp6(), renderEntityContext);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void u() {
        if (F.isEmpty()) {
            return;
        }
        Object object = W;
        synchronized (object) {
            F.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void E(int ... nArray) {
        Object object = W;
        synchronized (object) {
            for (int n : nArray) {
                F.remove(n);
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Collection<RenderEntityContext> E() {
        Object object = W;
        synchronized (object) {
            return F.values();
        }
    }

    public static void J() {
        y.clear();
        o.clear();
    }

    private static RenderEntityContext lambda$getEntityDataOrCreate$0(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP, Integer n) {
        RenderEntityContext renderEntityContext = new RenderEntityContext(n, entityLivingBase, entityPlayerSP);
        renderEntityContext.v(entityLivingBase, entityPlayerSP);
        return renderEntityContext;
    }

    public static RenderEntityContext Q(EntityLivingBase entityLivingBase) {
        return RenderEntityContextCache.V(entityLivingBase, Minecraft.thePlayer());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    public static RenderEntityContext r(int n) {
        Object object = W;
        synchronized (object) {
            return F.get(n);
        }
    }

    public static ITextComponent g(EntityPlayer entityPlayer) {
        if (y.containsKey(entityPlayer.S())) {
            return y.get(entityPlayer.S());
        }
        ITextComponent iTextComponent = entityPlayer.Q();
        y.put(entityPlayer.S(), iTextComponent);
        return iTextComponent;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static RenderEntityContext V(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        Object object = W;
        synchronized (object) {
            return F.computeIfAbsent(entityLivingBase.S(), arg_0 -> RenderEntityContextCache.lambda$getEntityDataOrCreate$0(entityLivingBase, entityPlayerSP, arg_0));
        }
    }

    public static String W(Entity entity) {
        return entity.Q().C();
    }
}

