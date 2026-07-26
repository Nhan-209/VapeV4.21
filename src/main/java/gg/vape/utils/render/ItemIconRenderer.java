package gg.vape.utils.render;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.utils.ItemStackScoreUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ItemIcon;
import gg.vape.utils.render.ItemIconKey;
import gg.vape.utils.render.ItemIconRenderBackend;
import gg.vape.utils.render.Post117ItemIconFramebufferRenderer;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemArmor;
import gg.vape.wrapper.impl.ItemStack;
import java.util.HashMap;

public class ItemIconRenderer {
    private static String[] W;
    static HashMap<ItemIconKey, ItemIconRenderBackend> y;

    public static void j(ItemStack itemStack) {
        Item item = itemStack.getItem();
        ItemIconRenderer.c(item.P(), ItemIconRenderer.u(itemStack, item), 1.0f);
    }

    public static String[] S() {
        return W;
    }

    public static void m(int n, int n2, float f, float f2, int n3, int n4) {
        ItemIconRenderer.q(n, n2, f, f2, n3, n4, 1.0f, 1.0f, false);
    }

    private static ItemIconKey i(int n, int n2, float f, ItemStack itemStack) {
        Item item;
        ItemIconKey itemIconKey = new ItemIconKey(n, n2, f);
        if (itemStack != null && ItemStackScoreUtil.R(item = itemStack.getItem()) && ForgeVersion.MC_1_8_9.B()) {
            ItemArmor itemArmor = new ItemArmor(item.getObject());
            itemIconKey.a(itemArmor.Y(itemStack));
        }
        return itemIconKey;
    }

    public static void Q(int n, int n2, float f, float f2, int n3, int n4, float f3) {
        ItemIconRenderer.q(n, n2, f, f2, n3, n4, f3, 1.0f, false);
    }

    public static void d(ItemStack itemStack, float f, float f2, int n, int n2, float f3, float f4) {
        ItemIconRenderer.j(itemStack, null, f, f2, n, n2, f3, f4, false);
    }

    public static void m(int n, int n2, float f, float f2, int n3, int n4, float f3, boolean bl) {
        ItemIconRenderer.q(n, n2, f, f2, n3, n4, f3, 1.0f, bl);
    }

    public static void C(ItemStack itemStack, float f, float f2, int n, int n2, float f3, boolean bl) {
        ItemIconRenderer.j(itemStack, null, f, f2, n, n2, f3, 1.0f, bl);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static void R(ItemStack itemStack, float f, float f2, int n, int n2) {
        ItemIconRenderer.j(itemStack, null, f, f2, n, n2, 1.0f, 1.0f, false);
    }

    public static void q(int n, int n2, float f, float f2, int n3, int n4, float f3, float f4, boolean bl) {
        ItemStack itemStack = ItemStack.S(Item.T(n));
        itemStack.s(n2);
        ItemIconRenderer.l(n, n2, itemStack, f, f2, n3, n4, f3, f4, bl);
    }

    public static void t() {
        for (ItemIconRenderBackend itemIconRenderBackend : y.values()) {
            itemIconRenderBackend.e();
        }
        y.clear();
    }

    private static ItemIconRenderBackend F(ItemStack itemStack, ItemIconKey itemIconKey) {
        ItemIconRenderBackend itemIconRenderBackend = GuiRenderPrimitives.d() ? new Post117ItemIconFramebufferRenderer() : new ItemIcon();
        itemIconRenderBackend.N(itemStack, itemIconKey.w());
        y.put(itemIconKey, itemIconRenderBackend);
        return itemIconRenderBackend;
    }

    public static void C(ItemStack itemStack, float f, float f2, int n, int n2, float f3) {
        ItemIconRenderer.j(itemStack, null, f, f2, n, n2, f3, 1.0f, false);
    }

    private static int u(ItemStack itemStack, Item item) {
        if (ItemStackScoreUtil.I(item) || ItemStackScoreUtil.R(item)) {
            return 0;
        }
        return itemStack.L();
    }

    public static void K(String[] stringArray) {
        W = stringArray;
    }

    public static ItemIconRenderBackend C(ItemIconKey itemIconKey, ItemStack itemStack) {
        if (!y.containsKey(itemIconKey)) {
            return ItemIconRenderer.F(itemStack, itemIconKey);
        }
        return null;
    }

    public static void j(ItemStack itemStack, Item item, float f, float f2, int n, int n2, float f3, float f4, boolean bl) {
        if (itemStack == null) {
            return;
        }
        if (item == null) {
            item = itemStack.getItem();
        }
        int n3 = item.P();
        int n4 = ItemIconRenderer.u(itemStack, item);
        ItemIconRenderer.l(n3, n4, itemStack, f, f2, n, n2, f3, f4, bl);
    }

    public static void k(int n, int n2) {
        ItemIconRenderer.c(n, n2, 1.0f);
    }

    public static ItemIconRenderBackend c(int n, int n2, float f) {
        ItemIconKey itemIconKey = new ItemIconKey(n, n2, f);
        if (!y.containsKey(itemIconKey)) {
            Item item = Item.T(n);
            if (item.isNull()) {
                return null;
            }
            ItemStack itemStack = ItemStack.S(item);
            itemStack.s(n2);
            return ItemIconRenderer.F(itemStack, itemIconKey);
        }
        return null;
    }

    public static void x(int n, int n2, float f, float f2, int n3, int n4, boolean bl) {
        ItemIconRenderer.q(n, n2, f, f2, n3, n4, 1.0f, 1.0f, bl);
    }

    private static void l(int n, int n2, ItemStack itemStack, float f, float f2, int n3, int n4, float f3, float f4, boolean bl) {
        ItemIconKey itemIconKey = ItemIconRenderer.i(n, n2, f4, itemStack);
        ItemIconRenderBackend itemIconRenderBackend = y.get(itemIconKey);
        if (itemIconRenderBackend != null) {
            itemIconRenderBackend.s(f, f2, n3, n4, f3, bl);
            return;
        }
        ItemIconRenderBackend itemIconRenderBackend2 = ItemIconRenderer.C(itemIconKey, itemStack);
        if (itemIconRenderBackend2 != null) {
            itemIconRenderBackend2.s(f, f2, n3, n4, f3, bl);
        }
    }

    static {
        y = new HashMap();
        ItemIconRenderer.K(new String[5]);
    }
}

