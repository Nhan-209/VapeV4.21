package gg.vape.value;

import com.google.gson.JsonObject;
import gg.vape.config.ProfileSnapshot;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.Value;
import java.util.Arrays;
import java.util.Objects;

public class ValueSnapshot<T extends Value<R, ?>, R> {
    private final ProfileSnapshot J;
    private final T E;
    private R b;

    public boolean h() {
        return this.x();
    }

    public void s(Object object) {
        if (object instanceof Object[]) {
            this.b = (R)Arrays.copyOf((Object[])object, ((Object[])object).length);
            return;
        }
        this.b = (R)object;
    }

    public R J() {
        return this.b;
    }

    public ProfileSnapshot m() {
        return this.J;
    }

    public boolean x() {
        R k = this.E.P$src$Ljava_lang_Object_$qcpui1();
        if (k instanceof Object[]) {
            return Arrays.equals((Object[])k, (Object[])this.b);
        }
        if (k instanceof double[]) {
            return Arrays.equals((double[])k, (double[])this.b);
        }
        return Objects.equals(k, this.b);
    }

    public T W() {
        return this.E;
    }

    public void j(JsonObject jsonObject) {
        this.E.f(true);
        R k = this.E.m();
        this.E.S();
        if (this.E.loadJson(jsonObject)) {
            this.b = this.E.m();
        }
        this.E.o(k);
        this.E.f(false);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ValueSnapshot(ProfileSnapshot profileSnapshot, T t) {
        this.J = profileSnapshot;
        this.E = t;
        this.b = t.P$src$Ljava_lang_Object_$qcpui1();
    }

    public JsonObject I() {
        R k = this.E.m();
        this.E.o(this.J());
        JsonObject jsonObject = this.E.H(false);
        this.E.o(k);
        return jsonObject;
    }
}
