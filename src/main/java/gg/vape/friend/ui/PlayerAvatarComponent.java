package gg.vape.friend.ui;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.utils.render.EntityModelRenderCache;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.EntityLivingBase;
import gg.vape.wrapper.impl.EntityPlayer;
import gg.vape.wrapper.impl.GameProfile;
import gg.vape.wrapper.impl.ResourceLocation;
import java.awt.Color;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerAvatarComponent
extends GuiComponent {
    @Nullable
    private EntityLivingBase I;
    private String O;
    @Nullable
    private ResourceLocation Q;
    @Nullable
    private UUID a;

    public String R() {
        return this.O;
    }

    public PlayerAvatarComponent(@Nullable UUID uUID, @NotNull String string, double d, double d2) {
        this.a = uUID;
        this.O = string;
        this.o(d);
        this.Y(d2);
    }

    public void J(@Nullable ResourceLocation resourceLocation) {
        this.Q = resourceLocation;
    }

    @Nullable
    public ResourceLocation O$src$Lgg_vape_wrapper_impl_ResourceLocation_$19a7oud() {
        return this.Q;
    }

    public PlayerAvatarComponent(double d, double d2) {
        this(null, "", d, d2);
    }

    public static PlayerAvatarComponent D(EntityPlayer entityPlayer, double d, double d2) {
        if (entityPlayer.isNull()) {
            return new PlayerAvatarComponent("", d, d2);
        }
        PlayerAvatarComponent playerAvatarComponent = new PlayerAvatarComponent(entityPlayer.X$src$Ljava_util_UUID_$1o5dyg6(), entityPlayer.getName(), d, d2);
        playerAvatarComponent.d(entityPlayer);
        return playerAvatarComponent;
    }

    public void d(@Nullable EntityLivingBase entityLivingBase) {
        this.I = entityLivingBase;
    }

    public PlayerAvatarComponent(@NotNull String string, double d, double d2) {
        this(null, string, d, d2);
    }


    public void O(String string) {
        this.O = string;
    }

    @Override
    public void H() {
        Color color = new Color(100, 100, 100, 70);
        GuiRenderPrimitives.g(this.G$src$D$1b2f02a(), this.n() + 1.0, this.A(), this.L(), 12.0f, 1.0f, color);
        if (this.I != null && this.I.isNotNull()) {
            EntityModelRenderCache.d(this.I, (float)this.G$src$D$1b2f02a(), (float)this.n(), (int)this.A(), (int)this.L(), Color.WHITE, 0.0f);
            return;
        }
        if (this.Q != null && this.Q.isNotNull()) {
            String string = this.O != null && !this.O.isEmpty() ? this.O : "unknown";
            EntityModelRenderCache.N(this.Q, string, (float)this.G$src$D$1b2f02a(), (float)this.n(), (int)this.A(), (int)this.L(), Color.WHITE, 0.0f);
            return;
        }
        ResourceLocation resourceLocation = EntityModelRenderCache.M();
        EntityModelRenderCache.N(resourceLocation, "steve", (float)this.G$src$D$1b2f02a(), (float)this.n(), (int)this.A(), (int)this.L(), Color.WHITE, 0.0f);
    }

    public static PlayerAvatarComponent H(GameProfile gameProfile, double d, double d2) {
        if (gameProfile.isNull()) {
            return new PlayerAvatarComponent("", d, d2);
        }
        return new PlayerAvatarComponent(gameProfile.getUUID(), gameProfile.getName(), d, d2);
    }

    public static PlayerAvatarComponent q(ResourceLocation resourceLocation, String string, double d, double d2) {
        PlayerAvatarComponent playerAvatarComponent = new PlayerAvatarComponent(string, d, d2);
        playerAvatarComponent.J(resourceLocation);
        return playerAvatarComponent;
    }

    @Nullable
    public EntityLivingBase b$src$Lgg_vape_wrapper_impl_EntityLivingBase_$11hwt76() {
        return this.I;
    }
}

