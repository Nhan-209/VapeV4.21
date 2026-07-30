package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MNetworkManager;
import gg.vape.wrapper.impl.ForgeVersion;

public class MPacket
extends Mapping {
    private final MappingMethod z;
    private MappingMethod n;

    private void processPacket(Object object, Object object2) {
        this.z.invokeVoid(object, object2);
    }

    public static boolean hasPriority(MPacket mPacket, Object object) {
        return mPacket.hasPriority(object);
    }

    public static void processPacket(MPacket mPacket, Object object, Object object2) {
        mPacket.processPacket(object, object2);
    }

    public MPacket() {
        this(MNetworkManager.Q());
    }

    private MPacket(String[] stringArray) {
        super(MappedClasses.Fm);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_7_10.L()) {
                Class[] classArray = new Class[]{};
                Class<Boolean> clazz = Boolean.TYPE;
                boolean bl = true;
                String string = "hasPriority";
                MPacket mPacket = this;
                this.n = mPacket.Y(string, bl, clazz, classArray);
            }
            Class[] classArray = new Class[]{MappedClasses.Yy};
            Class<Void> clazz = Void.TYPE;
            boolean bl = true;
            String string = "processPacket";
            MPacket mPacket = this;
            this.z = mPacket.Y(string, bl, clazz, classArray);
            return;
        }
        this.z = null;
    }


    private boolean hasPriority(Object object) {
        return this.n.invokeBoolean(object, new Object[0]);
    }
}
