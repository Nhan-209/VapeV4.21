package gg.vape.module.utility.inventory;

import gg.vape.input.KeyBindingHelper;
import gg.vape.mapping.MappedClasses;
import gg.vape.module.utility.MLGImpactState;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.Container;
import gg.vape.wrapper.impl.EntityPlayerSP;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.Slot;
import java.util.function.Predicate;
import org.jetbrains.annotations.Nullable;

public class ItemStackActionPredicate {
    @Nullable
    public static Slot K(Class clazz, MLGImpactState mLGImpactState) {
        return ItemStackActionPredicate.a(arg_0 -> ItemStackActionPredicate.lambda$findSlot$0(clazz, arg_0), mLGImpactState);
    }

    private static boolean lambda$findSlot$0(Class clazz, Slot slot) {
        return slot.isNotNull() && slot.I().isNotNull() && slot.I().getItem().isInstance(clazz);
    }

    @Nullable
    public static Slot a(Predicate<Slot> predicate, MLGImpactState mLGImpactState) {
        EntityPlayerSP entityPlayerSP = Minecraft.thePlayer();
        if (entityPlayerSP.isNull()) {
            return null;
        }
        Container container = entityPlayerSP.F$src$Lgg_vape_wrapper_impl_Container_$152y6lm();
        if (container.isNull()) {
            return null;
        }
        return ItemStackActionPredicate.c(container, predicate, mLGImpactState.J(), mLGImpactState.u());
    }

    public static boolean o() {
        return Minecraft.currentScreen().isInstance(MappedClasses.YS);
    }

    public static boolean V() {
        if (!ItemStackActionPredicate.o()) {
            KeyBindingHelper.v(Minecraft.gameSettings().j(), true, true);
            KeyBindingHelper.v(Minecraft.gameSettings().j(), false, false);
            return true;
        }
        return false;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static boolean L() {
        return Minecraft.currentScreen().isNotNull();
    }

    @Nullable
    public static Slot c(Container container, Predicate<Slot> predicate, int n, int n2) {
        for (Slot slot : container.getInventorySlots()) {
            int n3 = slot.g();
            if (n3 < n || n3 > n2 || !predicate.test(slot)) continue;
            return slot;
        }
        return null;
    }

    public static boolean f() {
        if (ItemStackActionPredicate.L()) {
            if (Minecraft.currentScreen().isInstance(MappedClasses.D2)) {
                return false;
            }
            Minecraft.thePlayer().Z$src$V$1ie832h();
            return true;
        }
        return false;
    }
}

