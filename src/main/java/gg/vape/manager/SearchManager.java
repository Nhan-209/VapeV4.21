package gg.vape.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import gg.vape.Vape;
import gg.vape.module.none.Search;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.unmap.SearchBlock;
import java.util.HashSet;
import java.util.Set;

public class SearchManager {
    private final Set<SearchBlock> n = new HashSet<SearchBlock>();

    public JsonArray toJson() {
        JsonArray jsonArray = new JsonArray();
        for (SearchBlock searchBlock : this.n) {
            jsonArray.add((JsonElement)searchBlock.com_google_gson_JsonObject_I());
        }
        return jsonArray;
    }

    public void y(SearchBlock searchBlock) {
        if (searchBlock != null) {
            Search search = Vape.INSTANCE.getModManager().getMod(Search.class);
            this.n.remove(searchBlock);
            search.u(searchBlock);
        }
    }

    public void r() {
        for (SearchBlock searchBlock : new HashSet<SearchBlock>(this.n)) {
            this.y(searchBlock);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public Set<SearchBlock> O() {
        return this.n;
    }

    public void H(SearchBlock searchBlock) {
        this.n.add(searchBlock);
        Search search = Vape.INSTANCE.getModManager().getMod(Search.class);
        search.T(searchBlock);
    }

    public void loadJson(JsonArray jsonArray) {
        this.r();
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonElement jsonElement = jsonArray.get(i);
            if (!jsonElement.isJsonObject() || jsonElement.isJsonNull()) continue;
            SearchBlock searchBlock = new SearchBlock(jsonElement.getAsJsonObject());
            this.H(searchBlock);
        }
    }
}

