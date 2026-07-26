package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import java.util.List;

public class MClientChunkProvider
extends Mapping {
    private MappingField T;
    private static final String b = "chunkListing";

    public MClientChunkProvider() {
        super(MappedClasses.le);
        Class<List> clazz = List.class;
        boolean bl = true;
        String string = b;
        MClientChunkProvider mClientChunkProvider = this;
        this.T = this.J(string, bl, clazz);
    }

    public List g(Object object) {
        return (List)this.T.getObject(object);
    }
}

