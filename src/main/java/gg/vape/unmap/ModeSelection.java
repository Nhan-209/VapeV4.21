package gg.vape.unmap;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.INamed;
import gg.vape.unmap.PropertyContainer;
import gg.vape.value.ModeValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModeSelection
extends PropertyContainer
implements INamed {
    private final String t;
    private static int[] e;
    private ModeValue K;
    public static HashMap<ModeValue, List<ModeSelection>> J;

    public ModeSelection(String name) {
        this.t = name;
    }

    public String G() {
        return this.getName().replace("\u00a7", "");
    }

    @Override
    public String getName() {
        return this.t;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException exception) {
        return exception;
    }

    public String z() {
        return this.toString();
    }

    public ModeValue getMode() {
        return this.K;
    }

    public void S(ModeValue modeValue) {
        this.K = modeValue;
        if (!J.containsKey(modeValue)) {
            J.put(modeValue, new ArrayList());
        }
        J.get(modeValue).add(this);
    }

    public static void L(int[] values) {
        e = values;
    }

    public String toString() {
        return this.G();
    }

    public static ModeSelection x(ModeValue modeValue, String name) {
        List<ModeSelection> selections = J.get(modeValue);
        for (ModeSelection selection : selections) {
            if (!selection.z().equalsIgnoreCase(name)) continue;
            return selection;
        }
        return null;
    }

    public boolean s() {
        return false;
    }

    public static int[] q() {
        return e;
    }

    static {
        J = new HashMap();
        ModeSelection.L(null);
    }
}

