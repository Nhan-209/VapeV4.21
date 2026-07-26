package gg.vape.click;

public enum ClickButton {
    RIGHT,
    LEFT;
    private static final ClickButton[] K;

    static {
        K = new ClickButton[]{LEFT, RIGHT};
    }
}
