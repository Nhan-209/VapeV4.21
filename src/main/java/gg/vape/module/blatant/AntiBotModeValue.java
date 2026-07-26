package gg.vape.module.blatant;

import gg.vape.unmap.ModeSelection;
import gg.vape.value.ModeValue;

public class AntiBotModeValue
extends ModeValue {
    public static AntiBotModeValue u(Object object, String string, String string2, ModeSelection modeSelection, int n, ModeSelection ... modeSelectionArray) {
        AntiBotModeValue mo_02 = new AntiBotModeValue(object, string, string, modeSelection, modeSelectionArray);
        mo_02.Z$src$Lgg_vape_value_Value_$16i62fx(string2);
        for (ModeSelection modeSelection2 : modeSelectionArray) {
            modeSelection2.S(mo_02);
        }
        return mo_02;
    }

    public AntiBotModeValue(Object object, String string, String string2, ModeSelection modeSelection, ModeSelection[] modeSelectionArray) {
        super(object, string, string2, modeSelection, modeSelectionArray);
    }
}

