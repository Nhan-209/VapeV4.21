package gg.vape.utils;

public class MutablePair<First, Second> {
    private First o;
    private Second P;

    public First O() {
        return this.o;
    }

    public MutablePair(First First2, Second Second) {
        this.o = First2;
        this.P = Second;
    }

    public MutablePair<First, Second> w(First First2) {
        this.o = First2;
        return this;
    }

    public Second K() {
        return this.P;
    }

    public MutablePair<First, Second> Q(Second Second) {
        this.P = Second;
        return this;
    }
}

