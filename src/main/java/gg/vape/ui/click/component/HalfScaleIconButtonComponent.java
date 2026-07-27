package gg.vape.ui.click.component;

import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.utils.render.ImageRenderer;

public class HalfScaleIconButtonComponent
extends IconButtonComponent {
    @Override
    public void H() {
        float f = (float)ImageRenderer.m(this.T$src$Ljava_lang_String_$1x2cerw());
        float f2 = (float)ImageRenderer.j(this.T$src$Ljava_lang_String_$1x2cerw());
        float f3 = (float)(this.G$src$D$1b2f02a() + this.A() / 2.0);
        f3 -= f / 4.0f;
        float f4 = (float)(this.n() + this.L() / 2.0);
        f4 -= f2 / 4.0f;
        f3 = (int)f3;
        f4 = (int)f4;
        if (this.D$src$Ljava_awt_Color_$os7bh8() != null) {
            ImageRenderer.E(this.D$src$Ljava_awt_Color_$os7bh8(), f3, f4, this.T$src$Ljava_lang_String_$1x2cerw(), f / 2.0f, f2 / 2.0f, false);
        } else {
            ImageRenderer.E(this.w$src$Z$e457mb() ? this.N() : this.e$src$Ljava_awt_Color_$1yl68fq(), f3, f4, this.T$src$Ljava_lang_String_$1x2cerw(), f / 2.0f, f2 / 2.0f, false);
        }
    }

    public HalfScaleIconButtonComponent(String string) {
        super(string);
    }

}

