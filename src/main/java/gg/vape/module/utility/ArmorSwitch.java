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
    private final NumberValue V;
    private static final long t = -8554964792182308859L;
    private boolean A;
    private final ModeValue r;
    private ArmorMaterialType k;
    private final HashMap<ModeOption, ArmorMaterialType> D = new HashMap();
    private int s;
    private final TimerUtil Y;
    private int j;
    private final Map<Integer, Integer> H;
    private final ModeValue o;
    private boolean U;
    private boolean Z;

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public ArmorSwitch() {
        super("ArmorSwitch", Category.M, "Switches between armor sets");
        this.V = NumberValue.create(this, "Delay", "#", "", 0.0, 100.0, 200.0);
        this.Y = new TimerUtil();
        this.H = new HashMap<Integer, Integer>();
        ModeOption modeOption = new ModeOption("Diamond");
        ModeOption modeOption2 = new ModeOption("Gold");
        ModeOption modeOption3 = new ModeOption("Iron");
        ModeOption modeOption4 = new ModeOption("Leather");
        ModeOption modeOption5 = new ModeOption("Chain");
        this.D.put(modeOption, ArmorMaterialType.DIAMOND);
        this.D.put(modeOption2, ArmorMaterialType.GOLD);
        this.D.put(modeOption3, ArmorMaterialType.IRON);
        this.D.put(modeOption4, ArmorMaterialType.LEATHER);
        this.D.put(modeOption5, ArmorMaterialType.CHAINMAIL);
        this.r = ModeValue.create((Object)this, "Set 1", modeOption, this.D.keySet().toArray(new ModeOption[this.D.size()]));
        this.o = ModeValue.create((Object)this, "Set 2", modeOption2, this.D.keySet().toArray(new ModeOption[this.D.size()]));
        this.addValue(this.r, this.o, this.V);
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNotNull() && entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode()) {
            this.Y(false);
            return;
        }
        if (this.U) {
            this.Y(false);
            return;
        }
        if (!Minecraft.currentScreen().isInstance(MappedClasses.YS)) {
            if (!this.A) {
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
        boolean active = this.A;
        if (!active) {
            this.A = true;
            return;
        }
        if (this.Z && this.Y.hasTimeElapsed(((Double)this.V.K()).longValue())) {
            this.Y.reset();
            int slot = this.H.get(this.s);
            this.Y(this.s, slot);
            if (this.s > 8) {
                this.U = true;
            }
            return;
        }
        boolean hasArmor = this.a$src$Z$y3jhsy();
        if (this.A && hasArmor && !this.Z) {
            boolean bl = this.k.equals((Object)this.D.get(this.r.K()));
            if (this.q(bl ? this.D.get(this.o.K()) : this.D.get(this.r.K()))) {
                this.Z = true;
            } else {
                this.Y(false);
            }
        }
        if (!hasArmor) {
            this.Y(false);
        }
    }

    private boolean q(ArmorMaterialType armorMaterialType) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        Container container = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        List<Slot> list = container.getInventorySlots();
        block6: for (int i = 9; i < 45; ++i) {
            ArmorItemMappingEntry ctFieldWithInit;
            Item item;
            Slot slot = list.get(i);
            if (!slot.v() || !ItemStackScoreUtil.R(item = slot.I().getItem()) || !(ctFieldWithInit = (ArmorItemMappingEntry)Vape.INSTANCE.getItemStackResolver().j(slot.I())).getArmorMaterial().equals((Object)armorMaterialType)) continue;
            int n5 = ItemStackScoreUtil.H(item);
            switch (n5) {
                case 0: {
                    n = i;
                    continue block6;
                }
                case 1: {
                    n2 = i;
                    continue block6;
                }
                case 2: {
                    n3 = i;
                    continue block6;
                }
                case 3: {
                    n4 = i;
                }
            }
        }
        if (ForgeVersion.MC_1_16_5.v() && ForgeVersion.MC_1_12_2.d()) {
            this.H.put(8, n);
            this.H.put(7, n2);
            this.H.put(6, n3);
            this.H.put(5, n4);
        } else {
            this.H.put(5, n);
            this.H.put(6, n2);
            this.H.put(7, n3);
            this.H.put(8, n4);
        }
        return n != 0 && n2 != 0 && n3 != 0 && n4 != 0;
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
        this.s = (int)t;
        this.j = 0;
        this.Z = false;
        this.U = false;
        this.A = false;
    }

    private void Y(int n, int n2) {
        int n3 = n2;
        int n4 = 0;
        if (this.j == 1) {
            n3 = n;
        }
        Minecraft.playerController().O(Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), n3, 0, n4, Minecraft.thePlayer());
        ++this.j;
        if (this.j >= 3) {
            this.j = 0;
            ++this.s;
        }
    }

    private boolean a$src$Z$y3jhsy() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        Container container = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        List<Slot> list = container.getInventorySlots();
        for (int i = 5; i < 9; ++i) {
            Slot slot = list.get(i);
            if (!slot.v() || !ItemStackScoreUtil.R(slot.I().getItem())) continue;
            ArmorItemMappingEntry ctFieldWithInit = (ArmorItemMappingEntry)Vape.INSTANCE.getItemStackResolver().j(slot.I());
            this.k = ctFieldWithInit.getArmorMaterial();
            return true;
        }
        return false;
    }
}
