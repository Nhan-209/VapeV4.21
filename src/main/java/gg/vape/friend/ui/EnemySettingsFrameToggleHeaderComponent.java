package gg.vape.friend.ui;

import gg.vape.friend.ui.EnemySettingsFrame;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.ToggleableFrameHeaderComponent;

class EnemySettingsFrameToggleHeaderComponent
extends ToggleableFrameHeaderComponent {
    final EnemySettingsFrame Ug;

    EnemySettingsFrameToggleHeaderComponent(EnemySettingsFrame enemySettingsFrame, Frame frame, String string, String string2) {
        super(frame, string, string2);
        this.Ug = enemySettingsFrame;
    }

    @Override
    public void R() {
        EnemySettingsFrame.Q(this.Ug).setVisible(this.I$src$Z$f74e2a());
        EnemySettingsFrame.N(this.Ug).setVisible(this.I$src$Z$f74e2a());
        EnemySettingsFrame.W(this.Ug).setVisible(this.I$src$Z$f74e2a());
        EnemySettingsFrame.B(this.Ug).setVisible(this.I$src$Z$f74e2a());
        EnemySettingsFrame.a(this.Ug).setVisible(this.I$src$Z$f74e2a());
        EnemySettingsFrame.g(this.Ug).setVisible(this.I$src$Z$f74e2a());
        this.w$src$Lgg_vape_ui_click_frame_Frame_$y4htd0().l$src$V$1mibm4x();
    }
}
