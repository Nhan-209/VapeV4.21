package gg.vape.mapping.runtime;

import gg.vape.Vape;
import gg.vape.mapping.runtime.ClassNameRemapTable;
import gg.vape.mapping.runtime.ClassNameRemapTableV100;
import gg.vape.mapping.runtime.ClassNameRemapTableV110;
import gg.vape.mapping.runtime.ClassNameRemapTableV23;
import gg.vape.mapping.runtime.ClassNameRemapTableV35V36Direct;
import gg.vape.mapping.runtime.ClassNameRemapTableV35V36Layered;
import gg.vape.mapping.runtime.ClassNameRemapTableV37;
import gg.vape.mapping.runtime.ClassNameRemapTableV50;
import gg.vape.mapping.runtime.ClassNameRemapTableV51;
import gg.vape.mapping.runtime.ClassNameRemapTableV54;
import gg.vape.mapping.runtime.ClassNameRemapTableV55;
import gg.vape.mapping.runtime.ClassNameRemapTableV56;
import gg.vape.mapping.runtime.ClassNameRemapTableV60;
import gg.vape.mapping.runtime.ClassNameRemapTableV61;
import gg.vape.mapping.runtime.MemberLookupSignature;
import gg.vape.mapping.runtime.MemberNameRemapTable;
import gg.vape.mapping.runtime.MemberNameRemapTableV100;
import gg.vape.mapping.runtime.MemberNameRemapTableV110;
import gg.vape.mapping.runtime.MemberNameRemapTableV35V36;
import gg.vape.mapping.runtime.MemberNameRemapTableV37;
import gg.vape.mapping.runtime.MemberNameRemapTableV50;
import gg.vape.mapping.runtime.MemberNameRemapTableV51;
import gg.vape.mapping.runtime.MemberNameRemapTableV54;
import gg.vape.mapping.runtime.MemberNameRemapTableV55;
import gg.vape.mapping.runtime.MemberNameRemapTableV56;
import gg.vape.mapping.runtime.MemberNameRemapTableV60;
import gg.vape.mapping.runtime.MemberNameRemapTableV61;
import gg.vape.runtime.NativeBridge;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class RuntimeNameMappingRegistry {
    private static MemberNameRemapTable D;
    private static final Map<String, String> Y;
    private static final ClassNameRemapTable l;

    public static void registerClassName(String string, String string2) {
        Y.put(string.replace("/", "."), string2.replace("/", "."));
        NativeBridge.scm(string, string2);
    }

    @Nullable
    public static MemberLookupSignature lookupMethodMapping(Class clazz, String string) {
        if (D == null) {
            return null;
        }
        return D.B(clazz, string);
    }

    @Nullable
    public static String lookupRegisteredClassName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return Y.get(clazz.getName());
    }

    @Nullable
    public static MemberLookupSignature lookupFieldMapping(Class clazz, String string) {
        if (D == null) {
            return null;
        }
        return D.U(clazz, string);
    }

    public static void initializeRegistry() {
        int n = ForgeVersion.c();
        switch (n) {
            case 35: 
            case 36: {
                D = new MemberNameRemapTableV35V36();
                break;
            }
            case 37: {
                D = new MemberNameRemapTableV37();
                break;
            }
            case 50: {
                D = new MemberNameRemapTableV50();
                break;
            }
            case 51: {
                D = new MemberNameRemapTableV51();
                break;
            }
            case 54: {
                D = new MemberNameRemapTableV54();
                break;
            }
            case 55: {
                D = new MemberNameRemapTableV55();
                break;
            }
            case 56: {
                D = new MemberNameRemapTableV56();
                break;
            }
            case 60: {
                D = new MemberNameRemapTableV60();
                break;
            }
            case 61: {
                D = new MemberNameRemapTableV61();
                break;
            }
            case 100: {
                D = new MemberNameRemapTableV100();
                break;
            }
            case 110: {
                D = new MemberNameRemapTableV110();
            }
        }
        if (D != null) {
            D.T();
        }
    }

    static {
        Y = new LinkedHashMap<String, String>();
        int n = ForgeVersion.c();
        switch (n) {
            case 23: {
                l = new ClassNameRemapTableV23();
                break;
            }
            case 35: 
            case 36: {
                if (Vape.INSTANCE.isNativeAvailable()) {
                    l = new ClassNameRemapTableV35V36Direct();
                    break;
                }
                l = new ClassNameRemapTableV35V36Layered();
                break;
            }
            case 37: {
                l = new ClassNameRemapTableV37();
                break;
            }
            case 50: {
                l = new ClassNameRemapTableV50();
                break;
            }
            case 51: {
                l = new ClassNameRemapTableV51();
                break;
            }
            case 54: {
                l = new ClassNameRemapTableV54();
                break;
            }
            case 55: {
                l = new ClassNameRemapTableV55();
                break;
            }
            case 56: {
                l = new ClassNameRemapTableV56();
                break;
            }
            case 60: {
                l = new ClassNameRemapTableV60();
                break;
            }
            case 61: {
                l = new ClassNameRemapTableV61();
                break;
            }
            case 100: {
                l = new ClassNameRemapTableV100();
                break;
            }
            case 110: {
                l = new ClassNameRemapTableV110();
                break;
            }
            default: {
                l = null;
            }
        }
        if (ForgeVersion.MC_1_16_5_ACTUAL.Y() && !Vape.INSTANCE.isNativeAvailable()) {
            ClassNameRemapTable.m = true;
            new ClassNameRemapTableV35V36Direct();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static String remapClassName(String string) {
        if (l == null) {
            return null;
        }
        return l.n(string);
    }
}

