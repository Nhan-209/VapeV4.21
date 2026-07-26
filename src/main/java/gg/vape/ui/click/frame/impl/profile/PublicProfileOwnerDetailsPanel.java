package gg.vape.ui.click.frame.impl.profile;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.api.PagedResult;
import gg.vape.api.PublicProfilePartialJsonPayloadBuilder;
import gg.vape.config.LegacyPublicProfile;
import gg.vape.config.Profile;
import gg.vape.config.ProfileSnapshot;
import gg.vape.config.ProfilesSyncPayloadBuilder;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileReview;
import gg.vape.config.PublicProfileReviewDisplayType;
import gg.vape.config.PublicProfileShareInfo;
import gg.vape.event.EventBus;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.PublicProfileReviewEvent;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.sync.RemoteProfileDataMap;
import gg.vape.ui.click.component.ConfirmationDialogComponent;
import gg.vape.ui.click.component.DualTextLabelRowComponent;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.InsetFilledSpacerComponent;
import gg.vape.ui.click.component.PagedResultListComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.PopupMenuButtonComponent;
import gg.vape.ui.click.component.ProfileSelectionPopupComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SkeletonPlaceholderComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.TwoLineTextDisplayComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.component.publicprofiles.PublicProfileIdBadgeComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.impl.profile.CompactPublicProfileFilterTokenSelectorComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileDateFormatUtil;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerBooleanToggleClickHandler;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerDetailsUnderlineIconComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerFixedWidthNoSubmitInputComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerShareCodeCopyClickHandler;
import gg.vape.ui.click.frame.impl.profile.PublicProfileReviewComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSnapshotPanelBase;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class PublicProfileOwnerDetailsPanel
extends PublicProfileSnapshotPanelBase
implements EventListener {
    private final String[] ro;
    @Nullable
    protected PublicProfileShareInfo rS;
    static final boolean rC = !PublicProfileOwnerDetailsPanel.class.desiredAssertionStatus();
    private final Set<Long> rU = new HashSet<Long>();
    private PanelComponent ru;
    private CompactPublicProfileFilterTokenSelectorComponent rh;
    private String rq;

    public PublicProfileOwnerDetailsPanel(PublicProfilesFrame publicProfilesFrame, @Nullable PublicProfile publicProfile, @Nullable ProfileSnapshot profileSnapshot) {
        super(publicProfilesFrame, publicProfile, profileSnapshot, true);
        this.ro = new String[]{"Settings", "Reviews", "Stats"};
        this.rq = this.ro[0];
        this.Hx = publicProfile;
        this.rS = publicProfile != null ? publicProfile.c() : null;
        this.Hd = profileSnapshot;
        this.gZ = false;
        this.z(this::Z$src$V$vbit14);
        this.e();
        EventBus.getInstance().registerListener(this, new Predicate[0]);
    }

    private void lambda$viewDetails$0(String string, PanelComponent panelComponent) {
        if (string.equalsIgnoreCase("stats")) {
            this.a$src$V$vfdd6n();
        } else if (string.equalsIgnoreCase("reviews")) {
            this.Q$src$V$v6knov();
        } else if (string.equalsIgnoreCase("settings")) {
            this.N$src$V$v4x9ws();
        }
        this.rq = string;
        for (GuiComponent guiComponent : panelComponent.f()) {
            TextLabel textLabel;
            if (!(guiComponent instanceof TextLabel)) continue;
            textLabel = (TextLabel)guiComponent;
            textLabel.l(textLabel.L$src$Ljava_lang_String_$1ncdwqb().equals(this.rq) ? PublicProfileOwnerDetailsPanel.J.A : PublicProfileOwnerDetailsPanel.J.C);
            textLabel.c(false);
        }
    }

    private void lambda$null$11(ApiResponse<PublicProfileShareInfo> apiResponse, Throwable throwable) {
        if (throwable != null) {
            PublicProfileManager.b("Failed to regenerate share code.");
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            PublicProfileManager.b("Failed to regenerate share code: " + apiResponse.N());
            return;
        }
        if (!rC && apiResponse.T() == null) {
            throw new AssertionError();
        }
        this.Hx.M(((PublicProfileShareInfo)apiResponse.T()).a());
        this.rS.d(((PublicProfileShareInfo)apiResponse.T()).a());
        this.e();
        PublicProfileManager.M("Successfully updated share code!");
    }

    private void lambda$update$25(ApiResponse<PublicProfile> apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            PublicProfileManager.b("Failed to update profile: " + apiResponse.N());
            return;
        }
        if (!rC && apiResponse.T() == null) {
            throw new AssertionError();
        }
        this.rS = ((PublicProfile)apiResponse.T()).c();
        PublicProfileManager.M("Successfully updated profile " + this.Hx.v() + "!");
        Vape.INSTANCE.getPublicProfileManager().m(this.Hx, (PublicProfile)apiResponse.T());
        this.e();
    }

    private static ApiResponse<Boolean> lambda$null$3(Throwable throwable) {
        return null;
    }

    private void L$src$V$v3toq2() {
        this.ru.S();
        this.h();
    }

    private static ApiResponse<Boolean> lambda$markViewedReviewsAsRead$24(Throwable throwable) {
        return null;
    }

    private PublicProfileReviewComponent lambda$viewDetailsReviews$5(PublicProfileReview publicProfileReview) {
        double d = this.ru.A();
        this.getClass();
        return new PublicProfileReviewComponent(this.Hx, publicProfileReview, d - 5.0, PublicProfileReviewDisplayType.REPLY);
    }

    private CompletableFuture lambda$viewDetailsSettings$20() {
        return this.F(this.Hx.c().v(), null, null, null, null, null, true);
    }

    private CompletableFuture lambda$viewDetailsSettings$18() {
        return ConfirmationDialogComponent.U(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), "Are you sure you want to delete this public profile?", "Delete", "newtrash", this::lambda$null$17);
    }

    private CompletableFuture lambda$viewDetailsSettings$14() {
        return ConfirmationDialogComponent.U(this.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa(), "Are you sure you want to regenerate this share code?", "Regenerate", "newsync", this::lambda$null$13);
    }

    private void lambda$setupProfileNameSubheader$1(Profile profile) {
        this.rS.m(profile.P$src$Ljava_util_UUID_$kdhg08());
        this.Hd = ProfileSnapshot.t(this.Hx, profile);
        this.e();
    }

    private void lambda$null$7(PublicProfileReview publicProfileReview) {
        if (publicProfileReview.L()) {
            return;
        }
        this.rU.add(publicProfileReview.M());
    }

    private void lambda$null$17() {
        ApiServices.d().R().i(this.Hx.w()).whenCompleteAsync(this::lambda$null$15, ClientSettings.f6).exceptionally(PublicProfileOwnerDetailsPanel::lambda$null$16);
    }

    private CompletableFuture<RemoteProfileDataMap> F(@Nullable UUID uUID, @Nullable String string, @Nullable List<String> list, @Nullable Boolean bl, @Nullable Boolean bl2, @Nullable Boolean bl3, boolean bl4) {
        if (!rC && this.Hx == null) {
            throw new AssertionError();
        }
        if (bl4) {
            this.Hd.D();
        }
        if (list != null && list.size() < 5) {
            String normalizedTag = LegacyPublicProfile.S(this.rh.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().i$src$Ljava_lang_String_$1n2xf3k().trim());
            if (normalizedTag != null) {
                String string3 = LegacyPublicProfile.e(normalizedTag);
                if (string3 != null) {
                    PublicProfileManager.b(string3);
                    return null;
                }
                list.add(normalizedTag);
                this.rh.V(new PublicProfileFilterTokenComponent(normalizedTag));
                this.rh.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().k("");
            }
        }
        JsonObject profileData = null;
        if (bl4 && this.Hd.d() != null && (profileData = this.Hd.d().J$src$Lcom_google_gson_JsonObject_$16ar19y()) == null) {
            Vape.INSTANCE.getProfilesManager().M(this.Hd.d());
        }
        return ApiServices.d().R().O(PublicProfilePartialJsonPayloadBuilder.c(this.Hx.w(), uUID, this.Hx.v(), string, list, bl != null ? Boolean.valueOf(bl == false) : null, bl2, bl3, profileData))
                .whenCompleteAsync(this::lambda$update$25, ClientSettings.f6)
                .thenComposeAsync(this::lambda$update$26)
                .thenApplyAsync(PublicProfileOwnerDetailsPanel::lambda$update$27, ClientSettings.f6);
    }

    @EventHandler
    public void e(PublicProfileReviewEvent publicProfileReviewEvent) {
        this.Q$src$V$v6knov();
    }

    private CompletionStage<ApiResponse<RemoteProfileDataMap>> lambda$update$26(ApiResponse<PublicProfile> apiResponse) {
        if (apiResponse == null || !apiResponse.t()) {
            return CompletableFuture.completedFuture(null);
        }
        Profile profile = this.Hd.d();
        if (profile == null) {
            return CompletableFuture.completedFuture(null);
        }
        return ApiServices.d().c().F(ProfilesSyncPayloadBuilder.T(Collections.singletonList(profile), null));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$markViewedReviewsAsRead$23(List<Long> list, ApiResponse<Boolean> apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            PublicProfileManager.b("Failed to mark reviews as read: " + apiResponse.N());
            return;
        }
        for (PublicProfileReview publicProfileReview : this.Hx.m().E()) {
            if (!list.contains(publicProfileReview.M())) continue;
            publicProfileReview.o(true);
            this.j(this.rS.o() - 1L);
        }
    }

    private List<GuiComponent> lambda$null$9(PagedResultListComponent pagedResultListComponent, Function<PublicProfileReview, PublicProfileReviewComponent> function, ApiResponse<PagedResult<PublicProfileReview>> apiResponse) {
        if (!apiResponse.t()) {
            return null;
        }
        if (!rC && apiResponse.T() == null) {
            throw new AssertionError();
        }
        pagedResultListComponent.t(apiResponse.T());
        ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>();
        for (PublicProfileReview publicProfileReview : apiResponse.T().E()) {
            arrayList.add(function.apply(publicProfileReview).u(() -> this.lambda$null$7(publicProfileReview)).g(PublicProfileOwnerDetailsPanel::lambda$null$8));
        }
        return arrayList;
    }

    private void j(long l) {
        if (!rC && this.rS == null) {
            throw new AssertionError();
        }
        this.rS.O(l);
        PublicProfile publicProfile = Vape.INSTANCE.getPublicProfileManager().A().get(this.Hx.w());
        if (publicProfile != null) {
            if (!rC && publicProfile.c() == null) {
                throw new AssertionError();
            }
            publicProfile.c().O(l);
        }
    }

    private void N$src$V$v4x9ws() {
        this.L$src$V$v3toq2();
        double d = this.gg.A();
        PanelComponent panelComponent = new PanelComponent(d, 14.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.d(false);
        PanelComponent panelComponent2 = new PanelComponent(d, this.ru.L() - panelComponent.L() - 8.0);
        panelComponent2.t(panelComponent2.L() - 2.0);
        panelComponent2.d(false);
        panelComponent2.T(Color.RED);
        double d2 = d - 3.0;
        FlowLayoutComponent flowLayoutComponent = new FlowLayoutComponent(d);
        flowLayoutComponent.d(false);
        flowLayoutComponent.T(Color.YELLOW);
        flowLayoutComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent2.h(flowLayoutComponent, new Object[0]);
        this.ru.h(panelComponent2, new Object[0]);
        this.ru.h(new FilledSpacerComponent(d2, 0.5, PublicProfileOwnerDetailsPanel.J.l), new Object[0]);
        this.ru.h(new SpacerComponent(0.0, 8.0), new Object[0]);
        this.ru.h(panelComponent, new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("DESCRIPTION", 0.7, PublicProfileOwnerDetailsPanel.J.C, true);
        simpleTextLabelComponent.g(0.0f);
        flowLayoutComponent.h(simpleTextLabelComponent, new Object[0]);
        PublicProfileOwnerFixedWidthNoSubmitInputComponent publicProfileOwnerFixedWidthNoSubmitInputComponent = new PublicProfileOwnerFixedWidthNoSubmitInputComponent(this, "+   Add Description (optional)", d2);
        publicProfileOwnerFixedWidthNoSubmitInputComponent.C(0.0);
        publicProfileOwnerFixedWidthNoSubmitInputComponent.H(0.0f);
        publicProfileOwnerFixedWidthNoSubmitInputComponent.V(1.0f);
        publicProfileOwnerFixedWidthNoSubmitInputComponent.k(this.Hx.h());
        publicProfileOwnerFixedWidthNoSubmitInputComponent.I(PublicProfileOwnerDetailsPanel.J.A);
        publicProfileOwnerFixedWidthNoSubmitInputComponent.A(PublicProfileOwnerDetailsPanel.J.Z);
        publicProfileOwnerFixedWidthNoSubmitInputComponent.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
        publicProfileOwnerFixedWidthNoSubmitInputComponent.d(false);
        publicProfileOwnerFixedWidthNoSubmitInputComponent.e(false);
        flowLayoutComponent.h(publicProfileOwnerFixedWidthNoSubmitInputComponent, new Object[0]);
        flowLayoutComponent.h(new InsetFilledSpacerComponent(d2, 2.0, 0.5, 0.0, PublicProfileOwnerDetailsPanel.J.l), new Object[0]);
        flowLayoutComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent("TAGS", 0.7, PublicProfileOwnerDetailsPanel.J.C, true);
        simpleTextLabelComponent2.g(0.0f);
        flowLayoutComponent.h(simpleTextLabelComponent2, "widthwrap");
        SimpleTextLabelComponent simpleTextLabelComponent3 = new SimpleTextLabelComponent("Comma-Seperated", 0.7, PublicProfileOwnerDetailsPanel.J.h, false);
        double d3 = simpleTextLabelComponent3.h();
        this.getClass();
        simpleTextLabelComponent3.o(d3 + (double)(5.0f * 2.0f));
        flowLayoutComponent.h(simpleTextLabelComponent3, "alignright, wrap");
        this.rh = new CompactPublicProfileFilterTokenSelectorComponent("+   Add Tags (optional)", this.ru.A(), 20.0);
        for (String object2 : this.Hx.X()) {
            this.rh.V(new PublicProfileFilterTokenComponent(object2));
        }
        flowLayoutComponent.h(this.rh, "wrap");
        flowLayoutComponent.h(new InsetFilledSpacerComponent(d2, 2.0, 0.5, 0.0, PublicProfileOwnerDetailsPanel.J.l), new Object[0]);
        flowLayoutComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent4 = new SimpleTextLabelComponent("PREFERENCES", 0.7, PublicProfileOwnerDetailsPanel.J.C, true);
        simpleTextLabelComponent4.g(0.0f);
        flowLayoutComponent.h(simpleTextLabelComponent4, new Object[0]);
        BooleanToggleComponent booleanToggleComponent = new BooleanToggleComponent("Discoverable with a share code only", 0.8);
        booleanToggleComponent.C(0.0);
        booleanToggleComponent.q(d2);
        booleanToggleComponent.d(false);
        booleanToggleComponent.h(!this.rS.q());
        flowLayoutComponent.h(booleanToggleComponent, new Object[0]);
        BooleanToggleComponent booleanToggleComponent2 = new BooleanToggleComponent("Friends only discovery", 0.8);
        booleanToggleComponent2.C(0.0);
        booleanToggleComponent2.q(d2);
        booleanToggleComponent2.d(false);
        booleanToggleComponent2.h(this.rS.b());
        booleanToggleComponent2.Z(!this.rS.q());
        flowLayoutComponent.h(booleanToggleComponent2, new Object[0]);
        booleanToggleComponent.j(new PublicProfileOwnerBooleanToggleClickHandler(this, booleanToggleComponent2));
        PanelComponent panelComponent3 = new PanelComponent(d2, 16.0);
        panelComponent3.d(true);
        panelComponent3.T(PublicProfileOwnerDetailsPanel.J.R);
        WrappingTextLabelComponent wrappingTextLabelComponent = new WrappingTextLabelComponent("Share Code: " + this.rS.a(), 0.8, PublicProfileOwnerDetailsPanel.J.B);
        wrappingTextLabelComponent.o(panelComponent3.A());
        wrappingTextLabelComponent.Y(panelComponent3.L());
        wrappingTextLabelComponent.w("Click to copy to clipboard");
        wrappingTextLabelComponent.l(true);
        wrappingTextLabelComponent.j(new PublicProfileOwnerShareCodeCopyClickHandler(this));
        panelComponent3.h(wrappingTextLabelComponent, new Object[0]);
        PublicProfileOwnerDetailsUnderlineIconComponent publicProfileOwnerDetailsUnderlineIconComponent = new PublicProfileOwnerDetailsUnderlineIconComponent(this, "newsync", 6.0, 6.0, 8.0, 8.0, null, null, null);
        publicProfileOwnerDetailsUnderlineIconComponent.o(false);
        publicProfileOwnerDetailsUnderlineIconComponent.w("Click to regenerate share code");
        publicProfileOwnerDetailsUnderlineIconComponent.e(this::lambda$viewDetailsSettings$14);
        panelComponent3.h(publicProfileOwnerDetailsUnderlineIconComponent, "OffsetX 192, OffsetY 5");
        flowLayoutComponent.h(panelComponent3, new Object[0]);
        BooleanToggleComponent booleanToggleComponent3 = new BooleanToggleComponent("Upload anonymously", 0.8);
        booleanToggleComponent3.C(0.0);
        booleanToggleComponent3.q(d2);
        booleanToggleComponent3.d(false);
        booleanToggleComponent3.h(this.rS.f());
        flowLayoutComponent.h(booleanToggleComponent3, new Object[0]);
        TextLabel textLabel = new TextLabel("Remove", 0.7, true);
        textLabel.l(PublicProfileOwnerDetailsPanel.J.d);
        textLabel.c(true);
        textLabel.o(30.0);
        textLabel.Y(panelComponent.L());
        textLabel.e(this::lambda$viewDetailsSettings$18);
        panelComponent.h(textLabel, new Object[0]);
        PanelComponent panelComponent4 = new PanelComponent(75.0, panelComponent.L());
        panelComponent4.d(false);
        panelComponent4.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.h(panelComponent4, "alignright");
        TextLabel textLabel2 = new TextLabel("Cancel", 0.7, true);
        textLabel2.l(PublicProfileOwnerDetailsPanel.J.Z);
        textLabel2.c(true);
        textLabel2.o(30.0);
        textLabel2.Y(panelComponent.L());
        textLabel2.r(this::lambda$viewDetailsSettings$19);
        panelComponent4.h(textLabel2, new Object[0]);
        List<GuiComponent> list = Arrays.asList(new TextLabel("Modules only", 0.75, false).B$src$Lgg_vape_ui_click_component_gui_TextLabel_$1bc29rb(true).l(Color.WHITE).c(true).e(this::lambda$viewDetailsSettings$20), new TextLabel("Details only", 0.75, false).B$src$Lgg_vape_ui_click_component_gui_TextLabel_$1bc29rb(true).l(Color.WHITE).c(true).e(() -> this.lambda$viewDetailsSettings$21(publicProfileOwnerFixedWidthNoSubmitInputComponent, booleanToggleComponent, booleanToggleComponent3, booleanToggleComponent2)));
        PopupMenuButtonComponent popupMenuButtonComponent = new PopupMenuButtonComponent("UPDATE", list, this.Hd.d() != null ? PublicProfileOwnerDetailsPanel.J.B : PublicProfileOwnerDetailsPanel.J.l, this.Hd.d() != null ? PublicProfileOwnerDetailsPanel.J.O : PublicProfileOwnerDetailsPanel.J.l, null, 1.0f, 1.0f);
        popupMenuButtonComponent.p(this.Hd.d() == null);
        popupMenuButtonComponent.l(true);
        popupMenuButtonComponent.o(60.0);
        popupMenuButtonComponent.Y(panelComponent.L());
        popupMenuButtonComponent.e(() -> this.lambda$viewDetailsSettings$22(publicProfileOwnerFixedWidthNoSubmitInputComponent, booleanToggleComponent, booleanToggleComponent3, booleanToggleComponent2));
        panelComponent4.h(popupMenuButtonComponent, new Object[0]);
        double d4 = textLabel2.A() + popupMenuButtonComponent.A();
        this.getClass();
        panelComponent4.q(d4 + 5.0);
    }

    private void a$src$V$vfdd6n() {
        this.L$src$V$v3toq2();
        String[] stringArray = new String[]{"Positive reviews", "Negative reviews", "Downloads", "Created", "Updated", "Reviews"};
        String[] stringArray2 = new String[]{String.valueOf(this.Hx.J()), String.valueOf(this.Hx.W()), String.valueOf(this.Hx.K()), PublicProfileDateFormatUtil.i(this.Hx.s$src$Ljava_util_Date_$tehmu9()), PublicProfileDateFormatUtil.i(this.Hx.C()), String.valueOf(this.Hx.m().L())};
        this.ru.h(new SpacerComponent(0.0, 5.0), "wrap");
        for (int i = 0; i < stringArray.length; ++i) {
            String string = stringArray[i];
            String string2 = stringArray2[i];
            if (i % 4 == 3) {
                this.ru.h(new SpacerComponent(0.0, 1.0), "wrap");
            }
            TwoLineTextDisplayComponent twoLineTextDisplayComponent = new TwoLineTextDisplayComponent(string, string2);
            twoLineTextDisplayComponent.E(1.0);
            twoLineTextDisplayComponent.H(string2.length() >= 5 ? 0.95 : 1.1);
            twoLineTextDisplayComponent.o(this.ru.A() / 3.0 - 4.0);
            twoLineTextDisplayComponent.Y(twoLineTextDisplayComponent.A());
            this.ru.h(new PaddedComponent(2.0, twoLineTextDisplayComponent), "widthwrap");
        }
    }

    @Override
    public void d$src$V$15t6q4y() {
        super.d$src$V$15t6q4y();
        this.h();
    }

    private void lambda$null$15(ApiResponse<Boolean> apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            PublicProfileManager.b("Failed to delete profile: " + apiResponse.N());
            return;
        }
        PublicProfileManager.M("Successfully deleted profile " + this.Hx.v() + "!");
        Vape.INSTANCE.getPublicProfileManager().Q(this.Hx);
        this.gc.O(null);
    }

    private static void lambda$null$8() {
    }

    private CompletableFuture lambda$viewDetailsSettings$21(TextInputComponentBase textInputComponentBase, BooleanToggleComponent booleanToggleComponent, BooleanToggleComponent booleanToggleComponent2, BooleanToggleComponent booleanToggleComponent3) {
        return this.F(null, textInputComponentBase.i$src$Ljava_lang_String_$1n2xf3k(), this.rh.m$src$Ljava_util_List_$17c1eke(), booleanToggleComponent.i$src$Z$1d37ezg(), booleanToggleComponent2.i$src$Z$1d37ezg(), booleanToggleComponent3.i$src$Z$1d37ezg(), false);
    }

    private void Q$src$V$v6knov() {
        this.L$src$V$v3toq2();
        PanelComponent panelComponent = new PanelComponent(this.ru.A(), 15.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.t(panelComponent.L());
        panelComponent.d(false);
        this.ru.h(panelComponent, new Object[0]);
        panelComponent.h(new DualTextLabelRowComponent("Reviews", String.valueOf(this.Hx.e()), 12.0, 0.9), new Object[0]);
        TextLabel textLabel = new TextLabel("mark all as read", 0.8, false, 50.0, 10.0);
        textLabel.l(null);
        textLabel.e(this::lambda$viewDetailsReviews$4);
        panelComponent.h(textLabel, "alignright");
        double d = this.ru.A();
        double d2 = this.ru.L() - this.ru.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().y();
        this.getClass();
        PagedResultListComponent pagedResultListComponent = new PagedResultListComponent(d, d2 - 5.0);
        pagedResultListComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        pagedResultListComponent.t(pagedResultListComponent.L());
        pagedResultListComponent.d(false);
        Function<PublicProfileReview, PublicProfileReviewComponent> function = this::lambda$viewDetailsReviews$5;
        pagedResultListComponent.e(() -> PublicProfileOwnerDetailsPanel.lambda$viewDetailsReviews$6(function));
        pagedResultListComponent.N(() -> this.lambda$viewDetailsReviews$10(pagedResultListComponent, function));
        pagedResultListComponent.W();
        this.ru.h(pagedResultListComponent, new Object[0]);
    }

    private static ApiResponse<Boolean> lambda$null$16(Throwable throwable) {
        return null;
    }

    private static ApiResponse<PublicProfileShareInfo> lambda$null$12(Throwable throwable) {
        return null;
    }

    private void lambda$null$2(ApiResponse<Boolean> apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            PublicProfileManager.b("Failed to mark all as read: " + apiResponse.N());
            return;
        }
        PublicProfileManager.M("Successfully marked all reviews as read!");
        for (PublicProfileReview publicProfileReview : this.Hx.m().E()) {
            if (publicProfileReview.A() == null) continue;
            publicProfileReview.o(true);
        }
        this.j(0L);
        this.Q$src$V$v6knov();
    }

    private void lambda$null$13() {
        ApiServices.d().R().H(this.Hx.w()).whenCompleteAsync(this::lambda$null$11, ClientSettings.f6).exceptionally(PublicProfileOwnerDetailsPanel::lambda$null$12);
    }

    private CompletableFuture lambda$viewDetailsReviews$4() {
        return ApiServices.d().R().b(this.Hx).whenCompleteAsync(this::lambda$null$2, ClientSettings.f6).exceptionally(PublicProfileOwnerDetailsPanel::lambda$null$3);
    }

    private void lambda$viewDetailsSettings$19() {
        this.gc.O(null);
    }

    private CompletableFuture<List<GuiComponent>> lambda$viewDetailsReviews$10(PagedResultListComponent pagedResultListComponent, Function<PublicProfileReview, PublicProfileReviewComponent> function) {
        return ApiServices.d().R().U(this.Hx.w(), pagedResultListComponent.A$src$J$1vju51i()).thenApplyAsync(arg_0 -> this.lambda$null$9(pagedResultListComponent, function, arg_0), (Executor)ClientSettings.f6);
    }

    private static RemoteProfileDataMap lambda$update$27(ApiResponse<RemoteProfileDataMap> apiResponse) {
        if (!apiResponse.t()) {
            return null;
        }
        return apiResponse.T();
    }

    @Override
    protected void e() {
        super.e();
        if (this.Hx == null || this.Hd == null) {
            this.p();
        }
    }

    @Override
    protected void z(PanelComponent panelComponent) {
        if (this.Hx == null || this.rS == null) {
            return;
        }
        Profile profile = this.rS.v() != null ? Vape.INSTANCE.getProfilesManager().H(this.rS.v()) : null;
        List<Profile> list = Vape.INSTANCE.getPublicProfileManager().T();
        ArrayList<Profile> arrayList = new ArrayList<Profile>(Vape.INSTANCE.getProfilesManager().b());
        arrayList.removeIf(list::contains);
        ProfileSelectionPopupComponent profileSelectionPopupComponent = new ProfileSelectionPopupComponent("Derived From", profile, arrayList.toArray(new Profile[0]));
        profileSelectionPopupComponent.Y(6.0);
        profileSelectionPopupComponent.o(panelComponent.A());
        profileSelectionPopupComponent.d(false);
        profileSelectionPopupComponent.Z(this::lambda$setupProfileNameSubheader$1);
        panelComponent.h(profileSelectionPopupComponent, "widthwrap");
    }

    private CompletableFuture lambda$viewDetailsSettings$22(TextInputComponentBase textInputComponentBase, BooleanToggleComponent booleanToggleComponent, BooleanToggleComponent booleanToggleComponent2, BooleanToggleComponent booleanToggleComponent3) {
        return this.F(this.Hx.c().v(), textInputComponentBase.i$src$Ljava_lang_String_$1n2xf3k(), this.rh.m$src$Ljava_util_List_$17c1eke(), booleanToggleComponent.i$src$Z$1d37ezg(), booleanToggleComponent2.i$src$Z$1d37ezg(), booleanToggleComponent3.i$src$Z$1d37ezg(), true);
    }

    private void h() {
        if (!rC && this.rS == null) {
            throw new AssertionError();
        }
        ArrayList<Long> arrayList = new ArrayList<Long>(this.rU);
        this.rU.clear();
        if (!arrayList.isEmpty()) {
            ApiServices.d().R().j(this.Hx, arrayList).whenCompleteAsync((arg_0, arg_1) -> this.lambda$markViewedReviewsAsRead$23(arrayList, arg_0, arg_1), ClientSettings.f6).exceptionally(PublicProfileOwnerDetailsPanel::lambda$markViewedReviewsAsRead$24);
        }
    }

    private static GuiComponent lambda$viewDetailsReviews$6(Function<PublicProfileReview, PublicProfileReviewComponent> function) {
        return function.apply(null);
    }

    private void Z$src$V$vbit14() {
        this.b$src$V$s019hq();
        double d = this.gg.A();
        PanelComponent panelComponent = new PanelComponent(d, 15.0);
        panelComponent.d(false);
        this.gg.h(panelComponent, new Object[0]);
        this.ru = new PanelComponent(d, this.gg.L() - panelComponent.L() - 6.0);
        this.ru.d(false);
        this.ru.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.L$src$V$v3toq2();
        this.gg.h(this.ru, new Object[0]);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.d(false);
        for (String string : this.ro) {
            TextLabel textLabel = new TextLabel(string, 0.75);
            textLabel.c(false);
            textLabel.l(string.equals(this.rq) ? PublicProfileOwnerDetailsPanel.J.A : PublicProfileOwnerDetailsPanel.J.C);
            textLabel.a(false);
            textLabel.Y(12.0);
            panelComponent.h(textLabel, new Object[0]);
            textLabel.r(() -> this.lambda$viewDetails$0(string, panelComponent));
            if (string.equalsIgnoreCase("reviews") && this.rS.o() > 0L) {
                PublicProfileIdBadgeComponent publicProfileIdBadgeComponent = new PublicProfileIdBadgeComponent(this.rS.o());
                panelComponent.h(publicProfileIdBadgeComponent, "offsetY 3");
                textLabel.o(textLabel.W());
            } else {
                textLabel.o(textLabel.W());
            }
            panelComponent.h(new SpacerComponent(8.0, this.L()), new Object[0]);
            if (!string.equalsIgnoreCase("reviews") || this.rS.o() <= 0L) continue;
            this.getClass();
            panelComponent.h(new SpacerComponent(5.0, this.L()), new Object[0]);
        }
        this.N$src$V$v4x9ws();
    }

    protected void p() {
        this.U$src$V$171rm8f();
        this.gg.Z(false);
        this.gb.Z(false);
        double d = this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().A();
        this.getClass();
        PanelComponent panelComponent = new PanelComponent(d - (double)(5.0f * 2.0f), this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().L());
        panelComponent.d(false);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.K$src$Lgg_vape_ui_click_component_PanelComponent_$111vavy().h(panelComponent, new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(80.0, 10.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(40.0, 10.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(panelComponent.A(), 15.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(20.0, 10.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(panelComponent.A(), 15.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 8.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(50.0, 10.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(panelComponent.A(), 12.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(panelComponent.A(), 15.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(panelComponent.A(), 12.0), new Object[0]);
        panelComponent.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        panelComponent.h(new SkeletonPlaceholderComponent(25.0, 12.0), "widthwrap");
        panelComponent.h(new SpacerComponent(panelComponent.A() - 25.0 - 100.0, 5.0), "widthwrap");
        panelComponent.h(new SkeletonPlaceholderComponent(25.0, 12.0), "widthwrap");
        panelComponent.h(new SpacerComponent(5.0, 5.0), "widthwrap");
        panelComponent.h(new SkeletonPlaceholderComponent(60.0, 12.0), "wrap");
    }
}
