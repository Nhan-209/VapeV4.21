package gg.vape.input;

import gg.vape.input.CharacterInputHandler;
import gg.vape.input.ExtendedMouseButtonPressInputHandler;
import gg.vape.input.ExtendedMouseButtonReleaseInputHandler;
import gg.vape.input.InputEventHandler;
import gg.vape.input.InputFocusGainedHandler;
import gg.vape.input.InputFocusLostHandlerPayload;
import gg.vape.input.InputFocusState;
import gg.vape.input.KeyboardInputState;
import gg.vape.input.KeyboardPressInputHandler;
import gg.vape.input.KeyboardReleaseInputHandler;
import gg.vape.input.MouseButtonPressInputHandler;
import gg.vape.input.MouseButtonReleaseInputHandler;
import gg.vape.input.MouseInputState;
import gg.vape.input.MouseMoveInputHandler;
import gg.vape.input.MouseWheelInputHandler;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.runtime.obfuscation.ZkmLongKeyState;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderTypeBuffer;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;

public class InputEventDispatcher {
    public HashMap<Integer, InputEventHandler> y = new HashMap();
    private InputFocusState M;
    private static InputEventDispatcher U;
    private MouseInputState S;
    private static final long a;
    private KeyboardInputState K = new KeyboardInputState();
    private long N;
    private static int[] o;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public static void M(int[] nArray) {
        o = nArray;
    }

    public KeyboardInputState getKeyboardState() {
        return this.K;
    }

    public static InputEventDispatcher getInstance() {
        if (U == null) {
            U = new InputEventDispatcher();
        }
        return U;
    }

    public InputEventDispatcher() {
        this.S = new MouseInputState();
        this.M = new InputFocusState();
    }

    public InputFocusState getFocusState() {
        return this.M;
    }

    public void U() {
        if (ForgeVersion.MC_1_21_10.v()) {
            return;
        }
        RenderTypeBuffer bD = Minecraft.K();
        if (bD.isNull()) {
            return;
        }
        if (!this.M.isFocused()) {
            return;
        }
        bD.q();
    }

    public boolean dispatch(int n, long l, long l2) {
        InputEventHandler inputEventHandler = this.y.get(n);
        if (inputEventHandler != null) {
            this.U();
            return inputEventHandler.handle(l, l2);
        }
        return false;
    }

    public void registerHandlers() {
        long l = a ^ 0x10693719AC0DL;
        int[] nArray = InputEventDispatcher.c();
        this.y.put(258, new CharacterInputHandler());
        this.y.put(256, new KeyboardPressInputHandler());
        this.y.put(257, new KeyboardReleaseInputHandler());
        this.y.put(260, new KeyboardPressInputHandler());
        this.y.put(261, new KeyboardReleaseInputHandler());
        this.y.put(513, new MouseButtonPressInputHandler(0));
        this.y.put(516, new MouseButtonPressInputHandler(1));
        this.y.put(519, new MouseButtonPressInputHandler(2));
        this.y.put(514, new MouseButtonReleaseInputHandler(0));
        this.y.put(517, new MouseButtonReleaseInputHandler(1));
        this.y.put(520, new MouseButtonReleaseInputHandler(2));
        this.y.put(523, new ExtendedMouseButtonPressInputHandler());
        this.y.put(524, new ExtendedMouseButtonReleaseInputHandler());
        this.y.put(522, new MouseWheelInputHandler());
        this.y.put(512, new MouseMoveInputHandler());
        this.y.put(7, new InputFocusGainedHandler());
        this.y.put(8, new InputFocusLostHandlerPayload());
        int[] nArray2 = nArray;
        if (nArray2 != null) {
            GuiComponent.D(new GuiComponent[5]);
        }
    }

    public static int[] c() {
        return o;
    }

    public long getWindowHandle() {
        return this.N;
    }

    public void setWindowHandle(long l) {
        this.N = l;
        this.M.markFocused();
    }

    public MouseInputState getMouseState() {
        return this.S;
    }

    static {
        a = ZkmLongKeyState.a(6224341716493192374L, -8737006317819562L, MethodHandles.lookup().lookupClass()).a(146197291856964L);
        if (InputEventDispatcher.c() != null) {
            InputEventDispatcher.M(new int[1]);
        }
    }
}

