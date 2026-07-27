package gg.vape.module.render;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventBus;
import gg.vape.input.KeyboardInput;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.render.freecam.FreecamController;
import gg.vape.module.render.freecam.FreecamLegacyController;
import gg.vape.module.render.freecam.FreecamModernController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.utils.PlayerSimulationUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.NetworkManager;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.UseEntityPacketBridge;
import java.util.function.Predicate;

public class Freecam
extends Mod {
    public final BooleanValue r;
    public double J;
    public double s;
    private final FreecamController<Freecam> controller;
    public final BooleanValue U;
    private static final long MODULE_ID = -3715766461932661926L;
    public int I;
    public float O;
    public NetworkManager K;
    public float F;
    public final BooleanValue A;
    public final NumberValue v;
    public final RotationControlClaim c = SharedModuleControlClaims.I;
    public double a;
    public boolean S = false;
    public boolean C = false;
    public EntityOtherPlayerMP Z;
    public final NumberValue Y;
    public Object P;
    public int H;

    public boolean c(Packet packet) {
        if (UseEntityPacketBridge.h(packet) && !this.U.L().booleanValue()) {
            return true;
        }
        if (!this.U.L().booleanValue()) {
            if (packet.isInstance(MappedClasses.DN)) {
                return true;
            }
            return packet.isInstance(MappedClasses.YB);
        }
        return false;
    }

    public void k$src$V$o7vvo0() {
        this.Z = PlayerSimulationUtil.y();
        this.Z.M(0.0f);
        this.Z.k$src$V$5315b7(0.0f);
        this.Z.R(false);
        RotationManager rotationManager = RotationManager.b;
        if (rotationManager.u()) {
            this.Z.H(rotationManager.V());
            this.Z.D(rotationManager.V());
            this.Z.z(rotationManager.V());
            this.Z.o(rotationManager.V());
            this.Z.C(rotationManager.x());
        }
        this.I = Minecraft.thePlayer().y$src$I$1ub55de();
        this.J = Minecraft.thePlayer().o$src$D$1u5n7bh();
        this.s = Minecraft.thePlayer().Q$src$D$1tp5din();
        this.a = Minecraft.thePlayer().X$src$D$1tszxo6();
        this.F = Minecraft.thePlayer().g();
        this.O = Minecraft.thePlayer().a$src$F$1txy325();
        if (this.A.L().booleanValue()) {
            this.H = ClientSettings.f();
            this.Z.Q(this.H);
            Minecraft.theWorld().D(this.H, this.Z);
        }
    }

    public void Z() {
        if (this.H != 0 && this.Z != null && Minecraft.theWorld().isNotNull()) {
            Minecraft.theWorld().M(this.Z);
            ClientSettings.I(this.H);
            this.H = 0;
        }
        this.Z = null;
    }

    @Override
    public void onDisable() {
        this.controller.I();
        EventBus.getInstance().unregisterListener(this.controller);
    }

    public Freecam() {
        super("Freecam", (int)MODULE_ID, Category.m, "Lets you fly and clip through walls freely\nwithout moving your player server-sided.");
        this.U = BooleanValue.create(this, "Allow Interacting", true, "Allows you to interact with blocks and entities while in freecam.");
        this.Y = NumberValue.create(this, "Speed", "#.#", "", 1.0, 3.0, 5.0, 0.1, "Horizontal speed multiplier");
        this.r = BooleanValue.create(this, "Move Fake", false, "Move your fake entity with your arrow keys.");
        this.A = BooleanValue.create(this, "Spawn Fake", true, "Spawns an entity on where your player is server-sided.\nUsing this will allow for simulated physics.");
        this.v = NumberValue.create(this, "Vertical Speed", "#.#", "", 1.0, 3.0, 5.0, 0.1, "Vertical speed multiplier");
        this.A.K(this.r);
        this.addValue(this.Y, this.v);
        this.U(this.U, ForgeVersion.MC_1_21_11.b());
        this.addValue(this.A, this.r);
        this.c.l(this, 100);
        this.controller = ForgeVersion.MC_1_21_11.d() ? new FreecamModernController(this) : new FreecamLegacyController(this);
    }

    public void M$src$V$nre1v6() {
        this.C = false;
        if (this.Z != null) {
            this.p();
        }
        super.s(false, false);
        this.Z = null;
        this.c.X(this);
    }

    @Override
    public void onEnable() {
        this.S = true;
        this.c.d(this);
        EventBus.getInstance().registerListener(this.controller, new Predicate[0]);
        this.controller.B();
    }

    public void p() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        entityPlayerSP.H(this.Z.z());
        entityPlayerSP.u(this.Z.N());
        entityPlayerSP.l(this.Z.h());
        entityPlayerSP.n(this.Z.f());
        entityPlayerSP.w(this.Z.H());
        entityPlayerSP.A(this.Z.R());
        entityPlayerSP.C(this.Z.z());
        entityPlayerSP.L(this.Z.N());
        entityPlayerSP.s(this.Z.h());
        entityPlayerSP.H(this.Z.J());
        entityPlayerSP.C(this.Z.V());
        entityPlayerSP.U(this.Z.b$src$Z$fqlxe4());
        entityPlayerSP.Y(this.Z.z());
        entityPlayerSP.I(this.Z.N());
        entityPlayerSP.z(this.Z.h());
        entityPlayerSP.Z(this.Z.J());
        entityPlayerSP.A(this.Z.V());
        entityPlayerSP.D(this.Z.j());
        entityPlayerSP.l(this.Z.D());
        entityPlayerSP.B(this.Z.z(), this.Z.N(), this.Z.h());
        entityPlayerSP.E(this.Z.t(), this.Z.q(), this.Z.T());
        Minecraft.thePlayer().E(this.I);
        entityPlayerSP.z(false);
        if (this.H != 0) {
            Minecraft.theWorld().M(this.Z);
            ClientSettings.I(this.H);
            this.H = 0;
        }
    }

    public void X$src$V$nxfse5() {
        this.Z.M(0.0f);
        this.Z.k$src$V$5315b7(0.0f);
        if (this.r.L().booleanValue() && Minecraft.currentScreen().isNull()) {
            if (KeyboardInput.isKeyDown(38)) {
                this.Z.M(1.0f);
            } else if (KeyboardInput.isKeyDown(40)) {
                this.Z.M(-1.0f);
            }
            if (KeyboardInput.isKeyDown(37)) {
                this.Z.k$src$V$5315b7(1.0f);
            } else if (KeyboardInput.isKeyDown(39)) {
                this.Z.k$src$V$5315b7(-1.0f);
            }
        }
        PlayerSimulationUtil.t(this.Z, true);
    }

    @Override
    public void s(boolean bl, boolean bl2) {
        if (bl) {
            super.s(bl, bl2);
        } else {
            this.C = true;
        }
    }

}

