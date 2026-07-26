package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.RefillInventoryState;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import org.jetbrains.annotations.Nullable;

public class RefillModule
extends Mod
implements RefillInventoryState {
    private static final String k = "Test Module";

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public RefillModule() {
        super(k, -1, Category.Y);
    }

    @Nullable
    public static EnumHand x() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        EnumHand enumHand = EnumHand.M();
        if (entityPlayerSP.isNull()) {
            return enumHand;
        }
        RayTraceResult rayTraceResult = Minecraft.p$src$Lgg_vape_wrapper_impl_RayTraceResult_$5rw6n0();
        if (rayTraceResult.isNull()) {
            return enumHand;
        }
        RayTraceResult_type rayTraceResult_type = rayTraceResult.getTypeOfHit();
        if (rayTraceResult_type.equals(RayTraceResult_type.miss())) {
            return enumHand;
        }
        return null;
    }
}

