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
    private final HashSet<Integer> pendingEntityIds;
    private final TimerUtil cleanupTimer;
    private final HashMap<Long, NameTagsNameState> stateByKey = new HashMap();
    public static final NameTagsRenderStateTracker u = new NameTagsRenderStateTracker();
    private final HashMap<Long, Long> lastSeenByKey;

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
        this.tick(eventPostRenderTick);
    }

    @EventHandler
    public void n(EventPreRenderTick eventPreRenderTick) {
        if (ForgeVersion.MC_1_21_4.d()) {
            return;
        }
        if (eventPreRenderTick.getWorld().isNull()) {
            return;
        }
        this.tick(eventPreRenderTick);
    }

    @Nullable
    public NameTagsNameState B(EntityPlayer entityPlayer) {
        long key = ItemStackFingerprint.T(entityPlayer);
        this.lastSeenByKey.put(key, System.currentTimeMillis());
        NameTagsNameState nameTagsNameState = this.stateByKey.get(key);
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
                this.stateByKey.remove(key);
                this.pendingEntityIds.add(entityPlayer.S());
                return null;
            }
            return nameTagsNameState;
        }
        this.pendingEntityIds.add(entityPlayer.S());
        return null;
    }

    private static Exception rethrow(Exception exception) {
        return exception;
    }

    private void tick(EventRenderTickBase eventRenderTickBase) {
        Object object;
        if (this.cleanupTimer.hasTimeElapsed(1000L)) {
            ArrayList staleKeys = new ArrayList();
            for (Long key : this.lastSeenByKey.keySet()) {
                if (System.currentTimeMillis() - this.lastSeenByKey.get(key) <= 10000L || (object = this.stateByKey.get(key)) == null) continue;
                staleKeys.add(key);
            }
            Iterator iterator = staleKeys.iterator();
            while (iterator.hasNext()) {
                Long key = (Long)iterator.next();
                object = this.stateByKey.get(key);
                if (object != null) {
                    ((NameTagsNameState)object).d().n();
                }
                this.stateByKey.remove(key);
                this.lastSeenByKey.remove(key);
            }
        }
        for (Integer entityId : this.pendingEntityIds) {
            try {
                WorldClient worldClient = eventRenderTickBase.getWorld();
                if (worldClient.isNull() || ((Wrapper)(object = worldClient.V(entityId))).isNull() || !((Wrapper)object).isNotNull() || !((Wrapper)object).isInstance(MappedClasses.Yl) || !worldClient.z().contains(((Wrapper)object).getObject())) continue;
                EntityPlayer entityPlayer = new EntityPlayer(object);
                long key = ItemStackFingerprint.T(entityPlayer);
                this.stateByKey.put(key, NameTagsNameState.T(entityPlayer));
            }
            catch (Exception exception) {}
        }
        this.pendingEntityIds.clear();
    }

    public void k() {
        if (!this.stateByKey.isEmpty()) {
            ArrayList<NameTagsNameState> states = new ArrayList<NameTagsNameState>(this.stateByKey.values());
            RenderThreadTaskQueue.M(() -> NameTagsRenderStateTracker.disposeStates(states));
        }
        this.stateByKey.clear();
        this.pendingEntityIds.clear();
        this.lastSeenByKey.clear();
    }

    public NameTagsRenderStateTracker() {
        this.pendingEntityIds = new HashSet();
        this.lastSeenByKey = new HashMap();
        this.cleanupTimer = new TimerUtil();
    }

    private static void disposeStates(List<NameTagsNameState> list) {
        for (NameTagsNameState nameTagsNameState : list) {
            if (nameTagsNameState == null || nameTagsNameState.d() == null) continue;
            try {
                nameTagsNameState.d().n();
            }
            catch (Exception exception) {}
        }
    }
}
