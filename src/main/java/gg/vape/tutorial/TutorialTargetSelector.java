package gg.vape.tutorial;

import gg.vape.ui.click.component.GuiComponent;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class TutorialTargetSelector<T extends GuiComponent> {
    private static boolean Z;
    private final Class<T> Y;

    public static void A(boolean bl) {
        Z = bl;
    }

    public Class<T> o() {
        return this.Y;
    }

    static {
        if (!TutorialTargetSelector.Y()) {
            TutorialTargetSelector.A(true);
        }
    }

    public abstract boolean X(T var1);

    public static boolean Y() {
        boolean bl = TutorialTargetSelector.Z();
        return !bl;
    }

    public static boolean Z() {
        return Z;
    }


    public TutorialTargetSelector(Class<T> clazz) {
        this.Y = clazz;
    }

    public ArrayList<GuiComponent> v(GuiComponent guiComponent) {
        if (this.o().isInstance(guiComponent) && this.X(this.o().cast(guiComponent))) {
            return new ArrayList<GuiComponent>(Arrays.asList(guiComponent));
        }
        return null;
    }
}
