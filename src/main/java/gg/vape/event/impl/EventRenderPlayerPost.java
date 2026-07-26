package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderPlayer;

public class EventRenderPlayerPost
extends Event {
    private final RenderPlayer A;
    private final EntityPlayer b;
    private static final EventListeners Z = new EventListeners();
    private final MatrixStack n;
    private final float m;

    @Override
    public EventListeners getListeners() {
        return Z;
    }

    public EventRenderPlayerPost(Object object, Object object2, float f, Object object3) {
        this.A = new RenderPlayer(object);
        this.b = new EntityPlayer(object2);
        this.m = f;
        this.n = new MatrixStack(object3);
    }

    public EntityPlayer getEntityPlayer() {
        return this.b;
    }

    public static EventListeners getEventListeners() {
        return Z;
    }

    public EventRenderPlayerPost(Object object, Object object2, float f) {
        this.A = new RenderPlayer(object);
        this.b = new EntityPlayer(object2);
        this.m = f;
        this.n = null;
    }

    public MatrixStack getMatrixStack() {
        return this.n;
    }

    public EventRenderPlayerPost(Object object, Object object2, Object object3) {
        this.A = new RenderPlayer(object3);
        this.b = new EntityPlayer(object);
        this.n = new MatrixStack(object2);
        this.m = Minecraft.getTimer().renderPartialTicks();
    }

    public RenderPlayer getRenderer() {
        return this.A;
    }

    public float getPartialTicks() {
        return this.m;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }
}

