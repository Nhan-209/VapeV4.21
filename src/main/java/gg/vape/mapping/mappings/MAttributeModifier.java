package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MIAttributeInstance;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.UUID;

public class MAttributeModifier
extends Mapping {
    private MappingField i;
    private MappingMethod M;
    private final MappingMethod Q;

    public static UUID getID(MAttributeModifier mAttributeModifier, Object object) {
        return mAttributeModifier.getID(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private Object R(Object object) {
        return this.i.getObject(object);
    }

    private UUID getID(Object object) {
        return (UUID)this.M.L(object, new Object[0]);
    }

    public double getAmount(Object object) {
        return this.Q.F(object, new Object[0]);
    }

    public static Object T(MAttributeModifier mAttributeModifier, Object object) {
        return mAttributeModifier.R(object);
    }

    public MAttributeModifier() {
        this(MIAttributeInstance.B());
    }

    private MAttributeModifier(String string) {
        super(MappedClasses.z_);
        if (string != null) {
            if (ForgeVersion.MC_1_21_0.d()) {
                Class clazz = MappedClasses.zC;
                boolean bl = true;
                String string2 = "id";
                MAttributeModifier mAttributeModifier = this;
                this.i = mAttributeModifier.J(string2, bl, clazz);
            } else {
                Class[] classArray = new Class[]{};
                Class<UUID> clazz = UUID.class;
                boolean bl = true;
                String string3 = "getID";
                MAttributeModifier mAttributeModifier = this;
                this.M = mAttributeModifier.Y(string3, bl, clazz, classArray);
            }
            Class[] classArray = new Class[]{};
            Class<Double> clazz = Double.TYPE;
            boolean bl = true;
            String string4 = "getAmount";
            MAttributeModifier mAttributeModifier = this;
            this.Q = mAttributeModifier.Y(string4, bl, clazz, classArray);
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                MIAttributeInstance.k("QRcZV");
            }
            return;
        }
        Class[] classArray = new Class[]{};
        Class<UUID> clazz = UUID.class;
        boolean bl = true;
        String string5 = "getID";
        MAttributeModifier mAttributeModifier = this;
        this.M = mAttributeModifier.Y(string5, bl, clazz, classArray); 
        Class[] classArray2 = new Class[]{};
        Class<Double> clazz2 = Double.TYPE;
        boolean bl2 = true;
        String string6 = "getAmount";
        MAttributeModifier mAttributeModifier2 = this;
        this.Q = this.Y(string6, bl2, clazz2, classArray2);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MIAttributeInstance.k("QRcZV");
        }
    }
}

