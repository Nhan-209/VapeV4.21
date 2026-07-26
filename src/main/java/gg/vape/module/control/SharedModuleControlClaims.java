package gg.vape.module.control;

import gg.vape.input.MouseButtonInputLock;
import gg.vape.input.MovementInputLock;
import gg.vape.module.control.MouseOverUpdateControlClaim;
import gg.vape.module.control.RenderPassControlClaim;
import gg.vape.module.control.RightClickUseControlClaim;
import gg.vape.module.control.SharedModuleControlClaimPrimary;
import gg.vape.module.control.SharedModuleControlClaimSecondary;
import gg.vape.rotation.RotationControlClaim;

public class SharedModuleControlClaims {
    public static SharedModuleControlClaimPrimary L;
    public static MouseButtonInputLock h;
    public static SharedModuleControlClaimSecondary d;
    public static RightClickUseControlClaim x;
    public static MovementInputLock l;
    public static RenderPassControlClaim p;
    public static RotationControlClaim I;
    public static MouseOverUpdateControlClaim a;

    static {
        h = new MouseButtonInputLock();
        l = new MovementInputLock();
        I = new RotationControlClaim();
        p = new RenderPassControlClaim();
        x = new RightClickUseControlClaim();
        a = new MouseOverUpdateControlClaim();
        L = new SharedModuleControlClaimPrimary();
        d = new SharedModuleControlClaimSecondary();
    }
}

