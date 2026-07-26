package gg.vape.ui.click.frame.impl.online;

import gg.vape.Vape;
import gg.vape.api.ApiResponse;
import gg.vape.api.ApiServices;
import gg.vape.manager.client.OnlineConnectionManager;
import gg.vape.module.none.ClientSettings;
import gg.vape.notification.NotificationType;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.WrappingTextLabelComponent;
import gg.vape.ui.click.component.input.DebouncedTextInputComponent;
import gg.vape.ui.click.component.layout.PaddedComponent;
import gg.vape.ui.click.frame.impl.online.OnlineAccountLinkCodeInputComponent;
import gg.vape.ui.click.frame.impl.online.OnlineConnectionSettingsPageComponent;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class OnlineAccountLinkCodePageComponent
extends OnlineConnectionSettingsPageComponent {
    private boolean q3 = false;
    private final WrappingTextLabelComponent qR;
    private final DebouncedTextInputComponent qk = new OnlineAccountLinkCodeInputComponent(this, "Username", 2000L);

    @Override
    public void s() {
    }

    static void r(OnlineAccountLinkCodePageComponent onlineAccountLinkCodePageComponent) {
        onlineAccountLinkCodePageComponent.W();
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public OnlineAccountLinkCodePageComponent() {
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.h(new SpacerComponent(0.0, 10.0), new Object[0]);
        WrappingTextLabelComponent wrappingTextLabelComponent = new WrappingTextLabelComponent("Create profile", 0.9);
        wrappingTextLabelComponent.o(this.A());
        wrappingTextLabelComponent.Y(24.0);
        wrappingTextLabelComponent.l(true);
        wrappingTextLabelComponent.T$src$V$1orl066(OnlineAccountLinkCodePageComponent.J.A);
        this.h(wrappingTextLabelComponent, new Object[0]);
        this.qk.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().W("newnext");
        this.qk.v(OnlineAccountLinkCodePageComponent.J.t);
        this.qk.I(OnlineAccountLinkCodePageComponent.J.h);
        this.qk.H(10.0f);
        this.qk.V(6.0f);
        this.h(this.qk, new Object[0]);
        this.qR = new WrappingTextLabelComponent("Create a profile to access Vape Online features\n \nYour selected username will not be visible anywhere unless you share it privately, or publicly comment with it", 0.7);
        this.qR.o(this.A() - 20.0);
        this.qR.Y(40.0);
        this.qR.T$src$V$1orl066(OnlineAccountLinkCodePageComponent.J.Z);
        this.qR.l(true);
        this.h(new PaddedComponent(10.0, 0.0, this.qR), new Object[0]);
    }

    private void lambda$inputOnEnter$1(ApiResponse apiResponse, Throwable throwable) {
        if (throwable != null) {
            this.M("Failed to register 1");
            Vape.INSTANCE.getNotificationManager().t("Registration Error", "Something went wrong while registering. ", NotificationType.WARNING, 5000L);
        }
        this.q3 = false;
    }

    private ApiResponse lambda$inputOnEnter$0(String string, ApiResponse apiResponse) {
        if (apiResponse.t()) {
            Vape.INSTANCE.getAccountInfo().y(string);
            Vape.INSTANCE.getAccountInfo().f().X(true);
            OnlineConnectionManager.T.I();
        } else {
            this.M("Registration Error:\n" + apiResponse.N());
            Vape.INSTANCE.getNotificationManager().t("Registration Error", apiResponse.N(), NotificationType.WARNING, 5000L);
        }
        return apiResponse;
    }

    private ApiResponse lambda$inputOnEnter$2(Throwable throwable) {
        this.M("Failed to register 2");
        return null;
    }

    private void M(String string) {
        this.qR.G(string);
        this.qR.i(0.7);
        this.qR.l(true);
    }

    private void W() {
        this.qk.L$src$V$w6nnjd();
        if (!this.q3) {
            this.q3 = true;
            String string = this.qk.i$src$Ljava_lang_String_$1n2xf3k();
            if (string.isEmpty()) {
                this.q3 = false;
                return;
            }
            ApiServices.d().A(string).thenApplyAsync(arg_0 -> this.lambda$inputOnEnter$0(string, arg_0), (Executor)ClientSettings.f6).whenCompleteAsync(this::lambda$inputOnEnter$1, (Executor)ClientSettings.f6).exceptionally(this::lambda$inputOnEnter$2);
        }
    }
}
