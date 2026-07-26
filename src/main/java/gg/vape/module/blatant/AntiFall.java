package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.manager.ModManager;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.Fly;
import gg.vape.module.blatant.Speed;
import gg.vape.module.render.Freecam;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.Blocks;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.MovementInput;
import gg.vape.wrapper.impl.World;

public class AntiFall
extends Mod {
    private final BooleanValue K = BooleanValue.create(this, "Speed Check", false, "Ignore falling when Speed is enabled.");
    private boolean v;
    private final TimerUtil t;
    private final NumberValue A = NumberValue.E(this, "Fall Dist", "#.#", "m", 0.1, 2.0, 5.0, "The amount of blocks to fall before attempting to lag back.");

    public AntiFall() {
        super("AntiFall", 16028225, Category.w, "Helps you with your Parkinson's\nPrevents you from falling into the void.");
        this.t = new TimerUtil();
        this.addValue(this.K, this.A);
    }

    private boolean hasBlockBelow() {
        EntityPlayerSP entityPlayerSP = Minecraft.a_xH_J();
        World world = entityPlayerSP.gg_vape_wrapper_impl_World_Z();
        for (double d = entityPlayerSP.double_N() - 1.0; d > 0.0; d -= 1.0) {
            Block block = world.getBlock(entityPlayerSP.double_z(), d, entityPlayerSP.double_h());
            if (block.isNull() || block.H().isInstance(Blocks.j().H().getObject().getClass())) continue;
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        EntityPlayerSP entityPlayerSP = Minecraft.a_xH_J();
        if (eventPacketReceive.getPacket().isNull() || entityPlayerSP.isNull() || entityPlayerSP.gg_vape_wrapper_impl_World_Z().isNull()) {
            return;
        }
        if (eventPacketReceive.getPacket().isInstance(MappedClasses.zw)) {
            entityPlayerSP.U(0.0f);
            entityPlayerSP.r(0.0);
            entityPlayerSP.i(0.0);
            this.v = false;
            this.t.reset();
        }
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    @EventHandler
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.a_xH_J();
        World world = entityPlayerSP.gg_vape_wrapper_impl_World_Z();
        ModManager modManager = Vape.INSTANCE.getModManager();
        if (entityPlayerSP.isNull() || world.isNull() || entityPlayerSP.boolean_M() || entityPlayerSP.boolean_d() || entityPlayerSP.a_xf_0_C().isCreativeMode() || entityPlayerSP.a_xf_0_C().isFlying() || modManager.getState(Freecam.class) || modManager.getState(Fly.class) || this.K.java_lang_Boolean_L().booleanValue() && modManager.getState(Speed.class)) {
            return;
        }
        if (!this.v && this.hasBlockBelow()) {
            return;
        }
        if (this.v && this.t.hasTimeElapsed(250L) || entityPlayerSP.boolean_u()) {
            this.v = false;
            this.t.reset();
            return;
        }
        double d = (Double)this.A.java_lang_Object_K();
        if ((double)entityPlayerSP.float_M() >= d && !modManager.getMod(Fly.class).boolean_r()) {
            Block block = world.getBlock(entityPlayerSP.double_z(), entityPlayerSP.double_N() - 1.0, entityPlayerSP.double_h());
            boolean bl = block.isNull() || block.H().isInstance(Blocks.j().H().getObject().getClass());
            boolean bl2 = bl;
            if (bl) {
                if (!this.v) {
                    this.v = true;
                    this.t.reset();
                } else {
                    MovementInput movementInput = entityPlayerSP.a_jw_2_I();
                    movementInput.B(0.0f);
                    movementInput.M(0.0f);
                    entityPlayerSP.r(0.0);
                    entityPlayerSP.i(0.0);
                    entityPlayerSP.B(entityPlayerSP.double_z(), entityPlayerSP.double_N() + (double)entityPlayerSP.float_M(), entityPlayerSP.double_h());
                    entityPlayerSP.U(0.0f);
                }
            }
        }
    }
}

