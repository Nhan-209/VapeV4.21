package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.Friend;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.TextInputComponentBase;

public class FriendSettingsAddFriendInputComponent
extends TextInputComponentBase {
    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public void p() {
        if (!this.u$src$Z$wt77ym()) {
            this.k("");
            return;
        }
        String[] stringArray = this.i$src$Ljava_lang_String_$1n2xf3k().split(" ");
        String string = stringArray[0];
        String string2 = stringArray.length > 1 ? stringArray[1] : stringArray[0];
        Vape.INSTANCE.getFriendManager().u(new Friend(string, string2));
        this.k("");
    }

    @Override
    public double r() {
        return this.A() - 35.0;
    }

    public FriendSettingsAddFriendInputComponent(String string) {
        super(string);
        this.d(false);
        this.a = FriendSettingsAddFriendInputComponent.J.B;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double x() {
        return 110.0;
    }
}

