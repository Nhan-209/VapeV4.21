package gg.vape.input;

import gg.vape.config.ClientSettings;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Minecraft;
import java.util.HashMap;

public class KeyboardInputState {
    private int M;
    private boolean P;
    private long A;
    private HashMap<Integer, Boolean> m = new HashMap();
    private boolean f;

    public boolean isLastKeyDown() {
        return this.P;
    }

    public long getLastChangeTime() {
        return this.A;
    }

    public boolean isKeyDown(int n) {
        return this.m.getOrDefault(n, false);
    }

    public boolean isCanceled() {
        return this.f;
    }

    private void dispatchChange(int n, boolean bl) {
        this.A = System.nanoTime();
        this.P = bl;
        this.M = n;
        EventKeyPress eventKeyPress = new EventKeyPress(n, bl);
        eventKeyPress.fire();
        this.f = eventKeyPress.isCanceled();
        if (!gg.vape.module.none.ClientSettings.fW.P) {
            int n2 = ClientSettings.H(Minecraft.gameSettings().y$src$Lgg_vape_wrapper_impl_KeyBinding_$1hvjjoh());
            if (n == n2) {
                return;
            }
            this.f = true;
        }
    }

    public int getLastKey() {
        return this.M;
    }

    public KeyboardInputState() {
        this.A = System.nanoTime();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void setKeyState(int n, boolean bl) {
        boolean bl2 = this.m.getOrDefault(n, false);
        if (bl2 != bl) {
            this.dispatchChange(n, bl);
        }
        this.m.put(n, bl);
    }

    public void releaseKey(int n) {
        this.m.put(n, false);
    }
}

