package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.asm.helper.DescUtils;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingFieldBuilder;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingProfileSnapshotRegistry;
import gg.vape.mapping.NativeMappedMemberInvoker;
import gg.vape.mapping.runtime.MemberLookupSignature;
import gg.vape.mapping.runtime.RuntimeNameMappingRegistry;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;
import java.lang.reflect.Field;

public class MappingField {
    private final String S;
    private boolean c;
    private final Class<?> m;
    private final String U;
    private final Class<?> f;
    private final boolean V;
    private static int[] M;
    private final Mapping G;
    private final String R;
    private final Class<?> w;
    private final String H;
    private static int F;
    private Field g;
    private final String o;
    private final int x;
    private final boolean P;
    private boolean n;

    public void setBoolean(Object object, boolean bl) {
        if (this.g != null) {
            try {
                this.g.setBoolean(object, bl);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.ll(this.x, object, bl));
    }

    public float getFloat(Object object) {
        if (this.g != null) {
            try {
                return this.g.getFloat(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.aa(this.x, object));
    }

    public char[] getCharArray(Object object) {
        if (this.g != null) {
            try {
                return (char[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return NativeMappedMemberInvoker.ee(this.x, object);
    }

    public double[] getDoubleArray(Object object) {
        if (this.g != null) {
            try {
                return (double[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.jj(this.x, object));
    }

    public void setFloatArray(Object object, float[] fArray) {
        if (this.g != null) {
            try {
                this.g.set(object, fArray);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.yy(this.x, object, fArray));
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    @FunctionalInterface
    private interface CheckedSupplier<T> {
        T get() throws Exception;
    }

    @FunctionalInterface
    private interface CheckedRunnable {
        void run() throws Exception;
    }

    private static <T> T unchecked(CheckedSupplier<T> supplier) {
        try {
            return supplier.get();
        }
        catch (Exception exception) {
            return MappingField.<RuntimeException, T>sneakyThrow(exception);
        }
    }

    private static void unchecked(CheckedRunnable runnable) {
        try {
            runnable.run();
        }
        catch (Exception exception) {
            MappingField.<RuntimeException, Void>sneakyThrow(exception);
        }
    }

    @SuppressWarnings("unchecked")
    private static <E extends Throwable, T> T sneakyThrow(Throwable throwable) throws E {
        throw (E)throwable;
    }

    public void setInt(Object object, int n) {
        if (this.g != null) {
            try {
                this.g.setInt(object, n);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.oo(this.x, object, n));
    }

    public Mapping U() {
        return this.G;
    }

    public String w() {
        return this.H;
    }

    public byte[] u(Object object) {
        if (this.g != null) {
            try {
                return (byte[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return NativeMappedMemberInvoker.hhh(this.x, object);
    }

    public void setChar(Object object, char c) {
        if (this.g != null) {
            try {
                this.g.setChar(object, c);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.mm(this.x, object, c));
    }

    public MappingField Y() {
        if ((Vape.INSTANCE.isMappingsRemapped() || Vape.INSTANCE.isForgeRemapInactive()) && !this.c) {
            this.g = this.H$src$Ljava_lang_reflect_Field_$1cv5a8g();
        }
        return this;
    }

    public boolean getBoolean(Object object) {
        if (this.g != null) {
            try {
                return this.g.getBoolean(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.v(this.x, object));
    }

    public void setLong(Object object, long l) {
        if (this.g != null) {
            try {
                this.g.setLong(object, l);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.pp(this.x, object, l));
    }

    public Field H$src$Ljava_lang_reflect_Field_$1cv5a8g() {
        try {
            String string = this.J();
            for (Class<?> clazz = this.m; clazz != null; clazz = clazz.getSuperclass()) {
                try {
                    Field field = clazz.getDeclaredField(string);
                    field.setAccessible(true);
                    return field;
                }
                catch (NoSuchFieldException noSuchFieldException) {
                    continue;
                }
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
        return null;
    }

    public static void h(int[] nArray) {
        M = nArray;
    }

    public void setShort(Object object, short s) {
        if (this.g != null) {
            try {
                this.g.setShort(object, s);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.nn(this.x, object, s));
    }

    public void setDouble(Object object, double d) {
        if (this.g != null) {
            try {
                this.g.setDouble(object, d);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        NativeMappedMemberInvoker.rr(this.x, object, d);
    }

    public Object getObject(Object object) {
        if (this.g != null) {
            try {
                return this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return NativeMappedMemberInvoker.cc(this.x, object);
    }

    public boolean g() {
        return this.V;
    }

    public void setFloat(Object object, float f) {
        if (this.g != null) {
            try {
                this.g.setFloat(object, f);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.qq(this.x, object, f));
    }

    public short getShort(Object object) {
        if (this.g != null) {
            try {
                return this.g.getShort(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.x(this.x, object));
    }

    public void setDoubleArray(Object object, double[] dArray) {
        if (this.g != null) {
            try {
                this.g.set(object, dArray);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.zz(this.x, object, dArray));
    }

    public String M() {
        return this.o;
    }

    public MappingField O() {
        String string = MappingMethod.A();
        try {
            if (Vape.INSTANCE.isForgeRemapInactive()) {
                this.Y();
                if (this.g == null) {
                    throw new NoSuchFieldException(this.m.getName() + "#" + this.o);
                }
            } else if (this.P) {
                NativeMappedMemberInvoker.t(this.x, this.m, this.U, this.o, this.H, this.R, this.V);
                this.Y();
            } else {
                NativeMappedMemberInvoker.u(this.x, this.m, this.o, this.H, this.V);
                this.Y();
            }
        }
        catch (Throwable throwable) {
            if (this.n) {
                MappingProfileSnapshotRegistry.j();
            } else {
                MappingProfileSnapshotRegistry.n(this);
            }
            this.c = true;
        }
        return this;
    }

    public MappingField(Mapping mapping, Class clazz, String string, boolean bl, boolean bl2, boolean bl3, Class clazz2, int n) {
        String string2 = MappingMethod.A();
        if (string2 != null) {
            if (ForgeVersion.MC_26_1.d()) {
                bl = false;
            }
            this.G = mapping;
            this.S = string;
            this.w = clazz2;
            MemberLookupSignature memberLookupSignature = RuntimeNameMappingRegistry.lookupFieldMapping(clazz, string);
            if (memberLookupSignature != null) {
                this.o = memberLookupSignature.M;
                this.f = memberLookupSignature.a != null ? memberLookupSignature.a : clazz2;
            } else {
                this.o = string;
                this.f = clazz2;
            }
            this.H = MappingField.Y(this.f, false, n);
            this.R = MappingField.Y(this.f, true, n);
            this.P = bl;
            this.m = clazz;
            this.U = MappedClasses.b(clazz).replace(".", "/");
            this.V = bl2;
            this.x = ++F;
            this.n = bl3;
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                MappingMethod.f("E1SOdc");
            }
            return;
        }
        MemberLookupSignature memberLookupSignature = RuntimeNameMappingRegistry.lookupFieldMapping(clazz, string);
        this.G = null;
        this.S = null;
        this.o = null;
        this.w = null;
        this.f = clazz2;
        this.H = MappingField.Y(this.f, false, n);
        this.R = MappingField.Y(this.f, true, n);
        this.P = bl;
        this.m = clazz;
        this.U = MappedClasses.b(clazz).replace(".", "/");
        this.V = bl2;
        this.x = ++F;
        this.n = bl3;
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MappingMethod.f("E1SOdc");
        }
    }

    public String J() {
        if (Vape.INSTANCE.isForgeRemapInactive()) {
            return this.o;
        }
        return NativeMappedMemberInvoker.gfn(this.x);
    }

    public void setIntArray(Object object, int[] nArray) {
        if (this.g != null) {
            try {
                this.g.set(object, nArray);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.ww(this.x, object, nArray));
    }

    public void setShortArray(Object object, short[] sArray) {
        if (this.g != null) {
            try {
                this.g.set(object, sArray);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.vv(this.x, object, sArray));
    }

    public char getChar(Object object) {
        if (this.g != null) {
            try {
                return this.g.getChar(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.w(this.x, object));
    }

    public Class<?> n() {
        return this.w;
    }

    public int getInt(Object object) {
        if (this.g != null) {
            try {
                return this.g.getInt(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return NativeMappedMemberInvoker.y(this.x, object);
    }

    public short[] getShortArray(Object object) {
        if (this.g != null) {
            try {
                return (short[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.ff(this.x, object));
    }

    public float[] getFloatArray(Object object) {
        if (this.g != null) {
            try {
                return (float[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.ii(this.x, object));
    }

    public void setObjectArray(Object object, Object[] objectArray) {
        if (this.g != null) {
            try {
                this.g.set(object, objectArray);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.aaa(this.x, object, objectArray));
    }

    public boolean x() {
        return this.c;
    }

    public boolean R() {
        return this.P;
    }

    public void setBooleanArray(Object object, boolean[] blArray) {
        if (this.g != null) {
            try {
                this.g.set(object, blArray);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.tt(this.x, object, blArray));
    }

    public int[] getIntArray(Object object) {
        if (this.g != null) {
            try {
                return (int[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.gg(this.x, object));
    }

    public double getDouble(Object object) {
        if (this.g != null) {
            try {
                return this.g.getDouble(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.bb(this.x, object));
    }

    public long getLong(Object object) {
        if (this.g != null) {
            try {
                return this.g.getLong(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.z(this.x, object));
    }

    public boolean[] getBooleanArray(Object object) {
        if (this.g != null) {
            try {
                return (boolean[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.dd(this.x, object));
    }

    public long[] getLongArray(Object object) {
        if (this.g != null) {
            try {
                return (long[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return MappingField.unchecked(() -> NativeMappedMemberInvoker.hh(this.x, object));
    }

    public static int[] P() {
        return M;
    }

    private static String Y(Class<?> clazz, boolean bl, int n) {
        String string = DescUtils.g(clazz, bl);
        if (n > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                stringBuilder.append("[");
            }
            string = stringBuilder + string;
        }
        return string;
    }

    public void setObject(Object object, Object object2) {
        if (this.g != null) {
            try {
                this.g.set(object, object2);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.ss(this.x, object, object2));
    }

    public static MappingField E(MappingFieldBuilder mappingFieldBuilder) {
        MappingField mappingField;
        MappingField mappingField2 = mappingField = new MappingField(mappingFieldBuilder.w(), mappingFieldBuilder.F$src$Ljava_lang_Class_$100ldxh(), mappingFieldBuilder.Y(), mappingFieldBuilder.z$src$Z$103hrpe(), mappingFieldBuilder.D(), mappingFieldBuilder.B(), mappingFieldBuilder.X(), mappingFieldBuilder.i());
        return mappingField2.O();
    }

    public void setCharArray(Object object, char[] cArray) {
        if (this.g != null) {
            try {
                this.g.set(object, cArray);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.uu(this.x, object, cArray));
    }

    public Object[] getObjectArray(Object object) {
        if (this.g != null) {
            try {
                return (Object[])this.g.get(object);
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        return NativeMappedMemberInvoker.kk(this.x, object);
    }

    public String n$src$Ljava_lang_String_$19qhmir() {
        return this.S;
    }

    public void setLongArray(Object object, long[] lArray) {
        if (this.g != null) {
            try {
                this.g.set(object, lArray);
                return;
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        MappingField.unchecked(() -> NativeMappedMemberInvoker.xx(this.x, object, lArray));
    }

    static {
        F = 0;
        MappingField.h(new int[3]);
    }

    public Class<?> H() {
        return this.f;
    }

    public Class L() {
        return this.m;
    }
}
