package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MRegistryAccess;
import gg.vape.ui.click.component.GuiComponent;
import java.util.Optional;

public class MRegistryLookup
extends Mapping {
    private final MappingMethod T;
    private final MappingMethod e;
    private final MappingMethod u;

    public MRegistryLookup() {
        super(MappedClasses.A);
        Class[] classArray = new Class[]{MappedClasses.qB};
        Class clazz = MappedClasses.u2;
        boolean bl = true;
        String string = "lookupOrThrow";
        MRegistryLookup mRegistryLookup = this;
        this.T = this.Y(string, bl, clazz, classArray);
        Class[] classArray2 = new Class[]{MappedClasses.qB};
        Class<Optional> clazz2 = Optional.class;
        boolean bl2 = true;
        String string2 = "lookup";
        MRegistryLookup mRegistryLookup2 = this;
        this.u = this.Y(string2, bl2, clazz2, classArray2);
        Class[] classArray3 = new Class[]{MappedClasses.qB};
        Class<Optional> clazz3 = Optional.class;
        boolean bl3 = true;
        String string3 = "get";
        MRegistryLookup mRegistryLookup3 = this;
        this.e = this.Y(string3, bl3, clazz3, classArray3);
        if (MRegistryAccess.d() != null) {
            return;
        }
        GuiComponent.D(new GuiComponent[3]);
    }

    public Optional<Object> f(Object object, Object object2) {
        return (Optional)this.u.L(object, object2);
    }


    public Optional<Object> W(Object object, Object object2) {
        return (Optional)this.e.L(object, object2);
    }

    public Object Y(Object object, Object object2) {
        return this.T.L(object, object2);
    }
}

