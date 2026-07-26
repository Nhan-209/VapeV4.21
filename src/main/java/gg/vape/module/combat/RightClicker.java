package gg.vape.module.combat;

import gg.vape.click.ClickButton;
import gg.vape.click.ClickEngine;
import gg.vape.module.combat.ClickerMod;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;

public class RightClicker
extends ClickerMod {
    private final NumberValue Y;
    private final ModeOption j;
    private final LimitValue p;
    private final BooleanValue U;
    private final BooleanValue H;
    private final ModeOption K;
    private final BooleanValue D;
    private final ModeOption a;
    private final ModeValue C;
    private final RandomValue I = RandomValue.create(this, "CPS", "#.#", "", 1.0, 7.0, 13.0, 20.0);

    @Override
    public boolean d(EntityPlayerSP entityPlayerSP) {
        if (SharedModuleControlClaims.x.v$src$Z$1r7ksy2()) {
            return true;
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            ItemStack itemStack = entityPlayerSP.i(EnumHand.M());
            ItemStack itemStack2 = entityPlayerSP.i(EnumHand.p());
            if (!this.p.A(itemStack) && this.p.A(itemStack2) && this.O(itemStack, entityPlayerSP)) {
                return true;
            }
        }
        return false;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double U() {
        return (Double)this.Y.K();
    }

    private boolean O(ItemStack itemStack, EntityPlayerSP entityPlayerSP) {
        return itemStack.isNotNull() && itemStack.getItem().isNotNull() && itemStack.getItem().I(itemStack, entityPlayerSP) > 0;
    }

    @Override
    public String r() {
        return this.I.c() + "cps";
    }

    public RightClicker() {
        super("RightClicker");
        this.D = BooleanValue.create(this, "Hold to Click", true);
        this.p = LimitValue.N(this, "autoclicker-allowed-items", "Item whitelist", LimitValue.r, new ItemLimitData("blocks")).F(true);
        this.H = BooleanValue.create(this, "Jitter", false);
        this.U = BooleanValue.create(this, "Use item whitelist", false);
        this.a = new ModeOption("Extra");
        this.j = new ModeOption("Extra+");
        this.K = new ModeOption("Normal");
        this.C = ModeValue.create((Object)this, "Randomization", this.j, this.K, this.a, this.j);
        this.Y = NumberValue.create(this, "Start Delay", "#.#", "", 0.0, 0.0, 1000.0);
        this.U.K(this.p);
        this.addValue(this.I, this.Y, this.C, this.H, this.U, this.p);
        ClickEngine clickEngine = new ClickEngine(ClickButton.RIGHT, this.I, this.U, this.p, this.D, this.C, this.H);
        this.F(clickEngine);
        this.I.V(0);
    }
}

