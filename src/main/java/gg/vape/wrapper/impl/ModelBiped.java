package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MModelBiped;
import gg.vape.wrapper.impl.ModelBipedSkeletonBridge;
import gg.vape.wrapper.impl.ModelRenderer;

public class ModelBiped
extends ModelBipedSkeletonBridge {
    public ModelBiped(Object object) {
        super(object);
    }

    public ModelRenderer getBipedLeftArm() {
        return new ModelRenderer(MModelBiped.j(ModelBiped.c.getMappingsMapperCompat().Ct, this.I));
    }

    public ModelRenderer getBipedLeftLeg() {
        return new ModelRenderer(MModelBiped.k(ModelBiped.c.getMappingsMapperCompat().Ct, this.I));
    }

    public ModelRenderer getBipedRightLeg() {
        return new ModelRenderer(MModelBiped.J(ModelBiped.c.getMappingsMapperCompat().Ct, this.I));
    }

    public ModelRenderer getBipedRightArm() {
        return new ModelRenderer(MModelBiped.R(ModelBiped.c.getMappingsMapperCompat().Ct, this.I));
    }

    public ModelRenderer Z() {
        return new ModelRenderer(MModelBiped.a(ModelBiped.c.getMappingsMapperCompat().Ct, this.I));
    }

    public ModelRenderer F() {
        return new ModelRenderer(MModelBiped.x(ModelBiped.c.getMappingsMapperCompat().Ct, this.I));
    }
}

