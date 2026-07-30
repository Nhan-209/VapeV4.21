package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.wrapper.impl.ForgeVersion;

public class MNetHandlerPlayClient
extends Mapping {
    private final MappingMethod Q;

    public static void sendPacket(MNetHandlerPlayClient mNetHandlerPlayClient, Object object, Object object2) {
        mNetHandlerPlayClient.sendPacket(object, object2);
    }

    public MNetHandlerPlayClient() {
        super(MappedClasses.Df);
        Class[] classArray = new Class[]{MappedClasses.s};
        Class<Void> clazz = Void.TYPE;
        String string = "handleEntityTeleport";
        MNetHandlerPlayClient mNetHandlerPlayClient = this;
        this.Q = ((MappingMethodBuilder)((MappingMethodBuilder)this.methodBuilder(string, clazz, classArray).setNameForVersion(ForgeVersion.MC_1_16_5.n(), "handleTeleportEntity")).setOwnerClassForVersion(ForgeVersion.MC_1_21_4.n(), MappedClasses.z2)).buildMethod();
    }

    private void sendPacket(Object object, Object object2) {
        this.Q.invokeVoid(object, object2);
    }
}

