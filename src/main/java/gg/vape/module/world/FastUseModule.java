package gg.vape.module.world;

import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.world.fastuse.FastUseDelayedPacketMode;
import gg.vape.module.world.fastuse.FastUsePacketDelaySubModule;
import gg.vape.module.world.fastuse.LegacyFastUseCombatPacketQueueMode;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.SubModuleValue;
import gg.vape.wrapper.impl.ForgeVersion;

public class FastUseModule
extends Mod {
    private final SubModuleValue repelMode;
    private final ModeValue modeValue;
    private static final long MOD_ID = -7445823933619103425L;
    public final NumberValue j;
    private final SubModuleValue dynamicMode;
    private final SubModuleValue latencyMode = new FastUseDelayedPacketMode(this, "Latency").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();

    public FastUseModule() {
        super("FakeLag", (int)MOD_ID, Category.Y, "Simulates lag");
        this.dynamicMode = new FastUsePacketDelaySubModule(this, "Dynamic").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.repelMode = new LegacyFastUseCombatPacketQueueMode(this, "Repel").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.j = NumberValue.create((Object)this, "Delay", "#", "ms", 1.0, 100.0, 1000.0, 10.0);
        this.modeValue = ForgeVersion.MC_1_7_10.Y() ? ModeValue.create((Object)this, "Mode", this.latencyMode, this.latencyMode, this.dynamicMode, this.repelMode) : ModeValue.create((Object)this, "Mode", this.latencyMode, this.latencyMode, this.repelMode);
        this.addValue(this.modeValue, this.j);
        this.j.C(0);
    }

    @Override
    public String E() {
        return this.modeValue.c();
    }

    @Override
    public String r() {
        SubModuleValue subModuleValue = (SubModuleValue)this.modeValue.K();
        return ((Mod)subModuleValue.getInstance()).r();
    }
}

