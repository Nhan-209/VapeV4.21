package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MShortPacketFieldBridge
extends Mapping {
    private final MappingField d;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public short P(Object object) {
        return this.d.getShort(object);
    }

    public MShortPacketFieldBridge() {
        this(MPacketIdFactory.A());
    }

    private MShortPacketFieldBridge(GuiComponent[] guiComponentArray) {
        super(MappedClasses.zy);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_7_10.L() && !Wrapper.c.isVanillaMinecraftPresent()) {
                Class<Short> clazz = Short.TYPE;
                boolean bl = Wrapper.G;
                String string = "field_149534_b";
                MShortPacketFieldBridge mShortPacketFieldBridge = this;
                this.d = mShortPacketFieldBridge.J(string, bl, clazz);
            } else {
                Class<Short> clazz = Short.TYPE;
                boolean bl = true;
                String string = "uid";
                MShortPacketFieldBridge mShortPacketFieldBridge = this;
                this.d = mShortPacketFieldBridge.J(string, bl, clazz);
            }
            return;
        }
        if (!ForgeVersion.MC_1_7_10.L()) {
            Class<Short> clazz = Short.TYPE;
            boolean bl = Wrapper.G;
            String string = "field_149534_b";
            MShortPacketFieldBridge mShortPacketFieldBridge = this;
            mShortPacketFieldBridge.J(string, bl, clazz);
        }
        Class<Short> clazz = Short.TYPE;
        boolean bl = true;
        String string = "uid";
        MShortPacketFieldBridge mShortPacketFieldBridge = this;
        this.d = mShortPacketFieldBridge.J(string, bl, clazz);
    }
}
