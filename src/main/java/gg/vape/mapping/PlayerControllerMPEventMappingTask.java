package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventBedBreakerUpdate;
import gg.vape.event.impl.EventPlayerUseItem;
import gg.vape.event.impl.EventPostAttack;
import gg.vape.event.impl.EventPreAttack;
import gg.vape.event.impl.EventWindowClick;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.PlayerUseItemCallback;
import gg.vape.wrapper.impl.ForgeVersion;

public class PlayerControllerMPEventMappingTask
extends JavassistMappingTask {
    public static final boolean U = ForgeVersion.MC_1_16_5.d() && (Vape.INSTANCE.isNativeAvailable() || Vape.INSTANCE.isVanillaMinecraftPresent());

    public PlayerControllerMPEventMappingTask() {
        super(MappedClasses.ld);
    }

    @Override
    public void c() {
        Object object;
        MappingMethod mappingMethod = Vape.INSTANCE.getMappings().hj.c;
        if (ForgeVersion.MC_1_12_2.v()) {
            this.O(mappingMethod, EventPlayerUseItem.class, "$3", "false");
        } else if (ForgeVersion.MC_1_12_2.d()) {
            object = ForgeVersion.MC_1_20_6.d() ? "$1, $2" : "$1, $3";
            this.O(mappingMethod, PlayerUseItemCallback.class, (String)object, MappedClasses.zr.getName() + "." + Vape.INSTANCE.getMappings().RD.B.J());
        }
        this.c(Vape.INSTANCE.getMappings().hj.L, EventBedBreakerUpdate.class, "");
        if (!U) {
            object = Vape.INSTANCE.getMappings().hj.i;
            this.c((MappingMethod)object, EventPreAttack.class, "$2");
            this.k((MappingMethod)object, EventPostAttack.class, "$2");
        }
        this.O(Vape.INSTANCE.getMappings().hj.I, EventWindowClick.class, "$0", "null");
    }

}

