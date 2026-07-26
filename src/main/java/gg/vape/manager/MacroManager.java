package gg.vape.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.config.ConfigJsonUtils;
import gg.vape.module.Macro;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.FrameMacros;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MacroManager {
    private final Set<Macro> U = new LinkedHashSet<Macro>();

    public void removeMacro(Macro macro) {
        this.U.remove(macro);
        FrameMacros.jo.Z(macro);
    }

    public void addMacro(Macro macro) {
        this.U.add(macro);
        FrameMacros.jo.v(macro);
    }

    public Macro getMacro(String string) {
        for (Macro macro : this.U) {
            if (!macro.getName().equalsIgnoreCase(string)) continue;
            return macro;
        }
        return null;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void clear() {
        for (Macro macro : new HashSet<Macro>(this.U)) {
            this.removeMacro(macro);
        }
    }

    public List<Macro> getMacros(List<Integer> list) {
        ArrayList<Macro> arrayList = new ArrayList<Macro>();
        for (Macro macro : this.U) {
            if (!macro.L().equals(list)) continue;
            arrayList.add(macro);
        }
        return arrayList;
    }

    public List<Macro> getMacros(int n) {
        ArrayList<Macro> arrayList = new ArrayList<Macro>();
        for (Macro macro : this.U) {
            if (!macro.L().contains(n)) continue;
            arrayList.add(macro);
        }
        return arrayList;
    }

    public JsonArray toJson() {
        JsonArray jsonArray = new JsonArray();
        for (Macro macro : this.U) {
            jsonArray.add((JsonElement)macro.toJson());
        }
        return jsonArray;
    }

    public Set<Macro> getMacros() {
        return this.U;
    }

    public void loadJson(JsonArray jsonArray) {
        this.clear();
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject jsonObject;
            JsonElement jsonElement = jsonArray.get(i);
            if (!jsonElement.isJsonObject() || jsonElement.isJsonNull() || (jsonObject = jsonElement.getAsJsonObject()).get("name") == null || jsonObject.get("name").isJsonNull()) continue;
            String string = ConfigJsonUtils.c(jsonObject, "name");
            Macro macro = Macro.create(string);
            macro.loadJson(jsonObject);
            this.addMacro(macro);
        }
    }
}

