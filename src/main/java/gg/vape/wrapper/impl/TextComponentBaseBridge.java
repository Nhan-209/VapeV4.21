package gg.vape.wrapper.impl;

import gg.vape.Vape;
import gg.vape.unmap.TextComponentBase;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.StringTextComponentBase;
import java.util.List;
import java.util.stream.Collectors;

public class TextComponentBaseBridge
extends ITextComponent {
    public TextComponentBaseBridge(Object object) {
        super(object);
    }

    public TextComponentBaseBridge N(TextComponentBase textComponentBase) {
        if (ForgeVersion.MC_1_20_6.v()) {
            Vape.notifyNativeStackTrace();
            throw new UnsupportedOperationException("Unsupported");
        }
        return new TextComponentBaseBridge(TextComponentBaseBridge.c.getMappings().Cp.X(this.getObject(), textComponentBase.getObject()));
    }

    private static UnsupportedOperationException b(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
    }

    public static TextComponentBaseBridge l(StringTextComponentBase stringTextComponentBase, List<ITextComponent> list, TextComponentBase textComponentBase) {
        if (ForgeVersion.MC_1_20_6.v()) {
            Vape.notifyNativeStackTrace();
            throw new UnsupportedOperationException("Unsupported");
        }
        return new TextComponentBaseBridge(TextComponentBaseBridge.c.getMappings().Cp.Q(stringTextComponentBase.getObject(), list.stream().map(Wrapper::getObject).collect(Collectors.toList()), textComponentBase.getObject()));
    }
}

