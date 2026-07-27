package gg.vape.ui.click.frame.impl.target;

import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleFrameBase;
import gg.vape.utils.RotationUtil;
import gg.vape.utils.TimerUtil;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.wrapper.impl.EntityLivingBase;
import java.awt.Color;
import org.jetbrains.annotations.Nullable;

public class TargetInfoHealthBarComponent
extends GuiComponent {
    private final TimerUtil o = new TimerUtil();
    private boolean a = false;
    private HudModuleFrameBase I;
    @Nullable
    private EntityLivingBase R;
    private float v = 0.0f;
    private float i = 0.0f;

    public void y(HudModuleFrameBase hudModuleFrameBase) {
        this.I = hudModuleFrameBase;
    }

    @Override
    public void H() {
        double d = this.M();
        if (d == -1.0) {
            return;
        }
        double d2 = Math.min(this.A() * this.M(), this.A());
        float f = (float)this.L() / 2.0f - 0.5f;
        Color color = new Color(0, 0, 0, 100);
        Color color2 = TargetInfoHealthBarComponent.J.B;
        Color color3 = new Color(0, 0, 0, 100);
        if (this.I != null) {
            color = this.I.l(color);
            color2 = this.I.l(color2);
            color3 = this.I.l(color3);
        }
        GuiRenderPrimitives.I(this.G$src$D$1b2f02a(), this.n(), this.A(), this.L(), color, false, f, 1.0f, 8.0f, color3);
        GuiRenderPrimitives.I(this.G$src$D$1b2f02a(), this.n(), d2, this.L(), color2, true, f, 1.0f, 8.0f, color3);
    }

    private float e() {
        float f;
        if (this.a) {
            return this.v;
        }
        float f2 = this.v;
        long l = this.o.getLastMS();
        if ((float)l >= (f = 10.0f)) {
            double d;
            float f3 = Math.abs(this.i - f2);
            float f4 = f3 < 0.5f && this.i < 0.5f ? 0.05f * ((float)l / f) : 0.08f * ((float)l / f);
            if (this.i < f2) {
                d = Math.max(1.0, Math.pow(f3, 0.5));
                this.i = (float)((double)this.i + (double)f4 * d);
            }
            if (this.i > f2) {
                d = Math.max(1.0, Math.pow(f3, 0.5));
                this.i = (float)((double)this.i - (double)f4 * d);
            }
            this.i = Math.max(0.0f, Math.min(this.i, 20.0f));
            if ((double)Math.abs(this.i - f2) < 0.001) {
                this.i = f2;
            }
            this.o.reset();
        }
        if (Float.isNaN(this.i) || !Float.isFinite(this.i)) {
            this.i = f2;
            this.o.reset();
        }
        f2 = this.i;
        f2 = Math.max(f2, 0.0f);
        return f2;
    }

    @Override
    public void u() {
        if (this.R != null) {
            this.v = RotationUtil.x(this.R);
            if (this.a) {
                this.a = false;
                this.o.reset();
                this.i = this.v;
            }
        } else {
            this.v = 0.0f;
        }
    }

    public double M() {
        if (this.R == null) {
            return -1.0;
        }
        float f = this.e();
        return f / this.R.I$src$F$14vyvep();
    }


    public void a(@Nullable EntityLivingBase entityLivingBase) {
        if (entityLivingBase != null && entityLivingBase.isNotNull() && entityLivingBase.equals(this.R)) {
            return;
        }
        this.R = entityLivingBase;
        this.a = true;
    }

    public TargetInfoHealthBarComponent(int n, int n2) {
        this.o(n);
        this.Y(n2);
    }

    public EntityLivingBase P() {
        return this.R;
    }
}

