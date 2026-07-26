package gg.vape.ui.click.component.input;

import gg.vape.ui.click.component.TextInputComponentBase;

public class SmallTextInputComponent
extends TextInputComponentBase {
    private static int[] cu;

    public static void T(int[] nArray) {
        cu = nArray;
    }

    @Override
    public void p() {
    }

    @Override
    public double x() {
        return 110.0;
    }

    static {
        if (SmallTextInputComponent.r$src$AI$f3mb4q() != null) {
            SmallTextInputComponent.T(new int[2]);
        }
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public float p$src$F$1qfoyd() {
        return -2.0f;
    }

    public SmallTextInputComponent(String string) {
        super(string);
        this.e(false);
        this.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
        this.A(SmallTextInputComponent.J.h);
    }

    public static int[] r$src$AI$f3mb4q() {
        return cu;
    }
}

