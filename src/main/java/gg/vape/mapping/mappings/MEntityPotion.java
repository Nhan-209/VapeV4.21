package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.mapping.mappings.MSPacketMapChunkBulk;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MEntityPotion
extends Mapping {
    private MappingMethod F;
    private MappingField o;
    private MappingMethod A;

    private Object d(Object object) {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.F.L(object, new Object[0]);
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            return this.A.L(object, new Object[0]);
        }
        return this.o.getObject(object);
    }

    public MEntityPotion() {
        this(MSPacketMapChunkBulk.E$src$Ljava_lang_String_$1dqes8v());
    }

    private MEntityPotion(String string) {
        super(MappedClasses.Zf);
        if (string != null) {
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.VK;
                String string2 = "getItem";
                MEntityPotion mEntityPotion = this;
                this.F = ((MappingMethodBuilder)((MappingMethodBuilder)mEntityPotion.u(string2, clazz, classArray).y(MappedClasses.ub)).Q(ForgeVersion.MC_1_21_4.n(), MappedClasses.Y4)).s();
            } else if (ForgeVersion.MC_1_12_2.d()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.VK;
                boolean bl = true;
                String string3 = "getPotion";
                MEntityPotion mEntityPotion = this;
                this.A = mEntityPotion.Y(string3, bl, clazz, classArray);
            } else {
                Class clazz = MappedClasses.VK;
                boolean bl = true;
                String string4 = "potionDamage";
                MEntityPotion mEntityPotion = this;
                this.o = mEntityPotion.J(string4, bl, clazz);
            }
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                MSPacketMapChunkBulk.o("JSVoh");
            }
            return;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            Class[] classArray = new Class[]{};
            Class clazz = MappedClasses.VK;
            boolean bl = true;
            String string5 = "getPotion";
            MEntityPotion mEntityPotion = this;
            this.A = mEntityPotion.Y(string5, bl, clazz, classArray);
        }
        Class clazz = MappedClasses.VK;
        boolean bl = true;
        String string6 = "potionDamage";
        MEntityPotion mEntityPotion = this;
        this.o = mEntityPotion.J(string6, bl, clazz); 
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MSPacketMapChunkBulk.o("JSVoh");
        }
    }


    public static Object s(MEntityPotion mEntityPotion, Object object) {
        return mEntityPotion.d(object);
    }
}
