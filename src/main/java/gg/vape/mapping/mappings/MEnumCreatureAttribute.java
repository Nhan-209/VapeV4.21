package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingFieldBuilder;
import gg.vape.mapping.MappingMethod;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MEnumCreatureAttribute
extends Mapping {
    private MappingField Z;
    private MappingField B;
    private final MappingField S;
    private final MappingMethod a;
    private final MappingMethod G;

    private Object N(Object object, Object object2) {
        return this.a.L(object, object2);
    }

    public static Object X(MEnumCreatureAttribute mEnumCreatureAttribute, Object object, Object object2) {
        return mEnumCreatureAttribute.N(object, object2);
    }

    public static Object E(MEnumCreatureAttribute mEnumCreatureAttribute) {
        return mEnumCreatureAttribute.T();
    }

    public MEnumCreatureAttribute() {
        this(BlockData.W());
    }

    private MEnumCreatureAttribute(String[] stringArray) {
        super(MappedClasses.uB);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_20_6.d()) {
                Class clazz = MappedClasses.Vo;
                boolean bl = true;
                String string = "type";
                MEnumCreatureAttribute mEnumCreatureAttribute = this;
                this.Z = mEnumCreatureAttribute.J(string, bl, clazz);
                Class<String> clazz2 = String.class;
                boolean bl2 = true;
                String string2 = "msgId";
                Class clazz3 = MappedClasses.Vz;
                MEnumCreatureAttribute mEnumCreatureAttribute2 = this;
                this.S = this.X(clazz3, string2, bl2, clazz2);
                Class[] classArray = new Class[]{MappedClasses.Yl};
                Class clazz4 = MappedClasses.uB;
                boolean bl3 = true;
                String string3 = "playerAttack";
                Class clazz5 = MappedClasses.ZZ;
                MEnumCreatureAttribute mEnumCreatureAttribute3 = this;
                this.a = this.W(clazz5, string3, bl3, clazz4, classArray);
            } else {
                Class<String> clazz = String.class;
                boolean bl = true;
                String string = "damageType";
                MEnumCreatureAttribute mEnumCreatureAttribute = this;
                this.S = mEnumCreatureAttribute.J(string, bl, clazz);
                Class[] classArray = new Class[]{MappedClasses.Yl};
                Class clazz6 = MappedClasses.uB;
                boolean bl4 = true;
                String string4 = "causePlayerDamage";
                MEnumCreatureAttribute mEnumCreatureAttribute4 = this;
                this.a = this.x(string4, bl4, clazz6, classArray);
            }
            if (ForgeVersion.MC_1_16_5.d()) {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.zc;
                boolean bl = true;
                String string = "getImmediateSource";
                MEnumCreatureAttribute mEnumCreatureAttribute = this;
                this.G = mEnumCreatureAttribute.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{};
                Class clazz = MappedClasses.zc;
                boolean bl = true;
                String string = "getSourceOfDamage";
                MEnumCreatureAttribute mEnumCreatureAttribute = this;
                this.G = mEnumCreatureAttribute.Y(string, bl, clazz, classArray);
            }
            if (ForgeVersion.MC_1_21_4.v()) {
                Class clazz = MappedClasses.uB;
                String string = "fall";
                MEnumCreatureAttribute mEnumCreatureAttribute = this;
                this.B = ((MappingFieldBuilder)((MappingFieldBuilder)mEnumCreatureAttribute.T(string, clazz).H(true)).A(ForgeVersion.MC_1_16_5.n(), "FALL")).z();
            }
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.Yl};
        Class clazz = MappedClasses.uB;
        boolean bl = true;
        String string = "causePlayerDamage";
        MEnumCreatureAttribute mEnumCreatureAttribute = this;
        this.a = mEnumCreatureAttribute.x(string, bl, clazz, classArray); 
        if (ForgeVersion.MC_1_16_5.d()) {
            Class[] classArray2 = new Class[]{};
            Class clazz7 = MappedClasses.zc;
            boolean bl5 = true;
            String string5 = "getImmediateSource";
            MEnumCreatureAttribute mEnumCreatureAttribute5 = this;
            mEnumCreatureAttribute5.Y(string5, bl5, clazz7, classArray2);
        }
        Class[] classArray3 = new Class[]{};
        Class clazz8 = MappedClasses.zc;
        boolean bl6 = true;
        String string6 = "getSourceOfDamage";
        MEnumCreatureAttribute mEnumCreatureAttribute6 = this;
        this.G = this.Y(string6, bl6, clazz8, classArray3);
        if (ForgeVersion.MC_1_21_4.v()) {
            Class clazz9 = MappedClasses.uB;
            String string7 = "fall";
            MEnumCreatureAttribute mEnumCreatureAttribute7 = this;
            this.B = ((MappingFieldBuilder)((MappingFieldBuilder)this.T(string7, clazz9).H(true)).A(ForgeVersion.MC_1_16_5.n(), "FALL")).z();
        }
        this.S = null;
    }

    private String B(Object object) {
        return (String)this.S.getObject(object);
    }


    private Object n(Object object) {
        return this.G.L(object, new Object[0]);
    }

    private Object T() {
        return this.B.getObject(null);
    }

    private Object r(Object object) {
        return this.Z.getObject(object);
    }
}

