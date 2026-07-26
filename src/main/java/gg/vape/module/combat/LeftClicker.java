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
import gg.vape.runtime.ObfuscatedRuntimeException;
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
    private boolean C = false;
    private final BooleanValue k;
    private final ModeOption D;
    private final ModeOption j;
    private final LimitValue P;
    private final ModeOption v;
    private final RandomValue V;
    private final BooleanValue a;
    private final BooleanValue t;
    private final LimitValue Y;
    private final BooleanValue A;
    private final BooleanValue H;
    private final BooleanValue r = BooleanValue.create(this, "Hold to click", true);
    private final TimerUtil p;
    private boolean K = false;
    private final RandomValue c;
    private final ModeValue U;

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
        return this.t.L();
    }

    private boolean v() {
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
        if (!this.u(entityPlayerSP)) {
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

    public boolean u(EntityPlayerSP entityPlayerSP) {
        if (!gg.vape.config.ClientSettings.M()) {
            this.p.reset();
        }
        if (this.a.L().booleanValue() && this.p.hasTimeElapsed((long)this.c.B())) {
            if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
                return true;
            }
            if (this.A.L().booleanValue() && !this.P.A(entityPlayerSP.getHeldItemHand())) {
                return true;
            }
            RayTraceResult rayTraceResult = RotationManager.b.D$src$Lgg_vape_wrapper_impl_RayTraceResult_$10z02ic();
            if (rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.block())) {
                return false;
            }
            this.p.reset();
        }
        return true;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public boolean C() {
        return this.K;
    }

    @EventHandler
    public void onTick$src$V$5jszx7(EventPreTick eventPreTick) {
        this.K = this.v();
        if (this.K && InputEventDispatcher.getInstance().getFocusState().isFocused() && ClientSettings.fW.P && Minecraft.currentScreen().isNull() && this.s.G() && !Minecraft.gameSettings().F().isKeyDown() && !this.C) {
            this.C = true;
            this.s.g();
        } else {
            this.C = false;
        }
    }

    @Override
    public String r() {
        return this.V.c() + "cps";
    }

    public LeftClicker() {
        super("AutoClicker");
        this.D = new ModeOption("Normal");
        this.v = new ModeOption("Extra");
        this.j = new ModeOption("Extra+");
        this.U = ModeValue.create((Object)this, "Randomization", this.v, this.D, this.v, this.j);
        this.k = BooleanValue.create(this, "Jitter", false);
        this.V = RandomValue.create(this, "CPS", "#.#", "", 1.0, 6.0, 13.0, 20.0);
        this.H = BooleanValue.create(this, "Limit items", false);
        this.Y = LimitValue.N(this, "autoclicker-allowed-items", "Item whitelist", LimitValue.r, new ItemLimitData("swords"));
        this.t = BooleanValue.create(this, "Trigger mode", false, "Only clicks while hovering an entity");
        this.a = BooleanValue.create(this, "Break blocks", false);
        this.c = RandomValue.create(this, "Break blocks delay", "#", "", 0.0, 0.0, 10.0, 2000.0);
        this.A = BooleanValue.create(this, "Break blocks whitelist", false);
        this.P = LimitValue.n(this, "autoclicker-blockbreak-items", "Items", LimitValue.r, Arrays.asList(new ItemLimitData("pickaxes"), new ItemLimitData("shovels")));
        this.p = new TimerUtil();
        this.H.K(this.Y);
        this.H.l(this.Y);
        this.a.K(this.c, this.A);
        this.A.l(this.P);
        this.A.K(this.P);
        this.addValue(this.r, this.t, this.a, this.c, this.A, this.P, this.V, this.U, this.k, this.H, this.Y);
        ClickEngine clickEngine = new ClickEngine(ClickButton.LEFT, this.V, this.H, this.Y, this.r, this.U, this.k);
        this.F(clickEngine);
        this.V.V(0);
    }
}

