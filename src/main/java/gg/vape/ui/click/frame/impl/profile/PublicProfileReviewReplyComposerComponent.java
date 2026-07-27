package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileReview;
import gg.vape.config.PublicProfileReviewResponse;
import gg.vape.event.impl.PublicProfileReviewEvent;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.ActionButtonGroupComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.input.SmallTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileUserAvatarComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class PublicProfileReviewReplyComposerComponent
extends GuiComponent {
    private final ActionButtonGroupComponent K;
    private final IconButtonComponent i;
    private final PanelComponent I;
    static final boolean v = !PublicProfileReviewReplyComposerComponent.class.desiredAssertionStatus();
    private final SmallTextInputComponent Q;
    private final Runnable R;
    private final PublicProfileUserAvatarComponent b;

    public PublicProfileReviewReplyComposerComponent(PublicProfile publicProfile, PublicProfileReview publicProfileReview, Runnable runnable, double d, double d2) {
        this.R = runnable;
        this.I = new PanelComponent(d, d2);
        this.I.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.I.d(false);
        this.b = new PublicProfileUserAvatarComponent(Vape.INSTANCE.getAccountInfo().i(), 10.0, 10.0);
        this.b.X(2.0f);
        this.i = new IconButtonComponent("newclose", 0.8);
        this.i.o(PublicProfileReviewReplyComposerComponent.J.f);
        this.i.P(Color.white);
        this.Q = new SmallTextInputComponent("Leave a response!");
        this.Q.k(publicProfileReview.H() != null ? publicProfileReview.H().m() : "");
        this.Q.o(d - 20.0);
        this.Q.o(this::lambda$new$0);
        this.i.e(() -> this.lambda$new$3(publicProfileReview));
        this.K = new ActionButtonGroupComponent(this.i);
        this.K.d(false);
        this.K.T(0.0);
        this.K.o(5.0);
        this.K.Y(15.0);
        this.I.H(new PaddedComponent(0.0, 2.0, this.b), this.Q, this.K);
        this.H(this.I);
    }

    @Override
    public double C() {
        return this.I.L();
    }

    @Override
    public void H() {
        this.I.K(this.G$src$D$1b2f02a());
        this.I.S(this.n());
        this.I.o(this.A());
        this.I.Y(this.L());
        this.Q.o(this.A() - this.K.A() - this.b.A());
        this.I.l$src$V$1mibm4x();
        GuiRenderPrimitives.u(this.G$src$D$1b2f02a() + 18.0, this.n() + this.L() - 3.0, this.G$src$D$1b2f02a() + this.A() - 4.0, this.n() + this.L() - 3.0, 1.0f, PublicProfileReviewReplyComposerComponent.J.l);
    }

    public PublicProfileUserAvatarComponent W() {
        return this.b;
    }

    private void lambda$null$1(PublicProfileReview publicProfileReview, ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            PublicProfileManager.b("Failed to leave response.");
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            PublicProfileManager.b("Failed to leave response: " + apiResponse.N());
            return;
        }
        if (!v && apiResponse.T() == null) {
            throw new AssertionError();
        }
        if (publicProfileReview.H() != null) {
            PublicProfileManager.M("Response updated!");
        } else {
            PublicProfileManager.M("Response posted!");
        }
        publicProfileReview.U((PublicProfileReviewResponse)apiResponse.T());
        new PublicProfileReviewEvent(publicProfileReview).fire();
        this.R.run();
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    private void lambda$new$0(char c, int n) {
        if (this.Q.i$src$Ljava_lang_String_$1n2xf3k().isEmpty()) {
            this.i.H("newclose");
        } else {
            this.i.H("submit@2x");
        }
    }

    @Override
    public void F() {
    }


    @Override
    public double x() {
        return this.I.A();
    }

    @Override
    public void u() {
    }

    public ActionButtonGroupComponent b$src$Lgg_vape_ui_click_component_ActionButtonGroupCom$115ixr1() {
        return this.K;
    }

    public SmallTextInputComponent X$src$Lgg_vape_ui_click_component_input_SmallTextInput$3ef19x() {
        return this.Q;
    }

    private static ApiResponse lambda$null$2(Throwable throwable) {
        return null;
    }

    private CompletableFuture lambda$new$3(PublicProfileReview publicProfileReview) {
        if (this.i.T$src$Ljava_lang_String_$1x2cerw().equalsIgnoreCase("newclose")) {
            return CompletableFuture.runAsync(this.R, ClientSettings.f6);
        }
        String string = this.Q.i$src$Ljava_lang_String_$1n2xf3k().trim();
        return ApiServices.d().R().h(publicProfileReview, string).whenCompleteAsync((arg_0, arg_1) -> this.lambda$null$1(publicProfileReview, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(PublicProfileReviewReplyComposerComponent::lambda$null$2);
    }

    @Override
    public void I() {
    }
}
