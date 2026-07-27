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
    private final NumberValue startDelay;
    private final ModeOption modeExtraPlus;
    private final LimitValue itemWhitelist;
    private final BooleanValue useItemWhitelist;
    private final BooleanValue jitter;
    private final ModeOption modeNormal;
    private final BooleanValue holdToClick;
    private final ModeOption modeExtra;
    private final ModeValue randomization;
    private final RandomValue cps = RandomValue.create(this, "CPS", "#.#", "", 1.0, 7.0, 13.0, 20.0);

    @Override
    public boolean d(EntityPlayerSP entityPlayerSP) {
        if (SharedModuleControlClaims.x.v$src$Z$1r7ksy2()) {
            return true;
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            ItemStack itemStack = entityPlayerSP.i(EnumHand.M());
            ItemStack itemStack2 = entityPlayerSP.i(EnumHand.p());
            if (!this.itemWhitelist.A(itemStack) && this.itemWhitelist.A(itemStack2) && this.isUsableItem(itemStack, entityPlayerSP)) {
                return true;
            }
        }
        return false;
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public double U() {
        return (Double)this.startDelay.K();
    }

    private boolean isUsableItem(ItemStack itemStack, EntityPlayerSP entityPlayerSP) {
        return itemStack.isNotNull() && itemStack.getItem().isNotNull() && itemStack.getItem().I(itemStack, entityPlayerSP) > 0;
    }

    @Override
    public String r() {
        return this.cps.c() + "cps";
    }

    public RightClicker() {
        super("RightClicker");
        this.holdToClick = BooleanValue.create(this, "Hold to Click", true);
        this.itemWhitelist = LimitValue.N(this, "autoclicker-allowed-items", "Item whitelist", LimitValue.r, new ItemLimitData("blocks")).F(true);
        this.jitter = BooleanValue.create(this, "Jitter", false);
        this.useItemWhitelist = BooleanValue.create(this, "Use item whitelist", false);
        this.modeExtra = new ModeOption("Extra");
        this.modeExtraPlus = new ModeOption("Extra+");
        this.modeNormal = new ModeOption("Normal");
        this.randomization = ModeValue.create((Object)this, "Randomization", this.modeExtraPlus, this.modeNormal, this.modeExtra, this.modeExtraPlus);
        this.startDelay = NumberValue.create(this, "Start Delay", "#.#", "", 0.0, 0.0, 1000.0);
        this.useItemWhitelist.K(this.itemWhitelist);
        this.addValue(this.cps, this.startDelay, this.randomization, this.jitter, this.useItemWhitelist, this.itemWhitelist);
        ClickEngine clickEngine = new ClickEngine(ClickButton.RIGHT, this.cps, this.useItemWhitelist, this.itemWhitelist, this.holdToClick, this.randomization, this.jitter);
        this.F(clickEngine);
        this.cps.V(0);
    }
}

