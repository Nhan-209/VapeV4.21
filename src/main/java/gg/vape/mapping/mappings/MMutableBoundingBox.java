package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.ForgeVersion;

public class MMutableBoundingBox
extends Mapping {
    public MappingMethod B;
    public MappingMethod C;

    public boolean D(Object object, Object object2) {
        return this.C.e(object, object2);
    }

    public MMutableBoundingBox() {
        this(BlockData.W());
    }

    private MMutableBoundingBox(String[] stringArray) {
        super(MappedClasses.f);
        if (stringArray != null) {
            Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = false;
            String string = "<init>";
            MMutableBoundingBox mMutableBoundingBox = this;
            this.B = mMutableBoundingBox.Y(string, bl, clazz, classArray);
            if (ForgeVersion.MC_1_17.d()) {
                Class[] classArray2 = new Class[]{MappedClasses.f};
                Class<Boolean> clazz2 = Boolean.TYPE;
                boolean bl2 = ForgeVersion.MC_1_20_6.d();
                String string2 = "m_71049_";
                MMutableBoundingBox mMutableBoundingBox2 = this;
                this.C = this.Y(string2, bl2, clazz2, classArray2);
            } else {
                Class[] classArray3 = new Class[]{MappedClasses.f};
                Class<Boolean> clazz3 = Boolean.TYPE;
                boolean bl3 = true;
                String string3 = "intersectsWith";
                MMutableBoundingBox mMutableBoundingBox3 = this;
                this.C = this.Y(string3, bl3, clazz3, classArray3);
            }
            return;
        }
        Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MMutableBoundingBox mMutableBoundingBox = this;
        this.C = mMutableBoundingBox.Y(string, bl, clazz, classArray);
        this.B = null;
    }


    public Object P(int n, int n2, int n3, int n4, int n5, int n6) {
        return this.B.O(n, n2, n3, n4, n5, n6);
    }
}

