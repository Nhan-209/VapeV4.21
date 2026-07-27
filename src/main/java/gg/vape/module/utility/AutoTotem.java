package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.EventPriority;
import gg.vape.event.impl.EventClickMouse;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.event.impl.EventRightClickMouse;
import gg.vape.event.impl.EventSendClickBlockToController;
import gg.vape.event.impl.EventThreadBoundPostTick;
import gg.vape.event.impl.EventThreadBoundPreTick;
import gg.vape.event.impl.EventWindowClick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.ModDisplayInfo;
import gg.vape.module.control.SharedModuleControlClaims;
import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.Freecam;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.rotation.AdaptiveRotationController;
import gg.vape.rotation.RotationControlClaim;
import gg.vape.rotation.RotationManager;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.frame.impl.hud.ActiveModuleStackFrame;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GameSettings;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AutoTotem
extends Mod
implements InventoryActionModule {
    private final Random random;
    private final TimerUtil actionTimer;
    private final TimerUtil clickTimer = new TimerUtil();
    private Object lastScreen;
    private final RotationControlClaim rotationClaim;
    private static Freecam freecam;
    private boolean clickingSlot;
    private long delayDuration = -1L;
    private final RandomValue silentMoveDelay;
    private final RandomValue delay;
    private boolean closePending;
    private boolean inventoryOpen;
    private AdaptiveRotationController rotationController;
    private final BooleanValue extraRandomization;
    private long clickDelay = -1L;
    private final BooleanValue showTotemCount;
    private final Queue<InventoryClick> clickQueue;
    private final TimerUtil delayTimer = new TimerUtil();
    private final BooleanValue closeInventory;
    private final BooleanValue inventoryOnly;
    private boolean suppressInput;
    private static final int UNUSED_CONST_A;
    private final BooleanValue silentOpen;
    private boolean silentActive;
    private final RotationManager rotationManager;
    private final BooleanValue openInventory;
    private static final int INVENTORY_SLOT_COUNT;
    private final BooleanValue randomSlot;

    private double computeDelay() {
        int[] nArray = ModeSelection.q();
        if (!this.extraRandomization.L().booleanValue()) {
            return this.delay.B();
        }
        double min = this.delay.q$src$D$vgz097();
        double max = this.delay.M();
        double range = Math.max(1.0, max - min);
        double center = min + range * 0.5;
        double stdDev = Math.max(1.0, range / 4.0);
        double result = center + this.random.nextGaussian() * stdDev;
        result = Math.max(min, Math.min(max, result));
        if (this.random.nextDouble() < 0.18) {
            double extraMin = Math.max(120.0, range * 1.25);
            double extraMax = Math.max(350.0, range * 3.25);
            result += extraMin + (extraMax - extraMin) * this.random.nextDouble();
        }
        return result;
    }

    private int X(EntityPlayerSP entityPlayerSP) {
        int count = 0;
        for (int i = 9; i <= 45; ++i) {
            ItemMappingEntry itemMappingEntry;
            ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I();
            if (itemStack.isNull() || (itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack)) == null || !itemMappingEntry.M().toLowerCase().contains("totem_of_undying")) continue;
            count += itemStack.t();
        }
        return count;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void onSendClickBlock(EventSendClickBlockToController eventSendClickBlockToController) {
        if (this.suppressInput) {
            eventSendClickBlockToController.setCancelled(true);
        }
    }

    private void closeInventory() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (this.inventoryOpen) {
            if (this.silentActive) {
                this.silentActive = false;
                if (this.rotationController != null) {
                    this.rotationController.Y(3.0f);
                    this.rotationController.D(true);
                    this.rotationController.s(true);
                    this.rotationManager.v(this.rotationController);
                    this.rotationController = null;
                }
                this.rotationClaim.X(this);
                entityPlayerSP.Z$src$V$1ie832h();
            } else if (this.closeInventory.L().booleanValue() && guiScreen.isNotNull()) {
                entityPlayerSP.Z$src$V$1ie832h();
            }
        }
        this.inventoryOpen = false;
        this.closePending = false;
    }

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && !this.clickQueue.isEmpty();
    }

    private boolean isDelayElapsed() {
        if (this.delayDuration <= 0L) {
            AutoTotem autoTotem = this;
            this.delayDuration = Math.max(1L, (long)autoTotem.computeDelay());
        }
        return this.delayTimer.hasTimeElapsed(this.delayDuration);
    }

    private void queueClick(int n, int n2, int n3, int n4) {
        this.clickQueue.add(new InventoryClick(n, n2, n3, n4));
    }

    private void M$src$V$g0nukx() {
        if (this.silentOpen.L().booleanValue()) {
            if (this.M$src$Z$g0nuod()) {
                return;
            }
            this.silentActive = true;
            this.suppressInput = true;
            this.actionTimer.reset();
            this.rotationController = new AdaptiveRotationController();
            this.rotationController.Y(0.0f);
            this.rotationManager.S(this.rotationController);
        } else {
            KeyBinding keyBinding = Minecraft.gameSettings().j();
            if (ForgeVersion.MC_1_16_5.d()) {
                KeyBindingHelper.a(keyBinding);
            } else {
                KeyBindingHelper.d(keyBinding, true);
                KeyBindingHelper.v(keyBinding, false, false);
            }
        }
        this.inventoryOpen = true;
        this.resetDelayTimer();
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void onClickMouse(EventClickMouse eventClickMouse) {
        if (this.suppressInput) {
            eventClickMouse.setCancelled(true);
        }
    }

    private void b$src$V$gc7j1i() {
        Object object = Minecraft.currentScreen().getObject();
        if (object != this.lastScreen) {
            this.resetDelayTimer();
        }
        this.lastScreen = object;
    }

    private void releaseMovementKeys(GameSettings gameSettings) {
        if (this.suppressInput) {
            KeyBindingHelper.v(gameSettings.Y(), gg.vape.config.ClientSettings.B(gameSettings.Y()), true);
            KeyBindingHelper.v(gameSettings.s(), gg.vape.config.ClientSettings.B(gameSettings.s()), true);
            KeyBindingHelper.v(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), gg.vape.config.ClientSettings.B(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg()), true);
            KeyBindingHelper.v(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), gg.vape.config.ClientSettings.B(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3()), true);
            KeyBindingHelper.v(gameSettings.O(), gg.vape.config.ClientSettings.B(gameSettings.O()), true);
            KeyBindingHelper.v(gameSettings.r(), gg.vape.config.ClientSettings.B(gameSettings.r()), true);
        }
    }

    @Override
    public ModDisplayInfo J() {
        if (!this.showTotemCount.L().booleanValue()) {
            return null;
        }
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        int totemCount = this.X(entityPlayerSP);
        Color color = new Color(255, 20, 20);
        if (totemCount >= 4) {
            color = new Color(2, 190, 58);
        } else if (totemCount >= 2) {
            color = new Color(255, 249, 18);
        }
        return new ModDisplayInfo(String.valueOf(totemCount), color);
    }

    @Override
    public void onDisable() {
        ClientSettings.g(ActiveModuleStackFrame.class).w(this);
        if (this.inventoryOpen && this.silentActive) {
            this.closeInventory();
        }
        this.closePending = false;
        this.inventoryOpen = false;
        this.delayDuration = -1L;
        this.clickDelay = -1L;
    }

    @EventHandler
    public void onWindowClick(EventWindowClick eventWindowClick) {
        boolean shouldCancel;
        GuiScreen guiScreen = eventWindowClick.getCurrentScreen();
        boolean inTotemScreen = shouldCancel = this.inventoryOpen && this.silentActive || guiScreen.isNotNull() && guiScreen.isInstance(MappedClasses.YS);
        if (shouldCancel && !this.clickingSlot && this.inventoryOpen) {
            eventWindowClick.setCancelled(true);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    static {
        UNUSED_CONST_A = 1215;
        INVENTORY_SLOT_COUNT = 45;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        boolean inInventoryScreen;
        boolean silentEquipping;
        if (this.suppressInput) {
            if (this.actionTimer.hasTimeElapsed((long)this.silentMoveDelay.B())) {
                this.releaseMovementKeys(eventPrePlayerTick.getGameSettings());
                this.suppressInput = false;
            } else {
                this.blockMovementKeys(eventPrePlayerTick.getGameSettings());
            }
        }
        if (Vape.INSTANCE.getModManager().N(AutoTotem.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.clickQueue.clear();
            return;
        }
        this.b$src$V$gc7j1i();
        EntityPlayerSP entityPlayerSP = eventPrePlayerTick.getThePlayer();
        if (entityPlayerSP.isNull() || entityPlayerSP.M$src$Z$ff28xj()) {
            if (this.inventoryOpen && this.silentActive) {
                this.closeInventory();
            }
            this.clickingSlot = false;
            return;
        }
        if (!entityPlayerSP.p$src$Lgg_vape_wrapper_impl_Container_$1a6go00().isNull() && entityPlayerSP.p$src$Lgg_vape_wrapper_impl_Container_$1a6go00().getWindowId() != 0) {
            return;
        }
        GuiScreen guiScreen = Minecraft.currentScreen();
        boolean silentOpenActive = silentEquipping = this.inventoryOpen && this.silentActive;
        if (silentEquipping) {
            this.actionTimer.reset();
        }
        boolean inventoryAccessible = inInventoryScreen = silentEquipping || guiScreen.isInstance(MappedClasses.YS) || guiScreen.isInstance(MappedClasses.n);
        if (this.openInventory.L().booleanValue() && !inInventoryScreen) {
            this.clickQueue.clear();
        }
        if (this.inventoryOnly.L().booleanValue() && !inInventoryScreen) {
            this.clickQueue.clear();
            return;
        }
        if (!this.isDelayElapsed()) {
            return;
        }
        if (!this.clickQueue.isEmpty()) {
            if (this.I$src$Z$fygoax()) {
                InventoryClick inventoryClick = this.clickQueue.poll();
                if (inventoryClick != null) {
                    this.clickingSlot = true;
                    inventoryClick.k();
                    this.clickingSlot = false;
                }
                this.actionTimer.reset();
                this.resetClickTimer();
            }
            return;
        }
        if (this.closePending) {
            this.closeInventory();
            return;
        }
        ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(45).I();
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        if (itemMappingEntry != null && itemMappingEntry.M().toLowerCase().contains("totem_of_undying")) {
            if (this.inventoryOpen && this.clickQueue.isEmpty()) {
                this.closePending = true;
                this.resetDelayTimer();
            }
            return;
        }
        int totemSlot = this.s$src$I$glk0tg();
        if (totemSlot != -1) {
            if (this.openInventory.L().booleanValue() && !guiScreen.isInstance(MappedClasses.YS) && !silentEquipping) {
                if (this.isDelayElapsed()) {
                    this.M$src$V$g0nukx();
                }
                return;
            }
            if (inInventoryScreen && !this.inventoryOpen) {
                this.inventoryOpen = true;
            }
            this.queueClick(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), totemSlot, 40, 2);
            this.resetClickTimer();
        }
        if (this.inventoryOpen && this.clickQueue.isEmpty()) {
            this.closePending = true;
            this.resetDelayTimer();
        }
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void onRightClickMouse(EventRightClickMouse eventRightClickMouse) {
        if (this.suppressInput) {
            eventRightClickMouse.setCancelled(true);
        }
    }

    public AutoTotem() {
        super("AutoTotem", -43691, Category.M, "Automatically equips totems to your offhand");
        this.actionTimer = new TimerUtil();
        this.clickQueue = new ConcurrentLinkedQueue<InventoryClick>();
        this.openInventory = BooleanValue.create(this, "Open inventory", true, "Opens your inventory to equip a totem");
        this.silentOpen = BooleanValue.create(this, "Silent open", false, "Silently opens your inventory to equip a totem");
        this.silentMoveDelay = RandomValue.G(this, "Silent move delay", "#", "ms", 50.0, 100.0, 120.0, 200.0, 1.0, "Delay before preventing movement keys after silently opening the inventory");
        this.closeInventory = BooleanValue.create(this, "Close inventory", true, "Closes your inventory after equipping a totem");
        this.inventoryOnly = BooleanValue.create(this, "Inventory only", false, "Only equips a totem when in your inventory");
        this.randomSlot = BooleanValue.create(this, "Random slot", true, "Chooses a random totem slot from your inventory");
        this.delay = RandomValue.G(this, "Delay", "#", "ms", 50.0, 100.0, 120.0, 200.0, 1.0, "How long to wait before equipping a totem");
        this.extraRandomization = BooleanValue.create(this, "Extra randomization", true, "Adds human-like timing variance while equipping totems");
        this.showTotemCount = BooleanValue.create(this, "Show totem count", false, "Renders your totem count on the center of your screen");
        this.random = new Random();
        this.rotationManager = RotationManager.b;
        this.rotationClaim = SharedModuleControlClaims.I;
        this.openInventory.K(this.silentOpen, this.silentMoveDelay, this.closeInventory);
        this.silentOpen.K(this.silentMoveDelay);
        this.silentOpen.C().z(this.closeInventory);
        this.addValue(this.openInventory, this.silentOpen, this.silentMoveDelay, this.closeInventory, this.inventoryOnly, this.randomSlot, this.delay, this.extraRandomization, this.showTotemCount);
        this.rotationClaim.l(this, 99);
    }

    private void resetClickTimer() {
        this.clickTimer.reset();
        AutoTotem autoTotem = this;
        this.clickDelay = Math.max(1L, (long)autoTotem.computeDelay());
    }

    private int s$src$I$glk0tg() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ArrayList<Integer> totemSlots = new ArrayList<Integer>();
        for (int i = 9; i < 45; ++i) {
            ItemMappingEntry itemMappingEntry;
            ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I();
            if (itemStack.isNull() || (itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack)) == null || !itemMappingEntry.M().toLowerCase().contains("totem_of_undying")) continue;
            if (!this.randomSlot.L().booleanValue()) {
                return i;
            }
            totemSlots.add(i);
        }
        if (totemSlots.isEmpty()) {
            return -1;
        }
        return (Integer)totemSlots.get(this.random.nextInt(totemSlots.size()));
    }

    private int findEmptySlot() {
        int slot;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        for (slot = 36; slot < 45; ++slot) {
            if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(slot).I().isNotNull()) continue;
            return slot;
        }
        for (slot = 9; slot < 36; ++slot) {
            if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(slot).I().isNotNull()) continue;
            return slot;
        }
        return -1;
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void onThreadBoundPostTick(EventThreadBoundPostTick eventThreadBoundPostTick) {
        if (this.suppressInput) {
            eventThreadBoundPostTick.setCancelled(true);
        }
    }

    private boolean I$src$Z$fygoax() {
        if (this.clickDelay <= 0L) {
            AutoTotem autoTotem = this;
            this.clickDelay = Math.max(1L, (long)autoTotem.computeDelay());
        }
        return this.clickTimer.hasTimeElapsed(this.clickDelay);
    }

    private boolean M$src$Z$g0nuod() {
        if (freecam == null) {
            freecam = Vape.INSTANCE.getModManager().getMod(Freecam.class);
        }
        return freecam != null && freecam.r$src$Z$14eylz9() || this.rotationClaim.e(this) && !this.rotationClaim.h(this, true);
    }

    @EventHandler(A=EventPriority.HIGHEST)
    public void onThreadBoundPreTick(EventThreadBoundPreTick eventThreadBoundPreTick) {
        if (this.suppressInput) {
            eventThreadBoundPreTick.setCancelled(true);
        }
    }

    @Override
    public void onEnable() {
        ClientSettings.g(ActiveModuleStackFrame.class).c(this);
        this.resetDelayTimer();
        this.resetClickTimer();
    }

    private void resetDelayTimer() {
        this.delayTimer.reset();
        AutoTotem autoTotem = this;
        this.delayDuration = Math.max(1L, (long)autoTotem.computeDelay());
    }

    private void blockMovementKeys(GameSettings gameSettings) {
        if (this.suppressInput) {
            KeyBindingHelper.d(gameSettings.Y(), false);
            KeyBindingHelper.d(gameSettings.s(), false);
            KeyBindingHelper.d(gameSettings.x$src$Lgg_vape_wrapper_impl_KeyBinding_$1cf7isg(), false);
            KeyBindingHelper.d(gameSettings.g$src$Lgg_vape_wrapper_impl_KeyBinding_$qqn5n3(), false);
            KeyBindingHelper.d(gameSettings.O(), false);
            KeyBindingHelper.d(gameSettings.r(), false);
        }
    }
}
