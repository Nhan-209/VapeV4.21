package gg.vape.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.vape.api.ApiHttpClient;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public class PublicProfilePartialJsonPayloadBuilder {
    public static JsonObject c(long l, @Nullable UUID uUID, @Nullable String string, @Nullable String string2, @Nullable List<String> list, @Nullable Boolean bl, @Nullable Boolean bl2, @Nullable Boolean bl3, @Nullable JsonObject jsonObject) {
        JsonArray jsonArray = new JsonArray();
        if (list != null) {
            for (String string3 : list) {
                jsonArray.add((JsonElement)new JsonPrimitive(string3));
            }
        }
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("profileId", (Number)l);
        jsonObject2.add("derivedFrom", ApiHttpClient.Z.toJsonTree((Object)uUID));
        jsonObject2.addProperty("name", string);
        jsonObject2.addProperty("vapeVersion", "4.21");
        jsonObject2.addProperty("description", string2);
        jsonObject2.add("tags", (JsonElement)jsonArray);
        jsonObject2.addProperty("listed", bl);
        jsonObject2.addProperty("anonymous", bl2);
        jsonObject2.addProperty("shareCodeFriendsOnly", bl3);
        jsonObject2.add("profileData", (JsonElement)jsonObject);
        return jsonObject2;
    }
}

