package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileReview;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.gui.AnimatedCenteredTextLabelComponent;
import gg.vape.ui.click.component.input.SmallTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileUserAvatarComponent;
import java.awt.Color;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class PublicProfileReviewComposerComponent
extends GuiComponent {
    private final boolean G;
    private final PublicProfile I;
    private final SmallTextInputComponent a;
    static final boolean Q = !PublicProfileReviewComposerComponent.class.desiredAssertionStatus();
    private final Runnable i;
    private FlowLayoutComponent v = new FlowLayoutComponent(100.0);
    private final Runnable K;
    private final GlyphIconComponent R;
    private final PublicProfileUserAvatarComponent b;
    private final PanelComponent o;

    @Override
    public double C() {
        return this.o.L();
    }

    private static ApiResponse lambda$submit$5(Throwable throwable) {
        return null;
    }

    private void lambda$submit$4(boolean bl, boolean bl2, ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            PublicProfileManager.b("Failed to leave review.");
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            PublicProfileManager.b("Failed to leave review: " + apiResponse.N());
            return;
        }
        if (!Q && apiResponse.T() == null) {
            throw new AssertionError();
        }
        if (this.G) {
            PublicProfileManager.M("Review updated!");
        } else {
            PublicProfileManager.M("Review posted!");
        }
        if (this.I.z() == null) {
            if (bl) {
                this.I.E(this.I.J() + 1L);
            } else {
                this.I.b(this.I.W() + 1L);
            }
        }
        this.I.B((PublicProfileReview)apiResponse.T());
        this.i.run();
        if (bl2) {
            this.K.run();
        }
    }

    private CompletableFuture lambda$new$3(Runnable runnable, boolean bl) {
        runnable.run();
        String string = this.a.i$src$Ljava_lang_String_$1n2xf3k().trim();
        if (string.isEmpty() && !bl) {
            PublicProfileManager.b("You must provide feedback when leaving a negative review!");
            return null;
        }
        return this.Y(bl, string, true);
    }

    private void lambda$new$1(Color color, char c, int n) {
        if (this.a.i$src$Ljava_lang_String_$1n2xf3k().isEmpty()) {
            this.R.o(PublicProfileReviewComposerComponent.J.W);
            this.R.k(true);
        } else {
            this.R.o(color);
            this.R.k(false);
        }
    }

    private void lambda$new$2(boolean bl) {
        if (bl) {
            this.a.b("Share additional feedback? (Optional)");
            this.a.A(PublicProfileReviewComposerComponent.J.B);
        } else {
            this.a.b("Please provide feedback with your rating...");
            this.a.A(PublicProfileReviewComposerComponent.J.I);
        }
    }

    public SmallTextInputComponent k() {
        return this.a;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$new$0() {
        CompletableFuture.runAsync(this.K, ClientSettings.f6);
    }

    public PublicProfileReviewComposerComponent(PublicProfile publicProfile, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
        this.I = publicProfile;
        this.G = bl2;
        this.K = runnable;
        this.i = runnable2;
        this.o = new PanelComponent(20.0, 20.0);
        this.o.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.o.d(false);
        this.b = new PublicProfileUserAvatarComponent(Vape.INSTANCE.getAccountInfo().i(), 15.0, 15.0);
        this.b.X(2.0f);
        Color color = bl ? PublicProfileReviewComposerComponent.J.B : PublicProfileReviewComposerComponent.J.d;
        Color color2 = bl ? PublicProfileReviewComposerComponent.J.O : PublicProfileReviewComposerComponent.J.c;
        this.R = new GlyphIconComponent("submit@2x", 6.0, 6.0, 6.0, 12.0, color, color2, null);
        this.R.o(PublicProfileReviewComposerComponent.J.W);
        this.R.k(true);
        this.R.R(true);
        AnimatedCenteredTextLabelComponent animatedCenteredTextLabelComponent = new AnimatedCenteredTextLabelComponent(bl && !bl2 ? "No thanks" : "Cancel", PublicProfileReviewComposerComponent.J.l);
        animatedCenteredTextLabelComponent.o(35.0);
        animatedCenteredTextLabelComponent.Y(12.0);
        animatedCenteredTextLabelComponent.y(0.75);
        animatedCenteredTextLabelComponent.s(this::lambda$new$0);
        this.a = new SmallTextInputComponent("");
        PublicProfileReview publicProfileReview = publicProfile.z();
        if (publicProfileReview != null) {
            this.a.k(publicProfileReview.I());
            this.a.o((arg_0, arg_1) -> this.lambda$new$1(color, arg_0, arg_1));
            Runnable runnable3 = () -> this.lambda$new$2(bl);
            runnable3.run();
            this.R.e(() -> this.lambda$new$3(runnable3, bl));
            this.v.h(this.R, new Object[0]);
            this.v.h(new FilledSpacerComponent(12.0, 12.0, 1.0, 6.0, PublicProfileReviewComposerComponent.J.l), new Object[0]);
            this.v.h(animatedCenteredTextLabelComponent, new Object[0]);
            this.v.d(false);
            this.o.H(this.b, this.a, new PaddedComponent(2.0, 0.0, 0.0, 0.0, this.v));
            this.H(this.o);
            if (bl && !bl2) {
                this.Y(true, publicProfileReview.I(), false);
            }
            return;
        }
        this.a.o((arg_0, arg_1) -> this.lambda$new$1(color, arg_0, arg_1));
        Runnable runnable4 = () -> this.lambda$new$2(bl);
        runnable4.run();
        this.R.e(() -> this.lambda$new$3(runnable4, bl));
        this.v.h(this.R, new Object[0]);
        this.v.h(new FilledSpacerComponent(12.0, 12.0, 1.0, 6.0, PublicProfileReviewComposerComponent.J.l), new Object[0]);
        this.v.h(animatedCenteredTextLabelComponent, new Object[0]);
        this.v.d(false);
        this.o.H(this.b, this.a, new PaddedComponent(2.0, 0.0, 0.0, 0.0, this.v));
        this.H(this.o);
        if (bl && !bl2) {
            this.Y(true, "", false);
        }
    }

    @Override
    public void H() {
        this.o.K(this.G$src$D$1b2f02a());
        this.o.S(this.n());
        this.o.o(this.A());
        this.o.Y(this.L());
        this.a.o(this.A() - (this.v.A() + 8.0) - this.b.A());
        this.o.l$src$V$1mibm4x();
    }

    private CompletableFuture<ApiResponse<PublicProfileReview>> Y(boolean bl, String string, boolean bl2) {
        return ApiServices.d().R().m(this.I, bl, string).whenCompleteAsync((arg_0, arg_1) -> this.lambda$submit$4(bl, bl2, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(PublicProfileReviewComposerComponent::lambda$submit$5);
    }

    public PublicProfileUserAvatarComponent g$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$1wft5vq() {
        return this.b;
    }

    @Override
    public double x() {
        return this.o.A();
    }
}
