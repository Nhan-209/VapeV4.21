package gg.vape.reflect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import gg.vape.wrapper.impl.ForgeVersion;
import org.junit.Test;

public class Forge1206MappingsTest {
    @Test
    public void loadsCompleteForge1206Resources() throws Exception {
        int previousVersion = ForgeVersion.gameVersion;
        ForgeVersion.gameVersion = 50;
        try {
            Map<String, Set<String>> methods = new HashMap<String, Set<String>>();
            Map<String, Set<String>> methodsReversed =
                    new HashMap<String, Set<String>>();
            Map<String, Set<String>> fields = new HashMap<String, Set<String>>();
            Map<String, Set<String>> fieldsReversed =
                    new HashMap<String, Set<String>>();

            try (InputStream stream = requiredResource(
                    "/mappings/forge1206/methods.csv")) {
                MappingRegistry.parseCsv(stream, methods, methodsReversed);
            }
            try (InputStream stream = requiredResource(
                    "/mappings/forge1206/fields.csv")) {
                MappingRegistry.parseCsv(stream, fields, fieldsReversed);
            }

            assertTrue(methodsReversed.size() >= 36000);
            assertTrue(fieldsReversed.size() >= 35000);
            assertEquals(singleCsvValue("/mappings/forge1206/methods.csv",
                            "m_91087_"),
                    methodsReversed.get("m_91087_").iterator().next());
            assertEquals(singleCsvValue("/mappings/forge1206/fields.csv",
                            "f_91074_"),
                    fieldsReversed.get("f_91074_").iterator().next());
            assertTrue(MappingRegistry.METHODS.get("getInstance")
                    .contains("m_91087_"));
            assertTrue(methods.get("use").contains("m_7203_"));
            assertTrue(methods.get("getPlayerPOVHitResult")
                    .contains("m_41435_"));
            assertTrue(MappingRegistry.FIELDS.get("player")
                    .contains("f_91074_"));
        }
        finally {
            ForgeVersion.gameVersion = previousVersion;
        }
    }

    private static InputStream requiredResource(String name) {
        InputStream stream = Forge1206MappingsTest.class.getResourceAsStream(name);
        if (stream == null) {
            throw new AssertionError("Missing test resource: " + name);
        }
        return stream;
    }

    private static String singleCsvValue(String resource, String key)
            throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                requiredResource(resource), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length >= 2 && key.equals(columns[0])) {
                    return columns[1];
                }
            }
        }
        throw new AssertionError("Missing mapping key " + key);
    }
}
