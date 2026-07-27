package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ui.TextSuggestionInputComponent;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;

class TextSuggestionDecoratedInput
extends LabeledTextInputComponent {
    final TextSuggestionInputComponent oF;

    @Override
    public void l$src$V$1mkxjop() {
        if (this.i$src$Ljava_lang_String_$1n2xf3k().isEmpty()) {
            this.oF.L$src$V$cciqa9();
        }
    }

    @Override
    public float g() {
        float f = 0.0f;
        for (GuiComponent guiComponent : TextSuggestionInputComponent.a(this.oF)) {
            f += (float)guiComponent.A() + 2.0f;
        }
        return super.g() + (f + 2.0f);
    }


    TextSuggestionDecoratedInput(TextSuggestionInputComponent textSuggestionInputComponent, String string, boolean bl, boolean bl2) {
        super(string, bl, bl2);
        this.oF = textSuggestionInputComponent;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        super.g(guiMouseEvent);
        this.oF.g(guiMouseEvent);
    }

    @Override
    protected void R() {
        double d = 0.0;
        double d2 = this.n() + this.L() / 2.0;
        for (GuiComponent guiComponent : TextSuggestionInputComponent.a(this.oF)) {
            guiComponent.K(this.G$src$D$1b2f02a() + (double)super.g() + d);
            guiComponent.S(d2 - guiComponent.L() / 2.0);
            if (guiComponent.t()) {
                guiComponent.J();
            }
            guiComponent.c();
            d += guiComponent.A() + 2.0;
        }
    }
}
