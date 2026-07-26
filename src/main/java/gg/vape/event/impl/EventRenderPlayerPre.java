package gg.vape.event.impl;

import gg.vape.event.Event;
import gg.vape.event.EventListeners;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.MatrixStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderPlayer;

public class EventRenderPlayerPre
extends Event {
    private double g;
    private final float Q;
    private double T;
    private static final EventListeners l = new EventListeners();
    private final EntityPlayer G;
    private final MatrixStack i;
    private final RenderPlayer p;
    private double j;

    public EventRenderPlayerPre(Object object, Object object2, double d, double d2, double d3, float f) {
        this.p = new RenderPlayer(object);
        this.G = new EntityPlayer(object2);
        this.Q = f;
        this.i = null;
        this.T = d;
        this.g = d2;
        this.j = d3;
    }

    @Override
    public boolean fire() {
        return super.fire();
    }

    public double getZ() {
        return this.j;
    }

    public EventRenderPlayerPre(Object object, Object object2, float f, Object object3) {
        this.p = new RenderPlayer(object);
        this.G = new EntityPlayer(object2);
        this.Q = f;
        this.i = new MatrixStack(object3);
        this.T = this.G.z();
        this.g = this.G.N();
        this.j = this.G.h();
    }

    public static EventListeners getEventListeners() {
        return l;
    }

    public double getX() {
        return this.T;
    }

    public double getY() {
        return this.g;
    }

    public EntityPlayer getEntityPlayer() {
        return this.G;
    }

    public float getPartialTicks() {
        return this.Q;
    }

    public EventRenderPlayerPre(Object object, Object object2, Object object3) {
        this.p = new RenderPlayer(object3);
        this.G = new EntityPlayer(object);
        this.i = new MatrixStack(object2);
        this.Q = Minecraft.getTimer().renderPartialTicks();
        this.T = this.G.z();
        this.g = this.G.N();
        this.j = this.G.h();
    }

    @Override
    public EventListeners getListeners() {
        return l;
    }

    public MatrixStack getMatrixStack() {
        return this.i;
    }

    public RenderPlayer getRenderer() {
        return this.p;
    }
}

