package gg.vape.value;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.utils.RotationUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.Value;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import java.util.Arrays;
import java.util.List;

public class EntityTargetFilterValue
extends Value<Boolean[], EntityTargetFilterValue> {
    private final BooleanValue u = BooleanValue.D(this, "Players" + this.P$src$Ljava_lang_String_$1ijjhmj(), "Players", true);
    private final BooleanValue D;
    private final List<BooleanValue> R;
    private final BooleanValue G;
    private final BooleanValue b;
    private final BooleanValue Z;
    boolean V;
    private final BooleanValue O;
    private final BooleanValue o = BooleanValue.D(this, "Mobs" + this.P$src$Ljava_lang_String_$1ijjhmj(), "Mobs", false);

    @Override
    public String c() {
        String string;
        StringBuilder stringBuilder = new StringBuilder();
        if (this.u.L().booleanValue()) {
            stringBuilder.append("Players ");
        }
        if (this.o.L().booleanValue()) {
            stringBuilder.append("Mobs ");
        }
        if (this.Z.L().booleanValue()) {
            stringBuilder.append("Peaceful ");
        }
        if (this.O.L().booleanValue()) {
            stringBuilder.append("Neutral ");
        }
        if ((string = stringBuilder.toString().trim()).isEmpty()) {
            string = "None";
        }
        if (this.G.L().booleanValue() || this.b.L().booleanValue() || this.D.L().booleanValue()) {
            stringBuilder.append(" | ");
            if (this.G.L().booleanValue()) {
                stringBuilder.append("NoNaked ");
            }
            if (this.b.L().booleanValue()) {
                stringBuilder.append("NoInvis ");
            }
            if (this.D.L().booleanValue()) {
                stringBuilder.append("NoWalls");
            }
            string = stringBuilder.toString().trim();
        }
        return string;
    }

    public BooleanValue S$src$Lgg_vape_value_BooleanValue_$7aakrq() {
        return this.O;
    }

    public List<BooleanValue> i() {
        return this.R;
    }

    public EntityTargetFilterValue(Object object, String string) {
        super(object, string, new Boolean[7]);
        this.Z = BooleanValue.D(this, "Peaceful" + this.P$src$Ljava_lang_String_$1ijjhmj(), "Peaceful", false);
        this.O = BooleanValue.D(this, "Neutral" + this.P$src$Ljava_lang_String_$1ijjhmj(), "Neutral", false);
        this.G = BooleanValue.D(this, "Ignore Naked" + this.P$src$Ljava_lang_String_$1ijjhmj(), "Ignore naked", false);
        this.b = BooleanValue.D(this, "Ignore invisible" + this.P$src$Ljava_lang_String_$1ijjhmj(), "Ignore invisible", false);
        this.D = BooleanValue.D(this, "Ignore behind walls" + this.P$src$Ljava_lang_String_$1ijjhmj(), "Ignore behind walls", false);
        this.R = Arrays.asList(this.u, this.o, this.Z, this.O, this.G, this.b, this.D);
        for (int i = 0; i < this.R.size(); ++i) {
            ((Boolean[])this.P$src$Ljava_lang_Object_$qcpui1())[i] = (Boolean)this.R.get(i).P$src$Ljava_lang_Object_$qcpui1();
            this.R.get(i).B(this::lambda$new$0);
        }
    }

    public BooleanValue x() {
        return this.D;
    }

    public BooleanValue r$src$Lgg_vape_value_BooleanValue_$167auuf() {
        return this.Z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (BooleanValue booleanValue : this.R) {
            stringBuilder.append(booleanValue.L() != false ? "1" : "0");
        }
        return stringBuilder.toString();
    }

    public void U(Boolean[] booleanArray) {
        for (int i = 0; i < this.R.size(); ++i) {
            this.R.get(i).o(booleanArray[i]);
        }
        super.o(booleanArray);
    }

    public BooleanValue q$src$Lgg_vape_value_BooleanValue_$4eyax4() {
        return this.G;
    }


    @Override
    public void parse(String string) {
        for (int i = 0; i < string.toCharArray().length && this.R.size() > i; ++i) {
            this.R.get(i).o(string.charAt(i) == '1');
        }
    }

    @Override
    public boolean loadJson(JsonObject jsonObject) {
        boolean bl = super.loadJson(jsonObject);
        this.parse(this.toString());
        return bl;
    }

    @Override
    public JsonObject H(boolean bl) {
        JsonObject jsonObject = this.toJson();
        jsonObject.addProperty("value", this.toString());
        return jsonObject;
    }

    public BooleanValue f() {
        return this.o;
    }

    @Override
    public boolean k() {
        return this.R.stream().allMatch(Value::k);
    }

    @Override
    public void S() {
        super.S();
        if (this.N$src$Z$1a793rp()) {
            for (BooleanValue booleanValue : this.R) {
                booleanValue.S();
            }
        }
    }

    private void lambda$new$0(BooleanValue booleanValue) {
        if (this.V) {
            return;
        }
        this.V = true;
        this.U(this.H());
        this.V = false;
    }

    public BooleanValue E() {
        return this.b;
    }

    public EntityTargetFilterValue e() {
        EntityTargetFilterValue entityTargetFilterValue = new EntityTargetFilterValue(null, this.P$src$Ljava_lang_String_$1ijjhmj());
        return entityTargetFilterValue;
    }

    @Override
    public EntityTargetFilterValue getALimit() {
        return this.e();
    }

    public Boolean[] H() {
        Boolean[] booleanArray = new Boolean[this.R.size()];
        for (int i = 0; i < this.R.size(); ++i) {
            booleanArray[i] = this.R.get(i).L();
        }
        return booleanArray;
    }

    public BooleanValue D() {
        return this.u;
    }

    public static EntityTargetFilterValue W(Mod mod) {
        return new EntityTargetFilterValue(mod, "Target Settings " + mod.getName());
    }

    public boolean c(Entity entity) {
        boolean bl;
        if (entity.isNull()) {
            return false;
        }
        if (ClientSettings.E(entity)) {
            return false;
        }
        if (!this.Z.L().booleanValue() && entity.isInstance(MappedClasses.zS)) {
            return false;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entity.equals(entityPlayerSP)) {
            return false;
        }
        if (!entity.isInstance(MappedClasses.zm)) {
            return false;
        }
        if (ForgeVersion.MC_1_7_10.Y() && entity.isInstance(MappedClasses.FT)) {
            return false;
        }
        EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getObject());
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f) {
            return false;
        }
        if (this.b.L().booleanValue() && RotationUtil.k(entityLivingBase)) {
            return false;
        }
        if (this.D.L().booleanValue() && !entityPlayerSP.canEntityBeSeen(entity)) {
            return false;
        }
        if (Vape.INSTANCE.getFriendManager().isFriend(entityLivingBase)) {
            return false;
        }
        boolean bl2 = entity.isInstance(MappedClasses.lG);
        Class clazz = ForgeVersion.MC_1_21_4.d() ? MappedClasses.Yw : MappedClasses.Fr;
        boolean bl3 = entity.isInstance(clazz) || entity.isInstance(MappedClasses.Zo) || entity.isInstance(MappedClasses.ur) || entity.isInstance(MappedClasses.Z8);
        boolean bl4 = bl = entity.isInstance(MappedClasses.ZP) || entity.isInstance(MappedClasses.Fb) || entity.isInstance(MappedClasses.qf);
        if (bl2) {
            if (!this.u.L().booleanValue()) {
                return false;
            }
            if (Vape.INSTANCE.getEnemyManager().q(entity.getName())) {
                return true;
            }
            if (this.G.L().booleanValue() && RotationUtil.b$src$Z$reqs95(entityLivingBase)) {
                return false;
            }
            if (Vape.INSTANCE.getClientSettings().e(entityPlayerSP, entityLivingBase)) {
                return false;
            }
            return !Vape.INSTANCE.getClientSettings().J(entityLivingBase);
        }
        if (bl3 && !this.o.L().booleanValue()) {
            return false;
        }
        if (bl && !this.Z.L().booleanValue()) {
            return false;
        }
        return bl3 || bl || this.Z.L() != false;
    }
}
