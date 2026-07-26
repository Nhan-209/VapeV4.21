package gg.vape.asm.transform.impl;

import gg.vape.Vape;
import gg.vape.asm.helper.IndexedLocal;
import gg.vape.asm.transform.ClassTransformer;
import gg.vape.event.impl.EventPostAttack;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.PlayerControllerMPEventMappingTask;
import gg.vape.runtime.ObfuscatedRuntimeException;

public class PlayerControllerMPTransformer
extends ClassTransformer {
    private static int D;

    static {
        if (PlayerControllerMPTransformer.c$src$I$1ezlko9() != 0) {
            PlayerControllerMPTransformer.w(19);
        }
    }

    public static int A() {
        return D;
    }

    public PlayerControllerMPTransformer() {
        super(MappedClasses.ld);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void w(int n) {
        D = n;
    }

    @Override
    public void c() {
        if (!PlayerControllerMPEventMappingTask.U) {
            return;
        }
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().hj.i;
        this.injectEventAtEntry(mappingMethod, EventPreAttack.class, new IndexedLocal(2).setDescriptorClass(Object.class));
        this.injectEventAtExit(mappingMethod, EventPostAttack.class, new IndexedLocal(2).setDescriptorClass(Object.class));
    }

    public static int c$src$I$1ezlko9() {
        int n = PlayerControllerMPTransformer.A();
        if (n == 0) {
            return 52;
        }
        return 0;
    }
}

