package gg.vape.ui.click.frame.impl.profile;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.LabeledTextInputComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileFilterTokenSelectorComponent;
import java.util.stream.Collectors;

public class PublicProfileFilterTokenSearchInputComponent
extends LabeledTextInputComponent {
    final PublicProfileFilterTokenSelectorComponent pL;
    final Runnable pF;

    public PublicProfileFilterTokenSearchInputComponent(PublicProfileFilterTokenSelectorComponent publicProfileFilterTokenSelectorComponent, String string, boolean bl, boolean bl2, Runnable runnable) {
        super(string, bl, bl2);
        this.pL = publicProfileFilterTokenSelectorComponent;
        this.pF = runnable;
    }

    @Override
    public void l$src$V$1mkxjop() {
        if (this.i$src$Ljava_lang_String_$1n2xf3k().isEmpty()) {
            this.pL.A$src$V$14t6dd1();
        }
    }

    @Override
    protected void R() {
        if (PublicProfileFilterTokenSelectorComponent.A(this.pL)) {
            PublicProfileFilterTokenSelectorComponent.E(this.pL).w(this.pL.i$src$Ljava_util_List_$1ydnhqa().stream().map(PublicProfileFilterTokenComponent::N).collect(Collectors.joining(", ")));
            PublicProfileFilterTokenSelectorComponent.E(this.pL).S(PublicProfileFilterTokenSelectorComponent.u(this.pL).size() + " tags");
            PublicProfileFilterTokenSelectorComponent.E(this.pL).K(this.G$src$D$1b2f02a() + (double)super.g());
            PublicProfileFilterTokenSelectorComponent.E(this.pL).S(this.n() + this.L() / 2.0 - PublicProfileFilterTokenSelectorComponent.E(this.pL).L() / 2.0);
            PublicProfileFilterTokenSelectorComponent.E(this.pL).c();
            if (PublicProfileFilterTokenSelectorComponent.E(this.pL).t()) {
                PublicProfileFilterTokenSelectorComponent.E(this.pL).J();
            }
            return;
        }
        double d = 0.0;
        double d2 = this.n() + this.L() / 2.0;
        for (GuiComponent guiComponent : PublicProfileFilterTokenSelectorComponent.u(this.pL)) {
            guiComponent.K(this.G$src$D$1b2f02a() + (double)super.g() + d);
            guiComponent.S(d2 - guiComponent.L() / 2.0);
            if (guiComponent.t()) {
                guiComponent.J();
            }
            guiComponent.c();
            d += guiComponent.A() + 2.0;
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        boolean bl = guiMouseEvent.getAction() == MouseButton.RIGHT_CLICK;
        String string = this.pL.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().i$src$Ljava_lang_String_$1n2xf3k();
        super.g(guiMouseEvent);
        this.pL.g(guiMouseEvent);
        if (bl && !this.pL.o$src$Lgg_vape_ui_click_component_TextInputComponentBa$1oe42xz().i$src$Ljava_lang_String_$1n2xf3k().equals(string)) {
            this.pF.run();
        }
    }

    @Override
    public float g() {
        if (PublicProfileFilterTokenSelectorComponent.A(this.pL)) {
            return (float)((double)super.g() + (PublicProfileFilterTokenSelectorComponent.E(this.pL).A() + 4.0));
        }
        float f = 0.0f;
        for (GuiComponent guiComponent : PublicProfileFilterTokenSelectorComponent.u(this.pL)) {
            f += (float)guiComponent.A() + 2.0f;
        }
        return super.g() + (f + 2.0f);
    }
}
