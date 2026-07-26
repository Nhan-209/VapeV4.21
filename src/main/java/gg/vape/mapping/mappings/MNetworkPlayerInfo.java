package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MS08PacketPlayerPosLook;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;

public class MNetworkPlayerInfo
extends Mapping {
    private MappingField I;
    private MappingField j;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int s(Object object) {
        return this.I.getInt(object);
    }

    public MNetworkPlayerInfo() {
        super(MappedClasses.ly);
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = "id";
        MNetworkPlayerInfo mNetworkPlayerInfo = this;
        this.I = this.J(string, bl, clazz);
        Class clazz2 = MappedClasses.Dd;
        boolean bl2 = true;
        String string2 = "values";
        MNetworkPlayerInfo mNetworkPlayerInfo2 = this;
        this.j = this.J(string2, bl2, clazz2);
        String[] stringArray = MS08PacketPlayerPosLook.c();
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MS08PacketPlayerPosLook.G(new String[5]);
        }
    }

    public Object C(Object object) {
        return this.j.getObject(object);
    }
}

