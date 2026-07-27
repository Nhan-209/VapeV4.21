package gg.vape.module.blatant.blockin;

import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.MouseRotationController;
import java.util.List;

public class BlockPlacementGraphNode {
    public final float W;
    public final float X;
    public final float Y;
    public final float e;
    public final float M;
    public final boolean z;
    public final float u;
    public final float m;
    public final float g;

    public BlockPlacementGraphNode(MouseRotationController mouseRotationController, float f, float f2) {
        this.e = mouseRotationController.G();
        this.m = mouseRotationController.u();
        if (mouseRotationController instanceof FixedRotationController) {
            this.W = ((FixedRotationController)mouseRotationController).b();
            this.g = ((FixedRotationController)mouseRotationController).s$src$F$15o72go();
        } else {
            this.W = -1.0f;
            this.g = -1.0f;
        }
        this.X = mouseRotationController.N();
        this.Y = mouseRotationController.V();
        this.z = mouseRotationController.V$src$Z$lb4tvc();
        this.M = f;
        this.u = f2;
    }


    public BlockPlacementGraphNode(AdaptiveRotationController adaptiveRotationController) {
        this(adaptiveRotationController, adaptiveRotationController.J(), adaptiveRotationController.X());
    }

    public String C(BlockPlacementGraphNode blockPlacementGraphNode) {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.e != blockPlacementGraphNode.e) {
            stringBuilder.append("DeltaX: ").append(blockPlacementGraphNode.e).append(" != ").append(this.e).append("\n");
        }
        if (this.m != blockPlacementGraphNode.m) {
            stringBuilder.append("DeltaY: ").append(blockPlacementGraphNode.m).append(" != ").append(this.m).append("\n");
        }
        if (this.W != blockPlacementGraphNode.W) {
            stringBuilder.append("TargetYaw: ").append(blockPlacementGraphNode.W).append(" != ").append(this.W).append("\n");
        }
        if (this.g != blockPlacementGraphNode.g) {
            stringBuilder.append("TargetPitch: ").append(blockPlacementGraphNode.g).append(" != ").append(this.g).append("\n");
        }
        if (this.X != blockPlacementGraphNode.X) {
            stringBuilder.append("YawChanged: ").append(blockPlacementGraphNode.X).append(" != ").append(this.X).append("\n");
        }
        if (this.Y != blockPlacementGraphNode.Y) {
            stringBuilder.append("PitchChanged: ").append(blockPlacementGraphNode.Y).append(" != ").append(this.Y).append("\n");
        }
        if (this.z != blockPlacementGraphNode.z) {
            stringBuilder.append("IsCompleted: ").append(blockPlacementGraphNode.z).append(" != ").append(this.z).append("\n");
        }
        if (this.M != blockPlacementGraphNode.M) {
            stringBuilder.append("CurrentYaw: ").append(blockPlacementGraphNode.M).append(" != ").append(this.M).append("\n");
        }
        if (this.u != blockPlacementGraphNode.u) {
            stringBuilder.append("CurrentPitch: ").append(blockPlacementGraphNode.u).append(" != ").append(this.u).append("\n");
        }
        return stringBuilder.toString();
    }

    public static String n(List<BlockPlacementGraphNode> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("targetYaw,targetPitch,deltaX,deltaY,yawChanged,pitchChanged,isCompleted,currentYaw,currentPitch\n");
        for (BlockPlacementGraphNode blockPlacementGraphNode : list) {
            stringBuilder.append(blockPlacementGraphNode.W).append(",").append(blockPlacementGraphNode.g).append(",").append(blockPlacementGraphNode.e).append(",").append(blockPlacementGraphNode.m).append(",").append(blockPlacementGraphNode.X).append(",").append(blockPlacementGraphNode.Y).append(",").append(blockPlacementGraphNode.z).append(",").append(blockPlacementGraphNode.M).append(",").append(blockPlacementGraphNode.u).append("\n");
        }
        return stringBuilder.toString();
    }
}

