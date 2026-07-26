package gg.vape.ui.click.component;

public enum IconShape {
    CIRCLE,
    ROUNDED_RECT;

    private static final IconShape[] R;

    static {
        String[] stringArray = new String[]{"ROUNDED_RECT", "CIRCLE"};


        R = new IconShape[]{CIRCLE, ROUNDED_RECT};
    }

}

