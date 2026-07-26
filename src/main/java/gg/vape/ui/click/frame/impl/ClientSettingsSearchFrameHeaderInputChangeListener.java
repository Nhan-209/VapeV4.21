package gg.vape.ui.click.frame.impl;

import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiKeyTypedListener;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrame;
import gg.vape.ui.click.frame.impl.ClientSettingsSearchFrameHeader;

public class ClientSettingsSearchFrameHeaderInputChangeListener
implements GuiKeyTypedListener {
    final ClientSettingsSearchFrameHeader W;
    final ClientSettingsSearchFrame y;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void v(char c, int n) {
        if (ClientSettingsSearchFrameHeader.b(this.W) != ClientSettingsSearchFrameHeader.j(this.W).i$src$Ljava_lang_String_$1n2xf3k().length()) {
            this.y.K$src$V$1nbah4f();
            ClientSettings.V = null;
        }
        ClientSettingsSearchFrameHeader.L(this.W, ClientSettingsSearchFrameHeader.j(this.W).i$src$Ljava_lang_String_$1n2xf3k().length());
    }

    public ClientSettingsSearchFrameHeaderInputChangeListener(ClientSettingsSearchFrameHeader clientSettingsSearchFrameHeader, ClientSettingsSearchFrame clientSettingsSearchFrame) {
        this.W = clientSettingsSearchFrameHeader;
        this.y = clientSettingsSearchFrame;
    }
}

