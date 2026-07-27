package gg.vape.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.vape.Vape;
import gg.vape.manager.ModManager;
import gg.vape.module.Mod;
import gg.vape.module.combat.AimAssist;
import gg.vape.module.combat.LeftClicker;
import gg.vape.module.combat.Reach;
import gg.vape.module.combat.Velocity;
import gg.vape.ui.click.frame.impl.VisibleModuleListFrame;
import java.util.ArrayList;
import java.util.List;

public class ModuleProfileMetadataCodec {
    private final List<Mod> o = new ArrayList<Mod>();

    public void a(Mod mod) {
        if (this.o.contains(mod)) {
            return;
        }
        this.o.add(mod);
        mod.M(true);
        VisibleModuleListFrame.e();
        Vape.INSTANCE.saveAndStop();
    }

    public int S() {
        int n = 0;
        for (Mod mod : this.o) {
            if (!mod.r$src$Z$14eylz9()) continue;
            ++n;
        }
        return n;
    }

    public List<Mod> k() {
        return this.o;
    }


    public void v(Mod mod) {
        if (!this.o.contains(mod)) {
            return;
        }
        this.o.remove(mod);
        mod.M(false);
        VisibleModuleListFrame.e();
        Vape.INSTANCE.saveAndStop();
    }

    public void Q(JsonObject jsonObject) {
        if (jsonObject.has("modules")) {
            this.o.clear();
            JsonArray jsonArray = jsonObject.get("modules").getAsJsonArray();
            for (JsonElement jsonElement : jsonArray) {
                Mod mod = Vape.INSTANCE.getModManager().getMod(jsonElement.getAsString());
                if (mod == null) continue;
                this.C(mod);
            }
            VisibleModuleListFrame.e();
        }
    }

    private void C(Mod mod) {
        if (this.o.contains(mod)) {
            return;
        }
        this.o.add(mod);
        mod.M(true);
    }

    public ModuleProfileMetadataCodec() {
        ModManager modManager = Vape.INSTANCE.getModManager();
        this.C(modManager.getMod(LeftClicker.class));
        this.C(modManager.getMod(AimAssist.class));
        this.C(modManager.getMod(Reach.class));
        this.C(modManager.getMod(Velocity.class));
    }

    public JsonObject J() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        for (Mod mod : this.o) {
            jsonArray.add((JsonElement)new JsonPrimitive(mod.getName()));
        }
        jsonObject.add("modules", (JsonElement)jsonArray);
        return jsonObject;
    }
}

