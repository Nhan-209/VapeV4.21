package gg.vape.mapping.mappings;

import gg.vape.config.ClientSettings;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.mapping.mappings.MTileEntityMobSpawner;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MTileEntityChest
extends Mapping {
    private MappingMethod k;
    private final MappingField h;
    private final MappingField d;

    public int b(Object object) {
        return this.h.getInt(object);
    }

    public float t(Object object, float f) {
        return this.k.s(object, Float.valueOf(f));
    }

    public MTileEntityChest() {
        this(MTileEntityMobSpawner.d());
    }

    private MTileEntityChest(int[] nArray) {
        super(MappedClasses.u0);
        if (nArray != null) {
            GuiComponent.D(new GuiComponent[1]);
            Class<Float> clazz = Float.TYPE;
            boolean bl = true;
            String string = ClientSettings.H ? "lidAngle" : "field_145972_a";
            MTileEntityChest mTileEntityChest = this;
            this.d = mTileEntityChest.J(string, bl, clazz);
            Class<Integer> clazz2 = Integer.TYPE;
            boolean bl2 = true;
            String string2 = ClientSettings.H ? "numPlayersUsing" : "field_145973_j";
            MTileEntityChest mTileEntityChest2 = this;
            this.h = this.J(string2, bl2, clazz2);
            return;
        }
        if (ForgeVersion.MC_1_17.d()) {
            Class<Float> clazz = Float.TYPE;
            boolean bl = true;
            String string = "openness";
            Class clazz3 = MappedClasses.lQ;
            MTileEntityChest mTileEntityChest = this;
            this.d = mTileEntityChest.X(clazz3, string, bl, clazz);
            Class<Integer> clazz4 = Integer.TYPE;
            boolean bl3 = true;
            String string3 = "openCount";
            Class clazz5 = MappedClasses.zn;
            MTileEntityChest mTileEntityChest3 = this;
            this.h = this.X(clazz5, string3, bl3, clazz4);
            Class[] classArray = new Class[]{Float.TYPE};
            Class<Float> clazz6 = Float.TYPE;
            String string4 = "getOpenNess";
            MTileEntityChest mTileEntityChest4 = this;
            this.k = ((MappingMethodBuilder)this.u(string4, clazz6, classArray).Q(ForgeVersion.MC_1_21_4.n(), MappedClasses.Fs)).s();
        } else {
            Class<Float> clazz = Float.TYPE;
            boolean bl = true;
            String string = ClientSettings.H ? "lidAngle" : "field_145972_a";
            MTileEntityChest mTileEntityChest = this;
            this.d = mTileEntityChest.J(string, bl, clazz);
            Class<Integer> clazz7 = Integer.TYPE;
            boolean bl4 = true;
            String string5 = ClientSettings.H ? "numPlayersUsing" : "field_145973_j";
            MTileEntityChest mTileEntityChest5 = this;
            this.h = this.J(string5, bl4, clazz7);
        }
    }

    public float p(Object object) {
        return this.d.getFloat(object);
    }

}

