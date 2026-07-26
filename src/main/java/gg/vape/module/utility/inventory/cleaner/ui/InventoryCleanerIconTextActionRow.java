package gg.vape.module.utility.inventory.cleaner.ui;

import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerClickableRowComponentBase;
import gg.vape.ui.click.component.GuiClickListener;
import gg.vape.ui.font.SmoothFontRenderer;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;

public class InventoryCleanerIconTextActionRow
extends InventoryCleanerClickableRowComponentBase {
    private final String Q;
    private final String o;

    @Override
    public void H() {
        super.H();
        ImageRenderer.E(Color.WHITE, (float)(this.G$src$D$1b2f02a() + 10.0), (float)(this.n() + this.L() / 2.0 - 3.0), this.o, 6.0f, 6.0f, false);
        SmoothFontRenderer smoothFontRenderer = this.O(0.8);
        smoothFontRenderer.d(this.Q, this.G$src$D$1b2f02a() + 10.0 + 6.0 + 5.0, this.n() + this.L() / 2.0 - smoothFontRenderer.d(this.Q) / 2.0, InventoryCleanerIconTextActionRow.J.A);
    }

    public InventoryCleanerIconTextActionRow(String string, String string2, GuiClickListener guiClickListener) {
        super(guiClickListener);
        this.Q = string;
        this.o = string2;
    }
}

