package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.animation.ColorAnimation;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.PanelComponent;
import gg.vape.ui.click.component.SelectableTextRowComponent;
import gg.vape.ui.click.component.SpacerComponent;
import gg.vape.ui.click.component.value.ListValueAddEntryInputComponent;
import gg.vape.ui.click.component.value.RemoveLimitValueEntryHandler;
import gg.vape.ui.click.component.value.RemoveOptionalLimitEntryHandler;
import gg.vape.unmap.ItemLimitData;
import gg.vape.value.LimitValue;
import gg.vape.value.ListValue;
import gg.vape.value.OptionalLimitEntry;
import gg.vape.value.OptionalLimitValue;
import java.awt.Color;
import java.util.List;

public class ListValueOptionsPanel
extends PanelComponent {
    private final FlowLayoutComponent Lj;
    private final ListValue L7;
    private final boolean Ld;
    private final boolean LO;
    private static final Color Lt = new Color(37, 36, 37);

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ListValueOptionsPanel(ListValue listValue, boolean bl, double d, double d2, boolean bl2) {
        super(d, d2);
        this.L7 = listValue;
        this.Ld = bl;
        this.LO = bl2;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M("wrap");
        if (this.LO) {
            this.d(false);
        }
        if (this.LO) {
            this.H(new SpacerComponent(0.0, 4.0));
        }
        ListValueAddEntryInputComponent listValueAddEntryInputComponent = new ListValueAddEntryInputComponent(this, bl, "Add entry...", listValue);
        listValueAddEntryInputComponent.E(listValue.o());
        listValueAddEntryInputComponent.P(true);
        listValueAddEntryInputComponent.o(d);
        if (this.LO) {
            listValueAddEntryInputComponent.D(0.75f);
            listValueAddEntryInputComponent.I(4.0f);
            listValueAddEntryInputComponent.s(ColorAnimation.Y(ListValueOptionsPanel.J.k));
            listValueAddEntryInputComponent.W(null);
            listValueAddEntryInputComponent.I(ListValueOptionsPanel.J.Z);
            listValueAddEntryInputComponent.A(ListValueOptionsPanel.J.h);
            listValueAddEntryInputComponent.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().o(10.0);
            listValueAddEntryInputComponent.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().Y(10.0);
            listValueAddEntryInputComponent.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().d(6.0);
            listValueAddEntryInputComponent.t$src$Lgg_vape_ui_click_component_GlyphIconComponent_$s6bz9o().U(6.0);
            listValueAddEntryInputComponent.O(0.0f);
            listValueAddEntryInputComponent.W(true);
            listValueAddEntryInputComponent.Y(16.0);
        }
        this.H(listValueAddEntryInputComponent);
        if (this.LO) {
            this.H(new SpacerComponent(0.0, 5.0));
        }
        this.Lj = new FlowLayoutComponent(d);
        this.Lj.t(d2 - 25.0);
        if (this.LO) {
            this.Lj.d(false);
        }
        this.H(this.Lj);
    }

    public ListValueOptionsPanel(ListValue listValue, boolean bl, double d, double d2) {
        this(listValue, bl, d, d2, false);
    }

    public void k$src$V$admw0a() {
        block5: {
            block4: {
                this.Lj.S();
                if (!(this.L7 instanceof OptionalLimitValue)) break block4;
                for (OptionalLimitEntry optionalLimitEntry : (List<OptionalLimitEntry>)((OptionalLimitValue)this.L7).K()) {
                    SelectableTextRowComponent selectableTextRowComponent = new SelectableTextRowComponent(this.Ld ? ListValueOptionsPanel.J.d : ListValueOptionsPanel.J.B, optionalLimitEntry.r()).I(new RemoveOptionalLimitEntryHandler(this, optionalLimitEntry)).s(optionalLimitEntry);
                    selectableTextRowComponent.P(true);
                    selectableTextRowComponent.o(this.A());
                    if (this.LO) {
                        selectableTextRowComponent.W(true);
                        selectableTextRowComponent.Y(20.0);
                        selectableTextRowComponent.d(4);
                        selectableTextRowComponent.B(0.5f);
                        selectableTextRowComponent.R(Lt);
                    }
                    this.Lj.h(selectableTextRowComponent, new Object[0]);
                }
                break block5;
            }
            if (!(this.L7 instanceof LimitValue)) break block5;
            for (ItemLimitData itemLimitData : (List<ItemLimitData>)((LimitValue)this.L7).K()) {
                String string = itemLimitData.getName() + (itemLimitData.L() < 0 ? "" : ":" + itemLimitData.L());
                SelectableTextRowComponent selectableTextRowComponent = new SelectableTextRowComponent(this.Ld ? ListValueOptionsPanel.J.d : ListValueOptionsPanel.J.B, string).I(new RemoveLimitValueEntryHandler(this, itemLimitData)).s(itemLimitData);
                selectableTextRowComponent.P(true);
                selectableTextRowComponent.o(this.A());
                if (this.LO) {
                    selectableTextRowComponent.W(true);
                    selectableTextRowComponent.Y(20.0);
                    selectableTextRowComponent.d(4);
                    selectableTextRowComponent.B(0.5f);
                    selectableTextRowComponent.R(Lt);
                }
                this.Lj.h(selectableTextRowComponent, new Object[0]);
            }
        }
    }

    public ListValueOptionsPanel(ListValue listValue, boolean bl) {
        this(listValue, bl, 110.0, 110.0, false);
    }

    public static ListValue I(ListValueOptionsPanel listValueOptionsPanel) {
        return listValueOptionsPanel.L7;
    }
}
