package gg.vape.utils;

import gg.vape.utils.RotationUtil;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Comparator;

public class EntityAngleComparator
implements Comparator<Entity> {
    @Override
    public int compare(Entity entity, Entity entity2) {
        return this.d(entity, entity2);
    }

    public int d(Entity entity, Entity entity2) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return Integer.compare(RotationUtil.a(entityPlayerSP, entity), RotationUtil.a(entityPlayerSP, entity2));
    }
}
