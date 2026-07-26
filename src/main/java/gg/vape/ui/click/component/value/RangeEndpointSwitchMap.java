package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.value.RangeEndpoint;

class RangeEndpointSwitchMap {
    static final int[] e = new int[RangeEndpoint.values().length];

    RangeEndpointSwitchMap() {
    }

    static {
        try {
            RangeEndpointSwitchMap.e[RangeEndpoint.MINIMUM.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        try {
            RangeEndpointSwitchMap.e[RangeEndpoint.MAXIMUM.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
    }
}

