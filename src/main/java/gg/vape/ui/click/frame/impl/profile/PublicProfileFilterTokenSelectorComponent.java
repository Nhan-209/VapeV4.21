package gg.vape.ui.click.frame.impl.profile;

import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenSearchInputComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenSelectorClickHandler;
import gg.vape.value.FixedStringListSuggestionProvider;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PublicProfileFilterTokenSelectorComponent
extends FlowLayoutComponent {
    SquareIconButtonComponent TA;
    private final Runnable T1;
    private boolean T7 = false;
    private final List<PublicProfileFilterTokenComponent> TY = new ArrayList<PublicProfileFilterTokenComponent>();
    private final TextInputComponentBase TF;
    private PublicProfileFilterTokenComponent To;

    public boolean F(String string) {
        for (PublicProfileFilterTokenComponent publicProfileFilterTokenComponent : this.TY) {
            if (!publicProfileFilterTokenComponent.N().equalsIgnoreCase(string)) continue;
            return true;
        }
        return false;
    }

    public TextInputComponentBase o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz() {
        return this.TF;
    }

    public void k$src$V$15g9qa7() {
        if (!this.TF.i$src$Ljava_lang_String_$1n2xf3k().isEmpty() || !this.TY.isEmpty()) {
            this.TF.k("");
            this.TY.clear();
            this.T1.run();
        }
    }

    @Override
    public void H() {
        super.H();
        double d = 0.0;
        for (PublicProfileFilterTokenComponent publicProfileFilterTokenComponent : this.TY) {
            if (!((d += publicProfileFilterTokenComponent.A() + 2.0) > this.A() / 2.0)) continue;
            this.T7 = true;
            return;
        }
        this.T7 = false;
    }

    public void V(PublicProfileFilterTokenComponent publicProfileFilterTokenComponent) {
        this.TY.add(publicProfileFilterTokenComponent);
        this.T1.run();
    }

    @Override
    public double x() {
        return super.x();
    }

    public List<PublicProfileFilterTokenComponent> i$src$Ljava_util_List_$1ydnhqa() {
        return this.TY;
    }

    @Override
    public double C() {
        return super.C();
    }

    public static PublicProfileFilterTokenComponent E(PublicProfileFilterTokenSelectorComponent publicProfileFilterTokenSelectorComponent) {
        return publicProfileFilterTokenSelectorComponent.To;
    }

    public List<String> m$src$Ljava_util_List_$17c1eke() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (PublicProfileFilterTokenComponent publicProfileFilterTokenComponent : this.TY) {
            arrayList.add(publicProfileFilterTokenComponent.N());
        }
        return arrayList;
    }

    public void R(PublicProfileFilterTokenComponent publicProfileFilterTokenComponent) {
        this.TY.remove(publicProfileFilterTokenComponent);
        this.T1.run();
    }

    public static List<PublicProfileFilterTokenComponent> u(PublicProfileFilterTokenSelectorComponent publicProfileFilterTokenSelectorComponent) {
        return publicProfileFilterTokenSelectorComponent.TY;
    }

    public PublicProfileFilterTokenSelectorComponent(String string, Runnable runnable, double d, double d2, boolean bl, boolean bl2) {
        super(d);
        this.T1 = runnable;
        this.To = new PublicProfileFilterTokenComponent("...");
        FixedStringListSuggestionProvider fixedStringListSuggestionProvider = new FixedStringListSuggestionProvider();
        fixedStringListSuggestionProvider.setComparator(null);
        this.TF = new PublicProfileFilterTokenSearchInputComponent(this, string, bl, bl2, runnable);
        this.TF.E(fixedStringListSuggestionProvider);
        this.TA = new SquareIconButtonComponent("newclose", 1.0);
        this.TA.Z(false);
        this.TA.o(10.0);
        this.TA.Y(10.0);
        PaddedComponent paddedComponent = new PaddedComponent(5.0, 0.0, 1.0, 4.0, this.TA);
        paddedComponent.o(15.0);
        paddedComponent.Y(10.0);
        this.TF.o(d - 16.0);
        this.TF.Y(d2);
        this.TF.d(false);
        this.TF.e(false);
        this.TF.C(0.0);
        this.TF.H(0.0f);
        this.TF.O(0.0f);
        this.TF.d(false);
        this.TF.e(false);
        this.TF.T(Color.RED);
        this.TF.W(Color.BLUE);
        this.d(false);
        this.TF.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
        this.TF.A(PublicProfileFilterTokenSelectorComponent.J.h);
        this.h(this.TF, new Object[0]);
        this.h(paddedComponent, new Object[0]);
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.TF.j(new PublicProfileFilterTokenSelectorClickHandler(this, atomicBoolean));
    }

    public static boolean A(PublicProfileFilterTokenSelectorComponent publicProfileFilterTokenSelectorComponent) {
        return publicProfileFilterTokenSelectorComponent.T7;
    }

    public SquareIconButtonComponent B$src$Lgg_vape_ui_click_component_SquareIconButtonComp$6e843w() {
        return this.TA;
    }

    public void A$src$V$14t6dd1() {
        if (!this.TY.isEmpty()) {
            this.TY.remove(this.TY.size() - 1);
            this.T1.run();
        }
    }

}
