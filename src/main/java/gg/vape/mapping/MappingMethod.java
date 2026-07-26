package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.asm.helper.DescUtils;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.mapping.MappingProfileSnapshotRegistry;
import gg.vape.mapping.NativeMappedMemberInvoker;
import gg.vape.mapping.access.GeneratedAccessorFactory;
import gg.vape.mapping.access.MethodInvoker;
import gg.vape.mapping.runtime.MemberLookupSignature;
import gg.vape.mapping.runtime.RuntimeNameMappingRegistry;
import gg.vape.wrapper.impl.ForgeVersion;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MappingMethod {
    private final String Y;
    private final Class<?> O;
    private static int a;
    private Constructor<?> V;
    private final boolean m;
    private Class<?> B;
    private Class<?> T;
    private final int p;
    private boolean v;
    private boolean k;
    private final String Q;
    private final Class<?>[] o;
    private final String l;
    private Class<?>[] M;
    private MethodInvoker H;
    private final boolean E;
    private Method P;
    private final String z;
    private boolean g;
    private final String w;
    private static String d;
    private final Mapping S;

    public char[] K(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (char[])this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (char[])this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.m(this.p, object, objectArray);
    }

    public void J(Class clazz) {
        if ((Vape.INSTANCE.isMappingsRemapped() || Vape.INSTANCE.isForgeRemapInactive()) && !this.v) {
            if (this.w.equals("<init>") || this.w.equals("<clinit>")) {
                this.D(clazz);
                return;
            }
            try {
                Class<? extends MethodInvoker> clazz2 = GeneratedAccessorFactory.A(clazz, this);
                if (clazz2 == null) {
                    return;
                }
                this.H = clazz2.newInstance();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    public Object[] invokeObjectArray(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (Object[])this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (Object[])this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.s(this.p, object, objectArray);
    }

    public void c(Object object, Object ... objectArray) {
        if (this.H != null) {
            this.H.invoke(object, objectArray);
        } else if (this.P != null) {
            this.l(object, objectArray);
        } else {
            NativeMappedMemberInvoker.c(this.p, object, objectArray);
        }
    }

    public static String A() {
        return d;
    }

    public String B() {
        return this.z;
    }

    public String v() {
        if (Vape.INSTANCE.isForgeRemapInactive()) {
            return this.w;
        }
        return NativeMappedMemberInvoker.gmn(this.p);
    }

    public Class<?>[] D() {
        return this.o;
    }

    public boolean[] K$src$AZ$hb7l1t(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (boolean[])this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (boolean[])this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.l(this.p, object, objectArray);
    }

    public long[] w$src$AJ$19yiaod(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (long[])this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (long[])this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.p(this.p, object, objectArray);
    }

    public short A(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (Short)this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (Short)this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.f(this.p, object, objectArray);
    }

    public MappingMethod g() {
        this.v = false;
        String string = MappingMethod.A();
        try {
            if (Vape.INSTANCE.isForgeRemapInactive()) {
                this.X();
            } else if (this.m) {
                String string2 = this.w;
                if (!Vape.INSTANCE.isVanillaMinecraftPresent()) {
                    string2 = string2 + ":" + this.Q;
                }
                NativeMappedMemberInvoker.a(this.p, this.O, this.l, this.w, this.Q, this.Y, this.E);
            } else {
                NativeMappedMemberInvoker.b(this.p, this.O, this.w, this.Q, this.E);
            }
            if (!this.k && !Vape.INSTANCE.isForgeRemapInactive()) {
                this.J(this.O);
            }
        }
        catch (Throwable throwable) {
            if (this.g) {
                MappingProfileSnapshotRegistry.O();
            } else {
                MappingProfileSnapshotRegistry.X(this);
            }
            this.v = true;
        }
        return this;
    }

    public float[] i(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (float[])this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (float[])this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.q(this.p, object, objectArray);
    }

    public Class O() {
        return this.O;
    }

    private void X() throws Throwable {
        if (this.w.equals("<init>") || this.w.equals("<clinit>")) {
            this.D(this.O);
            if (this.V == null) {
                throw new NoSuchMethodException(this.O.getName() + "#" + this.w + this.Q);
            }
            return;
        }
        this.J(this.O);
        if (this.H != null) {
            return;
        }
        this.P = this.c(this.O, this.w, this.M);
        if (this.P == null) {
            throw new NoSuchMethodException(this.O.getName() + "#" + this.w + this.Q);
        }
        this.P.setAccessible(true);
    }

    public Mapping Z() {
        return this.S;
    }

    public Object O(Object ... objectArray) {
        if (this.V != null) {
            try {
                return this.V.newInstance(objectArray);
            }
            catch (Throwable throwable) {
                Vape.logThrowable(throwable);
            }
        }
        try {
            return NativeMappedMemberInvoker.ccc(this.p, this.O, objectArray);
        }
        catch (Exception exception) {
            return MappingMethod.<RuntimeException, Object>sneakyThrow(exception);
        }
    }

    public Class<?>[] T() {
        return this.M;
    }

    public double[] H(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (double[])this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (double[])this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.r(this.p, object, objectArray);
    }

    public short[] o(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (short[])this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (short[])this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.n(this.p, object, objectArray);
    }

    public Object Y(Object object, Object ... objectArray) {
        return NativeMappedMemberInvoker.jjj(this.p, object, objectArray);
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable, T> T sneakyThrow(Throwable throwable) throws E {
        throw (E)throwable;
    }

    private Object l(Object object, Object ... objectArray) {
        try {
            return this.P.invoke(object, objectArray);
        }
        catch (Throwable throwable) {
            Vape.logThrowable(throwable);
            return null;
        }
    }

    public String j() {
        return this.Q;
    }

    public MappingMethod(Mapping mapping, Class clazz, String string, boolean bl, boolean bl2, boolean bl3, Class clazz2, Class ... classArray) {
        String string2 = MappingMethod.A();
        String string3 = string2;
        if (ForgeVersion.MC_26_1.d()) {
            bl = false;
        }
        this.S = mapping;
        this.O = clazz;
        this.l = MappedClasses.b(clazz).replace(".", "/");
        this.z = string;
        this.T = clazz2;
        this.o = classArray;
        MemberLookupSignature memberLookupSignature = RuntimeNameMappingRegistry.lookupMethodMapping(clazz, string);
        if (memberLookupSignature != null) {
            this.w = memberLookupSignature.M;
            this.B = memberLookupSignature.a != null ? memberLookupSignature.a : clazz2;
            this.M = memberLookupSignature.v.length > 0 ? memberLookupSignature.v : classArray;
            this.m = memberLookupSignature.H() != null ? memberLookupSignature.H() : bl;
        } else {
            this.w = string;
            this.B = clazz2;
            this.M = classArray;
            this.m = bl;
        }
        this.E = bl2;
        this.g = bl3;
        this.p = ++a;
        this.Q = DescUtils.S(false, this.B, this.M);
        this.Y = DescUtils.S(true, this.B, this.M);
    }

    public Class<?> P() {
        return this.B;
    }

    public void D(Class clazz) {
        try {
            this.V = clazz.getDeclaredConstructor(this.T());
            this.V.setAccessible(true);
        }
        catch (Throwable throwable) {
            Vape.logThrowable(throwable);
        }
    }

    public int R() {
        return this.p;
    }

    public Class<?> R$src$Ljava_lang_Class_$n2d0qz() {
        return this.T;
    }

    public byte w(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (Byte)this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (Byte)this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.ddd(this.p, object, objectArray);
    }

    public long n(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (Long)this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (Long)this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.h(this.p, object, objectArray);
    }

    public char u(Object object, Object ... objectArray) {
        if (this.H != null) {
            return ((Character)this.H.invoke(object, objectArray)).charValue();
        }
        if (this.P != null) {
            return ((Character)this.l(object, objectArray)).charValue();
        }
        return NativeMappedMemberInvoker.e(this.p, object, objectArray);
    }

    public boolean s() {
        return this.m;
    }

    static {
        MappingMethod.f("YeDWk");
        a = 0;
    }

    public Object L(Object object, Object ... objectArray) {
        if (this.H != null) {
            return this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.k(this.p, object, objectArray);
    }

    public boolean d() {
        return this.E;
    }

    public boolean e(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (Boolean)this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (Boolean)this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.d(this.p, object, objectArray);
    }

    public void B(Object object, Object ... objectArray) {
        NativeMappedMemberInvoker.bbb(this.p, object, objectArray);
    }

    public static MappingMethod G(MappingMethodBuilder mappingMethodBuilder) {
        MemberLookupSignature memberLookupSignature = RuntimeNameMappingRegistry.lookupMethodMapping(mappingMethodBuilder.F$src$Ljava_lang_Class_$100ldxh(), mappingMethodBuilder.Y());
        if (memberLookupSignature != null) {
            mappingMethodBuilder.v(memberLookupSignature.M);
            if (memberLookupSignature.a != null) {
                mappingMethodBuilder.l(memberLookupSignature.a);
            }
            if (memberLookupSignature.v.length > 0) {
                mappingMethodBuilder.G(memberLookupSignature.v);
            }
            if (memberLookupSignature.H() != null) {
                mappingMethodBuilder.S(memberLookupSignature.H());
            }
        }
        MappingMethod mappingMethod = new MappingMethod(mappingMethodBuilder.w(), mappingMethodBuilder.F$src$Ljava_lang_Class_$100ldxh(), mappingMethodBuilder.Y(), mappingMethodBuilder.z$src$Z$103hrpe(), mappingMethodBuilder.D(), mappingMethodBuilder.B(), mappingMethodBuilder.X(), mappingMethodBuilder.t());
        if (mappingMethodBuilder.o()) {
            mappingMethod.k = true;
        }
        MappingMethod mappingMethod2 = mappingMethod;
        return mappingMethod2.g();
    }

    public void F(Object object) {
        if (this.H != null) {
            this.H.invoke(object, new Object[0]);
        } else if (this.P != null) {
            this.l(object, new Object[0]);
        } else {
            NativeMappedMemberInvoker.c(this.p, object, new Object[0]);
        }
    }

    public double F(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (Double)this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (Double)this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.j(this.p, object, objectArray);
    }

    public int Z(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (Integer)this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (Integer)this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.g(this.p, object, objectArray);
    }

    public boolean h() {
        return this.v;
    }

    private Method c(Class<?> clazz, String string, Class<?>[] classArray) {
        try {
            return clazz.getMethod(string, classArray);
        }
        catch (NoSuchMethodException noSuchMethodException) {
            for (Class<?> clazz2 = clazz; clazz2 != null; clazz2 = clazz2.getSuperclass()) {
                try {
                    return clazz2.getDeclaredMethod(string, classArray);
                }
                catch (NoSuchMethodException noSuchMethodException2) {
                    continue;
                }
            }
            return null;
        }
    }

    public String V() {
        return this.w;
    }

    public static void f(String string) {
        d = string;
    }

    public float s(Object object, Object ... objectArray) {
        if (this.H != null) {
            return ((Float)this.H.invoke(object, objectArray)).floatValue();
        }
        if (this.P != null) {
            return ((Float)this.l(object, objectArray)).floatValue();
        }
        return NativeMappedMemberInvoker.i(this.p, object, objectArray);
    }

    public int[] z(Object object, Object ... objectArray) {
        if (this.H != null) {
            return (int[])this.H.invoke(object, objectArray);
        }
        if (this.P != null) {
            return (int[])this.l(object, objectArray);
        }
        return NativeMappedMemberInvoker.o(this.p, object, objectArray);
    }
}
