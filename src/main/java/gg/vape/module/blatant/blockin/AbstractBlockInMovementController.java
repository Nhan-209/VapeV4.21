package gg.vape.module.blatant.blockin;

import gg.vape.module.blatant.blockin.BlockInMovementController;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.World;

public abstract class AbstractBlockInMovementController
implements BlockInMovementController {
    protected boolean M = false;
    protected final EntityPlayer p;
    protected final World P;
    protected boolean E = false;
    protected int U = 0;
    private static int L;
    protected boolean f = false;
    protected float d = 0.0f;
    protected boolean R = false;
    protected final EntityPlayer O;
    protected boolean W = false;
    protected int B = 0;
    protected final EntityPlayerSP D;
    protected boolean A = false;
    protected boolean b = false;
    protected float Z = 0.0f;
    protected boolean r = false;
    protected boolean g = false;

    public boolean boolean_y() {
        return this.W;
    }

    public void E(boolean bl) {
        this.g = bl;
    }

    public void G(boolean bl) {
        this.E = bl;
    }

    public boolean boolean_w() {
        return this.E;
    }

    public boolean boolean_s() {
        return this.R;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException o8_02) {
        return o8_02;
    }

    public boolean boolean_z() {
        return this.M;
    }

    public float float_x() {
        return this.Z;
    }

    public void r(boolean bl) {
        this.R = bl;
    }

    public int int_C() {
        return this.B;
    }

    public void U(boolean bl) {
        this.A = bl;
    }

    public int int_K() {
        return this.U;
    }

    public void T(boolean bl) {
        this.r = bl;
    }

    public void q(boolean bl) {
        this.f = bl;
    }

    public static void J(int n) {
        L = n;
    }

    public boolean c() {
        return this.g;
    }

    public void Z(boolean bl) {
        this.M = bl;
    }

    public static int int_w() {
        return L;
    }

    public AbstractBlockInMovementController(EntityPlayer x_2, EntityPlayerSP xH2, EntityPlayer x_3, World world) {
        this.p = x_2;
        this.D = xH2;
        this.O = x_3;
        this.P = world;
    }

    public static int n() {
        int n = AbstractBlockInMovementController.int_w();
        return 116;
    }

    public boolean boolean_l() {
        return this.f;
    }

    public float float_q() {
        return this.d;
    }

    public boolean boolean_C() {
        return this.A;
    }

    public boolean boolean_N() {
        return this.r;
    }

    public boolean boolean_K() {
        return this.b;
    }

    static {
        if (AbstractBlockInMovementController.int_w() != 0) {
            AbstractBlockInMovementController.J(9);
        }
    }

    public /* synthetic */ int C$src$I$157pmda() {
        return this.int_C();
    }

    public /* synthetic */ boolean C$src$Z$157pmrx() {
        return this.boolean_C();
    }

    public /* synthetic */ int K$src$I$15c3z46() {
        return this.int_K();
    }

    public /* synthetic */ boolean K$src$Z$15c3zit() {
        return this.boolean_K();
    }

    public /* synthetic */ boolean N$src$Z$15drdaw() {
        return this.boolean_N();
    }

    public /* synthetic */ boolean l$src$Z$15u973q() {
        return this.boolean_l();
    }

    public /* synthetic */ float q$src$F$15x05lb() {
        return this.float_q();
    }

    public /* synthetic */ boolean s$src$Z$15y3r99() {
        return this.boolean_s();
    }

    public /* synthetic */ boolean w() {
        return this.boolean_w();
    }

    public /* synthetic */ float x$src$F$160upqu() {
        return this.float_x();
    }

    public /* synthetic */ boolean y$src$Z$161eitf() {
        return this.boolean_y();
    }

    public /* synthetic */ boolean z$src$Z$161ybes() {
        return this.boolean_z();
    }
}

