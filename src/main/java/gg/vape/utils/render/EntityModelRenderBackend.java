package gg.vape.utils.render;

import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.ResourceLocation;
import java.awt.Color;

public interface EntityModelRenderBackend {
    public void y(EntityLivingBase var1);

    public void g(float var1, float var2, int var3, int var4, Color var5, float var6);

    public void y(ResourceLocation var1);

    public void F();
}

