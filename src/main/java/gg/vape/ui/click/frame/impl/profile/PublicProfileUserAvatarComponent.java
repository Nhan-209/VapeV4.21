package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.PublicProfileUser;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.ImageTextureComponent;
import gg.vape.ui.click.frame.impl.profile.PublicProfileUserAvatarTextureCache;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class PublicProfileUserAvatarComponent
extends ImageTextureComponent {
    private long R;
    private static final String v = "avatar offline@2x";

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void H() {
        GlImageTexture glImageTexture = PublicProfileUserAvatarTextureCache.q().s(this.R);
        this.u(Color.white);
        if (glImageTexture != null) {
            this.J(glImageTexture);
        } else {
            this.J(ImageRenderer.loadResource(v, false, false));
        }
        super.H();
    }

    public PublicProfileUserAvatarComponent(@Nullable PublicProfileUser publicProfileUser, double d, double d2) {
        this(publicProfileUser != null ? publicProfileUser.j() : -1L, d, d2);
    }

    public void W(long l) {
        this.R = l;
    }

    public PublicProfileUserAvatarComponent(long l, double d, double d2) {
        this.R = l;
        this.o(d);
        this.Y(d2);
    }
}

