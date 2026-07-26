package gg.vape.module.blatant.blockin;

import gg.vape.Vape;
import gg.vape.module.blatant.blockin.BlockPathPlanner;
import gg.vape.module.blatant.blockin.BlockPlacementGraphNode;
import gg.vape.module.combat.Sprint;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.MouseRotationController;
import gg.vape.rotation.RotationManager;
import gg.vape.utils.MathUtil;
import gg.vape.wrapper.impl.AttributeInstance;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.MonsterAttributesBridge;
import gg.vape.wrapper.impl.MovementInput;
import java.util.ArrayList;
import java.util.List;

public class BlockPlacementGraph {
    public final double P;
    public final double j;
    public final int G;
    public final ArrayList f;
    public final float p;
    public final double t;
    public boolean A;
    public boolean N;
    public final int S;
    public final double k;
    public final boolean K;
    public boolean R;
    public final double Z;
    public final boolean C;
    public final boolean U;
    public final float B;
    public final boolean V;
    public final float E;
    private static Sprint o;
    public final float g;
    public boolean D;
    public final float s;
    public final double v;
    public final boolean c;
    public final float n;
    public final double H;
    public final float L;
    public final BlockPlacementGraphNode u;
    public final float Q;
    public boolean M;
    public final int J;
    public boolean Y;
    public final double I;
    public boolean y;
    public final double x;
    private static int[] w;
    public final boolean l;

    public static void o(int[] nArray) {
        w = nArray;
    }

    public String K() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.k).append(",");
        stringBuilder.append(this.v).append(",");
        stringBuilder.append(this.P).append(",");
        stringBuilder.append(this.Z).append(",");
        stringBuilder.append(this.j).append(",");
        stringBuilder.append(this.x).append(",");
        stringBuilder.append(this.I).append(",");
        stringBuilder.append(this.H).append(",");
        stringBuilder.append(this.t).append(",");
        stringBuilder.append(this.Q).append(",");
        stringBuilder.append(this.n).append(",");
        stringBuilder.append(this.g).append(",");
        stringBuilder.append(this.L).append(",");
        stringBuilder.append(this.J).append(",");
        stringBuilder.append(this.S).append(",");
        stringBuilder.append(this.U).append(",");
        stringBuilder.append(this.V).append(",");
        stringBuilder.append(this.K).append(",");
        stringBuilder.append(this.G).append(",");
        stringBuilder.append(this.E).append(",");
        stringBuilder.append(this.B).append(",");
        stringBuilder.append(this.C).append(",");
        stringBuilder.append(this.s).append(",");
        stringBuilder.append(this.p).append(",");
        stringBuilder.append(this.l).append(",");
        stringBuilder.append(this.c).append(",");
        stringBuilder.append(this.M).append(",");
        stringBuilder.append(this.D).append(",");
        stringBuilder.append(this.R).append(",");
        stringBuilder.append(this.Y).append(",");
        stringBuilder.append(this.y).append(",");
        stringBuilder.append(this.A).append(",");
        stringBuilder.append(this.N);
        return stringBuilder.toString();
    }

    public static BlockPlacementGraph S(String string) {
        String[] stringArray = string.split(",");
        if (stringArray.length < 32) {
            throw new IllegalArgumentException("Invalid data for MovementSnapshot");
        }
        double d = Double.parseDouble(stringArray[0]);
        double d2 = Double.parseDouble(stringArray[1]);
        double d3 = Double.parseDouble(stringArray[2]);
        double d4 = Double.parseDouble(stringArray[3]);
        double d5 = Double.parseDouble(stringArray[4]);
        double d6 = Double.parseDouble(stringArray[5]);
        double d7 = Double.parseDouble(stringArray[6]);
        double d8 = Double.parseDouble(stringArray[7]);
        double d9 = Double.parseDouble(stringArray[8]);
        float f = Float.parseFloat(stringArray[9]);
        float f2 = Float.parseFloat(stringArray[10]);
        float f3 = Float.parseFloat(stringArray[11]);
        float f4 = Float.parseFloat(stringArray[12]);
        int n = Integer.parseInt(stringArray[13]);
        int n2 = Integer.parseInt(stringArray[14]);
        boolean bl = Boolean.parseBoolean(stringArray[15]);
        boolean bl2 = Boolean.parseBoolean(stringArray[16]);
        boolean bl3 = Boolean.parseBoolean(stringArray[17]);
        int n3 = Integer.parseInt(stringArray[18]);
        float f5 = Float.parseFloat(stringArray[19]);
        float f6 = Float.parseFloat(stringArray[20]);
        boolean bl4 = Boolean.parseBoolean(stringArray[21]);
        float f7 = Float.parseFloat(stringArray[22]);
        float f8 = Float.parseFloat(stringArray[23]);
        boolean bl5 = Boolean.parseBoolean(stringArray[24]);
        boolean bl6 = Boolean.parseBoolean(stringArray[25]);
        boolean bl7 = Boolean.parseBoolean(stringArray[26]);
        boolean bl8 = Boolean.parseBoolean(stringArray[27]);
        boolean bl9 = Boolean.parseBoolean(stringArray[28]);
        boolean bl10 = Boolean.parseBoolean(stringArray[29]);
        boolean bl11 = Boolean.parseBoolean(stringArray[30]);
        boolean bl12 = Boolean.parseBoolean(stringArray[31]);
        boolean bl13 = Boolean.parseBoolean(stringArray[32]);
        return new BlockPlacementGraph(d, d2, d3, d4, d5, d6, d7, d8, d9, f, f2, f3, f4, n, n2, bl, bl2, bl3, n3, f5, f6, bl4, f7, f8, bl5, bl6, bl7, bl8, bl9, bl10, bl11, bl12, bl13);
    }

    public boolean G(BlockPlacementGraph blockPlacementGraph) {
        return this.M == blockPlacementGraph.M && this.D == blockPlacementGraph.D && this.R == blockPlacementGraph.R && this.Y == blockPlacementGraph.Y;
    }

    public BlockPlacementGraph(BlockPathPlanner blockPathPlanner) {
        EntityPlayer entityPlayer = blockPathPlanner.T();
        this.k = entityPlayer.z();
        this.v = entityPlayer.N();
        this.P = entityPlayer.h();
        this.Z = entityPlayer.f();
        this.j = entityPlayer.H();
        this.x = entityPlayer.R();
        this.I = entityPlayer.t();
        this.H = entityPlayer.q();
        this.t = entityPlayer.T();
        this.Q = entityPlayer.J();
        this.n = entityPlayer.V();
        this.g = entityPlayer.j();
        this.L = entityPlayer.D();
        this.u = this.s(blockPathPlanner.H());
        this.J = blockPathPlanner.D();
        this.S = blockPathPlanner.g();
        this.U = entityPlayer.b$src$Z$fqlxe4();
        this.V = blockPathPlanner.Y();
        this.K = entityPlayer.B$src$Z$f90iek();
        this.G = entityPlayer.B$src$I$14s4bbr();
        this.E = entityPlayer.y$src$F$15mczw1();
        this.B = entityPlayer.C$src$F$1i1kt1e();
        this.C = entityPlayer.D$src$Z$fa43la();
        AttributeInstance attributeInstance = ForgeVersion.MC_1_20_6.d() ? entityPlayer.t(MonsterAttributesBridge.U()) : entityPlayer.h(MonsterAttributesBridge.B());
        this.f = new ArrayList(attributeInstance.I());
        this.p = blockPathPlanner.k();
        this.s = blockPathPlanner.U();
        this.l = blockPathPlanner.K$src$Z$17o55j8();
        this.c = blockPathPlanner.Y();
        this.M = blockPathPlanner.C();
        this.D = blockPathPlanner.s();
        this.R = blockPathPlanner.o();
        this.Y = blockPathPlanner.d$src$Z$181w0d9();
        this.y = blockPathPlanner.R();
        this.A = blockPathPlanner.g$src$Z$183je5c();
        if (o == null) {
            o = Vape.INSTANCE.getModManager().getMod(Sprint.class);
        }
        this.N = blockPathPlanner.i() || o.r$src$Z$14eylz9();
    }

    private BlockPlacementGraph(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, float f, float f2, float f3, float f4, int n, int n2, boolean bl, boolean bl2, boolean bl3, int n3, float f5, float f6, boolean bl4, float f7, float f8, boolean bl5, boolean bl6, boolean bl7, boolean bl8, boolean bl9, boolean bl10, boolean bl11, boolean bl12, boolean bl13) {
        this.k = d;
        this.v = d2;
        this.P = d3;
        this.Z = d4;
        this.j = d5;
        this.x = d6;
        this.I = d7;
        this.H = d8;
        this.t = d9;
        this.Q = f;
        this.n = f2;
        this.g = f3;
        this.L = f4;
        this.J = n;
        this.S = n2;
        this.U = bl;
        this.V = bl2;
        this.K = bl3;
        this.G = n3;
        this.E = f5;
        this.B = f6;
        this.C = bl4;
        this.f = new ArrayList();
        this.s = f7;
        this.p = f8;
        this.l = bl5;
        this.c = bl6;
        this.M = bl7;
        this.D = bl8;
        this.R = bl9;
        this.Y = bl10;
        this.y = bl11;
        this.A = bl12;
        this.N = bl13;
        this.u = null;
    }

    private static IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        return illegalArgumentException;
    }

    public boolean Q(BlockPlacementGraph blockPlacementGraph) {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.k != blockPlacementGraph.k) {
            stringBuilder.append("posX: ").append(blockPlacementGraph.k).append(" -> ").append(this.k).append("\n");
        }
        if (this.v != blockPlacementGraph.v) {
            stringBuilder.append("posY: ").append(blockPlacementGraph.v).append(" -> ").append(this.v).append("\n");
        }
        if (this.P != blockPlacementGraph.P) {
            stringBuilder.append("posZ: ").append(blockPlacementGraph.P).append(" -> ").append(this.P).append("\n");
        }
        if (ForgeVersion.MC_1_16_5.B()) {
            if (this.Z != blockPlacementGraph.Z) {
                stringBuilder.append("prevPosX: ").append(blockPlacementGraph.Z).append(" -> ").append(this.Z).append("\n");
            }
            if (this.j != blockPlacementGraph.j) {
                stringBuilder.append("prevPosY: ").append(blockPlacementGraph.j).append(" -> ").append(this.j).append("\n");
            }
            if (this.x != blockPlacementGraph.x) {
                stringBuilder.append("prevPosZ: ").append(blockPlacementGraph.x).append(" -> ").append(this.x).append("\n");
            }
        }
        if (this.I != blockPlacementGraph.I) {
            stringBuilder.append("motionX: ").append(blockPlacementGraph.I).append(" -> ").append(this.I).append("\n");
        }
        if (this.H != blockPlacementGraph.H) {
            stringBuilder.append("motionY: ").append(blockPlacementGraph.H).append(" -> ").append(this.H).append("\n");
        }
        if (this.t != blockPlacementGraph.t) {
            stringBuilder.append("motionZ: ").append(blockPlacementGraph.t).append(" -> ").append(this.t).append("\n");
        }
        float f = RotationManager.b.E();
        float f2 = f * 0.6f + 0.2f;
        float f3 = f2 * f2 * f2 * 8.0f;
        float f4 = (float)(0.0 + (double)f3 * 0.15);
        if (this.J != blockPlacementGraph.J) {
            stringBuilder.append("sprintingTicksLeft: ").append(blockPlacementGraph.J).append(" -> ").append(this.J).append("\n");
        }
        if (this.S != blockPlacementGraph.S) {
            stringBuilder.append("sprintToggleTimer: ").append(blockPlacementGraph.S).append(" -> ").append(this.S).append("\n");
        }
        if (this.U != blockPlacementGraph.U) {
            stringBuilder.append("onGround: ").append(blockPlacementGraph.U).append(" -> ").append(this.U).append("\n");
        }
        if (this.V != blockPlacementGraph.V) {
            stringBuilder.append("sneaking: ").append(blockPlacementGraph.V).append(" -> ").append(this.V).append("\n");
        }
        if (this.K != blockPlacementGraph.K) {
            stringBuilder.append("sprinting: ").append(blockPlacementGraph.K).append(" -> ").append(this.K).append("\n");
        }
        if (this.G != blockPlacementGraph.G) {
            stringBuilder.append("jumpTicks: ").append(blockPlacementGraph.G).append(" -> ").append(this.G).append("\n");
        }
        if (this.E != blockPlacementGraph.E) {
            stringBuilder.append("jumpMovementFactor: ").append(blockPlacementGraph.E).append(" -> ").append(this.E).append("\n");
        }
        if (this.B != blockPlacementGraph.B) {
            stringBuilder.append("aiMoveSpeed: ").append(blockPlacementGraph.B).append(" -> ").append(this.B).append("\n");
        }
        if (this.C != blockPlacementGraph.C) {
            stringBuilder.append("inWater: ").append(blockPlacementGraph.C).append(" -> ").append(this.C).append("\n");
        }
        if (this.s != blockPlacementGraph.s) {
            stringBuilder.append("moveStrafe: ").append(blockPlacementGraph.s).append(" -> ").append(this.s).append("\n");
        }
        if (this.p != blockPlacementGraph.p) {
            stringBuilder.append("moveForward: ").append(blockPlacementGraph.p).append(" -> ").append(this.p).append("\n");
        }
        if (this.l != blockPlacementGraph.l) {
            stringBuilder.append("jump: ").append(blockPlacementGraph.l).append(" -> ").append(this.l).append("\n");
        }
        if (this.c != blockPlacementGraph.c) {
            stringBuilder.append("sneak: ").append(blockPlacementGraph.c).append(" -> ").append(this.c).append("\n");
        }
        if (this.u != null && blockPlacementGraph.u != null) {
            String string = this.u.C(blockPlacementGraph.u);
            if (!string.isEmpty()) {
                stringBuilder.append("AimJob Differences (Snapshot -> Current): \n");
                stringBuilder.append(string);
            }
        } else {
            float f5;
            float f6 = MathUtil.wrapAngleTo180(blockPlacementGraph.Q - this.Q);
            if (this.Q != blockPlacementGraph.Q && f6 >= f4) {
                stringBuilder.append("yaw: ").append(blockPlacementGraph.Q).append(" -> ").append(this.Q).append("\n");
                f5 = f6 / f4;
                stringBuilder.append("Yaw Diff Px: ").append(f5).append("\n");
            }
            f5 = MathUtil.wrapAngleTo180(blockPlacementGraph.n - this.n);
            if (this.n != blockPlacementGraph.n && f5 >= f4) {
                stringBuilder.append("pitch: ").append(blockPlacementGraph.n).append(" -> ").append(this.n).append("\n");
                float f7 = f5 / f4;
                stringBuilder.append("Pitch Diff Px: ").append(f7).append("\n");
            }
        }
        if (stringBuilder.length() > 0) {
            // empty if block
        }
        return stringBuilder.length() == 0;
    }

    static {
        BlockPlacementGraph.o(null);
    }

    private BlockPlacementGraphNode s(MouseRotationController mouseRotationController) {
        BlockPlacementGraphNode blockPlacementGraphNode = null;
        if (mouseRotationController != null) {
            blockPlacementGraphNode = mouseRotationController instanceof AdaptiveRotationController ? new BlockPlacementGraphNode((AdaptiveRotationController)mouseRotationController) : new BlockPlacementGraphNode(mouseRotationController, this.Q, this.n);
        }
        return blockPlacementGraphNode;
    }

    public static String H(List<BlockPlacementGraph> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("posX,posY,posZ,prevPosX,prevPosY,prevPosZ,motionX,motionY,motionZ,yaw,pitch,prevYaw,prevPitch,sprintingTicksLeft,sprintToggleTimer,onGround,sneaking,sprinting,jumpTicks,jumpMovementFactor,aiMoveSpeed,moveStrafe,moveForward,jump,sneak,inputForward,inputBackwards,inputLeft,inputRight,inputSneak,inputJump,inputSprint,targetYaw,targetPitch,deltaX,deltaY,yawChanged,pitchChanged,isCompleted,currentYaw,currentPitch\n");
        for (BlockPlacementGraph blockPlacementGraph : list) {
            stringBuilder.append(blockPlacementGraph.k).append(",").append(blockPlacementGraph.v).append(",").append(blockPlacementGraph.P).append(",").append(blockPlacementGraph.Z).append(",").append(blockPlacementGraph.j).append(",").append(blockPlacementGraph.x).append(",").append(blockPlacementGraph.I).append(",").append(blockPlacementGraph.H).append(",").append(blockPlacementGraph.t).append(",").append(blockPlacementGraph.Q).append(",").append(blockPlacementGraph.n).append(",").append(blockPlacementGraph.g).append(",").append(blockPlacementGraph.L).append(",").append(blockPlacementGraph.J).append(",").append(blockPlacementGraph.S).append(",").append(blockPlacementGraph.U).append(",").append(blockPlacementGraph.V).append(",").append(blockPlacementGraph.K).append(",").append(blockPlacementGraph.G).append(",").append(blockPlacementGraph.E).append(",").append(blockPlacementGraph.B).append(",").append(blockPlacementGraph.s).append(",").append(blockPlacementGraph.p).append(",").append(blockPlacementGraph.l).append(",").append(blockPlacementGraph.c).append(",").append(blockPlacementGraph.M).append(",").append(blockPlacementGraph.D).append(",").append(blockPlacementGraph.R).append(",").append(blockPlacementGraph.Y).append(",").append(blockPlacementGraph.y).append(",").append(blockPlacementGraph.A).append(",").append(blockPlacementGraph.N).append(",");
            if (blockPlacementGraph.u != null) {
                stringBuilder.append(blockPlacementGraph.u.W).append(",").append(blockPlacementGraph.u.g).append(",").append(blockPlacementGraph.u.e).append(",").append(blockPlacementGraph.u.m).append(",").append(blockPlacementGraph.u.X).append(",").append(blockPlacementGraph.u.Y).append(",").append(blockPlacementGraph.u.z).append(",").append(blockPlacementGraph.u.M).append(",").append(blockPlacementGraph.u.u).append("\n");
                continue;
            }
            stringBuilder.append(",,,,,,,,\n");
        }
        return stringBuilder.toString();
    }

    public BlockPlacementGraph(EntityPlayerSP entityPlayerSP) {
        this.k = entityPlayerSP.z();
        this.v = entityPlayerSP.N();
        this.P = entityPlayerSP.h();
        this.Z = entityPlayerSP.f();
        this.j = entityPlayerSP.H();
        this.x = entityPlayerSP.R();
        this.I = entityPlayerSP.t();
        this.H = entityPlayerSP.q();
        this.t = entityPlayerSP.T();
        this.Q = entityPlayerSP.J();
        this.n = entityPlayerSP.V();
        this.g = entityPlayerSP.j();
        this.L = entityPlayerSP.D();
        this.u = this.s(RotationManager.b.w());
        this.J = entityPlayerSP.z$src$I$1uboxyr();
        this.S = entityPlayerSP.L$src$I$1tmeeo5();
        this.U = entityPlayerSP.b$src$Z$fqlxe4();
        this.V = entityPlayerSP.P();
        this.K = entityPlayerSP.B$src$Z$f90iek();
        this.G = entityPlayerSP.B$src$I$14s4bbr();
        this.E = entityPlayerSP.y$src$F$15mczw1();
        this.B = entityPlayerSP.C$src$F$1i1kt1e();
        this.C = entityPlayerSP.D$src$Z$fa43la();
        AttributeInstance attributeInstance = ForgeVersion.MC_1_20_6.d() ? entityPlayerSP.t(MonsterAttributesBridge.U()) : entityPlayerSP.h(MonsterAttributesBridge.B());
        this.f = new ArrayList(attributeInstance.I());
        MovementInput movementInput = entityPlayerSP.movementInput();
        this.s = movementInput.T();
        this.p = movementInput.D();
        this.l = movementInput.G();
        this.c = movementInput.D$src$Z$v5d6e8();
        GameSettings gameSettings = Minecraft.gameSettings();
        this.M = gameSettings.Y().isKeyDown();
        this.D = gameSettings.s().isKeyDown();
        this.R = gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg().isKeyDown();
        this.Y = gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3().isKeyDown();
        this.y = gameSettings.d$src$Lgg_vape_wrapper_impl_KeyBinding_$adn2z0().isKeyDown();
        this.A = gameSettings.O().isKeyDown();
        if (o == null) {
            o = Vape.INSTANCE.getModManager().getMod(Sprint.class);
        }
        this.N = gameSettings.r().isKeyDown() || o.r$src$Z$14eylz9();
    }

    public boolean h(BlockPlacementGraph blockPlacementGraph) {
        return this.G(blockPlacementGraph) && this.y == blockPlacementGraph.y && this.A == blockPlacementGraph.A && this.N == blockPlacementGraph.N;
    }

    public static int[] Q() {
        return w;
    }
}

