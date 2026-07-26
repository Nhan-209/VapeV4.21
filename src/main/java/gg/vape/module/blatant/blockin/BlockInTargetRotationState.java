package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.BlockPlacementGraph;
import gg.vape.rotation.RotationAngles;
import gg.vape.utils.Vec3d;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.RayTraceResult;
import gg.vape.wrapper.impl.Vec3;

public class BlockInTargetRotationState {
    private Vec3d T;
    private float n;
    private boolean F;
    private BlockPlacementGraph P;
    private RayTraceResult j;
    private boolean f;
    private Vec3d E;
    private double M;
    private boolean H;
    private double J;
    private double a;
    private float O;

    public RotationAngles Z() {
        return new RotationAngles(this.O, this.n);
    }

    public boolean l() {
        return this.H;
    }

    public double b() {
        return this.J;
    }

    public boolean n() {
        return this.F;
    }

    public float i() {
        return this.O;
    }

    public void e(Vec3d vec3d) {
        this.T = vec3d;
    }

    public void R(RayTraceResult rayTraceResult) {
        this.j = rayTraceResult;
    }

    public float M() {
        return this.n;
    }

    public Vec3d m() {
        return this.T;
    }

    public Vec3d R() {
        return this.E;
    }

    public BlockPlacementGraph U() {
        return this.P;
    }

    public BlockInTargetRotationState(EntityLivingBase entityLivingBase) {
        this.J = entityLivingBase.z();
        this.M = entityLivingBase.N();
        this.a = entityLivingBase.h();
        this.O = entityLivingBase.J();
        this.n = entityLivingBase.V();
        this.f = entityLivingBase.b$src$Z$fqlxe4();
        this.H = entityLivingBase.P();
        this.F = entityLivingBase.B$src$Z$f90iek();
    }

    public boolean j() {
        return this.f;
    }

    public Vec3 M$src$Lgg_vape_wrapper_impl_Vec3_$ofcqpn() {
        return Vec3.create(this.J, this.M, this.a);
    }

    public double x() {
        return this.M;
    }

    public double L() {
        return this.a;
    }

    public void m(BlockPlacementGraph blockPlacementGraph) {
        this.P = blockPlacementGraph;
    }

    public RayTraceResult X() {
        return this.j;
    }

    public Vec3d h() {
        return new Vec3d(this.J, this.M, this.a);
    }

    public void M(Vec3d vec3d) {
        this.E = vec3d;
    }
}

