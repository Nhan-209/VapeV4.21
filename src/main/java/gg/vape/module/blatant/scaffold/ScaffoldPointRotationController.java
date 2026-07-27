package gg.vape.module.blatant.scaffold;

import gg.vape.module.blatant.Scaffold;
import gg.vape.rotation.PointRotationController;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.GuiScreen;

public class ScaffoldPointRotationController
extends PointRotationController {
    final int direction;
    boolean rotationApplied;
    final double[] placePos;
    final Scaffold scaffold;


    public ScaffoldPointRotationController(Scaffold scaffold, double d, double d2, double d3, int n, double[] dArray) {
        super(d, d2, d3);
        this.scaffold = scaffold;
        this.direction = n;
        this.placePos = dArray;
        this.rotationApplied = false;
    }

    @Override
    public void J(EntityPlayerSP entityPlayerSP, GuiScreen guiScreen) {
        if (guiScreen.isNotNull()) {
            return;
        }
        this.V$src$V$1law04n();
        this.m();
        EnumFacing enumFacing = Scaffold.B(this.scaffold, this.direction);
        if (RotationUtil.p(enumFacing, new BlockCoordinate(MathUtil.floor(this.placePos[0]), MathUtil.floor(this.placePos[1]), MathUtil.floor(this.placePos[2])))) {
            if (!this.rotationApplied) {
                this.Y(Scaffold.Access.W(this.scaffold, this.direction));
                this.rotationApplied = true;
            }
            this.A();
        }
    }
}
