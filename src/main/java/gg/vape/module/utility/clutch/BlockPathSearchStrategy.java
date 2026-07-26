package gg.vape.module.utility.clutch;

import gg.vape.utils.datas.BlockData;
import java.util.Vector;

public interface BlockPathSearchStrategy<T> {
    public int w();

    default public boolean V(BlockData blockData) {
        return true;
    }

    public boolean B(BlockData var1);

    default public int g(Vector<T> vector, int n) {
        return this.t(vector);
    }

    default public int t(Vector<T> vector) {
        return vector.size();
    }
}

