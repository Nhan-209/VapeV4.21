package gg.vape.ui.click.component;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.DropdownPopupCloseClickHandler;
import gg.vape.ui.click.component.DropdownSelectOptionComponent;
import gg.vape.ui.click.component.DropdownSelectionListener;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.FocusableComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.ModeEntryTextFormatter;
import gg.vape.ui.click.component.OptionTextFormatter;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.font.FontOption;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.unmap.PropertyContainer;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.value.ModeValue;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import gg.vape.module.utility.inventory.cleaner.DescribedOption;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

public class DropdownSelectComponent<T>
extends GuiComponent
implements FocusableComponent {
    @Nullable
    private ModeValue O;
    private final Map<String, List<T>> i = new LinkedHashMap<String, List<T>>();
    private boolean o;
    private final OptionTextFormatter<T> Q;
    private T b;
    private boolean zl;
    @Nullable
    private PopupFrame zS;
    private final List<DropdownSelectionListener> v = new ArrayList<DropdownSelectionListener>();
    private boolean K;
    private final String R;
    private boolean G;
    private final ColorAnimation zn;
    private final float zi = 2.0f;
    private final PanelComponent zJ;
    private boolean I;
    private static String[] a;
    @Nullable
    private String zV;


    @Override
    public double x() {
        return 50.0;
    }

    @Override
    public void c() {
        super.c();
        PopupFrame popupFrame = this.zS;
        if (popupFrame != null) {
            this.b$src$V$1ru9wly();
            this.zJ.H(true);
            this.zJ.c();
        }
    }

    @Override
    public void u() {
        if (this.zl && !this.w$src$Z$e457mb() && !this.l$src$Z$1rzrun0()) {
            this.zn.J();
            this.zl = false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void H() {
        this.onDisable();
        SmoothFontRenderer smoothFontRenderer = this.O(0.85);
        Object selectedValue = this.O != null ? this.O.K() : this.b;
        String string = selectedValue != null ? this.Q.I(this.O != null ? (T)this.O.K() : this.b) : (this.zV != null ? this.zV : "");
        if (this.I && !this.R.isEmpty()) {
            string = this.R + " - " + string;
        }
        double d = smoothFontRenderer.d(string);
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        this.getClass();
        double d3 = d2 + (double)(5.0f / 8.0f);
        double d4 = this.n() + this.L() / 2.0 - (double)(this.zi / 2.0f);
        double d5 = this.L();
        this.getClass();
        double d6 = d5 - 5.0;
        if (this.l$src$Z$1rzrun0()) {
            d6 += this.zJ.L();
        }
        if (this.K) {
            double d7 = this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2();
            double d8 = this.n();
            this.getClass();
            GuiRenderPrimitives.e(d7, d8 + (double)(5.0f / 2.0f) + 0.5, this.A() - this.Z$src$D$1wvori2() - 8.0 + 2.0, d6 - 1.0, this.o ? DropdownSelectComponent.J.S.darker() : DropdownSelectComponent.J.S, false, 2.0f, 1.0f);
        } else {
            double d9 = this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2();
            double d10 = this.n();
            this.getClass();
            GuiRenderPrimitives.e(d9, d10 + (double)(5.0f / 2.0f) + 0.5, this.A() - this.Z$src$D$1wvori2() - 8.0 + 2.0, d6 - 1.0, this.o ? DropdownSelectComponent.J.l.darker() : DropdownSelectComponent.J.i, false, 2.0f, 1.0f);
            double d11 = this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2();
            double d12 = this.n();
            this.getClass();
            GuiRenderPrimitives.P(d11, d12 + (double)(5.0f / 2.0f), this.A() - this.Z$src$D$1wvori2() - 8.0 + 2.0, d6 - 1.0, this.zn.getInterpolatedColor(), 3.0f, 0.75f, 1.0f);
        }
        smoothFontRenderer.d(string, this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2() + 5.0, d3, this.o ? DropdownSelectComponent.J.h : DropdownSelectComponent.J.Z);
        Color color = DropdownSelectComponent.J.W;
        float f = (float)(this.G$src$D$1b2f02a() + this.A());
        this.getClass();
        ImageRenderer.E(color, f - 5.0f * 3.0f, (float)d4, this.l$src$Z$1rzrun0() ? "upcollapse" : "downexpand", this.zi, this.zi, false);
    }

    public static PopupFrame e(DropdownSelectComponent dropdownSelectComponent) {
        return dropdownSelectComponent.zS;
    }

    public void B(String string, T ... TArray) {
        for (T t : TArray) {
            this.e(string, t);
        }
    }

    public static void R(String[] stringArray) {
        a = stringArray;
    }

    public void L$src$V$1e9izof() {
    }

    @Override
    public void I() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void U$src$V$1rn4kw9() {
        PopupFrame popupFrame = this.zS;
        OptionTextFormatter<T> optionTextFormatter = this.Q;
        synchronized (optionTextFormatter) {
            if (popupFrame != null) {
                this.zS = null;
                ClientSettings.fT = null;
                ClientSettings.K(popupFrame);
            } else {
                this.zJ.S();
                this.zJ.q(this.A() - this.Z$src$D$1wvori2() - 8.0 + 2.0);
                this.zJ.V(3.0f);
                for (Map.Entry<String, List<T>> entry : this.i.entrySet()) {
                    String string = entry.getKey();
                    List<T> list = entry.getValue();
                    if (list.isEmpty()) continue;
                    if (string != null) {
                        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(string, 0.8);
                        simpleTextLabelComponent.l(true);
                        simpleTextLabelComponent.T$src$V$1orl066(DropdownSelectComponent.J.A);
                        simpleTextLabelComponent.o(simpleTextLabelComponent.h() * 1.3);
                        double d = (this.zJ.A() - simpleTextLabelComponent.A()) / 2.0;
                        this.zJ.h(new FilledSpacerComponent(d, simpleTextLabelComponent.L(), d - 4.0, 0.5, DropdownSelectComponent.J.y), "widthwrap");
                        this.zJ.h(simpleTextLabelComponent, "widthwrap");
                        this.zJ.h(new FilledSpacerComponent(d, simpleTextLabelComponent.L(), d - 4.0, 0.5, DropdownSelectComponent.J.y), "wrap");
                    }
                    for (T e : list) {
                        String string2 = this.Q.I(e);
                        DropdownSelectOptionComponent dropdownSelectOptionComponent = new DropdownSelectOptionComponent(string2, 0.85);
                        if (e instanceof DescribedOption) {
                            DescribedOption describedOption = (DescribedOption)e;
                            dropdownSelectOptionComponent.w(describedOption.E());
                        }
                        if (e instanceof FontOption) {
                            FontOption fontOption = (FontOption)e;
                            dropdownSelectOptionComponent.Y(fontOption);
                        }
                        if (e instanceof ModeOption) {
                            ModeOption modeOption = (ModeOption)e;
                            dropdownSelectOptionComponent.a(modeOption.getProperty(PropertyContainer.x));
                            dropdownSelectOptionComponent.K(modeOption.getProperty(PropertyContainer.B));
                        }
                        dropdownSelectOptionComponent.o(this.zJ.A() - 1.0);
                        dropdownSelectOptionComponent.Y(12.0);
                        dropdownSelectOptionComponent.s(() -> this.lambda$toggleContent$0(e));
                        this.zJ.h(dropdownSelectOptionComponent, "wrap");
                    }
                }
                this.zJ.h(new SpacerComponent(0.0, 0.5), "wrap");
                this.zJ.u(Math.min(this.zJ.d$src$D$ibccpu(), this.zJ.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y()));
                this.zJ.H(true);
                this.zS = ClientSettings.g(this, this.zJ, PopupFrame.class);
                this.zS.Z(new DropdownPopupCloseClickHandler(this));
                ClientSettings.fT = this;
                this.b$src$V$1ru9wly();
            }
        }
    }

    private static List lambda$addOption$1(String string) {
        return new ArrayList();
    }

    public void O(T t) {
        this.i.computeIfAbsent(null, DropdownSelectComponent::lambda$addOption$1).add(t);
    }

    private static List lambda$addOption$2(String string) {
        return new ArrayList();
    }

    @Nullable
    public String U$src$Ljava_lang_String_$10cfff1() {
        return this.zV;
    }

    public boolean l$src$Z$1rzrun0() {
        return this.zS != null;
    }

    public static String[] D$src$ALjava_lang_String_$11t6fwp() {
        return a;
    }

    public boolean J$src$Z$1rh2ugq() {
        return this.K;
    }

    public void G(T t) {
        this.b = t;
        if (t instanceof DescribedOption) {
            DescribedOption codeConverter$ArrayAccessReplacementMethodNames = (DescribedOption)t;
            this.w(codeConverter$ArrayAccessReplacementMethodNames.E());
        } else {
            this.w((String)null);
        }
    }

    public static void w(DropdownSelectComponent dropdownSelectComponent) {
        dropdownSelectComponent.U$src$V$1rn4kw9();
    }

    private void b$src$V$1ru9wly() {
        PopupFrame popupFrame = this.zS;
        if (popupFrame != null) {
            popupFrame.K(this.G$src$D$1b2f02a() + this.Z$src$D$1wvori2());
            popupFrame.S(this.n() + 17.0);
        }
    }

    @Override
    public void F() {
        if (!this.zl) {
            this.zn.J();
        }
        this.zl = true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void A$src$V$1rc4p11() {
        OptionTextFormatter<T> optionTextFormatter = this.Q;
        synchronized (optionTextFormatter) {
            if (this.zS != null) {
                ClientSettings.K(this.zS);
                this.zS = null;
                if (ClientSettings.fT == this) {
                    ClientSettings.fT = null;
                }
            }
        }
    }

    public void q(boolean bl) {
        this.I = bl;
    }

    @Override
    public boolean v() {
        return this.o;
    }

    public String o$src$Ljava_lang_String_$cd7zeb() {
        return this.R;
    }

    public @UnmodifiableView List<T> t$src$Ljava_util_List_$1iagpvr() {
        ArrayList<T> arrayList = new ArrayList<T>();
        for (List<T> list : this.i.values()) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public DropdownSelectComponent(String string, OptionTextFormatter<T> optionTextFormatter, T ... TArray) {
        this(string, optionTextFormatter, Arrays.asList(TArray));
    }

    public void v(boolean bl) {
        this.K = bl;
    }

    public T j$src$Ljava_lang_Object_$an7bt2() {
        return this.b;
    }

    public DropdownSelectComponent<T> D(DropdownSelectionListener dropdownSelectionListener) {
        this.v.add(dropdownSelectionListener);
        return this;
    }

    public void e(String string, T t) {
        this.i.computeIfAbsent(string, DropdownSelectComponent::lambda$addOption$2).add(t);
    }

    public boolean H$src$Z$1rfz9a0() {
        return this.I;
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public void F(boolean bl) {
        this.o = bl;
    }

    static {
        DropdownSelectComponent.R(new String[2]);
    }

    @SuppressWarnings("unchecked")
    public DropdownSelectComponent(ModeValue modeValue) {
        this(modeValue.getName(), (OptionTextFormatter<T>)(OptionTextFormatter<?>)ModeEntryTextFormatter.B, (T[])modeValue.getModes());
        this.O = modeValue;
        this.C(modeValue);
    }

    public void V(T ... TArray) {
        for (T t : TArray) {
            this.O(t);
        }
    }

    private void lambda$toggleContent$0(T object) {
        this.b = object;
        this.L$src$V$1e9izof();
        this.U$src$V$1rn4kw9();
        if (this.O != null) {
            this.O.setValue((ModeSelection)this.b);
            this.C(this.O);
        }
        for (DropdownSelectionListener dropdownSelectionListener : this.v) {
            dropdownSelectionListener.e();
        }
        if (this.O != null && !this.O.q$src$Ljava_util_List_$fyau59().isEmpty()) {
            this.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().l$src$V$1mibm4x();
        }
    }

    public DropdownSelectComponent(String string, OptionTextFormatter<T> optionTextFormatter, List<T> list) {
        this.getClass();
        this.zn = new ColorAnimation(0.15, DropdownSelectComponent.J.l, DropdownSelectComponent.J.y);
        this.I = true;
        this.K = false;
        this.R = string;
        this.Q = optionTextFormatter;
        this.o(110.0);
        this.i.put(null, list);
        this.zJ = new PanelComponent(110.0, 20.0);
        this.zJ.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.zJ.t(120.0);
        this.zJ.d(false);
        this.zJ.T(DropdownSelectComponent.J.R);
        this.zJ.I(true);
        this.o(true);
    }

    public void H(@Nullable String string) {
        this.zV = string;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.G) {
            return;
        }
        PopupFrame popupFrame = this.zS;
        if (popupFrame != null) {
            if (popupFrame.t()) {
                popupFrame.D(guiMouseEvent);
            }
            if (!this.o && !popupFrame.t()) {
                this.U$src$V$1rn4kw9();
            }
            return;
        }
        if (!this.o) {
            this.U$src$V$1rn4kw9();
        }
    }
}
