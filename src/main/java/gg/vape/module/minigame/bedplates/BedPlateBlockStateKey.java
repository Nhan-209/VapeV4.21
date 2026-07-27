package gg.vape.module.minigame.bedplates;

import gg.vape.module.minigame.bedplates.BedPlateBlockStateKeyMarker;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import java.util.Objects;

public class BedPlateBlockStateKey {
    public int f;
    private boolean isNull;
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

    private BedPlateBlockStateKey(int id, int meta) {
        this.i = id;
        this.f = meta;
        this.isNull = id == 0 || Item.T(id).isNull();
    }

    public String toString() {
        return "BlockData{id=" + this.i + ", meta=" + this.f + '}';
    }

    public BedPlateBlockStateKey(int id, int meta, BedPlateBlockStateKeyMarker bedPlateBlockStateKeyMarker) {
        this(id, meta);
    }

    public boolean U() {
        return this.isNull;
    }

    private static ObfuscatedRuntimeException passThroughException(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int hashCode() {
        return Objects.hash(this.i, this.f);
    }
}

