package gg.vape.utils.render;

import gg.vape.mapping.MappedClasses;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityOtherPlayerMP;
import java.util.Objects;

public class EntityModelRenderCacheKey {
    private final int b;
    boolean V = false;

    public int u() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (object instanceof EntityModelRenderCacheKey) {
            boolean bl = ((EntityModelRenderCacheKey)object).u() == this.u();
            return bl;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.u());
    }

    public EntityModelRenderCacheKey(EntityLivingBase entityLivingBase) {
        if (entityLivingBase == null) {
            this.b = 0;
            return;
        }
        if (entityLivingBase.isInstance(MappedClasses.lG)) {
            EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(entityLivingBase);
            this.b = entityOtherPlayerMP.c$src$Lgg_vape_wrapper_impl_GameProfile_$ir8937().getObject().hashCode();
        } else {
            this.b = entityLivingBase.getObject().getClass().hashCode();
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public EntityModelRenderCacheKey(String string) {
        this.b = string != null ? string.hashCode() : 0;
    }
}

