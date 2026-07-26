package gg.vape.utils;

import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Comparator;

public class EntityDistanceComparator
implements Comparator<Entity> {
    @Override
    public int compare(Entity entity, Entity entity2) {
        return this.e(entity, entity2);
    }

    public int e(Entity entity, Entity entity2) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return Float.compare(entityPlayerSP.getDistanceToEntity(entity), entityPlayerSP.getDistanceToEntity(entity2));
    }
}
