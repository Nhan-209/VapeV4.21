package gg.vape.utils.render;

public enum VertexAttributeType {
    Float(1, false, 5126),
    Vec2(2, false, 5126),
    Vec3(3, false, 5126),
    Vec4(4, true, 5126);

    private static final /* synthetic */ VertexAttributeType[] a;
    public final int count;
    public final int type;
    public final boolean normalized;

    private VertexAttributeType(int n2, boolean bl, int n3) {
        this.count = n2;
        this.normalized = bl;
        this.type = n3;
    }

    static {
        String[] stringArray = new String[]{"Vec3", "Vec4", "Vec2", "Float"};




        a = new VertexAttributeType[]{Float, Vec2, Vec3, Vec4};
    }

}

