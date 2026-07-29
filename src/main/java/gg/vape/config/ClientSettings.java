package gg.vape.config;

import gg.vape.Vape;
import gg.vape.config.ClientSettingsBindChangeListener;
import gg.vape.config.PublicProfileSettings;
import gg.vape.input.BindSet;
import gg.vape.input.GlfwToVirtualKeyCodeMap;
import gg.vape.input.KeyBindingInputState;
import gg.vape.input.KeyboardCodeUtil;
import gg.vape.input.KeyboardInput;
import gg.vape.input.MouseInput;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Mod;
import gg.vape.module.blatant.AntiBot;
import gg.vape.module.render.entity.RenderEntityContext;
import gg.vape.unmap.ModeOption;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.KeyBoardUtil;
import gg.vape.utils.MutableColor;
import gg.vape.utils.RotationUtil;
import gg.vape.value.BindValue;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.ModeValue;
import gg.vape.wrapper.impl.AttributeModifier;
import gg.vape.wrapper.impl.Enchantment;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumCreatureAttribute;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ItemAttributeModifiers;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionRegistry;
import java.awt.Color;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.jetbrains.annotations.Nullable;

public class ClientSettings {
    public BooleanValue estimateFallDamage;
    public BooleanValue T;
    public BooleanValue S;
    private static int n;
    public final BooleanValue A;
    private static final long i;
    public BooleanValue N;
    public BindValue Z;
    public final BooleanValue c;
    private static Set<Integer> l;
    @Nullable
    public AntiBot q;
    public BooleanValue healthPrediction;
    private static final Random k;
    public final BooleanValue e;
    public static boolean d;
    public static String F;
    public static boolean H;
    public ModeValue W;
    public BooleanValue estimateFoodHealing;
    static double[] f;
    public static final ModeOption u;
    public final BooleanValue C;
    public static final ModeOption O;
    public final ModeValue o;
    public ColorValue w = ColorValue.create(this, "Gui Color", new Color(5, 134, 105));
    public static final ModeOption Y;

    public static boolean E(Entity entity) {
        return ClientSettings.B(entity.S());
    }

    public static void b(KeyBinding keyBinding, boolean bl) {
        int n = ClientSettings.H(keyBinding);
        if (n > 0) {
            if (bl) {
                KeyBoardUtil.l(n);
            } else {
                KeyBoardUtil.K(n);
            }
        }
    }

    public static boolean B(int n) {
        return l.contains(n);
    }

    public static boolean l(int n) {
        if (n < 0) {
            return MouseInput.isButtonDown(100 + n);
        }
        return KeyboardInput.isKeyDown(n);
    }

    public static boolean H$src$Z$9w16bz(KeyBinding keyBinding) {
        int n;
        int n2 = ClientSettings.H(keyBinding);
        int n3 = n = ForgeVersion.MC_1_21_4.d() ? 4 : 0;
        if (n2 > n) {
            return KeyBoardUtil.m(n2);
        }
        if (ForgeVersion.MC_1_21_4.v()) {
            n2 += 100;
        }
        return KeyBindingInputState.isMouseButtonDown(n2);
    }

    public static double X(ItemStack itemStack) {
        double d = 0.0;
        ItemAttributeModifiers itemAttributeModifiers = itemStack.o();
        if (itemAttributeModifiers.i() > 0) {
            int n = ForgeVersion.MC_1_12_2.L() ? 1 : 0;
            AttributeModifier attributeModifier = new AttributeModifier(itemAttributeModifiers.f().toArray()[n]);
            d = attributeModifier.getAmount();
        }
        return d += (double)EnchantmentHelper.C(itemStack, EnumCreatureAttribute.R());
    }

    public static void I(int n) {
        l.remove(n);
    }

    public static double c(ItemStack itemStack) {
        return ItemStackScoreUtil.O(itemStack);
    }

    public static boolean M() {
        int n;
        int n2 = Minecraft.gameSettings().F().getKeyCode();
        int n3 = n = ForgeVersion.MC_1_16_5.d() ? n2 : 100 + n2;
        if (n == 0) {
            return KeyBindingInputState.isLeftButtonDown();
        }
        return KeyBindingInputState.isRightButtonDown();
    }

    public static void D(EntityLivingBase entityLivingBase) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        int n = 0;
        int n2 = PublicProfileSettings.c();
        if (!entityPlayerSP.i(PotionRegistry.E)) {
            int n3 = n = entityPlayerSP.i(PotionRegistry.u) ? 6 + (1 + entityPlayerSP.b(PotionRegistry.u).L()) * 2 : 6;
        }
        if (!entityPlayerSP.Y$src$Z$154rldp() || entityPlayerSP.i() >= n / 2 || entityPlayerSP.i() < 0) {
            entityPlayerSP.p(-1);
            entityPlayerSP.H(true);
        }
        boolean bl = entityPlayerSP.M$src$F$ff28gb() > 0.0f && !entityPlayerSP.b$src$Z$fqlxe4() && !entityPlayerSP.S$src$Z$151gttj() && !entityPlayerSP.h$src$Z$ftwoya() && !entityPlayerSP.i(PotionRegistry.K) && entityPlayerSP.S$src$Lgg_vape_wrapper_impl_Entity_$dgzs12().isNull();
        float f = EnchantmentHelper.C(entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt(), entityLivingBase.k$src$Lgg_vape_wrapper_impl_EnumCreatureAttribute_$uojvxj());
        if (bl) {
            entityPlayerSP.e(entityLivingBase);
        }
        if (f > 0.0f) {
            entityPlayerSP.C(entityLivingBase);
        }
    }

    public boolean e(@Nullable EntityPlayerSP entityPlayerSP, Entity entity) {
        if (this.q == null || !this.q.r$src$Z$14eylz9()) {
            return false;
        }
        return this.q.isTeammate(entityPlayerSP, entity);
    }

    public MutableColor e(RenderEntityContext renderEntityContext) {
        String string = renderEntityContext.getName();
        if (Vape.INSTANCE.getFriendManager().E(string) && Vape.INSTANCE.getFriendManager().q.getEffectiveValue().booleanValue()) {
            return Vape.INSTANCE.getFriendManager().R.getMutableColor();
        }
        if (Vape.INSTANCE.getEnemyManager().q(string) && Vape.INSTANCE.getEnemyManager().p.getEffectiveValue().booleanValue()) {
            return Vape.INSTANCE.getEnemyManager().i.getMutableColor();
        }
        return this.l(renderEntityContext);
    }

    public boolean g(Entity entity, boolean bl) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entity.isNull()) {
            return false;
        }
        if (entity.isInstance(MappedClasses.zS)) {
            return false;
        }
        if (entity.equals(entityPlayerSP)) {
            return false;
        }
        if (!entity.isInstance(MappedClasses.zm)) {
            return false;
        }
        if (ForgeVersion.MC_1_7_10.Y() && entity.isInstance(MappedClasses.FT)) {
            return false;
        }
        EntityLivingBase entityLivingBase = new EntityLivingBase(entity.getObject());
        if (entityLivingBase.w$src$F$15l9epb() <= 0.0f) {
            return false;
        }
        if (bl && RotationUtil.k(entityLivingBase)) {
            return false;
        }
        if (Vape.INSTANCE.getFriendManager().isFriend(entityLivingBase)) {
            return false;
        }
        if (this.e(entityPlayerSP, entity)) {
            return false;
        }
        return !this.J(entity);
    }

    public static int C() {
        return n;
    }

    static {
        ClientSettings.Z(30);
        i = -1790989181904648544L;
        H = ForgeVersion.MC_1_7_10.Y();
        d = false;
        F = new String(new char[]{'\u00a7'});
        O = new ModeOption("None");
        u = new ModeOption("Slow");
        Y = new ModeOption("Proper");
        l = ConcurrentHashMap.newKeySet();
        k = new Random();
        f = new double[]{0.5, 0.8, 1.0, 1.2, 1.5};
    }

    public static boolean B(KeyBinding keyBinding) {
        int n = ClientSettings.H(keyBinding);
        if (n > 0) {
            return KeyboardInput.isKeyDown(n);
        }
        return MouseInput.isButtonDown(100 + n);
    }

    private double U() {
        int n = Minecraft.h();
        if (n >= 2000) {
            return 1.5;
        }
        if (n >= 1000) {
            return 1.2;
        }
        return 1.0;
    }

    public static int H(KeyBinding keyBinding) {
        int n = keyBinding.getKeyCode();
        if (n > 0) {
            if (ForgeVersion.MC_1_16_5.v()) {
                n = KeyboardCodeUtil.convertLegacyKeyCode(n);
            } else {
                int n2 = GlfwToVirtualKeyCodeMap.toVirtualKey(n);
                if (n2 != 0) {
                    n = n2;
                }
            }
        }
        return n;
    }

    public static int J() {
        int n = ClientSettings.C();
        return 0;
    }

    public MutableColor y(RenderEntityContext renderEntityContext, boolean bl) {
        return this.B(renderEntityContext, bl, false);
    }

    public boolean J(Entity entity) {
        if (ForgeVersion.MC_1_7_10.L()) {
            return false;
        }
        if (this.q != null) {
            return this.q.isBot(entity);
        }
        return false;
    }

    public double s() {
        int n = this.W.getSelectedIndex();
        if (n == 0) {
            return this.U();
        }
        return f[n - 1];
    }

    public static void Z(int n) {
        ClientSettings.n = n;
    }


    public static double U(ItemStack itemStack) {
        double d = ItemStackScoreUtil.a$src$F$2aw1mh(itemStack);
        d += (double)EnchantmentHelper.C(itemStack, EnumCreatureAttribute.R());
        return d += (double)((float)EnchantmentHelper.q(Enchantment.fireAspect().getId(), itemStack) * 0.01f);
    }

    public void k(Mod mod) {
    }

    public static boolean V() {
        int n;
        int n2 = Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362().getKeyCode();
        int n3 = n = ForgeVersion.MC_1_16_5.d() ? n2 : 100 + n2;
        if (n == 0) {
            return KeyBindingInputState.isLeftButtonDown();
        }
        return KeyBindingInputState.isRightButtonDown();
    }

    public MutableColor l(RenderEntityContext renderEntityContext) {
        return this.y(renderEntityContext, false);
    }

    public ClientSettings() {
        this.Z = BindValue.createEmpty(this, "Add friend bind");
        this.S = BooleanValue.create(this, "Show NBT Tags", false, "Shows NBT tags set by the server.\nUseful for servers with custom items.");
        this.N = BooleanValue.create(this, "Lobby Check", false, "Temporarily disables certain features in server lobbies.");
        this.T = BooleanValue.create(this, "Sanity Check", false, "Disables all modules when you connect/disconnect from a server.");
        this.healthPrediction = BooleanValue.create(this, "Health prediction", false, "Estimates player health on pvp servers\nBy default attacks and health pots will be estimated\nNOTE: This feature may not always be accurate!");
        this.estimateFoodHealing = BooleanValue.create(this, "Estimate Food", true, "Automatically estimates food + healing from food.");
        this.estimateFallDamage = BooleanValue.create(this, "Estimate Fall", true, "Automatically estimates damage from falling.");
        this.o = ModeValue.create((Object)this, "Movement", "Corrects your movement to prevent irregular speeds whilst silent aiming, which is normally impossible\nNone - Does not correct your movement\nSlow - Will slow down your movement to prevent irregular speeds\nProper - Will attempt to steer you towards your cursor location with proper movements", (ModeSelection)Y, O, u, Y);
        this.c = BooleanValue.create(this, "3rd person aim view", false, "In 3rd person sets your 3D model angles where you are aiming silently");
        this.e = BooleanValue.create(this, "Aim indicator", false, "Shows a line where you are aiming silently");
        this.C = BooleanValue.create(this, "Use Reach", false, "Uses Reach module to increase reach for Silent Aim modules");
        this.A = BooleanValue.create(this, "Use Hitboxes", false, "Uses Hitboxes module to increase hitboxes for Silent Aim modules");
        ModeOption modeOption = new ModeOption("Auto");
        this.W = ModeValue.create((Object)this, "GUI Scale", "Scale of GUI", (ModeSelection)modeOption, modeOption, new ModeOption("Tiny"), new ModeOption("Small"), new ModeOption("Normal"), new ModeOption("Large"), new ModeOption("Huge"));
        this.w.setColorTransformEnabled(true);
        this.healthPrediction.addDependentValues(this.estimateFoodHealing, this.estimateFallDamage);
        ((BindSet)this.Z.getValue()).addChangeListener(new ClientSettingsBindChangeListener(this));
    }

    public static int f() {
        int n;
        while (l.contains(n = -k.ints(1, (int)i).findFirst().getAsInt()) || Minecraft.theWorld().V(n).isNotNull()) {
        }
        l.add(n);
        return n;
    }

    public MutableColor B(RenderEntityContext renderEntityContext, boolean bl, boolean bl2) {
        if (this.q == null) {
            return null;
        }
        return this.q.resolveEntityTeamColor(renderEntityContext, bl, bl2);
    }

    public boolean S(Entity entity) {
        return this.e(null, entity);
    }

    public boolean J$src$Z$c57s1l() {
        return this.N.getEffectiveValue() != false && !Minecraft.thePlayer().C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().c();
    }
}

