package gg.vape.module.world;

import gg.vape.Vape;
import gg.vape.mapping.mappings.MTextComponentTranslation;
import gg.vape.unmap.TextComponentBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.TextComponent;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class ChestStealInventoryState
extends TextComponentBase {
    private static final String UNSUPPORTED_MESSAGE = "This method is available on 1.20.6 and later.";

    public static ChestStealInventoryState v(String string, Object ... objectArray) {
        return new ChestStealInventoryState(MTextComponentTranslation.B(ChestStealInventoryState.c.getMappings().D_, string, objectArray));
    }

    public String U() {
        if (ForgeVersion.MC_1_16_5.v()) {
            Vape.notifyNativeStackTrace();
        }
        return MTextComponentTranslation.C(ChestStealInventoryState.c.getMappings().D_, this.getObject());
    }

    public String p() {
        if (ForgeVersion.MC_1_20_6.v()) {
            Vape.notifyNativeStackTrace();
        }
        return MTextComponentTranslation.a(ChestStealInventoryState.c.getMappings().D_, this.getObject());
    }

    public static ChestStealInventoryState A(String string, @Nullable String string2, Object[] objectArray) {
        if (ForgeVersion.MC_1_20_6.v()) {
            Vape.notifyNativeStackTrace();
            throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
        }
        return new ChestStealInventoryState(MTextComponentTranslation.k(ChestStealInventoryState.c.getMappings().D_, string, string2, objectArray));
    }

    @Override
    public String C() {
        if (ForgeVersion.MC_1_20_6.d()) {
            TextComponent textComponent = TextComponent.p(this.I);
            return textComponent.C();
        }
        return super.C();
    }

    public Object[] c() {
        if (ForgeVersion.MC_1_16_5.v()) {
            Vape.notifyNativeStackTrace();
        }
        return MTextComponentTranslation.x(ChestStealInventoryState.c.getMappings().D_, this.getObject());
    }

    private static UnsupportedOperationException b(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
    }

    public ChestStealInventoryState(Object object) {
        super(object);
    }

    public List b() {
        return ChestStealInventoryState.c.getMappings().D_.k(this.getObject());
    }
}

