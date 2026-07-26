package gg.vape.module.render;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.module.render.entity.RenderEntityContextCache;
import gg.vape.module.render.esp.ESP2D;
import gg.vape.module.render.esp.ESP3D;
import gg.vape.module.render.esp.ESPOutline;
import gg.vape.module.render.esp.ESPSkeleton;
import gg.vape.render.OffscreenRenderContext;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.MutableColor;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.RenderManager;
import java.awt.Color;

public class ESP
extends Mod {
    public BooleanValue J;
    private final ModeOption V;
    private final BooleanValue C;
    public BooleanValue a;
    public BooleanValue o;
    public BooleanValue S;
    private final ESP2D Y;
    public BooleanValue D;
    private ESPOutline p;
    public final ModeValue O;
    public BooleanValue I;
    public RenderManager r;
    public final ModeOption L = new ESP3D(this, "3D").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
    private final ModeOption s;
    public BooleanValue U;
    public final ColorValue H;
    public BooleanValue Z;
    private final ModeOption t;
    public BooleanValue A;
    public BooleanValue F;
    public BooleanValue K;
    public BooleanValue c;

    public ESP() {
        super("ESP", -16711936, Category.k, "Extra Sensory Perception\nRenders an ESP on players.");
        this.Y = new ESP2D(this, "2D");
        this.t = this.Y.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.p = new ESPOutline(this, "Outline");
        this.V = this.p.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.s = new ESPSkeleton(this, "Skeleton").r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx();
        this.H = ColorValue.L(this, "Player Color", new Color(-14368924));
        this.C = BooleanValue.create(this, "Invisibles", false, "Show invisibles.");
        this.S = BooleanValue.create(this, "Enemy Only", false, "Only render enemies.");
        this.D = BooleanValue.create(this, "Priority Only", false, "Only shows the ESP box on friends/enemies.");
        this.o = BooleanValue.create(this, "Enemies List Only", false);
        this.Z = BooleanValue.create(this, "Hide Bots", false);
        this.A = BooleanValue.create(this, "Hitbox", false, "Shows the current entity hitbox size.\n(HitBoxes expansion visible)\n(3D Only)");
        this.K = BooleanValue.create(this, "Show Normal", false, "Shows the true entity hitbox size.\n(3D Only)");
        this.c = BooleanValue.create(this, "Bounding Box", true, "Shows an ESP box that wraps around the players BoundingBox.");
        this.J = BooleanValue.create(this, "Health Bar", false, "Shows a healthbar next to the ESP box");
        this.U = BooleanValue.create(this, "Name", false, "Shows a nametag above the ESP box");
        this.I = BooleanValue.create(this, "Use Displayname", false, "Shows the tab list display name.");
        this.a = BooleanValue.create(this, "Show Background", false, "Renders a box behind the text.");
        this.F = BooleanValue.create(this, "Damage", false, "Shows enemy damage relative to yours");
        this.r = Minecraft.D();
        this.A.K(this.K);
        this.O = ForgeVersion.MC_1_17.d() ? ModeValue.create((Object)this, "Mode", this.L, this.L, this.t) : (ForgeVersion.MC_1_12_2.d() ? ModeValue.create((Object)this, "Mode", this.L, this.L, this.t, this.s) : ModeValue.create((Object)this, "Mode", this.L, this.L, this.t, this.s, this.V));
        this.A.K(this.K);
        this.c.K(this.D);
        this.U.K(this.I, this.a);
        this.O.U(this.t).z(this.c, this.D, this.J, this.U, this.I, this.a);
        this.O.U(this.L).z(this.A, this.K);
        this.addValue(this.H, this.O, this.A, this.K, this.c, this.D, this.J, this.U, this.I, this.a, this.C, this.Z);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public MutableColor J(EntityPlayerSP entityPlayerSP, Object object) {
        if (OffscreenRenderContext.W()) {
            return null;
        }
        if (object == null) {
            return null;
        }
        Entity entity = new Entity(object);
        if (!entity.isInstance(MappedClasses.zm)) {
            return null;
        }
        if (object.equals(entityPlayerSP)) {
            return null;
        }
        EntityLivingBase entityLivingBase = new EntityLivingBase(object);
        RenderEntityContext renderEntityContext = RenderEntityContextCache.V(entityLivingBase, entityPlayerSP);
        if (renderEntityContext.P()) {
            return null;
        }
        if (this.Z.L().booleanValue() && renderEntityContext.D()) {
            return null;
        }
        if (!this.C.L().booleanValue() && renderEntityContext.o$src$Z$1y639j7()) {
            return null;
        }
        if (entityLivingBase.isInstance(MappedClasses.lG)) {
            MutableColor mutableColor = Vape.INSTANCE.getClientSettings().e(renderEntityContext);
            if (mutableColor == null) {
                mutableColor = this.H.q$src$Lgg_vape_utils_MutableColor_$1dowyd3();
            }
            return new MutableColor(((Color)mutableColor).getRGB(), ((Color)mutableColor).getAlpha());
        }
        return null;
    }

    public boolean c() {
        return this.O.K() == this.V && this.p.K();
    }
}

