package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MFramebuffer;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class Framebuffer
extends Wrapper {
    public Framebuffer(Object object) {
        super(object);
    }

    public void createFramebuffer(int n, int n2) {
        if (ForgeVersion.MC_1_16_5.d()) {
            MFramebuffer.w(Framebuffer.c.getMappings().R9, this.I, n, n2, false);
            return;
        }
        MFramebuffer.G(Framebuffer.c.getMappings().R9, this.I, n, n2);
    }

    public void unbindFramebuffer() {
        MFramebuffer.m(Framebuffer.c.getMappings().R9, this.I);
    }

    public void unbindFramebufferTexture() {
        MFramebuffer.D(Framebuffer.c.getMappings().R9, this.I);
    }

    public void setDepthBuffer(int n) {
        MFramebuffer.depthBuffer(Framebuffer.c.getMappings().R9, this.I, n);
    }

    public void x() {
        MFramebuffer.e(Framebuffer.c.getMappings().R9, this.I);
    }

    public static Framebuffer create(int n, int n2, boolean bl) {
        if (ForgeVersion.MC_1_17.d()) {
            return new Framebuffer(MFramebuffer.create(Framebuffer.c.getMappings().R9, n, n2, true));
        }
        return new Framebuffer(MFramebuffer.create(Framebuffer.c.getMappings().R9, n, n2, bl));
    }

    public int getDepthBuffer() {
        return MFramebuffer.L(Framebuffer.c.getMappings().R9, this.I);
    }

    public void createBindFramebuffer(int n, int n2) {
        MFramebuffer.f(Framebuffer.c.getMappings().R9, this.I, n, n2);
    }


    public void bindFramebuffer(boolean bl) {
        MFramebuffer.bindFramebuffer(Framebuffer.c.getMappings().R9, this.I, bl);
    }

    public void bindFramebufferTexture() {
        MFramebuffer.l(Framebuffer.c.getMappings().R9, this.I);
    }
}

