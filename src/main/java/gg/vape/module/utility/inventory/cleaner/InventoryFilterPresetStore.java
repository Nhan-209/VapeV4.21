package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.SharedInventoryFilterPreset;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryFilterPresetStore {
    private final List<SharedInventoryFilterPreset> A = new ArrayList<SharedInventoryFilterPreset>();

    @Nullable
    public SharedInventoryFilterPreset l(UUID uUID) {
        for (SharedInventoryFilterPreset pV : this.A) {
            if (!pV.j().equals(uUID)) continue;
            return pV;
        }
        return null;
    }

    public void r(SharedInventoryFilterPreset pV) {
        this.A.remove(pV);
    }

    public @UnmodifiableView List<SharedInventoryFilterPreset> M() {
        return this.A;
    }

    public void u(@Nullable SharedInventoryFilterPreset pV, SharedInventoryFilterPreset pV2) {
        if (pV != null) {
            this.r(pV);
        }
        this.n(pV2);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public void n(SharedInventoryFilterPreset pV) {
        this.A.add(pV);
    }
}

