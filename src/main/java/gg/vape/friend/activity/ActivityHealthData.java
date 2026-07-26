package gg.vape.friend.activity;

import gg.vape.protocol.ZeusPacketBuffer;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActivityHealthData {
    private final float V;
    private final float b;
    private final int E;
    private final Map<Short, Integer> h;
    private final float O;

    public int L() {
        return this.E;
    }

    public float j() {
        return this.O;
    }

    public float V() {
        return this.b;
    }

    public Map<Short, Integer> G() {
        return this.h;
    }

    public ActivityHealthData(float f, float f2, float f3, int n, Map<Short, Integer> map) {
        this.O = f;
        this.b = f2;
        this.V = f3;
        this.E = n;
        this.h = map;
    }

    ActivityHealthData(ZeusPacketBuffer gx_12) {
        this.O = gx_12.e();
        this.b = gx_12.e();
        this.V = gx_12.e();
        this.E = gx_12.k();
        this.h = new LinkedHashMap<Short, Integer>();
        int n = gx_12.Y();
        for (int i = 0; i < n; ++i) {
            short s = gx_12.x();
            int n2 = gx_12.Y();
            this.h.put(s, n2);
        }
    }

    public void h(ZeusPacketBuffer gx_12) {
        gx_12.l(this.O);
        gx_12.l(this.b);
        gx_12.l(this.V);
        gx_12.K(this.E);
        gx_12.i(this.h.size());
        for (Map.Entry<Short, Integer> entry : this.h.entrySet()) {
            gx_12.t(entry.getKey());
            gx_12.i(entry.getValue());
        }
    }

    public float H() {
        return this.V;
    }
}

