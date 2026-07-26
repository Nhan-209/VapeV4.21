package gg.vape.asm.transform;

import org.objectweb.asm.ClassWriter;

public class TransformClassWriter
extends ClassWriter {
    public TransformClassWriter(int n) {
        super(n);
    }

    @Override
    protected String getCommonSuperClass(String string, String string2) {
        return super.getCommonSuperClass(string, string2);
    }
}

