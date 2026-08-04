package gg.vape.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Assume;
import org.junit.Test;

/** Exercises the installed Badlion artifact when one is available locally. */
public class Badlion189MappingsIntegrationTest {
    @Test
    public void resolvesInstalledBadlion189Artifact() throws Exception {
        File badlionJar = locateBadlionJar();
        Assume.assumeTrue("Badlion 1.8.9 is not installed", badlionJar.isFile());

        List<URL> classPath = new ArrayList<URL>();
        classPath.add(badlionJar.toURI().toURL());
        File minecraftDirectory = locateMinecraftDirectory(badlionJar);
        Path libraries = new File(minecraftDirectory, "libraries").toPath();
        if (Files.isDirectory(libraries)) {
            try (Stream<Path> paths = Files.walk(libraries)) {
                paths.filter(path -> path.toString().endsWith(".jar"))
                        .forEach(path -> addUrl(classPath, path));
            }
        }

        try (URLClassLoader loader = new URLClassLoader(
                classPath.toArray(new URL[classPath.size()]), null)) {
            assertTrue(Badlion189Mappings.isRuntimePresent(loader));
            Class<?> minecraftClass = Badlion189Mappings.resolveClass(
                    "net/minecraft/client/Minecraft", loader);
            assertEquals("ave", minecraftClass.getName());

            Field instance = minecraftClass.getDeclaredField("S");
            Method getter = minecraftClass.getDeclaredMethod("A");
            Method scheduler = minecraftClass.getDeclaredMethod(
                    "a", Runnable.class);
            assertEquals("field_71432_P",
                    Badlion189Mappings.lookupFieldSrgName(instance));
            assertEquals("func_71410_x",
                    Badlion189Mappings.lookupMethodSrgName(getter));
            assertEquals("func_152344_a",
                    Badlion189Mappings.lookupMethodSrgName(scheduler));
        }
    }

    private static File locateBadlionJar() {
        String configuredPath = System.getProperty("badlion189.jar");
        if (configuredPath != null && !configuredPath.trim().isEmpty()) {
            return new File(configuredPath);
        }
        String appData = System.getenv("APPDATA");
        if (appData == null) {
            return new File("");
        }
        return new File(appData,
                ".minecraft/versions/BLClient/PRODUCTION4/1.8.9/BLClient.jar");
    }

    private static File locateMinecraftDirectory(File badlionJar) {
        File current = badlionJar;
        while (current != null && !"versions".equals(current.getName())) {
            current = current.getParentFile();
        }
        return current == null ? badlionJar.getParentFile()
                : current.getParentFile();
    }

    private static void addUrl(List<URL> classPath, Path path) {
        try {
            classPath.add(path.toUri().toURL());
        }
        catch (Exception exception) {
            throw new IllegalStateException(
                    "Unable to add Badlion library " + path, exception);
        }
    }
}
