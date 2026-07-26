package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import java.util.stream.Stream;

public class MResourceKeyRegistryLookupStreamBridge
extends Mapping {
    private final MappingMethod B;
    private static final String b = "listElements";

    public MResourceKeyRegistryLookupStreamBridge() {
        super(MappedClasses.Da);
        Class[] classArray = new Class[]{};
        Class<Stream> clazz = Stream.class;
        boolean bl = true;
        String string = b;
        MResourceKeyRegistryLookupStreamBridge mResourceKeyRegistryLookupStreamBridge = this;
        this.B = this.Y(string, bl, clazz, classArray);
    }

    public Stream<Object> a(Object object) {
        return (Stream)this.B.L(object, new Object[0]);
    }
}

