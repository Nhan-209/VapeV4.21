package gg.vape.friend.ui;

import gg.vape.value.BooleanValue;
import gg.vape.value.NumberValue;

public class OnlinePlayerPreviewSettings {
    public final NumberValue W;
    public final BooleanValue A;
    public final NumberValue d = NumberValue.create(this, "Size", "#.#", "", 0.5, 1.0, 2.0);
    public final NumberValue p = NumberValue.create(this, "FPS", "#", "", 1.0, 30.0, 60.0);

    public OnlinePlayerPreviewSettings() {
        this.W = NumberValue.create(this, "FOV", "#", "", 50.0, 90.0, 150.0);
        this.A = BooleanValue.create(this, "Level view", true);
    }
}

