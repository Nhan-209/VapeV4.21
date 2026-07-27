package gg.vape.ui.click.frame.impl.hud;

import gg.vape.module.none.ClientSettings;
import gg.vape.module.render.hud.ArmorStatusHudModule;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.frame.impl.hud.ArmorStatusItemComponent;
import gg.vape.ui.click.frame.impl.hud.HudModuleConfigFrameBase;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.Item;
import gg.vape.wrapper.impl.ItemStack;
import gg.vape.wrapper.impl.Minecraft;
import java.util.ArrayList;

public class ArmorStatusHudFrame
extends HudModuleConfigFrameBase {
    private ArrayList<ArmorStatusItemComponent> q4 = new ArrayList();
    private ArmorStatusItemComponent qV;
    private boolean q9 = false;
    private ArmorStatusItemComponent qZ;
    private static final String tb = "ArmorStatusFrame";
    private ArmorStatusItemComponent q1;
    private ArmorStatusItemComponent qe;
    private ArmorStatusHudModule qS = (ArmorStatusHudModule)this.l$src$Lgg_vape_module_render_hud_HudModule_$v08nt0();

    private boolean q$src$Z$3m3fvt() {
        for (ArmorStatusItemComponent armorStatusItemComponent : this.q4) {
            if (!armorStatusItemComponent.E().isNotNull()) continue;
            return true;
        }
        return false;
    }

    public int e() {
        if (this.qS == null) {
            return 20;
        }
        return this.qS.P.L() != false ? 22 : 20;
    }

    @Override
    public void o() {
        if (Minecraft.thePlayer().isNotNull()) {
            this.e$src$V$3fhwo1();
        }
    }

    @Override
    public double A() {
        if (this.qS == null) {
            return 70.0;
        }
        return this.qS.P.L() != false ? 26.0 : 54.0;
    }


    private void e$src$V$3fhwo1() {
        for (GuiComponent guiComponent : this.f()) {
            ArmorStatusItemComponent armorStatusItemComponent;
            if (!(guiComponent instanceof ArmorStatusItemComponent) || !(armorStatusItemComponent = (ArmorStatusItemComponent)guiComponent).B$src$Lgg_vape_wrapper_impl_Slot_$1r5ac0m().I().isNotNull()) continue;
            armorStatusItemComponent.Z(true);
        }
        double d = this.n() + 2.0;
        boolean bl = this.qS.P.L();
        ArmorStatusItemComponent armorStatusItemComponent = null;
        boolean bl2 = false;
        if (!ClientSettings.fW.P && !this.q$src$Z$3m3fvt()) {
            bl2 = true;
        }
        for (ArmorStatusItemComponent armorStatusItemComponent2 : this.q4) {
            if (armorStatusItemComponent2.V$src$Z$1xhop3l()) {
                armorStatusItemComponent2.K(this.G$src$D$1b2f02a() + 2.0);
                armorStatusItemComponent2.S(d);
                d += (double)this.e();
                if (bl) {
                    d += 2.0;
                }
                armorStatusItemComponent2.m(false);
                armorStatusItemComponent = armorStatusItemComponent2;
            }
            armorStatusItemComponent2.V(bl2);
            if (bl2) {
                armorStatusItemComponent2.Z(true);
            }
            armorStatusItemComponent2.q(bl);
        }
        if (armorStatusItemComponent != null) {
            armorStatusItemComponent.m(true);
        }
        if (!this.q9) {
            this.l$src$V$1mibm4x();
            this.q9 = true;
        }
    }

    @Override
    public String getName() {
        return tb;
    }

    public ArmorStatusHudFrame() {
        super(ArmorStatusHudModule.class);
        int n = 310;
        int n2 = 311;
        int n3 = 312;
        int n4 = 313;
        if (ForgeVersion.MC_1_21_10.d()) {
            n = 970;
            n2 = 971;
            n3 = 972;
            n4 = 973;
        } else if (ForgeVersion.MC_1_21_0.d()) {
            n = 899;
            n2 = 900;
            n3 = 901;
            n4 = 902;
        } else if (ForgeVersion.MC_1_20_6.d()) {
            n = 868;
            n2 = 869;
            n3 = 870;
            n4 = 871;
        } else if (ForgeVersion.MC_1_17.d()) {
            n = 750;
            n2 = 751;
            n3 = 752;
            n4 = 753;
        } else if (ForgeVersion.MC_1_16_5.d()) {
            n = 634;
            n2 = 635;
            n3 = 636;
            n4 = 637;
        }
        this.q1 = new ArmorStatusItemComponent(this, 5, ItemStack.S(Item.T(n)));
        this.qV = new ArmorStatusItemComponent(this, 6, ItemStack.S(Item.T(n2)));
        this.qe = new ArmorStatusItemComponent(this, 7, ItemStack.S(Item.T(n3)));
        this.qZ = new ArmorStatusItemComponent(this, 8, ItemStack.S(Item.T(n4)));
        this.q4.add(this.q1);
        this.q4.add(this.qV);
        this.q4.add(this.qe);
        this.q4.add(this.qZ);
        this.H(this.q1, this.qV, this.qe, this.qZ);
    }

    @Override
    public double L() {
        int n = 0;
        for (GuiComponent guiComponent : this.f()) {
            if (!(guiComponent instanceof ArmorStatusItemComponent) || !guiComponent.V$src$Z$1xhop3l()) continue;
            ++n;
        }
        if (n == 0) {
            return 0.0;
        }
        if (this.qS == null) {
            return 20.0;
        }
        int n2 = this.e();
        if (this.qS.P.L().booleanValue()) {
            n2 += 2;
        }
        double d = n * n2 + 4;
        if (n == 1) {
            d = n2 + 4;
        }
        if (this.qS.P.L().booleanValue()) {
            d -= 2.0;
        }
        return d;
    }
}

