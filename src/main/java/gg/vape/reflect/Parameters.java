package gg.vape.reflect;

import gg.vape.reflect.ParameterResolver;

public class Parameters {
    public static boolean checkParameterTypes(Class<?>[] params, Class<?> returnType, String desc) {
        return ParameterResolver.matchesDescriptor(params, returnType, desc);
    }
}

