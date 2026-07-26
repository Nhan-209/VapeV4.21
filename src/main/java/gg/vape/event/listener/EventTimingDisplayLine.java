package gg.vape.event.listener;

public class EventTimingDisplayLine {
    private final String I;
    private final long V;

    public EventTimingDisplayLine(String string, long l) {
        this.I = string;
        this.V = l;
    }

    public long h() {
        return this.V;
    }

    public String Y() {
        return this.I;
    }
}

