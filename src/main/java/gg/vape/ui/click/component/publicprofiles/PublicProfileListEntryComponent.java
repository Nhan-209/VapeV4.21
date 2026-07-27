package gg.vape.ui.click.component.publicprofiles;

import gg.vape.config.PublicProfile;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.publicprofiles.PublicProfileIdBadgeComponent;
import gg.vape.utils.render.GuiRenderPrimitives;
import java.awt.Color;

public class PublicProfileListEntryComponent
extends InteractiveComponent {
    private final PublicProfileIdBadgeComponent Q;
    private final PublicProfile b;
    private boolean K;
    private final TruncatedTextComponent I;
    private static final String ab = "...";


    @Override
    public void u() {
        if (this.K && !this.w$src$Z$e457mb()) {
            this.K = false;
        }
    }

    @Override
    public double x() {
        return 92.0;
    }

    @Override
    public double C() {
        return 18.0;
    }

    public PublicProfileListEntryComponent(PublicProfile publicProfile) {
        this.b = publicProfile;
        this.I = new TruncatedTextComponent(publicProfile.v(), ab, 0.0, 0.85, PublicProfileListEntryComponent.J.A, false);
        this.Q = new PublicProfileIdBadgeComponent(this.Z$src$J$qasse7());
        this.o(true);
        this.H(this.I, this.Q);
    }

    public PublicProfile I$src$Lgg_vape_config_PublicProfile_$mb4s3m() {
        return this.b;
    }

    @Override
    public void F() {
        this.K = true;
    }

    @Override
    public void H() {
        float f;
        double d = this.I.f$src$D$ldt7xy();
        double d2 = this.n() + this.L() / 2.0 - d / 2.0;
        Color color = PublicProfileListEntryComponent.J.m;
        Color color2 = PublicProfileListEntryComponent.J.Z;
        if (this.K) {
            color = PublicProfileListEntryComponent.J.a;
            color2 = PublicProfileListEntryComponent.J.A;
        }
        this.I.K(this.G$src$D$1b2f02a() + 7.0);
        this.I.S(d2);
        double d3 = this.A();
        this.getClass();
        double d4 = d3 - 5.0;
        if (this.Q.h() > 0L) {
            f = 15.0f;
        } else {
            this.getClass();
            f = 5.0f;
        }
        this.I.D(d4 - (double)f);
        this.I.R(color2);
        this.Q.g(this.Z$src$J$qasse7());
        this.Q.K(this.G$src$D$1b2f02a() + this.A() - this.Q.A() - 8.0);
        this.Q.S(this.n() + this.L() / 2.0 - this.Q.L() / 2.0);
        GuiRenderPrimitives.d(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), color);
    }

    private long Z$src$J$qasse7() {
        return this.b.c() != null ? this.b.c().o() : 0L;
    }
}

