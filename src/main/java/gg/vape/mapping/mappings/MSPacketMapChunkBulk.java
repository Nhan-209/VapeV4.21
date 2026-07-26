package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;

public class MSPacketMapChunkBulk
extends Mapping {
    private static String e;

    public MSPacketMapChunkBulk() {
        super(MappedClasses.uv);
    }

    public static String java_lang_String_E() {
        return e;
    }

    public static void o(String string) {
        e = string;
    }

    static {
        if (MSPacketMapChunkBulk.java_lang_String_E() == null) {
            MSPacketMapChunkBulk.o("ih9zKb");
        }
    }

    public static /* synthetic */ String E$src$Ljava_lang_String_$1dqes8v() {
        return MSPacketMapChunkBulk.java_lang_String_E();
    }
}

