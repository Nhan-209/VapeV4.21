package gg.vape.friend.ui;

import gg.vape.Vape;
import gg.vape.friend.Enemy;
import gg.vape.friend.ui.EnemySettingsAddEnemyInputComponent;
import gg.vape.friend.ui.EnemySettingsEntryRow;
import gg.vape.friend.ui.EnemySettingsFrameToggleHeaderComponent;
import gg.vape.friend.ui.EnemySettingsRemoveEntryClickHandler;
import gg.vape.ui.click.component.ColorDividerComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.ColorValueEditorComponent;
import gg.vape.ui.click.frame.CollapsibleFrame;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameHeaderComponent;

public class EnemySettingsFrame
extends Frame
implements CollapsibleFrame {
    private BooleanToggleComponent XH;
    private BooleanToggleComponent Xg;
    private ColorValueEditorComponent XR;
    private BooleanToggleComponent X9;
    private ColorDividerComponent X5;
    private BooleanToggleComponent X0;
    private boolean Xj = true;

    static ColorValueEditorComponent Q(EnemySettingsFrame enemySettingsFrame) {
        return enemySettingsFrame.XR;
    }

    @Override
    public void v() {
    }

    @Override
    public boolean q() {
        return this.Xj;
    }

    public EnemySettingsFrame() {
        this.X5 = new ColorDividerComponent(EnemySettingsFrame.J.l);
        this.setDisabledOverlayColor(EnemySettingsFrame.J.i);
        this.K(300.0);
        this.S(100.0);
        this.setVisible(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        this.Y(new EnemySettingsFrameToggleHeaderComponent(this, this, "newfriends", "Enemies"));
        this.XR = new ColorValueEditorComponent(Vape.INSTANCE.getEnemyManager().i);
        this.Xg = new BooleanToggleComponent(Vape.INSTANCE.getFriendManager().q);
        this.X0 = new BooleanToggleComponent(Vape.INSTANCE.getEnemyManager().L);
        this.X9 = new BooleanToggleComponent(Vape.INSTANCE.getFriendManager().J);
        this.XH = new BooleanToggleComponent(Vape.INSTANCE.getFriendManager().C);
        this.XR.setDisabledOverlayColor(EnemySettingsFrame.J.r);
        this.Xg.setDisabledOverlayColor(EnemySettingsFrame.J.r);
        this.X0.setDisabledOverlayColor(EnemySettingsFrame.J.r);
        this.X9.setDisabledOverlayColor(EnemySettingsFrame.J.r);
        this.XH.setDisabledOverlayColor(EnemySettingsFrame.J.r);
        this.XR.setVisible(false);
        this.Xg.setVisible(false);
        this.X0.setVisible(false);
        this.X9.setVisible(false);
        this.XH.setVisible(false);
        this.X5.setVisible(false);
    }

    public void Q$src$V$1u5tkk5() {
        this.removeMarkedChildren();
        this.addChildren(this.XR, this.Xg, this.X0, this.X9, this.XH, this.X5);
        this.h(new EnemySettingsAddEnemyInputComponent("Username / Alias"), new Object[0]);
        for (Enemy enemy : Vape.INSTANCE.getEnemyManager().y()) {
            this.h(new EnemySettingsEntryRow(enemy).setDeleteActionListener(new EnemySettingsRemoveEntryClickHandler(this, enemy)), new Object[0]);
        }
        this.l$src$V$1mibm4x();
    }

    static BooleanToggleComponent W(EnemySettingsFrame enemySettingsFrame) {
        return enemySettingsFrame.X0;
    }

    @Override
    public void Y() {
    }

    @Override
    public void w() {
        this.Xj = !this.Xj;
        for (GuiComponent guiComponent : this.f()) {
            if (guiComponent instanceof FrameHeaderComponent) continue;
            guiComponent.setVisible(this.Xj);
        }
        this.l$src$V$1mibm4x();
    }

    static BooleanToggleComponent N(EnemySettingsFrame enemySettingsFrame) {
        return enemySettingsFrame.Xg;
    }

    static ColorDividerComponent g(EnemySettingsFrame enemySettingsFrame) {
        return enemySettingsFrame.X5;
    }


    static BooleanToggleComponent a(EnemySettingsFrame enemySettingsFrame) {
        return enemySettingsFrame.XH;
    }

    static BooleanToggleComponent B(EnemySettingsFrame enemySettingsFrame) {
        return enemySettingsFrame.X9;
    }

    @Override
    public String getName() {
        return "Enemies";
    }
}

