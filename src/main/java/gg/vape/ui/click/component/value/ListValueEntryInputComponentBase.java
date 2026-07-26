package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.TextInputComponentBase;

public class ListValueEntryInputComponentBase
extends TextInputComponentBase {
    private int jY;
    private boolean jW;

    @Override
    public double r() {
        return this.A() - 35.0;
    }

    public int w$src$I$1yeostt() {
        return this.jY;
    }

    @Override
    public double C() {
        return 20.0;
    }

    public ListValueEntryInputComponentBase(boolean bl, String string) {
        this(bl, string, false);
    }

    public boolean N() {
        return this.jW;
    }

    public ListValueEntryInputComponentBase(boolean bl, String string, boolean bl2) {
        super(string);
        this.jW = bl2;
        this.a = bl ? ListValueEntryInputComponentBase.J.d : ListValueEntryInputComponentBase.J.B;
    }

    @Override
    public void p() {
    }

    @Override
    public double x() {
        return 110.0;
    }
}

