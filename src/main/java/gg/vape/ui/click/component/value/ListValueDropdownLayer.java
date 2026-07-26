package gg.vape.ui.click.component.value;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.value.FloatingValueDropdownLayer;
import gg.vape.ui.click.component.value.ListValueComponent;
import gg.vape.ui.click.component.value.ListValueDropdownCloseClickHandler;
import gg.vape.ui.click.component.value.ListValueOptionsPanel;
import gg.vape.ui.click.frame.Frame;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfilesFrameHeaderActionComponent;

public class ListValueDropdownLayer
extends FloatingValueDropdownLayer<ListValueComponent> {
    private final ListValueOptionsPanel Kc;

    public ListValueOptionsPanel m$src$Lgg_vape_ui_click_component_value_ListValueOptio$g5twj8() {
        return this.Kc;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void e() {
        this.Kc.k$src$V$admw0a();
    }

    @Override
    public void u() {
        Frame frame;
        FrameComponent frameComponent;
        super.u();
        ListValueComponent listValueComponent = (ListValueComponent)this.C$src$Lgg_vape_ui_click_component_value_AbstractListVa$13qpumn();
        if (listValueComponent != null && (frameComponent = listValueComponent.B$src$Lgg_vape_ui_click_frame_FrameComponent_$1yr52yb()).equals(frame = frameComponent.L$src$Lgg_vape_ui_click_frame_Frame_$1djx6sa()) && !frame.f().contains(listValueComponent)) {
            ((ListValueComponent)this.C$src$Lgg_vape_ui_click_component_value_AbstractListVa$13qpumn()).a(false);
        }
    }

    public ListValueDropdownLayer(ListValueComponent listValueComponent) {
        super(listValueComponent);
        this.Y(new PublicProfilesFrameHeaderActionComponent(this, listValueComponent.n$src$Z$13pgjoe() ? "blockedicon" : "allowedicon", listValueComponent.E()).Q(new ListValueDropdownCloseClickHandler(this, listValueComponent)));
        this.Kc = new ListValueOptionsPanel(listValueComponent.i$src$Lgg_vape_value_ListValue_$1aag8wx(), listValueComponent.n$src$Z$13pgjoe());
        this.H(this.Kc);
    }
}

