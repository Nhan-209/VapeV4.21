package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.module.utility.inventory.cleaner.TextFilterCondition;
import gg.vape.module.utility.inventory.cleaner.TextMatchMode;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.Base64Util;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTextFilterCondition<T extends AbstractTextFilterCondition<T>>
implements TextFilterCondition<T> {
    private List<String> m = new ArrayList<String>();
    private TextMatchMode N = TextMatchMode.EQUALS;

    @Override
    public TextMatchMode M() {
        return this.N;
    }

    @Override
    public List<String> M$src$Ljava_util_List_$bgq9xa() {
        return this.m;
    }

    public T l(TextMatchMode textMatchMode) {
        this.N = textMatchMode;
        return (T)this;
    }

    @Override
    public T W(TextMatchMode textMatchMode) {
        return this.l(textMatchMode);
    }

    public T S(String string) {
        this.m.remove(string);
        return (T)this;
    }

    @Override
    public T l(String string) {
        return this.S(string);
    }

    protected AbstractTextFilterCondition(List<String> list, TextMatchMode textMatchMode) {
        this.m = new ArrayList<String>(list);
        this.N = textMatchMode;
    }

    public T clearText() {
        this.m.clear();
        return (T)this;
    }

    @Override
    public T B() {
        return this.clearText();
    }

    @Override
    public T w() {
        return this.H();
    }

    protected AbstractTextFilterCondition(JsonObject jsonObject) {
        JsonArray jsonArray = ConfigJsonUtils.q(jsonObject, "text");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); ++i) {
                this.m.add(Base64Util.decodeUtf8Base64(jsonArray.get(i).getAsString()));
            }
        }
        this.N = TextMatchMode.p(jsonObject.get("operator").getAsString());
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public abstract T H();

    public T Q(String string) {
        this.m.add(string);
        return (T)this;
    }

    @Override
    public T n(String string) {
        return this.Q(string);
    }

    protected AbstractTextFilterCondition() {
    }
}
