package gg.vape.value;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.unmap.ItemLimitData;
import gg.vape.value.ListValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LimitValue
extends ListValue<ItemLimitData, LimitValue> {
    private final Color J;
    public static final Color R;
    public static final Color r;
    private final int Q;
    public static final Color G;
    private boolean A = false;

    public void n(List<ItemLimitData> list) {
        super.K().clear();
        super.K().addAll(list);
    }

    public static LimitValue w(Object object, String string, String string2, Color color, int n, List<ItemLimitData> list) {
        LimitValue limitValue = new LimitValue(object, string, string2, color, n, list);
        limitValue.K().addAll(list);
        return limitValue;
    }

    public LimitValue t() {
        return new LimitValue(null, this.P$src$Ljava_lang_String_$1ijjhmj(), this.getName(), this.K$src$Ljava_awt_Color_$y64ykf(), this.M(), this.P$src$Ljava_lang_Object_$qcpui1());
    }

    public ItemLimitData Z(String string, int n) {
        return this.n(new ItemLimitData(string, n));
    }

    public ItemLimitData n(ItemLimitData itemLimitData) {
        this.K().add(itemLimitData);
        if (this.Q == -1) {
            itemLimitData.m(-1);
        }
        return itemLimitData;
    }

    public boolean K(String string) {
        boolean bl = true;
        for (ItemLimitData itemLimitData : this.K()) {
            if (!itemLimitData.C$src$Z$ttiu0t()) continue;
            if (itemLimitData.getName().toLowerCase().equals(string.toLowerCase())) {
                return true;
            }
            bl = false;
        }
        return bl;
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        if (jsonObject.get("id").getAsString().equalsIgnoreCase(this.P$src$Ljava_lang_String_$1ijjhmj())) {
            if (jsonObject.get("value").isJsonArray()) {
                JsonArray jsonArray = jsonObject.get("value").getAsJsonArray();
                ArrayList<ItemLimitData> arrayList = new ArrayList<ItemLimitData>(this.K());
                for (ItemLimitData itemLimitData : arrayList) {
                    this.k(itemLimitData);
                }
                for (JsonElement jsonElement : jsonArray) {
                    try {
                        ItemLimitData itemLimitData2 = new ItemLimitData("", -1);
                        itemLimitData2.W(jsonElement.getAsJsonObject());
                        this.n(itemLimitData2);
                    }
                    catch (Exception exception) {}
                }
            }
            return true;
        }
        return super.loadJson(jsonObject);
    }

    @Override
    public String c() {
        List<ItemLimitData> list = this.K();
        if (list.isEmpty()) {
            return "None";
        }
        if (list.size() == 1) {
            return ((ItemLimitData)list.get(0)).getName();
        }
        return ((ItemLimitData)list.get(0)).getName() + " +" + (list.size() - 1);
    }

    public boolean z(ItemStack itemStack) {
        if (this.K().isEmpty()) {
            return true;
        }
        for (ItemLimitData itemLimitData : this.K()) {
            if (!itemLimitData.W(itemStack)) continue;
            return true;
        }
        return false;
    }

    public boolean E(boolean bl) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        ItemStack itemStack = Minecraft.thePlayer().getHeldItemHand();
        if (this.A(itemStack)) {
            return true;
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            ItemStack itemStack2 = entityPlayerSP.i(EnumHand.p());
            if (bl && this.A(itemStack2)) {
                return true;
            }
        }
        return false;
    }

    public int M() {
        return this.Q;
    }

    public boolean y() {
        return this.E(this.A);
    }

    public boolean k(ItemStack itemStack) {
        for (ItemLimitData itemLimitData : this.K()) {
            if (!itemLimitData.W(itemStack)) continue;
            return false;
        }
        return true;
    }

    @Override
    public void parse(String string) {
    }

    @Override
    public LimitValue getALimit() {
        return this.t();
    }

    public void k(ItemLimitData itemLimitData) {
        try {
            this.K().remove(itemLimitData);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public Color K$src$Ljava_awt_Color_$y64ykf() {
        return this.J;
    }

    @Override
    public void F(List<ItemLimitData> list) {
        this.C(list);
    }

    public void C(List<ItemLimitData> list) {
        super.F(list);
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.toJson();
        JsonArray jsonArray = new JsonArray();
        for (ItemLimitData itemLimitData : this.K()) {
            jsonArray.add(itemLimitData.toJson());
        }
        jsonObject.add("value", (JsonElement)jsonArray);
        return jsonObject;
    }

    public boolean isValid(ItemStack itemStack, boolean bl) {
        if (bl && this.K().isEmpty()) {
            return false;
        }
        if (!bl && this.K().isEmpty()) {
            return true;
        }
        for (ItemLimitData itemLimitData : this.K()) {
            if (!itemLimitData.W(itemStack)) continue;
            return true;
        }
        return false;
    }

    public ItemLimitData D(String string) {
        for (ItemLimitData itemLimitData : this.K()) {
            if (!itemLimitData.getName().toLowerCase().equals(string.toLowerCase())) continue;
            return itemLimitData;
        }
        return null;
    }

    public static LimitValue N(Object object, String string, String string2, Color color, ItemLimitData ... itemLimitDataArray) {
        return LimitValue.w(object, string, string2, color, -1, Arrays.asList(itemLimitDataArray));
    }

    @Override
    public ItemLimitData j(String string, int n) {
        return this.Z(string, n);
    }

    public boolean A(ItemStack itemStack) {
        return this.isValid(itemStack, false);
    }

    @Override
    public boolean k() {
        List<ItemLimitData> list = this.K();
        List<ItemLimitData> list2 = this.P$src$Ljava_lang_Object_$qcpui1();
        return list.size() == list2.size() && list.equals(list2);
    }

    @Override
    public void o(List<ItemLimitData> list) {
        this.n(list);
    }

    @Override
    public void S() {
        super.o(new ArrayList<ItemLimitData>(this.P$src$Ljava_lang_Object_$qcpui1()));
    }

    static {
        r = new Color(0, 170, 0);
        R = new Color(170, 170, 170);
        G = new Color(170, 0, 0);
    }

    public LimitValue F(boolean bl) {
        this.A = bl;
        return this;
    }

    public static LimitValue n(Object object, String string, String string2, Color color, List<ItemLimitData> list) {
        return LimitValue.w(object, string, string2, color, -1, list);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private LimitValue(Object object, String string, String string2, Color color, int n, List<ItemLimitData> list) {
        super(object, string, string2, list);
        this.J = color;
        this.Q = n;
    }
}
