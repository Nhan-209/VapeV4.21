package gg.vape.friend.ui;

import gg.vape.friend.Enemy;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.SelectableTextRowComponent;

public class EnemySettingsEntryRow
extends SelectableTextRowComponent {
    private final Enemy enemy;

    @Override
    public boolean isSelected() {
        return !this.enemy.t();
    }

    public Enemy getEnemy() {
        return this.enemy;
    }

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
        this.enemy.k(!this.enemy.t());
    }


    @Override
    public void H() {
        String text = this.enemy.y();
        if (!this.enemy.R().equals(this.enemy.y()) && !this.isHovered()) {
            text = "*" + this.enemy.x();
        }
        this.setText(text);
        super.H();
    }

    public EnemySettingsEntryRow(Enemy enemy) {
        super(EnemySettingsEntryRow.J.d, enemy.y());
        this.enemy = enemy;
    }
}

