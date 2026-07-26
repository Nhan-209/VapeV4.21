package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.Enemy;
import gg.vape.friend.ui.EnemySettingsFrame;
import gg.vape.ui.click.component.GuiClickListener;

class EnemySettingsRemoveEntryClickHandler
implements GuiClickListener {
    final Enemy o;
    final EnemySettingsFrame Y;

    @Override
    public void P() {
        Vape.INSTANCE.saveAndStop();
        Vape.INSTANCE.getEnemyManager().s(this.o);
        this.Y.Q$src$V$1u5tkk5();
    }

    EnemySettingsRemoveEntryClickHandler(EnemySettingsFrame enemySettingsFrame, Enemy enemy) {
        this.Y = enemySettingsFrame;
        this.o = enemy;
    }
}

