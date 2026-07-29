package gg.vape.module.utility.inventory;

import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.inventory.HotbarSlotRule;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemPickerFrame;
import gg.vape.module.utility.inventory.HotbarSlotRuleItemSelectClickHandler;
import gg.vape.module.utility.inventory.ItemStackIconButtonComponent;
import gg.vape.ui.click.frame.InsetFrameBase;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.NonNullList;
import gg.vape.wrapper.impl.ResourceLocation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class HotbarSlotRuleItemListFrame
extends InsetFrameBase {
    public static final List<ItemStack> Oq = new ArrayList<ItemStack>();
    private HotbarSlotRuleItemPickerFrame OM;

    public HotbarSlotRuleItemListFrame(HotbarSlotRuleItemPickerFrame hotbarSlotRuleItemPickerFrame) {
        this.OM = hotbarSlotRuleItemPickerFrame;
        this.I2 = false;
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().I(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().u(false);
        this.setVisible(true);
        this.p();
    }

    public List<ItemStack> n$src$Ljava_util_List_$re8j0m() {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        for (ItemStack itemStack : ItemStackScoreUtil.S()) {
            if (!itemStack.x().toLowerCase(Locale.ROOT).contains(this.OM.E$src$Ljava_lang_String_$ous8w6().toLowerCase(Locale.ROOT))) continue;
            arrayList.add(itemStack);
        }
        return arrayList;
    }

    @Override
    public double L() {
        return 110.0;
    }

    @Override
    public double A() {
        return super.A();
    }


    @Override
    public void Y() {
    }

    static {
        HotbarSlotRuleItemListFrame.N$src$V$199jj6x();
    }

    @Override
    public void c() {
        super.c();
        this.t(this.L());
    }

    public void p() {
        this.removeMarkedChildren();
        List<ItemStack> list = this.OM.E$src$Ljava_lang_String_$ous8w6() != null && this.OM.E$src$Ljava_lang_String_$ous8w6().length() > 0 ? this.n$src$Ljava_util_List_$re8j0m() : Oq;
        for (int i = 0; i < list.size(); ++i) {
            int n = i;
            this.h(new ItemStackIconButtonComponent(HotbarSlotRuleItemListFrame.J.m, HotbarSlotRuleItemListFrame.J.l, list.get(i)).addClickListener(new HotbarSlotRuleItemSelectClickHandler(this, list, n)), i > 0 && (i + 1) % 9 == 0 ? "wrap" : "");
        }
    }

    static HotbarSlotRuleItemPickerFrame a(HotbarSlotRuleItemListFrame hotbarSlotRuleItemListFrame) {
        return hotbarSlotRuleItemListFrame.OM;
    }

    private static void h() {
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList("heal", "regen", "swift", "fire", "poison"));
        for (Object e : Item.Y().D()) {
            Wrapper wrapper;
            Object object;
            if (ForgeVersion.MC_1_7_10.L()) {
                object = Item.Y().S(e);
            } else {
                wrapper = new ResourceLocation(e);
                object = Item.Y().S(wrapper.getObject());
            }
            if (!MappedClasses.lb.isInstance(object)) continue;
            wrapper = new Item(object);
            List list = new ArrayList();
            if (ForgeVersion.MC_1_12_2.d()) {
                list = (List)NonNullList.F().getObject();
            }
            ((Item)wrapper).D((Item)wrapper, list);
            block1: for (Object e2 : list) {
                String string;
                ItemStack itemStack = new ItemStack(e2);
                if (itemStack.getItem().P() == 62 || !(string = itemStack.x().toLowerCase(Locale.ROOT)).contains("splash")) continue;
                for (String string2 : arrayList) {
                    if (!string.contains(string2)) continue;
                    Oq.add(itemStack);
                    arrayList.remove(string2);
                    continue block1;
                }
            }
        }
    }

    @Override
    public String getName() {
        return "hotbarbrowser";
    }

    @Override
    public void v() {
    }

    private static void N$src$V$199jj6x() {
        if (ForgeVersion.MC_1_21_4.d()) {
            Oq.add(new HotbarSlotRule(869).createItemStack());
            Oq.add(new HotbarSlotRule(871).createItemStack());
            Oq.add(new HotbarSlotRule(872).createItemStack());
            Oq.add(new HotbarSlotRule(832).createItemStack());
            Oq.add(new HotbarSlotRule(1038).createItemStack());
            Oq.add(new HotbarSlotRule(1042).createItemStack());
            Oq.add(new HotbarSlotRule(945).createItemStack());
            Oq.add(new HotbarSlotRule(960).createItemStack());
            Oq.add(new HotbarSlotRule(980).createItemStack());
            Oq.add(new HotbarSlotRule(915).createItemStack());
            Oq.add(new HotbarSlotRule(916).createItemStack());
            Oq.add(new HotbarSlotRule(880).createItemStack());
            Oq.add(new HotbarSlotRule(662).createItemStack());
            HotbarSlotRuleItemListFrame.h();
            Oq.add(new HotbarSlotRule(615).createItemStack());
            Oq.add(new HotbarSlotRule(1).createItemStack());
        } else if (ForgeVersion.MC_1_16_5.d()) {
            Oq.add(new HotbarSlotRule(603).createItemStack());
            Oq.add(new HotbarSlotRule(605).createItemStack());
            Oq.add(new HotbarSlotRule(606).createItemStack());
            Oq.add(new HotbarSlotRule(574).createItemStack());
            Oq.add(new HotbarSlotRule(740).createItemStack());
            Oq.add(new HotbarSlotRule(744).createItemStack());
            Oq.add(new HotbarSlotRule(666).createItemStack());
            Oq.add(new HotbarSlotRule(682).createItemStack());
            Oq.add(new HotbarSlotRule(684).createItemStack());
            Oq.add(new HotbarSlotRule(650).createItemStack());
            Oq.add(new HotbarSlotRule(651).createItemStack());
            Oq.add(new HotbarSlotRule(661).createItemStack());
            Oq.add(new HotbarSlotRule(662).createItemStack());
            HotbarSlotRuleItemListFrame.h();
            Oq.add(new HotbarSlotRule(615).createItemStack());
            Oq.add(new HotbarSlotRule(1).createItemStack());
        } else {
            Oq.add(new HotbarSlotRule(276).createItemStack());
            Oq.add(new HotbarSlotRule(278).createItemStack());
            Oq.add(new HotbarSlotRule(279).createItemStack());
            Oq.add(new HotbarSlotRule(261).createItemStack());
            Oq.add(new HotbarSlotRule(364).createItemStack());
            Oq.add(new HotbarSlotRule(368).createItemStack());
            Oq.add(new HotbarSlotRule(332).createItemStack());
            Oq.add(new HotbarSlotRule(344).createItemStack());
            Oq.add(new HotbarSlotRule(346).createItemStack());
            Oq.add(new HotbarSlotRule(322).createItemStack());
            Oq.add(new HotbarSlotRule(322).setMetadata(1).createItemStack());
            Oq.add(new HotbarSlotRule(326).createItemStack());
            Oq.add(new HotbarSlotRule(327).createItemStack());
            if (ForgeVersion.MC_1_12_2.d()) {
                HotbarSlotRuleItemListFrame.h();
            } else {
                Oq.add(new HotbarSlotRule(373).setMetadata(8259).createItemStack());
                Oq.add(new HotbarSlotRule(373).setMetadata(8258).createItemStack());
                Oq.add(new HotbarSlotRule(373).setMetadata(16421).createItemStack());
                Oq.add(new HotbarSlotRule(373).setMetadata(16388).createItemStack());
                Oq.add(new HotbarSlotRule(373).setMetadata(16449).createItemStack());
            }
            Oq.add(new HotbarSlotRule(282).createItemStack());
            Oq.add(new HotbarSlotRule(1).createItemStack());
        }
    }
}

