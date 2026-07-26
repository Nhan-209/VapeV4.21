package gg.vape.wrapper.impl;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.EntityEquipmentSlot;
import gg.vape.wrapper.impl.ResourceKey;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;

public class Equippable
extends Wrapper {
    @Nullable
    public ResourceKey j() {
        Optional optional = this.T();
        if (optional.isPresent()) {
            return new ResourceKey(optional.get());
        }
        return null;
    }

    public Optional T() {
        return Equippable.c.getMappings().C3.p(this.I);
    }

    public Equippable(Object object) {
        super(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public EntityEquipmentSlot m$src$Lgg_vape_wrapper_impl_EntityEquipmentSlot_$bzr9md() {
        return new EntityEquipmentSlot(Equippable.c.getMappings().C3.G(this.I));
    }
}

