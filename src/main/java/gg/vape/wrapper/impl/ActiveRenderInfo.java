package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MActiveRenderInfo;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockReader;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.Quaternion;
import gg.vape.wrapper.impl.Vec3;

public class ActiveRenderInfo
extends Wrapper {
    public Entity W() {
        return new Entity(MActiveRenderInfo.Z(ActiveRenderInfo.c.getMappings().hi, this.I));
    }

    public void g(Vec3 vec3) {
        MActiveRenderInfo.P(ActiveRenderInfo.c.getMappings().hi, this.I, vec3.getObject());
    }

    public Quaternion G() {
        return new Quaternion(MActiveRenderInfo.d(ActiveRenderInfo.c.getMappings().hi, this.I));
    }

    public float Z() {
        return MActiveRenderInfo.q(ActiveRenderInfo.c.getMappings().hi, this.I);
    }

    public float x() {
        return MActiveRenderInfo.A(ActiveRenderInfo.c.getMappings().hi, this.I);
    }

    public Vec3 o() {
        return new Vec3(MActiveRenderInfo.p(ActiveRenderInfo.c.getMappings().hi, this.I));
    }

    public ActiveRenderInfo(Object object) {
        super(object);
    }

    public BlockReader x$src$Lgg_vape_wrapper_impl_BlockReader_$120g8sh() {
        return new BlockReader(MActiveRenderInfo.i(ActiveRenderInfo.c.getMappings().hi, this.I));
    }
}

