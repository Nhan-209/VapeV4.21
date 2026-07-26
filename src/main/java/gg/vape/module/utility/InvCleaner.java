package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.inventory.InventoryClick;
import gg.vape.inventory.InventoryClickQueue;
import gg.vape.mapping.ItemMappingEntry;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.inventory.InventoryActionGuard;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.module.utility.inventory.cleaner.ArmorSlotInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.HiddenInventoryItemMatchers;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfile;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;
import gg.vape.module.utility.inventory.cleaner.InventoryFilterAction;
import gg.vape.module.utility.inventory.cleaner.InventoryItemCategoryRegistry;
import gg.vape.module.utility.inventory.cleaner.ItemInventoryFilterRule;
import gg.vape.module.utility.inventory.cleaner.SlotInventoryFilterRule;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.ModeValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.GuiContainer;
import gg.vape.wrapper.impl.GuiScreen;
import gg.vape.wrapper.impl.InventoryPlayer;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;

public class InvCleaner
extends Mod
implements InventoryActionModule {
    private boolean J;
    private final ModeValue O;
    private boolean j;
    private final TimerUtil V;
    private final TimerUtil c;
    private String[] r;
    private GuiScreen A = null;
    private static final long bb = 6780151590972480718L;
    private String[] H;
    private final InventoryActionGuard C;
    private final TimerUtil b;
    private final RandomValue Y = RandomValue.create(this, "Click delay", "#", "", 50.0, 100.0, 150.0, 300.0);
    private final ModeOption k = new ModeOption("On Key");
    private final ModeOption L = new ModeOption("Toggle");
    private final List<Integer> K;
    private String[] o;
    private boolean a = false;
    private boolean P = false;
    private final BooleanValue F;
    private final InventoryCleanerProfileValue p;
    private boolean I = false;
    private String[] v;
    private final BooleanValue t;
    private final Queue<InventoryClick> D;

    private boolean D() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (guiScreen.isNotNull() && guiScreen.isInstance(MappedClasses.YS) && !this.D.isEmpty()) {
            if (!this.V.hasTimeElapsed(200 + ThreadLocalRandom.current().nextInt(200))) {
                return true;
            }
            if (this.c.hasTimeElapsed((long)this.Y.B())) {
                InventoryClick inventoryClick = this.D.poll();
                if (inventoryClick != null) {
                    inventoryClick.k();
                }
                this.c.reset();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && !this.D.isEmpty() && (this.t.L() != false || Minecraft.currentScreen().isNull());
    }

    @Override
    public boolean X() {
        return this.O.K() == this.k;
    }

    private Collection<SlotInventoryFilterRule> A() {
        ArrayList<SlotInventoryFilterRule> arrayList = new ArrayList<SlotInventoryFilterRule>(((InventoryCleanerProfile)this.p.K()).P());
        if (((InventoryCleanerProfile)this.p.K()).U.o()) {
            arrayList.add(new ArmorSlotInventoryFilterRule(0));
            arrayList.add(new ArmorSlotInventoryFilterRule(1));
            arrayList.add(new ArmorSlotInventoryFilterRule(2));
            arrayList.add(new ArmorSlotInventoryFilterRule(3));
        }
        return arrayList;
    }

    private boolean f(Slot slot) {
        ItemStack itemStack = slot.I();
        return itemStack.isNull();
    }

    private boolean Z(SlotInventoryFilterRule slotInventoryFilterRule) {
        Container container;
        Wrapper wrapper;
        if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            wrapper = new GuiContainer(Minecraft.currentScreen());
            container = ((GuiContainer)wrapper).getInventorySlots();
        } else {
            container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        }
        if (slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule) {
            wrapper = container.getSlot(slotInventoryFilterRule.b());
            return ((Slot)wrapper).I().isNull();
        }
        for (int i = 0; i < 9; ++i) {
            Slot slot = container.getSlot(36 + i);
            if (!slot.I().isNull()) continue;
            return i == slotInventoryFilterRule.m();
        }
        return false;
    }

    private boolean C$src$Z$1v3h936() {
        if (Vape.INSTANCE.getModManager().N(InvCleaner.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.D.clear();
            this.c.reset();
            this.K.clear();
            return true;
        }
        if (Minecraft.thePlayer().C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode()) {
            return true;
        }
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.p.K();
        return inventoryCleanerProfile == null;
    }

    private void h$src$V$1vntmy3() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (RotationUtil.Z().isNotNull()) {
            this.D.clear();
            InventoryClickQueue.Q(0, this.D);
            return;
        }
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.p.K();
        Container container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        if (inventoryCleanerProfile.Q().isEmpty()) {
            return;
        }
        ArrayList<Slot> arrayList = new ArrayList<Slot>();
        for (Slot object : container.getInventorySlots()) {
            arrayList.add(object);
        }
        LinkedHashMap<ItemInventoryFilterRule, List<Slot>> linkedHashMap = new LinkedHashMap<>();
        for (Slot slot : arrayList) {
            SlotInventoryFilterRule slotInventoryFilterRule;
            ItemInventoryFilterRule itemInventoryFilterRule;
            ItemStack itemStack = slot.I();
            if (itemStack.isNull() || itemStack.getItem().isNull() || (itemInventoryFilterRule = inventoryCleanerProfile.e(itemStack)) == null || !itemInventoryFilterRule.q(itemStack) || slot.g() >= 36 && slot.g() <= 44 && (slotInventoryFilterRule = inventoryCleanerProfile.I(slot.g() - 36)).q().h(itemStack) && slotInventoryFilterRule.q(itemStack) || this.K.contains(slot.g())) continue;
            if (itemInventoryFilterRule.K() == InventoryFilterAction.REMOVE) {
                InventoryClickQueue.a(slot.g(), container.getWindowId(), this.D);
                continue;
            }
            if (itemInventoryFilterRule.K() != InventoryFilterAction.CONDENSE) continue;
            linkedHashMap.computeIfAbsent(itemInventoryFilterRule, InvCleaner::lambda$getFilterClicks$1).add(slot);
        }
        this.d(linkedHashMap, container.getWindowId());
    }

    private boolean A$src$Z$1v2dnwg() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (!guiScreen.isInstance(MappedClasses.YS)) {
            return false;
        }
        GuiContainer guiContainer = new GuiContainer(guiScreen);
        Container container = guiContainer.getInventorySlots();
        for (int i = 9; i < 36; ++i) {
            Slot slot = container.getSlot(i);
            ItemStack itemStack = slot.I();
            if (!itemStack.isNull()) continue;
            return true;
        }
        return false;
    }

    private int A(int n) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        return entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().isNotNull() ? entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I().L() : 999;
    }

    private boolean P() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (!this.D.isEmpty() && this.j && (guiScreen.isNull() || !guiScreen.isInstance(MappedClasses.YS))) {
            this.P = true;
            this.H(false);
            return true;
        }
        return false;
    }

    public InvCleaner() {
        super("InventoryManager", (int)bb, Category.M, "Manage your inventory");
        this.O = ModeValue.create((Object)this, "Activation", this.k, this.k, this.L);
        this.t = BooleanValue.create(this, "Open inventory", true, "If on, inventory will automatically be opened when inventory needs to be managed\nIf off, inventory will only be managed after inventory is manually opened");
        this.F = BooleanValue.create(this, "Combat check", false);
        this.p = InventoryCleanerProfileValue.Q(this, "Inventory");
        this.D = new ConcurrentLinkedQueue<InventoryClick>();
        this.K = new ArrayList<Integer>();
        this.c = new TimerUtil();
        this.V = new TimerUtil();
        this.b = new TimerUtil();
        this.C = new InventoryActionGuard(20);
        this.O.L(this.t, this.L);
        this.O.L(this.F, this.L);
        this.addValue(this.O, this.t, this.F, this.Y, this.p);
        this.r = new String[]{"cap", "helmet"};
        this.o = new String[]{"tunic", "chestplate"};
        this.H = new String[]{"pants", "leggings"};
        this.v = new String[]{"boots"};
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    private static List<Slot> lambda$getFilterClicks$1(ItemInventoryFilterRule itemInventoryFilterRule) {
        return new ArrayList<>();
    }

    @Override
    public void onEnable() {
        this.G();
        if (this.O.K() == this.k) {
            this.a = true;
        }
    }

    private void R() {
        InventoryPlayer inventoryPlayer = Minecraft.thePlayer().V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6();
        if (RotationUtil.Z().isNull()) {
            return;
        }
        if (this.A$src$Z$1v2dnwg()) {
            return;
        }
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (guiScreen.isNull()) {
            return;
        }
        GuiContainer guiContainer = new GuiContainer(guiScreen);
        Container container = guiContainer.getInventorySlots();
        ItemStack itemStack = RotationUtil.Z();
        boolean bl = false;
        for (SlotInventoryFilterRule slotInventoryFilterRule : this.A()) {
            int n;
            if (!slotInventoryFilterRule.q().h(itemStack) || !slotInventoryFilterRule.q(itemStack)) continue;
            Slot slot = container.getSlot(slotInventoryFilterRule.b());
            Comparator<ItemStack> comparator = slotInventoryFilterRule.o().j();
            if (slotInventoryFilterRule.o().equals(InventoryItemCategoryRegistry.m) || comparator != null && (n = comparator.compare(slot.I(), itemStack)) >= 0) continue;
            bl = true;
            break;
        }
        if (!bl) {
            this.D.clear();
            InventoryClickQueue.Q(0, this.D);
            return;
        }
    }

    private void d(Map<ItemInventoryFilterRule, List<Slot>> map, int n) {
        for (Map.Entry<ItemInventoryFilterRule, List<Slot>> entry : map.entrySet()) {
            ItemInventoryFilterRule itemInventoryFilterRule = entry.getKey();
            List<Slot> list = entry.getValue();
            list.removeIf(InvCleaner::lambda$condenseInventory$2);
            if (list.size() <= 1) continue;
            list.sort(InvCleaner::lambda$condenseInventory$3);
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            block1: for (int i = 0; i < list.size(); ++i) {
                Slot slot = list.get(i);
                ItemStack itemStack = slot.I();
                int n2 = itemStack.t();
                if (arrayList.contains(slot.g())) continue;
                for (int j = i + 1; j < list.size(); ++j) {
                    Slot slot2 = list.get(j);
                    if (slot.g() == slot2.g()) continue;
                    ItemStack itemStack2 = slot2.I();
                    int n3 = itemStack2.t();
                    if (!itemStack.getItem().equals(itemStack2.getItem())) continue;
                    int n4 = n2 + n3;
                    int n5 = itemStack.P();
                    if (n4 <= n5) {
                        arrayList.add(slot2.g());
                        InventoryClickQueue.V(slot2.g(), slot.g(), n, this.D);
                        continue block1;
                    }
                    int n6 = n5 - n2;
                    InventoryClickQueue.e(slot2.g(), slot.g(), n, this.D);
                    InventoryClickQueue.e(slot.g(), slot2.g(), n, this.D);
                    InventoryClickQueue.e(slot2.g(), slot.g(), n, this.D);
                    if (n6 == 0) continue block1;
                }
            }
        }
    }

    @Override
    public void onDisable() {
        this.G();
    }

    private int b(int n, boolean bl) {
        int n2 = -1;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ItemStack itemStack = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(n).I();
        double d = 0.0;
        double d2 = 999.0;
        if (itemStack.isNotNull()) {
            d = ItemStackScoreUtil.L(itemStack);
            d2 = this.A(n);
        }
        double d3 = d;
        double d4 = d2;
        for (int i = 9; i < 45; ++i) {
            ItemStack itemStack2;
            if (!entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I().isNotNull() || this.k(itemStack2 = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getSlot(i).I()) == -1 || this.k(itemStack2) != n) continue;
            double d5 = ItemStackScoreUtil.L(itemStack2);
            double d6 = this.A(i);
            if (d5 > d3) {
                d3 = d5;
                n2 = i;
                d4 = d6;
                continue;
            }
            if (!bl || d5 != d3 || !(d6 < d4)) continue;
            d4 = d6;
            n2 = i;
        }
        return n2;
    }

    private List<Slot> R(Container container, Slot slot, SlotInventoryFilterRule slotInventoryFilterRule) {
        Item item;
        Object object;
        int n;
        int n2;
        List<Slot> list = container.getInventorySlots();
        if (slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule && (n2 = this.b(n = slotInventoryFilterRule.b(), false)) != -1 && !this.K.contains(n2)) {
            return Collections.singletonList(list.get(n2));
        }
        ArrayList<Slot> arrayList = new ArrayList<Slot>();
        ItemStack itemStack = null;
        for (int i = 9; i < list.size(); ++i) {
            ItemStack itemStack2;
            if (this.K.contains(i) || !(itemStack2 = ((Slot)(object = container.getSlot(i))).I()).isNotNull() || !slotInventoryFilterRule.q().h(itemStack2) || !slotInventoryFilterRule.q(itemStack2)) continue;
            itemStack = itemStack2;
            arrayList.add((Slot)object);
        }
        if (itemStack != null && (item = itemStack.getItem()).isNotNull() && (object = slotInventoryFilterRule.o().j()) != null) {
            Comparator itemComparator = (Comparator)object;
            arrayList.sort((arg_0, arg_1) -> InvCleaner.lambda$getOtherValid$0(itemComparator, arg_0, arg_1));
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (this.C$src$Z$1v3h936()) {
            return;
        }
        if (this.P()) {
            return;
        }
        if (this.c()) {
            return;
        }
        if (this.J) {
            if (this.b.hasTimeElapsed(200 + ThreadLocalRandom.current().nextInt(200))) {
                this.H(true);
            }
            return;
        }
        if (this.O.K() == this.L && this.F.L().booleanValue()) {
            this.C.i(eventPrePlayerTick.getPlayer());
            if (this.C.l()) {
                this.a = false;
                this.s();
                this.V.reset();
                return;
            }
        }
        if (this.O.K() == this.L && this.D.isEmpty()) {
            this.a = true;
        }
        if (this.D()) {
            if (this.D.isEmpty()) {
                this.z();
                this.a = false;
                if (this.D.isEmpty()) {
                    this.h$src$V$1vntmy3();
                }
                if (this.D.isEmpty()) {
                    this.s();
                }
            }
        } else if (!this.a) {
            this.R();
            if (this.D.isEmpty()) {
                this.s();
                return;
            }
        }
        if (this.a) {
            this.z();
            this.a = false;
            if (this.D.isEmpty()) {
                this.h$src$V$1vntmy3();
            }
            if (this.D.isEmpty()) {
                this.I = true;
            }
            if (this.O.K() != this.L && this.D.isEmpty()) {
                this.s();
                Vape.INSTANCE.getNotificationManager().k("Inventory Manager", "No work available", 4000L);
                return;
            }
        }
        if ((this.O.K() == this.k || this.t.L().booleanValue()) && this.I$src$Z$1v6s0nc()) {
            return;
        }
    }

    private static int lambda$getOtherValid$0(Comparator comparator, Slot slot, Slot slot2) {
        return comparator.compare(slot.I(), slot2.I());
    }

    private void s() {
        this.J = true;
        this.b.reset();
    }

    private static int lambda$condenseInventory$3(Slot slot, Slot slot2) {
        ItemStack itemStack = slot.I();
        ItemStack itemStack2 = slot2.I();
        return Integer.compare(itemStack.isNull() ? 0 : itemStack.t(), itemStack2.isNull() ? 0 : itemStack2.t());
    }

    private void G() {
        this.c.reset();
        this.D.clear();
        this.K.clear();
        this.J = false;
        this.j = false;
        this.P = false;
    }

    @Override
    public boolean q$src$Z$12h8h4c() {
        if (this.k.o()) {
            return false;
        }
        return super.q$src$Z$12h8h4c();
    }

    private static boolean lambda$condenseInventory$2(Slot slot) {
        ItemStack itemStack = slot.I();
        return itemStack.isNull() || itemStack.t() >= itemStack.P();
    }

    private boolean F$src$Z$1v54mv9() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (!guiScreen.isInstance(MappedClasses.YS)) {
            return false;
        }
        GuiContainer guiContainer = new GuiContainer(guiScreen);
        Container container = guiContainer.getInventorySlots();
        for (int i = 9; i < 36; ++i) {
            Slot slot = container.getSlot(i);
            ItemStack itemStack = slot.I();
            if (!itemStack.isNull()) continue;
            InventoryClickQueue.e(i, 0, container.getWindowId(), this.D);
            return true;
        }
        return false;
    }

    @Override
    public String r() {
        if (this.F.L().booleanValue() && this.C.l()) {
            return ClientSettings.F + "c[In Combat]";
        }
        return super.r();
    }

    private boolean c() {
        if (this.O.K() != this.L) {
            return false;
        }
        if (this.t.L().booleanValue()) {
            return false;
        }
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (this.A == null) {
            this.A = guiScreen;
        }
        if (!this.A.equals(guiScreen)) {
            this.I = false;
        }
        if (this.I) {
            return true;
        }
        this.A = guiScreen;
        return false;
    }

    private int k(ItemStack itemStack) {
        ItemMappingEntry itemMappingEntry = Vape.INSTANCE.getItemStackResolver().j(itemStack);
        for (String string : this.v) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 8;
        }
        for (String string : this.H) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 7;
        }
        for (String string : this.o) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 6;
        }
        for (String string : this.r) {
            if (!itemMappingEntry.M().toLowerCase().contains(string)) continue;
            return 5;
        }
        return -1;
    }

    private boolean I$src$Z$1v6s0nc() {
        GuiScreen guiScreen = Minecraft.currentScreen();
        if (guiScreen.isNotNull()) {
            return false;
        }
        if (!this.D.isEmpty() && guiScreen.isNull()) {
            KeyBinding keyBinding = Minecraft.gameSettings().j();
            if (ForgeVersion.MC_1_16_5.d()) {
                KeyBindingHelper.a(keyBinding);
            } else {
                KeyBindingHelper.d(keyBinding, true);
                KeyBindingHelper.v(keyBinding, false, false);
            }
            this.j = true;
            this.V.reset();
            return true;
        }
        return false;
    }

    private void z() {
        List<Slot> list;
        Wrapper wrapper;
        this.R();
        this.K.clear();
        InventoryCleanerProfile inventoryCleanerProfile = (InventoryCleanerProfile)this.p.K();
        Container container = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        for (SlotInventoryFilterRule slotInventoryFilterRule : this.A()) {
            Slot slot;
            if (HiddenInventoryItemMatchers.R.equals(slotInventoryFilterRule.q().c())) {
                this.K.add(slotInventoryFilterRule.b());
            }
            if (((ItemStack)(wrapper = (slot = container.getSlot(slotInventoryFilterRule.b())).I())).isNull() || !(slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule) && (!slotInventoryFilterRule.q().h((ItemStack)wrapper) || !slotInventoryFilterRule.q((ItemStack)wrapper))) continue;
            list = this.R(container, slot, slotInventoryFilterRule);
            if (!list.isEmpty()) {
                Slot slot2 = (Slot)list.get(0);
                if (slot2.g() != slot.g()) continue;
                this.K.add(slotInventoryFilterRule.b());
                continue;
            }
            if (!(slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule)) continue;
            this.K.add(slotInventoryFilterRule.b());
        }
        for (SlotInventoryFilterRule slotInventoryFilterRule : this.A()) {
            boolean bl;
            int n;
            int n2 = slotInventoryFilterRule.b();
            Slot targetSlot = container.getSlot(n2);
            ItemStack currentStack = targetSlot.I();
            boolean bl2 = false;
            List<Slot> list2 = this.R(container, targetSlot, slotInventoryFilterRule);
            if (currentStack.isNotNull() && currentStack.getItem().isNotNull()) {
                if (slotInventoryFilterRule.q().h(currentStack) && slotInventoryFilterRule.q(currentStack)) {
                    if (list2.size() <= 1 || targetSlot.g() == list2.get(0).g()) continue;
                    Slot bestSlot = list2.get(0);
                    Comparator<ItemStack> comparator = slotInventoryFilterRule.o().j();
                    if (slotInventoryFilterRule.o().equals(InventoryItemCategoryRegistry.m)) continue;
                    if (comparator != null && (n = comparator.compare(targetSlot.I(), bestSlot.I())) >= 0) {
                        this.K.add(slotInventoryFilterRule.b());
                        continue;
                    }
                }
                ItemInventoryFilterRule itemRule = inventoryCleanerProfile.e(targetSlot.I());
                if (!(slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule) && itemRule != null && itemRule.q(targetSlot.I()) && itemRule.K() == InventoryFilterAction.MOVE) {
                    InventoryClickQueue.Q(targetSlot.g(), container.getWindowId(), this.D);
                }
            }
            if (list2.isEmpty()) continue;
            Slot selectedSlot = list2.get(0);
            if (selectedSlot.equals(targetSlot)) continue;
            this.K.add(slotInventoryFilterRule.b());
            int n3 = 0;
            if (currentStack.isNotNull()) {
                n3 += currentStack.P();
            }
            n = !this.f(targetSlot) ? 1 : 0;
            boolean bl3 = bl = this.Z(slotInventoryFilterRule) && (selectedSlot.g() < 36 || slotInventoryFilterRule instanceof ArmorSlotInventoryFilterRule);
            if (bl) {
                InventoryClickQueue.Q(selectedSlot.g(), container.getWindowId(), this.D);
            } else if (n != 0) {
                InventoryClickQueue.H(selectedSlot.g(), n2, container.getWindowId(), this.D);
            } else {
                InventoryClickQueue.V(selectedSlot.g(), n2, container.getWindowId(), this.D);
            }
            this.K.add(selectedSlot.g());
        }
    }

    public InventoryCleanerProfileValue E$src$Lgg_vape_module_utility_inventory_cleaner_Invent$199cpgr() {
        return this.p;
    }

    private void H(boolean bl) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        ItemStack itemStack = RotationUtil.Z();
        if (!this.P && itemStack.isNotNull() && this.F$src$Z$1v54mv9()) {
            this.J = false;
            return;
        }
        if (this.O.K() == this.k) {
            this.Y(false);
        }
        if (bl && !Minecraft.currentScreen().isNull() && (this.O.K() == this.k || this.t.L().booleanValue())) {
            entityPlayerSP.Z$src$V$1ie832h();
            this.J = false;
        }
        this.D.clear();
        this.c.reset();
        this.K.clear();
        this.J = false;
    }
}
