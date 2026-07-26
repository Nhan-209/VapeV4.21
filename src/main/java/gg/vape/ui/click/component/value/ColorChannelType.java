package gg.vape.ui.click.component.value;

public enum ColorChannelType {
    BLOCK_CHILD("Custom color"),
    RAINBOW("Rainbow"),
    SATURATION("Saturation"),
    VIBRANCE("Vibrance"),
    OPACITY("Opacity");

    private final String O;
    private static final ColorChannelType[] y;

    public String T() {
        return this.O;
    }

    static {
        String[] stringArray = new String[]{"Opacity", "SATURATION", "Saturation", "Custom color", "OPACITY", "BLOCK_CHILD", "Vibrance", "Rainbow", "VIBRANCE", "RAINBOW"};





        y = new ColorChannelType[]{BLOCK_CHILD, RAINBOW, SATURATION, VIBRANCE, OPACITY};
    }

    private ColorChannelType(String string2) {
        this.O = string2;
    }
}

