package gg.vape.friend.ui;

import gg.vape.ui.click.component.ItemStackSlotComponent;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.GuiRenderPrimitives;

public class OnlineActivityHeldItemSlotComponent
extends ItemStackSlotComponent {
    private int b = 0;

    public void X(int n) {
        this.b = n;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        GuiRenderPrimitives.e(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), new MutableColor(OnlineActivityHeldItemSlotComponent.J.Z).withAlpha(this.b), false, 1.0f, 1.0f);
    }

    public int Y$src$I$1ottky1() {
        return this.b;
    }
}

