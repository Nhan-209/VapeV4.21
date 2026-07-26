package gg.vape.ui.click.component.value;

public enum RangeEndpoint {
    MINIMUM,
    MAXIMUM;

    private static final RangeEndpoint[] B;

    static {
        String[] stringArray = new String[]{"MINIMUM", "MAXIMUM"};


        B = new RangeEndpoint[]{MINIMUM, MAXIMUM};
    }

}

