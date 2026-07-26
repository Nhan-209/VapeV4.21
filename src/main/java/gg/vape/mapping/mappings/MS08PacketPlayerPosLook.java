package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;

public class MS08PacketPlayerPosLook
extends Mapping {
    private static String[] V;
    private MappingField u;
    private MappingField K;

    static {
        MS08PacketPlayerPosLook.G(new String[1]);
    }

    public MS08PacketPlayerPosLook() {
        super(MappedClasses.Dg);
        Class<Float> clazz = Float.TYPE;
        boolean bl = true;
        String string = "yRot";
        MS08PacketPlayerPosLook mS08PacketPlayerPosLook = this;
        this.u = this.J(string, bl, clazz);
        if (MS08PacketPlayerPosLook.c() != null) {
            Class<Float> clazz2 = Float.TYPE;
            boolean bl2 = true;
            String string2 = "xRot";
            MS08PacketPlayerPosLook mS08PacketPlayerPosLook2 = this;
            this.K = this.J(string2, bl2, clazz2);
            return;
        }
        Class<Float> clazz3 = Float.TYPE;
        boolean bl3 = true;
        String string3 = "xRot";
        MS08PacketPlayerPosLook mS08PacketPlayerPosLook3 = this;
        this.K = this.J(string3, bl3, clazz3);
        GuiComponent.D(new GuiComponent[1]);
    }

    public static String[] c() {
        return V;
    }

    public float I(Object object) {
        return this.K.getFloat(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void Q(Object object, float f) {
        this.u.setFloat(object, f);
    }

    public float j(Object object) {
        return this.u.getFloat(object);
    }

    public void K(Object object, float f) {
        this.K.setFloat(object, f);
    }

    public static void G(String[] stringArray) {
        V = stringArray;
    }
}

