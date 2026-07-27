package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileReview;
import gg.vape.config.PublicProfileReviewDisplayType;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.MirroredSpacerComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SkeletonPlaceholderComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.gui.WrappedTextComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileDateFormatUtil;
import gg.vape.ui.click.frame.impl.profile.PublicProfileReviewReplyComposerComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileReviewResponsePanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfileUserAvatarComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import org.jetbrains.annotations.Nullable;

public class PublicProfileReviewComponent
extends GuiComponent {
    private PublicProfileReviewReplyComposerComponent a;
    private SimpleTextLabelComponent v;
    @Nullable
    private Runnable O;
    @Nullable
    private final PublicProfileReview o;
    private final PublicProfile b;
    private PanelComponent I;
    private final PublicProfileReviewDisplayType K;
    @Nullable
    private Runnable G;
    @Nullable
    private Runnable Q;
    @Nullable
    private Runnable R;

    public PublicProfileReviewDisplayType r$src$Lgg_vape_config_PublicProfileReviewDisplayType_$b7p1z4() {
        return this.K;
    }

    private void lambda$null$0(PublicProfileReview publicProfileReview) {
        publicProfileReview.B(this.b, this.R);
    }

    public PublicProfileReviewComponent g(@Nullable Runnable runnable) {
        this.G = runnable;
        return this;
    }

    @Override
    public double x() {
        return 0.0;
    }

    public PublicProfileReviewComponent u(@Nullable Runnable runnable) {
        this.O = runnable;
        return this;
    }

    @Override
    public double C() {
        if (this.o == null) {
            return 30.0;
        }
        if (this.o.I().isEmpty()) {
            return 15.0;
        }
        return this.I.L() + (this.a != null ? this.a.L() : 0.0) + 2.0;
    }

    private void lambda$new$7() {
        Vape.debugLog("parentFrame = " + this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa());
        ClientSettings.g(PublicProfilesFrame.class).y(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), new PublicProfileReviewResponsePanel(this.b, this.o));
    }

    private void lambda$new$1(PublicProfileReview publicProfileReview) {
        ConfirmationDialogComponent.U(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), "Are you sure you want to delete your review?", "Delete", "newtrash", () -> this.lambda$null$0(publicProfileReview));
    }

    private void lambda$null$5(Consumer consumer, AtomicReference atomicReference) {
        PanelComponent panelComponent = new PanelComponent(this.A(), 20.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.d(false);
        this.getClass();
        panelComponent.h(new SpacerComponent(5.0f * 2.0f, 0.0), new Object[0]);
        this.a = new PublicProfileReviewReplyComposerComponent(this.b, this.o, () -> PublicProfileReviewComponent.lambda$null$4(consumer, panelComponent), panelComponent.A() - 10.0, panelComponent.L());
        panelComponent.h(this.a, new Object[0]);
        atomicReference.set(panelComponent);
        this.H(panelComponent);
        Runnable runnable = this.G;
        if (runnable != null) {
            runnable.run();
        }
    }

    public PublicProfileReview y$src$Lgg_vape_config_PublicProfileReview_$4iqplh() {
        return this.o;
    }

    public PublicProfileReviewComponent q(@Nullable Runnable runnable) {
        this.R = runnable;
        return this;
    }

    public PublicProfileReviewComponent D(@Nullable Runnable runnable) {
        this.Q = runnable;
        return this;
    }


    public PublicProfileReviewComponent(PublicProfile publicProfile, @Nullable PublicProfileReview publicProfileReview, double d, PublicProfileReviewDisplayType publicProfileReviewDisplayType) {
        GuiComponent guiComponent;
        this.b = publicProfile;
        this.o = publicProfileReview;
        this.K = publicProfileReviewDisplayType;
        this.o(d);
        if (this.o == null) {
            this.I = new PanelComponent(d, this.C());
            this.I.d(false);
            this.I.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
            this.I.h(new SkeletonPlaceholderComponent(d / 2.0, 10.0), new Object[0]);
            this.I.h(new SpacerComponent(0.0, 2.0), new Object[0]);
            this.I.h(new SkeletonPlaceholderComponent(d, 12.0), new Object[0]);
            this.H(this.I);
            return;
        }
        this.I = new PanelComponent(d, 10.0);
        this.I.d(false);
        this.I.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.H(this.I);
        PanelComponent panelComponent = new PanelComponent(this.I.A(), 10.0);
        panelComponent.d(false);
        this.I.h(panelComponent, new Object[0]);
        panelComponent.h(new SpacerComponent(2.0, 0.0), new Object[0]);
        PanelComponent panelComponent2 = new PanelComponent(panelComponent.A() / 2.0, panelComponent.L());
        panelComponent2.d(false);
        panelComponent.h(panelComponent2, new Object[0]);
        PublicProfileUserAvatarComponent publicProfileUserAvatarComponent = new PublicProfileUserAvatarComponent(this.o.F(), 8.0, 8.0);
        PaddedComponent paddedComponent = new PaddedComponent(0.5, 0.0, 0.0, 0.0, publicProfileUserAvatarComponent);
        panelComponent2.h(paddedComponent, new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(this.o.F().o(), 0.7);
        double d2 = simpleTextLabelComponent.h();
        this.getClass();
        simpleTextLabelComponent.o(d2 + 5.0);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.Y(10.0);
        simpleTextLabelComponent.T$src$V$1orl066(PublicProfileReviewComponent.J.A);
        panelComponent2.h(simpleTextLabelComponent, new Object[0]);
        this.v = new SimpleTextLabelComponent("   ", 0.7);
        this.v.l(true);
        this.v.Y(10.0);
        this.v.T$src$V$1orl066(PublicProfileReviewComponent.J.Z);
        panelComponent2.h(this.v, new Object[0]);
        Date date = this.o.a() != null ? this.o.a() : this.o.P();
        SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent(PublicProfileDateFormatUtil.i(date), 0.7);
        simpleTextLabelComponent2.w(PublicProfileDateFormatUtil.T(date));
        double d3 = simpleTextLabelComponent2.h();
        this.getClass();
        simpleTextLabelComponent2.o(d3 + (double)(5.0f * 2.0f));
        simpleTextLabelComponent2.l(true);
        simpleTextLabelComponent2.Y(10.0);
        simpleTextLabelComponent2.T$src$V$1orl066(PublicProfileReviewComponent.J.h);
        panelComponent2.h(simpleTextLabelComponent2, new Object[0]);
        MutableColor mutableColor = this.o.X() ? new MutableColor(PublicProfileReviewComponent.J.B).F(0.8f).withAlpha(120) : new MutableColor(PublicProfileReviewComponent.J.d).withAlpha(150);
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent(this.o.X() ? "like active@2x" : "dislike active@2x", 6.0, 5.0, 20.0, 20.0, mutableColor, mutableColor, null);
        glyphIconComponent.w(this.o.X() ? 2.0 : 3.0);
        panelComponent2.h(glyphIconComponent, new Object[0]);
        PanelComponent panelComponent3 = new PanelComponent(this.K == PublicProfileReviewDisplayType.SELF ? 20.0 : (this.K == PublicProfileReviewDisplayType.REPLY ? 20.0 : 5.0), panelComponent.L());
        panelComponent3.d(false);
        panelComponent3.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.h(panelComponent3, "alignright");
        if (this.K == PublicProfileReviewDisplayType.SELF) {
            guiComponent = new TextLabel("delete", 0.75, false, 20.0, panelComponent3.L());
            ((TextLabel)guiComponent).c(true);
            ((InteractiveComponent)guiComponent).s(() -> this.lambda$new$1(publicProfileReview));
            panelComponent3.h(guiComponent, new Object[0]);
        } else if (this.K == PublicProfileReviewDisplayType.REPLY) {
            guiComponent = new TextLabel("reply", 0.75, false, 15.0, panelComponent3.L());
            panelComponent3.Z(false);
            ((TextLabel)guiComponent).l(null);
            ((TextLabel)guiComponent).c(true);
            AtomicReference atomicReference = new AtomicReference();
            ((InteractiveComponent)guiComponent).e(() -> this.lambda$new$6(atomicReference));
            panelComponent3.h(guiComponent, new Object[0]);
        }
        if (this.K == PublicProfileReviewDisplayType.REPLY || this.K == PublicProfileReviewDisplayType.OTHER) {
            guiComponent = new IconButtonComponent("flag comment hover@2x", 0.5, PublicProfileReviewComponent.J.Z, PublicProfileReviewComponent.J.f, 5.0, 5.0);
            panelComponent3.Z(false);
            ((InteractiveComponent)guiComponent).r(this::lambda$new$7);
            panelComponent3.h(guiComponent, "widthwrap");
            this.d(panelComponent3::Z);
        }
        guiComponent = new PanelComponent(this.I.A(), 20.0);
        ((FrameComponent)guiComponent).l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        guiComponent.d(false);
        this.I.h(guiComponent, "wrap");
        double d4 = 15.0;
        ((FrameComponent)guiComponent).h(new SpacerComponent(15.0, 0.0), new Object[0]);
        PanelComponent panelComponent4 = new PanelComponent(this.I.A() - 15.0, 10.0);
        panelComponent4.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent4.d(false);
        ((FrameComponent)guiComponent).h(panelComponent4, new Object[0]);
        WrappedTextComponent wrappedTextComponent = new WrappedTextComponent(this.o.I(), 0.8, PublicProfileReviewComponent.J.h, true);
        wrappedTextComponent.o(panelComponent4.A());
        wrappedTextComponent.c(panelComponent4.A());
        wrappedTextComponent.Y(7 * wrappedTextComponent.Q$src$Ljava_util_List_$1gv03oz().size());
        if (publicProfileReviewDisplayType == PublicProfileReviewDisplayType.REPLY && publicProfileReview.I$src$Z$148jdrc()) {
            wrappedTextComponent.l(true);
            wrappedTextComponent.T$src$V$1orl066(PublicProfileReviewComponent.J.A);
        }
        panelComponent4.h(new SpacerComponent(5.0, 1.0), new Object[0]);
        panelComponent4.h(wrappedTextComponent, new Object[0]);
        panelComponent4.h(new SpacerComponent(5.0, 2.0), new Object[0]);
        if (this.o.H() != null) {
            PanelComponent panelComponent5 = new PanelComponent(this.I.A() - 15.0, 15.0);
            panelComponent5.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
            panelComponent5.d(false);
            panelComponent4.h(panelComponent5, "wrap");
            panelComponent5.h(new MirroredSpacerComponent(panelComponent5, 1.0, new FilledSpacerComponent(1.0, panelComponent5.L(), PublicProfileReviewComponent.J.h)), new Object[0]);
            PanelComponent panelComponent6 = new PanelComponent(panelComponent5.A() - 1.0, 50.0);
            panelComponent6.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
            panelComponent6.d(false);
            panelComponent5.h(panelComponent6, new Object[0]);
            PanelComponent panelComponent7 = new PanelComponent(panelComponent6.A(), 6.0);
            panelComponent7.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
            panelComponent7.d(false);
            panelComponent6.h(panelComponent7, new Object[0]);
            SimpleTextLabelComponent simpleTextLabelComponent3 = new SimpleTextLabelComponent("Response from owner", 0.8, PublicProfileReviewComponent.J.A, true);
            simpleTextLabelComponent3.o(simpleTextLabelComponent3.h());
            simpleTextLabelComponent3.Y(8.0);
            panelComponent7.h(simpleTextLabelComponent3, new Object[0]);
            panelComponent7.h(new SpacerComponent(4.0, 0.0), new Object[0]);
            Date date2 = this.o.H().j() != null ? this.o.H().j() : this.o.H().Y();
            SimpleTextLabelComponent simpleTextLabelComponent4 = new SimpleTextLabelComponent(PublicProfileDateFormatUtil.i(date2), 0.8, PublicProfileReviewComponent.J.h, true);
            simpleTextLabelComponent4.w(PublicProfileDateFormatUtil.T(date2));
            simpleTextLabelComponent4.o(simpleTextLabelComponent4.h());
            simpleTextLabelComponent4.Y(8.0);
            panelComponent7.h(simpleTextLabelComponent4, new Object[0]);
            panelComponent6.h(new SpacerComponent(0.0, 2.0), new Object[0]);
            PanelComponent panelComponent8 = new PanelComponent(panelComponent6.A(), 8.0);
            panelComponent8.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
            panelComponent8.d(false);
            panelComponent6.h(panelComponent8, new Object[0]);
            panelComponent8.h(new SpacerComponent(5.0, 0.0), new Object[0]);
            WrappedTextComponent wrappedTextComponent2 = new WrappedTextComponent(this.o.H().m(), 0.8);
            wrappedTextComponent2.o(panelComponent6.A() - 8.0);
            wrappedTextComponent2.c(panelComponent6.A() - 8.0);
            wrappedTextComponent2.Y(7 * wrappedTextComponent2.Q$src$Ljava_util_List_$1gv03oz().size());
            panelComponent8.h(wrappedTextComponent2, new Object[0]);
            panelComponent8.u(-1.0);
            panelComponent8.Y(wrappedTextComponent2.L());
            panelComponent6.u(-1.0);
            panelComponent6.Y(panelComponent6.f().stream().mapToDouble(GuiComponent::L).sum());
            panelComponent5.u(-1.0);
            panelComponent5.Y(panelComponent6.L());
            panelComponent4.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        }
        panelComponent4.u(-1.0);
        panelComponent4.Y(panelComponent4.f().stream().mapToDouble(GuiComponent::L).sum());
        guiComponent.u(-1.0);
        guiComponent.Y(((FrameComponent)guiComponent).f().stream().mapToDouble(GuiComponent::L).sum());
        double d5 = this.I.f().stream().mapToDouble(GuiComponent::L).sum();
        this.I.u(-1.0);
        this.I.Y(d5);
    }

    @Override
    public void H() {
        this.I.K(this.G$src$D$1b2f02a());
        this.I.S(this.n());
        this.I.o(this.A());
        if (this.o != null) {
            PublicProfileReviewReplyComposerComponent publicProfileReviewReplyComposerComponent = this.a;
            if (publicProfileReviewReplyComposerComponent != null) {
                publicProfileReviewReplyComposerComponent.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().K(this.G$src$D$1b2f02a());
                publicProfileReviewReplyComposerComponent.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().S(this.n() + this.I.L());
                publicProfileReviewReplyComposerComponent.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb().P$src$V$i0cha4();
            }
            GuiRenderPrimitives.V(this.v.G$src$D$1b2f02a() + 4.0, this.v.n() + 4.0, 1.0, 1.0, PublicProfileReviewComponent.J.h);
        }
        this.I.l$src$V$1mibm4x();
    }

    private static void lambda$null$3(Consumer consumer, GuiComponent guiComponent) {
        consumer.accept(guiComponent);
    }

    private CompletableFuture lambda$new$6(AtomicReference atomicReference) {
        GuiComponent guiComponent = (GuiComponent)atomicReference.get();
        Consumer<GuiComponent> consumer = arg_0 -> this.lambda$null$2(atomicReference, arg_0);
        if (guiComponent != null) {
            return CompletableFuture.runAsync(() -> PublicProfileReviewComponent.lambda$null$3(consumer, guiComponent), ClientSettings.f6);
        }
        return CompletableFuture.runAsync(() -> this.lambda$null$5(consumer, atomicReference), ClientSettings.f6);
    }

    public PublicProfile K$src$Lgg_vape_config_PublicProfile_$rcyx8r() {
        return this.b;
    }

    @Override
    public void F() {
        Runnable runnable = this.O;
        if (runnable != null) {
            runnable.run();
        }
    }

    private void lambda$null$2(AtomicReference atomicReference, GuiComponent guiComponent) {
        this.I(guiComponent);
        this.a = null;
        Runnable runnable = this.G;
        if (runnable != null) {
            runnable.run();
        }
        atomicReference.set(null);
    }

    private static void lambda$null$4(Consumer consumer, PanelComponent panelComponent) {
        consumer.accept(panelComponent);
    }
}

