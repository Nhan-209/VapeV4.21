package gg.vape.module.render.animations;

import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventLivingUpdate;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPreTick;
import gg.vape.event.impl.EventTickBase;
import gg.vape.event.impl.SyntheticAttackRequestEvent;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.Animations;
import gg.vape.module.render.animations.AnimationsMode;
import gg.vape.rotation.RotationManager;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;

public class DamageResponsiveAnimationsMode
extends AnimationsMode {
    private long A;
    private boolean P;
    private boolean V = false;

    private void k(EntityPlayerSP entityPlayerSP) {
        if (((Animations)this.getParent()).n$src$Z$uk21qf() && !gg.vape.config.ClientSettings.V()) {
            return;
        }
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.isNotNull() && rayTraceResult.getEntity().isInstance(MappedClasses.zm)) {
            EntityLivingBase entityLivingBase = new EntityLivingBase(rayTraceResult.getEntity());
            int n = entityLivingBase.c$src$I$15a9iwo();
            int n2 = AttackPacketTimingTracker.a.Z() + 1;
            if (!this.V && !entityPlayerSP.o$src$Z$1iprrmi() && n <= n2 + 1 && n <= n2 && ((Animations)this.getParent()).j$src$Lgg_vape_wrapper_impl_EntityLivingBase_$m2mrxi() != null) {
                boolean bl = true;
                DamageResponsiveAnimationsMode damageResponsiveAnimationsMode = this;
                damageResponsiveAnimationsMode.o(bl);
                this.A = System.currentTimeMillis() + 50L * (long)(n2 + 1);
            }
        }
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        boolean bl;
        boolean bl2;
        int[] nArray = ClientSettings.A();
        if (Minecraft.thePlayer().isNull() || !((Animations)this.getParent()).a$src$Z$ucwq0q()) {
            return;
        }
        if (Minecraft.currentScreen().isNotNull()) {
            return;
        }
        EntityLivingBase entityLivingBase = ((Animations)this.getParent()).j$src$Lgg_vape_wrapper_impl_EntityLivingBase_$m2mrxi();
        boolean bl3 = eventPreTick.getThePlayer().c$src$I$15a9iwo() > AttackPacketTimingTracker.a.Z() + 1;
        boolean bl4 = bl2 = this.A > 0L && System.currentTimeMillis() >= this.A;
        if (entityLivingBase == null || bl3 || bl2) {
            boolean bl5 = false;
            DamageResponsiveAnimationsMode damageResponsiveAnimationsMode = this;
            damageResponsiveAnimationsMode.o(bl5);
            return;
        }
        if (((Animations)this.getParent()).n$src$Z$uk21qf() && !gg.vape.config.ClientSettings.V()) {
            return;
        }
        int n = eventPreTick.getThePlayer().c$src$I$15a9iwo();
        int n2 = AttackPacketTimingTracker.a.Z() + 2;
        boolean bl6 = bl = entityLivingBase.c$src$I$15a9iwo() <= n2 + 2;
        if (!(this.V && this.P && bl || this.V || n > n2 + 1 || n <= 0)) {
            boolean bl7 = true;
            DamageResponsiveAnimationsMode damageResponsiveAnimationsMode = this;
            damageResponsiveAnimationsMode.o(bl7);
            this.P = true;
            this.A = System.currentTimeMillis() + 50L * (long)n2;
        }
    }

    @Override
    public boolean i() {
        return false;
    }

    @Override
    public boolean M() {
        return this.V;
    }


    public DamageResponsiveAnimationsMode(Mod mod, String string) {
        super(mod, string);
    }

    @EventHandler(A=EventPriority.LOW)
    public void a(SyntheticAttackRequestEvent syntheticAttackRequestEvent) {
        int[] nArray = ClientSettings.A();
        if (syntheticAttackRequestEvent.isCanceled() || !((Animations)this.getParent()).a$src$Z$ucwq0q()) {
            return;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return;
        }
        this.k(entityPlayerSP);
    }

    public void o(boolean bl) {
        int[] nArray = ClientSettings.A();
        if (this.V != bl) {
            this.V = bl;
            this.A = 0L;
            this.P = false;
            Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362().setPressed(bl);
        }
    }

    @EventHandler
    public void onUpdate(EventLivingUpdate eventLivingUpdate) {
        if (!((Animations)this.getParent()).a$src$Z$ucwq0q()) {
            return;
        }
        this.A = System.currentTimeMillis();
        if (eventLivingUpdate.getEntity().getObject().equals(Minecraft.thePlayer().getObject())) {
            EventTickBase.S.execute(this::lambda$onDamaged$0);
        }
    }

    @EventHandler
    public void c(EventMouseButton eventMouseButton) {
        int n = -100 + eventMouseButton.getButton();
        int[] nArray = ClientSettings.A();
        if (!eventMouseButton.getButtonState() || !((Animations)this.getParent()).a$src$Z$ucwq0q()) {
            return;
        }
        if (n == Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362().getKeyCode() && ((Animations)this.getParent()).n$src$Z$uk21qf() && gg.vape.config.ClientSettings.M()) {
            eventMouseButton.setCancelled(true);
            return;
        }
        if (n == Minecraft.gameSettings().F().getKeyCode()) {
            this.k(eventMouseButton.getThePlayer());
        }
    }

    private void lambda$onDamaged$0() {
        boolean bl = false;
        DamageResponsiveAnimationsMode damageResponsiveAnimationsMode = this;
        damageResponsiveAnimationsMode.o(bl);
    }
}

