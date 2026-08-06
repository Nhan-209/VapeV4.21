package gg.vape.mapping.runtime;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassNameRemapTableV50Test {
    @Test
    public void remapsLegacyActionResultHolderType() {
        ClassNameRemapTableV50 mappings = new ClassNameRemapTableV50();

        assertEquals("net/minecraft/world/InteractionResultHolder",
                mappings.lookupRemappedClassName(
                        "net/minecraft/util/ActionResult"));
    }
}
