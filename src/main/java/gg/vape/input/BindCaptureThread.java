package gg.vape.input;

import gg.vape.Vape;
import gg.vape.input.BindCaptureTask;
import gg.vape.input.BindCaptureThreadConstructorMarker;
import gg.vape.input.InputEventDispatcher;
import gg.vape.input.KeyboardInputState;
import gg.vape.input.MouseInput;
import gg.vape.module.none.ClientSettings;
import java.util.ArrayList;
import java.util.Collections;

class BindCaptureThread
extends Thread {
    final BindCaptureTask y;

    BindCaptureThread(BindCaptureTask bindCaptureTask, BindCaptureThreadConstructorMarker bindCaptureThreadConstructorMarker) {
        this(bindCaptureTask);
    }

    private BindCaptureThread(BindCaptureTask bindCaptureTask) {
        this.y = bindCaptureTask;
    }

    @Override
    public void run() {
        ClientSettings clientSettings = Vape.INSTANCE.getModManager().getMod(ClientSettings.class);
        KeyboardInputState keyboardInputState = InputEventDispatcher.getInstance().getKeyboardState();
        long l = keyboardInputState.getLastChangeTime();
        long l2 = MouseInput.l$src$J$dk87ei();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int n = -1;
        int n2 = -1;
        while (true) {
            int n3;
            if (Thread.interrupted()) {
                this.y.p();
                return;
            }
            if (n != -1 && !keyboardInputState.isKeyDown(n) || n2 != -1 && !MouseInput.I(n2) || arrayList.size() >= 3) break;
            if (l != keyboardInputState.getLastChangeTime() && keyboardInputState.isLastKeyDown()) {
                l = keyboardInputState.getLastChangeTime();
                n3 = keyboardInputState.getLastKey();
                if (arrayList.contains(n3)) continue;
                arrayList.add(n3);
                if (n == -1) {
                    n = n3;
                }
                if (!clientSettings.fE.L().booleanValue()) break;
                continue;
            }
            if (keyboardInputState.isKeyDown(160)) continue;
            if (l2 != MouseInput.l$src$J$dk87ei() && MouseInput.E()) {
                l2 = MouseInput.l$src$J$dk87ei();
                if (MouseInput.l() == 0) {
                    BindCaptureTask.Z(this.y).c(Collections.emptyList());
                    break;
                }
                n3 = -100 + MouseInput.l();
                if (n2 == -1) {
                    n2 = MouseInput.l();
                }
                if (arrayList.contains(n3)) continue;
                arrayList.add(n3);
                if (!clientSettings.fE.L().booleanValue()) break;
                continue;
            }
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        BindCaptureTask.Z(this.y).c(arrayList);
        this.y.p();
    }

    private static InterruptedException a(InterruptedException interruptedException) {
        return interruptedException;
    }
}

