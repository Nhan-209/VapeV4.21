package gg.vape.ui.click.text;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.text.TextLabelFitSpec;
import gg.vape.ui.font.SmoothFontRenderer;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class TextLabelFitScaleCache {
    LinkedHashMap<String, Double> p = new LinkedHashMap();
    public static TextLabelFitScaleCache m = new TextLabelFitScaleCache();

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public double T(TextLabelFitSpec textLabelFitSpec) {
        if (this.p.containsKey(textLabelFitSpec.toString())) {
            return this.p.get(textLabelFitSpec.toString());
        }
        double d = textLabelFitSpec.u();
        while (d >= textLabelFitSpec.g() && d <= textLabelFitSpec.u()) {
            SmoothFontRenderer smoothFontRenderer;
            SmoothFontRenderer smoothFontRenderer2 = smoothFontRenderer = textLabelFitSpec.x() ? Vape.INSTANCE.getFontManager().W(d, false) : Vape.INSTANCE.getFontManager().E(d, false);
            if (!(smoothFontRenderer.N(textLabelFitSpec.o()) > textLabelFitSpec.T())) break;
            d = new BigDecimal(d).subtract(BigDecimal.valueOf(textLabelFitSpec.h())).setScale(1, 4).doubleValue();
        }
        this.p.put(textLabelFitSpec.toString(), d);
        return d;
    }
}

