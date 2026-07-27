package gg.vape.input;

import gg.vape.event.impl.EventMouseButton;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiScreenNativeCallbackBridge;
import java.util.HashMap;

public class MouseInputState {
    private HashMap<Integer, Boolean> p = new HashMap();
    private boolean U;
    private int V;
    private boolean S;
    private int l;
    private long b = System.nanoTime();
    private int g;
    private int Z;

    private boolean J(int n, boolean bl) {
        this.b = System.nanoTime();
        this.S = bl;
        this.l = n;
        if (!ClientSettings.fW.P) {
            if (bl) {
                GuiScreenNativeCallbackBridge.mouseClicked(null, this.g, this.Z, n);
            }
            this.U = true;
            return true;
        }
        return new EventMouseButton(n, bl).fire();
    }

    public boolean setButtonState(int n, boolean bl) {
        boolean bl2 = this.p.getOrDefault(n, false);
        boolean bl3 = false;
        if (bl2 != bl) {
            bl3 = this.J(n, bl);
        }
        this.p.put(n, bl);
        return bl3;
    }

    public boolean updateCursorPosition(int n, int n2) {
        this.g = n;
        this.Z = n2;
        if (!ClientSettings.fW.P) {
            GuiScreenNativeCallbackBridge.handleMouseInput(null);
        }
        return false;
    }

    public int getLastButton() {
        return this.l;
    }


    public boolean H(int n) {
        return this.p.getOrDefault(n, false);
    }

    public long getLastChangeTime() {
        return this.b;
    }

    public boolean isButtonDown(int n) {
        return this.p.getOrDefault(n, false);
    }

    public boolean setScrollDelta(int n) {
        if (!ClientSettings.fW.P) {
            this.V = n;
            return true;
        }
        return false;
    }

    public int getMouseX() {
        return this.g;
    }

    public int getMouseY() {
        return this.Z;
    }

    public boolean Q() {
        return this.U;
    }

    public boolean isLastButtonDown() {
        return this.S;
    }

    public void S() {
    }

    public void resetScrollDelta() {
        this.V = 0;
    }

    public int getScrollDelta() {
        int n = this.V;
        return n;
    }
}

