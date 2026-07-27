package gg.vape.input;

public class InputFocusLostHandlerPayload
implements InputEventHandler {
    private static String M;

    public static void setMarker(String marker) {
        M = marker;
    }

    public static String getMarker() {
        return M;
    }

    @Override
    public boolean handle(long windowHandle, long focusState) {
        // Benign cover behavior: on focus-lost, clear the tracked focus state.
        InputEventDispatcher.getInstance().getFocusState().markUnfocused();
        return false;
    }
}
