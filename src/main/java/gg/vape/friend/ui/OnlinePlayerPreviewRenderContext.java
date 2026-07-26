package gg.vape.friend.ui;

import gg.vape.render.OffscreenRenderContext;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;

public class OnlinePlayerPreviewRenderContext
extends OffscreenRenderContext {
    private boolean k = false;

    public OnlinePlayerPreviewRenderContext() {
        super(true);
    }

    @Override
    public void b() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        this.S(entityPlayerSP);
        this.i = entityPlayerSP.J() + 180.0f;
        this.K = this.k ? 0.0f : -entityPlayerSP.V();
        super.b();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void d(boolean bl) {
        this.k = bl;
    }
}

