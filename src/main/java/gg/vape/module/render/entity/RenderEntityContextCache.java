package gg.vape.module.render.entity;

import gg.vape.module.render.entity.RenderEntityContext;
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
    private static final HashMap<Integer, ITextComponent> customNames;
    private static final Object lock;
    private static final Map<Integer, RenderEntityContext> contexts;
    private static final HashMap<Integer, ITextComponent> displayNameCache;
    private static final boolean UNUSED = false;

    static {
        lock = new Object();
        contexts = new LinkedHashMap<Integer, RenderEntityContext>();
        displayNameCache = new HashMap();
        customNames = new HashMap();
    }

    @Nullable
    public static RenderEntityContext T(EntityLivingBase entityLivingBase) {
        return RenderEntityContextCache.r(entityLivingBase.S());
    }

    public static void G(EntityPlayer entityPlayer, ITextComponent iTextComponent) {
        customNames.put(entityPlayer.S(), iTextComponent);
    }

    public static ITextComponent I(EntityPlayer entityPlayer) {
        if (customNames.containsKey(entityPlayer.S())) {
            return customNames.get(entityPlayer.S());
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void i(RenderEntityContext renderEntityContext) {
        Object object = lock;
        synchronized (object) {
            contexts.put(renderEntityContext.U$src$I$1xrslp6(), renderEntityContext);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void u() {
        if (contexts.isEmpty()) {
            return;
        }
        Object object = lock;
        synchronized (object) {
            contexts.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void E(int ... nArray) {
        Object object = lock;
        synchronized (object) {
            for (int n : nArray) {
                contexts.remove(n);
            }
        }
    }


    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Collection<RenderEntityContext> E() {
        Object object = lock;
        synchronized (object) {
            return contexts.values();
        }
    }

    public static void J() {
        displayNameCache.clear();
        customNames.clear();
    }

    private static RenderEntityContext createContext(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP, Integer n) {
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
        Object object = lock;
        synchronized (object) {
            return contexts.get(n);
        }
    }

    public static ITextComponent g(EntityPlayer entityPlayer) {
        if (displayNameCache.containsKey(entityPlayer.S())) {
            return displayNameCache.get(entityPlayer.S());
        }
        ITextComponent iTextComponent = entityPlayer.Q();
        displayNameCache.put(entityPlayer.S(), iTextComponent);
        return iTextComponent;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static RenderEntityContext V(EntityLivingBase entityLivingBase, EntityPlayerSP entityPlayerSP) {
        Object object = lock;
        synchronized (object) {
            return contexts.computeIfAbsent(entityLivingBase.S(), arg_0 -> RenderEntityContextCache.createContext(entityLivingBase, entityPlayerSP, arg_0));
        }
    }

    public static String W(Entity entity) {
        return entity.Q().C();
    }
}

