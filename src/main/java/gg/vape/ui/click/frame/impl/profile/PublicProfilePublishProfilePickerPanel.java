package gg.vape.ui.click.frame.impl.profile;

import gg.vape.Vape;
import gg.vape.config.Profile;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.IconTextActionRowComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrame;
import java.awt.Color;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PublicProfilePublishProfilePickerPanel
extends PanelComponent {
    private final PublicProfilesFrame Yo;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public PublicProfilePublishProfilePickerPanel(PublicProfilesFrame publicProfilesFrame) {
        super(108.0, 155.0);
        this.Yo = publicProfilesFrame;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.d(true);
        this.I(true);
        this.T(PublicProfilePublishProfilePickerPanel.J.B);
        this.h(new SpacerComponent(0.0, 10.0), new Object[0]);
        WrappingTextLabelComponent wrappingTextLabelComponent = new WrappingTextLabelComponent("Create from...", 0.9, Color.WHITE);
        wrappingTextLabelComponent.l(true);
        wrappingTextLabelComponent.Y(12.0);
        wrappingTextLabelComponent.o(this.A());
        this.h(wrappingTextLabelComponent, new Object[0]);
        this.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        IconTextActionRowComponent iconTextActionRowComponent = new IconTextActionRowComponent("Current settings");
        iconTextActionRowComponent.o(this.A());
        iconTextActionRowComponent.s(() -> {
            UUID uUID = Vape.INSTANCE.getProfilesManager().M().P$src$Ljava_util_UUID_$kdhg08();
            Profile profile = new Profile("Current settings", "4.21");
            profile.B(true);
            profile.K(uUID);
            profile.a();
            publicProfilesFrame.O(null);
            publicProfilesFrame.e(profile);
        });
        this.h(iconTextActionRowComponent, "wrap");
        this.h(new SpacerComponent(0.0, 5.0), new Object[0]);
        SimpleTextLabelComponent simpleTextLabelComponent = new SimpleTextLabelComponent("     PRIVATE PROFILES", 0.65, new Color(255, 255, 255, 153));
        simpleTextLabelComponent.l(true);
        simpleTextLabelComponent.Y(12.0);
        simpleTextLabelComponent.o(this.A());
        this.h(simpleTextLabelComponent, new Object[0]);
        this.h(new SpacerComponent(0.0, 2.0), new Object[0]);
        PanelComponent panelComponent = new PanelComponent(this.A(), 90.0);
        panelComponent.d(false);
        panelComponent.T(this.d());
        panelComponent.I(true);
        panelComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        panelComponent.t(panelComponent.L());
        this.h(panelComponent, "widthwrap");
        List<Profile> list = Vape.INSTANCE.getPublicProfileManager().T();
        for (Profile profile : Vape.INSTANCE.getProfilesManager().b()) {
            if (list.contains(profile)) continue;
            IconTextActionRowComponent iconTextActionRowComponent2 = new IconTextActionRowComponent(profile.n$src$Ljava_lang_String_$xqhelw());
            iconTextActionRowComponent2.o(this.A());
            iconTextActionRowComponent2.s(() -> {
                publicProfilesFrame.O(null);
                publicProfilesFrame.e(profile);
            });
            panelComponent.h(iconTextActionRowComponent2, "wrap");
        }
    }

    private void lambda$onMouseGlobal$2() {
        this.Yo.O(null);
    }

    @Override
    public void U(GuiMouseEvent guiMouseEvent) {
        boolean bl = this.Q().J(guiMouseEvent.getX(), guiMouseEvent.getY());
        if (!bl) {
            CompletableFuture.runAsync(this::lambda$onMouseGlobal$2, ClientSettings.f6);
        }
    }

}
