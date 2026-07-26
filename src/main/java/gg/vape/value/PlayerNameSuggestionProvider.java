package gg.vape.value;

import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.value.AbstractListValueSuggestionProvider;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.Minecraft;
import gg.vape.wrapper.impl.WorldClient;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.UnmodifiableView;

public class PlayerNameSuggestionProvider
extends AbstractListValueSuggestionProvider {
    private static ObfuscatedRuntimeException b(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public @UnmodifiableView List<String> getValues() {
        ArrayList<String> arrayList = new ArrayList<String>();
        WorldClient worldClient = Minecraft.theWorld();
        if (worldClient.isNull()) {
            return arrayList;
        }
        for (Object e : worldClient.X()) {
            if (!MappedClasses.Yl.isInstance(e)) continue;
            EntityPlayer entityPlayer = new EntityPlayer(e);
            arrayList.add(entityPlayer.getName());
        }
        return arrayList;
    }
}

