package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MPacketIdFactory;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MCPacketHeldItemChange
extends Mapping {
    private final MappingMethod q;
    private final MappingField Q;

    public MCPacketHeldItemChange() {
        this(MPacketIdFactory.A());
    }

    private MCPacketHeldItemChange(GuiComponent[] guiComponentArray) {
        super(MappedClasses.e);
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        if (ForgeVersion.MC_1_8_9.L()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "slotId";
            MCPacketHeldItemChange mCPacketHeldItemChange = this;
            this.Q = mCPacketHeldItemChange.J(string, bl, clazz);
        } else if (ForgeVersion.MC_1_7_10.L() && Wrapper.c.isVanillaMinecraftPresent()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "slotId";
            MCPacketHeldItemChange mCPacketHeldItemChange = this;
            this.Q = mCPacketHeldItemChange.J(string, bl, clazz);
        } else {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = Wrapper.G;
            String string = "field_149615_a";
            MCPacketHeldItemChange mCPacketHeldItemChange = this;
            this.Q = mCPacketHeldItemChange.J(string, bl, clazz);
        }
        Class[] classArray = new Class[]{Integer.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = false;
        String string = "<init>";
        MCPacketHeldItemChange mCPacketHeldItemChange = this;
        this.q = mCPacketHeldItemChange.Y(string, bl, clazz, classArray); 
    }

    public void d(Object object, int n) {
        this.Q.setInt(object, n);
    }

    public int B(Object object) {
        return this.Q.getInt(object);
    }


    public Object l(int n) {
        return this.q.O(n);
    }
}
