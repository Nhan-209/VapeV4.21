package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.combat.AimAssist;
import gg.vape.utils.RotationUtil;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;

public class Strafe
extends Mod {
    private final NumberValue L;
    private boolean H;
    private final NumberValue V = NumberValue.create((Object)this, "Distance", "#.#", "", 0.1, 3.3, 6.0, 0.1);
    private AimAssist a;
    private final NumberValue j;
    private final NumberValue p = NumberValue.create(this, "Speed", "#.#", "", 0.1, 0.5, 1.0);

    public Strafe() {
        super("Strafe", -256, Category.w);
        this.j = NumberValue.create(this, "Target minimum angle", "#", "", 1.0, 120.0, 360.0);
        this.L = NumberValue.create(this, "Your minimum angle", "#", "", 1.0, 90.0, 360.0);
        this.addValue(this.V, this.p, this.j, this.L);
        this.v(10L, true);
    }

    private void void_t() {
        this.a = Vape.INSTANCE.getModManager().getMod(AimAssist.class);
    }

    private void void_q() {
        double targetDistance;
        if (Minecraft.a_pt_1_w().isNotNull()) {
            return;
        }
        EntityPlayerSP player = Minecraft.a_xH_J();
        GameSettings settings = Minecraft.a_w3_0_S();
        KeyBinding sprintKey = settings.s();
        boolean physicallyDown = ClientSettings.B(sprintKey);
        KeyBindingHelper.d(Minecraft.a_w3_0_S().s(), physicallyDown);
        double distance = (Double)this.V.java_lang_Object_K();
        double speed = (Double)this.p.java_lang_Object_K() / 5.0;
        speed *= 0.1;
        boolean hasTarget = this.a.boolean_r() && this.a.a_xa_0_q() != null;
        EntityLivingBase target = new EntityLivingBase(this.a.a_xa_0_q());
        if (target.isNull()) {
            return;
        }
        boolean targetAngle = RotationUtil.g(player, target, (Double)this.j.java_lang_Object_K() / 2.0);
        boolean selfAngle = RotationUtil.g(target, player, (Double)this.L.java_lang_Object_K() / 2.0);
        if (hasTarget && targetAngle && selfAngle && (targetDistance = (double)player.getDistanceToEntity(target)) < distance && !player.boolean_h() && !player.boolean_r() && !player.boolean_S() && player.boolean_b() && !player.boolean_o()) {
            double targetX = target.double_z();
            double targetZ = target.double_h();
            if (player.double_z() - targetX > 0.5) {
                player.r(player.double_t() + speed);
            }
            if (player.double_z() - targetX < 0.5) {
                player.r(player.double_t() - speed);
            }
            if (player.double_h() - targetZ > 0.5) {
                player.i(player.double_T() + speed);
            }
            if (player.double_h() - targetZ < 0.5) {
                player.i(player.double_T() - speed);
            }
            this.H = true;
        }
    }

    @EventHandler
    public void onTick(EventPrePlayerTick event) {
        EntityPlayerSP player = Minecraft.a_xH_J();
        if (this.H) {
            player.R(false);
            this.H = false;
        }
    }

    @Override
    public void t() {
        this.void_t();
    }

    @Override
    public void q() {
        this.void_q();
    }
}

