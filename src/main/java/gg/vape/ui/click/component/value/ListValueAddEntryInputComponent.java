package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.value.ListValueEntryInputComponentBase;
import gg.vape.ui.click.component.value.ListValueOptionsPanel;
import gg.vape.value.ListValue;

public class ListValueAddEntryInputComponent
extends ListValueEntryInputComponentBase {
    final ListValueOptionsPanel _a;
    final ListValue _Y;

    @Override
    public void p() {
        if (!this.u$src$Z$wt77ym()) {
            this.k("");
            return;
        }
        String string = this.i$src$Ljava_lang_String_$1n2xf3k();
        this._Y.j(string, -1);
        this._Y.g$src$V$1akzyia();
        this._a.k$src$V$admw0a();
        this.k("");
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ListValueAddEntryInputComponent(ListValueOptionsPanel listValueOptionsPanel, boolean bl, String string, ListValue listValue) {
        super(bl, string);
        this._a = listValueOptionsPanel;
        this._Y = listValue;
    }
}
