package gg.vape.module.world.bedbreaker;

import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.RenderManager;
import java.util.Objects;

public class BedTargetRenderPosition {
    private static int[] R;
    private final double G;
    private final double a;
    private final double z;

    public int hashCode() {
        return Objects.hash(this.a, this.z, this.G);
    }

    public double b() {
        double d = RenderManager.getInterpolatedRenderPosZ();
        return this.G - d;
    }

    public int h() {
        return MathUtil.floor(this.z);
    }

    public double double_D() {
        double d = RenderManager.getInterpolatedRenderPosY();
        return this.z - d;
    }

    public double r() {
        return this.z;
    }

    public static int[] Y() {
        return R;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        BedTargetRenderPosition bedTargetRenderPosition = (BedTargetRenderPosition)object;
        boolean bl = this.a == bedTargetRenderPosition.a && this.z == bedTargetRenderPosition.z && this.G == bedTargetRenderPosition.G;
        return bl;
    }

    public double Z() {
        return this.a;
    }

    public static void l(int[] nArray) {
        R = nArray;
    }

    public double G() {
        double d = RenderManager.getInterpolatedRenderPosX();
        return this.a - d;
    }

    public int int_D() {
        return MathUtil.floor(this.G);
    }

    public int N() {
        return MathUtil.floor(this.a);
    }

    public BedTargetRenderPosition(double d, double d2, double d3) {
        this.a = d;
        this.z = d2;
        this.G = d3;
    }

    public double E() {
        return this.G;
    }


    public BedTargetRenderPosition(int n, int n2, int n3) {
        this.a = n;
        this.z = n2;
        this.G = n3;
    }

    static {
        if (BedTargetRenderPosition.Y() != null) {
            BedTargetRenderPosition.l(new int[4]);
        }
    }

    public /* synthetic */ double D() {
        return this.double_D();
    }

    public /* synthetic */ int D$src$I$nuyd86() {
        return this.int_D();
    }
}

