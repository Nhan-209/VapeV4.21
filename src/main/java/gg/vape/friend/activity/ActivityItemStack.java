package gg.vape.friend.activity;

import gg.vape.friend.activity.ActivityItemStackPayload;
import gg.vape.utils.EnchantmentUtil;
import gg.vape.wrapper.impl.Enchantment;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class ActivityItemStack {
    private final int S;
    private final Map<Short, Short> J;
    private final int g;
    private final int q;

    public ActivityItemStack(int n, int n2, int n3, Map<Short, Short> map) {
        this.g = n;
        this.S = n2;
        this.q = n3;
        this.J = map;
    }

    public boolean O() {
        boolean bl = !this.J.isEmpty();
        return bl;
    }


    public int I() {
        return this.g;
    }

    public int k() {
        return this.q;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        ActivityItemStack activityItemStack = (ActivityItemStack)object;
        if (this.g != activityItemStack.g) {
            return false;
        }
        if (this.S != activityItemStack.S) {
            return false;
        }
        if (this.q != activityItemStack.q) {
            return false;
        }
        boolean bl = this.J.size() == activityItemStack.J.size() && this.J.equals(activityItemStack.J);
        return bl;
    }

    public int n() {
        return this.S;
    }

    public Map<Short, Short> E() {
        return this.J;
    }

    public ActivityItemStackPayload W() {
        return new ActivityItemStackPayload(this.g, this.S, this.q, this.J);
    }

    public int hashCode() {
        int n = this.g;
        n = 31 * n + this.S;
        n = 31 * n + this.q;
        n = 31 * n + this.J.hashCode();
        return n;
    }

    @Nullable
    public static ActivityItemStack C(@Nullable ActivityItemStackPayload activityItemStackPayload) {
        if (activityItemStackPayload == null || activityItemStackPayload.E() == 0) {
            return null;
        }
        return new ActivityItemStack(activityItemStackPayload.E(), activityItemStackPayload.P(), activityItemStackPayload.n(), activityItemStackPayload.p());
    }

    @Nullable
    public ItemStack T() {
        if (this.g == 0) {
            return null;
        }
        Item item = Item.T(this.g);
        if (item.isNotNull()) {
            ItemStack itemStack = ItemStack.S(item);
            itemStack.s(this.q);
            itemStack.Y(this.S);
            if (!this.J.isEmpty()) {
                for (Map.Entry<Short, Short> entry : this.J.entrySet()) {
                    Enchantment enchantment = EnchantmentUtil.k(entry.getKey());
                    if (enchantment == null) continue;
                    itemStack.v(enchantment, entry.getValue().shortValue());
                }
            }
            return itemStack;
        }
        return null;
    }
}

