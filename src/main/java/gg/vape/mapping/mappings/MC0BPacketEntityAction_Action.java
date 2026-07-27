package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MC0BPacketEntityAction_Action
extends Mapping {
    private MappingField f;
    private MappingField L;


    public Object O() {
        return this.f.getObject(null);
    }

    public MC0BPacketEntityAction_Action() {
        this(MPacketIdFactory.A());
    }

    private MC0BPacketEntityAction_Action(GuiComponent[] guiComponentArray) {
        super(MappedClasses.Do);
        if (guiComponentArray != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                if (ForgeVersion.MC_1_21_6.v()) {
                    Class clazz = MappedClasses.Do;
                    boolean bl = Wrapper.G;
                    String string = "PRESS_SHIFT_KEY";
                    MC0BPacketEntityAction_Action mC0BPacketEntityAction_Action = this;
                    this.L = mC0BPacketEntityAction_Action.u(string, bl, clazz);
                    Class clazz2 = MappedClasses.Do;
                    boolean bl2 = Wrapper.G;
                    String string2 = "RELEASE_SHIFT_KEY";
                    MC0BPacketEntityAction_Action mC0BPacketEntityAction_Action2 = this;
                    this.f = this.u(string2, bl2, clazz2);
                }
            } else {
                Class clazz = MappedClasses.Do;
                boolean bl = Wrapper.G;
                String string = "START_SNEAKING";
                MC0BPacketEntityAction_Action mC0BPacketEntityAction_Action = this;
                this.L = mC0BPacketEntityAction_Action.u(string, bl, clazz);
                Class clazz3 = MappedClasses.Do;
                boolean bl3 = Wrapper.G;
                String string3 = "STOP_SNEAKING";
                MC0BPacketEntityAction_Action mC0BPacketEntityAction_Action3 = this;
                this.f = this.u(string3, bl3, clazz3);
            }
            return;
        }
        Class clazz = MappedClasses.Do;
        boolean bl = Wrapper.G;
        String string = "STOP_SNEAKING";
        MC0BPacketEntityAction_Action mC0BPacketEntityAction_Action = this;
        this.f = mC0BPacketEntityAction_Action.u(string, bl, clazz); 
    }

    public Object R() {
        return this.L.getObject(null);
    }
}

