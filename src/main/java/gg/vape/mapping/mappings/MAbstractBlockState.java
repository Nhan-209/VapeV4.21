package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MAbstractBlockState
extends Mapping {
    private MappingMethod B;
    private MappingMethod d;

    private boolean j(Object object, Object object2, Object object3) {
        return this.d.e(object, object2, object3);
    }

    public MAbstractBlockState() {
        this(BlockData.W());
    }

    private MAbstractBlockState(String[] stringArray) {
        super(MappedClasses.Fj);
        if (stringArray != null) {
            if (ForgeVersion.MC_1_20_6.d()) {
                Class[] classArray = new Class[]{MappedClasses.zJ, MappedClasses.lf};
                Class clazz = MappedClasses.la;
                boolean bl = true;
                String string = "getShape";
                MAbstractBlockState mAbstractBlockState = this;
                this.B = mAbstractBlockState.Y(string, bl, clazz, classArray);
            } else {
                Class[] classArray = new Class[]{MappedClasses.zJ, MappedClasses.lf};
                Class clazz = MappedClasses.la;
                boolean bl = Wrapper.G;
                String string = "func_196954_c";
                MAbstractBlockState mAbstractBlockState = this;
                this.B = mAbstractBlockState.Y(string, bl, clazz, classArray);
                Class[] classArray2 = new Class[]{MappedClasses.zJ, MappedClasses.lf};
                Class<Boolean> clazz2 = Boolean.TYPE;
                boolean bl2 = Wrapper.G;
                String string2 = "func_229980_m_";
                MAbstractBlockState mAbstractBlockState2 = this;
                this.d = this.Y(string2, bl2, clazz2, classArray2);
            }
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.zJ, MappedClasses.lf};
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl = Wrapper.G;
        String string = "func_229980_m_";
        MAbstractBlockState mAbstractBlockState = this;
        this.d = mAbstractBlockState.Y(string, bl, clazz, classArray); 
    }

    public static Object i(MAbstractBlockState mAbstractBlockState, Object object, Object object2, Object object3) {
        return mAbstractBlockState.I(object, object2, object3);
    }

    private Object I(Object object, Object object2, Object object3) {
        return this.B.L(object, object2, object3);
    }

    public static boolean R(MAbstractBlockState mAbstractBlockState, Object object, Object object2, Object object3) {
        return mAbstractBlockState.j(object, object2, object3);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
