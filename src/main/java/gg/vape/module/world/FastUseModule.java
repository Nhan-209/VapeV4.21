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
    public final NumberValue delay;
    private final SubModuleValue dynamicMode;
    private final SubModuleValue latencyMode = new FastUseDelayedPacketMode(this, "Latency").getSelectionValue();

    public FastUseModule() {
        super("FakeLag", (int)MOD_ID, Category.Y, "Simulates lag");
        this.dynamicMode = new FastUsePacketDelaySubModule(this, "Dynamic").getSelectionValue();
        this.repelMode = new LegacyFastUseCombatPacketQueueMode(this, "Repel").getSelectionValue();
        this.delay = NumberValue.create((Object)this, "Delay", "#", "ms", 1.0, 100.0, 1000.0, 10.0);
        this.modeValue = ForgeVersion.MC_1_7_10.Y() ? ModeValue.create((Object)this, "Mode", this.latencyMode, this.latencyMode, this.dynamicMode, this.repelMode) : ModeValue.create((Object)this, "Mode", this.latencyMode, this.latencyMode, this.repelMode);
        this.addValue(this.modeValue, this.delay);
        this.delay.setMaximumFractionDigits(0);
    }

    @Override
    public String getSimpleSuffix() {
        return this.modeValue.getDisplayValue();
    }

    @Override
    public String getDetailedSuffix() {
        SubModuleValue subModuleValue = (SubModuleValue)this.modeValue.getValue();
        return ((Mod)subModuleValue.getInstance()).getDetailedSuffix();
    }
}

