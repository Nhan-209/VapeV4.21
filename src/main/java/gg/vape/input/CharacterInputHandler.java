package gg.vape.input;

import gg.vape.input.InputEventHandler;
import gg.vape.module.none.ClientSettings;
import gg.vape.ui.click.component.GuiKeyTypedDispatcher;
import java.util.HashSet;
import java.util.Set;

public class CharacterInputHandler
implements InputEventHandler {
    private Set<Integer> b = new HashSet<Integer>();
    private static int q;


    @Override
    public boolean handle(long l, long l2) {
        char c3 = (char)l;
        char c2 = '\u0000';
        if (c3 == '\u00a7' || c3 < ' ' || c3 == '\u007f') {
            if (this.b.contains(Character.valueOf(c3))) {
                c2 = c3;
            }
            if (c2 == '\u0000') {
                return false;
            }
        }
        GuiKeyTypedDispatcher.p(c3, c2);
        return !ClientSettings.fW.P;
    }

    public static int r() {
        int n = CharacterInputHandler.z();
        if (n == 0) {
            return 63;
        }
        return 0;
    }

    public CharacterInputHandler() {
        this.b.add(8);
        this.b.add(3);
        this.b.add(22);
        this.b.add(27);
        this.b.add(13);
        this.b.add(9);
    }

    public static void G(int n) {
        q = n;
    }

    public static int z() {
        return q;
    }

    static {
        if (CharacterInputHandler.r() == 0) {
            CharacterInputHandler.G(4);
        }
    }
}

