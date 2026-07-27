package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MItemStack;
import gg.vape.wrapper.impl.ForgeVersion;

public class MMethodSlotZYBridge
extends Mapping {
    public final MappingMethod v;

    public MMethodSlotZYBridge() {
        this(MItemStack.f());
    }

    private MMethodSlotZYBridge(int n) {
        super(MappedClasses.zY);
        int n2 = n;
        if (ForgeVersion.MC_1_21_4.d()) {
            Class[] classArray = new Class[]{MappedClasses.YU, MappedClasses.Yl, MappedClasses.Yf};
            Class clazz = MappedClasses.Yn;
            boolean bl = true;
            String string = "use";
            Class clazz2 = MappedClasses.lb;
            MMethodSlotZYBridge mMethodSlotZYBridge = this;
            this.v = mMethodSlotZYBridge.W(clazz2, string, bl, clazz, classArray);
        } else if (ForgeVersion.MC_1_16_5.d()) {
            Class[] classArray = new Class[]{MappedClasses.YU, MappedClasses.Yl, MappedClasses.Yf};
            Class clazz = MappedClasses.zU;
            boolean bl = true;
            String string = "use";
            MMethodSlotZYBridge mMethodSlotZYBridge = this;
            this.v = mMethodSlotZYBridge.Y(string, bl, clazz, classArray);
        } else if (ForgeVersion.MC_1_12_2.d()) {
            Class[] classArray = new Class[]{MappedClasses.YU, MappedClasses.Yl, MappedClasses.Yf};
            Class clazz = MappedClasses.zU;
            boolean bl = true;
            String string = "onItemRightClick";
            MMethodSlotZYBridge mMethodSlotZYBridge = this;
            this.v = mMethodSlotZYBridge.Y(string, bl, clazz, classArray);
        } else {
            Class[] classArray = new Class[]{MappedClasses.VK, MappedClasses.YU, MappedClasses.Yl};
            Class clazz = MappedClasses.VK;
            boolean bl = true;
            String string = "onItemRightClick";
            MMethodSlotZYBridge mMethodSlotZYBridge = this;
            this.v = mMethodSlotZYBridge.Y(string, bl, clazz, classArray);
        }
    }

}

