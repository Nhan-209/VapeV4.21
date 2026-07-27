package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ui.TextSuggestionClickListener;
import gg.vape.module.utility.inventory.cleaner.ui.TextSuggestionDecoratedInput;
import gg.vape.module.utility.inventory.cleaner.ui.TextSuggestionRow;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.value.FixedStringListSuggestionProvider;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class TextSuggestionInputComponent
extends FlowLayoutComponent {
    private static final String eb;
    private SquareIconButtonComponent p_;
    private final LabeledTextInputComponent pi;
    private static String[] p1;
    private final List<TextSuggestionRow> pB = new ArrayList<TextSuggestionRow>();
    private final Consumer<TextSuggestionRow> pk;

    static {
        TextSuggestionInputComponent.n(null);
        eb = "newclose";
    }

    public TextSuggestionInputComponent(String string, Consumer<TextSuggestionRow> consumer, double d, double d2, boolean bl, boolean bl2) {
        super(d);
        this.pk = consumer;
        FixedStringListSuggestionProvider fixedStringListSuggestionProvider = new FixedStringListSuggestionProvider();
        fixedStringListSuggestionProvider.setComparator(null);
        this.pi = new TextSuggestionDecoratedInput(this, string, bl, bl2);
        this.pi.E(fixedStringListSuggestionProvider);
        this.pi.v$src$Lgg_vape_ui_click_component_IconButtonComponent_$9khxxe().Z(false);
        this.p_ = new SquareIconButtonComponent(eb, 1.0);
        this.p_.Z(false);
        this.p_.o(10.0);
        this.p_.Y(10.0);
        this.pi.o(d);
        this.pi.Y(d2);
        this.pi.d(false);
        this.pi.e(false);
        this.pi.C(0.0);
        this.pi.H(0.0f);
        this.pi.O(0.0f);
        this.pi.d(false);
        this.pi.e(false);
        this.pi.T(Color.RED);
        this.pi.W(Color.BLUE);
        this.d(false);
        this.pi.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
        this.pi.A(TextSuggestionInputComponent.J.h);
        this.h(this.pi, new Object[0]);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.pi.j(new TextSuggestionClickListener(this, atomicBoolean, consumer));
    }

    public TextInputComponentBase Q$src$Lgg_vape_ui_click_component_TextInputComponentBa$1qaiwh6() {
        return this.pi;
    }

    @Override
    public double x() {
        return super.x();
    }

    public List<TextSuggestionRow> s$src$Ljava_util_List_$1i440fr() {
        return this.pB;
    }

    public static String[] f$src$ALjava_lang_String_$1b1orlo() {
        return p1;
    }

    public static void n(String[] stringArray) {
        p1 = stringArray;
    }

    public void L$src$V$cciqa9() {
        TextSuggestionRow textSuggestionRow;
        if (!this.pB.isEmpty() && (textSuggestionRow = this.pB.remove(this.pB.size() - 1)) != null) {
            this.pk.accept(textSuggestionRow);
        }
    }

    static List<TextSuggestionRow> a(TextSuggestionInputComponent textSuggestionInputComponent) {
        return textSuggestionInputComponent.pB;
    }

    @Override
    public double C() {
        return super.C();
    }

    public void W() {
        if (!this.pi.i$src$Ljava_lang_String_$1n2xf3k().isEmpty() || !this.pB.isEmpty()) {
            this.pi.k("");
            this.pB.clear();
        }
    }

    public boolean J(String string) {
        for (TextSuggestionRow textSuggestionRow : this.pB) {
            if (!textSuggestionRow.x$src$Ljava_lang_String_$1m64ofa().equalsIgnoreCase(string)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void H() {
        super.H();
    }

    public SquareIconButtonComponent t$src$Lgg_vape_ui_click_component_SquareIconButtonComp$a4ih09() {
        return this.p_;
    }

    public List<String> K$src$Ljava_util_List_$14fso67() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (TextSuggestionRow textSuggestionRow : this.pB) {
            arrayList.add(textSuggestionRow.x$src$Ljava_lang_String_$1m64ofa());
        }
        return arrayList;
    }

    public void z(TextSuggestionRow textSuggestionRow) {
        this.pB.add(textSuggestionRow);
    }

    public void Q(TextSuggestionRow textSuggestionRow) {
        this.pB.remove(textSuggestionRow);
    }

}
