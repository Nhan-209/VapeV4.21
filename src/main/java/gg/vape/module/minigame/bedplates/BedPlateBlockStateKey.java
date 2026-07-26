package gg.vape.module.minigame.bedplates;

import gg.vape.module.minigame.bedplates.BedPlateBlockStateKeyMarker;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import java.util.Objects;

public class BedPlateBlockStateKey {
    public int f;
    private boolean n;
    public int i;

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        BedPlateBlockStateKey bedPlateBlockStateKey = (BedPlateBlockStateKey)object;
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.i == bedPlateBlockStateKey.i;
        }
        return this.i == bedPlateBlockStateKey.i && this.f == bedPlateBlockStateKey.f;
    }

    private BedPlateBlockStateKey(int n, int n2) {
        this.i = n;
        this.f = n2;
        this.n = n == 0 || Item.T(n).isNull();
    }

    public String toString() {
        return "BlockData{id=" + this.i + ", meta=" + this.f + '}';
    }

    public BedPlateBlockStateKey(int n, int n2, BedPlateBlockStateKeyMarker bedPlateBlockStateKeyMarker) {
        this(n, n2);
    }

    public boolean U() {
        return this.n;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int hashCode() {
        return Objects.hash(this.i, this.f);
    }
}

