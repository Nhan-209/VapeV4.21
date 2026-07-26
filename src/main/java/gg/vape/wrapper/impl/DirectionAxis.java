package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MDirectionAxis;
import gg.vape.wrapper.Wrapper;

public class DirectionAxis
extends Wrapper {
    public static DirectionAxis j() {
        return new DirectionAxis(MDirectionAxis.G(DirectionAxis.c.getMappingsMapperCompat().p));
    }

    public static DirectionAxis T() {
        return new DirectionAxis(MDirectionAxis.h(DirectionAxis.c.getMappingsMapperCompat().p));
    }

    public DirectionAxis(Object object) {
        super(object);
    }

    public double W(double d, double d2, double d3) {
        return DirectionAxis.c.getMappingsMapperCompat().p.T(this.I, d, d2, d3);
    }
}

