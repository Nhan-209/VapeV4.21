package gg.vape.ui.click.component;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.frame.AnchoredPopupFrame;
import gg.vape.ui.click.frame.FrameComponent;
import org.jetbrains.annotations.Nullable;

public class PopupSelectorComponent
extends InteractiveComponent {
    protected final FrameComponent Q;
    @Nullable
    protected AnchoredPopupFrame K;
    private static String v;

    static {
        if (PopupSelectorComponent.A$src$Ljava_lang_String_$jvxvcn() != null) {
            PopupSelectorComponent.L("ECQHRb");
        }
    }

    public FrameComponent u$src$Lgg_vape_ui_click_frame_FrameComponent_$bcl1km() {
        return this.Q;
    }

    public static String A$src$Ljava_lang_String_$jvxvcn() {
        return v;
    }

    private void W() {
        this.K = ClientSettings.g(this, this.Q, AnchoredPopupFrame.class);
        this.K.t(true);
    }

    @Nullable
    public AnchoredPopupFrame p() {
        return this.K;
    }

    public PopupSelectorComponent(FrameComponent frameComponent) {
        this.Q = frameComponent;
        this.s(this::W);
    }

    public static void L(String string) {
        v = string;
    }
}

