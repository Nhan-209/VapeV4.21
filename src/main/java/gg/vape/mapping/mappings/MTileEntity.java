package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MTileEntityMobSpawner;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.TileEntity;

public class MTileEntity
extends Mapping {
    private MappingField q;
    private MappingField e;
    private MappingField J;
    private MappingField n;

    public Object D(Object object) {
        return this.n.getObject(object);
    }

    public int K(Object object) {
        return this.e.getInt(object);
    }

    public int E(Object object) {
        return this.q.getInt(object);
    }

    public MTileEntity() {
        this(MTileEntityMobSpawner.d());
    }

    private MTileEntity(int[] nArray) {
        super(MappedClasses.ZI);
        TileEntity.j(ForgeVersion.MC_1_7_10.L());
        if (nArray != null) {
            Class clazz = MappedClasses.lf;
            boolean bl = true;
            String string = "pos";
            MTileEntity mTileEntity = this;
            this.n = mTileEntity.J(string, bl, clazz);
            return;
        }
        if (ForgeVersion.MC_1_7_10.L()) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "xCoord";
            MTileEntity mTileEntity = this;
            this.q = mTileEntity.J(string, bl, clazz);
            Class<Integer> clazz2 = Integer.TYPE;
            boolean bl2 = true;
            String string2 = "yCoord";
            MTileEntity mTileEntity2 = this;
            this.e = this.J(string2, bl2, clazz2);
            Class<Integer> clazz3 = Integer.TYPE;
            boolean bl3 = true;
            String string3 = "zCoord";
            MTileEntity mTileEntity3 = this;
            this.J = this.J(string3, bl3, clazz3);
        } else {
            Class clazz = MappedClasses.lf;
            boolean bl = true;
            String string = "pos";
            MTileEntity mTileEntity = this;
            this.n = mTileEntity.J(string, bl, clazz);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public int g(Object object) {
        return this.J.getInt(object);
    }
}

