package gg.vape.friend.activity;

import gg.vape.protocol.ZeusPacketBuffer;
import java.util.UUID;

public class ActivityTargetData {
    private final UUID d;
    private final String n;

    public UUID B() {
        return this.d;
    }

    public ActivityTargetData(ZeusPacketBuffer gx_12) {
        this.d = gx_12.N();
        this.n = gx_12.v(16);
    }

    public ActivityTargetData(UUID uUID, String string) {
        this.d = uUID;
        this.n = string;
    }

    public void i(ZeusPacketBuffer gx_12) {
        gx_12.r(this.d);
        gx_12.y(this.n);
    }

    public String a() {
        return this.n;
    }
}

