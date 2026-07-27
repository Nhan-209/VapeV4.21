package gg.vape.utils.render;

import java.util.Objects;

class CachedTextTextureKey {
    String O;
    int J;

    public String k() {
        return this.O;
    }

    public CachedTextTextureKey(String string, int n) {
        this.O = string;
        this.J = n;
    }


    public int H() {
        return this.J;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        if (!(object instanceof CachedTextTextureKey)) return false;
        if (!((CachedTextTextureKey)object).k().equals(this.k())) return false;
        if (((CachedTextTextureKey)object).H() != this.H()) return false;
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.k(), this.H());
    }
}

