package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MEntityRenderer;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;

public class MRenderBlocks
extends Mapping {
    public final MappingMethod G;
    private final MappingField Y;
    public final MappingMethod w;
    public final MappingMethod y;
    public final MappingMethod E;
    public final MappingMethod b;
    public final MappingMethod e;
    public final MappingMethod x;
    public final MappingMethod X;
    public final MappingMethod I;

    public MRenderBlocks() {
        this(MEntityRenderer.X());
    }

    private MRenderBlocks(int n) {
        super(MappedClasses.q5);
        if (n != 0) {
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl = true;
            String string = "renderAllFaces";
            MRenderBlocks mRenderBlocks = this;
            this.Y = mRenderBlocks.J(string, bl, clazz);
            Class[] classArray = new Class[]{MappedClasses.Zk, Integer.TYPE, Integer.TYPE, Integer.TYPE};
            Class<Boolean> clazz2 = Boolean.TYPE;
            boolean bl2 = true;
            String string2 = "renderBlockByRenderType";
            MRenderBlocks mRenderBlocks2 = this;
            this.G = this.Y(string2, bl2, clazz2, classArray);
            Class[] classArray2 = new Class[]{MappedClasses.Zk, Integer.TYPE, Integer.TYPE, Integer.TYPE};
            Class<Boolean> clazz3 = Boolean.TYPE;
            boolean bl3 = true;
            String string3 = "renderStandardBlock";
            MRenderBlocks mRenderBlocks3 = this;
            this.w = this.Y(string3, bl3, clazz3, classArray2);
            Class[] classArray3 = new Class[]{MappedClasses.Zk, Integer.TYPE, Integer.TYPE, Integer.TYPE, Float.TYPE, Float.TYPE, Float.TYPE};
            Class<Boolean> clazz4 = Boolean.TYPE;
            boolean bl4 = true;
            String string4 = "renderStandardBlockWithColorMultiplier";
            MRenderBlocks mRenderBlocks4 = this;
            this.E = this.Y(string4, bl4, clazz4, classArray3);
            Class[] classArray4 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
            Class<Void> clazz5 = Void.TYPE;
            boolean bl5 = true;
            String string5 = "renderFaceXNeg";
            MRenderBlocks mRenderBlocks5 = this;
            this.I = this.Y(string5, bl5, clazz5, classArray4);
            Class[] classArray5 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
            Class<Void> clazz6 = Void.TYPE;
            boolean bl6 = true;
            String string6 = "renderFaceXPos";
            MRenderBlocks mRenderBlocks6 = this;
            this.x = this.Y(string6, bl6, clazz6, classArray5);
            Class[] classArray6 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
            Class<Void> clazz7 = Void.TYPE;
            boolean bl7 = true;
            String string7 = "renderFaceYNeg";
            MRenderBlocks mRenderBlocks7 = this;
            this.e = this.Y(string7, bl7, clazz7, classArray6);
            Class[] classArray7 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
            Class<Void> clazz8 = Void.TYPE;
            boolean bl8 = true;
            String string8 = "renderFaceYPos";
            MRenderBlocks mRenderBlocks8 = this;
            this.b = this.Y(string8, bl8, clazz8, classArray7);
            Class[] classArray8 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
            Class<Void> clazz9 = Void.TYPE;
            boolean bl9 = true;
            String string9 = "renderFaceZNeg";
            MRenderBlocks mRenderBlocks9 = this;
            this.y = this.Y(string9, bl9, clazz9, classArray8);
            Class[] classArray9 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
            Class<Void> clazz10 = Void.TYPE;
            boolean bl10 = true;
            String string10 = "renderFaceZPos";
            MRenderBlocks mRenderBlocks10 = this;
            this.X = this.Y(string10, bl10, clazz10, classArray9);
            GuiComponent.D(new GuiComponent[3]);
            return;
        }
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl = true;
        String string = "renderAllFaces";
        MRenderBlocks mRenderBlocks = this;
        this.Y = mRenderBlocks.J(string, bl, clazz);
        Class[] classArray = new Class[]{MappedClasses.Zk, Integer.TYPE, Integer.TYPE, Integer.TYPE};
        Class<Boolean> clazz11 = Boolean.TYPE;
        boolean bl11 = true;
        String string11 = "renderBlockByRenderType";
        MRenderBlocks mRenderBlocks11 = this;
        this.G = this.Y(string11, bl11, clazz11, classArray);
        Class[] classArray10 = new Class[]{MappedClasses.Zk, Integer.TYPE, Integer.TYPE, Integer.TYPE};
        Class<Boolean> clazz12 = Boolean.TYPE;
        boolean bl12 = true;
        String string12 = "renderStandardBlock";
        MRenderBlocks mRenderBlocks12 = this;
        this.w = this.Y(string12, bl12, clazz12, classArray10);
        Class[] classArray11 = new Class[]{MappedClasses.Zk, Integer.TYPE, Integer.TYPE, Integer.TYPE, Float.TYPE, Float.TYPE, Float.TYPE};
        Class<Boolean> clazz13 = Boolean.TYPE;
        boolean bl13 = true;
        String string13 = "renderStandardBlockWithColorMultiplier";
        MRenderBlocks mRenderBlocks13 = this;
        this.E = this.Y(string13, bl13, clazz13, classArray11);
        Class[] classArray12 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
        Class<Void> clazz14 = Void.TYPE;
        boolean bl14 = true;
        String string14 = "renderFaceXNeg";
        MRenderBlocks mRenderBlocks14 = this;
        this.I = this.Y(string14, bl14, clazz14, classArray12);
        Class[] classArray13 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
        Class<Void> clazz15 = Void.TYPE;
        boolean bl15 = true;
        String string15 = "renderFaceXPos";
        MRenderBlocks mRenderBlocks15 = this;
        this.x = this.Y(string15, bl15, clazz15, classArray13);
        Class[] classArray14 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
        Class<Void> clazz16 = Void.TYPE;
        boolean bl16 = true;
        String string16 = "renderFaceYNeg";
        MRenderBlocks mRenderBlocks16 = this;
        this.e = this.Y(string16, bl16, clazz16, classArray14);
        Class[] classArray15 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
        Class<Void> clazz17 = Void.TYPE;
        boolean bl17 = true;
        String string17 = "renderFaceYPos";
        MRenderBlocks mRenderBlocks17 = this;
        this.b = this.Y(string17, bl17, clazz17, classArray15);
        Class[] classArray16 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
        Class<Void> clazz18 = Void.TYPE;
        boolean bl18 = true;
        String string18 = "renderFaceZNeg";
        MRenderBlocks mRenderBlocks18 = this;
        this.y = this.Y(string18, bl18, clazz18, classArray16);
        Class[] classArray17 = new Class[]{MappedClasses.Zk, Double.TYPE, Double.TYPE, Double.TYPE, MappedClasses.Z9};
        Class<Void> clazz19 = Void.TYPE;
        boolean bl19 = true;
        String string19 = "renderFaceZPos";
        MRenderBlocks mRenderBlocks19 = this;
        this.X = this.Y(string19, bl19, clazz19, classArray17);
    }

    public boolean m(Object object, Object object2, int n, int n2, int n3, float f, float f2, float f3) {
        return this.E.e(object, object2, n, n2, n3, Float.valueOf(f), Float.valueOf(f2), Float.valueOf(f3));
    }

    public void A(Object object, boolean bl) {
        this.Y.setBoolean(object, bl);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

