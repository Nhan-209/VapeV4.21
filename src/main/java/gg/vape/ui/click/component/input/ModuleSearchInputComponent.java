package gg.vape.ui.click.component.input;

import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.MouseButton;
import gg.vape.ui.click.component.TextInputComponentBase;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrame;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrameHeader;
import gg.vape.ui.click.frame.impl.ModuleSearchFrameHeader;

public class ModuleSearchInputComponent
extends TextInputComponentBase {
    private ClientSettingsSearchFrameHeader XF;
    private ModuleSearchFrameHeader Xt;
    private int Xh = 0;

    @Override
    public double x() {
        return 0.0;
    }

    public ModuleSearchInputComponent(ModuleSearchFrameHeader moduleSearchFrameHeader) {
        super("");
        this.Xt = moduleSearchFrameHeader;
        this.b.Z(false);
    }

    @Override
    public void c() {
        if (!(this.XF == null || ClientSettings.fT != null && ClientSettings.fT.equals(this))) {
            ClientSettings.fT = this;
        }
        this.e$src$V$wkeide();
    }

    @Override
    public float y() {
        return 0.0f;
    }


    @Override
    public float g() {
        return 0.0f;
    }

    @Override
    public void F() {
    }

    @Override
    public void u() {
        int n;
        if (this.V$src$Z$1xhop3l() && this.Xt != null && (n = this.i$src$Ljava_lang_String_$1n2xf3k().length()) != this.Xh) {
            this.Xt.l$src$V$11ec2hr();
            this.Xt.K$src$V$10w6uwu();
            this.Xh = n;
        }
    }

    public ModuleSearchInputComponent(ClientSettingsSearchFrameHeader clientSettingsSearchFrameHeader) {
        super("");
        this.XF = clientSettingsSearchFrameHeader;
        this.b.Z(false);
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        if (this.Xt != null) {
            super.g(guiMouseEvent);
            return;
        }
        boolean bl = this.Q().J(guiMouseEvent.getX(), guiMouseEvent.getY());
        if (bl) {
            if (guiMouseEvent.getAction().equals((Object)MouseButton.RIGHT_CLICK)) {
                this.k("");
                ((ClientSettingsSearchFrame)this.XF.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0()).K$src$V$1nbah4f();
            }
            if (ClientSettings.fT != null) {
                // empty if block
            }
            return;
        }
        if (ClientSettings.fT != null) {
            ClientSettings.fT = null;
            this.XF.a(false);
            this.Z(false);
        }
    }

    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public void H() {
    }

    @Override
    public void p() {
    }
}

