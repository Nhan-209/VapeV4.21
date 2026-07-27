package gg.vape.utils.render;

import java.util.Objects;

public class PotionEffectIconKey {
    private int C;

    public int Y() {
        return this.C;
    }

    public int hashCode() {
        return Objects.hash(this.Y());
    }

    public PotionEffectIconKey(int n) {
        this.C = n;
    }


    public boolean equals(Object object) {
        if (object instanceof PotionEffectIconKey) {
            boolean bl = ((PotionEffectIconKey)object).Y() == this.Y();
            return bl;
        }
        return false;
    }
}

