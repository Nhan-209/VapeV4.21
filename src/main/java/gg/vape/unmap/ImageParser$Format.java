package gg.vape.unmap;

public enum ImageParser$Format {
    ALPHA(1, true),
    LUMINANCE(1, false),
    LUMINANCE_ALPHA(2, true),
    RGB(3, false),
    RGBA(4, true),
    BGRA(4, true),
    ABGR(4, true),
    WHITE(4, true);

    final boolean E;
    final int Z;
    private static final ImageParser$Format[] X;

    private ImageParser$Format(int n2, boolean bl) {
        this.Z = n2;
        this.E = bl;
    }

    static {
        String[] stringArray = new String[]{"LUMINANCE", "WHITE", "ALPHA", "ABGR", "LUMINANCE_ALPHA", "RGB", "BGRA", "RGBA"};








        X = new ImageParser$Format[]{ALPHA, LUMINANCE, LUMINANCE_ALPHA, RGB, RGBA, BGRA, ABGR, WHITE};
    }

    public boolean q() {
        return this.E;
    }

    public int M() {
        return this.Z;
    }

}

