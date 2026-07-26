package gg.vape.unmap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import gg.vape.config.ClientSettings;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.input.BindActivationMode;
import gg.vape.input.KeyboardInput;
import gg.vape.input.MouseInput;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.BendableBindList;
import gg.vape.unmap.BindChangeListener;
import gg.vape.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public abstract class Bendable {
    private final List<Integer> E = new BendableBindList(this);
    private final List<BindChangeListener> j = new ArrayList<BindChangeListener>();
    private static int T;

    public abstract void A();

    public boolean A$src$Z$jg36ch() {
        return false;
    }

    public boolean f(int n) {
        if (this.L().isEmpty()) {
            return false;
        }
        if (this.L().size() == 1) {
            if (this.L().contains(n)) {
                this.L$src$V$qc2osj();
                return true;
            }
        } else {
            int n2 = 0;
            for (int n3 : this.L()) {
                if (n3 == n) {
                    ++n2;
                    continue;
                }
                if (n3 < 0) {
                    if (!MouseInput.I(100 + n3)) continue;
                    ++n2;
                    continue;
                }
                if (!KeyboardInput.isKeyDown(n3)) continue;
                ++n2;
            }
            if (n2 == this.L().size()) {
                this.L$src$V$qc2osj();
                return true;
            }
        }
        return false;
    }

    public void Y(BindActivationMode bindActivationMode) {
    }

    private void L$src$V$qc2osj() {
        this.A();
        for (BindChangeListener bindChangeListener : this.j) {
            bindChangeListener.S();
        }
    }

    public boolean y$src$Z$r0tfl8() {
        boolean bl = !this.L().isEmpty();
        for (Integer n : this.L()) {
            if (n != 0) continue;
            bl = false;
            break;
        }
        return bl;
    }

    public boolean Y() {
        return true;
    }

    protected boolean n(int n) {
        return this.L().contains(n);
    }

    public static int G$src$I$q9bpij() {
        int n = Bendable.q();
        return 0;
    }

    public abstract boolean m();

    public boolean U(int n, boolean bl) {
        if (!bl) {
            return false;
        }
        return this.f(n);
    }

    public boolean K() {
        if (this.L().isEmpty()) {
            return false;
        }
        if (this.L().size() == 1) {
            return ClientSettings.l(this.L().get(0));
        }
        int n = 0;
        for (int n2 : this.L()) {
            if (!ClientSettings.l(n2)) continue;
            ++n;
        }
        return n == this.L().size();
    }

    public JsonArray toJson$src$Lcom_google_gson_JsonArray_$13cfbto() {
        JsonArray jsonArray = new JsonArray();
        for (Integer n : this.L()) {
            jsonArray.add(new Gson().toJsonTree((Object)n));
        }
        return jsonArray;
    }

    public abstract String y();

    public void O(JsonArray jsonArray, boolean bl) {
        List<Integer> list = ConfigJsonUtils.o(jsonArray, bl);
        if (!list.isEmpty()) {
            this.L().clear();
            for (int n : list) {
                this.L().add(n);
            }
        }
    }

    public List<Integer> L() {
        return this.E;
    }

    public BindActivationMode G() {
        return BindActivationMode.TOGGLE;
    }

    public void R(BindChangeListener bindChangeListener) {
        this.j.add(bindChangeListener);
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public String h() {
        return StringUtils.q(this.L());
    }

    static {
        if (Bendable.q() == 0) {
            Bendable.L(28);
        }
    }

    public static void L(int n) {
        T = n;
    }

    public static int q() {
        return T;
    }

    public void n$src$V$quroyt() {
        this.Y(this.G().I());
    }

    public void c(List<Integer> list) {
        this.L().clear();
        for (Integer n : list) {
            if (n == 27) continue;
            this.L().add(n);
        }
    }
}

