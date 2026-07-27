package gg.vape.wrapper.impl;

import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionEntry;
import gg.vape.wrapper.impl.PotionEntryResolveException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class PotionRegistry {
    public static final PotionEntry O;
    public static final PotionEntry u;
    public static final PotionEntry k;
    public static final PotionEntry d;
    public static final PotionEntry T;
    public static final PotionEntry Z;
    public static final PotionEntry w;
    public static final PotionEntry W;
    public static final PotionEntry v;
    private static final Map<Integer, PotionEntry> V;
    public static final PotionEntry L;
    public static final PotionEntry o;
    public static final PotionEntry c;
    public static final PotionEntry P;
    public static final PotionEntry f;
    public static final PotionEntry a;
    public static final PotionEntry h;
    public static final PotionEntry S;
    public static final PotionEntry B;
    public static final PotionEntry G;
    public static final PotionEntry K;
    public static final PotionEntry X;
    public static final PotionEntry R;
    public static final PotionEntry x;
    private static boolean A;
    public static final PotionEntry e;
    public static final PotionEntry z;
    private static boolean F;
    public static final PotionEntry i;
    public static final PotionEntry J;
    public static final PotionEntry N;
    public static final PotionEntry j;
    public static final PotionEntry t;
    public static final PotionEntry E;
    public static final PotionEntry Q;
    private static final Map<Short, PotionEntry> b;
    public static final PotionEntry C;
    public static final PotionEntry y;
    public static final PotionEntry r;
    public static final PotionEntry M;
    public static final PotionEntry l;
    public static final PotionEntry U;
    public static final PotionEntry H;

    public static List<PotionEntry> O() {
        ArrayList<PotionEntry> arrayList = new ArrayList<PotionEntry>();
        for (PotionEntry potionEntry : b.values()) {
            if (potionEntry.l() == null) continue;
            arrayList.add(potionEntry);
        }
        return arrayList;
    }

    public static boolean m() {
        boolean bl = PotionRegistry.g();
        return true;
    }

    @Nullable
    public static PotionEntry R(PotionEffect potionEffect) {
        return V.get(potionEffect.C());
    }

    @Nullable
    public static PotionEntry A(short s) {
        return b.get(s);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static void x(boolean bl) {
        A = bl;
    }

    public static boolean g() {
        return A;
    }

    public static List<PotionEntry> S() {
        return new ArrayList<PotionEntry>(b.values());
    }

    public static void d() {
        if (F) {
            return;
        }
        F = true;
        for (Field field : PotionRegistry.class.getDeclaredFields()) {
            if (!field.getType().equals(PotionEntry.class)) continue;
            PotionEntry potionEntry = null;
            try {
                potionEntry = (PotionEntry)field.get(null);
                potionEntry.S();
            }
            catch (PotionEntryResolveException potionEntryResolveException) {
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            if (potionEntry == null) continue;
            b.put(potionEntry.T(), potionEntry);
            V.put(potionEntry.D(), potionEntry);
        }
    }

    static {
        if (PotionRegistry.g()) {
            PotionRegistry.x(true);
        }
        String[] stringArray = new String[]{"Strength", "Weaving", "Health Boost", "Levitation", "Instant Health", "Absorption", "Glowing", "Dolphin's Grace", "Saturation", "Raid Omen", "Haste", "Mining Fatigue", "Slowness", "Luck", "Bad Omen", "Regeneration", "Infested", "Weakness", "Speed", "Hunger", "Fire Resistance", "Jump Boost", "Conduit Power", "Poison", "Hero of the Village", "Wind Charge", "Bad Luck", "Oozing", "Night Vision", "Slow Falling", "Nausea", "Resistance", "Invisibility", "Trial Omen", "Blindness", "Water Breathing", "Instant Damage", "Wither", "Darkness"};
        U = PotionEntry.o().w(stringArray[18]).s((short)0).J(ForgeVersion.MC_1_7_10, 1).X(ForgeVersion.MC_1_7_10).H();
        o = PotionEntry.o().w(stringArray[12]).s((short)1).J(ForgeVersion.MC_1_7_10, 2).X(ForgeVersion.MC_1_7_10).H();
        t = PotionEntry.o().w(stringArray[0]).s((short)2).J(ForgeVersion.MC_1_7_10, 5).X(ForgeVersion.MC_1_7_10).H();
        z = PotionEntry.o().w(stringArray[4]).s((short)3).J(ForgeVersion.MC_1_7_10, 6).X(ForgeVersion.MC_1_7_10).H();
        i = PotionEntry.o().w(stringArray[15]).s((short)4).J(ForgeVersion.MC_1_7_10, 10).X(ForgeVersion.MC_1_7_10).H();
        P = PotionEntry.o().w(stringArray[31]).s((short)5).J(ForgeVersion.MC_1_7_10, 11).X(ForgeVersion.MC_1_7_10).H();
        W = PotionEntry.o().w(stringArray[20]).s((short)6).J(ForgeVersion.MC_1_7_10, 12).X(ForgeVersion.MC_1_7_10).H();
        Z = PotionEntry.o().w(stringArray[21]).s((short)7).J(ForgeVersion.MC_1_7_10, 8).X(ForgeVersion.MC_1_7_10).H();
        K = PotionEntry.o().w(stringArray[34]).s((short)8).J(ForgeVersion.MC_1_7_10, 15).X(ForgeVersion.MC_1_7_10).H();
        E = PotionEntry.o().w(stringArray[10]).s((short)9).J(ForgeVersion.MC_1_7_10, 3).X(ForgeVersion.MC_1_7_10).H();
        X = PotionEntry.o().w(stringArray[30]).s((short)10).J(ForgeVersion.MC_1_7_10, 9).X(ForgeVersion.MC_1_7_10).H();
        u = PotionEntry.o().w(stringArray[11]).s((short)11).J(ForgeVersion.MC_1_7_10, 4).X(ForgeVersion.MC_1_7_10).H();
        B = PotionEntry.o().w(stringArray[36]).s((short)12).J(ForgeVersion.MC_1_7_10, 7).X(ForgeVersion.MC_1_7_10).H();
        c = PotionEntry.o().w(stringArray[35]).s((short)13).J(ForgeVersion.MC_1_7_10, 13).X(ForgeVersion.MC_1_7_10).H();
        R = PotionEntry.o().w(stringArray[32]).s((short)14).J(ForgeVersion.MC_1_7_10, 14).X(ForgeVersion.MC_1_7_10).H();
        T = PotionEntry.o().w(stringArray[28]).s((short)15).J(ForgeVersion.MC_1_7_10, 16).X(ForgeVersion.MC_1_7_10).H();
        j = PotionEntry.o().w(stringArray[19]).s((short)16).J(ForgeVersion.MC_1_7_10, 17).X(ForgeVersion.MC_1_7_10).H();
        M = PotionEntry.o().w(stringArray[17]).s((short)17).J(ForgeVersion.MC_1_7_10, 18).X(ForgeVersion.MC_1_7_10).H();
        r = PotionEntry.o().w(stringArray[23]).s((short)18).J(ForgeVersion.MC_1_7_10, 19).X(ForgeVersion.MC_1_7_10).H();
        G = PotionEntry.o().w(stringArray[37]).s((short)19).J(ForgeVersion.MC_1_7_10, 20).X(ForgeVersion.MC_1_7_10).H();
        J = PotionEntry.o().w(stringArray[2]).s((short)20).J(ForgeVersion.MC_1_7_10, 21).X(ForgeVersion.MC_1_7_10).H();
        d = PotionEntry.o().w(stringArray[5]).s((short)21).J(ForgeVersion.MC_1_7_10, 22).X(ForgeVersion.MC_1_7_10).H();
        Q = PotionEntry.o().w(stringArray[8]).s((short)22).J(ForgeVersion.MC_1_7_10, 23).X(ForgeVersion.MC_1_7_10).H();
        v = PotionEntry.o().w(stringArray[6]).s((short)23).J(ForgeVersion.MC_1_12_2, 24).X(ForgeVersion.MC_1_12_2).H();
        h = PotionEntry.o().w(stringArray[3]).s((short)24).J(ForgeVersion.MC_1_12_2, 25).X(ForgeVersion.MC_1_12_2).H();
        L = PotionEntry.o().w(stringArray[13]).s((short)25).J(ForgeVersion.MC_1_12_2, 26).X(ForgeVersion.MC_1_12_2).H();
        C = PotionEntry.o().w(stringArray[26]).s((short)26).J(ForgeVersion.MC_1_12_2, 27).X(ForgeVersion.MC_1_12_2).H();
        k = PotionEntry.o().w(stringArray[29]).s((short)27).J(ForgeVersion.MC_1_16_5, 28).X(ForgeVersion.MC_1_16_5).H();
        f = PotionEntry.o().w(stringArray[22]).s((short)28).J(ForgeVersion.MC_1_16_5, 29).X(ForgeVersion.MC_1_16_5).H();
        H = PotionEntry.o().w(stringArray[7]).s((short)29).J(ForgeVersion.MC_1_16_5, 30).X(ForgeVersion.MC_1_16_5).H();
        a = PotionEntry.o().w(stringArray[14]).s((short)30).J(ForgeVersion.MC_1_16_5, 31).X(ForgeVersion.MC_1_16_5).H();
        y = PotionEntry.o().w(stringArray[24]).s((short)31).J(ForgeVersion.MC_1_16_5, 32).X(ForgeVersion.MC_1_16_5).H();
        S = PotionEntry.o().w(stringArray[38]).s((short)32).X(ForgeVersion.MC_1_21_0).H();
        x = PotionEntry.o().w(stringArray[33]).s((short)33).X(ForgeVersion.MC_1_21_0).H();
        w = PotionEntry.o().w(stringArray[9]).s((short)34).X(ForgeVersion.MC_1_21_0).H();
        N = PotionEntry.o().w(stringArray[25]).s((short)35).X(ForgeVersion.MC_1_21_0).H();
        l = PotionEntry.o().w(stringArray[1]).s((short)36).X(ForgeVersion.MC_1_21_0).H();
        e = PotionEntry.o().w(stringArray[27]).s((short)37).X(ForgeVersion.MC_1_21_0).H();
        O = PotionEntry.o().w(stringArray[16]).s((short)38).X(ForgeVersion.MC_1_21_0).H();
        b = new LinkedHashMap<Short, PotionEntry>();
        V = new LinkedHashMap<Integer, PotionEntry>();
        F = false;
    }
}
