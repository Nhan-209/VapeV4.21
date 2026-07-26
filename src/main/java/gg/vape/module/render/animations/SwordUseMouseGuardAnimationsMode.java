package gg.vape.module.render.animations;

import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.module.Mod;
import gg.vape.module.render.Animations;
import gg.vape.module.render.animations.AnimationsMode;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.RayTraceResult_type;

public class SwordUseMouseGuardAnimationsMode
extends AnimationsMode {
    private int V = 1;

    public SwordUseMouseGuardAnimationsMode(Mod mod, String string) {
        super(mod, string);
    }

    @Override
    public boolean M() {
        return this.i();
    }

    @EventHandler
    public void w(EventMouseButton eventMouseButton) {
        if (!((Animations)this.getParent()).a$src$Z$ucwq0q()) {
            return;
        }
        int n = -100 + eventMouseButton.getButton();
        if (eventMouseButton.getButtonState() && n == Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362().getKeyCode() && ((Animations)this.getParent()).n$src$Z$uk21qf() && ClientSettings.M()) {
            eventMouseButton.setCancelled(true);
            return;
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean l() {
        if (((Animations)this.getParent()).n$src$Z$uk21qf() && !ClientSettings.V()) {
            return false;
        }
        boolean bl = true;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ItemStack itemStack = entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
        if (itemStack.isNull() || itemStack.getItem().isNull() || !ItemStackScoreUtil.h(itemStack.getItem())) {
            return false;
        }
        boolean bl2 = false;
        RayTraceResult rayTraceResult = RotationManager.b.n();
        if (rayTraceResult.isNotNull() && rayTraceResult.getTypeOfHit().equals(RayTraceResult_type.entity())) {
            bl2 = true;
        }
        if (this.V != 1 || !bl2) {
            bl = false;
            int n = 3;
            if (this.V >= n) {
                this.V = 0;
            }
        }
        this.V = bl2 ? ++this.V : 1;
        return bl;
    }

    @Override
    public boolean i() {
        return this.l();
    }
}

