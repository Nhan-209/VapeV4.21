package gg.vape.reflect;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/** Resolves MCP/SRG names against the obfuscated Minecraft 1.8.9 runtime. */
public final class Vanilla189Mappings {
    private static final String RESOURCE = "/mappings/vanilla189/joined.srg";
    private static final String[] RUNTIME_ANCHORS = {
            "net/minecraft/client/Minecraft",
            "net/minecraft/client/entity/EntityPlayerSP",
            "net/minecraft/world/World"
    };
    private static final MappingData DATA = loadMappings();

    private Vanilla189Mappings() {
    }

    public static String remapClassName(String sourceClassName) {
        return DATA.classNames.get(normalizeInternalName(sourceClassName));
    }

    public static Class<?> resolveClass(String sourceClassName,
                                        ClassLoader... preferredLoaders) {
        String normalizedName = normalizeInternalName(sourceClassName);
        if (normalizedName == null || normalizedName.isEmpty()) {
            return null;
        }

        Set<String> candidateNames = new LinkedHashSet<String>();
        String remappedName = DATA.classNames.get(normalizedName);
        if (remappedName != null) {
            candidateNames.add(remappedName);
        }
        candidateNames.add(normalizedName);

        Set<ClassLoader> loaders = candidateLoaders(preferredLoaders);
        for (String candidateName : candidateNames) {
            String binaryName = candidateName.replace('/', '.');
            for (ClassLoader loader : loaders) {
                try {
                    return Class.forName(binaryName, false, loader);
                }
                catch (ClassNotFoundException ignored) {
                    // Try the next loader/name pair.
                }
                catch (LinkageError ignored) {
                    // An incompatible class with the same name is not a valid match.
                }
            }
        }
        return null;
    }

    public static boolean isRuntimePresent(ClassLoader... preferredLoaders) {
        for (String anchor : RUNTIME_ANCHORS) {
            String runtimeName = DATA.classNames.get(anchor);
            if (runtimeName == null
                    || resolveRuntimeClass(runtimeName, preferredLoaders) == null) {
                return false;
            }
        }
        return true;
    }

    static String lookupFieldSrgName(Field field) {
        if (field == null) {
            return null;
        }
        return DATA.fieldNames.get(memberKey(
                field.getDeclaringClass().getName(), field.getName()));
    }

    static String lookupMethodSrgName(Method method) {
        if (method == null) {
            return null;
        }
        String ownerAndName = memberKey(
                method.getDeclaringClass().getName(), method.getName());
        String descriptor = ParameterResolver.methodDescriptor(
                method.getParameterTypes(), method.getReturnType());
        return DATA.methodNames.get(ownerAndName + ' ' + descriptor);
    }

    public static int getClassMappingCount() {
        return DATA.classNames.size();
    }

    public static int getFieldMappingCount() {
        return DATA.fieldNames.size();
    }

    public static int getMethodMappingCount() {
        return DATA.methodNames.size();
    }

    private static Class<?> resolveRuntimeClass(String runtimeInternalName,
                                                ClassLoader... preferredLoaders) {
        String binaryName = runtimeInternalName.replace('/', '.');
        for (ClassLoader loader : candidateLoaders(preferredLoaders)) {
            try {
                return Class.forName(binaryName, false, loader);
            }
            catch (ClassNotFoundException ignored) {
                // Try the next loader.
            }
            catch (LinkageError ignored) {
                // Treat an incompatible class as absent.
            }
        }
        return null;
    }

    private static Set<ClassLoader> candidateLoaders(ClassLoader... preferredLoaders) {
        Set<ClassLoader> loaders = new LinkedHashSet<ClassLoader>();
        if (preferredLoaders != null) {
            for (ClassLoader loader : preferredLoaders) {
                if (loader != null) {
                    loaders.add(loader);
                }
            }
        }
        ClassLoader contextLoader = Thread.currentThread().getContextClassLoader();
        if (contextLoader != null) {
            loaders.add(contextLoader);
        }
        ClassLoader mappingLoader = Vanilla189Mappings.class.getClassLoader();
        if (mappingLoader != null) {
            loaders.add(mappingLoader);
        }
        try {
            ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
            if (systemLoader != null) {
                loaders.add(systemLoader);
            }
        }
        catch (SecurityException ignored) {
            // The injected runtime loaders above are sufficient.
        }
        return loaders;
    }

    private static MappingData loadMappings() {
        Map<String, String> classNames = new LinkedHashMap<String, String>();
        Map<String, String> fieldNames = new LinkedHashMap<String, String>();
        Map<String, String> methodNames = new LinkedHashMap<String, String>();

        try (InputStream stream = Vanilla189Mappings.class.getResourceAsStream(RESOURCE)) {
            if (stream == null) {
                throw new FileNotFoundException("Missing mapping resource: " + RESOURCE);
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("CL: ")) {
                        String[] columns = line.split("\\s+");
                        if (columns.length == 3) {
                            classNames.put(columns[2], columns[1]);
                        }
                    } else if (line.startsWith("FD: ")) {
                        String[] columns = line.split("\\s+");
                        if (columns.length == 3) {
                            fieldNames.put(columns[1], simpleMemberName(columns[2]));
                        }
                    } else if (line.startsWith("MD: ")) {
                        String[] columns = line.split("\\s+");
                        if (columns.length == 5) {
                            methodNames.put(columns[1] + ' ' + columns[2],
                                    simpleMemberName(columns[3]));
                        }
                    }
                }
            }
        }
        catch (IOException exception) {
            throw new ExceptionInInitializerError(exception);
        }

        if (classNames.isEmpty() || fieldNames.isEmpty() || methodNames.isEmpty()) {
            throw new ExceptionInInitializerError(
                    "Incomplete Minecraft 1.8.9 mappings in " + RESOURCE);
        }
        return new MappingData(classNames, fieldNames, methodNames);
    }

    private static String normalizeInternalName(String className) {
        if (className == null) {
            return null;
        }
        String normalized = className.trim().replace('.', '/');
        if (normalized.length() > 2 && normalized.charAt(0) == 'L'
                && normalized.charAt(normalized.length() - 1) == ';') {
            normalized = normalized.substring(1, normalized.length() - 1);
        }
        return normalized;
    }

    private static String memberKey(String ownerName, String memberName) {
        return ownerName.replace('.', '/') + '/' + memberName;
    }

    private static String simpleMemberName(String qualifiedName) {
        int separator = qualifiedName.lastIndexOf('/');
        return separator < 0 ? qualifiedName : qualifiedName.substring(separator + 1);
    }

    private static final class MappingData {
        final Map<String, String> classNames;
        final Map<String, String> fieldNames;
        final Map<String, String> methodNames;

        MappingData(Map<String, String> classNames, Map<String, String> fieldNames,
                    Map<String, String> methodNames) {
            this.classNames = Collections.unmodifiableMap(classNames);
            this.fieldNames = Collections.unmodifiableMap(fieldNames);
            this.methodNames = Collections.unmodifiableMap(methodNames);
        }
    }
}
