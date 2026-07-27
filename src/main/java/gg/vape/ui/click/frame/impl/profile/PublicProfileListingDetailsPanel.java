package gg.vape.ui.click.frame.impl.profile;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.api.PagedResult;
import gg.vape.config.Profile;
import gg.vape.config.ProfileSnapshot;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileReview;
import gg.vape.config.PublicProfileReviewDisplayType;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.none.ClientSettings;
import gg.vape.sync.RemoteProfileData;
import gg.vape.ui.click.component.ActionButtonGroupComponent;
import gg.vape.ui.click.component.CenteredGlyphComponent;
import gg.vape.ui.click.component.CollapsiblePanelComponent;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.DualTextLabelRowComponent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.IconButtonComponent;
import gg.vape.ui.click.component.PagedResultListComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SkeletonPlaceholderComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TwoLineTextDisplayComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileDateFormatUtil;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileReviewComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileReviewComposerComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSnapshotPanelBase;
import gg.vape.ui.click.frame.impl.profile.PublicProfileUserAvatarComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import gg.vape.ui.click.layout.WrappingFlowLayout;
import gg.vape.utils.ClipboardUtil;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

public class PublicProfileListingDetailsPanel
extends PublicProfileSnapshotPanelBase {
    private final IconButtonComponent ro = new IconButtonComponent("like active@2x", 0.8);
    private final IconButtonComponent rZ = new IconButtonComponent("dislike active@2x", 0.8);
    @Nullable
    private final PublicProfile rn;
    static final boolean rz = !PublicProfileListingDetailsPanel.class.desiredAssertionStatus();
    private PagedResultListComponent rT;
    @Nullable
    private final ProfileSnapshot rx;

    private void lambda$footerReviewInput$14() {
        this.rn.z().B(this.rn, this::p);
    }

    @Override
    protected void e() {
        super.e();
        if (this.rx == null || this.rn == null) {
            this.Z$src$V$1ttgszt();
            return;
        }
        this.w$src$V$1u9eu7a();
    }

    private void lambda$footerReviewInput$16(boolean bl) {
        this.rn.z().B(this.rn, () -> this.lambda$null$15(bl));
    }

    private static ApiResponse lambda$null$9(Throwable throwable) {
        return null;
    }

    private void a$src$V$1txbd5c() {
        PanelComponent panelComponent;
        if (!rz && this.rn == null) {
            throw new AssertionError();
        }
        this.b$src$V$s019hq();
        double d = this.gg.A();
        this.getClass();
        double d2 = d - 5.0;
        PanelComponent panelComponent2 = new PanelComponent(d2, 12.0);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent2.d(false);
        this.gg.h(panelComponent2, new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("Details", 0.9);
        simpleTextLabelComponent.g(0.0f);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.T$src$V$1orl066(PublicProfileListingDetailsPanel.J.A);
        panelComponent2.h(simpleTextLabelComponent, new Object[0]);
        simpleTextLabelComponent.o(panelComponent2.A());
        FlowLayoutComponent flowLayoutComponent = new FlowLayoutComponent(d2);
        flowLayoutComponent.d(false);
        CollapsiblePanelComponent collapsiblePanelComponent = new CollapsiblePanelComponent(this.rn.h(), d2);
        SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent("Created: " + PublicProfileDateFormatUtil.H(this.rn.s$src$Ljava_util_Date_$tehmu9()), 0.8);
        simpleTextLabelComponent2.Y(8.0);
        simpleTextLabelComponent2.o(d2);
        simpleTextLabelComponent2.g(0.0f);
        collapsiblePanelComponent.N$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$1f7l5nx().h(new SpacerComponent(0.0, 6.0), new Object[0]);
        collapsiblePanelComponent.N$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$1f7l5nx().h(simpleTextLabelComponent2, new Object[0]);
        PanelComponent panelComponent3 = new PanelComponent(d2, 10.0);
        panelComponent3.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
        panelComponent3.d(false);
        SimpleTextLabelComponent simpleTextLabelComponent3 = new SimpleTextLabelComponent("Share code: " + this.rn.s(), 0.8);
        simpleTextLabelComponent3.Y(8.0);
        simpleTextLabelComponent3.g(0.0f);
        panelComponent3.h(simpleTextLabelComponent3, new Object[0]);
        GlyphIconComponent glyphIconComponent = new GlyphIconComponent("newcopy", 4.0, 4.0, 8.0, 8.0, PublicProfileListingDetailsPanel.J.W, PublicProfileListingDetailsPanel.J.f, PublicProfileListingDetailsPanel.J.l);
        glyphIconComponent.Z(0.75f);
        glyphIconComponent.A(2.0);
        glyphIconComponent.w(2.0);
        panelComponent3.h(new SpacerComponent(4.0, 0.0), new Object[0]);
        panelComponent3.h(glyphIconComponent, new Object[0]);
        glyphIconComponent.s(this::lambda$viewDetails$0);
        collapsiblePanelComponent.N$src$Lgg_vape_ui_click_component_FlowLayoutComponent_$1f7l5nx().h(panelComponent3, new Object[0]);
        flowLayoutComponent.h(collapsiblePanelComponent, new Object[0]);
        flowLayoutComponent.h(new SpacerComponent(0.0, 8.0), new Object[0]);
        this.gg.h(flowLayoutComponent, new Object[0]);
        if (!this.rn.X().isEmpty()) {
            panelComponent = new PanelComponent(d2, 12.0);
            panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
            panelComponent.d(false);
            this.gg.h(panelComponent, new Object[0]);
            for (String stringArray2 : this.rn.X()) {
                panelComponent.h(new PublicProfileFilterTokenComponent(stringArray2), new Object[0]);
                panelComponent.h(new SpacerComponent(2.0, 0.0), new Object[0]);
            }
        }
        this.gg.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        panelComponent = new PanelComponent(d2, 40.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.d(false);
        this.gg.h(panelComponent, new Object[0]);
        String[] stringArray3 = new String[]{"Positive reviews", "Last updated", "Downloads"};
        String[] stringArray = new String[]{String.valueOf(this.rn.J()), PublicProfileDateFormatUtil.i(this.rn.C()), String.valueOf(this.rn.K())};
        panelComponent.h(new SpacerComponent(0.0, 5.0), "wrap");
        for (int function = 0; function < stringArray3.length; ++function) {
            String string = stringArray3[function];
            String string2 = stringArray[function];
            TwoLineTextDisplayComponent twoLineTextDisplayComponent = new TwoLineTextDisplayComponent(string, string2);
            twoLineTextDisplayComponent.o(panelComponent.A() / (double)stringArray3.length - 2.0);
            twoLineTextDisplayComponent.Y(29.0);
            panelComponent.h(new PaddedComponent(1.0, twoLineTextDisplayComponent), "widthwrap");
        }
        panelComponent.h(new SpacerComponent(0.0, 5.0), "wrap");
        this.rT = new PagedResultListComponent(d2, 50.0, 2);
        this.rT.H(6);
        this.rT.N(new WrappingFlowLayout(this.rT));
        this.rT.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.rT.d(false);
        this.rT.N(false);
        this.rT.T(this.gg);
        Function<PublicProfileReview, PublicProfileReviewComponent> function = this::lambda$viewDetails$1;
        this.rT.e(() -> PublicProfileListingDetailsPanel.lambda$viewDetails$2(function));
        this.rT.N(() -> this.lambda$viewDetails$4(function));
        this.rT.t(this.rn.m());
        this.gg.h(this.rT, new Object[0]);
        this.p();
    }

    private void lambda$null$8(ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            PublicProfileManager.b("Failed to download profile.");
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to download public profile: " + apiResponse.N());
            PublicProfileManager.b("Failed to view public profile: " + apiResponse.N());
            return;
        }
        RemoteProfileData remoteProfileData = (RemoteProfileData)apiResponse.T();
        if (!rz && remoteProfileData == null) {
            throw new AssertionError();
        }
        JsonObject jsonObject = this.D(remoteProfileData);
        Profile profile = new Profile(remoteProfileData.i(), remoteProfileData.v());
        profile.e(jsonObject);
        profile.A(jsonObject);
        Vape.INSTANCE.getProfilesManager().T(profile);
        this.w$src$V$1u9eu7a();
        PublicProfileManager.M("Successfully downloaded " + remoteProfileData.i());
    }

    private PublicProfileReviewComponent lambda$viewDetails$1(PublicProfileReview publicProfileReview) {
        return new PublicProfileReviewComponent(this.rn, publicProfileReview, this.rT.A(), PublicProfileReviewDisplayType.OTHER);
    }

    private static ApiResponse lambda$null$12(Throwable throwable) {
        return null;
    }

    private CompletableFuture lambda$footerDownload$13(Profile profile) {
        return ApiServices.d().R().A(this.rn.w()).whenCompleteAsync((arg_0, arg_1) -> this.lambda$null$11(profile, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(PublicProfileListingDetailsPanel::lambda$null$12);
    }

    private void Z$src$V$1ttgszt() {
        this.U$src$V$171rm8f();
        this.gg.Z(false);
        this.gb.Z(false);
        double d = this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().A();
        this.getClass();
        PanelComponent panelComponent = new PanelComponent(d - (double)(5.0f * 2.0f), this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().L());
        panelComponent.d(false);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().h(panelComponent, new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(30.0, 10.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        double d2 = panelComponent.A();
        this.getClass();
        panelComponent.h(new SkeletonPlaceholderComponent(d2 - 5.0, 20.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(panelComponent.A() / 2.0, 10.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        double d3 = panelComponent.A();
        this.getClass();
        panelComponent.h(new SkeletonPlaceholderComponent(d3 - 5.0, 30.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(50.0, 10.0), "widthwrap");
        panelComponent.h(new SpacerComponent(panelComponent.A() - 100.0, 2.0), "widthwrap");
        panelComponent.h(new SkeletonPlaceholderComponent(45.0, 10.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        double d4 = panelComponent.A();
        this.getClass();
        panelComponent.h(new SkeletonPlaceholderComponent(d4 - 5.0, 68.0), new Object[0]);
    }

    private void lambda$footerDownload$6() {
        this.y(true);
    }

    private void w$src$V$1u9eu7a() {
        Object object;
        this.s$src$V$1l7a8uk();
        this.gb.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.gb.h(new SpacerComponent(0.0, 8.0), "wrap");
        this.gb.h(new SpacerComponent(5.0, 0.0), new Object[0]);
        if (OnlineConnectionManager.T.Q(this.rn.S())) {
            this.gb.h(new SpacerComponent(45.0, 0.0), new Object[0]);
        } else {
            this.ro.P(PublicProfileListingDetailsPanel.J.O);
            this.ro.s(this::lambda$footerDownload$6);
            this.rZ.P(PublicProfileListingDetailsPanel.J.c);
            this.rZ.s(this::lambda$footerDownload$7);
            this.o$src$V$1u50hge();
            object = new ActionButtonGroupComponent(this.ro, this.rZ);
            ((GuiComponent)object).o(45.0);
            ((GuiComponent)object).Y(15.0);
            this.gb.h((GuiComponent)object, new Object[0]);
        }
        object = Vape.INSTANCE.getProfilesManager().X(this.rn.w());
        if (object == null) {
            this.gb.h(new SpacerComponent(15.0, 0.0), new Object[0]);
            TextButton textButton = new TextButton("Download", 0.8, PublicProfileListingDetailsPanel.J.B, PublicProfileListingDetailsPanel.J.O);
            textButton.F(false);
            textButton.h(Color.WHITE);
            textButton.o(144.0);
            textButton.Y(15.0);
            textButton.e(this::lambda$footerDownload$10);
            this.gb.h(textButton, new Object[0]);
        } else {
            Profile downloadedProfile = (Profile)object;
            if (!rz && ((Profile)object).j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0() == null) {
                throw new AssertionError();
            }
            if (this.rn.H() == ((Profile)object).j$src$Lgg_vape_config_ProfileRemoteMetadata_$1dp9fd0().V()) {
                this.gb.h(new SpacerComponent(68.0, 0.0), new Object[0]);
                GlyphIconComponent glyphIconComponent = new GlyphIconComponent("info", 8.0, 8.0, 8.0, 8.0, PublicProfileListingDetailsPanel.J.W, PublicProfileListingDetailsPanel.J.W, null);
                this.gb.h(new PaddedComponent(4.0, 0.0, 0.0, 0.0, glyphIconComponent), new Object[0]);
                SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("Downloaded and up to date", 0.8, PublicProfileListingDetailsPanel.J.Z, true);
                simpleTextLabelComponent.g(3.0f);
                simpleTextLabelComponent.o(70.0);
                simpleTextLabelComponent.Y(17.0);
                this.gb.h(simpleTextLabelComponent, new Object[0]);
            } else {
                this.gb.h(new SpacerComponent(12.0, 0.0), new Object[0]);
                CenteredGlyphComponent centeredGlyphComponent = new CenteredGlyphComponent("info", 8.0f, 8.0f);
                centeredGlyphComponent.o(10.0);
                centeredGlyphComponent.Y(12.0);
                this.gb.h(centeredGlyphComponent, new Object[0]);
                SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("Previously downloaded", 0.8, PublicProfileListingDetailsPanel.J.Z, true);
                simpleTextLabelComponent.o(75.0);
                simpleTextLabelComponent.Y(17.0);
                this.gb.h(simpleTextLabelComponent, new Object[0]);
                TextButton textButton = new TextButton("Update", 0.8, PublicProfileListingDetailsPanel.J.B, PublicProfileListingDetailsPanel.J.O);
                textButton.o(60.0);
                textButton.Y(15.0);
                textButton.F(false);
                textButton.h(Color.WHITE);
                textButton.e(() -> this.lambda$footerDownload$13(downloadedProfile));
                this.gb.h(textButton, new Object[0]);
            }
        }
    }

    private void o$src$V$1u50hge() {
        if (this.rn.z() != null) {
            if (this.rn.z().X()) {
                this.ro.o(PublicProfileListingDetailsPanel.J.B);
                this.rZ.o(PublicProfileListingDetailsPanel.J.W);
            } else {
                this.rZ.o(PublicProfileListingDetailsPanel.J.d);
                this.ro.o(PublicProfileListingDetailsPanel.J.W);
            }
        } else {
            this.ro.o(PublicProfileListingDetailsPanel.J.W);
            this.rZ.o(PublicProfileListingDetailsPanel.J.W);
        }
    }

    private CompletableFuture lambda$viewDetails$4(Function function) {
        return ApiServices.d().R().F(this.rn, this.rT.A$src$J$1vju51i()).thenApplyAsync(arg_0 -> this.lambda$null$3(function, arg_0), (Executor)ClientSettings.f6);
    }

    private void lambda$null$11(Profile profile, ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to down profile update: " + apiResponse.N());
            PublicProfileManager.b("Failed to download profile update: " + apiResponse.N());
            return;
        }
        RemoteProfileData remoteProfileData = (RemoteProfileData)apiResponse.T();
        if (!rz && remoteProfileData == null) {
            throw new AssertionError();
        }
        JsonObject jsonObject = this.D(remoteProfileData);
        profile.e(jsonObject);
        this.w$src$V$1u9eu7a();
        PublicProfileManager.M("Successfully updated " + remoteProfileData.i());
    }

    private void lambda$viewDetails$0() {
        ClipboardUtil.setText(this.rn.s());
        PublicProfileManager.M("Copied share code to clipboard");
    }

    private void f(PublicProfileReviewComponent publicProfileReviewComponent) {
        this.s$src$V$1l7a8uk();
        this.gb.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.gb.h(new SpacerComponent(0.0, 8.0), "wrap");
        this.gb.h(new SpacerComponent(5.0, 0.0), new Object[0]);
        PublicProfileReviewComposerComponent publicProfileReviewComposerComponent = new PublicProfileReviewComposerComponent(this.rn, publicProfileReviewComponent.y$src$Lgg_vape_config_PublicProfileReview_$4iqplh().X(), true, this::w$src$V$1u9eu7a, this::p);
        publicProfileReviewComposerComponent.k().k(publicProfileReviewComponent.y$src$Lgg_vape_config_PublicProfileReview_$4iqplh().I());
        publicProfileReviewComposerComponent.o(this.gb.A() - 5.0);
        publicProfileReviewComposerComponent.Y(this.gb.L() - 8.0);
        this.gb.h(publicProfileReviewComposerComponent, new Object[0]);
    }

    private List lambda$null$3(Function function, ApiResponse apiResponse) {
        if (!apiResponse.t()) {
            return null;
        }
        if (!rz && apiResponse.T() == null) {
            throw new AssertionError();
        }
        PagedResult<PublicProfileReview> reviews = (PagedResult<PublicProfileReview>)apiResponse.T();
        this.rT.t(reviews);
        ArrayList arrayList = new ArrayList();
        for (PublicProfileReview publicProfileReview : reviews.E()) {
            arrayList.add(function.apply(publicProfileReview));
        }
        return arrayList;
    }

    private void lambda$footerDownload$7() {
        this.y(false);
    }

    private static GuiComponent lambda$viewDetails$2(Function function) {
        return (PublicProfileReviewComponent)function.apply(null);
    }

    private void lambda$updateReviewsContainer$5(PublicProfileReviewComponent publicProfileReviewComponent) {
        this.f(publicProfileReviewComponent);
    }

    private CompletableFuture lambda$footerDownload$10() {
        return ApiServices.d().R().q(this.rn.w()).whenCompleteAsync(this::lambda$null$8, (Executor)ClientSettings.f6).exceptionally(PublicProfileListingDetailsPanel::lambda$null$9);
    }

    private void lambda$null$15(boolean bl) {
        this.p();
        this.y(bl);
    }


    public PublicProfileListingDetailsPanel(PublicProfilesFrame publicProfilesFrame, @Nullable PublicProfile publicProfile, @Nullable ProfileSnapshot profileSnapshot) {
        super(publicProfilesFrame, publicProfile, profileSnapshot);
        this.rn = publicProfile;
        this.rx = profileSnapshot;
        this.z(this::a$src$V$1txbd5c);
        this.e();
    }

    @Override
    protected void z(PanelComponent panelComponent) {
        if (this.rn == null) {
            return;
        }
        panelComponent.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        PanelComponent panelComponent2 = new PanelComponent(this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().A(), 10.0);
        panelComponent2.d(false);
        panelComponent2.h(new SpacerComponent(5.0, 0.0), new Object[0]);
        PublicProfileUserAvatarComponent publicProfileUserAvatarComponent = new PublicProfileUserAvatarComponent(this.rn.S(), 10.0, 10.0);
        panelComponent2.h(publicProfileUserAvatarComponent, new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("By " + (this.rn.S() != null ? this.rn.S().o() : "Anonymous"));
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.Y(10.0);
        panelComponent2.h(simpleTextLabelComponent, new Object[0]);
        panelComponent.h(panelComponent2, new Object[0]);
    }

    private void y(boolean bl) {
        if (OnlineConnectionManager.T.Q(this.rn.S())) {
            return;
        }
        if (this.rn.z() != null) {
            if (this.rn.z().X() == bl) {
                ConfirmationDialogComponent.U(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), "Are you sure you want to delete your review?", "Delete", "newtrash", this::lambda$footerReviewInput$14);
            } else {
                ConfirmationDialogComponent.U(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), "Are you sure you want to change your review?", "Confirm", "reset_circle", () -> this.lambda$footerReviewInput$16(bl));
            }
            return;
        }
        this.s$src$V$1l7a8uk();
        this.gb.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.gb.h(new SpacerComponent(0.0, 8.0), "wrap");
        this.gb.h(new SpacerComponent(5.0, 0.0), new Object[0]);
        PublicProfileReviewComposerComponent publicProfileReviewComposerComponent = new PublicProfileReviewComposerComponent(this.rn, bl, this.rn.z() != null, this::w$src$V$1u9eu7a, this::p);
        publicProfileReviewComposerComponent.o(this.gb.A() - 5.0);
        publicProfileReviewComposerComponent.Y(this.gb.L() - 8.0);
        this.gb.h(publicProfileReviewComposerComponent, new Object[0]);
    }

    private JsonObject D(RemoteProfileData remoteProfileData) {
        JsonObject jsonObject = remoteProfileData.G();
        JsonObject jsonObject2 = jsonObject.getAsJsonObject("data");
        if (jsonObject2 == null) {
            return jsonObject;
        }
        JsonObject jsonObject3 = jsonObject2.getAsJsonObject("enabled");
        if (jsonObject3 == null) {
            return jsonObject;
        }
        for (Mod mod : Vape.INSTANCE.getModManager().collectMods()) {
            if (mod.getCategory() != Category.w) continue;
            jsonObject3.remove(mod.getName());
        }
        return jsonObject;
    }

    private void p() {
        this.rT.S();
        PanelComponent panelComponent = new PanelComponent(this.rT.A(), 8.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.d(false);
        this.rT.h(panelComponent, new Object[0]);
        panelComponent.h(new DualTextLabelRowComponent("Reviews", String.valueOf(this.rn.e()), 8.0, 0.8), new Object[0]);
        PanelComponent panelComponent2 = new PanelComponent(65.0, panelComponent.L());
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent2.d(false);
        panelComponent.h(panelComponent2, "alignright");
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(this.rn.R() + "% positive reviews", 0.8, PublicProfileListingDetailsPanel.J.B);
        panelComponent2.h(simpleTextLabelComponent, new Object[0]);
        this.rT.h(new SpacerComponent(0.0, 5.0), "wrap");
        PublicProfileReview publicProfileReview = this.rn.z();
        if (publicProfileReview != null) {
            PublicProfileReviewComponent publicProfileReviewComponent = new PublicProfileReviewComponent(this.rn, publicProfileReview, this.rT.A(), PublicProfileReviewDisplayType.SELF).q(this::p);
            publicProfileReviewComponent.D(() -> this.lambda$updateReviewsContainer$5(publicProfileReviewComponent));
            this.rT.h(publicProfileReviewComponent, new Object[0]);
            this.rT.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        }
        for (PublicProfileReview publicProfileReview2 : this.rn.m().E()) {
            this.rT.h(new PublicProfileReviewComponent(this.rn, publicProfileReview2, this.rT.A(), PublicProfileReviewDisplayType.OTHER), new Object[0]);
            this.rT.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        }
        this.rT.u(this.rT.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y());
        this.rT.t(this.rT.H$src$D$1wlsgtk());
        this.o$src$V$1u50hge();
    }
}
