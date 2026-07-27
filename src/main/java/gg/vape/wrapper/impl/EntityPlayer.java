package gg.vape.wrapper.impl;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.mappings.MEntityPlayer;
import gg.vape.module.render.freecam.FreecamPlayerBridge;
import gg.vape.wrapper.impl.AbstractClientPlayer;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerMacroBridge;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.FoodStats;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.ModelPlayer;
import gg.vape.wrapper.impl.MoverType;
import gg.vape.wrapper.impl.NetHandlerPlayClientImpl;
import gg.vape.wrapper.impl.Team;
import gg.vape.wrapper.impl.Vec3;

public class EntityPlayer
extends EntityLivingBase {
    private static boolean H;

    public void j(double d) {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return;
            }
            freecamPlayerBridge.J(d);
            return;
        }
        MEntityPlayer.r(EntityPlayer.c.getMappings().hd, this.I, d);
    }

    public void V$src$V$1ic0wp1() {
        MEntityPlayer.i(EntityPlayer.c.getMappings().hd, this.I);
    }

    public static boolean z$src$Z$1ivti5h() {
        boolean bl = EntityPlayer.T$src$Z$1iaxblr();
        return false;
    }

    public boolean X$src$Z$1id4hz7() {
        return MEntityPlayer.j(EntityPlayer.c.getMappings().hd, this.I);
    }

    public void G(double d) {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return;
            }
            freecamPlayerBridge.B(d);
            return;
        }
        MEntityPlayer.U(EntityPlayer.c.getMappings().hd, this.I, d);
    }

    public void Z$src$V$1ie832h() {
        MEntityPlayer.a(EntityPlayer.c.getMappings().hd, this.I);
    }

    public boolean l$src$Z$1io4duf() {
        return MEntityPlayer.U(EntityPlayer.c.getMappings().hd, this.I);
    }

    public float C$src$F$1i1kt1e() {
        return MEntityPlayer.y(EntityPlayer.c.getMappings().hd, this.I);
    }

    public FoodStats Y$src$Lgg_vape_wrapper_impl_FoodStats_$fakh1z() {
        return new FoodStats(MEntityPlayer.s$src$Ljava_lang_Object_$11essic(EntityPlayer.c.getMappings().hd, this.I));
    }

    static {
        if (!EntityPlayer.T$src$Z$1iaxblr()) {
            EntityPlayer.f(true);
        }
    }

    public double F$src$D$1i386rr() {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return -1.0;
            }
            return freecamPlayerBridge.L();
        }
        return MEntityPlayer.F(EntityPlayer.c.getMappings().hd, this.I);
    }

    public ItemStack getHeldItemHand() {
        return new ItemStack(MEntityPlayer.M$src$Ljava_lang_Object_$159ckze(EntityPlayer.c.getMappings().hd, this.I));
    }

    public void w$src$V$1iu649y() {
        if (!G) {
            MEntityPlayer.A(EntityPlayer.c.getMappings().hd, this.getObject());
        }
    }

    public void S(double d) {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return;
            }
            freecamPlayerBridge.y(d);
            return;
        }
        MEntityPlayer.O(EntityPlayer.c.getMappings().hd, this.I, d);
    }

    public int F$src$I$1i386w2() {
        return MEntityPlayer.W(EntityPlayer.c.getMappings().hd, this.I);
    }

    public double L$src$D$1i6iybx() {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return -1.0;
            }
            return freecamPlayerBridge.A();
        }
        return MEntityPlayer.q(EntityPlayer.c.getMappings().hd, this.I);
    }

    public Container p$src$Lgg_vape_wrapper_impl_Container_$1a6go00() {
        return new Container(MEntityPlayer.u(EntityPlayer.c.getMappings().hd, this.I));
    }

    public void d$src$V$1ijq103() {
        MEntityPlayer.p$src$V$19rwqod(EntityPlayer.c.getMappings().hd, this.I);
    }

    private FreecamPlayerBridge M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm() {
        if (ForgeVersion.MC_1_21_10.d() && this.isInstance(MappedClasses.zt)) {
            AbstractClientPlayer abstractClientPlayer = new AbstractClientPlayer(this.I);
            return abstractClientPlayer.E();
        }
        return null;
    }

    public Vec3 m(Vec3 vec3, MoverType moverType) {
        return new Vec3(MEntityPlayer.v(EntityPlayer.c.getMappings().hd, this.I, vec3.getObject(), moverType.getObject()));
    }

    public Team J$src$Lgg_vape_wrapper_impl_Team_$1jrmnx4() {
        return new Team(MEntityPlayer.v$src$Ljava_lang_Object_$92h3ox(EntityPlayer.c.getMappings().hd, this.I));
    }

    public void i$src$V$1imgzyw() {
        MEntityPlayer.y$src$V$epmkwm(EntityPlayer.c.getMappings().hd, this.I);
    }

    public InventoryPlayer V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6() {
        return new InventoryPlayer(EntityPlayer.c.getMappings().hd.K(this.I));
    }

    public void c(double d) {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return;
            }
            freecamPlayerBridge.G(d);
            return;
        }
        MEntityPlayer.J(EntityPlayer.c.getMappings().hd, this.I, d);
    }

    public void P(double d) {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return;
            }
            freecamPlayerBridge.m(d);
            return;
        }
        MEntityPlayer.B(EntityPlayer.c.getMappings().hd, this.I, d);
    }

    public void C(Entity entity) {
        MEntityPlayer.o(EntityPlayer.c.getMappings().hd, this.I, entity.getObject());
    }

    public void p(double d) {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return;
            }
            freecamPlayerBridge.i(d);
            return;
        }
        MEntityPlayer.g(EntityPlayer.c.getMappings().hd, this.I, d);
    }

    public boolean i$src$Z$1imh02c() {
        return MEntityPlayer.O(EntityPlayer.c.getMappings().hd, this.I);
    }

    public float getCooledAttackStrength(float f) {
        return MEntityPlayer.d(EntityPlayer.c.getMappings().hd, this.I, f);
    }

    public double s$src$D$1iryxh0() {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return -1.0;
            }
            return freecamPlayerBridge.A();
        }
        return MEntityPlayer.S(EntityPlayer.c.getMappings().hd, this.I);
    }

    public boolean N$src$Z$1i7mk1l() {
        return MEntityPlayer.x(EntityPlayer.c.getMappings().hd, this.I);
    }

    public ModelPlayer C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86() {
        return new ModelPlayer(MEntityPlayer.p(EntityPlayer.c.getMappings().hd, this.I));
    }

    public void e(Entity entity) {
        MEntityPlayer.c(EntityPlayer.c.getMappings().hd, this.I, entity.getObject());
    }

    public boolean s$src$Z$1iryxzy() {
        if (ForgeVersion.MC_1_21_11.d()) {
            if (this.isInstance(MappedClasses.z5)) {
                EntityPlayerSP entityPlayerSP = new EntityPlayerSP(this.I);
                NetHandlerPlayClientImpl netHandlerPlayClientImpl = entityPlayerSP.sendQueue();
                return netHandlerPlayClientImpl.M();
            }
            return false;
        }
        return MEntityPlayer.v$src$Z$p2dyxb(EntityPlayer.c.getMappings().hd, this.I);
    }

    public double C$src$D$1i1kszo() {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return -1.0;
            }
            return freecamPlayerBridge.j();
        }
        return MEntityPlayer.v(EntityPlayer.c.getMappings().hd, this.I);
    }

    public EntityPlayer(Object object) {
        super(object);
    }

    public void c(Entity entity) {
        MEntityPlayer.e(EntityPlayer.c.getMappings().hd, this.I, entity.getObject());
    }

    public boolean o$src$Z$1iprrmi() {
        return MEntityPlayer.P(EntityPlayer.c.getMappings().hd, this.I);
    }

    public double a$src$D$1ii2msi() {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return -1.0;
            }
            return freecamPlayerBridge.X();
        }
        return MEntityPlayer.l(EntityPlayer.c.getMappings().hd, this.I);
    }

    public static void f(boolean bl) {
        H = bl;
    }

    public void N(int n) {
        MEntityPlayer.p(EntityPlayer.c.getMappings().hd, this.I, n);
    }

    public Container F$src$Lgg_vape_wrapper_impl_Container_$152y6lm() {
        return new Container(MEntityPlayer.G$src$Ljava_lang_Object_$qwvwn4(EntityPlayer.c.getMappings().hd, this.I));
    }

    public static boolean T$src$Z$1iaxblr() {
        return H;
    }

    public boolean y$src$Z$1iv9pk4() {
        return MEntityPlayer.G$src$Z$1982x40(EntityPlayer.c.getMappings().hd, this.I);
    }

    public GameProfile c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937() {
        return new GameProfile(MEntityPlayer.S$src$Ljava_lang_Object_$1jlt9bo(EntityPlayer.c.getMappings().hd, this.I));
    }

    public int j$src$I$1in0s92() {
        return MEntityPlayer.M(EntityPlayer.c.getMappings().hd, this.I);
    }

    public double G$src$D$1i3rzd4() {
        if (ForgeVersion.MC_1_21_10.d()) {
            FreecamPlayerBridge freecamPlayerBridge = this.M$src$Lgg_vape_module_render_freecam_FreecamPlayerBrid$1gqqowm();
            if (freecamPlayerBridge == null) {
                return -1.0;
            }
            return freecamPlayerBridge.H();
        }
        return MEntityPlayer.s(EntityPlayer.c.getMappings().hd, this.I);
    }


    public EntityPlayerMacroBridge K$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1agjn9() {
        return new EntityPlayerMacroBridge(MEntityPlayer.k(EntityPlayer.c.getMappings().hd, this.I));
    }

    public float i$src$F$1imgzl4() {
        return MEntityPlayer.G(EntityPlayer.c.getMappings().hd, this.I);
    }

    public boolean w$src$Z$1iu64de() {
        return MEntityPlayer.L(EntityPlayer.c.getMappings().hd, this.I);
    }

    public ModelPlayer a_xf_0_C() {
        return this.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86();
    }

    public boolean boolean_o() {
        return this.o$src$Z$1iprrmi();
    }
}

