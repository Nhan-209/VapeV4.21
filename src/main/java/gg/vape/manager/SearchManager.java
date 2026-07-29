package gg.vape.manager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import gg.vape.Vape;
import gg.vape.module.none.Search;
import gg.vape.ui.unmap.SearchBlock;
import java.util.HashSet;
import java.util.Set;

public class SearchManager {
    private final Set<SearchBlock> searchBlocks = new HashSet<SearchBlock>();

    public JsonArray toJson() {
        JsonArray jsonArray = new JsonArray();
        for (SearchBlock searchBlock : this.searchBlocks) {
            jsonArray.add((JsonElement)searchBlock.com_google_gson_JsonObject_I());
        }
        return jsonArray;
    }

    public void removeSearchBlock(SearchBlock searchBlock) {
        if (searchBlock != null) {
            Search search = Vape.INSTANCE.getModManager().getMod(Search.class);
            this.searchBlocks.remove(searchBlock);
            search.removeSearchBlock(searchBlock);
        }
    }

    public void clearSearchBlocks() {
        for (SearchBlock searchBlock : new HashSet<SearchBlock>(this.searchBlocks)) {
            this.removeSearchBlock(searchBlock);
        }
    }


    public Set<SearchBlock> getSearchBlocks() {
        return this.searchBlocks;
    }

    public void addSearchBlock(SearchBlock searchBlock) {
        this.searchBlocks.add(searchBlock);
        Search search = Vape.INSTANCE.getModManager().getMod(Search.class);
        search.addSearchBlock(searchBlock);
    }

    public void loadJson(JsonArray jsonArray) {
        this.clearSearchBlocks();
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonElement jsonElement = jsonArray.get(i);
            if (!jsonElement.isJsonObject() || jsonElement.isJsonNull()) continue;
            SearchBlock searchBlock = new SearchBlock(jsonElement.getAsJsonObject());
            this.addSearchBlock(searchBlock);
        }
    }
}

