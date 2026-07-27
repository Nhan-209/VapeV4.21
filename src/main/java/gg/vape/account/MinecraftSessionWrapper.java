package gg.vape.account;

import gg.vape.mapping.mappings.MSession;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.WorldRendererBuilder;
import java.util.Optional;
import java.util.UUID;

public class MinecraftSessionWrapper
extends Wrapper {

    public static MinecraftSessionWrapper J(String string, UUID uUID, String string2, Optional<String> optional, Optional<String> optional2, WorldRendererBuilder worldRendererBuilder) {
        return new MinecraftSessionWrapper(MSession.B(MinecraftSessionWrapper.c.getMappings().hw, string, uUID, string2, optional, optional2, worldRendererBuilder.getObject()));
    }

    public MinecraftSessionWrapper(Object object) {
        super(object);
    }

    public String M() {
        return MinecraftSessionWrapper.c.getMappings().hw.s(this.I);
    }

    public UUID R() {
        if (ForgeVersion.MC_1_20_6.d()) {
            return (UUID)MinecraftSessionWrapper.c.getMappings().hw.V(this.I);
        }
        String string = (String)MinecraftSessionWrapper.c.getMappings().hw.V(this.I);
        if (string == null || string.isEmpty()) {
            return UUID.fromString("00000000-0000-0000-0000-000000000000");
        }
        return UUID.fromString(string.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
    }

    public static MinecraftSessionWrapper U(String string, String string2, String string3, String string4) {
        return new MinecraftSessionWrapper(MSession.t(MinecraftSessionWrapper.c.getMappings().hw, string, string2, string3, string4));
    }
}

