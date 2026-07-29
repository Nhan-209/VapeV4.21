package gg.vape.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.Base64Util;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

public class ConfigJsonUtils {
    private static GuiComponent[] I;
    private static final String b;

    @Nullable
    public static Boolean t(JsonObject jsonObject, String string) {
        return ConfigJsonUtils.P(jsonObject, string, JsonPrimitive::getAsBoolean);
    }

    public static void S(GuiComponent[] guiComponentArray) {
        I = guiComponentArray;
    }

    public static GuiComponent[] g() {
        return I;
    }

    @Nullable
    public static String P(JsonObject jsonObject, String string) {
        return ConfigJsonUtils.t(jsonObject, string, ConfigJsonUtils::lambda$getString$2);
    }

    public static boolean C(JsonObject jsonObject, String string) {
        Boolean bl = ConfigJsonUtils.t(jsonObject, string);
        return bl != null ? bl : false;
    }

    static {
        ConfigJsonUtils.S(new GuiComponent[5]);
        b = "b64:";
    }

    public static String c(JsonObject jsonObject, String string) {
        if (jsonObject.get(string) != null && !jsonObject.get(string).isJsonNull()) {
            String string2 = jsonObject.get(string).getAsString();
            if (string2.startsWith(b)) {
                string2 = Base64Util.decodeUtf8Base64(string2.split(":")[1]);
            }
            return string2;
        }
        return "";
    }

    @Nullable
    public static Long R(JsonObject jsonObject, String string) {
        return ConfigJsonUtils.P(jsonObject, string, JsonPrimitive::getAsLong);
    }

    @Nullable
    public static JsonArray q(JsonObject jsonObject, String string) {
        return ConfigJsonUtils.t(jsonObject, string, ConfigJsonUtils::lambda$getJsonArray$1);
    }

    private static Object lambda$getByPrimitive$3(Function function, JsonElement jsonElement) {
        if (!jsonElement.isJsonPrimitive()) {
            return null;
        }
        return function.apply(jsonElement.getAsJsonPrimitive());
    }

    private static JsonArray lambda$getJsonArray$1(JsonElement jsonElement) {
        if (!jsonElement.isJsonArray()) {
            return null;
        }
        return jsonElement.getAsJsonArray();
    }

    @Nullable
    private static <T> T P(JsonObject jsonObject, String string, Function<JsonPrimitive, T> function) {
        return (T)ConfigJsonUtils.t(jsonObject, string, arg_0 -> ConfigJsonUtils.lambda$getByPrimitive$3(function, arg_0));
    }

    @Nullable
    public static String B(JsonObject jsonObject, String string) {
        String string2 = ConfigJsonUtils.P(jsonObject, string);
        if (string2 == null) {
            return null;
        }
        return Base64Util.decodeUtf8Base64(string2);
    }

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    @Nullable
    public static UUID u(JsonObject jsonObject, String string) {
        String string2 = ConfigJsonUtils.P(jsonObject, string);
        if (string2 == null) {
            return null;
        }
        try {
            return UUID.fromString(string2);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }

    @Nullable
    public static Double p(JsonObject jsonObject, String string) {
        return ConfigJsonUtils.P(jsonObject, string, JsonPrimitive::getAsDouble);
    }

    public static List<Integer> o(JsonArray jsonArray, boolean bl) {
        List<Integer> list = ConfigJsonUtils.h(jsonArray);
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (Integer n : list) {
            if (n == 0) continue;
            if (bl && n > 0) {
                n = KeyboardCodeUtil.convertLegacyKeyCode(n);
            }
            if (n == 0 || n == 27) continue;
            arrayList.add(n);
        }
        return arrayList;
    }

    @Nullable
    public static Short f(JsonObject jsonObject, String string) {
        return ConfigJsonUtils.P(jsonObject, string, JsonPrimitive::getAsShort);
    }

    private static JsonObject lambda$getJsonObject$0(JsonElement jsonElement) {
        if (!jsonElement.isJsonObject()) {
            return null;
        }
        return jsonElement.getAsJsonObject();
    }

    @Nullable
    public static Integer r(JsonObject jsonObject, String string) {
        return ConfigJsonUtils.P(jsonObject, string, JsonPrimitive::getAsInt);
    }

    public static List<Integer> h(JsonArray jsonArray) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (JsonElement jsonElement : jsonArray) {
            JsonPrimitive jsonPrimitive;
            if (jsonElement.isJsonNull() || !jsonElement.isJsonPrimitive() || !(jsonPrimitive = jsonElement.getAsJsonPrimitive()).isNumber()) continue;
            arrayList.add(jsonPrimitive.getAsInt());
        }
        return arrayList;
    }

    @Nullable
    public static JsonObject E(JsonObject jsonObject, String string) {
        return ConfigJsonUtils.t(jsonObject, string, ConfigJsonUtils::lambda$getJsonObject$0);
    }

    @Nullable
    private static <T> T t(JsonObject jsonObject, String string, Function<JsonElement, T> function) {
        JsonElement jsonElement = jsonObject.get(string);
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return null;
        }
        return function.apply(jsonElement);
    }

    private static String lambda$getString$2(JsonElement jsonElement) {
        if (!jsonElement.isJsonPrimitive()) {
            return null;
        }
        return jsonElement.getAsString();
    }
}
