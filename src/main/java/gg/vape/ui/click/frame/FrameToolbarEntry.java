package gg.vape.ui.click.frame;

import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;

public class FrameToolbarEntry {
    private final FlowLayoutComponent R;
    private boolean t;
    private final GuiComponent O;

    public FlowLayoutComponent P() {
        return this.R;
    }

    public void T(boolean bl) {
        this.t = bl;
    }

    public GuiComponent S() {
        return this.O;
    }

    public FrameToolbarEntry(GuiComponent guiComponent, boolean bl) {
        this.O = guiComponent;
        this.t = bl;
        this.R = new FlowLayoutComponent(guiComponent.double_A());
        this.R.h(guiComponent, new Object[0]);
        this.R.d(false);
    }

    public FrameToolbarEntry(GuiComponent guiComponent) {
        this(guiComponent, true);
    }

    public boolean h() {
        return this.t;
    }
}

