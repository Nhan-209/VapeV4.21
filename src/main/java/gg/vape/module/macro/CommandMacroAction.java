package gg.vape.module.macro;

import gg.vape.module.Macro;
import gg.vape.module.macro.CommandMacro;
import gg.vape.module.macro.MacroAction;
import gg.vape.wrapper.impl.Minecraft;

class CommandMacroAction
implements MacroAction {
    final CommandMacro r;

    CommandMacroAction(CommandMacro commandMacro) {
        this.r = commandMacro;
    }

    @Override
    public boolean h() {
        return true;
    }

    @Override
    public Macro g() {
        return this.r;
    }

    @Override
    public void Z() {
        Minecraft.a_xH_J().sendChatMessage(this.r.getName());
    }
}

