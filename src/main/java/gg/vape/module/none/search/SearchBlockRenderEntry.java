package gg.vape.module.none.search;

public class SearchBlockRenderEntry {
    private int posX;
    private int posY;
    private int posZ;
    private int extra;
    private int color;

    public int f() {
        return this.color;
    }

    public int k() {
        return this.posY;
    }

    public int i() {
        return this.posZ;
    }

    public SearchBlockRenderEntry(int x, int y, int z, int colorValue, int extraValue) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.color = colorValue;
        this.extra = extraValue;
    }

    public void L(int x, int y, int z, int colorValue, int extraValue) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.color = colorValue;
        this.extra = extraValue;
    }

    public int R() {
        return this.posX;
    }

    public int N() {
        return this.extra;
    }
}

