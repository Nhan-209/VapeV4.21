package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketUseEntity
extends Mapping {
    private MappingField E;
    private final MappingField Z;
    private final MappingField U;

    public static Object L(MCPacketUseEntity mCPacketUseEntity) {
        return mCPacketUseEntity.s();
    }

    private Object G() {
        return this.E.getObject(null);
    }

    private Object s() {
        return this.Z.getObject(null);
    }

    private Object e() {
        return this.U.getObject(null);
    }

    public MCPacketUseEntity() {
        this(MPacketIdFactory.A());
    }

    private MCPacketUseEntity(GuiComponent[] guiComponentArray) {
        super(MappedClasses.D5);
        if (guiComponentArray != null) {
            Class clazz = MappedClasses.D5;
            boolean bl = Wrapper.G;
            String string = "INTERACT";
            MCPacketUseEntity mCPacketUseEntity = this;
            this.U = mCPacketUseEntity.u(string, bl, clazz);
            Class clazz2 = MappedClasses.D5;
            boolean bl2 = Wrapper.G;
            String string2 = "ATTACK";
            MCPacketUseEntity mCPacketUseEntity2 = this;
            this.Z = this.u(string2, bl2, clazz2);
            if (ForgeVersion.MC_1_8_9.d()) {
                Class clazz3 = MappedClasses.D5;
                boolean bl3 = Wrapper.G;
                String string3 = "INTERACT_AT";
                MCPacketUseEntity mCPacketUseEntity3 = this;
                this.E = this.u(string3, bl3, clazz3);
            }
            return;
        }
        Class clazz = MappedClasses.D5;
        boolean bl = Wrapper.G;
        String string = "INTERACT";
        MCPacketUseEntity mCPacketUseEntity = this;
        this.U = mCPacketUseEntity.u(string, bl, clazz); 
        Class clazz4 = MappedClasses.D5;
        boolean bl4 = Wrapper.G;
        String string4 = "ATTACK";
        MCPacketUseEntity mCPacketUseEntity4 = this;
        this.E = this.u(string4, bl4, clazz4);
        this.Z = null;
    }

    public static Object x(MCPacketUseEntity mCPacketUseEntity) {
        return mCPacketUseEntity.G();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static Object W(MCPacketUseEntity mCPacketUseEntity) {
        return mCPacketUseEntity.e();
    }
}

