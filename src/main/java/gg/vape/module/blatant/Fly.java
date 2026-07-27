package gg.vape.module.blatant;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventMotion;
import gg.vape.event.impl.EventMove;
import gg.vape.event.impl.EventPreMotion;
import gg.vape.event.impl.EventPreMove;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.util.HashMap;
import java.util.Map;

public class Fly
extends Mod {
    private final NumberValue verticalSpeed;
    private static final Map stringMap;
    private static final long longKeyB;
    private static final String[] stringTableA;
    private final NumberValue speed = NumberValue.create(this, "Speed", "#.#", "", 0.1, 0.5, 5.0, 0.1, "Speed for Normal fly mode.");
    private static final String[] stringTableB;
    private static final long longKeyA;

    private static CallSite bootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        return new MutableCallSite(methodType);
    }

    private double randomInRange(double d, double d2) {
        return Math.random() * (d - d2) + d2;
    }

    private static Object invokeDynamicTarget(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        if (objectArray != null && objectArray.length >= 2 && objectArray[0] instanceof Integer && objectArray[1] instanceof Long) {
            return Fly.decodeStringPair((Integer)objectArray[0], (Long)objectArray[1]);
        }
        return "";
    }

    @EventHandler
    public void onMotionUpdate(EventPreMotion eventPreMotion) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        EventMotion.setOnGround(false);
        entityPlayerSP.U(false);
    }

    @Override
    public void onEnable() {
        Vape.INSTANCE.getClientSettings().k(this);
    }

    private void applyHorizontalMove(EventMove eventMove, double d) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d2 = entityPlayerSP.movementInput().D();
        double d3 = entityPlayerSP.movementInput().T();
        float f = entityPlayerSP.J();
        if (d2 == 0.0 && d3 == 0.0) {
            eventMove.setX(0.0).setZ(0.0);
            return;
        }
        if (d2 != 0.0) {
            if (d3 > 0.0) {
                f += d2 > 0.0 ? -45.0f : 45.0f;
            } else if (d3 < 0.0) {
                f += d2 > 0.0 ? 45.0f : -45.0f;
            }
            d3 = 0.0;
            if (d2 > 0.0) {
                d2 = 1.0;
            } else if (d2 < 0.0) {
                d2 = -1.0;
            }
        }
        eventMove.setX(d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f)));
        eventMove.setZ(d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f)));
    }


    private static String decodeUtf8(byte[] byArray) {
        int n = byArray.length;
        char[] cArray = new char[n];
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            char c;
            int n3 = byArray[i] & 0xFF;
            if (n3 < 192) {
                cArray[n2++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((n3 & 0x1F) << 6);
                n3 = byArray[++i];
                cArray[n2++] = (char)(c | n3 & 0x3F);
                continue;
            }
            if (i >= n - 2) continue;
            c = (char)((n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (n3 & 0x3F) << 6);
            n3 = byArray[++i];
            cArray[n2++] = (char)(c | n3 & 0x3F);
        }
        return new String(cArray, 0, n2);
    }

    @Override
    public void onDisable() {
    }

    public double G() {
        double d = 0.2873;
        if (Minecraft.thePlayer().i(PotionRegistry.U)) {
            int n = Minecraft.thePlayer().b(PotionRegistry.U).L();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public Fly() {
        super("Fly", 49630, Category.w, "Makes you go zoom.");
        this.verticalSpeed = NumberValue.create(this, "Vertical Speed", "#.#", "", 0.1, 0.2, 5.0, 0.1, "Speed for Normal vertical fly mode.");
        this.R(false);
        this.addValue(this.speed, this.verticalSpeed);
    }

    @Override
    public boolean isBlatantMod() {
        return true;
    }

    @EventHandler
    public void M(EventPreMove eventPreMove) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        double d = Minecraft.gameSettings().O().u() ? (Double)this.verticalSpeed.java_lang_Object_K() : (Minecraft.gameSettings().d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0().u() ? -((Double)this.verticalSpeed.java_lang_Object_K()).doubleValue() : 0.0);
        eventPreMove.setY(d);
        entityPlayerSP.k(d);
        this.applyHorizontalMove(eventPreMove, Math.max((Double)this.speed.java_lang_Object_K(), this.G()));
    }

    private static String decodeStringPair(int n, long l) {
        return "";
    }

    static {
        longKeyA = 0L;
        longKeyB = 0L;
        stringMap = new HashMap(13);
        stringTableA = new String[8];
        stringTableB = new String[8];
    }
}

