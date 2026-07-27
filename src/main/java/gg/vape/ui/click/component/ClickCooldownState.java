package gg.vape.ui.click.component;

import gg.vape.utils.TimerUtil;

public class ClickCooldownState {
    long e = 0L;
    boolean C = false;
    TimerUtil k = new TimerUtil();

    public long m() {
        return this.e;
    }


    public void I(long l) {
        this.e = l;
    }

    public boolean t() {
        boolean bl = this.C && !this.k.hasTimeElapsed(this.e);
        return bl;
    }

    public void j(boolean bl) {
        if (bl) {
            this.C = true;
            this.k.reset();
        } else {
            this.C = false;
        }
    }
}

