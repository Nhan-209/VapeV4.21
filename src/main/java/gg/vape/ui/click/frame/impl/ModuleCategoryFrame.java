package gg.vape.ui.click.frame.impl;

import com.google.gson.JsonObject;
import gg.vape.Vape;
import gg.vape.module.Category;
import gg.vape.module.Mod;
import gg.vape.module.ModuleDisplayScope;
import gg.vape.module.none.ClientSettings;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.module.ModuleComponent;
import gg.vape.ui.click.frame.CollapsibleFrame;
import gg.vape.ui.click.frame.FrameHeaderComponent;
import gg.vape.ui.click.frame.OutlinedFrameBase;
import gg.vape.ui.click.frame.impl.ModuleCategoryFrameHeader;
import gg.vape.utils.NameComparator;
import java.util.ArrayList;
import java.util.Collections;

public class ModuleCategoryFrame
extends OutlinedFrameBase
implements CollapsibleFrame {
    protected ModuleCategoryFrameHeader HF;
    private Category Hp;
    private static final String fb = "wrap";
    private boolean HU;
    private String HK;
    private Mod HI;

    @Override
    public String getName() {
        return this.Hp.getName();
    }

    public Mod N$src$Lgg_vape_module_Mod_$1rbaf6a() {
        return this.HI;
    }

    public void s$src$V$1a2f6mi() {
        this.HF = new ModuleCategoryFrameHeader(this, this.Hp.N(), this.HK);
        this.Y(this.HF);
        ArrayList<Mod> arrayList = new ArrayList<Mod>(Vape.INSTANCE.getModManager().collectMods());
        Collections.sort(arrayList, new NameComparator());
        for (Mod mod : arrayList) {
            if (!mod.getCategory().equals(this.Hp) || mod.J$src$Lgg_vape_module_ModuleDisplayScope_$1w905sh() == ModuleDisplayScope.STANDALONE_ONLY) continue;
            ModuleComponent moduleComponent = new ModuleComponent(this, mod);
            this.h(moduleComponent, new Object[0]);
            moduleComponent.d$src$V$m6rlha();
        }
    }

    public void G(Mod mod) {
    }

    private static ObfuscatedRuntimeException d(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void w() {
        if (this.HF == null) {
            return;
        }
        this.HU = !this.HU;
        this.HF.h();
    }

    public int A$src$I$wwnvku() {
        int n = 0;
        for (GuiComponent guiComponent : this.f()) {
            if (guiComponent instanceof ModuleComponent) {
                boolean bl = ((ModuleComponent)guiComponent).N$src$Lgg_vape_module_Mod_$rb0ew8().O();
                ((ModuleComponent)guiComponent).m(false);
                if (!bl) {
                    ++n;
                }
                if (ClientSettings.Y) {
                    guiComponent.Z(true);
                    continue;
                }
                guiComponent.Z(bl);
                continue;
            }
            if (guiComponent instanceof FrameHeaderComponent) continue;
            guiComponent.Z(false);
        }
        this.P$src$V$i0cha4();
        return n;
    }

    @Override
    public void t(JsonObject jsonObject) {
        super.t(jsonObject);
        this.G(null);
    }

    public String L$src$Ljava_lang_String_$ahld16() {
        return this.HK;
    }

    @Override
    public void c() {
        super.c();
    }

    public ModuleComponent W(Mod mod) {
        for (GuiComponent guiComponent : this.f()) {
            if (!(guiComponent instanceof ModuleComponent) || !((ModuleComponent)guiComponent).N$src$Lgg_vape_module_Mod_$rb0ew8().equals(mod)) continue;
            return (ModuleComponent)guiComponent;
        }
        return null;
    }

    @Override
    public boolean q() {
        return this.HU;
    }

    public ModuleCategoryFrame(Category category) {
        this.HK = category.getName();
        this.Hp = category;
        if (category.equals(Category.b)) {
            return;
        }
        this.T(ModuleCategoryFrame.J.i);
        this.K(200.0);
        this.S(100.0);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(false);
        this.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().M(fb);
        this.s$src$V$1a2f6mi();
        this.Z(false);
    }

    public Category G$src$Lgg_vape_module_Category_$qyt4o7() {
        return this.Hp;
    }
}

