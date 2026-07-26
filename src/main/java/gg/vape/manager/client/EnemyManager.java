package gg.vape.manager.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import gg.vape.friend.Enemy;
import gg.vape.friend.ui.EnemySettingsFrame;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.none.ClientSettings;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RayTraceResult;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class EnemyManager {
    public BooleanValue z;
    public ColorValue i;
    public BooleanValue L;
    public BooleanValue p;
    public BooleanValue O;
    private final Set<Enemy> x = new HashSet<Enemy>();
    private static int[] U;

    public EnemyManager() {
        this.L = BooleanValue.create(this, "Use Enemies", true);
        this.z = BooleanValue.create(this, "Use Alias", true);
        this.O = BooleanValue.create(this, "Spoof alias", false, "This will make the enemies name be replaced in chat with their alias.\nApplies on regular Nametags as well");
        this.p = BooleanValue.create(this, "Use color", true, "Re-colors certain render modules to use \"Enemies Color\" on enemies");
        this.i = ColorValue.L(this, "Enemies Color", new Color(244, 66, 66));
        this.O.B(this::lambda$new$0);
    }

    public static void x(int[] nArray) {
        U = nArray;
    }

    public void I() {
        this.y().clear();
    }

    public boolean q(String string) {
        if (!this.L.L().booleanValue()) {
            return false;
        }
        Enemy enemy = this.A(string);
        return enemy != null;
    }

    private void lambda$new$0(BooleanValue booleanValue) {
        this.f();
    }

    public void Q(Enemy enemy) {
        Enemy enemy2 = this.A(enemy.y());
        if (enemy2 != null) {
            this.x.remove(enemy2);
        }
        this.x.add(enemy);
        this.f();
    }

    public Enemy f(String string, boolean bl) {
        if (this.L.L().booleanValue() || !bl) {
            Enemy enemy = this.A(string);
            if (enemy != null) {
                return null;
            }
            return enemy;
        }
        return null;
    }

    static {
        EnemyManager.x(null);
    }

    public boolean y(EntityLivingBase entityLivingBase) {
        Enemy enemy = this.r(entityLivingBase.getName());
        if (enemy != null) {
            return enemy.t();
        }
        return false;
    }

    public static int[] A() {
        return U;
    }

    public boolean R(EntityLivingBase entityLivingBase) {
        return this.q(entityLivingBase.getName());
    }

    public void f() {
        if (Minecraft.theWorld().isNull()) {
            return;
        }
        for (Object e : Minecraft.theWorld().X()) {
            new EntityPlayer(e).w$src$V$1iu649y();
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public JsonArray H() {
        JsonArray jsonArray = new JsonArray();
        for (Enemy enemy : this.y()) {
            jsonArray.add((JsonElement)enemy.s$src$Lcom_google_gson_JsonObject_$hkaqtu());
        }
        return jsonArray;
    }

    public void d(JsonArray jsonArray) {
        if (jsonArray.size() == 0) {
            return;
        }
        this.I();
        for (int i = 0; i < jsonArray.size(); ++i) {
            try {
                JsonElement jsonElement = jsonArray.get(i);
                if (!jsonElement.isJsonObject() || jsonElement.isJsonNull()) continue;
                Enemy enemy = Enemy.c(jsonElement.getAsJsonObject());
                this.Q(enemy);
                continue;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        ClientSettings.g(EnemySettingsFrame.class).Q$src$V$1u5tkk5();
    }

    public Enemy A(String string) {
        for (Enemy enemy : this.x) {
            if (!enemy.y().equalsIgnoreCase(string)) continue;
            return enemy;
        }
        return null;
    }

    public Enemy r(String string) {
        return this.f(string, true);
    }

    public void s(Enemy enemy) {
        this.x.remove(enemy);
        this.f();
    }

    public Set<Enemy> y() {
        return this.x;
    }

    public void c() {
        RayTraceResult rayTraceResult = Minecraft.p$src$Lgg_vape_wrapper_impl_RayTraceResult_$5rw6n0();
        if (rayTraceResult.isNull()) {
            return;
        }
        Entity entity = rayTraceResult.getEntity();
        if (entity.isNull()) {
            return;
        }
        if (entity.isInstance(MappedClasses.lG)) {
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(entity);
            String string = entityOtherPlayerMP.getName();
            Enemy enemy = this.A(string);
            if (enemy != null) {
                this.s(enemy);
            } else {
                this.Q(new Enemy(string, string));
            }
        }
    }
}

