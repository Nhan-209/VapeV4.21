package gg.vape.utils;

import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Comparator;

public class EntityEquipmentValueComparator
implements Comparator<Entity> {
    @Override
    public int compare(Entity entity, Entity entity2) {
        return this.q(entity, entity2);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int q(Entity entity, Entity entity2) {
        if (entity.isInstance(MappedClasses.Yl) && entity2.isInstance(MappedClasses.Yl)) {
            return Double.compare(this.O(entity), this.O(entity2));
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return Float.compare(entityPlayerSP.getDistanceToEntity(entity), entityPlayerSP.getDistanceToEntity(entity2));
    }

    private double O(Entity entity) {
        double d = 0.0;
        for (Object object : new EntityPlayer(entity.getObject()).V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().i()) {
            ItemStack itemStack = new ItemStack(object);
            d += ItemStackScoreUtil.L(itemStack);
        }
        return d;
    }
}
