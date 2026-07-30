package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MOrdering;
import gg.vape.ui.click.component.GuiComponent;
import java.util.Collection;

public class MItemAttributeModifiers
extends Mapping {
    public MappingMethod X;
    public MappingMethod u;
    public MappingMethod z;

    public boolean y(Object object, Object object2, Object object3) {
        return this.u.invokeBoolean(object, object2, object3);
    }

    public int y(Object object) {
        return this.X.invokeInt(object, new Object[0]);
    }

    public MItemAttributeModifiers() {
        this(MOrdering.y());
    }

    private MItemAttributeModifiers(String string) {
        super(MappedClasses.Yb);
        if (string != null) {
            Class[] classArray = new Class[]{};
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = false;
            String string2 = "size";
            MItemAttributeModifiers mItemAttributeModifiers = this;
            this.X = mItemAttributeModifiers.Y(string2, bl, clazz, classArray);
            Class[] classArray2 = new Class[]{};
            Class<Collection> clazz2 = Collection.class;
            boolean bl2 = false;
            String string3 = "values";
            MItemAttributeModifiers mItemAttributeModifiers2 = this;
            this.z = this.Y(string3, bl2, clazz2, classArray2);
            Class[] classArray3 = new Class[]{Object.class, Object.class};
            Class<Boolean> clazz3 = Boolean.TYPE;
            boolean bl3 = false;
            String string4 = "put";
            MItemAttributeModifiers mItemAttributeModifiers3 = this;
            this.u = this.Y(string4, bl3, clazz3, classArray3);
            return;
        }
        Class[] classArray = new Class[]{};
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = false;
        String string5 = "size";
        MItemAttributeModifiers mItemAttributeModifiers = this;
        this.X = mItemAttributeModifiers.Y(string5, bl, clazz, classArray); 
        Class[] classArray4 = new Class[]{};
        Class<Collection> clazz4 = Collection.class;
        boolean bl4 = false;
        String string6 = "values";
        MItemAttributeModifiers mItemAttributeModifiers4 = this;
        this.z = this.Y(string6, bl4, clazz4, classArray4);
        Class[] classArray5 = new Class[]{Object.class, Object.class};
        Class<Boolean> clazz5 = Boolean.TYPE;
        boolean bl5 = false;
        String string7 = "put";
        MItemAttributeModifiers mItemAttributeModifiers5 = this;
        this.u = this.Y(string7, bl5, clazz5, classArray5);
        GuiComponent.setLegacyComponentState(new GuiComponent[4]);
    }


    public Collection g(Object object) {
        return (Collection)this.z.invokeObject(object, new Object[0]);
    }
}

