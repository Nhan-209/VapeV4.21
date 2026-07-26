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
    private final SubModuleValue s;
    private final ModeValue p;
    private static final long o = -7445823933619103425L;
    public final NumberValue j;
    private final SubModuleValue D;
    private final SubModuleValue A = new FastUseDelayedPacketMode(this, "Latency").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();

    public FastUseModule() {
        super("FakeLag", (int)o, Category.Y, "Simulates lag");
        this.D = new FastUsePacketDelaySubModule(this, "Dynamic").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.s = new LegacyFastUseCombatPacketQueueMode(this, "Repel").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.j = NumberValue.create((Object)this, "Delay", "#", "ms", 1.0, 100.0, 1000.0, 10.0);
        this.p = ForgeVersion.MC_1_7_10.Y() ? ModeValue.create((Object)this, "Mode", this.A, this.A, this.D, this.s) : ModeValue.create((Object)this, "Mode", this.A, this.A, this.s);
        this.addValue(this.p, this.j);
        this.j.C(0);
    }

    @Override
    public String E() {
        return this.p.c();
    }

    @Override
    public String r() {
        SubModuleValue subModuleValue = (SubModuleValue)this.p.K();
        return ((Mod)subModuleValue.getInstance()).r();
    }
}

