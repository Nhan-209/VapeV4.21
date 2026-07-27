package gg.vape.mapping;

import gg.vape.mapping.MappingMemberBuilder;
import gg.vape.mapping.MappingMethod;
import gg.vape.module.MinecraftVersionConstraint;
import java.util.HashMap;
import java.util.Map;

public class MappingMethodBuilder
extends MappingMemberBuilder<MappingMethodBuilder, MappingMethod> {
    private boolean E;
    private Class<?>[] B;
    private final Map<MinecraftVersionConstraint, Class[]> Q = new HashMap<MinecraftVersionConstraint, Class[]>();

    public MappingMethodBuilder Q(boolean bl) {
        this.E = bl;
        return this;
    }

    public boolean o() {
        return this.E;
    }


    public Class<?>[] t() {
        if (!this.Q.isEmpty()) {
            for (Map.Entry<MinecraftVersionConstraint, Class[]> entry : this.Q.entrySet()) {
                if (!entry.getKey().y()) continue;
                return entry.getValue();
            }
        }
        return this.B;
    }

    public MappingMethodBuilder v(MinecraftVersionConstraint minecraftVersionConstraint, Class ... classArray) {
        this.Q.put(minecraftVersionConstraint, classArray);
        return this;
    }

    public MappingMethod s() {
        if (this.c()) {
            return null;
        }
        return MappingMethod.G(this);
    }

    @Override
    public MappingMethod F() {
        return this.s();
    }

    public MappingMethodBuilder G(Class<?>[] classArray) {
        this.B = classArray;
        return this;
    }
}
