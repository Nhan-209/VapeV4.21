package gg.vape.mapping.runtime;

public class MemberLookupSignature {
    public final String M;
    public final Class<?> a;
    public final Class<?>[] v;
    private final Boolean H;

    public MemberLookupSignature(String string, Boolean bl, Class<?> clazz, Class<?> ... classArray) {
        this.M = string;
        this.a = clazz;
        this.v = classArray;
        this.H = bl;
    }

    public Boolean H() {
        return this.H;
    }
}

