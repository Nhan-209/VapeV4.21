package gg.vape.module.combat;

import gg.vape.click.ClickButton;
import gg.vape.click.ClickEngine;
import gg.vape.click.ClickerWorker;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreRenderTick;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.InputEventDispatcher;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.rotation.RotationManager;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.SleepUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;
import java.util.Random;

public class ClickerMod
extends Mod {
    private long attackStartTime;
    private boolean clicking;
    private static final long MODULE_ID = 7524649824893733649L;
    protected boolean S;
    public ClickEngine s;
    private final ClickerWorker clickerWorker = new ClickerWorker(this);

    public boolean h$src$Z$qo2e1d() {
        return false;
    }

    public boolean d(EntityPlayerSP entityPlayerSP) {
        return false;
    }

    public boolean K(ClickEngine clickEngine, EntityPlayerSP entityPlayerSP) {
        return false;
    }

    public boolean z() {
        return false;
    }

    @Override
    public void onEnable() {
        this.clickerWorker.K();
    }

    @Override
    public void onDisable() {
        this.clickerWorker.W();
    }


    public ClickerMod(String string, int n, Category category) {
        super(string, n, category, "");
    }

    public ClickerMod(String string) {
        super(string, (int)MODULE_ID, Category.g, "");
    }

    public void F(ClickEngine clickEngine) {
        this.s = clickEngine;
    }

    public boolean C() {
        return false;
    }

    public boolean D(EntityPlayerSP entityPlayerSP) {
        boolean bl;
        boolean bl2;
        boolean bl3;
        RayTraceResult rayTraceResult = RotationManager.b.n();
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (ClickEngine.O() != null) {
            boolean bl4;
            boolean bl5;
            boolean bl6 = rayTraceResult.isNotNull();
            boolean bl7 = bl6;
            boolean bl8 = bl7;
            if (bl8) {
                boolean bl9;
                boolean bl10;
                boolean bl11 = bl10 = (bl9 = this.z());
                GuiComponent.D(new GuiComponent[4]);
                return bl11;
            }
            boolean bl12 = bl5 = (bl4 = this.z());
            GuiComponent.D(new GuiComponent[4]);
            return bl12;
        }
        boolean bl13 = rayTraceResult.isNotNull();
        boolean bl14 = bl3 = bl13 && (bl2 = rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity()));
        if (bl3) {
            int n;
            float f;
            boolean bl15 = this.z();
            if (bl15 && ForgeVersion.MC_1_12_2.d() && (f = Minecraft.thePlayer().getCooledAttackStrength(0.5f)) < 1.0f) {
                return true;
            }
            boolean bl16 = this.h$src$Z$qo2e1d();
            if (bl16 && rayTraceResult.getEntity().isNotNull() && entityPlayerSP.b$src$Z$fqlxe4() && (n = rayTraceResult.getEntity().V$src$I$fk0dv5()) > 12) {
                return true;
            }
            boolean bl17 = false;
            return bl17;
        }
        boolean bl18 = this.z();
        if (bl18) {
            float f;
            if (guiScreen.isNull()) {
                return true;
            }
            if (ForgeVersion.MC_1_12_2.d() && (f = Minecraft.thePlayer().getCooledAttackStrength(0.5f)) < 1.0f) {
                return true;
            }
        }
        if (bl = this.h$src$Z$qo2e1d()) {
            // empty if block
        }
        boolean bl19 = false;
        return bl19;
    }

    public double U() {
        return 0.0;
    }

    @EventHandler
    public final void onTick(EventPreTick eventPreTick) {
        this.s.J(eventPreTick);
    }

    @EventHandler
    public final void t(EventPreRenderTick eventPreRenderTick) {
        this.s.r(eventPreRenderTick);
    }

    public void U$src$V$ml8mr6() {
        EntityPlayerSP entityPlayerSP;
        ClickerMod clickerMod;
        boolean bl;
        EntityPlayerSP entityPlayerSP2 = Minecraft.thePlayer();
        GuiScreen guiScreen = Minecraft.currentScreen();
        boolean bl2 = guiScreen.isNull();
        GuiComponent[] guiComponentArray = ClickEngine.O();
        if (!bl2) {
            return;
        }
        if (!InputEventDispatcher.getInstance().getFocusState().isFocused()) {
            return;
        }
        if (!this.s.G()) {
            this.clicking = false;
            return;
        }
        if (!this.clicking) {
            this.clicking = true;
            this.attackStartTime = System.currentTimeMillis();
        }
        long l = 50L + (long)this.U();
        if (System.currentTimeMillis() - this.attackStartTime < l) {
            return;
        }
        if (!this.s.R(entityPlayerSP2) && guiScreen.isNull()) {
            return;
        }
        if (this.d(entityPlayerSP2)) {
            return;
        }
        boolean bl3 = bl = this.s.z().s$src$I$vi2lk8() > 20;
        if (bl) {
            EntityPlayerSP entityPlayerSP3;
            ClickerMod clickerMod2;
            long l2 = this.s.u() - 5L;
            double d = (double)(100L - Math.min(l2, 99L) + 45L) / 100.0;
            double d2 = 40.0 * d;
            Random random = new Random();
            double d3 = ((double)(30 + random.nextInt() % 10) + d2) / 100.0;
            long l3 = (long)((double)l2 * (1.0 - d3));
            long l4 = (long)((double)l2 * d3);
            if (!this.s.G()) {
                this.clicking = false;
                return;
            }
            if (this.s.s() == ClickButton.LEFT && (clickerMod2 = this).D(entityPlayerSP3 = entityPlayerSP2)) {
                return;
            }
            bl2 = Minecraft.a();
            if (!bl2) {
                return;
            }
            TimerUtil timerUtil = new TimerUtil();
            long l5 = timerUtil.getLastMS();
            l3 = Math.max(0L, l3 - l5);
            if (this.S) {
                this.S = false;
            }
            this.s.g();
            boolean bl4 = this.K(this.s, entityPlayerSP2);
            if (bl4) {
                this.s.F();
            }
            SleepUtil.sleep(l3);
            timerUtil.reset();
            if (bl4) {
                this.s.d();
            }
            if (!this.S) {
                this.s.N();
            } else {
                this.S = false;
            }
            long l6 = timerUtil.getLastMS();
            l4 = Math.max(0L, l4 - l6);
            SleepUtil.sleep(l4);
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                ClickEngine.B(new GuiComponent[1]);
            }
            return;
        }
        long l7 = this.s.u() - 5L;
        if (l7 - 50L <= 0L) {
            l7 = 45L;
        }
        double d = (double)(100L - Math.min(l7, 99L) + 45L) / 100.0;
        double d4 = 40.0 * d;
        Random random = new Random();
        double d5 = ((double)(30 + random.nextInt() % 10) + d4) / 100.0;
        long l8 = (long)((double)l7 * (1.0 - d5));
        long l9 = (long)((double)l7 * d5);
        if (!this.s.G()) {
            this.clicking = false;
            return;
        }
        if (this.s.s() == ClickButton.LEFT && (clickerMod = this).D(entityPlayerSP = entityPlayerSP2)) {
            return;
        }
        bl2 = Minecraft.a();
        if (!bl2) {
            return;
        }
        TimerUtil timerUtil = new TimerUtil();
        if (this.S) {
            this.S = false;
        }
        this.s.g();
        boolean bl5 = this.K(this.s, entityPlayerSP2);
        if (bl5) {
            this.s.F();
        }
        SleepUtil.sleep(l8);
        timerUtil.reset();
        if (bl5) {
            this.s.d();
        }
        if (!this.S) {
            this.s.N();
        } else {
            this.S = false;
        }
        SleepUtil.sleep(l9);
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            ClickEngine.B(new GuiComponent[1]);
        }
    }
}

