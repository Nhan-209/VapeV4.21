package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.config.PublicProfile;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.CenteredGlyphComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.InsetFilledSpacerComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SkeletonPlaceholderComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotApplyBarComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotModuleCountEmptyStateComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotValueRowComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayCloseButton;
import gg.vape.ui.click.frame.impl.profile.PublicProfileOverlayPanelBase;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import gg.vape.value.ValueSnapshot;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Nullable;

public class PublicProfileSnapshotPanelBase
extends PublicProfileOverlayPanelBase {
    @Nullable
    private Runnable H1;
    protected PublicProfile Hx;
    private boolean Hh;
    protected ProfileSnapshot Hd;

    @Override
    public void s$src$V$1l7a8uk() {
        super.s$src$V$1l7a8uk();
        this.gb.h(new SpacerComponent(0.0, 1.5), new Object[0]);
        this.gb.h(new InsetFilledSpacerComponent(this.gb.A(), 1.0, 0.5, 0.0, PublicProfileSnapshotPanelBase.J.a), "wrap");
    }

    public boolean D$src$Z$16sf48q() {
        return this.Hh;
    }

    protected void U$src$V$171rm8f() {
        PanelComponent panelComponent = this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j();
        this.getClass();
        panelComponent.h(new SpacerComponent(5.0, 0.0), new Object[0]);
        double d = this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().A();
        this.getClass();
        PanelComponent panelComponent2 = new PanelComponent(d - 5.0, this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().L());
        panelComponent2.d(false);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().h(panelComponent2, new Object[0]);
        panelComponent2.h(new SkeletonPlaceholderComponent(panelComponent2.A(), 20.0), new Object[0]);
        panelComponent2.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        panelComponent2.h(new SkeletonPlaceholderComponent(panelComponent2.A(), 15.0), new Object[0]);
        panelComponent2.h(new SpacerComponent(0.0, 3.0), new Object[0]);
        panelComponent2.h(new SkeletonPlaceholderComponent(panelComponent2.A(), 15.0), new Object[0]);
        panelComponent2.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        panelComponent2.h(new SkeletonPlaceholderComponent(panelComponent2.A(), 110.0), new Object[0]);
    }

    @Nullable
    public Runnable f$src$Ljava_lang_Runnable_$1nnivky() {
        return this.H1;
    }

    public ProfileSnapshot W() {
        return this.Hd;
    }

    public PublicProfile y$src$Lgg_vape_config_PublicProfile_$7edhxt() {
        return this.Hx;
    }

    public PublicProfileSnapshotPanelBase(PublicProfilesFrame publicProfilesFrame, PublicProfile publicProfile, ProfileSnapshot profileSnapshot) {
        this(publicProfilesFrame, publicProfile, profileSnapshot, false);
    }

    public void z(@Nullable Runnable runnable) {
        this.H1 = runnable;
    }

    private static ObfuscatedRuntimeException f(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void lambda$setup$1(ProfileModuleSnapshot profileModuleSnapshot) {
        this.n(profileModuleSnapshot);
    }

    private static int lambda$viewModuleDetails$2(ValueSnapshot valueSnapshot, ValueSnapshot valueSnapshot2) {
        return Boolean.compare(valueSnapshot.h(), valueSnapshot2.h());
    }

    private void lambda$setup$0() {
        if (this.H1 != null) {
            this.H1.run();
        }
    }

    @Override
    protected void e() {
        GuiComponent guiComponent;
        super.e();
        if (this.Hd == null) {
            return;
        }
        this.n$src$V$s6msm2();
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().d(false);
        PanelComponent panelComponent = this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j();
        this.getClass();
        panelComponent.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        PanelComponent panelComponent2 = new PanelComponent(this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().A(), 25.0);
        panelComponent2.d(false);
        panelComponent2.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().h(panelComponent2, new Object[0]);
        this.getClass();
        panelComponent2.h(new SpacerComponent(5.0, 0.0), "widthwrap");
        CenteredGlyphComponent centeredGlyphComponent = new CenteredGlyphComponent("vertical menu hover@2x", 5.0f, 5.0f);
        String string = this.Hx.v();
        double d = panelComponent2.A();
        this.getClass();
        TruncatedTextComponent truncatedTextComponent = new TruncatedTextComponent(string, "...", d - (double)(5.0f * 2.0f) - centeredGlyphComponent.A(), 1.0, PublicProfileSnapshotPanelBase.J.A, true);
        truncatedTextComponent.o(truncatedTextComponent.v());
        truncatedTextComponent.Y(10.0);
        panelComponent2.h(truncatedTextComponent, new Object[0]);
        centeredGlyphComponent.H(PublicProfileSnapshotPanelBase.J.Z);
        this.z(panelComponent2);
        PublicProfileOverlayCloseButton publicProfileOverlayCloseButton = new PublicProfileOverlayCloseButton("Details", 0.8, true, this, this::lambda$setup$0);
        publicProfileOverlayCloseButton.P(true);
        publicProfileOverlayCloseButton.o(panelComponent2.A() - 2.0);
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().h(publicProfileOverlayCloseButton, new Object[0]);
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().h(new SpacerComponent(0.0, 6.0), new Object[0]);
        PanelComponent panelComponent3 = new PanelComponent(this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().A(), this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().L() - panelComponent2.L());
        this.X$src$Lgg_vape_ui_click_component_PanelComponent_$ylzx2j().h(panelComponent3, new Object[0]);
        panelComponent3.d(false);
        panelComponent3.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        List<ProfileModuleSnapshot> list = this.Hd.Z(false);
        if (this.Hh && this.Hd.d() != null) {
            guiComponent = new ProfileSnapshotApplyBarComponent(this.Hd, panelComponent3.A(), false);
            ((ProfileSnapshotApplyBarComponent)guiComponent).M(ClientSettings.f0);
            ((ProfileSnapshotApplyBarComponent)guiComponent).K(this.Hd);
            panelComponent3.h(guiComponent, new Object[0]);
        } else {
            guiComponent = new ProfileSnapshotModuleCountEmptyStateComponent(list.size());
            panelComponent3.h(guiComponent, new Object[0]);
        }
        PanelComponent panelComponent4 = new PanelComponent(panelComponent3.A(), panelComponent3.L() - guiComponent.L() - publicProfileOverlayCloseButton.L());
        panelComponent4.d(false);
        panelComponent4.T(this.d());
        panelComponent4.t(panelComponent4.L() - 6.0);
        panelComponent4.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        for (ProfileModuleSnapshot profileModuleSnapshot : list) {
            PublicProfileOverlayCloseButton publicProfileOverlayCloseButton2 = new PublicProfileOverlayCloseButton(profileModuleSnapshot.getName(), 0.8, this, () -> this.lambda$setup$1(profileModuleSnapshot));
            publicProfileOverlayCloseButton2.P(true);
            publicProfileOverlayCloseButton2.o(panelComponent4.A() - 4.0);
            panelComponent4.h(publicProfileOverlayCloseButton2, new Object[0]);
        }
        panelComponent3.h(panelComponent4, new Object[0]);
    }

    protected void n(ProfileModuleSnapshot profileModuleSnapshot) {
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
        simpleTextLabelComponent.T$src$V$1orl066(PublicProfileSnapshotPanelBase.J.A);
        panelComponent.h(simpleTextLabelComponent, new Object[0]);
        List<ValueSnapshot<?, ?>> list = profileModuleSnapshot.z().stream().sorted(PublicProfileSnapshotPanelBase::lambda$viewModuleDetails$2).collect(Collectors.toList());
        for (ValueSnapshot<?, ?> valueSnapshot : list) {
            ProfileSnapshotValueRowComponent profileSnapshotValueRowComponent = new ProfileSnapshotValueRowComponent(this.Hd, profileModuleSnapshot, valueSnapshot);
            profileSnapshotValueRowComponent.o(this.gg.A() - 5.0);
            profileSnapshotValueRowComponent.T(PublicProfileSnapshotPanelBase.J.m);
            this.gg.h(profileSnapshotValueRowComponent, new Object[0]);
        }
    }

    protected void z(PanelComponent panelComponent) {
    }

    public PublicProfileSnapshotPanelBase(PublicProfilesFrame publicProfilesFrame, PublicProfile publicProfile, ProfileSnapshot profileSnapshot, boolean bl) {
        super(publicProfilesFrame);
        this.Hx = publicProfile;
        this.Hd = profileSnapshot;
        this.Hh = bl;
        this.e();
    }
}
