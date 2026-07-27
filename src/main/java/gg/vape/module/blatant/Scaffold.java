package gg.vape.module.blatant;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;
import gg.vape.module.blatant.scaffold.BlatantScaffoldMode;
import gg.vape.module.blatant.scaffold.LegitScaffoldMode;
import gg.vape.module.blatant.scaffold.ScaffoldPointRotationController;
import gg.vape.module.blatant.scaffold.TellyBridgeScaffoldMode;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.FreeLookHudModule;
import gg.vape.movement.MovementInputHelper;
import gg.vape.movement.PlayerMovementTaskManager;
import gg.vape.rotation.FixedRotationController;
import gg.vape.rotation.PointRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.ui.click.frame.impl.hud.ActiveModuleStackFrame;
import gg.vape.unmap.ItemLimitData;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MathUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.ValueDisplayDescriptor;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.Blocks;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.EnumFacing;
import gg.vape.wrapper.impl.EnumHand;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scaffold
extends Mod {
    private static final long MODULE_ID = 5194648552353087966L;
    private final RotationControlClaim rotationClaim;
    private BlatantScaffoldMode godBridgeMode;
    private TellyBridgeScaffoldMode tellyBridgeMode;
    private final NumberValue pitchValue;
    protected final BooleanValue a;
    protected final ModeValue s;
    private final LimitValue whitelistLimit;
    private final List<ItemLimitData> blacklistItems;
    private final LimitValue blacklistLimit;
    private LegitScaffoldMode legitMode = new LegitScaffoldMode(this, "Legit");
    private final BooleanValue whitelistToggle;
    private final BooleanValue blacklistToggle;
    private final BooleanValue pitchCheckToggle;
    public FixedRotationController A = null;

    protected double[] e(double[] dArray, double d, int n) {
        double d2 = dArray[0];
        double d3 = dArray[1];
        double d4 = dArray[2];
        double d5 = 0.45 + Math.random() * 0.2;
        if (n == 6) {
            d2 = new BigDecimal(String.valueOf(d2 + d5)).doubleValue();
            d4 = new BigDecimal(String.valueOf(d4 + 0.5 - d)).doubleValue();
        } else if (n == 8) {
            d2 = new BigDecimal(String.valueOf(d2 + 1.0 - d5)).doubleValue();
            d4 = new BigDecimal(String.valueOf(d4 + 0.5 + d)).doubleValue();
        } else if (n == 7) {
            d2 = new BigDecimal(String.valueOf(d2 + 0.5 + d)).doubleValue();
            d4 = new BigDecimal(String.valueOf(d4 + d5)).doubleValue();
        } else if (n == 5) {
            d2 = new BigDecimal(String.valueOf(d2 + 0.5 - d)).doubleValue();
            d4 = new BigDecimal(String.valueOf(d4 + 1.0 - d5)).doubleValue();
        }
        d3 = new BigDecimal(String.valueOf(d3 + 1.0)).doubleValue();
        return new double[]{d2, d3, d4};
    }

    protected boolean p(ItemStack itemStack) {
        if (!this.blacklistToggle.L().booleanValue()) {
            return false;
        }
        return !this.blacklistLimit.k(itemStack);
    }

    protected boolean U(double[] dArray) {
        return this.i(dArray[0], dArray[1], dArray[2]);
    }

    protected void V$src$V$dhg0vg() {
    }

    protected void J$src$V$dauhr4() {
        this.W();
        this.A();
        MovementInputHelper.r();
        MovementInputHelper.q();
    }

    protected double P(double d, double d2) {
        double d3;
        double d4 = Math.abs(d - d2);
        return d4 <= (d3 = Math.abs(360.0 - d + d2)) ? d4 : d3;
    }

    @Override
    public String E() {
        return this.s.c();
    }

    protected int M(ArrayList<Integer> arrayList) {
        if (FreeLookHudModule.z()) {
            return 0;
        }
        int n = 0;
        for (Integer n2 : arrayList) {
            int n3 = n2;
            n += n3;
        }
        return n;
    }

    protected PointRotationController o(double[] dArray, float f) {
        return null;
    }

    protected boolean w$src$Z$dzl8jt() {
        return this.a.L();
    }

    protected int s(EntityPlayerSP entityPlayerSP, ItemStack itemStack) {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack2 = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
            if (!itemStack2.isNotNull() || !itemStack2.getItem().equals(itemStack.getItem())) continue;
            return i;
        }
        return -1;
    }

    protected ArrayList<Integer> J(ArrayList<Integer> arrayList) {
        arrayList.add(0, Math.abs(Minecraft.s().d()));
        arrayList.add(0, Math.abs(Minecraft.s().z()));
        if (arrayList.size() > 6) {
            for (int i = 6; i < arrayList.size(); ++i) {
                arrayList.remove(i);
            }
        }
        return arrayList;
    }

    protected void S(float[] fArray, float f) {
        if (this.A instanceof PointRotationController) {
            this.W();
        }
        if (this.A == null) {
            this.A = new FixedRotationController(fArray[0], fArray[1]);
            this.A.Y(Math.min(Math.max(2.0f, f), 12.0f));
            this.A.k(true);
            this.A.t(0.5f);
            this.A.s(true);
            this.A.w(true);
            RotationManager.b.S(this.A);
        } else {
            this.A.Y(Math.min(Math.max(2.0f, f), 12.0f));
            this.A.g(fArray[0], fArray[1]);
        }
    }

    protected int G(boolean bl) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return 0;
        }
        if (!ForgeVersion.MC_1_21_4.d()) {
            return 0;
        }
        boolean bl2 = entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode();
        ItemStack itemStack = entityPlayerSP.i(EnumHand.p());
        if (this.y(itemStack)) {
            return !bl && bl2 ? 64 : itemStack.t();
        }
        return 0;
    }

    protected double[] X(double[] dArray, int n, int n2) {
        double d = dArray[0];
        double d2 = dArray[1];
        double d3 = dArray[2];
        if (n2 == 1) {
            d += (double)n;
            d3 += (double)n;
        } else if (n2 == 2) {
            d -= (double)n;
            d3 += (double)n;
        } else if (n2 == 3) {
            d -= (double)n;
            d3 -= (double)n;
        } else if (n2 == 4) {
            d += (double)n;
            d3 -= (double)n;
        } else if (n2 == 6) {
            d += (double)n;
        } else if (n2 == 8) {
            d -= (double)n;
        } else if (n2 == 7) {
            d3 += (double)n;
        } else if (n2 == 5) {
            d3 -= (double)n;
        }
        return new double[]{d, d2, d3};
    }

    protected int J(int n) {
        return n % 2 == 0 ? 0 : 2;
    }

    protected int K(int n) {
        return n % 2 == 0 ? 2 : 0;
    }

    protected boolean X(double[] dArray, double[] dArray2, int n, Double d, int n2) {
        if (n > 4 && dArray[this.K(n)] != dArray2[this.K(n)]) {
            return true;
        }
        if (n < 5 && Math.abs(dArray[0] - dArray2[0]) >= 4.0 || Math.abs(dArray[2] - dArray2[2]) >= 4.0) {
            return true;
        }
        if (dArray[1] != dArray2[1]) {
            return true;
        }
        double[] dArray3 = this.X(dArray, -n2, n);
        return RotationUtil.b(dArray3, dArray2) > d + 2.0;
    }

    protected boolean i(double d, double d2, double d3) {
        if (ForgeVersion.MC_1_7_10.L()) {
            d2 -= 1.0;
        }
        Block block = Minecraft.theWorld().getBlock(d, d2, d3);
        return block.equals(Blocks.j());
    }

    protected boolean y(ItemStack itemStack) {
        return this.a(itemStack);
    }

    protected double[] S(double[] dArray, double d, double d2, int n) {
        double d3 = dArray[0];
        double d4 = new BigDecimal(String.valueOf(dArray[1] + 0.5 + d2)).doubleValue();
        double d5 = dArray[2];
        if (n == 6) {
            d3 = new BigDecimal(String.valueOf(d3 + 1.0)).doubleValue();
            d5 = new BigDecimal(String.valueOf(d5 + 0.5 - d)).doubleValue();
        } else if (n == 8) {
            d3 = new BigDecimal(String.valueOf(d3)).doubleValue();
            d5 = new BigDecimal(String.valueOf(d5 + 0.5 + d)).doubleValue();
        } else if (n == 7) {
            d3 = new BigDecimal(String.valueOf(d3 + 0.5 + d)).doubleValue();
            d5 = new BigDecimal(String.valueOf(d5 + 1.0)).doubleValue();
        } else if (n == 5) {
            d3 = new BigDecimal(String.valueOf(d3 + 0.5 - d)).doubleValue();
            d5 = new BigDecimal(String.valueOf(d5)).doubleValue();
        }
        return new double[]{d3, d4, d5};
    }

    protected int v() {
        int n = 0;
        double d = (RotationUtil.c() + 180.0f) % 360.0f;
        double d2 = Math.abs(d - 0.0);
        double d3 = Math.abs(d - 45.0);
        double d4 = Math.abs(d - 90.0);
        double d5 = Math.abs(d - 135.0);
        double d6 = Math.abs(d - 180.0);
        double d7 = Math.abs(d - 225.0);
        double d8 = Math.abs(d - 270.0);
        double d9 = Math.abs(d - 315.0);
        double d10 = Math.abs(d - 360.0);
        if (d9 < d8 && d9 < d10 && d9 < d2) {
            n = 1;
        } else if (d3 < d2 && d3 < d10 && d3 < d4) {
            n = 2;
        } else if (d5 < d4 && d5 < d6) {
            n = 3;
        } else if (d7 < d6 && d7 < d8) {
            n = 4;
        } else if (d8 < d7 && d8 < d9) {
            n = 6;
        } else if (d4 < d3 && d4 < d5) {
            n = 8;
        } else if (d2 < d3 && d2 < d9 || d10 < d3 && d10 < d9) {
            n = 7;
        } else if (d6 < d5 && d6 < 225.0) {
            n = 5;
        }
        return n;
    }

    protected void t(double[] dArray, float f, int n, double[] dArray2) {
        boolean bl = GuiScreen.p$src$Z$8062rc();
        if (!(this.A instanceof PointRotationController)) {
            this.W();
        }
        if (this.A == null) {
            this.A = new ScaffoldPointRotationController(this, dArray[0], dArray[1], dArray[2], n, dArray2);
            this.A.Y(Math.min(Math.max(2.0f, f), 12.0f));
            this.A.k(true);
            this.A.t(0.0f);
            this.A.w(true);
            this.A.s(true);
            RotationManager.b.S(this.A);
        } else {
            this.A.Y(Math.min(Math.max(2.0f, f), 12.0f));
            ((PointRotationController)this.A).z(dArray[0], dArray[1], dArray[2]);
        }
    }

    public Scaffold() {
        super("Scaffold", (int)MODULE_ID, Category.Y, "Helps you make bridges/scaffold walk.");
        this.godBridgeMode = new BlatantScaffoldMode(this, "GodBridge");
        this.tellyBridgeMode = new TellyBridgeScaffoldMode(this, "TellyBridge");
        this.a = BooleanValue.create(this, "Block count", false, "Renders your block count on the center of your screen");
        this.pitchCheckToggle = BooleanValue.create(this, "Pitch check", false, "Scaffold will not activate unless you are aiming lower than this angle");
        this.pitchValue = NumberValue.create(this, "Pitch", "#", " ", 0.0, 45.0, 90.0);
        this.blacklistToggle = BooleanValue.create(this, "Blacklist", true, "Scaffold will not use these blocks for scaffolding");
        this.blacklistItems = Arrays.asList(new ItemLimitData("Dispenser"), new ItemLimitData("Note Block"), new ItemLimitData("Cobweb"), new ItemLimitData("TNT"), new ItemLimitData("Monster Spawner"), new ItemLimitData("Enchantment Table"), new ItemLimitData("Oak Fence"), new ItemLimitData("Jukebox"), new ItemLimitData("Melon"), new ItemLimitData("Command Block"), new ItemLimitData("Anvil"), new ItemLimitData("Glass Pane"), new ItemLimitData("White Stained Glass Pane"), new ItemLimitData("Iron Bars"), new ItemLimitData("Ice"), new ItemLimitData("Packed Ice"), new ItemLimitData("Anvil"), new ItemLimitData("Block of Redstone"), new ItemLimitData("Gold Ore"), new ItemLimitData("Iron Ore"), new ItemLimitData("Coal Ore"), new ItemLimitData("Lapis Lazuli Ore"), new ItemLimitData("Redstone Ore"), new ItemLimitData("Acacia Wood Stairs"), new ItemLimitData("Wooden Pressure Plate"), new ItemLimitData("Stone Pressure Plate"), new ItemLimitData("Beacon"), new ItemLimitData("Oak Sapling"), new ItemLimitData("Powered Rail"), new ItemLimitData("Detector Rail"), new ItemLimitData("Shrub"), new ItemLimitData("Dead Bush"), new ItemLimitData("Dandelion"), new ItemLimitData("Poppy"), new ItemLimitData("Mushroom"), new ItemLimitData("Ladder"), new ItemLimitData("Rail"), new ItemLimitData("Wooden Trapdoor"), new ItemLimitData("Lily Pad"), new ItemLimitData("Tripwire Hook"), new ItemLimitData("Carpet"), new ItemLimitData("Snow"), new ItemLimitData("Trapped Chest"), new ItemLimitData("Daylight Sensor"), new ItemLimitData("Hopper"), new ItemLimitData("Chest"), new ItemLimitData("Torch"), new ItemLimitData("Lever"), new ItemLimitData("Redstone Torch"), new ItemLimitData("Button"), new ItemLimitData("Cactus"));
        this.blacklistLimit = LimitValue.n(this, "scaffold-blacklist", "Block Blacklist", LimitValue.G, this.blacklistItems);
        this.whitelistToggle = BooleanValue.create(this, "Whitelist", false, "Only activates scaffold when\nwhitelisted blocks are held.");
        this.whitelistLimit = (LimitValue)LimitValue.N(this, "scaffold-allowedblocks", "Block Whitelist", LimitValue.r, new ItemLimitData("blocks")).Z$src$Lgg_vape_value_Value_$16i62fx("Scaffold will not function unless you are currently holding an item whitelisted here");
        this.rotationClaim = SharedModuleControlClaims.I;
        this.s = ForgeVersion.MC_1_16_5.d() || ForgeVersion.MC_1_7_10.L() ? ModeValue.create((Object)this, "Mode", "Legit - Automatically shifts at edge of block when backwards (fastbridge/ninja/eagle)\nGodBridge - Places while walking at full speed diagonally without needing to shift\n", (ModeSelection)this.legitMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.legitMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.godBridgeMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx()) : ModeValue.create((Object)this, "Mode", "Legit - Automatically shifts at edge of block when backwards (fastbridge/ninja/eagle)\nGodBridge - Places while walking at full speed diagonally without needing to shift\nTellyBridge - Places blocks behind you while jumping (tellybridging may be prevented on some servers, even if done legitimately)\n", (ModeSelection)this.legitMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.legitMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.godBridgeMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx(), this.tellyBridgeMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx());
        this.pitchCheckToggle.K(this.pitchValue);
        this.blacklistToggle.K(this.blacklistLimit);
        this.whitelistToggle.K(this.whitelistLimit);
        this.addValue(this.s, this.a, this.pitchCheckToggle, this.pitchValue, this.blacklistToggle, this.blacklistLimit, this.whitelistToggle, this.whitelistLimit);
        this.i(ValueDisplayDescriptor.o(this.a, "BC"), ValueDisplayDescriptor.X(this.s), ValueDisplayDescriptor.p(this.blacklistToggle), ValueDisplayDescriptor.p(this.pitchValue), ValueDisplayDescriptor.p(this.whitelistToggle));
        this.rotationClaim.l(this, 6);
    }

    protected float L(float[] fArray) {
        return (float)Math.min(2.0 + this.P(RotationUtil.c(), fArray[0]) / 8.0, 12.0);
    }

    protected int u$src$I$dyhmyg() {
        double d = (RotationUtil.c() + 180.0f) % 360.0f;
        if (d > 315.0 || d <= 45.0) {
            return 7;
        }
        if (d > 45.0 && d <= 135.0) {
            return 8;
        }
        if (d > 135.0 && d <= 225.0) {
            return 5;
        }
        if (d > 225.0 && d <= 315.0) {
            return 6;
        }
        return 0;
    }

    protected float W(int n) {
        double d = RotationUtil.c();
        if (n == 6) {
            d = 90.0;
        } else if (n == 8) {
            d = 270.0;
        } else if (n == 7) {
            d = 0.0;
        } else if (n == 5) {
            d = 180.0;
        }
        return (float)Math.min(2.0 + this.P(RotationUtil.c(), d) / 8.0, 12.0);
    }

    public static EnumFacing B(Scaffold scaffold, int n) {
        return scaffold.facingForDirection(n);
    }

    protected int l() {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().c(i);
            if (!itemStack.isNotNull() || !this.a(itemStack)) continue;
            return i;
        }
        return -1;
    }

    protected double J(EntityPlayerSP entityPlayerSP) {
        double d = entityPlayerSP.N();
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(d));
        if (Math.abs(bigDecimal.doubleValue() - (double)bigDecimal.intValue()) == 0.5) {
            return MathUtil.floor(d);
        }
        return MathUtil.floor(d - 1.0);
    }

    protected int n(int n) {
        return this.n(n, false);
    }

    protected int n(int n, boolean bl) {
        int n2 = this.G(bl);
        boolean bl2 = Minecraft.thePlayer().C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode();
        if (bl2) {
            if (n == 0) {
                ItemStack itemStack = Minecraft.thePlayer().B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
                if (this.y(itemStack)) {
                    int n3;
                    int n4;
                    int n5 = n2;
                    if (!bl) {
                        int n6;
                        n4 = n6 = n5;
                        n3 = 64;
                    } else {
                        n4 = n5;
                        n3 = itemStack.t();
                    }
                    n2 = n4 + n3;
                }
            } else {
                int n7;
                int n8 = n7 = n == 1 ? 36 : 9;
                while (n7 < 45) {
                    ItemStack itemStack = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n7).I();
                    if (this.y(itemStack)) {
                        int n9;
                        int n10;
                        int n11 = n2;
                        if (!bl) {
                            int n12;
                            n10 = n12 = n11;
                            n9 = 64;
                        } else {
                            n10 = n11;
                            n9 = itemStack.t();
                        }
                        n2 = n10 + n9;
                    }
                    ++n7;
                }
            }
            return n2;
        }
        if (n == 0) {
            ItemStack itemStack = Minecraft.thePlayer().B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
            if (this.y(itemStack)) {
                int n13;
                int n14 = n2;
                if (bl) {
                    n13 = n14;
                } else {
                    int n15 = n14;
                    n13 = n15;
                }
                n2 = n13 + itemStack.t();
            }
        } else {
            int n16;
            int n17 = n16 = n == 1 ? 36 : 9;
            while (n16 < 45) {
                ItemStack itemStack = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n16).I();
                if (this.y(itemStack)) {
                    int n18;
                    int n19 = n2;
                    if (bl) {
                        n18 = n19;
                    } else {
                        int n20 = n19;
                        n18 = n20;
                    }
                    n2 = n18 + itemStack.t();
                }
                ++n16;
            }
        }
        return n2;
    }

    @Override
    public void onDisable() {
        this.rotationClaim.X(this);
        ClientSettings.g(ActiveModuleStackFrame.class).w(this);
    }

    protected boolean G() {
        return ((ModeSelection)this.s.K()).equals(this.tellyBridgeMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx()) || ((ModeSelection)this.s.K()).equals(this.godBridgeMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx()) || this.whitelistToggle.L() == false || this.whitelistLimit.z(Minecraft.thePlayer().B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt());
    }


    protected void A() {
        if (PlayerMovementTaskManager.G.e() != null) {
            PlayerMovementTaskManager.G.Q(PlayerMovementTaskManager.G.e());
        }
    }

    protected boolean a(ItemStack itemStack) {
        if (itemStack.isNull() || itemStack.getItem().isNull()) {
            return false;
        }
        Item item = itemStack.getItem();
        if (this.p(itemStack) || !this.c(itemStack)) {
            return false;
        }
        return item.isInstance(MappedClasses.Vw);
    }

    protected void s(int n) {
        Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
    }

    @Override
    public void onEnable() {
        ClientSettings.g(ActiveModuleStackFrame.class).c(this);
    }

    protected double V$src$D$dhg0fy() {
        return (Double)this.pitchValue.K();
    }

    public boolean o$src$Z$dv6vsx() {
        return false;
    }

    private EnumFacing facingForDirection(int n) {
        switch (n) {
            case 5: {
                return EnumFacing.T(3);
            }
            case 6: {
                return EnumFacing.T(4);
            }
            case 7: {
                return EnumFacing.T(2);
            }
            case 8: {
                return EnumFacing.T(5);
            }
        }
        return null;
    }

    @Override
    public ModDisplayInfo J() {
        if (!this.w$src$Z$dzl8jt() || Minecraft.thePlayer().isNull()) {
            return null;
        }
        int n = 2;
        if (((ModeSelection)this.s.K()).equals(this.legitMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx())) {
            n = 0;
        } else if (((ModeSelection)this.s.K()).equals(this.godBridgeMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx()) || ((ModeSelection)this.s.K()).equals(this.tellyBridgeMode.r$src$Lgg_vape_value_SubModuleValue_$1rfa4wx())) {
            n = 1;
        }
        int n2 = this.n(n, true);
        Color color = new Color(255, 20, 20);
        if (n2 >= 32) {
            color = new Color(2, 190, 58);
        } else if (n2 >= 16) {
            color = new Color(255, 249, 18);
        }
        return new ModDisplayInfo(String.valueOf(n2), color);
    }

    protected void W() {
        if (this.A != null) {
            this.A.w(false);
            this.A.u(true);
            RotationManager.b.v(this.A);
            this.A = null;
        }
    }

    protected boolean c(ItemStack itemStack) {
        if (!this.whitelistToggle.L().booleanValue()) {
            return true;
        }
        return this.whitelistLimit.z(itemStack);
    }

    protected boolean J$src$Z$dauhuk() {
        return this.pitchCheckToggle.L();
    }

    public static final class Access {
        private Access() {
        }

        public static double[] e(Scaffold scaffold, double[] dArray, double d, int n) {
            return scaffold.e(dArray, d, n);
        }

        public static boolean U(Scaffold scaffold, double[] dArray) {
            return scaffold.U(dArray);
        }

        public static void V$src$V$dhg0vg(Scaffold scaffold) {
            scaffold.V$src$V$dhg0vg();
        }

        public static void J$src$V$dauhr4(Scaffold scaffold) {
            scaffold.J$src$V$dauhr4();
        }

        public static double P(Scaffold scaffold, double d, double d2) {
            return scaffold.P(d, d2);
        }

        public static int M(Scaffold scaffold, ArrayList<Integer> arrayList) {
            return scaffold.M(arrayList);
        }

        public static int s(Scaffold scaffold, EntityPlayerSP entityPlayerSP, ItemStack itemStack) {
            return scaffold.s(entityPlayerSP, itemStack);
        }

        public static void s(Scaffold scaffold, int n) {
            scaffold.s(n);
        }

        public static ArrayList<Integer> J(Scaffold scaffold, ArrayList<Integer> arrayList) {
            return scaffold.J(arrayList);
        }

        public static double J(Scaffold scaffold, EntityPlayerSP entityPlayerSP) {
            return scaffold.J(entityPlayerSP);
        }

        public static void S(Scaffold scaffold, float[] fArray, float f) {
            scaffold.S(fArray, f);
        }

        public static double[] S(Scaffold scaffold, double[] dArray, double d, double d2, int n) {
            return scaffold.S(dArray, d, d2, n);
        }

        public static double[] X(Scaffold scaffold, double[] dArray, int n, int n2) {
            return scaffold.X(dArray, n, n2);
        }

        public static boolean X(Scaffold scaffold, double[] dArray, double[] dArray2, int n, Double d, int n2) {
            return scaffold.X(dArray, dArray2, n, d, n2);
        }

        public static boolean i(Scaffold scaffold, double d, double d2, double d3) {
            return scaffold.i(d, d2, d3);
        }

        public static void t(Scaffold scaffold, double[] dArray, float f, int n, double[] dArray2) {
            scaffold.t(dArray, f, n, dArray2);
        }

        public static float L(Scaffold scaffold, float[] fArray) {
            return scaffold.L(fArray);
        }

        public static int u$src$I$dyhmyg(Scaffold scaffold) {
            return scaffold.u$src$I$dyhmyg();
        }

        public static float W(Scaffold scaffold, int n) {
            return scaffold.W(n);
        }

        public static void W(Scaffold scaffold) {
            scaffold.W();
        }

        public static int l(Scaffold scaffold) {
            return scaffold.l();
        }

        public static int n(Scaffold scaffold, int n) {
            return scaffold.n(n);
        }

        public static boolean G(Scaffold scaffold) {
            return scaffold.G();
        }

        public static void A(Scaffold scaffold) {
            scaffold.A();
        }

        public static boolean a(Scaffold scaffold, ItemStack itemStack) {
            return scaffold.a(itemStack);
        }

        public static double V$src$D$dhg0fy(Scaffold scaffold) {
            return scaffold.V$src$D$dhg0fy();
        }

        public static boolean J$src$Z$dauhuk(Scaffold scaffold) {
            return scaffold.J$src$Z$dauhuk();
        }
    }
}
