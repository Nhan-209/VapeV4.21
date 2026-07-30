package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MResourceLocationKey;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MResourceLocationName
extends Mapping {
    private final MappingMethod r;
    private final MappingMethod x;

    public String L(Object object) {
        return (String)this.r.invokeObject(object, new Object[0]);
    }

    public String v(Object object, Object object2) {
        return (String)this.x.invokeObject(object, object2);
    }


    public MResourceLocationName() {
        this(MResourceLocationKey.A());
    }

    private MResourceLocationName(boolean bl) {
        super(MappedClasses.qA);
        if (bl) {
            GuiComponent.setLegacyComponentState(new GuiComponent[1]);
            this.x = null;
            if (ForgeVersion.MC_1_8_9.L()) {
                Class[] classArray = new Class[]{};
                Class<String> clazz = String.class;
                boolean bl2 = true;
                String string = "getCompleteReport";
                MResourceLocationName mResourceLocationName = this;
                mResourceLocationName.Y(string, bl2, clazz, classArray);
            }
            this.r = null;
            return;
        }
        if (MappedClasses.qJ != null) {
            Class[] classArray = new Class[]{MappedClasses.qJ};
            Class<String> clazz = String.class;
            boolean bl3 = true;
            String string = "getFriendlyReport";
            MResourceLocationName mResourceLocationName = this;
            this.x = mResourceLocationName.Y(string, bl3, clazz, classArray);
        } else {
            this.x = null;
        }
        if (ForgeVersion.MC_1_8_9.L()) {
            Class[] classArray = new Class[]{};
            Class<String> clazz = String.class;
            boolean bl4 = true;
            String string = "getCompleteReport";
            MResourceLocationName mResourceLocationName = this;
            this.r = mResourceLocationName.Y(string, bl4, clazz, classArray);
        } else {
            this.r = null;
        }
    }
}
