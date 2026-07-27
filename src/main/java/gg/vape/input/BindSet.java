package gg.vape.input;

import gg.vape.input.BindActivationMode;
import gg.vape.unmap.Bendable;
import gg.vape.unmap.BendableInputDispatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BindSet
extends Bendable {
    private final List<Integer> h;
    private final boolean i;
    private BindActivationMode v = BindActivationMode.TOGGLE;

    public BindSet(List<Integer> list, boolean bl, boolean bl2) {
        this.c(list);
        if (bl) {
            BendableInputDispatcher.H(this);
        }
        this.i = bl2;
        this.h = new ArrayList<Integer>(this.L());
    }

    @Override
    public void Y(BindActivationMode bindActivationMode) {
        this.v = bindActivationMode == null ? BindActivationMode.TOGGLE : bindActivationMode;
    }

    public BindSet(List<Integer> list, boolean bl) {
        this(list, bl, false);
    }

    @Override
    public boolean A$src$Z$jg36ch() {
        return this.i;
    }

    @Override
    public BindActivationMode G() {
        return this.v;
    }

    @Override
    public void A() {
    }

    public BindSet() {
        this.c(new ArrayList<Integer>());
        BendableInputDispatcher.H(this);
        this.i = false;
        this.h = new ArrayList<Integer>(this.L());
    }


    @Override
    public String y() {
        return this.h();
    }

    @Override
    public boolean m() {
        return false;
    }

    public BindSet(int n) {
        this.c(Arrays.asList(n));
        BendableInputDispatcher.H(this);
        this.i = false;
        this.h = new ArrayList<Integer>(this.L());
    }

    public List<Integer> o() {
        return this.h;
    }
}

