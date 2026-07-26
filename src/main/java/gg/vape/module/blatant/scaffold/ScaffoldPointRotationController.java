package gg.vape.module.blatant.scaffold;

import gg.vape.module.blatant.Scaffold;
import gg.vape.rotation.PointRotationController;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.GuiScreen;

public class ScaffoldPointRotationController
extends PointRotationController {
    final int M;
    boolean j;
    final double[] D;
    final Scaffold u;

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ScaffoldPointRotationController(Scaffold scaffold, double d, double d2, double d3, int n, double[] dArray) {
        super(d, d2, d3);
        this.u = scaffold;
        this.M = n;
        this.D = dArray;
        this.j = false;
    }

    @Override
    public void J(EntityPlayerSP entityPlayerSP, GuiScreen guiScreen) {
        if (guiScreen.isNotNull()) {
            return;
        }
        this.V$src$V$1law04n();
        this.m();
        EnumFacing enumFacing = Scaffold.B(this.u, this.M);
        if (RotationUtil.p(enumFacing, new BlockCoordinate(MathUtil.floor(this.D[0]), MathUtil.floor(this.D[1]), MathUtil.floor(this.D[2])))) {
            if (!this.j) {
                this.Y(Scaffold.Access.W(this.u, this.M));
                this.j = true;
            }
            this.A();
        }
    }
}
