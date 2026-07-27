package gg.vape.unmap;

import gg.vape.event.EventHandler;
import gg.vape.event.EventListener;
import gg.vape.event.impl.EventKeyPress;
import gg.vape.event.impl.EventMouseButton;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.unmap.Bendable;
import java.util.ArrayList;

public class BendableInputDispatcher
implements EventListener {
    private static final ArrayList<Bendable> b = new ArrayList();
    private static GuiComponent[] E;

    @EventHandler
    public void C(EventKeyPress eventKeyPress) {
        if (!eventKeyPress.isDown()) {
            return;
        }
        for (Bendable bendable : b) {
            bendable.f(eventKeyPress.getKey());
        }
    }

    public static void H(Bendable bendable) {
        b.add(bendable);
    }

    @EventHandler
    public void Z(EventMouseButton eventMouseButton) {
        if (!eventMouseButton.getButtonState()) {
            return;
        }
        for (Bendable bendable : b) {
            bendable.f(-100 + eventMouseButton.getButton());
        }
    }

    public static GuiComponent[] G() {
        return E;
    }

    public static void T(GuiComponent[] upArray) {
        E = upArray;
    }


    static {
        BendableInputDispatcher.T(new GuiComponent[1]);
    }
}

