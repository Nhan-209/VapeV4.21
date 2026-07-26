package gg.vape.friend.ui;

import gg.vape.ui.click.component.PanelComponent;

public abstract class PartyMemberStatusComponent
extends PanelComponent {
    private boolean _q = false;
    private static String _C;

    public boolean boolean_l() {
        return this._q;
    }

    public PartyMemberStatusComponent() {
        super(0.0, 0.0);
    }

    public void J(boolean bl) {
        this._q = bl;
    }

    public static void i(String string) {
        _C = string;
    }

    public static String java_lang_String_P() {
        return _C;
    }

    public abstract boolean boolean_I();

    static {
        if (PartyMemberStatusComponent.java_lang_String_P() != null) {
            PartyMemberStatusComponent.i("fFjvh");
        }
    }

    public /* synthetic */ boolean I$src$Z$19lcktz() {
        return this.boolean_I();
    }
}

