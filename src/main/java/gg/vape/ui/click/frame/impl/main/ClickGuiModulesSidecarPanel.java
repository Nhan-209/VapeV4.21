package gg.vape.ui.click.frame.impl.main;

import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.IconGlyphComponent;
import gg.vape.ui.click.component.IconShape;
import gg.vape.ui.click.component.ShapeIconComponent;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSidecarPrimaryMouseListener;
import gg.vape.ui.click.frame.impl.main.ClickGuiModulesSidecarSecondaryMouseListener;
import gg.vape.ui.click.frame.impl.main.ClickGuiSidecarPanelBase;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public class ClickGuiModulesSidecarPanel
extends ClickGuiSidecarPanelBase {
    private final ShapeIconComponent yB;
    private final IconGlyphComponent y9;
    @Nullable
    private Supplier<String> yR;
    @Nullable
    private Runnable yQ;

    public ClickGuiModulesSidecarPanel(@Nullable Runnable runnable) {
        this.v().W("moduleback");
        this.v().Z(true);
        this.y9 = new IconGlyphComponent("newstar", 6.0f, 6.0f, ClickGuiModulesSidecarPanel.J.W);
        this.y9.Z(false);
        this.y9.o(10.0);
        this.y9.Y(7.0);
        this.yB = new ShapeIconComponent(IconShape.ROUNDED_RECT, "", 12.0, 12.0, 4.0, 2.0f, ClickGuiModulesSidecarPanel.J.F, ClickGuiModulesSidecarPanel.J.A, 0.6);
        this.yB.Z(false);
        this.yB.o(14.0);
        this.yB.Y(10.0);
        this.H(this.y9, this.yB);
        this.e(this.yB);
        this.e(this.y9);
    }

    public void z(boolean bl) {
        this.y9.S(bl ? ClickGuiModulesSidecarPanel.J.I : ClickGuiModulesSidecarPanel.J.W);
    }

    public void f(boolean bl) {
        this.yB.Z(bl);
    }

    public void T(@Nullable Runnable runnable) {
        this.yB.r$src$V$1x8vu68();
        if (runnable != null) {
            this.yB.j(new ClickGuiModulesSidecarPrimaryMouseListener(this, runnable));
        }
    }

    @Override
    public void H() {
        block2: {
            boolean bl;
            ShapeIconComponent shapeIconComponent;
            block5: {
                block4: {
                    ShapeIconComponent shapeIconComponent2;
                    block3: {
                        String string;
                        ShapeIconComponent shapeIconComponent3;
                        if (this.yR == null) break block2;
                        String string2 = this.yR.get();
                        ShapeIconComponent shapeIconComponent4 = this.yB;
                        if (string2 != null) {
                            shapeIconComponent3 = shapeIconComponent4;
                            string = string2;
                        } else {
                            shapeIconComponent3 = shapeIconComponent4;
                            string = "";
                        }
                        shapeIconComponent3.O(string);
                        shapeIconComponent2 = this.yB;
                        if (string2 == null) break block3;
                        shapeIconComponent = shapeIconComponent2;
                        if (string2.isEmpty()) break block4;
                        bl = true;
                        break block5;
                    }
                    shapeIconComponent = shapeIconComponent2;
                }
                bl = false;
            }
            shapeIconComponent.Z(bl);
        }
        super.H();
    }

    @Override
    public void B(@Nullable String string) {
    }

    public void J(@Nullable String string) {
        if (string == null) {
            string = "";
        }
        this.yB.O(string);
        this.yB.Z(!string.isEmpty());
    }

    public void q(@Nullable Runnable runnable) {
        this.y9.r$src$V$1x8vu68();
        if (runnable != null) {
            this.y9.j(new ClickGuiModulesSidecarSecondaryMouseListener(this, runnable));
        }
    }

    public void E(@Nullable Supplier<String> supplier) {
        this.yR = supplier;
        if (supplier == null) {
            this.J("");
        }
    }

    @Override
    public void N(@Nullable Runnable runnable) {
        if (runnable != null && this.yQ == null) {
            this.yQ = runnable;
        }
        Runnable runnable2 = runnable != null ? runnable : this.yQ;
        super.N(runnable2);
        if (runnable2 != null) {
            this.k().Z(false);
            this.v().Z(true);
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void k(boolean bl) {
        this.y9.Z(bl);
    }
}

