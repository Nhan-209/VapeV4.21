package gg.vape.input;

import gg.vape.utils.AxisAlignedBBDistanceComparator;
import gg.vape.utils.datas.BlockData;
import gg.vape.wrapper.impl.AxisAlignedBB;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.KeyBinding;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyBindingHelper {
    public static void v(KeyBinding keyBinding, boolean bl, boolean bl2) {
        KeyBinding.setKeyBindState(keyBinding, bl);
        if (bl2) {
            KeyBinding.onTick(keyBinding);
        }
    }

    public static void a(KeyBinding keyBinding) {
        keyBinding.onTick(1);
    }

    public static BlockData Y() {
        AxisAlignedBB axisAlignedBB;
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        if (ForgeVersion.MC_1_8_9.d()) {
            axisAlignedBB = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
        } else {
            AxisAlignedBB axisAlignedBB2 = entityPlayerSP.R$src$Lgg_vape_wrapper_impl_AxisAlignedBB_$r19dfl();
            axisAlignedBB = axisAlignedBB2.copy();
        }
        double d = ForgeVersion.MC_1_20_6.d() ? 1.0 : -1.0;
        AxisAlignedBB axisAlignedBB3 = axisAlignedBB.k(0.0, d, 0.0);
        List list = Minecraft.theWorld().i(entityPlayerSP, axisAlignedBB3);
        ArrayList<AxisAlignedBB> arrayList = new ArrayList<AxisAlignedBB>();
        for (Object e : list) {
            arrayList.add(new AxisAlignedBB(e));
        }
        if (arrayList.size() == 0) {
            return null;
        }
        if (arrayList.size() == 1) {
            AxisAlignedBB axisAlignedBB4 = (AxisAlignedBB)arrayList.get(0);
            return BlockData.P(axisAlignedBB4);
        }
        Collections.sort(arrayList, new AxisAlignedBBDistanceComparator(entityPlayerSP));
        return BlockData.P((AxisAlignedBB)arrayList.get(0));
    }

    public static void d(KeyBinding keyBinding, boolean bl) {
        KeyBindingHelper.v(keyBinding, bl, true);
    }


    public static void k(KeyBinding keyBinding, boolean bl) {
        if (ForgeVersion.MC_1_21_4.v()) {
            KeyBindingHelper.v(keyBinding, bl, bl);
            return;
        }
        Minecraft.s().L(Minecraft.p().e$src$J$14hgru1(), keyBinding.getKeyCode(), bl);
    }
}

