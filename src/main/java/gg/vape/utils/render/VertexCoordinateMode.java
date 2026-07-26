package gg.vape.utils.render;

public enum VertexCoordinateMode {
    DEFAULT,
    MINECRAFT;

    private static final VertexCoordinateMode[] h;

    static {
        String[] stringArray = new String[]{"DEFAULT", "MINECRAFT"};


        h = new VertexCoordinateMode[]{DEFAULT, MINECRAFT};
    }
}

