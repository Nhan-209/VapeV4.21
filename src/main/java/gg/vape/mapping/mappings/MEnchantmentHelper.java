package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.asm.helper.DescUtils;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEnchantments;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MEnchantmentHelper
extends Mapping {
    private MappingMethod B;
    private MappingMethod c;
    private final MappingMethod K;
    private MappingMethod U;
    private MappingMethod l;
    private MappingMethod N;
    private final MappingMethod m;
    private MappingMethod f;
    private MappingField C;
    private MappingMethod k;
    private final MappingMethod b;

    private void Z(Object object, Iterable iterable) {
        if (this.c == null) {
            return;
        }
        try {
            this.c.c(null, object, iterable);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private int w(Object object) {
        if (this.k == null) {
            return 0;
        }
        return this.k.Z(null, object);
    }

    public MEnchantmentHelper() {
        this(MEnchantments.u());
    }

    private MEnchantmentHelper(String string) {
        super(MappedClasses.FS);
        String string2 = string;
        if (ForgeVersion.MC_1_21_0.d()) {
            Class[] classArray = new Class[]{MappedClasses.Vo, MappedClasses.P};
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string3 = "getEnchantmentLevel";
            MEnchantmentHelper mEnchantmentHelper = this;
            this.l = mEnchantmentHelper.x(string3, bl, clazz, classArray);
        }
        if (ForgeVersion.MC_1_20_6.d()) {
            Class[] classArray = new Class[]{MappedClasses.VK};
            Class clazz = MappedClasses.VX;
            boolean bl = true;
            String string4 = "getEnchantmentsForCrafting";
            MEnchantmentHelper mEnchantmentHelper = this;
            this.U = mEnchantmentHelper.x(string4, bl, clazz, classArray);
            if (ForgeVersion.MC_26_1.d()) {
                Class[] classArray2 = new Class[]{MappedClasses.Vo, MappedClasses.q7};
                Class<Integer> clazz2 = Integer.TYPE;
                boolean bl2 = true;
                String string5 = "getItemEnchantmentLevel";
                MEnchantmentHelper mEnchantmentHelper2 = this;
                this.B = this.x(string5, bl2, clazz2, classArray2);
            } else {
                Class[] classArray3 = new Class[]{MappedClasses.lR, MappedClasses.VK};
                Class<Integer> clazz3 = Integer.TYPE;
                boolean bl3 = true;
                String string6 = "getItemEnchantmentLevel";
                MEnchantmentHelper mEnchantmentHelper3 = this;
                this.B = this.x(string6, bl3, clazz3, classArray3);
            }
            Class[] classArray4 = new Class[]{MappedClasses.VK};
            Class<Boolean> clazz4 = Boolean.TYPE;
            boolean bl4 = true;
            String string7 = "hasAnyEnchantments";
            MEnchantmentHelper mEnchantmentHelper4 = this;
            this.N = this.x(string7, bl4, clazz4, classArray4);
        }
        if (ForgeVersion.MC_1_21_4.d()) {
            Class[] classArray = new Class[]{MappedClasses.ZB, MappedClasses.VK, MappedClasses.zc, MappedClasses.uB, Float.TYPE};
            Class<Float> clazz = Float.TYPE;
            boolean bl = true;
            String string8 = "modifyDamage";
            MEnchantmentHelper mEnchantmentHelper = this;
            this.f = mEnchantmentHelper.x(string8, bl, clazz, classArray);
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            if (ForgeVersion.MC_1_21_0.v()) {
                Class[] classArray = new Class[]{MappedClasses.zm};
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string9 = "getDepthStriderModifier";
                MEnchantmentHelper mEnchantmentHelper = this;
                this.k = mEnchantmentHelper.x(string9, bl, clazz, classArray);
            }
            if (ForgeVersion.MC_26_1.v()) {
                Class[] classArray = new Class[]{MappedClasses.lR, MappedClasses.VK};
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string10 = "getEnchantmentLevel";
                MEnchantmentHelper mEnchantmentHelper = this;
                this.K = mEnchantmentHelper.x(string10, bl, clazz, classArray);
            } else {
                this.K = null;
            }
            if (ForgeVersion.MC_1_21_0.v()) {
                Class[] classArray = new Class[]{Iterable.class, MappedClasses.uB};
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string11 = "getEnchantmentModifierDamage";
                MEnchantmentHelper mEnchantmentHelper = this;
                this.m = mEnchantmentHelper.x(string11, bl, clazz, classArray);
                Class[] classArray5 = new Class[]{MappedClasses.VK, MappedClasses.O};
                Class<Float> clazz5 = Float.TYPE;
                boolean bl5 = true;
                String string12 = "getModifierForCreature";
                MEnchantmentHelper mEnchantmentHelper5 = this;
                this.b = this.x(string12, bl5, clazz5, classArray5);
                Class[] classArray6 = new Class[]{MappedClasses.Vp, Iterable.class};
                Class<Void> clazz6 = Void.TYPE;
                boolean bl6 = true;
                String string13 = "applyEnchantmentModifierArray";
                MEnchantmentHelper mEnchantmentHelper6 = this;
                this.c = this.x(string13, bl6, clazz6, classArray6);
            } else {
                this.m = null;
                this.b = null;
                this.c = null;
            }
        } else {
            if (ForgeVersion.MC_1_7_10.Y()) {
                Class[] classArray = new Class[]{MappedClasses.zc};
                Class<Integer> clazz = Integer.TYPE;
                boolean bl = true;
                String string14 = "getDepthStriderModifier";
                MEnchantmentHelper mEnchantmentHelper = this;
                this.k = mEnchantmentHelper.x(string14, bl, clazz, classArray);
            }
            Class[] classArray = new Class[]{Integer.TYPE, MappedClasses.VK};
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string15 = "getEnchantmentLevel";
            MEnchantmentHelper mEnchantmentHelper = this;
            this.K = mEnchantmentHelper.x(string15, bl, clazz, classArray);
            Class[] classArray7 = new Class[]{DescUtils.getArrayType(MappedClasses.VK), MappedClasses.uB};
            Class<Integer> clazz7 = Integer.TYPE;
            boolean bl7 = true;
            String string16 = "getEnchantmentModifierDamage";
            MEnchantmentHelper mEnchantmentHelper7 = this;
            this.m = this.x(string16, bl7, clazz7, classArray7);
            Class[] classArray8 = new Class[]{MappedClasses.Vp, DescUtils.getArrayType(MappedClasses.VK)};
            Class<Void> clazz8 = Void.TYPE;
            boolean bl8 = true;
            String string17 = "applyEnchantmentModifierArray";
            MEnchantmentHelper mEnchantmentHelper8 = this;
            this.c = this.x(string17, bl8, clazz8, classArray8);
            if (Vape.INSTANCE.isVanillaMinecraftPresent() && ForgeVersion.MC_1_7_10.Y()) {
                Class[] classArray9 = new Class[]{MappedClasses.VK, MappedClasses.O};
                Class<Float> clazz9 = Float.TYPE;
                boolean bl9 = true;
                String string18 = "getModifierForCreature";
                MEnchantmentHelper mEnchantmentHelper9 = this;
                this.b = this.x(string18, bl9, clazz9, classArray9);
            } else {
                Class[] classArray10 = new Class[]{MappedClasses.VK, MappedClasses.O};
                Class<Float> clazz10 = Float.TYPE;
                boolean bl10 = Wrapper.G;
                String string19 = "func_152377_a";
                MEnchantmentHelper mEnchantmentHelper10 = this;
                this.b = this.x(string19, bl10, clazz10, classArray10);
            }
        }
        if (ForgeVersion.MC_1_16_5.v()) {
            Class clazz = MappedClasses.l8;
            boolean bl = true;
            String string20 = ForgeVersion.MC_1_12_2.d() ? "ENCHANTMENT_MODIFIER_DAMAGE" : "enchantmentModifierDamage";
            MEnchantmentHelper mEnchantmentHelper = this;
            this.C = mEnchantmentHelper.u(string20, bl, clazz); 
        }
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MEnchantments.b("T0jqMc");
        }
    }

    private int Q(Object object, Object object2) {
        if (this.K == null) {
            return 0;
        }
        return this.K.Z(null, object, object2);
    }

    public static Object l(MEnchantmentHelper mEnchantmentHelper, Object object) {
        return mEnchantmentHelper.B(object);
    }

    private int N(Object object, Object object2) {
        return this.l.Z(null, object, object2);
    }

    private float a(Object object, Object object2, Object object3, Object object4, float f) {
        return this.b.s(null, object, object2, object3, object4, Float.valueOf(f));
    }

    private float L(Object object, Object object2) {
        if (ForgeVersion.MC_1_21_0.d()) {
            throw new UnsupportedOperationException("Unavailable");
        }
        return this.b.s(null, object, object2);
    }

    public static int h(MEnchantmentHelper mEnchantmentHelper, int n, Object object) {
        return mEnchantmentHelper.N(n, object);
    }

    private int p(Object object, Object object2) {
        return this.B.Z(null, object, object2);
    }

    private void M(Object object, Object[] objectArray) {
        if (this.c == null) {
            return;
        }
        try {
            this.c.c(null, object, objectArray);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static Object z(MEnchantmentHelper mEnchantmentHelper) {
        return mEnchantmentHelper.K();
    }

    private Object B(Object object) {
        return this.U.L(null, object);
    }

    public static boolean z(MEnchantmentHelper mEnchantmentHelper, Object object) {
        return mEnchantmentHelper.m(object);
    }

    public static void T(MEnchantmentHelper mEnchantmentHelper, Object object, Iterable iterable) {
        mEnchantmentHelper.Z(object, iterable);
    }

    public static int W(MEnchantmentHelper mEnchantmentHelper, Object object, Object object2) {
        return mEnchantmentHelper.Q(object, object2);
    }

    private int y(Object[] objectArray, Object object) {
        return this.m.Z(null, objectArray, object);
    }

    public static void o(MEnchantmentHelper mEnchantmentHelper, Object object, Object[] objectArray) {
        mEnchantmentHelper.M(object, objectArray);
    }

    public static int X(MEnchantmentHelper mEnchantmentHelper, Object object, Object object2) {
        return mEnchantmentHelper.p(object, object2);
    }

    public static int N(MEnchantmentHelper mEnchantmentHelper, Iterable iterable, Object object) {
        return mEnchantmentHelper.w(iterable, object);
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static float F(MEnchantmentHelper mEnchantmentHelper, Object object, Object object2, Object object3, Object object4, float f) {
        return mEnchantmentHelper.a(object, object2, object3, object4, f);
    }

    public static float w(MEnchantmentHelper mEnchantmentHelper, Object object, Object object2) {
        return mEnchantmentHelper.L(object, object2);
    }

    private boolean m(Object object) {
        return this.N.e(null, object);
    }

    private int w(Iterable iterable, Object object) {
        return this.m.Z(null, iterable, object);
    }

    private int N(int n, Object object) {
        return this.K.Z(null, n, object);
    }

    private Object K() {
        return this.C.getObject(null);
    }

    public static int p(MEnchantmentHelper mEnchantmentHelper, Object[] objectArray, Object object) {
        return mEnchantmentHelper.y(objectArray, object);
    }

    public static int a(MEnchantmentHelper mEnchantmentHelper, Object object) {
        return mEnchantmentHelper.w(object);
    }
}

