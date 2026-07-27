package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSPacketEntityVelocity;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MSPacketDestroyEntitiesArrayBridge
extends Mapping {
    private MappingField l;

    public Object M(Object object) {
        return this.l.getObject(object);
    }

    public MSPacketDestroyEntitiesArrayBridge() {
        this(MSPacketEntityVelocity.G());
    }

    private MSPacketDestroyEntitiesArrayBridge(int[] nArray) {
        super(MappedClasses.Yv);
        if (nArray != null) {
            if (ForgeVersion.MC_1_7_10.L()) {
                Class<int[]> clazz = int[].class;
                boolean bl = Wrapper.G;
                String string = "field_149100_a";
                MSPacketDestroyEntitiesArrayBridge mSPacketDestroyEntitiesArrayBridge = this;
                this.l = mSPacketDestroyEntitiesArrayBridge.J(string, bl, clazz);
            } else if (ForgeVersion.MC_1_17.d()) {
                Class clazz = MappedClasses.N;
                boolean bl = true;
                String string = "entityIds";
                MSPacketDestroyEntitiesArrayBridge mSPacketDestroyEntitiesArrayBridge = this;
                this.l = mSPacketDestroyEntitiesArrayBridge.J(string, bl, clazz);
            } else {
                Class<int[]> clazz = int[].class;
                boolean bl = true;
                String string = "entityIDs";
                MSPacketDestroyEntitiesArrayBridge mSPacketDestroyEntitiesArrayBridge = this;
                this.l = mSPacketDestroyEntitiesArrayBridge.J(string, bl, clazz);
            }
            return;
        }
        if (ForgeVersion.MC_1_7_10.L()) {
            Class clazz = MappedClasses.N;
            boolean bl = true;
            String string = "entityIds";
            MSPacketDestroyEntitiesArrayBridge mSPacketDestroyEntitiesArrayBridge = this;
            this.l = mSPacketDestroyEntitiesArrayBridge.J(string, bl, clazz);
        }
        Class<int[]> clazz = int[].class;
        boolean bl = true;
        String string = "entityIDs";
        MSPacketDestroyEntitiesArrayBridge mSPacketDestroyEntitiesArrayBridge = this;
        this.l = mSPacketDestroyEntitiesArrayBridge.J(string, bl, clazz);
    }

    public int[] Y(Object object) {
        return this.l.getIntArray(object);
    }

}
