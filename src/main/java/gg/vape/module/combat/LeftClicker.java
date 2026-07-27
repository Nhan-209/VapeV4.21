package gg.vape.module.combat;

import gg.vape.Vape;
import gg.vape.click.ClickButton;
import gg.vape.click.ClickEngine;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.InputEventDispatcher;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.combat.ClickerMod;
import gg.vape.module.combat.WTap;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.Animations;
import gg.vape.rotation.RotationManager;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import java.util.Arrays;

public class LeftClicker
extends ClickerMod {
    private boolean wasClicking = false;
    private final BooleanValue jitter;
    private final ModeOption normalMode;
    private final ModeOption extraPlusMode;
    private final LimitValue blockBreakItems;
    private final ModeOption extraMode;
    private final RandomValue cps;
    private final BooleanValue breakBlocks;
    private final BooleanValue triggerMode;
    private final LimitValue itemWhitelist;
    private final BooleanValue breakBlocksWhitelist;
    private final BooleanValue limitItems;
    private final BooleanValue holdToClick = BooleanValue.create(this, "Hold to click", true);
    private final TimerUtil breakBlockTimer;
    private boolean blocked = false;
    private final RandomValue breakBlocksDelay;
    private final ModeValue randomization;

    @Override
    public boolean K(ClickEngine clickEngine, EntityPlayerSP entityPlayerSP) {
        boolean bl;
        Animations animations = Vape.INSTANCE.getModManager().getMod(Animations.class);
        boolean bl2 = Packet.A();
        if (bl2) {
            boolean bl3;
            Animations animations2 = animations;
            boolean bl4 = bl3 = animations2.c();
            GuiComponent.D(new GuiComponent[2]);
            return bl4;
        }
        Animations animations3 = animations;
        boolean bl5 = animations3 != null && (bl = animations.c());
        return bl5;
    }

    @Override
    public boolean z() {
        return this.triggerMode.L();
    }

    private boolean computeBlocked() {
        if (!ClientSettings.fW.v()) {
            return true;
        }
        if (!InputEventDispatcher.getInstance().getFocusState().isFocused()) {
            return true;
        }
        if (SharedModuleControlClaims.h.I()) {
            return true;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return true;
        }
        if (!this.shouldAllowClick(entityPlayerSP)) {
            if (gg.vape.config.ClientSettings.M()) {
                if (!Minecraft.gameSettings().F().isKeyDown()) {
                    // empty if block
                }
                this.S = true;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean h$src$Z$qo2e1d() {
        WTap wTap = Vape.INSTANCE.getModManager().getMod(WTap.class);
        if (wTap == null) {
            return false;
        }
        return wTap.r$src$Z$14eylz9() && wTap.o$src$Z$1nxkzhj();
    }

    public boolean shouldAllowClick(EntityPlayerSP entityPlayerSP) {
        if (!gg.vape.config.ClientSettings.M()) {
            this.breakBlockTimer.reset();
        }
        if (this.breakBlocks.L().booleanValue() && this.breakBlockTimer.hasTimeElapsed((long)this.breakBlocksDelay.B())) {
            if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
                return true;
            }
            if (this.breakBlocksWhitelist.L().booleanValue() && !this.blockBreakItems.A(entityPlayerSP.getHeldItemHand())) {
                return true;
            }
            RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            if (rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
                return false;
            }
            this.breakBlockTimer.reset();
        }
        return true;
    }


    @Override
    public boolean C() {
        return this.blocked;
    }

    @EventHandler
    public void onTick$src$V$5jszx7(EventPreTick eventPreTick) {
        this.blocked = this.computeBlocked();
        if (this.blocked && InputEventDispatcher.getInstance().getFocusState().isFocused() && ClientSettings.fW.P && Minecraft.currentScreen().isNull() && this.s.G() && !Minecraft.gameSettings().F().isKeyDown() && !this.wasClicking) {
            this.wasClicking = true;
            this.s.g();
        } else {
            this.wasClicking = false;
        }
    }

    @Override
    public String r() {
        return this.cps.c() + "cps";
    }

    public LeftClicker() {
        super("AutoClicker");
        this.normalMode = new ModeOption("Normal");
        this.extraMode = new ModeOption("Extra");
        this.extraPlusMode = new ModeOption("Extra+");
        this.randomization = ModeValue.create((Object)this, "Randomization", this.extraMode, this.normalMode, this.extraMode, this.extraPlusMode);
        this.jitter = BooleanValue.create(this, "Jitter", false);
        this.cps = RandomValue.create(this, "CPS", "#.#", "", 1.0, 6.0, 13.0, 20.0);
        this.limitItems = BooleanValue.create(this, "Limit items", false);
        this.itemWhitelist = LimitValue.N(this, "autoclicker-allowed-items", "Item whitelist", LimitValue.r, new ItemLimitData("swords"));
        this.triggerMode = BooleanValue.create(this, "Trigger mode", false, "Only clicks while hovering an entity");
        this.breakBlocks = BooleanValue.create(this, "Break blocks", false);
        this.breakBlocksDelay = RandomValue.create(this, "Break blocks delay", "#", "", 0.0, 0.0, 10.0, 2000.0);
        this.breakBlocksWhitelist = BooleanValue.create(this, "Break blocks whitelist", false);
        this.blockBreakItems = LimitValue.n(this, "autoclicker-blockbreak-items", "Items", LimitValue.r, Arrays.asList(new ItemLimitData("pickaxes"), new ItemLimitData("shovels")));
        this.breakBlockTimer = new TimerUtil();
        this.limitItems.K(this.itemWhitelist);
        this.limitItems.l(this.itemWhitelist);
        this.breakBlocks.K(this.breakBlocksDelay, this.breakBlocksWhitelist);
        this.breakBlocksWhitelist.l(this.blockBreakItems);
        this.breakBlocksWhitelist.K(this.blockBreakItems);
        this.addValue(this.holdToClick, this.triggerMode, this.breakBlocks, this.breakBlocksDelay, this.breakBlocksWhitelist, this.blockBreakItems, this.cps, this.randomization, this.jitter, this.limitItems, this.itemWhitelist);
        ClickEngine clickEngine = new ClickEngine(ClickButton.LEFT, this.cps, this.limitItems, this.itemWhitelist, this.holdToClick, this.randomization, this.jitter);
        this.F(clickEngine);
        this.cps.V(0);
    }
}

