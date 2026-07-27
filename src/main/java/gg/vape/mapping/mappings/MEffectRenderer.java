package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MSoundAwareEntityFX;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.List;

public class MEffectRenderer
extends Mapping {
    private MappingField v;
    private final MappingField F;

    public MEffectRenderer() {
        this(MSoundAwareEntityFX.t());
    }

    private MEffectRenderer(String[] stringArray) {
        super(MappedClasses.qv);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_7_10.Y()) {
                Class<List> clazz = List.class;
                boolean bl = true;
                String string = "particleEmitters";
                MEffectRenderer mEffectRenderer = this;
                this.v = mEffectRenderer.J(string, bl, clazz);
                Class<List> clazz2 = List.class;
                String string2 = "fxLayers";
                MEffectRenderer mEffectRenderer2 = this;
                this.F = this.T(string2, clazz2).J(2).z();
            } else {
                Class<List> clazz = List.class;
                String string = "fxLayers";
                MEffectRenderer mEffectRenderer = this;
                this.F = mEffectRenderer.T(string, clazz).J(1).z();
            }
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                MSoundAwareEntityFX.Z(new String[5]);
            }
            return;
        }
        Class<List> clazz = List.class;
        String string = "fxLayers";
        MEffectRenderer mEffectRenderer = this;
        this.F = mEffectRenderer.T(string, clazz).J(1).z(); 
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MSoundAwareEntityFX.Z(new String[5]);
        }
    }

    private List[] V(Object object) {
        return (List[])this.F.getObjectArray(object);
    }

    public static List[] z(MEffectRenderer mEffectRenderer, Object object) {
        return mEffectRenderer.V(object);
    }


    private List[][] s(Object object) {
        if (object == null || this.F == null || this.F.x()) {
            return new List[0][];
        }
        return (List[][])this.F.getObjectArray(object);
    }

    public List A(Object object) {
        return (List)this.v.getObject(object);
    }

    public static List[][] N(MEffectRenderer mEffectRenderer, Object object) {
        return mEffectRenderer.s(object);
    }
}

