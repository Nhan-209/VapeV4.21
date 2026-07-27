package gg.vape.utils.render;


public enum PrimitiveTopology {
    LINES(2, 2, "line"),
    LINES_LOOP(2, 2, "line"),
    QUADS(6, 4, "quad"),
    TRIANGLES(3, 3, "triangle");

    public final String name;
    public final int verticesCount;
    private static final PrimitiveTopology[] n;
    public final int indicesCount;

    private PrimitiveTopology(int n2, int n3, String string2) {
        this.indicesCount = n2;
        this.verticesCount = n3;
        this.name = string2;
    }

    static {
        String[] stringArray = new String[]{"quad", "line", "line", "QUADS", "TRIANGLES", "LINES", "triangle", "LINES_LOOP"};




        n = new PrimitiveTopology[]{LINES, LINES_LOOP, QUADS, TRIANGLES};
    }


    public int Q() {
        switch (this) {
            case LINES: {
                return 1;
            }
            case QUADS: 
            case TRIANGLES: {
                return 4;
            }
            case LINES_LOOP: {
                return 2;
            }
        }
        return -1;
    }
}

