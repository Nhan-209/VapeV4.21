package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.Optional;

public class MPacketVelocityBridge
extends Mapping {
    private MappingField p;
    private MappingField O;
    private MappingField W;
    private MappingField E;

    private void A(Object object, float f) {
        this.p.setFloat(object, f);
    }

    public MPacketVelocityBridge() {
        this(MSPacketEntityVelocity.G());
    }

    private MPacketVelocityBridge(int[] nArray) {
        super(MappedClasses.qe);
        if (nArray != null) {
            if (ForgeVersion.MC_1_21_4.d()) {
                Class<Optional> clazz = Optional.class;
                boolean bl = true;
                String string = "playerKnockback";
                MPacketVelocityBridge mPacketVelocityBridge = this;
                this.W = mPacketVelocityBridge.J(string, bl, clazz);
            } else {
                Class<Float> clazz = Float.TYPE;
                boolean bl = Wrapper.isNativeAvailable;
                String string = "field_149152_f";
                MPacketVelocityBridge mPacketVelocityBridge = this;
                this.E = mPacketVelocityBridge.J(string, bl, clazz);
                Class<Float> clazz2 = Float.TYPE;
                boolean bl2 = Wrapper.isNativeAvailable;
                String string2 = "field_149153_g";
                MPacketVelocityBridge mPacketVelocityBridge2 = this;
                this.p = this.J(string2, bl2, clazz2);
                Class<Float> clazz3 = Float.TYPE;
                boolean bl3 = Wrapper.isNativeAvailable;
                String string3 = "field_149159_h";
                MPacketVelocityBridge mPacketVelocityBridge3 = this;
                this.O = this.J(string3, bl3, clazz3);
            }
            return;
        }
        Class<Float> clazz = Float.TYPE;
        boolean bl = Wrapper.isNativeAvailable;
        String string = "field_149159_h";
        MPacketVelocityBridge mPacketVelocityBridge = this;
        this.O = mPacketVelocityBridge.J(string, bl, clazz);
    }

    private float w(Object object) {
        return this.E.getFloat(object);
    }

    private void Y(Object object, float f) {
        this.O.setFloat(object, f);
    }

    public static float z(MPacketVelocityBridge mPacketVelocityBridge, Object object) {
        return mPacketVelocityBridge.w(object);
    }

    private void N(Object object, float f) {
        this.E.setFloat(object, f);
    }

    public Optional<Object> M(Object object) {
        return (Optional)this.W.getObject(object);
    }

    public static void i(MPacketVelocityBridge mPacketVelocityBridge, Object object, float f) {
        mPacketVelocityBridge.Y(object, f);
    }

    public static void u(MPacketVelocityBridge mPacketVelocityBridge, Object object, float f) {
        mPacketVelocityBridge.A(object, f);
    }

    public static float A(MPacketVelocityBridge mPacketVelocityBridge, Object object) {
        return mPacketVelocityBridge.Q(object);
    }

    public static float Y(MPacketVelocityBridge mPacketVelocityBridge, Object object) {
        return mPacketVelocityBridge.t(object);
    }

    private float Q(Object object) {
        return this.p.getFloat(object);
    }

    public static void P(MPacketVelocityBridge mPacketVelocityBridge, Object object, float f) {
        mPacketVelocityBridge.N(object, f);
    }


    private float t(Object object) {
        return this.O.getFloat(object);
    }
}

