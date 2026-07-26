package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.api.PublicProfileJsonPayloadBuilder;
import gg.vape.config.LegacyPublicProfile;
import gg.vape.config.Profile;
import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.config.PublicProfile;
import gg.vape.friend.ui.OnlineFriendUiHelper;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.InsetFilledSpacerComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.ProfileSelectionPopupComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.gui.TextLabel;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.frame.impl.profile.CompactPublicProfileFilterTokenSelectorComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilePublishEditorBooleanToggleClickHandler;
import gg.vape.ui.click.frame.impl.profile.ProfilePublishFirstFixedWidthNoSubmitInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfilePublishSecondFixedWidthNoSubmitInputComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotModuleCountEmptyStateComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotValueRowComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayCloseButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayPanelBase;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import gg.vape.ui.notification.NotificationType;
import gg.vape.value.ValueSnapshot;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

public class ProfilePublishEditorPanel
extends PublicProfileOverlayPanelBase {
    static final boolean oJ = !ProfilePublishEditorPanel.class.desiredAssertionStatus();
    private ProfileSnapshot oj;
    private Profile oz;
    private CompactPublicProfileFilterTokenSelectorComponent oT;
    private TextInputComponentBase oy;
    private BooleanToggleComponent oN;
    private BooleanToggleComponent o8;
    private BooleanToggleComponent oE;
    private TruncatedTextComponent oi;
    private TextInputComponentBase ow;
    private TextButton ok;

    private void K$src$V$eyc5r7() {
        this.b$src$V$s019hq();
        double d = this.gg.A() - 3.0;
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("NAME", 0.7, ProfilePublishEditorPanel.J.C, true);
        simpleTextLabelComponent.g(0.0f);
        this.gg.h(simpleTextLabelComponent, new Object[0]);
        if (this.oy == null) {
            this.oy = new ProfilePublishFirstFixedWidthNoSubmitInputComponent(this, "+   Enter profile name...", d);
        }
        this.oy.C(0.0);
        this.oy.H(0.0f);
        this.oy.V(1.0f);
        this.oy.I(ProfilePublishEditorPanel.J.A);
        this.oy.A(ProfilePublishEditorPanel.J.Z);
        this.oy.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
        this.oy.d(false);
        this.oy.e(false);
        this.oy.o(this::lambda$viewDetails$2);
        this.gg.h(this.oy, new Object[0]);
        this.gg.h(new InsetFilledSpacerComponent(d, 2.0, 0.5, 0.0, ProfilePublishEditorPanel.J.l), new Object[0]);
        this.gg.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent2 = new SimpleTextLabelComponent("DESCRIPTION", 0.7, ProfilePublishEditorPanel.J.C, true);
        simpleTextLabelComponent2.g(0.0f);
        this.gg.h(simpleTextLabelComponent2, new Object[0]);
        if (this.ow == null) {
            this.ow = new ProfilePublishSecondFixedWidthNoSubmitInputComponent(this, "+   Add Description (optional)", d);
        }
        this.ow.C(0.0);
        this.ow.H(0.0f);
        this.ow.V(1.0f);
        this.ow.I(ProfilePublishEditorPanel.J.A);
        this.ow.A(ProfilePublishEditorPanel.J.Z);
        this.ow.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Z(false);
        this.ow.d(false);
        this.ow.e(false);
        this.gg.h(this.ow, new Object[0]);
        this.gg.h(new InsetFilledSpacerComponent(d, 2.0, 0.5, 0.0, ProfilePublishEditorPanel.J.l), new Object[0]);
        this.gg.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent3 = new SimpleTextLabelComponent("TAGS", 0.7, ProfilePublishEditorPanel.J.C, true);
        simpleTextLabelComponent3.g(0.0f);
        this.gg.h(simpleTextLabelComponent3, "widthwrap");
        SimpleTextLabelComponent simpleTextLabelComponent4 = new SimpleTextLabelComponent("Comma-Seperated", 0.7, ProfilePublishEditorPanel.J.h, false);
        double d2 = simpleTextLabelComponent4.h();
        this.getClass();
        simpleTextLabelComponent4.o(d2 + (double)(5.0f * 2.0f));
        this.gg.h(simpleTextLabelComponent4, "alignright, wrap");
        if (this.oT == null) {
            this.oT = new CompactPublicProfileFilterTokenSelectorComponent("+   Add Tags (optional)", d, 20.0);
        }
        this.gg.h(this.oT, "wrap");
        this.gg.h(new InsetFilledSpacerComponent(d, 2.0, 0.5, 0.0, ProfilePublishEditorPanel.J.l), new Object[0]);
        this.gg.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent5 = new SimpleTextLabelComponent("PREFERENCES", 0.7, ProfilePublishEditorPanel.J.C, true);
        simpleTextLabelComponent5.g(0.0f);
        this.gg.h(simpleTextLabelComponent5, new Object[0]);
        if (this.o8 == null) {
            this.o8 = new BooleanToggleComponent("Discoverable with a Share Code only", 0.8);
        }
        this.o8.C(0.0);
        this.o8.q(d);
        this.o8.d(false);
        this.gg.h(this.o8, new Object[0]);
        if (this.oE == null) {
            this.oE = new BooleanToggleComponent("Friends only discovery", 0.8);
            this.o8.j(new ProfilePublishEditorBooleanToggleClickHandler(this));
            this.oE.Z(false);
        }
        this.oE.C(0.0);
        this.oE.q(d);
        this.oE.d(false);
        this.gg.h(this.oE, new Object[0]);
        if (this.oN == null) {
            this.oN = new BooleanToggleComponent("Upload anonymously", 0.8);
        }
        this.oN.C(0.0);
        this.oN.q(d);
        this.oN.d(false);
        this.gg.h(this.oN, new Object[0]);
    }

    private void lambda$null$4(ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            PublicProfileManager.b("Failed to publish profile.");
            this.gc.O(null);
            return;
        }
        if (!apiResponse.t()) {
            PublicProfileManager.b("Failed to publish profile: " + apiResponse.N());
            return;
        }
        this.gc.O(null);
        PublicProfile publicProfile = (PublicProfile)apiResponse.T();
        if (!oJ && publicProfile == null) {
            throw new AssertionError();
        }
        Vape.INSTANCE.getPublicProfileManager().I(publicProfile);
        this.gc.N(publicProfile);
    }

    private static ApiResponse lambda$null$5(Throwable throwable) {
        return null;
    }

    private static int lambda$viewModuleDetails$7(ValueSnapshot valueSnapshot, ValueSnapshot valueSnapshot2) {
        return Boolean.compare(valueSnapshot.h(), valueSnapshot2.h());
    }

    public static BooleanToggleComponent m(ProfilePublishEditorPanel profilePublishEditorPanel) {
        return profilePublishEditorPanel.oE;
    }

    private void u(ProfileModuleSnapshot profileModuleSnapshot) {
        this.b$src$V$s019hq();
        double d = this.gg.A();
        this.getClass();
        double d2 = d - 5.0;
        PanelComponent panelComponent = new PanelComponent(d2, 12.0);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent.d(false);
        this.gg.h(panelComponent, new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent(profileModuleSnapshot.getName(), 1.0);
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.T$src$V$1orl066(ProfilePublishEditorPanel.J.A);
        panelComponent.h(simpleTextLabelComponent, new Object[0]);
        List<ValueSnapshot<?, ?>> list = profileModuleSnapshot.z().stream().sorted(ProfilePublishEditorPanel::lambda$viewModuleDetails$7).collect(Collectors.toList());
        for (ValueSnapshot valueSnapshot : list) {
            ProfileSnapshotValueRowComponent profileSnapshotValueRowComponent = new ProfileSnapshotValueRowComponent(this.oj, profileModuleSnapshot, valueSnapshot);
            profileSnapshotValueRowComponent.o(this.gg.A() - 5.0);
            profileSnapshotValueRowComponent.T(ProfilePublishEditorPanel.J.m);
            this.gg.h(profileSnapshotValueRowComponent, new Object[0]);
        }
    }

    private void a$src$V$fafmt5() {
        this.s$src$V$1l7a8uk();
        this.gb.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.gb.h(new SpacerComponent(0.0, 10.0), "wrap");
        this.gb.h(new SpacerComponent(105.0, 0.0), new Object[0]);
        TextLabel textLabel = new TextLabel("CANCEL", 0.7, true);
        textLabel.l(null);
        textLabel.c(true);
        textLabel.o(40.0);
        textLabel.Y(15.0);
        textLabel.r(this::lambda$footerPublish$3);
        this.gb.h(textLabel, new Object[0]);
        this.ok = new TextButton("PUBLISH", 0.7, ProfilePublishEditorPanel.J.B, ProfilePublishEditorPanel.J.O);
        this.ok.c(true);
        this.ok.o(60.0);
        this.ok.Y(15.0);
        this.ok.F(false);
        this.ok.h(Color.WHITE);
        this.Z$src$V$f6l2nm();
        this.ok.e(this::lambda$footerPublish$6);
        this.gb.h(this.ok, new Object[0]);
    }

    private void lambda$footerPublish$3() {
        this.gc.O(null);
    }

    private void lambda$setup$0(Profile profile) {
        ProfileSnapshot profileSnapshot = profile.n(true);
        if (profileSnapshot == null) {
            OnlineFriendUiHelper.w(NotificationType.WARNING, "Failed to change derived from.");
            return;
        }
        this.oz = profile;
        this.oj = profileSnapshot;
        this.e();
    }

    public ProfilePublishEditorPanel(PublicProfilesFrame publicProfilesFrame, Profile profile) {
        super(publicProfilesFrame);
        this.oz = profile;
        this.oj = this.oz.n(true);
        this.e();
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$setup$1(ProfileModuleSnapshot profileModuleSnapshot) {
        this.u(profileModuleSnapshot);
    }

    private void lambda$viewDetails$2(char c, int n) {
        this.oi.O(this.oy.i$src$Ljava_lang_String_$1n2xf3k());
        this.Z$src$V$f6l2nm();
    }

    private CompletableFuture lambda$footerPublish$6() {
        Object object;
        String string = this.oy.i$src$Ljava_lang_String_$1n2xf3k().trim();
        if (string.length() < 3) {
            PublicProfileManager.b("Please provide a profile name!");
            this.oy.A(ProfilePublishEditorPanel.J.d);
            return null;
        }
        String string2 = this.ow.i$src$Ljava_lang_String_$1n2xf3k().trim();
        boolean bl = this.o8.i$src$Z$1d37ezg();
        boolean bl2 = this.oE.i$src$Z$1d37ezg();
        boolean bl3 = this.oN.i$src$Z$1d37ezg();
        ArrayList<String> arrayList = new ArrayList<String>(this.oT.m$src$Ljava_util_List_$17c1eke());
        if (arrayList.size() < 5 && (object = LegacyPublicProfile.S(this.oT.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().i$src$Ljava_lang_String_$1n2xf3k().trim())) != null) {
            String string3 = LegacyPublicProfile.e((String)object);
            if (string3 != null) {
                PublicProfileManager.b(string3);
                return null;
            }
            arrayList.add((String)object);
            this.oT.V(new PublicProfileFilterTokenComponent((String)object));
            this.oT.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().k("");
        }
        if ((object = this.oz.I()) == null) {
            Vape.INSTANCE.getProfilesManager().M(this.oz);
        }
        return ApiServices.d().R().y(PublicProfileJsonPayloadBuilder.b(string, "4.21", string2, arrayList, !bl, bl3, bl2, this.oz.P$src$Ljava_util_UUID_$kdhg08(), (com.google.gson.JsonObject)object)).whenCompleteAsync(this::lambda$null$4, (Executor)ClientSettings.f6).exceptionally(ProfilePublishEditorPanel::lambda$null$5);
    }

    @Override
    protected void e() {
        super.e();
        if (this.oz == null) {
            return;
        }
        this.getClass();
        double d = 5.0f * 4.0f;
        this.o(this.gc.A() - d);
        double d2 = this.gc.L() - this.gc.j$src$Lgg_vape_ui_click_frame_FrameHeaderComponent_$175vsfc().L() - 2.0 - d;
        this.getClass();
        this.Y(d2 - 5.0);
        PanelComponent panelComponent = this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j();
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().d(false);
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.h(panelComponent, new Object[0]);
        double d3 = panelComponent.A();
        this.getClass();
        PanelComponent panelComponent2 = new PanelComponent(d3 - (double)(5.0f * 2.0f), 25.0);
        panelComponent2.d(false);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        PanelComponent panelComponent3 = this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j();
        this.getClass();
        panelComponent3.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        panelComponent.h(panelComponent2, new Object[0]);
        double d4 = panelComponent2.A();
        this.getClass();
        this.oi = new TruncatedTextComponent("New Profile", "...", d4 - (double)(5.0f * 2.0f), 1.0, ProfilePublishEditorPanel.J.A, true);
        this.oi.Y(0.0);
        this.oi.u(0.0);
        PanelComponent panelComponent4 = new PanelComponent(panelComponent2.A(), 8.0);
        panelComponent4.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        panelComponent4.d(false);
        this.getClass();
        panelComponent4.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        panelComponent4.h(this.oi, new Object[0]);
        panelComponent2.h(panelComponent4, new Object[0]);
        this.getClass();
        panelComponent2.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        List<Profile> list = Vape.INSTANCE.getPublicProfileManager().T();
        ArrayList<Profile> arrayList = new ArrayList<Profile>(Vape.INSTANCE.getProfilesManager().b());
        arrayList.removeIf(list::contains);
        ProfileSelectionPopupComponent profileSelectionPopupComponent = new ProfileSelectionPopupComponent("Derived From", this.oz, arrayList.toArray(new Profile[0]));
        profileSelectionPopupComponent.Y(6.0);
        profileSelectionPopupComponent.o(panelComponent2.A());
        profileSelectionPopupComponent.Z(this::lambda$setup$0);
        profileSelectionPopupComponent.d(false);
        panelComponent2.h(profileSelectionPopupComponent, "widthwrap");
        PanelComponent panelComponent5 = new PanelComponent(panelComponent.A(), panelComponent.L() - panelComponent2.L() - 6.0);
        panelComponent.h(panelComponent5, new Object[0]);
        panelComponent5.d(false);
        panelComponent5.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        PublicProfileOverlayCloseButton publicProfileOverlayCloseButton = new PublicProfileOverlayCloseButton("Details", 0.8, true, this, this::K$src$V$eyc5r7);
        publicProfileOverlayCloseButton.P(true);
        publicProfileOverlayCloseButton.o(panelComponent5.A());
        panelComponent5.h(publicProfileOverlayCloseButton, new Object[0]);
        panelComponent5.h(new SpacerComponent(0.0, 6.0), new Object[0]);
        List<ProfileModuleSnapshot> list2 = this.oj.Z(false);
        ProfileSnapshotModuleCountEmptyStateComponent profileSnapshotModuleCountEmptyStateComponent = new ProfileSnapshotModuleCountEmptyStateComponent(list2.size());
        panelComponent5.h(profileSnapshotModuleCountEmptyStateComponent, new Object[0]);
        PanelComponent panelComponent6 = new PanelComponent(panelComponent5.A(), panelComponent5.L() - profileSnapshotModuleCountEmptyStateComponent.L() - publicProfileOverlayCloseButton.L());
        panelComponent6.d(false);
        panelComponent6.t(panelComponent6.L());
        panelComponent5.h(panelComponent6, new Object[0]);
        panelComponent6.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        for (ProfileModuleSnapshot profileModuleSnapshot : list2) {
            PublicProfileOverlayCloseButton publicProfileOverlayCloseButton2 = new PublicProfileOverlayCloseButton(profileModuleSnapshot.getName(), 0.8, this, () -> this.lambda$setup$1(profileModuleSnapshot));
            publicProfileOverlayCloseButton2.P(true);
            publicProfileOverlayCloseButton2.o(panelComponent6.A() - 2.0);
            panelComponent6.h(publicProfileOverlayCloseButton2, new Object[0]);
        }
        this.a$src$V$fafmt5();
    }

    private void Z$src$V$f6l2nm() {
        String string = this.oy.i$src$Ljava_lang_String_$1n2xf3k().trim();
        if (string.length() < 3) {
            this.ok.G(ProfilePublishEditorPanel.J.l, ProfilePublishEditorPanel.J.l);
            this.ok.h(ProfilePublishEditorPanel.J.C);
        } else {
            this.ok.G(ProfilePublishEditorPanel.J.B, ProfilePublishEditorPanel.J.O);
            this.ok.h(Color.WHITE);
        }
        this.oy.A(ProfilePublishEditorPanel.J.Z);
    }
}
