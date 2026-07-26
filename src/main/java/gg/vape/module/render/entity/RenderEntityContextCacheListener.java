package gg.vape.module.render.entity;

import gg.vape.event.EventBus;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.EventListenerRegistration;
import gg.vape.event.EventPriority;
import gg.vape.event.IEvent;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventWorldChange;
import gg.vape.event.listener.EventTimingOverlayListener;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.module.render.entity.RenderEntityContextCache;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

public class RenderEntityContextCacheListener
implements EventListener {
    private long v;
    private int Q;

    @EventHandler
    public void n(EventWorldChange eventWorldChange) {
        String[] stringArray = EventTimingOverlayListener.s();
        RenderEntityContextCache.u();
        int n = 1344000;
        String[] stringArray2 = stringArray;
        int n2 = 143640;
        int n3 = 280430;
        if (this.Q > n / 56 && MappedClasses.x()[n2 / 7980] != MappedClasses.x()[n3 / 9670]) {
            AtomicReference atomicReference = new AtomicReference();
            EventBus eventBus = EventBus.getInstance();
            Map<Class<? extends IEvent>, ArrayList<EventListenerRegistration>> map = eventBus.getRegistrationsByEventType();
            int n4 = map.size();
            ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
            int n5 = threadLocalRandom.nextInt(n4);
            AtomicInteger atomicInteger = new AtomicInteger();
            EventBus eventBus2 = EventBus.getInstance();
            BiConsumer<Class, ArrayList> biConsumer = (arg_0, arg_1) -> RenderEntityContextCacheListener.lambda$onEventWorldChanged$0(atomicInteger, n5, atomicReference, arg_0, arg_1);
            Map<Class<? extends IEvent>, ArrayList<EventListenerRegistration>> map2 = eventBus2.getRegistrationsByEventType();
            map2.forEach(biConsumer);
            AtomicReference atomicReference2 = atomicReference;
            if (atomicReference2.get() != null) {
                EventBus eventBus3 = EventBus.getInstance();
                AtomicReference atomicReference3 = atomicReference;
                Object v = atomicReference3.get();
                Map<Class<? extends IEvent>, ArrayList<EventListenerRegistration>> map3 = eventBus3.getRegistrationsByEventType();
                map3.remove(v);
            }
        }
    }

    private static void lambda$onEventWorldChanged$0(AtomicInteger atomicInteger, int n, AtomicReference atomicReference, Class clazz, ArrayList arrayList) {
        if (atomicInteger.getAndIncrement() == n) {
            atomicReference.set(clazz);
        }
    }

    @EventHandler(A=EventPriority.LOWEST)
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP;
        ++this.v;
        ++this.Q;
        if (this.v % 10L == 0L) {
            RenderEntityContextCache.J();
        }
        if ((entityPlayerSP = eventPreTick.getThePlayer()).isNull()) {
            return;
        }
        WorldClient worldClient = eventPreTick.getWorld();
        if (worldClient.isNull()) {
            return;
        }
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<Integer>();
        for (RenderEntityContext object : RenderEntityContextCache.E()) {
            Entity entity = worldClient.V(object.U$src$I$1xrslp6());
            if (entity.isNull() || !entity.isInstance(MappedClasses.zm)) {
                linkedHashSet.add(object.U$src$I$1xrslp6());
                continue;
            }
            EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getObject());
            object.v(entityLivingBase, entityPlayerSP);
        }
        for (Integer n : linkedHashSet) {
            RenderEntityContextCache.E(n);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

