package gg.vape.ui.click.component.value;

import gg.vape.Vape;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.font.FontOption;
import gg.vape.value.ModeValue;

public final class PublicProfileModeDropdownComponent
extends DropdownSelectComponent<FontOption> {
    public PublicProfileModeDropdownComponent(ModeValue modeValue) {
        super(modeValue);
    }

    @Override
    public void L$src$V$1e9izof() {
        Vape.INSTANCE.getFontSelector().N((FontOption)this.j$src$Ljava_lang_Object_$an7bt2());
    }
}

