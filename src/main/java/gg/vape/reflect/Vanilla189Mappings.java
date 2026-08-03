package gg.vape.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/** Resolves MCP/SRG names against the obfuscated Minecraft 1.8.9 runtime. */
public final class Vanilla189Mappings {
    private static final VanillaSrgMappings MAPPINGS = new VanillaSrgMappings(
            "Minecraft 1.8.9",
            "/mappings/vanilla189/joined.srg",
            "ave",
            "A",
            "S",
            "bew",
            "adm");

    private Vanilla189Mappings() {
    }

    public static String remapClassName(String sourceClassName) {
        return MAPPINGS.remapClassName(sourceClassName);
    }

    public static Class<?> resolveClass(String sourceClassName,
                                        ClassLoader... preferredLoaders) {
        return MAPPINGS.resolveClass(sourceClassName, preferredLoaders);
    }

    public static boolean isRuntimePresent(ClassLoader... preferredLoaders) {
        if (MAPPINGS.isRuntimePresent(preferredLoaders)) {
            return true;
        }

        Class<?> minecraftClass = MAPPINGS.resolveClass(
                "net/minecraft/client/Minecraft", preferredLoaders);
        Class<?> worldRendererClass = MAPPINGS.resolveClass(
                "net/minecraft/client/renderer/WorldRenderer", preferredLoaders);
        Class<?> blockPosClass = MAPPINGS.resolveClass(
                "net/minecraft/util/BlockPos", preferredLoaders);
        Class<?> modernBlockPosClass = MAPPINGS.resolveClass(
                "net/minecraft/util/math/BlockPos", preferredLoaders);
        if (minecraftClass == null || worldRendererClass == null
                || blockPosClass == null || modernBlockPosClass != null) {
            return false;
        }

        ClassLoader gameLoader = minecraftClass.getClassLoader();
        if (worldRendererClass.getClassLoader() != gameLoader
                || blockPosClass.getClassLoader() != gameLoader) {
            return false;
        }

        try {
            Method getter = minecraftClass.getDeclaredMethod("getMinecraft");
            Field instance = minecraftClass.getDeclaredField("theMinecraft");
            return Modifier.isStatic(getter.getModifiers())
                    && getter.getReturnType() == minecraftClass
                    && Modifier.isStatic(instance.getModifiers())
                    && instance.getType() == minecraftClass;
        }
        catch (NoSuchMethodException ignored) {
            return false;
        }
        catch (NoSuchFieldException ignored) {
            return false;
        }
        catch (SecurityException ignored) {
            return false;
        }
        catch (LinkageError ignored) {
            return false;
        }
    }

    static String lookupFieldSrgName(Field field) {
        return MAPPINGS.lookupFieldSrgName(field);
    }

    static String lookupMethodSrgName(Method method) {
        return MAPPINGS.lookupMethodSrgName(method);
    }

    public static int getClassMappingCount() {
        return MAPPINGS.getClassMappingCount();
    }

    public static int getFieldMappingCount() {
        return MAPPINGS.getFieldMappingCount();
    }

    public static int getMethodMappingCount() {
        return MAPPINGS.getMethodMappingCount();
    }
}
