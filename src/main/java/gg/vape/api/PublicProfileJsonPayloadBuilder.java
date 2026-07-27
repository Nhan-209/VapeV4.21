package gg.vape.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class PublicProfileJsonPayloadBuilder {
    public static JsonObject b(String string, String string2, String string3, List<String> list, boolean bl, boolean bl2, boolean bl3, @Nullable UUID uUID, JsonObject jsonObject) {
        JsonArray jsonArray = new JsonArray();
        for (String string4 : list) {
            jsonArray.add((JsonElement)new JsonPrimitive(string4));
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("name", string);
        jsonObject2.addProperty("vapeVersion", string2);
        jsonObject2.addProperty("description", string3);
        jsonObject2.add("tags", (JsonElement)jsonArray);
        jsonObject2.addProperty("listed", Boolean.valueOf(bl));
        jsonObject2.addProperty("anonymous", Boolean.valueOf(bl2));
        jsonObject2.addProperty("shareCodeFriendsOnly", Boolean.valueOf(bl3));
        jsonObject2.addProperty("derivedFrom", uUID != null ? uUID.toString() : null);
        jsonObject2.add("profileData", (JsonElement)jsonObject);
        return jsonObject2;
    }

}

