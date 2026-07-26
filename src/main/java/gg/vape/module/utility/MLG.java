package gg.vape.module.utility;

import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPacketReceive;
import gg.vape.event.impl.EventPreTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.MLGBlockWrapper;
import gg.vape.module.utility.MLGImpactState;
import gg.vape.module.utility.inventory.ItemStackActionPredicate;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.BlockUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.Block;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.BlockStateWorldBridge;
import gg.vape.wrapper.impl.Entity;
import gg.vape.wrapper.impl.EntityPlayerMacroBridge;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Material;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Packet;
import gg.vape.wrapper.impl.SPacketEntityVelocity;
import gg.vape.wrapper.impl.Slot;
import gg.vape.wrapper.impl.World;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jetbrains.annotations.Nullable;

public class MLG
extends Mod {
    private final BooleanValue c;
    private final BooleanValue F;
    private final TimerUtil V;
    private static final int v;
    private static final boolean L = false;
    private boolean Z = false;
    private static final double r = 0.05;
    private final Queue<InventoryClick> D;
    private boolean U = false;
    private boolean O = false;
    private final ArrayDeque<KeyBinding> t;
    private final TimerUtil j;
    private double H = 0.0;
    private final TimerUtil Y;
    private static final int a;
    private final RandomValue o = RandomValue.G(this, "Click delay", "#", "ms", 50.0, 75.0, 125.0, 200.0, 5.0, "How long to wait between clicks in the inventory");
    private boolean A = false;
    private static final boolean p = false;
    private static final boolean P = true;
    private static final boolean S = false;
    private final ArrayDeque<KeyBinding> s;
    private final BooleanValue I;
    private final TimerUtil k;

    private boolean E$src$Z$172jn17() {
        if (this.A) {
            this.D.clear();
            this.A = false;
            return ItemStackActionPredicate.f();
        }
        return false;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private boolean U() {
        KeyBinding keyBinding;
        boolean bl = false;
        KeyBinding keyBinding2 = this.t.poll();
        if (keyBinding2 != null && keyBinding2.isNotNull()) {
            KeyBindingHelper.v(keyBinding2, false, false);
            bl = true;
        }
        if ((keyBinding = this.s.poll()) != null && keyBinding.isNotNull()) {
            KeyBindingHelper.v(keyBinding, true, true);
            bl = true;
            this.t.add(keyBinding);
        }
        return bl;
    }

    static {
        a = 1000;
        v = 3000;
    }

    private void r(String string, Object ... objectArray) {
    }

    private boolean P(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        double d = entityPlayerMacroBridge.z();
        double d2 = entityPlayerMacroBridge.N();
        double d3 = entityPlayerMacroBridge.h();
        for (double d4 = d2; d4 >= 0.0 && d4 >= d2 - 3.0; d4 -= 1.0) {
            Block block = worldClient.getBlock(d, d4, d3);
            if (block == null || !block.isNotNull()) continue;
            if (BlockUtil.C(block)) {
                return true;
            }
            if (BlockUtil.p(block)) continue;
            return false;
        }
        return false;
    }

    private void i(String string) {
    }

    private boolean R() {
        this.A = true;
        return ItemStackActionPredicate.V();
    }

    @EventHandler
    public void onTick(EventPreTick eventPreTick) {
        EntityPlayerSP entityPlayerSP = eventPreTick.getThePlayer();
        WorldClient worldClient = eventPreTick.getWorld();
        if (!this.A()) {
            InventoryClick inventoryClick;
            Slot slot = ItemStackActionPredicate.K(MappedClasses.Yi, MLGImpactState.D);
            if (slot != null && slot.isNotNull()) {
                int n;
                if (ItemStackActionPredicate.L()) {
                    if (this.A) {
                        this.E$src$Z$172jn17();
                    }
                    return;
                }
                InventoryPlayer inventoryPlayer = entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
                if (inventoryPlayer.isNull()) {
                    return;
                }
                int n2 = inventoryPlayer.v();
                if (n2 == (n = slot.g() - 36)) {
                    return;
                }
                inventoryPlayer.g(n);
                return;
            }
            if (!this.c.L().booleanValue()) {
                this.Y(false);
                return;
            }
            Slot slot2 = ItemStackActionPredicate.K(MappedClasses.Yi, MLGImpactState.i);
            if (slot2 == null || slot2.isNull()) {
                this.Y(false);
                return;
            }
            int n = slot2.g();
            if (!ItemStackActionPredicate.o()) {
                if (ItemStackActionPredicate.L()) {
                    if (this.A) {
                        this.E$src$Z$172jn17();
                    }
                    return;
                }
                this.R();
                return;
            }
            GuiContainer guiContainer = new GuiContainer(Minecraft.currentScreen().getObject());
            int n3 = guiContainer.getInventorySlots().getWindowId();
            if (this.D.isEmpty()) {
                this.D.add(new InventoryClick(n3, n, 0, 2));
                return;
            }
            if (this.Y.hasTimeElapsed((long)this.o.B()) && (inventoryClick = this.D.poll()) != null) {
                this.H(inventoryClick, n3);
            }
            return;
        }
        if (ItemStackActionPredicate.L()) {
            if (this.A) {
                this.E$src$Z$172jn17();
            }
            return;
        }
        if (this.U()) {
            return;
        }
        EntityPlayerMacroBridge entityPlayerMacroBridge = this.o$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1opz9om();
        if (entityPlayerMacroBridge == null || entityPlayerMacroBridge.isNull()) {
            this.N();
            return;
        }
        Entity entity = entityPlayerMacroBridge.r$src$Lgg_vape_wrapper_impl_Entity_$18p7x3h();
        if (entity != null && entity.isNotNull()) {
            if (this.I.L().booleanValue()) {
                this.N();
            } else {
                this.Y(false);
            }
            return;
        }
        if (!this.E(entityPlayerMacroBridge, worldClient)) {
            if (this.F.L().booleanValue() && this.k.hasTimeElapsed(3000L)) {
                this.N();
            }
            return;
        }
        this.k.reset();
        double d = entityPlayerMacroBridge.q();
        if (!this.Z) {
            double d2 = entityPlayerMacroBridge.t();
            double d3 = entityPlayerMacroBridge.T();
            double d4 = Math.abs(d2) + Math.abs(d) + Math.abs(d3);
            if (d4 <= 0.05) {
                if (this.V.hasTimeElapsed(1000L)) {
                    this.Z = true;
                }
            } else {
                this.V.reset();
            }
            return;
        }
        this.H = d <= -0.1 ? (this.H += d) : 0.0;
        if (this.H <= -0.05 || this.U) {
            if (this.H <= -0.05) {
                // empty if block
            }
            this.N();
            this.j.reset();
            this.V.reset();
            this.k.reset();
            this.Z = false;
            this.U = false;
        } else if (Math.abs(d) > 0.001) {
            // empty if block
        }
    }

    @Nullable
    private EntityPlayerMacroBridge o$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1opz9om() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        EntityPlayerMacroBridge entityPlayerMacroBridge = entityPlayerSP.K$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1agjn9();
        if (entityPlayerMacroBridge.isNotNull()) {
            return entityPlayerMacroBridge;
        }
        World world = entityPlayerSP.getWorld();
        if (world.isNull()) {
            return null;
        }
        ArrayList<EntityPlayerMacroBridge> arrayList = new ArrayList<EntityPlayerMacroBridge>();
        for (Object e : world.z()) {
            EntityPlayerMacroBridge entityPlayerMacroBridge2;
            Entity entity = new Entity(e);
            if (!entity.isInstance(MappedClasses.lM) || !entityPlayerSP.equals((entityPlayerMacroBridge2 = new EntityPlayerMacroBridge(entity.getObject())).A$src$Lgg_vape_wrapper_impl_Entity_$12ijiu4())) continue;
            arrayList.add(entityPlayerMacroBridge2);
        }
        switch (arrayList.size()) {
            case 0: {
                return null;
            }
            case 1: {
                return (EntityPlayerMacroBridge)arrayList.get(0);
            }
        }
        return null;
    }

    private boolean W(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        BlockPos blockPos = entityPlayerMacroBridge.J$src$Lgg_vape_wrapper_impl_BlockPos_$kv8a0x();
        BlockStateWorldBridge blockStateWorldBridge = worldClient.o(blockPos);
        float f = 0.0f;
        if (blockStateWorldBridge.o(MLGBlockWrapper.t())) {
            f = blockStateWorldBridge.i(worldClient, blockPos);
        }
        return f > 0.0f;
    }

    private boolean O(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        int n = 5;
        double d = 0.0;
        for (int i = 0; i < n; ++i) {
            AxisAlignedBB axisAlignedBB = entityPlayerMacroBridge.u$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$kogbsu();
            double d2 = axisAlignedBB.getMaxY() - axisAlignedBB.getMinY();
            double d3 = axisAlignedBB.getMinY() + d2 * (double)i / (double)n;
            double d4 = axisAlignedBB.getMinY() + d2 * (double)(i + 1) / (double)n;
            AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.create(axisAlignedBB.getMinX(), d3, axisAlignedBB.getMinZ(), axisAlignedBB.getMaxX(), d4, axisAlignedBB.getMaxZ());
            if (!entityPlayerMacroBridge.getWorld().h(axisAlignedBB2, Material.f())) continue;
            d += 1.0 / (double)n;
        }
        return d > 0.0;
    }

    private void N() {
        boolean bl;
        EntityPlayerMacroBridge entityPlayerMacroBridge = this.o$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1opz9om();
        boolean bl2 = bl = entityPlayerMacroBridge != null && entityPlayerMacroBridge.isNotNull();
        if (bl || !this.O || this.j.hasTimeElapsed(1000L)) {
            this.g(Minecraft.gameSettings().b$src$Lgg_vape_wrapper_impl_KeyBinding_$1yi3362());
            this.j.reset();
            this.k.reset();
            this.V.reset();
            this.O = true;
            this.H = 0.0;
        }
    }

    private void g(KeyBinding keyBinding) {
        KeyBindingHelper.v(keyBinding, true, true);
        this.t.add(keyBinding);
    }

    public MLG() {
        super("AutoFish", 12452021, Category.m, "Automatically fishes for you.");
        this.D = new ConcurrentLinkedQueue<InventoryClick>();
        this.s = new ArrayDeque();
        this.t = new ArrayDeque();
        this.I = BooleanValue.create(this, "Recast caught", false, "Automatically recasts if the hook catches onto an entity");
        this.F = BooleanValue.create(this, "Recast ground", false, "Automatically recasts if the hook hits the ground");
        this.Y = new TimerUtil();
        this.j = new TimerUtil();
        this.V = new TimerUtil();
        this.k = new TimerUtil();
        this.c = BooleanValue.create(this, "Refill rods", true, "Automatically replaces broken rods with rods from your inventory.");
        this.c.K(this.o);
        this.addValue(this.F, this.I, this.c, this.o);
    }

    private boolean E(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        if (entityPlayerMacroBridge.h$src$Z$ftwoya()) {
            return true;
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.W(entityPlayerMacroBridge, worldClient);
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            return this.X(entityPlayerMacroBridge, worldClient);
        }
        return this.O(entityPlayerMacroBridge, worldClient) || this.P(entityPlayerMacroBridge, worldClient);
    }

    private void H(InventoryClick inventoryClick, int n) {
        this.Y.reset();
        int n2 = inventoryClick.t();
        if (n == n2) {
            inventoryClick.k();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.p();
    }

    private void p() {
        this.Y.reset();
        this.j.reset();
        this.V.reset();
        this.k.reset();
        this.D.clear();
        this.s.clear();
        this.t.clear();
        this.O = false;
        this.Z = false;
        this.U = false;
        this.A = false;
        this.H = 0.0;
    }

    @EventHandler
    public void onPacketReceive(EventPacketReceive eventPacketReceive) {
        if (!this.Z) {
            return;
        }
        EntityPlayerMacroBridge entityPlayerMacroBridge = this.o$src$Lgg_vape_wrapper_impl_EntityPlayerMacroBridge_$1opz9om();
        if (entityPlayerMacroBridge == null || entityPlayerMacroBridge.isNull()) {
            return;
        }
        Packet packet = eventPacketReceive.getPacket();
        WorldClient worldClient = eventPacketReceive.getWorld();
        EntityPlayerSP entityPlayerSP = eventPacketReceive.getThePlayer();
        if (packet.isInstance(MappedClasses.YX)) {
            SPacketEntityVelocity sPacketEntityVelocity = new SPacketEntityVelocity(packet);
            if (sPacketEntityVelocity.getEntityId() != entityPlayerMacroBridge.S()) {
                return;
            }
            int n = sPacketEntityVelocity.getMotionX();
            int n2 = sPacketEntityVelocity.getMotionZ();
            double d = (double)sPacketEntityVelocity.getMotionY() / 8000.0;
            if (n == 0 && n2 == 0 && d <= -0.05) {
                this.U = true;
            }
            return;
        }
        if (packet.isInstance(MappedClasses.qz) && !packet.isInstance(MappedClasses.uJ)) {
            return;
        }
        if (packet.isInstance(MappedClasses.Dk)) {
            return;
        }
    }

    private boolean X(EntityPlayerMacroBridge entityPlayerMacroBridge, WorldClient worldClient) {
        return this.P(entityPlayerMacroBridge, worldClient);
    }

    private boolean A() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return false;
        }
        ItemStack itemStack = entityPlayerSP.getHeldItemHand();
        return !itemStack.isNull() && itemStack.getItem().isInstance(MappedClasses.Yi);
    }
}

