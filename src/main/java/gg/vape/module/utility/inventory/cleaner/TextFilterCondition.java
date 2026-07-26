package gg.vape.module.utility.inventory.cleaner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterCondition;
import gg.vape.module.utility.inventory.cleaner.TextMatchMode;
import gg.vape.utils.Base64Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.UnmodifiableView;

public interface TextFilterCondition<T extends InventoryFilterCondition<T>>
extends InventoryFilterCondition<T> {
    public static final Map g = new HashMap(13);
    public static final String[] d = new String[2];
    public static final String[] c = null;

    public @UnmodifiableView List<String> M$src$Ljava_util_List_$bgq9xa();

    public T l(String var1);

    public T n(String var1);

    public T W(TextMatchMode var1);

    public TextMatchMode M();

    public T B();

    @Override
    default public JsonObject L() {
        JsonObject jsonObject = InventoryFilterCondition.super.L();
        JsonArray jsonArray = new JsonArray();
        for (String string : this.M$src$Ljava_util_List_$bgq9xa()) {
            jsonArray.add((JsonElement)new JsonPrimitive(Base64Util.encodeUtf8Base64(string)));
        }
        jsonObject.addProperty("operator", this.M().getName());
        jsonObject.add("text", (JsonElement)jsonArray);
        return jsonObject;
    }
}
