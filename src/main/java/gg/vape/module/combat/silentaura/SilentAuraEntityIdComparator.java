package gg.vape.module.combat.silentaura;

import gg.vape.module.combat.SilentAura;
import gg.vape.wrapper.impl.EntityLivingBase;
import java.util.Comparator;

public class SilentAuraEntityIdComparator
implements Comparator {
    final SilentAura silentAura;

    public int compareByEntityId(EntityLivingBase entityLivingBase, EntityLivingBase entityLivingBase2) {
        return Integer.compare(entityLivingBase.c$src$I$15a9iwo(), entityLivingBase2.c$src$I$15a9iwo());
    }

    @Override
    public int compare(Object first, Object second) {
        return this.compareByEntityId((EntityLivingBase)first, (EntityLivingBase)second);
    }

    public SilentAuraEntityIdComparator(SilentAura silentAura) {
        this.silentAura = silentAura;
    }
}
