package gg.vape.module.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.config.ClientSettings;
import gg.vape.event.EventHandler;
import gg.vape.event.impl.EventPrePlayerTick;
import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.utility.autohotbar.AutoHotbarSlotGroup;
import gg.vape.module.utility.inventory.InventoryActionModule;
import gg.vape.unmap.ModeOption;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.value.BooleanValue;
import gg.vape.value.LimitValue;
import gg.vape.value.ModeValue;
import gg.vape.value.RandomValue;
import gg.vape.wrapper.impl.DamageSource;
import gg.vape.wrapper.impl.EnchantmentHelper;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemSplashPotion;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.PotionEffect;
import gg.vape.wrapper.impl.PotionEntry;
import gg.vape.wrapper.impl.PotionRegistry;
import gg.vape.wrapper.impl.Slot;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AutoHotbar
extends Mod
implements InventoryActionModule {
    private final RandomValue J;
    private final LimitValue O;
    private final BooleanValue L;
    private final BooleanValue H;
    private final ModeValue S;
    private final ModeOption C;
    private final TimerUtil a;
    private final BooleanValue r;
    private final BooleanValue k;
    private final BooleanValue V;
    private ItemStack P;
    private ItemStack K;
    private final ModeOption Y;
    private ItemStack[] t;
    private ItemStack F;
    private boolean I;
    private ItemStack p;
    private final Queue<Integer> b = new ArrayDeque<Integer>();
    private static final long v = -3117147329120510770L;

    @Override
    public boolean x() {
        return this.r$src$Z$14eylz9() && this.I && (this.H.L() != false || Minecraft.currentScreen().isNull());
    }

    public AutoHotbar() {
        super("InvCleaner", (int)v, Category.M, "Cleans blacklisted items from your inventory");
        this.a = new TimerUtil();
        this.J = RandomValue.C(this, "Delay", "#", "", 1.0, 100.0, 120.0, 200.0, 1.0);
        this.r = BooleanValue.create(this, "Best Items", true, "Keeps the best set of armor, sword, axe, pickaxe and bow");
        this.V = BooleanValue.create(this, "Remove Negative Potions", true, "Will always throw out negative potions");
        this.L = BooleanValue.create(this, "Remove Food", true, "Remove Food except for Golden Apples");
        this.H = BooleanValue.create(this, "Open Inventory", true, "Opens your inventory when cleaning.");
        this.k = BooleanValue.create(this, "Inventory Only", true, "Only cleans while your inventory is open.");
        this.O = LimitValue.n(this, "invcleaner-blacklisted", "Blacklisted", LimitValue.G, Collections.emptyList());
        this.Y = new ModeOption("On Key");
        this.C = new ModeOption("Toggle");
        this.S = ModeValue.create((Object)this, "Activation", this.Y, this.Y, this.C);
        this.R(false);
        this.S.K(this.H, this.k);
        this.S.L(this.H, this.Y);
        this.S.L(this.k, this.C);
        this.addValue(this.S, this.J, this.H, this.k, this.r, this.V, this.L, this.O);
    }

    private boolean A(ItemStack itemStack) {
        if (!itemStack.getItem().isInstance(MappedClasses.Di)) {
            return false;
        }
        ItemSplashPotion itemSplashPotion = new ItemSplashPotion(itemStack.getItem());
        List<PotionEffect> list = itemSplashPotion.getPotionEffects(itemStack);
        for (PotionEffect potionEffect : list) {
            PotionEntry potionEntry = PotionRegistry.R(potionEffect);
            if (!potionEntry.L()) continue;
            return true;
        }
        return false;
    }

    private ItemStack H(List<Slot> list, Class<?> clazz, Comparator<ItemStack> comparator) {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        for (Slot slot : list) {
            ItemStack itemStack;
            if (!slot.v() || (itemStack = slot.I()).isNull() || !itemStack.getItem().isInstance(clazz)) continue;
            arrayList.add(itemStack);
        }
        Collections.reverse(arrayList);
        arrayList.sort(comparator);
        Collections.reverse(arrayList);
        return arrayList.isEmpty() ? null : (ItemStack)arrayList.get(0);
    }

    private boolean T(ItemStack itemStack) {
        int n;
        Item item = itemStack.getItem();
        if (ItemStackScoreUtil.R(item) && this.t[n = ItemStackScoreUtil.t(itemStack)] != null && !this.t[n].equals(itemStack)) {
            return true;
        }
        n = 1;
        n = this.r.L() != false ? (item.isInstance(MappedClasses.Vl) && !this.P.equals(itemStack) || item.isInstance(MappedClasses.DU) && !this.F.equals(itemStack) || ItemStackScoreUtil.h(item) && !this.K.equals(itemStack) || item.isInstance(MappedClasses.YP) && !this.p.equals(itemStack) ? 1 : 0) : 0;
        return this.O.isValid(itemStack, true) || n != 0 || this.L.L() != false && item.isInstance(MappedClasses.DL) && !item.isInstance(MappedClasses.q3) || this.V.L() != false && item.isInstance(MappedClasses.Di) && this.A(itemStack);
    }

    @Override
    public void loadJson(JsonObject jsonObject) {
        super.loadJson(jsonObject);
        if (jsonObject.get("blacklisted-items") != null) {
            JsonArray jsonArray = jsonObject.get("blacklisted-items").getAsJsonArray();
            JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("id", this.O.P$src$Ljava_lang_String_$1ijjhmj());
            jsonObject2.add("value", (JsonElement)jsonArray);
            this.O.loadJson(jsonObject2);
        }
    }

    @Override
    public void I() {
        this.O.Z("280", -1);
        this.O.Z("287", -1);
        this.O.Z("318", -1);
        this.O.Z("345", -1);
        this.O.Z("288", -1);
        this.O.Z("374", -1);
        this.O.Z("116", -1);
        this.O.Z("54", -1);
        this.O.Z("145", -1);
    }

    @Override
    public boolean X() {
        return this.S.K() == this.Y;
    }

    private void H(EntityPlayerSP entityPlayerSP) {
        if (Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            entityPlayerSP.Z$src$V$1ie832h();
        }
    }

    public static double I(ItemStack itemStack) {
        double d = AutoHotbar.x(itemStack);
        d += (double)EnchantmentHelper.q(32, itemStack);
        d += (double)EnchantmentHelper.q(16, itemStack);
        d += (double)EnchantmentHelper.q(19, itemStack);
        d += (double)EnchantmentHelper.q(20, itemStack);
        d += (double)EnchantmentHelper.q(48, itemStack);
        return d += (double)EnchantmentHelper.q(34, itemStack);
    }

    public LimitValue A() {
        return this.O;
    }

    private boolean g$src$Z$1qmj2fq() {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (!Minecraft.currentScreen().isInstance(MappedClasses.Ft) && this.H.L().booleanValue() && this.S.K() == this.Y) {
            KeyBinding keyBinding = Minecraft.gameSettings().j();
            if (ForgeVersion.MC_1_16_5.d()) {
                KeyBindingHelper.a(keyBinding);
            } else {
                KeyBindingHelper.d(keyBinding, true);
                KeyBindingHelper.v(keyBinding, false, false);
            }
            return false;
        }
        if (entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().isNull()) {
            return false;
        }
        List<Slot> list = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getInventorySlots();
        list.sort(Comparator.comparingInt(this::x));
        this.t = this.m();
        this.K = this.H(list, MappedClasses.V5, Comparator.comparingDouble(ClientSettings::U));
        this.F = this.H(list, MappedClasses.DU, Comparator.comparingDouble(ClientSettings::X));
        this.P = this.H(list, MappedClasses.Vl, Comparator.comparingDouble(ClientSettings::c));
        this.p = this.H(list, MappedClasses.YP, Comparator.comparingDouble(ClientSettings::U));
        Map<Object, AutoHotbarSlotGroup> hashMap = new HashMap<Object, AutoHotbarSlotGroup>();
        block4: for (Slot object : entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getInventorySlots()) {
            try {
                if (!object.v() || object.I().isNull()) continue;
                for (Object object2 : entityPlayerSP.V$src$Lgg_vape_wrapper_impl_InventoryPlayer_$erqak6().i()) {
                    if (object2 != null && object2.equals(object.I())) continue block4;
                }
                if (!this.T(object.I())) continue;
                this.q(object.g());
            }
            catch (Exception exception) {
                Vape.logThrowable(exception);
            }
        }
        try {
            for (Map.Entry<Object, AutoHotbarSlotGroup> entry : hashMap.entrySet()) {
                AutoHotbarSlotGroup autoHotbarSlotGroup = entry.getValue();
                autoHotbarSlotGroup.W();
                List<Integer> list2 = autoHotbarSlotGroup.r();
                if (list2.size() <= 0) continue;
                for (int i = AutoHotbarSlotGroup.l(autoHotbarSlotGroup); i < list2.size(); ++i) {
                    if (i <= 0) continue;
                    this.q(list2.get(i));
                }
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
        return true;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private int x(Slot slot) {
        int n = slot.g();
        if (n >= 36 && n <= 44) {
            return 0;
        }
        if (n >= 9 && n <= 17) {
            return 1;
        }
        if (n >= 18 && n <= 26) {
            return 2;
        }
        return 3;
    }

    private ItemStack[] m() {
        ItemStack[] itemStackArray = new ItemStack[4];
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        List<Slot> list = Minecraft.thePlayer().F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getInventorySlots();
        for (Slot wrapper : list) {
            if (!wrapper.v() || !ItemStackScoreUtil.R(wrapper.I().getItem())) continue;
            arrayList.add(wrapper.I());
        }
        for (ItemStack itemStack : arrayList) {
            int n = ItemStackScoreUtil.t(itemStack);
            ItemStack itemStack2 = itemStackArray[n];
            if (itemStack2 != null && !(AutoHotbar.x(itemStack) > AutoHotbar.x(itemStack2))) continue;
            itemStackArray[n] = itemStack;
        }
        return itemStackArray;
    }

    private static double x(ItemStack itemStack) {
        int n = 0;
        if (itemStack.isNull()) {
            return n;
        }
        if (itemStack.getItem().isNotNull() && ItemStackScoreUtil.R(itemStack.getItem())) {
            n = (int)ItemStackScoreUtil.P(itemStack);
        }
        return n += EnchantmentHelper.B(new ItemStack[]{itemStack}, DamageSource.C(Minecraft.thePlayer()));
    }

    @Override
    public void onEnable() {
        this.b.clear();
        this.I = false;
    }

    private void q(int n) {
        if (this.b.contains(n)) {
            return;
        }
        this.b.add(n);
        this.b.add(-999);
        this.I = true;
    }

    @EventHandler
    public void onTick(EventPrePlayerTick eventPrePlayerTick) {
        if (Vape.INSTANCE.getModManager().N(AutoHotbar.class) || Vape.INSTANCE.getClientSettings().J$src$Z$c57s1l()) {
            this.I = false;
            return;
        }
        EntityPlayerSP entityPlayerSP = eventPrePlayerTick.getThePlayer();
        if (!this.I) {
            if (this.g$src$Z$1qmj2fq() && !this.I && this.S.K() == this.Y) {
                this.Y(false);
                if (this.H.L().booleanValue()) {
                    this.H(entityPlayerSP);
                }
            }
            return;
        }
        if (this.S.K() == this.C && this.k.L().booleanValue() && (!Minecraft.currentScreen().isInstance(MappedClasses.Ft) || entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode())) {
            return;
        }
        if (this.I && this.H.L().booleanValue() && !Minecraft.currentScreen().isInstance(MappedClasses.Ft)) {
            this.F();
            return;
        }
        if (!this.b.isEmpty()) {
            if (this.a.hasTimeElapsed((long)this.J.B())) {
                this.a.reset();
                int n = this.b.poll();
                Minecraft.playerController().O(entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm().getWindowId(), n, 0, 0, entityPlayerSP);
            }
            return;
        }
        if (this.S.K() == this.Y) {
            this.Y(false);
            if (this.H.L().booleanValue()) {
                this.H(entityPlayerSP);
            }
        } else {
            this.I = false;
        }
        if (entityPlayerSP.C$src$Lgg_vape_wrapper_impl_ModelPlayer_$19uhx86().isCreativeMode() && Minecraft.currentScreen().isInstance(MappedClasses.Ft) && this.S.K() == this.Y) {
            this.Y(false);
        }
    }
}
