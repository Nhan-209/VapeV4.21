package gg.vape.module.macro;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.event.impl.EventPreTick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Macro;
import gg.vape.module.macro.MacroAction;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Iterator;
import java.util.List;

public class MacroEventListener
implements EventListener {
    private MacroAction m;

    private boolean G(Macro macro) {
        MacroAction pc_02 = macro.N();
        if (pc_02 == null) {
            return false;
        }
        if (this.m != null) {
            this.m.N();
            pc_02.J(this.m);
        }
        this.m = pc_02;
        return true;
    }

    @EventHandler
    public void onTick(EventPreTick eventTick) {
        if (this.m == null) {
            return;
        }
        this.m.Z();
        if (this.m.h()) {
            this.m = null;
        }
    }


    @EventHandler
    public void I(EventMouseButton eventMouseButton) {
        if (eventMouseButton.getButtonState()) {
            Macro macro;
            List<Macro> list = Vape.INSTANCE.getMacrosManager().getMacros(-100 + eventMouseButton.getButton());
            if (list.isEmpty()) {
                return;
            }
            Iterator<Macro> iterator = list.iterator();
            while (!(!iterator.hasNext() || (macro = iterator.next()).f(-100 + eventMouseButton.getButton()) && this.G(macro))) {
            }
        }
    }

    @EventHandler
    public void y(EventKeyPress eventKeyPress) {
        Macro macro;
        if (eventKeyPress.isDown()) {
            return;
        }
        if (eventKeyPress.getThePlayer().isNull()) {
            return;
        }
        if (Minecraft.a_pt_1_w().isInstance(MappedClasses.qo)) {
            return;
        }
        List<Macro> list = Vape.INSTANCE.getMacrosManager().getMacros(eventKeyPress.getKey());
        if (list.isEmpty()) {
            return;
        }
        Iterator<Macro> iterator = list.iterator();
        while (!(!iterator.hasNext() || (macro = iterator.next()).f(eventKeyPress.getKey()) && this.G(macro))) {
        }
    }
}

