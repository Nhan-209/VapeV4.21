package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.HotbarSlotResolutionStatus;
import gg.vape.runtime.ObfuscatedRuntimeException;
import org.jetbrains.annotations.Nullable;

public class HotbarSlotResolution<T extends HotbarSlotResolution<T>> {
    @Nullable
    private String message;
    private HotbarSlotResolutionStatus status;
    private boolean forced = false;

    public static HotbarSlotResolution J(@Nullable String string) {
        return new HotbarSlotResolution(HotbarSlotResolutionStatus.PENDING, string);
    }

    public T i(boolean bl) {
        this.forced = bl;
        return (T)this;
    }

    HotbarSlotResolution(HotbarSlotResolutionStatus hotbarSlotResolutionStatus, @Nullable String string) {
        this.status = hotbarSlotResolutionStatus;
        this.message = string;
    }

    public T A() {
        this.forced = true;
        return (T)this;
    }

    public boolean B() {
        return this.status == HotbarSlotResolutionStatus.PENDING || this.status == HotbarSlotResolutionStatus.SUCCESS || this.status == HotbarSlotResolutionStatus.FAIL && this.forced;
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
        return this.status == HotbarSlotResolutionStatus.FAIL;
    }

    public String b() {
        return this.message == null ? "" : this.message;
    }

    T P(@Nullable String string) {
        this.message = string;
        return (T)this;
    }

    public boolean Q() {
        return this.status == HotbarSlotResolutionStatus.PENDING;
    }

    public T Q(@Nullable String string) {
        return this.H(HotbarSlotResolutionStatus.FAIL).P(string);
    }

    HotbarSlotResolutionStatus q() {
        return this.status;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public boolean v() {
        return this.status == HotbarSlotResolutionStatus.SUCCESS;
    }

    public T f(@Nullable String string) {
        return this.H(HotbarSlotResolutionStatus.PENDING).P(string);
    }

    T H(HotbarSlotResolutionStatus hotbarSlotResolutionStatus) {
        this.status = hotbarSlotResolutionStatus;
        return (T)this;
    }
}
