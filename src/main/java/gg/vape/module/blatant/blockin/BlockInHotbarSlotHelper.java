package gg.vape.module.blatant.blockin;

import gg.vape.Vape;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.module.blatant.blockin.BlockPlacementUtility;
import gg.vape.module.blatant.blockin.HotbarSlotResolution;
import gg.vape.module.blatant.blockin.HotbarSlotResolutionWithValue;
import gg.vape.module.combat.AttackPacketTimingTracker;
import gg.vape.module.utility.AutoMLG;
import gg.vape.module.utility.MLGImpactState;
import gg.vape.module.utility.inventory.ItemStackActionPredicate;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.datas.BlockCoordinate;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.BlockPos;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockInHotbarSlotHelper {
    private final AutoMLG c;

    @Nullable
    private Slot J(ItemMappingEntry itemMappingEntry) {
        if (!this.c.kF.L().booleanValue()) {
            return null;
        }
        return ItemStackActionPredicate.a(BlockPlacementUtility.F(itemMappingEntry), MLGImpactState.i);
    }

    @NotNull
    public HotbarSlotResolution y(@Nullable BlockCoordinate blockCoordinate, @NotNull TimerUtil timerUtil) {
        AttackPacketTimingTracker attackPacketTimingTracker;
        HotbarSlotResolutionWithValue<BlockPos> hotbarSlotResolutionWithValue;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return HotbarSlotResolution.J("Player is unavailable");
        }
        ItemStack itemStack = entityPlayerSP.B$src$Lgg_vape_wrapper_impl_ItemStack_$impdvt();
        ItemMappingEntry itemMappingEntry = BlockPlacementUtility.U();
        boolean bl = itemMappingEntry.equals(Vape.INSTANCE.getItemStackResolver().j(itemStack));
        Slot slot = BlockPlacementUtility.Z(itemMappingEntry);
        if (!bl) {
            Slot candidateSlot = slot == null || slot.isNull() ? this.J(itemMappingEntry) : slot;
            if (candidateSlot == null || candidateSlot.isNull()) {
                return HotbarSlotResolution.W("No empty buckets available in the inventory");
            }
            HotbarSlotResolutionWithValue<Slot> hotbarSlotResolutionWithValue3 = this.l(candidateSlot);
            if (hotbarSlotResolutionWithValue3.h()) {
                return HotbarSlotResolution.W(String.format("Failed to move item into hand: %s", hotbarSlotResolutionWithValue3.b())).i(hotbarSlotResolutionWithValue3.B());
            }
            if (hotbarSlotResolutionWithValue3.Q()) {
                return HotbarSlotResolution.J(String.format("Waiting to complete moving item into hand (%s)", hotbarSlotResolutionWithValue3.b()));
            }
            slot = hotbarSlotResolutionWithValue3.w();
        }
        if (!this.c.J.hasTimeElapsed(Math.max((attackPacketTimingTracker = AttackPacketTimingTracker.a).Y(), 150L))) {
            return HotbarSlotResolution.J("Waiting for latency timer to exceed ping / 50ms");
        }
        if (slot == null || slot.isNull()) {
            return HotbarSlotResolution.J("Waiting to find a Hotbar Slot w/ Empty Bucket.");
        }
        hotbarSlotResolutionWithValue = this.c.p.D(BlockPlacementUtility.U(), blockCoordinate, timerUtil);
        if (hotbarSlotResolutionWithValue.v()) {
            this.c.J.reset();
        }
        return hotbarSlotResolutionWithValue;
    }

    @Nullable
    private Slot r() {
        Slot slot = this.Z();
        if (slot != null) {
            return slot;
        }
        return this.R();
    }

    @Nullable
    private Slot Z() {
        Slot slot;
        if (this.c.k_.L().booleanValue() && (slot = BlockPlacementUtility.Z(BlockPlacementUtility.Y())) != null) {
            return slot;
        }
        if (this.c.K.L().booleanValue()) {
            return BlockPlacementUtility.Z(BlockPlacementUtility.e());
        }
        return null;
    }

    public BlockInHotbarSlotHelper(AutoMLG autoMLG) {
        this.c = autoMLG;
    }

    @Nullable
    private Slot N() {
        Slot slot = ItemStackActionPredicate.a(BlockInHotbarSlotHelper::lambda$getHotbarReplaceSlot$0, MLGImpactState.D);
        if (slot == null || slot.isNull()) {
            slot = ItemStackActionPredicate.a(this::lambda$getHotbarReplaceSlot$1, MLGImpactState.D);
        }
        return slot;
    }

    private static boolean lambda$getHotbarReplaceSlot$0(Slot slot) {
        return slot.I().isNull();
    }

    private boolean lambda$getHotbarReplaceSlot$1(Slot slot) {
        return slot.I().isNotNull() && this.c.j.k(slot.I());
    }

    @Nullable
    private Slot R() {
        Slot slot;
        if (!this.c.kF.L().booleanValue()) {
            return null;
        }
        if (this.c.k_.L().booleanValue() && (slot = this.J(BlockPlacementUtility.Y())) != null) {
            return slot;
        }
        if (this.c.K.L().booleanValue()) {
            return this.J(BlockPlacementUtility.e());
        }
        return null;
    }

    @NotNull
    private HotbarSlotResolutionWithValue<Slot> l(@NotNull Slot slot) {
        InventoryClick inventoryClick;
        boolean bl;
        HotbarSlotResolutionWithValue hotbarSlotResolutionWithValue = new HotbarSlotResolutionWithValue();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Player is unavailable");
        }
        boolean bl2 = bl = slot.g() >= 36 && slot.g() <= 44;
        if (bl) {
            HotbarSlotResolution hotbarSlotResolution = this.c.f$src$Lgg_vape_module_blatant_blockin_HotbarSlotResolu$1985fcl();
            if (hotbarSlotResolution.h()) {
                return (HotbarSlotResolutionWithValue)((HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.Q(String.format("Failed to close GUI before equipping MLG item. Reason: %s", hotbarSlotResolution.b()))).i(hotbarSlotResolution.B());
            }
            if (hotbarSlotResolution.Q()) {
                return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Waiting for GUI to close before equipping MLG item");
            }
            int n = slot.g() - 36;
            entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().g(n);
            return ((HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.m("Equipped MLG Item")).q(slot);
        }
        if (!this.c.kF.L().booleanValue()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.Q("MLG Item is in inventory, but inventory use is disabled.");
        }
        Slot slot2 = this.N();
        if (slot2 == null || slot2.isNull()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.Q("No slot to replace found on Hotbar");
        }
        HotbarSlotResolution hotbarSlotResolution = this.c.M$src$Lgg_vape_module_blatant_blockin_HotbarSlotResolu$1womqjg();
        if (hotbarSlotResolution.h()) {
            return (HotbarSlotResolutionWithValue)((HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.Q(String.format("Failed to open Inventory GUI before equipping MLG item due to: %s", hotbarSlotResolution.b()))).i(hotbarSlotResolution.B());
        }
        if (hotbarSlotResolution.Q()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f(String.format("Waiting to open Inventory GUI (state: %s)", hotbarSlotResolution.b()));
        }
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (guiScreen.isNull()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Current screen is null, cannot enqueue click.");
        }
        Container container = new GuiContainer(guiScreen.getObject()).getInventorySlots();
        if (container.isNull()) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Inventory slots are null, cannot enqueue click.");
        }
        int n = container.getWindowId();
        if (this.c.Z.isEmpty()) {
            inventoryClick = InventoryClick.P().g(n).j(slot.g()).D(slot2.g() - 36).V();
            this.c.Z.add(inventoryClick);
        }
        if (!this.c.Z.isEmpty() && this.c.g$src$Z$1ths2vi() && (inventoryClick = this.c.Z.poll()) != null) {
            this.D(inventoryClick, n);
        }
        return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.f("Waiting to execute queued click");
    }

    @NotNull
    public HotbarSlotResolutionWithValue<Slot> p() {
        HotbarSlotResolutionWithValue hotbarSlotResolutionWithValue = new HotbarSlotResolutionWithValue();
        Slot slot = this.r();
        if (slot == null) {
            return (HotbarSlotResolutionWithValue)hotbarSlotResolutionWithValue.Q("No MLG Item in Inventory");
        }
        return this.l(slot);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private void D(@NotNull InventoryClick inventoryClick, int n) {
        this.c.kN.reset();
        int n2 = inventoryClick.t();
        if (n == n2) {
            inventoryClick.k();
        }
    }
}
