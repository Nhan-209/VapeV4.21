package gg.vape.mapping;

import gg.vape.mapping.MappingClassBytecodeResolver;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class BytecodeClassPool
extends ClassPool {
    private final MappingClassBytecodeResolver W;

    private static Exception a(Exception exception) {
        return exception;
    }

    @Override
    public CtClass get(String string) throws NotFoundException {
        try {
            return super.get(string);
        }
        catch (NotFoundException notFoundException) {
            if (this.N(string)) {
                return super.get(string);
            }
            throw notFoundException;
        }
    }

    public BytecodeClassPool(MappingClassBytecodeResolver mappingClassBytecodeResolver) {
        super(true);
        this.W = mappingClassBytecodeResolver;
    }

    @Override
    public CtClass getOrNull(String string) {
        CtClass ctClass = super.getOrNull(string);
        if (ctClass == null && this.N(string)) {
            ctClass = super.getOrNull(string);
        }
        return ctClass;
    }

    private boolean N(String string) {
        byte[] byArray = this.W.y(string);
        if (byArray != null) {
            this.insertClassPath(new ByteArrayClassPath(string, byArray));
            return true;
        }
        return false;
    }
}
