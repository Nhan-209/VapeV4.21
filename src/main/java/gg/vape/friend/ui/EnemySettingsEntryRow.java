package gg.vape.friend.ui;

import gg.vape.friend.Enemy;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.SelectableTextRowComponent;

public class EnemySettingsEntryRow
extends SelectableTextRowComponent {
    private Enemy oi;

    @Override
    public boolean l() {
        return !this.oi.t();
    }

    public Enemy H$src$Lgg_vape_friend_Enemy_$swsf8f() {
        return this.oi;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        this.oi.k(!this.oi.t());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void H() {
        String string = this.oi.y();
        if (!this.oi.R().equals(this.oi.y()) && !this.u$src$Z$1dafklf()) {
            string = "*" + this.oi.x();
        }
        this.n(string);
        super.H();
    }

    public EnemySettingsEntryRow(Enemy enemy) {
        super(EnemySettingsEntryRow.J.d, enemy.y());
        this.oi = enemy;
    }
}

