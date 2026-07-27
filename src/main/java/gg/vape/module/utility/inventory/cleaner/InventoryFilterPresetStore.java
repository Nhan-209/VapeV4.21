package gg.vape.module.utility.inventory.cleaner;

import gg.vape.module.utility.inventory.cleaner.SharedInventoryFilterPreset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class InventoryFilterPresetStore {
    private final List<SharedInventoryFilterPreset> presets = new ArrayList<SharedInventoryFilterPreset>();

    @Nullable
    public SharedInventoryFilterPreset l(UUID uUID) {
        for (SharedInventoryFilterPreset preset : this.presets) {
            if (!preset.j().equals(uUID)) continue;
            return preset;
        }
        return null;
    }

    public void r(SharedInventoryFilterPreset preset) {
        this.presets.remove(preset);
    }

    public @UnmodifiableView List<SharedInventoryFilterPreset> M() {
        return this.presets;
    }

    public void u(@Nullable SharedInventoryFilterPreset oldPreset, SharedInventoryFilterPreset newPreset) {
        if (oldPreset != null) {
            this.r(oldPreset);
        }
        this.n(newPreset);
    }


    public void n(SharedInventoryFilterPreset preset) {
        this.presets.add(preset);
    }
}

