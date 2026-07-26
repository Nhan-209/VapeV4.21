package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MMainWindow;
import gg.vape.wrapper.Wrapper;

public class MainWindow
extends Wrapper {
    public MainWindow(Object object) {
        super(object);
    }

    public float i() {
        return MMainWindow.E(MainWindow.c.getMappingsMapperCompat().DU, this.I);
    }

    public float q() {
        return MMainWindow.y(MainWindow.c.getMappingsMapperCompat().DU, this.I);
    }

    public int x() {
        return MMainWindow.T(MainWindow.c.getMappingsMapperCompat().DU, this.I);
    }

    public int o() {
        return MMainWindow.C(MainWindow.c.getMappingsMapperCompat().DU, this.I);
    }

    public float r() {
        return MMainWindow.e(MainWindow.c.getMappingsMapperCompat().DU, this.I);
    }

    public float A() {
        return MMainWindow.W(MainWindow.c.getMappingsMapperCompat().DU, this.I);
    }
}

