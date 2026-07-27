package gg.vape.module.blatant;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPreEntityUpdate;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.input.KeyboardInput;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.blatant.InvWalkSettingsState;
import gg.vape.module.none.ClientSettings;
import gg.vape.utils.MathUtil;
import gg.vape.value.BooleanValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;

public class InvWalk
extends Mod {
    private boolean bindsHeld;
    private final BooleanValue rotate;
    private final BooleanValue sneak;
    private final BooleanValue inventoryOnly = BooleanValue.create(this, "Inventory Only", false, "Only moves you when in inventory/pause screen.\nIgnores any chests/containers.");

    public InvWalk() {
        super("InvWalk", new Color(193, 113, 0).getRGB(), Category.w, "Walk and look around in UI's\nUse arrow keys to look around\nDoes not bypass some anti-cheats!");
        this.sneak = BooleanValue.create(this, "Sneak", false, "Takes sneaking input");
        this.rotate = BooleanValue.create(this, "Rotate", false, "Rotates your head with the arrow keys.");
        this.R(false);
        this.addValue(this.inventoryOnly);
        this.addValue(this.sneak);
        this.addValue(this.rotate);
    }

    @EventHandler
    public void X(EventPreEntityUpdate eventPreEntityUpdate) {
        if (eventPreEntityUpdate.getEntity().isInstance(MappedClasses.z5)) {
            if (this.g$src$Z$tdg77x()) {
                this.bindsHeld = true;
                GameSettings gameSettings = Minecraft.gameSettings();
                InvWalkSettingsState.C(gameSettings.Y());
                InvWalkSettingsState.C(gameSettings.s());
                InvWalkSettingsState.C(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg());
                InvWalkSettingsState.C(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3());
                InvWalkSettingsState.C(gameSettings.O());
                if (this.sneak.L().booleanValue()) {
                    InvWalkSettingsState.C(gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0());
                }
            } else {
                if (this.bindsHeld) {
                    this.resetBinds();
                }
                this.bindsHeld = false;
            }
        }
    }

    public boolean g$src$Z$tdg77x() {
        if (Minecraft.currentScreen().isNull()) {
            return false;
        }
        if (ClientSettings.fT != null) {
            return false;
        }
        if (!(Minecraft.currentScreen().isInstance(MappedClasses.Ft) && Minecraft.currentScreen().isInstance(MappedClasses.YS) || !this.inventoryOnly.L().booleanValue())) {
            return false;
        }
        return !Minecraft.currentScreen().isInstance(MappedClasses.qo);
    }

    @Override
    public boolean isBlatantMod() {
        return false;
    }

    private void resetBinds() {
        GameSettings gameSettings = Minecraft.gameSettings();
        InvWalkSettingsState.C(gameSettings.Y());
        InvWalkSettingsState.C(gameSettings.s());
        InvWalkSettingsState.C(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg());
        InvWalkSettingsState.C(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3());
        InvWalkSettingsState.C(gameSettings.O());
        InvWalkSettingsState.C(gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0());
    }

    @Override
    public void onDisable() {
        if (Minecraft.currentScreen().isNotNull()) {
            GameSettings gameSettings = Minecraft.gameSettings();
            InvWalkSettingsState.L(gameSettings.Y(), false);
            InvWalkSettingsState.L(gameSettings.s(), false);
            InvWalkSettingsState.L(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), false);
            InvWalkSettingsState.L(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), false);
            InvWalkSettingsState.L(gameSettings.O(), false);
            InvWalkSettingsState.L(gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0(), false);
        } else {
            this.resetBinds();
        }
    }

    @EventHandler
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        if (this.rotate.L().booleanValue()) {
            EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
            if (this.g$src$Z$tdg77x()) {
                if (KeyboardInput.isKeyDown(38) && entityPlayerSP.V() - 3.0f > -90.0f) {
                    entityPlayerSP.C(MathUtil.clamp(entityPlayerSP.V() - 3.0f, -89.5f, 89.5f));
                }
                if (KeyboardInput.isKeyDown(40) && entityPlayerSP.V() + 3.0f < 90.0f) {
                    entityPlayerSP.C(MathUtil.clamp(entityPlayerSP.V() + 3.0f, -89.5f, 89.5f));
                }
                if (KeyboardInput.isKeyDown(37)) {
                    entityPlayerSP.H(entityPlayerSP.J() - 5.0f);
                }
                if (KeyboardInput.isKeyDown(39)) {
                    entityPlayerSP.H(entityPlayerSP.J() + 5.0f);
                }
            }
        }
    }

}

