package gg.vape.ui.click.component.value;

import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.click.component.value.StringMapEntryComponent;
import gg.vape.ui.click.component.value.StringMapValueComponent;

class StringMapEntryRemoveHandler
implements GuiClickListener {
    final StringMapEntryComponent c;
    final StringMapValueComponent r;

    @Override
    public void P() {
        StringMapValueComponent.L(this.r).E(this.c.x$src$Ljava_lang_String_$18ql3qa());
        StringMapValueComponent.P(this.r);
    }

    StringMapEntryRemoveHandler(StringMapValueComponent stringMapValueComponent, StringMapEntryComponent stringMapEntryComponent) {
        this.r = stringMapValueComponent;
        this.c = stringMapEntryComponent;
    }
}

