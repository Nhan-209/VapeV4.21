package gg.vape.module.macro;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.input.KeyBindingInputState;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Macro;
import gg.vape.module.Mod;
import gg.vape.module.macro.ItemMacro;
import gg.vape.module.macro.ItemMacroActionState;
import gg.vape.module.macro.MacroAction;
import gg.vape.module.render.Animations;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.RandomUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerMacroBridge;
import gg.vape.wrapper.impl.Minecraft;

public class ItemMacroAction
implements MacroAction {
    private final TimerUtil timer = new TimerUtil();
    private boolean finished = false;
    private final ItemMacro macro;
    private int step = 0;
    private boolean doubleClickArmed = false;
    private int savedSlot = -1;

    public int K() {
        return this.savedSlot;
    }


    @Override
    public Macro g() {
        return this.macro;
    }

    @Override
    public void N() {
        this.finished = true;
    }

    @Override
    public void J(MacroAction macroAction) {
        if (macroAction instanceof ItemMacroAction) {
            this.savedSlot = ((ItemMacroAction)macroAction).K();
        }
    }

    private static ObfuscatedRuntimeException rethrow(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void U(int n) {
        this.savedSlot = n;
    }

    @Override
    public boolean h() {
        return this.finished;
    }

    public ItemMacroAction(ItemMacro itemMacro) {
        this.macro = itemMacro;
    }

    @Override
    public void Z() {
        Object object;
        int n;
        if (this.savedSlot == -1) {
            n = ItemMacro.k(this.macro);
            if (n == -1) {
                this.finished = true;
                return;
            }
            this.savedSlot = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            this.timer.reset();
            object = Vape.INSTANCE.getModManager().getMod(Animations.class);
            if (ClientSettings.V()) {
                if (((Mod)object).r$src$Z$14eylz9() && ((Animations)object).n$src$Z$uk21qf() && ClientSettings.H$src$Z$9w16bz(Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362())) {
                    if (((Animations)object).V$src$Lgg_vape_module_render_animations_AnimationsMode$1evu1tq().M() && !((Animations)object).V$src$Lgg_vape_module_render_animations_AnimationsMode$1evu1tq().i()) {
                        this.step = 2;
                    }
                } else {
                    this.step = 2;
                }
            }
        }
        switch (this.step) {
            case 0: {
                KeyBindingInputState.D();
                ++this.step;
                break;
            }
            case 1: {
                KeyBindingInputState.V();
                this.timer.reset();
                ++this.step;
                break;
            }
            case 2: {
                if (this.macro.getDoubleClick().L().booleanValue() && !this.doubleClickArmed) {
                    n = this.timer.hasTimeElapsed(RandomUtil.i(this.macro.getDoubleClickDelay())) ? 1 : 0;
                    if (this.macro instanceof ItemMacroActionState) {
                        Entity entity;
                        object = Minecraft.thePlayer();
                        EntityPlayerMacroBridge entityPlayerMacroBridge = ((EntityPlayer)object).K$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1agjn9();
                        if (((Wrapper)object).isNotNull() && entityPlayerMacroBridge.isNotNull() && ((entity = ((EntityPlayer)object).K$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1agjn9().r$src$Lgg_vape_wrapper_impl_Entity_$18p7x3h()).isNotNull() && entity.isInstance(MappedClasses.lG) || entityPlayerMacroBridge.o())) {
                            n = 1;
                        }
                    }
                    if (n == 0) break;
                    this.doubleClickArmed = true;
                    this.step = 0;
                    break;
                }
                ++this.step;
                break;
            }
            case 3: {
                if (!this.timer.hasTimeElapsed(RandomUtil.i(this.macro.getDelay()) - 2)) break;
                ++this.step;
                break;
            }
            case 4: {
                Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.savedSlot);
                this.finished = true;
            }
        }
    }
}
