package gg.vape.manager;

import gg.vape.manager.ModManager;
import gg.vape.module.MinecraftVersionConstraint;
import gg.vape.module.Mod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class ModRegistrationBuilder<T extends Mod> {
    private T D;
    @NotNull
    private final List<List<MinecraftVersionConstraint>> T = new ArrayList<List<MinecraftVersionConstraint>>();

    public void e(ModManager modManager) {
        modManager.d((Mod)this.D, this.T, false);
    }

    public ModRegistrationBuilder<T> u(MinecraftVersionConstraint ... gi_1Array) {
        this.T.add(Arrays.asList(gi_1Array));
        return this;
    }

    public static <T extends Mod> ModRegistrationBuilder<T> X() {
        return new ModRegistrationBuilder<T>();
    }

    public ModRegistrationBuilder<T> k(@NotNull List<MinecraftVersionConstraint> list) {
        this.T.add(list);
        return this;
    }

    public ModRegistrationBuilder<T> O(@NotNull T t) {
        this.D = t;
        return this;
    }

    ModRegistrationBuilder() {
    }

    public ModRegistrationBuilder<T> H(@NotNull MinecraftVersionConstraint gi_12) {
        this.T.add(Collections.singletonList(gi_12));
        return this;
    }
}

