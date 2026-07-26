package gg.vape.friend.activity;

import gg.vape.protocol.ZeusPacketBuffer;
import java.util.LinkedHashMap;
import java.util.Map;

public class ActivityItemStackPayload {
    private final int m;
    private final int g;
    private static String f;
    private final Map<Short, Short> K;
    private final int C;

    public ActivityItemStackPayload(ZeusPacketBuffer zeusPacketBuffer) {
        this.C = zeusPacketBuffer.k();
        this.g = zeusPacketBuffer.k();
        this.m = zeusPacketBuffer.k();
        LinkedHashMap<Short, Short> linkedHashMap = new LinkedHashMap<Short, Short>();
        int n = zeusPacketBuffer.Y();
        for (int i = 0; i < n; ++i) {
            short s = zeusPacketBuffer.x();
            short s2 = zeusPacketBuffer.x();
            linkedHashMap.put(s, s2);
        }
        this.K = linkedHashMap;
    }

    public int E() {
        return this.C;
    }

    public ActivityItemStackPayload(int n, int n2, int n3, Map<Short, Short> map) {
        this.C = n;
        this.g = n2;
        this.m = n3;
        this.K = map;
    }

    public Map<Short, Short> p() {
        return this.K;
    }

    public void Q(ZeusPacketBuffer zeusPacketBuffer) {
        zeusPacketBuffer.K(this.C);
        zeusPacketBuffer.K(this.g);
        zeusPacketBuffer.K(this.m);
        zeusPacketBuffer.i(this.K.size());
        for (Map.Entry<Short, Short> entry : this.K.entrySet()) {
            zeusPacketBuffer.t(entry.getKey());
            zeusPacketBuffer.t(entry.getValue());
        }
    }

    public int P() {
        return this.g;
    }

    public int n() {
        return this.m;
    }

    public static String Q() {
        return f;
    }

    public static void p(String string) {
        f = string;
    }

    static {
        if (ActivityItemStackPayload.Q() == null) {
            ActivityItemStackPayload.p("Wfzy2");
        }
    }
}

