package gg.vape.ui.click.frame.impl.profile;

import com.google.gson.JsonArray;
import gg.vape.Vape;
import gg.vape.api.ApiHttpClient;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.api.PagedResult;
import gg.vape.config.Profile;
import gg.vape.config.ProfileSnapshot;
import gg.vape.config.PublicProfile;
import gg.vape.config.PublicProfileSortMode;
import gg.vape.config.PublicProfileSummary;
import gg.vape.event.EventBus;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.PublicProfileCreatedEvent;
import gg.vape.event.impl.PublicProfileDeletedEvent;
import gg.vape.friend.ui.OnlineConnectionStatusPanelBody;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.manager.client.OnlineConnectionState;
import gg.vape.manager.client.PublicProfileManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FilledSpacerComponent;
import gg.vape.ui.click.component.GlyphIconComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.InsetFilledSpacerComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.SquareIconButtonComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.gui.TextButton;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.component.publicprofiles.PublicProfileListEntryComponent;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.FrameScrollbarPlacement;
import gg.vape.ui.click.frame.PopupFrame;
import gg.vape.ui.click.frame.impl.hud.HudSettingsFrameBase;
import gg.vape.ui.click.frame.impl.main.ClickGuiFrameManager;
import gg.vape.ui.click.frame.impl.profile.ProfilePublishEditorPanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileListingDetailsPanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfileListingResultCardComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileListingResultOpenClickHandler;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayPanelBase;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayPopupFrame;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOwnerDetailsPanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfilePublishProfilePickerPanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfileResultsListComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileSearchFilterPanel;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrameHeaderComponent;
import gg.vape.ui.click.layout.WrappingFlowLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PublicProfilesFrame
extends Frame
implements EventListener {
    private String Fc = "";
    private boolean Fn;
    private PaddedComponent Fy;
    private PublicProfileResultsListComponent FX;
    private boolean Fb = true;
    private PanelComponent F6;
    static final boolean F4 = !PublicProfilesFrame.class.desiredAssertionStatus();
    private PublicProfileSearchFilterPanel FN;
    private PublicProfileSortMode F8 = PublicProfileSortMode.RATED;
    private boolean Fk;
    private boolean FB;
    private PanelComponent Fq;
    @Nullable
    private PopupFrame FH;

    private static ApiResponse lambda$openWithEditor$23(Throwable throwable) {
        return null;
    }

    private static void lambda$openWithEditor$21(PublicProfilesFrame publicProfilesFrame) {
        publicProfilesFrame.l((PublicProfile)null);
    }

    public PublicProfileResultsListComponent P$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$1ezbs2g() {
        return this.FX;
    }

    private static void lambda$createCenteredOverlayNode$11(Runnable runnable) {
        runnable.run();
    }

    private void t(PanelComponent panelComponent) {
        GuiComponent guiComponent;
        PanelComponent panelComponent2 = new PanelComponent(this.Fb ? 92.0 : 8.0, panelComponent.L());
        this.Fy = new PaddedComponent(4.0, 4.0, 6.0, 6.0, panelComponent2);
        panelComponent.h(this.Fy, new Object[0]);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        PanelComponent panelComponent3 = new PanelComponent(panelComponent2.A(), 12.0);
        if (this.Fb) {
            guiComponent = new SimpleTextLabelComponent("YOUR PUBLIC PROFILES");
            ((SimpleTextLabelComponent)guiComponent).l(true);
            ((SimpleTextLabelComponent)guiComponent).T$src$V$1orl066(PublicProfilesFrame.J.h);
            ((SimpleTextLabelComponent)guiComponent).i(0.7);
            ((SimpleTextLabelComponent)guiComponent).g(0.0f);
            ((SimpleTextLabelComponent)guiComponent).z(-2.0f);
            panelComponent3.N(false);
            panelComponent3.h(guiComponent, new Object[0]);
        }
        guiComponent = new GlyphIconComponent(this.Fb ? "hide hover@2x" : "show hover@2x", 5.0, 4.0, 5.0, 4.0, null, null, null);
        panelComponent3.h(guiComponent, "alignright");
        ((InteractiveComponent)guiComponent).r(this::lambda$createLeftContainer$1);
        panelComponent2.h(panelComponent3, new Object[0]);
        if (this.Fb) {
            PanelComponent panelComponent4 = new PanelComponent(panelComponent2.A(), panelComponent2.L() - panelComponent3.L());
            panelComponent4.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
            panelComponent2.h(panelComponent4, "widthwrap");
            TextButton textButton = new TextButton("CREATE NEW", 0.8, PublicProfilesFrame.J.B, PublicProfilesFrame.J.O);
            textButton.o(panelComponent4.A());
            textButton.Y(14.0);
            textButton.r(() -> this.lambda$createLeftContainer$2(textButton));
            textButton.c(true);
            textButton.F(false);
            textButton.h(Color.WHITE);
            panelComponent4.h(textButton, new Object[0]);
            SpacerComponent spacerComponent = new SpacerComponent(0.0, 2.0);
            panelComponent4.h(spacerComponent, new Object[0]);
            this.Fq = new PanelComponent(panelComponent4.A(), panelComponent2.L() - panelComponent3.L() - textButton.L() - spacerComponent.L() + 2.0);
            this.Fq.k(true);
            this.Fq.t(this.Fq.L());
            this.Fq.d(false);
            this.Fq.F(FrameScrollbarPlacement.OUTSIDE);
            this.Fq.T(true);
            this.Fq.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
            panelComponent4.h(this.Fq, new Object[0]);
            for (PublicProfile publicProfile : Vape.INSTANCE.getPublicProfileManager().A().values()) {
                this.e(publicProfile);
            }
        }
    }

    private void J(PopupFrame popupFrame, boolean bl) {
        if (popupFrame == null) {
            return;
        }
        ClientSettings.p(popupFrame);
        ClientSettings.K(popupFrame);
        if (popupFrame.V$src$Lgg_vape_ui_click_component_GuiComponent_$jpbobc() instanceof PopupFrame && !bl) {
            this.J((PopupFrame)popupFrame.V$src$Lgg_vape_ui_click_component_GuiComponent_$jpbobc(), true);
        }
    }

    private static void lambda$openWithPublicListing$16(PublicProfilesFrame publicProfilesFrame) {
        publicProfilesFrame.l((PublicProfile)null);
    }

    public void O(@Nullable PopupFrame popupFrame) {
        PopupFrame popupFrame2 = this.FH;
        if (popupFrame2 != null) {
            this.D(popupFrame2);
            this.a();
            this.l$src$V$1mibm4x();
        }
        this.FH = popupFrame;
    }

    private static void lambda$openWithEditor$22(PublicProfileOwnerDetailsPanel publicProfileOwnerDetailsPanel, PublicProfilesFrame publicProfilesFrame, ApiResponse apiResponse, Throwable throwable) {
        if (publicProfileOwnerDetailsPanel.R$src$Ljava_util_concurrent_CompletableFuture_$1ccqvok().isCancelled()) {
            return;
        }
        publicProfileOwnerDetailsPanel.T((CompletableFuture<?>)null);
        if (throwable != null) {
            Vape.logThrowable(throwable);
            publicProfilesFrame.D(publicProfileOwnerDetailsPanel.E());
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to load public profile data: " + apiResponse.N());
            PublicProfileManager.b("Failed to view profile: " + apiResponse.N());
            publicProfilesFrame.D(publicProfileOwnerDetailsPanel.E());
            return;
        }
        if (!F4 && apiResponse.T() == null) {
            throw new AssertionError();
        }
        publicProfilesFrame.N((PublicProfile)apiResponse.T());
    }

    private List<GuiComponent> lambda$null$7(Function<PublicProfileSummary, PaddedComponent> function, ApiResponse<PagedResult<PublicProfileSummary>> apiResponse) {
        if (!apiResponse.t()) {
            return null;
        }
        if (!F4 && apiResponse.T() == null) {
            throw new AssertionError();
        }
        this.FX.t(apiResponse.T());
        List<PublicProfileSummary> summaries = apiResponse.T().E();
        ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>();
        for (PublicProfileSummary publicProfileSummary : summaries) {
            PaddedComponent paddedComponent = function.apply(publicProfileSummary);
            arrayList.add(paddedComponent);
            Vape.INSTANCE.getPublicProfileManager().y(publicProfileSummary);
            PublicProfileListingResultCardComponent publicProfileListingResultCardComponent = paddedComponent.t(PublicProfileListingResultCardComponent.class);
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            publicProfileListingResultCardComponent.j(new PublicProfileListingResultOpenClickHandler(this, atomicBoolean, publicProfileSummary));
        }
        return arrayList;
    }

    private static GuiComponent lambda$createRightContainer$6(Function function) {
        return (PaddedComponent)function.apply(null);
    }

    public static void a(@Nullable Consumer<PublicProfilesFrame> consumer) {
        PublicProfilesFrame publicProfilesFrame = ClientSettings.g(PublicProfilesFrame.class);
        publicProfilesFrame.t(true, false);
        ClientSettings.fW.I(ClientSettings.f0);
        if (consumer != null) {
            consumer.accept(publicProfilesFrame);
        }
    }

    @Override
    public void t(boolean bl, boolean bl2) {
        super.t(bl, bl2);
        this.Fk = false;
    }

    @Override
    public void v() {
    }

    @Override
    public void Y() {
    }

    public void T(PopupFrame popupFrame) {
        this.J(popupFrame, false);
    }

    private void lambda$createLeftContainer$1() {
        this.Fb = !this.Fb;
        this.W();
    }

    public PublicProfileOverlayPopupFrame y(@Nullable Frame frame, GuiComponent guiComponent) {
        AtomicReference<PublicProfileOverlayPopupFrame> atomicReference = new AtomicReference<PublicProfileOverlayPopupFrame>();
        Frame frame2 = frame != null ? frame : this;
        PublicProfileOverlayPopupFrame publicProfileOverlayPopupFrame = ClientSettings.g(frame2, this.Z(guiComponent, () -> this.lambda$addCenteredOverlay$10(atomicReference)), PublicProfileOverlayPopupFrame.class);
        atomicReference.set(publicProfileOverlayPopupFrame);
        publicProfileOverlayPopupFrame.T((int)((frame2.A() - guiComponent.A()) / 2.0));
        publicProfileOverlayPopupFrame.Q((int)((frame2.L() - guiComponent.L()) / 2.0));
        this.C(publicProfileOverlayPopupFrame);
        return publicProfileOverlayPopupFrame;
    }

    private void lambda$createLeftContainer$2(TextButton textButton) {
        this.h(textButton);
    }

    @Nullable
    public static CompletableFuture<?> J(boolean bl, long l) {
        if (bl) {
            if (!OnlineConnectionManager.T.n().equals((Object)OnlineConnectionState.ONLINE)) {
                PublicProfilesFrame.a(PublicProfilesFrame::lambda$openWithEditor$20);
                return null;
            }
            PublicProfilesFrame.a(PublicProfilesFrame::lambda$openWithEditor$21);
        }
        PublicProfilesFrame publicProfilesFrame = ClientSettings.g(PublicProfilesFrame.class);
        PublicProfileOwnerDetailsPanel publicProfileOwnerDetailsPanel = publicProfilesFrame.N((PublicProfile)null);
        publicProfileOwnerDetailsPanel.T(ApiServices.d().R().x(l).whenCompleteAsync((arg_0, arg_1) -> PublicProfilesFrame.lambda$openWithEditor$22(publicProfileOwnerDetailsPanel, publicProfilesFrame, arg_0, arg_1), (Executor)ClientSettings.f6).exceptionally(PublicProfilesFrame::lambda$openWithEditor$23));
        return publicProfileOwnerDetailsPanel.R$src$Ljava_util_concurrent_CompletableFuture_$1ccqvok();
    }

    private void lambda$null$3() {
        this.FX.W();
    }

    public PublicProfileOverlayPopupFrame S(@Nullable Frame frame, GuiComponent guiComponent) {
        PublicProfileOverlayPopupFrame publicProfileOverlayPopupFrame = ClientSettings.g(frame != null ? frame : this, guiComponent, PublicProfileOverlayPopupFrame.class);
        this.O(publicProfileOverlayPopupFrame);
        return publicProfileOverlayPopupFrame;
    }

    public void l(PublicProfileSortMode publicProfileSortMode) {
        this.F8 = publicProfileSortMode;
    }

    public PublicProfilesFrame() {
        this.K(200.0);
        this.S(200.0);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().t(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().I(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().U(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Y(new PublicProfilesFrameHeaderComponent(this, this, "newprofiles", "Public Profiles", 0.5).Q(PublicProfilesFrame::lambda$new$0));
        this.W();
        this.Y(false);
        this.Z(true);
        this.L(false, true);
        this.g(true);
        EventBus.getInstance().registerListener(this, new Predicate[0]);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static void lambda$null$17(ApiResponse apiResponse, PublicProfilesFrame publicProfilesFrame) {
        publicProfilesFrame.l((PublicProfile)apiResponse.T());
    }

    private static void lambda$new$0() {
        if (ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v() instanceof ClickGuiFrameManager) {
            ClickGuiFrameManager clickGuiFrameManager = (ClickGuiFrameManager)ClientSettings.fW.b$src$Lgg_vape_ui_click_frame_FrameStackManager_$8fdo9v();
            clickGuiFrameManager.G();
        } else {
            ClientSettings.fW.I(ClientSettings.a);
        }
    }

    private void W() {
        if (!OnlineConnectionManager.T.n().equals((Object)OnlineConnectionState.ONLINE)) {
            this.d$src$V$fo8605();
            return;
        }
        this.FB = false;
        this.S();
        this.h(new InsetFilledSpacerComponent(this.A(), 2.0, 0.5, 0.0, PublicProfilesFrame.J.l), new Object[0]);
        PanelComponent panelComponent = new PanelComponent(this.x(), 185.0);
        this.h(panelComponent, new Object[0]);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
        this.t(panelComponent);
        FilledSpacerComponent filledSpacerComponent = new FilledSpacerComponent(1.0, panelComponent.L() + 2.0, PublicProfilesFrame.J.m);
        if (!this.Fb) {
            panelComponent.h(filledSpacerComponent, new Object[0]);
        }
        panelComponent.h(new InsetFilledSpacerComponent(4.0, 0.0, 0.5, 0.0, new Color(0, 0, 0, 0)), new Object[0]);
        this.D(panelComponent);
        this.FX.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.H(true);
    }

    private static void lambda$openWithPublicListing$15(PublicProfilesFrame publicProfilesFrame) {
    }

    public PublicProfileOverlayPopupFrame W(@Nullable Frame frame, GuiComponent guiComponent) {
        Frame frame2 = frame != null ? frame : this;
        AtomicReference<PublicProfileOverlayPopupFrame> atomicReference = new AtomicReference<PublicProfileOverlayPopupFrame>();
        PublicProfileOverlayPopupFrame publicProfileOverlayPopupFrame = ClientSettings.g(frame2, this.Z(guiComponent, () -> this.lambda$setCenteredOverlay$9(atomicReference)), PublicProfileOverlayPopupFrame.class);
        atomicReference.set(publicProfileOverlayPopupFrame);
        publicProfileOverlayPopupFrame.T((int)((frame2.A() - guiComponent.A()) / 2.0));
        publicProfileOverlayPopupFrame.Q((int)((frame2.L() - guiComponent.L()) / 2.0));
        this.O(publicProfileOverlayPopupFrame);
        return publicProfileOverlayPopupFrame;
    }

    @Override
    public double L() {
        return 214.0;
    }

    @Override
    public String getName() {
        return "Public Profiles";
    }

    private void D(PopupFrame popupFrame) {
        PanelComponent panelComponent = popupFrame.k(PanelComponent.class);
        if (panelComponent != null) {
            for (GuiComponent guiComponent : panelComponent.f()) {
                FrameComponent frameComponent;
                if (guiComponent instanceof PanelComponent) {
                    frameComponent = (PanelComponent)guiComponent;
                    for (GuiComponent guiComponent2 : frameComponent.f()) {
                        FrameComponent frameComponent2;
                        if (guiComponent2 instanceof PublicProfileOverlayPanelBase) {
                            frameComponent2 = (PublicProfileOverlayPanelBase)guiComponent2;
                            ((PublicProfileOverlayPanelBase)frameComponent2).d$src$V$15t6q4y();
                        }
                        if (!(guiComponent2 instanceof HudSettingsFrameBase)) continue;
                        frameComponent2 = (HudSettingsFrameBase)guiComponent2;
                        ((HudSettingsFrameBase)frameComponent2).Q$src$V$1vahh5c();
                    }
                }
                if (guiComponent instanceof PublicProfileOverlayPanelBase) {
                    frameComponent = (PublicProfileOverlayPanelBase)guiComponent;
                    ((PublicProfileOverlayPanelBase)frameComponent).d$src$V$15t6q4y();
                }
                if (!(guiComponent instanceof HudSettingsFrameBase)) continue;
                frameComponent = (HudSettingsFrameBase)guiComponent;
                ((HudSettingsFrameBase)frameComponent).Q$src$V$1vahh5c();
            }
        }
        ClientSettings.K(popupFrame);
        if (this.FH == popupFrame) {
            this.FH = null;
        }
    }

    @Override
    public void a() {
        PopupFrame popupFrame = this.FH;
        if (popupFrame != null) {
            this.T(popupFrame);
            this.FH = null;
        }
    }

    static void k(PublicProfilesFrame publicProfilesFrame, PopupFrame popupFrame) {
        publicProfilesFrame.D(popupFrame);
    }

    private void lambda$onPublicProfileDelete$14(PublicProfileDeletedEvent publicProfileDeletedEvent) {
        ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>();
        for (GuiComponent guiComponent : this.Fq.f()) {
            PaddedComponent paddedComponent;
            PublicProfileListEntryComponent publicProfileListEntryComponent;
            if (!(guiComponent instanceof PaddedComponent) || (publicProfileListEntryComponent = (paddedComponent = (PaddedComponent)guiComponent).t(PublicProfileListEntryComponent.class)) == null || publicProfileListEntryComponent.I$src$Lgg_vape_config_PublicProfile_$mb4s3m().w() != publicProfileDeletedEvent.getProfile().w()) continue;
            arrayList.add(guiComponent);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        for (GuiComponent guiComponent : arrayList) {
            this.Fq.I(guiComponent);
        }
        Vape.INSTANCE.getProfilesManager().T();
    }

    private static PaddedComponent lambda$createRightContainer$5(PublicProfileSummary publicProfileSummary) {
        PublicProfileListingResultCardComponent publicProfileListingResultCardComponent = new PublicProfileListingResultCardComponent(publicProfileSummary);
        return new PaddedComponent(1.0, 2.0, 0.0, 3.0, publicProfileListingResultCardComponent);
    }

    @Nullable
    public static CompletableFuture<?> s(long l) {
        if (!OnlineConnectionManager.T.n().equals((Object)OnlineConnectionState.ONLINE)) {
            PublicProfilesFrame.a(PublicProfilesFrame::lambda$openWithPublicListing$15);
            return null;
        }
        PublicProfilesFrame.a(PublicProfilesFrame::lambda$openWithPublicListing$16);
        return ApiServices.d().R().x(l).whenCompleteAsync(PublicProfilesFrame::lambda$openWithPublicListing$18, (Executor)ClientSettings.f6).exceptionally(PublicProfilesFrame::lambda$openWithPublicListing$19);
    }

    private void lambda$addCenteredOverlay$10(AtomicReference atomicReference) {
        PublicProfileOverlayPopupFrame publicProfileOverlayPopupFrame = (PublicProfileOverlayPopupFrame)atomicReference.get();
        if (publicProfileOverlayPopupFrame != null) {
            this.D(publicProfileOverlayPopupFrame);
        }
    }

    private static void lambda$openWithEditor$20(PublicProfilesFrame publicProfilesFrame) {
    }

    public void e(Profile profile) {
        this.W(null, new ProfilePublishEditorPanel(this, profile));
    }

    @Override
    public void u() {
        if (!OnlineConnectionManager.T.n().equals((Object)OnlineConnectionState.ONLINE) && !this.FB) {
            this.d$src$V$fo8605();
        } else if (OnlineConnectionManager.T.n().equals((Object)OnlineConnectionState.ONLINE) && this.FB) {
            this.Fk = false;
        }
        PopupFrame popupFrame = this.FH;
        if (popupFrame != null && popupFrame.V$src$Z$1xhop3l()) {
            popupFrame.T$src$V$1wse0de();
        }
    }

    public PublicProfileSortMode Z$src$Lgg_vape_config_PublicProfileSortMode_$18pvsyy() {
        return this.F8;
    }

    private static ApiResponse lambda$openWithPublicListing$19(Throwable throwable) {
        return null;
    }

    @Override
    public void c() {
        if (!this.Fk) {
            this.Fk = true;
            this.Fn = true;
            this.W();
            this.Fn = false;
        }
        if (this.FX != null) {
            this.FX.H(true);
        }
        if (this.F6 != null) {
            this.F6.H(true);
        }
        super.c();
    }

    public static void w$src$V$fyo9a0() {
        PublicProfilesFrame.a((Consumer<PublicProfilesFrame>)null);
    }

    private PanelComponent Z(@NotNull GuiComponent guiComponent, Runnable runnable) {
        PanelComponent panelComponent = new PanelComponent(guiComponent.A(), guiComponent.L() + 11.0);
        panelComponent.T(PublicProfilesFrame.J.m);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.z(PublicProfilesFrame.J.l);
        panelComponent.R(1.0f);
        PanelComponent panelComponent2 = new PanelComponent(guiComponent.A(), 10.0);
        panelComponent2.d(false);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("alignright");
        panelComponent.h(panelComponent2, new Object[0]);
        SquareIconButtonComponent squareIconButtonComponent = new SquareIconButtonComponent("newclose", 1.2, new Color(0, 0, 0, 0), PublicProfilesFrame.J.h, 8.0, 8.0);
        squareIconButtonComponent.r(() -> PublicProfilesFrame.lambda$createCenteredOverlayNode$11(runnable));
        panelComponent2.h(new PaddedComponent(2.0, 2.0, 2.0, 2.0, squareIconButtonComponent), new Object[0]);
        panelComponent.h(guiComponent, new Object[0]);
        return panelComponent;
    }

    public void C(PopupFrame popupFrame) {
        Vape.debugLog("addPopup(" + popupFrame + ")");
        PopupFrame popupFrame2 = this.FH;
    }

    public PublicProfileOwnerDetailsPanel N(@Nullable PublicProfile publicProfile) {
        if (publicProfile == null) {
            PublicProfileOwnerDetailsPanel publicProfileOwnerDetailsPanel = new PublicProfileOwnerDetailsPanel(this, null, null);
            publicProfileOwnerDetailsPanel.S(this.W(null, publicProfileOwnerDetailsPanel));
            return publicProfileOwnerDetailsPanel;
        }
        ProfileSnapshot profileSnapshot = ProfileSnapshot.z(publicProfile);
        PublicProfileOwnerDetailsPanel publicProfileOwnerDetailsPanel = new PublicProfileOwnerDetailsPanel(this, publicProfile, profileSnapshot);
        publicProfileOwnerDetailsPanel.S(this.W(null, publicProfileOwnerDetailsPanel));
        return publicProfileOwnerDetailsPanel;
    }

    @Nullable
    public PopupFrame A$src$Lgg_vape_ui_click_frame_PopupFrame_$45a6ba() {
        return this.FH;
    }

    public String o$src$Ljava_lang_String_$ububnq() {
        return this.Fc;
    }

    private CompletableFuture<List<GuiComponent>> lambda$createRightContainer$8(Function<PublicProfileSummary, PaddedComponent> function) {
        String string;
        this.Fc = string = this.FN.V$src$Lgg_vape_ui_click_component_TextInputComponentBa$1su0cvr().i$src$Ljava_lang_String_$1n2xf3k().trim();
        return ApiServices.d().R().r(this.F8, this.FX.A$src$J$1vju51i(), string, this.FN.U$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$1cokhj1().i$src$Ljava_util_List_$1ydnhqa().stream().map(PublicProfileFilterTokenComponent::N).collect(Collectors.toList())).thenApplyAsync(arg_0 -> this.lambda$null$7(function, arg_0), (Executor)ClientSettings.f6);
    }

    private void lambda$setCenteredOverlay$9(AtomicReference atomicReference) {
        PublicProfileOverlayPopupFrame publicProfileOverlayPopupFrame = (PublicProfileOverlayPopupFrame)atomicReference.get();
        if (publicProfileOverlayPopupFrame != null) {
            this.D(publicProfileOverlayPopupFrame);
        }
    }

    private void e(PublicProfile publicProfile) {
        PublicProfileListEntryComponent publicProfileListEntryComponent = new PublicProfileListEntryComponent(publicProfile);
        publicProfileListEntryComponent.o(92.0);
        publicProfileListEntryComponent.e(() -> PublicProfilesFrame.lambda$addPublicProfileButton$12(publicProfile));
        this.Fq.h(new PaddedComponent(0.0, 1.0, publicProfileListEntryComponent), new Object[0]);
    }

    @EventHandler
    public void R(PublicProfileCreatedEvent publicProfileCreatedEvent) {
        ClientSettings.f6.execute(() -> this.lambda$onPublicProfileCreate$13(publicProfileCreatedEvent));
    }

    private static void lambda$openWithPublicListing$18(ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            Vape.logThrowable(throwable);
            return;
        }
        if (!apiResponse.t()) {
            Vape.debugLog("Failed to load public profile data: " + apiResponse.N());
            PublicProfileManager.b("Failed to view profile: " + apiResponse.N());
            return;
        }
        if (!F4 && apiResponse.T() == null) {
            throw new AssertionError();
        }
        PublicProfilesFrame.a(arg_0 -> PublicProfilesFrame.lambda$null$17(apiResponse, arg_0));
    }

    @EventHandler
    public void X(PublicProfileDeletedEvent publicProfileDeletedEvent) {
        ClientSettings.f6.execute(() -> this.lambda$onPublicProfileDelete$14(publicProfileDeletedEvent));
    }

    public PublicProfileListingDetailsPanel l(@Nullable PublicProfile publicProfile) {
        if (publicProfile == null) {
            PublicProfileListingDetailsPanel publicProfileListingDetailsPanel = new PublicProfileListingDetailsPanel(this, null, null);
            publicProfileListingDetailsPanel.S(this.W(null, publicProfileListingDetailsPanel));
            return publicProfileListingDetailsPanel;
        }
        Object var5_4 = publicProfile.s$src$Ljava_util_Map_$1fhtcsp() != null ? publicProfile.s$src$Ljava_util_Map_$1fhtcsp().getOrDefault("modules", null) : null;
        ProfileSnapshot profileSnapshot = new ProfileSnapshot(null, (JsonArray)ApiHttpClient.Z.fromJson(var5_4 != null ? ApiHttpClient.Z.toJson(var5_4) : "[]", JsonArray.class));
        PublicProfileListingDetailsPanel publicProfileListingDetailsPanel = new PublicProfileListingDetailsPanel(this, publicProfile, profileSnapshot);
        publicProfileListingDetailsPanel.S(this.W(null, publicProfileListingDetailsPanel));
        return publicProfileListingDetailsPanel;
    }

    private void lambda$createRightContainer$4() {
        ClientSettings.f6.execute(this::lambda$null$3);
    }

    private void d$src$V$fo8605() {
        this.FB = true;
        this.S();
        OnlineConnectionStatusPanelBody onlineConnectionStatusPanelBody = new OnlineConnectionStatusPanelBody();
        this.h(new PaddedComponent(this.A() / 2.0 - onlineConnectionStatusPanelBody.A() / 2.0, this.L() / 2.0 - onlineConnectionStatusPanelBody.L() / 2.0 - 20.0, onlineConnectionStatusPanelBody), new Object[0]);
        this.H(true);
    }

    private static CompletableFuture lambda$addPublicProfileButton$12(PublicProfile publicProfile) {
        return PublicProfilesFrame.J(false, publicProfile.w());
    }

    public void h(TextButton textButton) {
        PublicProfileOverlayPopupFrame publicProfileOverlayPopupFrame = this.S(null, new PublicProfilePublishProfilePickerPanel(this));
        publicProfileOverlayPopupFrame.T((int)(textButton.G$src$D$1b2f02a() - this.G$src$D$1b2f02a()) + 45);
        publicProfileOverlayPopupFrame.Q((int)(textButton.n() - this.n()) + 5);
    }

    private void D(PanelComponent panelComponent) {
        this.F6 = new PanelComponent(panelComponent.A() - this.Fy.A() - (double)(this.Fb ? 5 : 6), panelComponent.L() + 10.0);
        this.F6.t(this.F6.L());
        this.F6.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.h(this.F6, new Object[0]);
        this.F6.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("ALL PUBLIC PROFILES");
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.T$src$V$1orl066(PublicProfilesFrame.J.h);
        simpleTextLabelComponent.i(0.7);
        simpleTextLabelComponent.g(0.0f);
        this.F6.h(simpleTextLabelComponent, new Object[0]);
        this.F6.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        PublicProfileSearchFilterPanel publicProfileSearchFilterPanel = this.FN;
        this.FN = new PublicProfileSearchFilterPanel(this.Fb ? 240.0 : 324.0, this::lambda$createRightContainer$4);
        if (publicProfileSearchFilterPanel != null) {
            this.FN.V$src$Lgg_vape_ui_click_component_TextInputComponentBa$1su0cvr().k(publicProfileSearchFilterPanel.V$src$Lgg_vape_ui_click_component_TextInputComponentBa$1su0cvr().i$src$Ljava_lang_String_$1n2xf3k());
            for (PublicProfileFilterTokenComponent publicProfileFilterTokenComponent : publicProfileSearchFilterPanel.U$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$1cokhj1().i$src$Ljava_util_List_$1ydnhqa()) {
                this.FN.U$src$Lgg_vape_ui_click_frame_impl_profile_PublicProfi$1cokhj1().V(publicProfileFilterTokenComponent);
            }
        }
        this.F6.h(this.FN, new Object[0]);
        this.F6.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        if (this.FX == null || this.Fn) {
            this.FX = new PublicProfileResultsListComponent(this.F6.A() - 6.0, 50.0);
            this.FX.N(new WrappingFlowLayout(this.FX));
            this.FX.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("widthwrap");
            this.FX.N(false);
            this.FX.A(this.Fb ? 3 : 4);
            this.FX.F(FrameScrollbarPlacement.OUTSIDE);
            Function<PublicProfileSummary, PaddedComponent> function = PublicProfilesFrame::lambda$createRightContainer$5;
            this.FX.e(() -> PublicProfilesFrame.lambda$createRightContainer$6(function));
            this.FX.N(() -> this.lambda$createRightContainer$8(function));
            this.FX.W();
        } else {
            this.FX.q(this.F6.A() - 6.0);
            this.FX.A(this.Fb ? 3 : 4);
            this.FX.s$src$V$1wbbuzw();
        }
        this.FX.T(this.F6);
        this.FX.d(false);
        this.F6.h(this.FX, new Object[0]);
    }

    @Override
    public double x() {
        return 356.0;
    }

    private void lambda$onPublicProfileCreate$13(PublicProfileCreatedEvent publicProfileCreatedEvent) {
        this.e(publicProfileCreatedEvent.getProfile());
        Vape.INSTANCE.getProfilesManager().T();
    }
}
