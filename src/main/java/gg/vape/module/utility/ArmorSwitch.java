package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.UtilityMod;
import gg.vape.module.utility.armorswitch.ArmorMaterialType;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gg.vape.module.utility.inventory.ArmorItemMappingEntry;

public class ArmorSwitch
extends UtilityMod {
    private final NumberValue delayValue;
    private static final long INITIAL_SLOT = -8554964792182308859L;
    private boolean screenReady;
    private final ModeValue set1;
    private ArmorMaterialType currentMaterial;
    private final HashMap<ModeOption, ArmorMaterialType> materialByOption = new HashMap();
    private int slotIndex;
    private final TimerUtil timer;
    private int clickPhase;
    private final Map<Integer, Integer> armorSlotTargets;
    private final ModeValue set2;
    private boolean finished;
    private boolean collecting;

    private static ObfuscatedRuntimeException passThrough(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ArmorSwitch() {
        super("ArmorSwitch", Category.M, "Switches between armor sets");
        this.delayValue = NumberValue.create(this, "Delay", "#", "", 0.0, 100.0, 200.0);
        this.timer = new TimerUtil();
        this.armorSlotTargets = new HashMap<Integer, Integer>();
        ModeOption modeOption = new ModeOption("Diamond");
        ModeOption modeOption2 = new ModeOption("Gold");
        ModeOption modeOption3 = new ModeOption("Iron");
        ModeOption modeOption4 = new ModeOption("Leather");
        ModeOption modeOption5 = new ModeOption("Chain");
        this.materialByOption.put(modeOption, ArmorMaterialType.DIAMOND);
        this.materialByOption.put(modeOption2, ArmorMaterialType.GOLD);
        this.materialByOption.put(modeOption3, ArmorMaterialType.IRON);
        this.materialByOption.put(modeOption4, ArmorMaterialType.LEATHER);
        this.materialByOption.put(modeOption5, ArmorMaterialType.CHAINMAIL);
        this.set1 = ModeValue.create((Object)this, "Set 1", modeOption, this.materialByOption.keySet().toArray(new ModeOption[this.materialByOption.size()]));
        this.set2 = ModeValue.create((Object)this, "Set 2", modeOption2, this.materialByOption.keySet().toArray(new ModeOption[this.materialByOption.size()]));
        this.addValue(this.set1, this.set2, this.delayValue);
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNotNull() && entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode()) {
            this.Y(false);
            return;
        }
        if (this.finished) {
            this.Y(false);
            return;
        }
        if (!Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
            if (!this.screenReady) {
                KeyBinding keyBinding = Minecraft.gameSettings().j();
                if (ForgeVersion.MC_1_16_5.d()) {
                    KeyBindingHelper.a(keyBinding);
                } else {
                    KeyBindingHelper.d(keyBinding, true);
                    KeyBindingHelper.v(keyBinding, false, false);
                }
            } else {
                this.Y(false);
            }
            return;
        }
        boolean active = this.screenReady;
        if (!active) {
            this.screenReady = true;
            return;
        }
        if (this.collecting && this.timer.hasTimeElapsed(((Double)this.delayValue.K()).longValue())) {
            this.timer.reset();
            int slot = this.armorSlotTargets.get(this.slotIndex);
            this.performClick(this.slotIndex, slot);
            if (this.slotIndex > 8) {
                this.finished = true;
            }
            return;
        }
        boolean hasArmor = this.hasEquippedArmor();
        if (this.screenReady && hasArmor && !this.collecting) {
            boolean wearingSet1 = this.currentMaterial.equals((Object)this.materialByOption.get(this.set1.K()));
            if (this.findArmorSlots(wearingSet1 ? this.materialByOption.get(this.set2.K()) : this.materialByOption.get(this.set1.K()))) {
                this.collecting = true;
            } else {
                this.Y(false);
            }
        }
        if (!hasArmor) {
            this.Y(false);
        }
    }

    private boolean findArmorSlots(ArmorMaterialType armorMaterialType) {
        int helmetSlot = 0;
        int chestplateSlot = 0;
        int leggingsSlot = 0;
        int bootsSlot = 0;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        Container container = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        List<Slot> list = container.getInventorySlots();
        block6: for (int i = 9; i < 45; ++i) {
            ArmorItemMappingEntry ctFieldWithInit;
            Item item;
            Slot slot = list.get(i);
            if (!slot.v() || !ItemStackScoreUtil.R(item = slot.I().getItem()) || !(ctFieldWithInit = (ArmorItemMappingEntry)Vape.INSTANCE.getItemStackResolver().j(slot.I())).getArmorMaterial().equals((Object)armorMaterialType)) continue;
            int armorType = ItemStackScoreUtil.H(item);
            switch (armorType) {
                case 0: {
                    helmetSlot = i;
                    continue block6;
                }
                case 1: {
                    chestplateSlot = i;
                    continue block6;
                }
                case 2: {
                    leggingsSlot = i;
                    continue block6;
                }
                case 3: {
                    bootsSlot = i;
                }
            }
        }
        if (ForgeVersion.MC_1_16_5.v() && ForgeVersion.MC_1_12_2.d()) {
            this.armorSlotTargets.put(8, helmetSlot);
            this.armorSlotTargets.put(7, chestplateSlot);
            this.armorSlotTargets.put(6, leggingsSlot);
            this.armorSlotTargets.put(5, bootsSlot);
        } else {
            this.armorSlotTargets.put(5, helmetSlot);
            this.armorSlotTargets.put(6, chestplateSlot);
            this.armorSlotTargets.put(7, leggingsSlot);
            this.armorSlotTargets.put(8, bootsSlot);
        }
        return helmetSlot != 0 && chestplateSlot != 0 && leggingsSlot != 0 && bootsSlot != 0;
    }

    @Override
    public void onDisable() {
        if (Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
            Minecraft.thePlayer().Z$src$V$1ie832h();
        }
    }

    @Override
    public void onEnable() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNotNull() && entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode()) {
            this.Y(false);
            return;
        }
        this.slotIndex = (int)INITIAL_SLOT;
        this.clickPhase = 0;
        this.collecting = false;
        this.finished = false;
        this.screenReady = false;
    }

    private void performClick(int inventorySlot, int targetSlot) {
        int clickSlot = targetSlot;
        int mouseButton = 0;
        if (this.clickPhase == 1) {
            clickSlot = inventorySlot;
        }
        Minecraft.playerController().O(Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), clickSlot, 0, mouseButton, Minecraft.thePlayer());
        ++this.clickPhase;
        if (this.clickPhase >= 3) {
            this.clickPhase = 0;
            ++this.slotIndex;
        }
    }

    private boolean hasEquippedArmor() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        Container container = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        List<Slot> list = container.getInventorySlots();
        for (int i = 5; i < 9; ++i) {
            Slot slot = list.get(i);
            if (!slot.v() || !ItemStackScoreUtil.R(slot.I().getItem())) continue;
            ArmorItemMappingEntry ctFieldWithInit = (ArmorItemMappingEntry)Vape.INSTANCE.getItemStackResolver().j(slot.I());
            this.currentMaterial = ctFieldWithInit.getArmorMaterial();
            return true;
        }
        return false;
    }
}
