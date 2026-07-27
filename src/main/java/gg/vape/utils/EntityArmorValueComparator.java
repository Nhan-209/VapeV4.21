package gg.vape.utils;

import gg.vape.mapping.MappedClasses;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionRegistry;
import java.util.Comparator;

public class EntityArmorValueComparator
implements Comparator<Entity> {
    @Override
    public int compare(Entity entity, Entity entity2) {
        return this.B(entity, entity2);
    }

    public int B(Entity entity, Entity entity2) {
        if (entity.isInstance(MappedClasses.Yl) && entity2.isInstance(MappedClasses.Yl)) {
            return Float.compare(this.m(entity), this.m(entity2));
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return Float.compare(entityPlayerSP.getDistanceToEntity(entity), entityPlayerSP.getDistanceToEntity(entity2));
    }


    private float m(Entity entity) {
        float f = 0.0f;
        EntityPlayer entityPlayer = new EntityPlayer(entity.getObject());
        if (entityPlayer.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt().isNotNull()) {
            PotionEffect potionEffect;
            f += ItemStackScoreUtil.I$src$F$dh3k81(entityPlayer.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt());
            if (entityPlayer.i(PotionRegistry.t) && (potionEffect = entityPlayer.b(PotionRegistry.t)).k() > 0) {
                f = (float)((double)f * (1.375 * (double)potionEffect.L()));
            }
        }
        return f;
    }
}
