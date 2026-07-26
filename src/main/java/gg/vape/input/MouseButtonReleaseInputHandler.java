package gg.vape.input;

import gg.vape.input.InputEventDispatcher;
import gg.vape.input.InputEventHandler;

public class MouseButtonReleaseInputHandler
implements InputEventHandler {
    int V;

    public MouseButtonReleaseInputHandler(int n) {
        this.V = n;
    }

    @Override
    public boolean handle(long l, long l2) {
        return InputEventDispatcher.getInstance().getMouseState().setButtonState(this.V, false);
    }
}

