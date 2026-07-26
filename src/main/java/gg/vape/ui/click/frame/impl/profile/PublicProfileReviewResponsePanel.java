package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileReview;
import gg.vape.config.PublicProfileReviewResponse;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.InsetFilledSpacerComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.input.SmallTextInputComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.PopupFrame;
import java.awt.Color;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.Nullable;

public class PublicProfileReviewResponsePanel
extends PanelComponent {
    private final PublicProfile ne;

    private ApiResponse lambda$null$2(Throwable throwable) {
        this.o$src$V$1sjvopv();
        return null;
    }

    private CompletableFuture lambda$setup$5(PublicProfileReview publicProfileReview, SmallTextInputComponent smallTextInputComponent, PublicProfileReviewResponse publicProfileReviewResponse) {
        if (publicProfileReview != null) {
            return ApiServices.d().R().Y(publicProfileReview.M(), smallTextInputComponent.i$src$Ljava_lang_String_$1n2xf3k()).whenCompleteAsync(this::lambda$null$1, (Executor)ClientSettings.f6).exceptionally(this::lambda$null$2);
        }
        if (publicProfileReviewResponse != null) {
            return ApiServices.d().R().h(publicProfileReviewResponse.c(), smallTextInputComponent.i$src$Ljava_lang_String_$1n2xf3k()).whenCompleteAsync(this::lambda$null$3, (Executor)ClientSettings.f6).exceptionally(this::lambda$null$4);
        }
        return null;
    }

    private void o$src$V$1sjvopv() {
        Frame frame = this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa();
        if (!(frame instanceof PopupFrame)) {
            return;
        }
        PopupFrame popupFrame = (PopupFrame)frame;
        ClientSettings.K(popupFrame);
    }

    private ApiResponse lambda$null$4(Throwable throwable) {
        this.o$src$V$1sjvopv();
        return null;
    }

    private void lambda$null$1(ApiResponse apiResponse, Throwable throwable) {
        this.o$src$V$1sjvopv();
        if (throwable != null) {
            Vape.logThrowable(throwable);
            PublicProfileManager.b("Failed to create report.");
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to create report: " + apiResponse.N());
            PublicProfileManager.b("Failed to create report: " + apiResponse.N());
            return;
        }
        PublicProfileManager.M("Successfully created report.");
    }

    public PublicProfileReviewResponsePanel(PublicProfile publicProfile, PublicProfileReviewResponse publicProfileReviewResponse) {
        super(200.0, 100.0);
        this.ne = publicProfile;
        this.E(null, publicProfileReviewResponse);
    }

    private void lambda$setup$0() {
        ClientSettings.f6.execute(this::o$src$V$1sjvopv);
    }

    public PublicProfileReviewResponsePanel(PublicProfile publicProfile, PublicProfileReview publicProfileReview) {
        super(200.0, 100.0);
        this.ne = publicProfile;
        this.E(publicProfileReview, null);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$null$3(ApiResponse apiResponse, Throwable throwable) {
        this.o$src$V$1sjvopv();
        if (throwable != null) {
            Vape.logThrowable(throwable);
            PublicProfileManager.b("Failed to create report.");
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to create report: " + apiResponse.N());
            PublicProfileManager.b("Failed to create report: " + apiResponse.N());
            return;
        }
        PublicProfileManager.M("Successfully created report.");
    }

    private void E(@Nullable PublicProfileReview publicProfileReview, @Nullable PublicProfileReviewResponse publicProfileReviewResponse) {
        if (publicProfileReview == null && publicProfileReviewResponse == null) {
            return;
        }
        this.d(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        String userName = publicProfileReview != null ? publicProfileReview.F().o() : this.ne.S() != null ? this.ne.S().o() : "Anonymous";
        String reportType = publicProfileReview != null ? "review" : "response";
        WrappingTextLabelComponent wrappingTextLabelComponent10 = new WrappingTextLabelComponent("Report " + userName + "'s " + reportType, 1.0);
        wrappingTextLabelComponent10.o(this.A());
        wrappingTextLabelComponent10.T$src$V$1orl066(PublicProfileReviewResponsePanel.J.A);
        wrappingTextLabelComponent10.l(true);
        this.h(wrappingTextLabelComponent10, new Object[0]);
        this.h(new SpacerComponent(0.0, 20.0), new Object[0]);
        SmallTextInputComponent smallTextInputComponent = new SmallTextInputComponent("+  Type reason...");
        smallTextInputComponent.V(0.0f);
        smallTextInputComponent.C(0.0);
        smallTextInputComponent.H(0.0f);
        double d = this.A() / 2.0 - smallTextInputComponent.A() / 2.0;
        this.h(new SpacerComponent(d, 12.0), "widthwrap");
        this.h(smallTextInputComponent, new Object[0]);
        this.h(new SpacerComponent(d, 12.0), "widthwrap");
        this.h(new InsetFilledSpacerComponent(smallTextInputComponent.A(), 0.0, 0.5, 0.0, PublicProfileReviewResponsePanel.J.y), "widthwrap");
        PanelComponent panelComponent = new PanelComponent(this.A(), 12.0);
        panelComponent.d(false);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.h(new SpacerComponent(0.0, 6.0), new Object[0]);
        this.h(panelComponent, new Object[0]);
        panelComponent.h(new SpacerComponent(100.0, 12.0), new Object[0]);
        TextLabel textLabel = new TextLabel("CANCEL", 0.7);
        textLabel.l(PublicProfileReviewResponsePanel.J.Z);
        textLabel.c(true);
        textLabel.o(30.0);
        textLabel.Y(12.0);
        textLabel.r(this::lambda$setup$0);
        panelComponent.h(textLabel, new Object[0]);
        TextButton textButton = new TextButton("REPORT", PublicProfileReviewResponsePanel.J.B, PublicProfileReviewResponsePanel.J.O);
        textButton.y(0.7);
        textButton.F(false);
        textButton.h(Color.WHITE);
        textButton.c(true);
        textButton.o(30.0);
        textButton.Y(12.0);
        textButton.e(() -> this.lambda$setup$5(publicProfileReview, smallTextInputComponent, publicProfileReviewResponse));
        panelComponent.h(textButton, new Object[0]);
    }
}
