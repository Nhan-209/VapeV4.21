package gg.vape.utils.render;

import gg.vape.wrapper.impl.EntityRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.TickEvent_Phase;

public class Post117RenderPhaseCompat {

    public static void applyRenderPhaseCompat() {
        if (ForgeVersion.MC_1_21_6.v()) {
            return;
        }
        EntityRenderer entityRenderer = Minecraft.m$src$Lgg_vape_wrapper_impl_EntityRenderer_$13begmf();
        entityRenderer.V().P(entityRenderer.h().v(TickEvent_Phase.F()));
    }
}

