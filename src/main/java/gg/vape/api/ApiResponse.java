package gg.vape.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ApiResponse<T> {
    @SerializedName(value="successful")
    private final boolean A;
    private static GuiComponent[] C;
    @SerializedName(value="error")
    @Nullable
    private final String f;
    @SerializedName(value="data")
    @Nullable
    private final T J;

    public static GuiComponent[] e() {
        return C;
    }

    public static <T> ApiResponse<T> m(JsonObject jsonObject, Function<JsonElement, T> function) {
        JsonElement jsonElement = jsonObject.get("data");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return ApiResponse.w(jsonObject.get("error").getAsString());
        }
        return ApiResponse.G(function.apply(jsonElement));
    }

    public static <T> ApiResponse<T> G(@NotNull T t) {
        return new ApiResponse<T>(t, null, true);
    }

    static {
        ApiResponse.i(new GuiComponent[5]);
    }

    public static <T> ApiResponse<T> w(@Nullable String string) {
        return new ApiResponse<T>(null, string, false);
    }

    public boolean t() {
        return this.A;
    }

    @Nullable
    public String N() {
        return this.f;
    }

    @Nullable
    public T T() {
        return this.J;
    }

    ApiResponse(@Nullable T t, @Nullable String string, boolean bl) {
        this.J = t;
        this.f = string;
        this.A = bl;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void i(GuiComponent[] guiComponentArray) {
        C = guiComponentArray;
    }

    public String toString() {
        return "VapeResponse{data=" + this.J + ", error='" + this.f + '\'' + ", successful=" + this.A + '}';
    }
}
