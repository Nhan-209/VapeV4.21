package gg.vape.wrapper.impl;

import gg.vape.wrapper.Wrapper;

public class PointOfView
extends Wrapper {
    public static PointOfView[] F() {
        Object[] objectArray = PointOfView.c.getMappingsMapperCompat().RZ.M();
        PointOfView[] w7_0Array = new PointOfView[objectArray.length];
        for (int i = 0; i < objectArray.length; ++i) {
            w7_0Array[i] = new PointOfView(objectArray[i]);
        }
        return w7_0Array;
    }

    public PointOfView(Object object) {
        super(object);
    }

}

