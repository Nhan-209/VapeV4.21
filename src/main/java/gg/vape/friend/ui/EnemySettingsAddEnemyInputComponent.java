package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.Enemy;
import gg.vape.friend.ui.EnemySettingsFrame;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.TextInputComponentBase;

public class EnemySettingsAddEnemyInputComponent
extends TextInputComponentBase {
    @Override
    public double C() {
        return 20.0;
    }

    @Override
    public double r() {
        return this.A() - 35.0;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public EnemySettingsAddEnemyInputComponent(String string) {
        super(string);
        this.d(false);
        this.a = EnemySettingsAddEnemyInputComponent.J.d;
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
        Vape.INSTANCE.getEnemyManager().Q(new Enemy(string, string2));
        ClientSettings.g(EnemySettingsFrame.class).Q$src$V$1u5tkk5();
        this.k("");
    }

    @Override
    public double x() {
        return 110.0;
    }
}

