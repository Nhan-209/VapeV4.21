package gg.vape.module.render;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventPostRenderTick;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventRenderTickBase;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.NameTagsNameState;
import gg.vape.util.RenderThreadTaskQueue;
import gg.vape.utils.ItemStackFingerprint;
import gg.vape.utils.TimerUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class NameTagsRenderStateTracker
implements EventListener {
    private final HashSet<Integer> P;
    private final TimerUtil x;
    private final HashMap<Long, NameTagsNameState> C = new HashMap();
    public static final NameTagsRenderStateTracker u = new NameTagsRenderStateTracker();
    private final HashMap<Long, Long> N;

    @EventHandler
    public void R(EventWorldChange eventWorldChange) {
        this.k();
    }

    @EventHandler
    public void C(EventPostRenderTick eventPostRenderTick) {
        if (ForgeVersion.MC_1_21_4.v()) {
            return;
        }
        if (eventPostRenderTick.getWorld().isNull()) {
            return;
        }
        this.s(eventPostRenderTick);
    }

    @EventHandler
    public void n(EventPreRenderTick eventPreRenderTick) {
        if (ForgeVersion.MC_1_21_4.d()) {
            return;
        }
        if (eventPreRenderTick.getWorld().isNull()) {
            return;
        }
        this.s(eventPreRenderTick);
    }

    @Nullable
    public NameTagsNameState B(EntityPlayer entityPlayer) {
        long l = ItemStackFingerprint.T(entityPlayer);
        this.N.put(l, System.currentTimeMillis());
        NameTagsNameState nameTagsNameState = this.C.get(l);
        if (nameTagsNameState != null) {
            if (nameTagsNameState.d() == null || !nameTagsNameState.d().R()) {
                try {
                    if (nameTagsNameState.d() != null) {
                        nameTagsNameState.d().n();
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
                this.C.remove(l);
                this.P.add(entityPlayer.S());
                return null;
            }
            return nameTagsNameState;
        }
        this.P.add(entityPlayer.S());
        return null;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private void s(EventRenderTickBase eventRenderTickBase) {
        Object object;
        if (this.x.hasTimeElapsed(1000L)) {
            ArrayList arrayList = new ArrayList();
            for (Long l : this.N.keySet()) {
                if (System.currentTimeMillis() - this.N.get(l) <= 10000L || (object = this.C.get(l)) == null) continue;
                arrayList.add(l);
            }
            Iterator iterator = arrayList.iterator();
            while (iterator.hasNext()) {
                Long l = (Long)iterator.next();
                object = this.C.get(l);
                if (object != null) {
                    ((NameTagsNameState)object).d().n();
                }
                this.C.remove(l);
                this.N.remove(l);
            }
        }
        for (Integer n : this.P) {
            try {
                WorldClient worldClient = eventRenderTickBase.getWorld();
                if (worldClient.isNull() || ((Wrapper)(object = worldClient.V(n))).isNull() || !((Wrapper)object).isNotNull() || !((Wrapper)object).isInstance(MappedClasses.Yl) || !worldClient.z().contains(((Wrapper)object).getObject())) continue;
                EntityPlayer entityPlayer = new EntityPlayer(object);
                long l = ItemStackFingerprint.T(entityPlayer);
                this.C.put(l, NameTagsNameState.T(entityPlayer));
            }
            catch (Exception exception) {}
        }
        this.P.clear();
    }

    public void k() {
        if (!this.C.isEmpty()) {
            ArrayList<NameTagsNameState> arrayList = new ArrayList<NameTagsNameState>(this.C.values());
            RenderThreadTaskQueue.M(() -> NameTagsRenderStateTracker.lambda$reset$0(arrayList));
        }
        this.C.clear();
        this.P.clear();
        this.N.clear();
    }

    public NameTagsRenderStateTracker() {
        this.P = new HashSet();
        this.N = new HashMap();
        this.x = new TimerUtil();
    }

    private static void lambda$reset$0(List<NameTagsNameState> list) {
        for (NameTagsNameState nameTagsNameState : list) {
            if (nameTagsNameState == null || nameTagsNameState.d() == null) continue;
            try {
                nameTagsNameState.d().n();
            }
            catch (Exception exception) {}
        }
    }
}
