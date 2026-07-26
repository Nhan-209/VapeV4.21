package gg.vape.rotation;

import gg.vape.rotation.MouseRotationController;
import gg.vape.rotation.WorldPointRotationTarget;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.RotationUtil;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Vec3;

public class WorldPointMouseRotationController
extends MouseRotationController
implements WorldPointRotationTarget {
    private double z;
    private double j;
    private double H;

    public void A(double d) {
        this.j = d;
    }

    public WorldPointMouseRotationController(double d, double d2, double d3) {
        this.H = d;
        this.j = d2;
        this.z = d3;
    }

    public void e(double d) {
        this.H = d;
    }

    @Override
    public boolean A() {
        double d = RotationUtil.T(Minecraft.thePlayer(), this.H, this.z);
        if (d > (double)this.W) {
            this.B = RotationUtil.k(Minecraft.thePlayer(), this.H, this.z) ? (this.B -= this.O()) : (this.B += this.O());
            return false;
        }
        return true;
    }

    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public double a() {
        return this.z;
    }

    @Override
    public void J(Vec3 vec3) {
        this.H = vec3.getX();
        this.j = vec3.getY();
        this.z = vec3.getZ();
    }

    @Override
    public Vec3 w() {
        return Vec3.create(this.Q(), this.Y(), this.a());
    }

    public double Q() {
        return this.H;
    }

    @Override
    public boolean m() {
        double d = RotationUtil.H(Minecraft.thePlayer(), this.H, this.j, this.z);
        if (d > (double)this.W || d < (double)(-this.W)) {
            this.y = RotationUtil.h$src$Z$kozqzr(Minecraft.thePlayer(), this.H, this.j, this.z) ? (this.y += this.O()) : (this.y -= this.O());
            return false;
        }
        return true;
    }

    public double Y() {
        return this.j;
    }

    public void h(double d) {
        this.z = d;
    }
}

