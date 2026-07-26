package gg.vape.event.listener;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventPreTick;
import gg.vape.module.render.NameTagsRenderStateTracker;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.EntityModelRenderCache;
import gg.vape.utils.render.ItemIconRenderer;
import gg.vape.utils.render.PotionEffectIconRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.ResourceManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VapeClientEventListener
implements EventListener {
    private final TimerUtil C = new TimerUtil();
    private Set<String> Y;
    private List<String> S;
    private boolean a;
    private Object z;

    private static Exception a(Exception exception) {
        return exception;
    }

    private void v() {
        ItemIconRenderer.t();
        EntityModelRenderCache.F();
        PotionEffectIconRenderer.g();
        NameTagsRenderStateTracker.u.k();
    }

    private void Z() {
        List<String> list = Minecraft.gameSettings().f$src$Ljava_util_List_$1i0ug5l();
        if (list != null) {
            boolean bl;
            Map map = Minecraft.P();
            if (map != null) {
                boolean bl2;
                boolean bl3;
                ArrayList<String> arrayList = new ArrayList<String>(list);
                if (this.S == null) {
                    this.S = arrayList;
                    this.z = map;
                    return;
                }
                boolean bl4 = bl3 = !this.S.equals(arrayList);
                if (bl3) {
                    boolean bl5;
                    boolean bl6 = bl5 = this.z != null && map != this.z;
                    if (bl5) {
                        this.S = arrayList;
                        this.a = true;
                        if (this.a) {
                            this.z = map;
                            this.a = false;
                            this.v();
                            return;
                        }
                        if (!this.a) {
                            this.z = map;
                            this.v();
                            return;
                        }
                        if (this.z == null) {
                            this.z = map;
                        }
                        return;
                    }
                    this.S = arrayList;
                    this.a = true;
                    if (this.a) {
                        // empty if block
                    }
                    if (!this.a) {
                        // empty if block
                    }
                    if (this.z == null) {
                        this.z = map;
                    }
                    return;
                }
                boolean bl7 = bl2 = this.z != null && map != this.z;
                if (bl2) {
                    if (this.a) {
                        this.z = map;
                        this.a = false;
                        this.v();
                        return;
                    }
                    if (!this.a) {
                        this.z = map;
                        this.v();
                        return;
                    }
                    if (this.z == null) {
                        this.z = map;
                    }
                    return;
                }
                if (this.a) {
                    // empty if block
                }
                if (!this.a) {
                    // empty if block
                }
                if (this.z == null) {
                    this.z = map;
                }
                return;
            }
            ArrayList<String> arrayList = new ArrayList<String>(list);
            if (this.S == null) {
                this.S = arrayList;
                this.z = map;
                return;
            }
            boolean bl8 = bl = !this.S.equals(arrayList);
            if (bl) {
                boolean bl9 = false;
                this.S = arrayList;
                this.a = true;
                if (this.a) {
                    // empty if block
                }
                if (!this.a) {
                    // empty if block
                }
                if (this.z == null) {
                    this.z = map;
                }
                return;
            }
            boolean bl10 = false;
            if (this.a) {
                // empty if block
            }
            if (!this.a) {
                // empty if block
            }
            if (this.z == null) {
                this.z = map;
            }
            return;
        }
        Map map = Minecraft.P();
        if (map != null) {
            this.F();
            return;
        }
        this.F();
    }

    private void F() {
        ResourceManager resourceManager = Minecraft.c();
        if (resourceManager.isNull()) {
            return;
        }
        Collection<String> collection = resourceManager.U();
        if (collection == null) {
            return;
        }
        HashSet<String> hashSet = new HashSet<String>(collection);
        if (this.Y == null) {
            this.Y = hashSet;
            return;
        }
        if (!this.Y.equals(hashSet)) {
            this.Y = hashSet;
            this.v();
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        if (!this.C.hasTimeElapsed(1000L)) {
            return;
        }
        this.C.reset();
        try {
            if (ForgeVersion.MC_1_21_10.d()) {
                this.Z();
                return;
            }
            this.F();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

