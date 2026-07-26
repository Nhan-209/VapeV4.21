package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MItem_ToolMaterial;
import gg.vape.wrapper.Wrapper;

public class ToolMaterial
extends Wrapper {
    public float I() {
        return MItem_ToolMaterial.j(ToolMaterial.c.getMappings().Re, this.I);
    }

    public static ToolMaterial S() {
        return new ToolMaterial(MItem_ToolMaterial.H(ToolMaterial.c.getMappings().Re));
    }

    public static ToolMaterial u() {
        return new ToolMaterial(MItem_ToolMaterial.r(ToolMaterial.c.getMappings().Re));
    }

    public ToolMaterial(Object object) {
        super(object);
    }

    public static ToolMaterial f() {
        return new ToolMaterial(MItem_ToolMaterial.U(ToolMaterial.c.getMappings().Re));
    }

    public static ToolMaterial I$src$Lgg_vape_wrapper_impl_ToolMaterial_$3t5lsk() {
        return new ToolMaterial(MItem_ToolMaterial.M(ToolMaterial.c.getMappings().Re));
    }
}

