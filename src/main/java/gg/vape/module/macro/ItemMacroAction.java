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
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.utils.RandomUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerMacroBridge;
import gg.vape.wrapper.impl.Minecraft;
import java.lang.invoke.MethodHandles;

public class ItemMacroAction
implements MacroAction {
    private final TimerUtil n = new TimerUtil();
    private boolean o = false;
    private final ItemMacro b;
    private int T = 0;
    private boolean y = false;
    private int B = -1;
    private static final long a;

    public int K() {
        return this.B;
    }

    static {
        long l = a = ZkmLongKeyState.a(2612327236907924835L, 6591889970205458087L, MethodHandles.lookup().lookupClass()).a(138057076883091L);
    }

    @Override
    public Macro g() {
        return this.b;
    }

    @Override
    public void N() {
        this.o = true;
    }

    @Override
    public void J(MacroAction macroAction) {
        if (macroAction instanceof ItemMacroAction) {
            this.B = ((ItemMacroAction)macroAction).K();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void U(int n) {
        this.B = n;
    }

    @Override
    public boolean h() {
        return this.o;
    }

    public ItemMacroAction(ItemMacro itemMacro) {
        this.b = itemMacro;
    }

    @Override
    public void Z() {
        Object object;
        int n;
        long l = a ^ 0x1A6992AA6D1FL;
        if (this.B == -1) {
            n = ItemMacro.k(this.b);
            if (n == -1) {
                this.o = true;
                return;
            }
            this.B = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().v();
            Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            this.n.reset();
            object = Vape.INSTANCE.getModManager().getMod(Animations.class);
            if (ClientSettings.V()) {
                if (((Mod)object).r$src$Z$14eylz9() && ((Animations)object).n$src$Z$uk21qf() && ClientSettings.H$src$Z$9w16bz(Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362())) {
                    if (((Animations)object).V$src$Lgg_vape_module_render_animations_AnimationsMode$1evu1tq().M() && !((Animations)object).V$src$Lgg_vape_module_render_animations_AnimationsMode$1evu1tq().i()) {
                        this.T = 2;
                    }
                } else {
                    this.T = 2;
                }
            }
        }
        switch (this.T) {
            case 0: {
                KeyBindingInputState.D();
                ++this.T;
                break;
            }
            case 1: {
                KeyBindingInputState.V();
                this.n.reset();
                ++this.T;
                break;
            }
            case 2: {
                if (this.b.getDoubleClick().L().booleanValue() && !this.y) {
                    n = this.n.hasTimeElapsed(RandomUtil.i(this.b.getDoubleClickDelay())) ? 1 : 0;
                    if (this.b instanceof ItemMacroActionState) {
                        Entity entity;
                        object = Minecraft.thePlayer();
                        EntityPlayerMacroBridge entityPlayerMacroBridge = ((EntityPlayer)object).K$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1agjn9();
                        if (((Wrapper)object).isNotNull() && entityPlayerMacroBridge.isNotNull() && ((entity = ((EntityPlayer)object).K$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1agjn9().r$src$Lgg_vape_wrapper_impl_Entity_$18p7x3h()).isNotNull() && entity.isInstance(MappedClasses.lG) || entityPlayerMacroBridge.o())) {
                            n = 1;
                        }
                    }
                    if (n == 0) break;
                    this.y = true;
                    this.T = 0;
                    break;
                }
                ++this.T;
                break;
            }
            case 3: {
                if (!this.n.hasTimeElapsed(RandomUtil.i(this.b.getDelay()) - 2)) break;
                ++this.T;
                break;
            }
            case 4: {
                Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(this.B);
                this.o = true;
            }
        }
    }
}
