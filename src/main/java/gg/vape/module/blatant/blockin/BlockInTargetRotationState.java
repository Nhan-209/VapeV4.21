package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.rotation.RotationAngles;
import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Vec3;

public class BlockInTargetRotationState {
    private Vec3d lookVec;
    private float pitch;
    private boolean flagF;
    private BlockPlacementGraph placementGraph;
    private RayTraceResult rayTrace;
    private boolean flagF2;
    private Vec3d targetVec;
    private double posY;
    private boolean flagH;
    private double posX;
    private double posZ;
    private float yaw;

    public RotationAngles Z() {
        return new RotationAngles(this.yaw, this.pitch);
    }

    public boolean l() {
        return this.flagH;
    }

    public double b() {
        return this.posX;
    }

    public boolean n() {
        return this.flagF;
    }

    public float i() {
        return this.yaw;
    }

    public void e(Vec3d vec3d) {
        this.lookVec = vec3d;
    }

    public void R(RayTraceResult rayTraceResult) {
        this.rayTrace = rayTraceResult;
    }

    public float M() {
        return this.pitch;
    }

    public Vec3d m() {
        return this.lookVec;
    }

    public Vec3d R() {
        return this.targetVec;
    }

    public BlockPlacementGraph U() {
        return this.placementGraph;
    }

    public BlockInTargetRotationState(EntityLivingBase entityLivingBase) {
        this.posX = entityLivingBase.z();
        this.posY = entityLivingBase.N();
        this.posZ = entityLivingBase.h();
        this.yaw = entityLivingBase.J();
        this.pitch = entityLivingBase.V();
        this.flagF2 = entityLivingBase.b$src$Z$fqlxe4();
        this.flagH = entityLivingBase.P();
        this.flagF = entityLivingBase.B$src$Z$f90iek();
    }

    public boolean j() {
        return this.flagF2;
    }

    public Vec3 M$src$Lgg_vape_wrapper_impl_Vec3_$ofcqpn() {
        return Vec3.create(this.posX, this.posY, this.posZ);
    }

    public double x() {
        return this.posY;
    }

    public double L() {
        return this.posZ;
    }

    public void m(BlockPlacementGraph blockPlacementGraph) {
        this.placementGraph = blockPlacementGraph;
    }

    public RayTraceResult X() {
        return this.rayTrace;
    }

    public Vec3d h() {
        return new Vec3d(this.posX, this.posY, this.posZ);
    }

    public void M(Vec3d vec3d) {
        this.targetVec = vec3d;
    }
}

