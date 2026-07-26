package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.HotbarSlotResolutionStatus;
import gg.vape.runtime.ObfuscatedRuntimeException;
import org.jetbrains.annotations.Nullable;

public class HotbarSlotResolution<T extends HotbarSlotResolution<T>> {
    @Nullable
    private String R;
    private HotbarSlotResolutionStatus Q;
    private boolean l = false;

    public static HotbarSlotResolution J(@Nullable String string) {
        return new HotbarSlotResolution(HotbarSlotResolutionStatus.PENDING, string);
    }

    public T i(boolean bl) {
        this.l = bl;
        return (T)this;
    }

    HotbarSlotResolution(HotbarSlotResolutionStatus hotbarSlotResolutionStatus, @Nullable String string) {
        this.Q = hotbarSlotResolutionStatus;
        this.R = string;
    }

    public T A() {
        this.l = true;
        return (T)this;
    }

    public boolean B() {
        return this.Q == HotbarSlotResolutionStatus.PENDING || this.Q == HotbarSlotResolutionStatus.SUCCESS || this.Q == HotbarSlotResolutionStatus.FAIL && this.l;
    }

    public T m(@Nullable String string) {
        return this.H(HotbarSlotResolutionStatus.SUCCESS).P(string);
    }

    public static HotbarSlotResolution W(@Nullable String string) {
        return new HotbarSlotResolution(HotbarSlotResolutionStatus.FAIL, string);
    }

    public static HotbarSlotResolution j(@Nullable String string) {
        return new HotbarSlotResolution(HotbarSlotResolutionStatus.SUCCESS, string);
    }

    public boolean h() {
        return this.Q == HotbarSlotResolutionStatus.FAIL;
    }

    public String b() {
        return this.R == null ? "" : this.R;
    }

    T P(@Nullable String string) {
        this.R = string;
        return (T)this;
    }

    public boolean Q() {
        return this.Q == HotbarSlotResolutionStatus.PENDING;
    }

    public T Q(@Nullable String string) {
        return this.H(HotbarSlotResolutionStatus.FAIL).P(string);
    }

    HotbarSlotResolutionStatus q() {
        return this.Q;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean v() {
        return this.Q == HotbarSlotResolutionStatus.SUCCESS;
    }

    public T f(@Nullable String string) {
        return this.H(HotbarSlotResolutionStatus.PENDING).P(string);
    }

    T H(HotbarSlotResolutionStatus hotbarSlotResolutionStatus) {
        this.Q = hotbarSlotResolutionStatus;
        return (T)this;
    }
}
