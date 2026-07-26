package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.MappingMethodBuilder;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MPlaySoundAtEntityEvent
extends Mapping {
    private MappingField c;
    public MappingMethod A;
    public MappingMethod T;
    private static GuiComponent[] C;
    public MappingMethod S;

    public static void Y(GuiComponent[] guiComponentArray) {
        C = guiComponentArray;
    }

    public MPlaySoundAtEntityEvent() {
        this(MPlaySoundAtEntityEvent.e());
    }

    private MPlaySoundAtEntityEvent(GuiComponent[] guiComponentArray) {
        super(MappedClasses.Vk);
        Class[] classArray = new Class[]{};
        Class<Void> clazz = Void.TYPE;
        String string = "endSection";
        MPlaySoundAtEntityEvent mPlaySoundAtEntityEvent = this;
        this.S = ((MappingMethodBuilder)((MappingMethodBuilder)this.u(string, clazz, classArray).A(ForgeVersion.MC_1_16_5.n(), "pop")).Q(ForgeVersion.MC_1_21_4.n(), MappedClasses.Y0)).s();
        GuiComponent[] guiComponentArray2 = guiComponentArray;
        if (ForgeVersion.MC_1_16_5.d()) {
            Class<String> clazz2 = String.class;
            boolean bl = true;
            String string2 = "currentSectionName";
            MPlaySoundAtEntityEvent mPlaySoundAtEntityEvent2 = this;
            this.c = this.J(string2, bl, clazz2);
            Class[] classArray2 = new Class[]{String.class};
            Class<Void> clazz3 = Void.TYPE;
            String string3 = "func_219895_b";
            MPlaySoundAtEntityEvent mPlaySoundAtEntityEvent3 = this;
            this.A = ((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)((MappingMethodBuilder)this.u(string3, clazz3, classArray2).y(MappedClasses.FV)).i(ForgeVersion.MC_1_21_4.b(), Wrapper.G)).A(ForgeVersion.MC_1_16_5.n(), "popPush")).Q(ForgeVersion.MC_1_21_4.n(), MappedClasses.Y0)).s();
        } else {
            Class[] classArray3 = new Class[]{String.class};
            Class<Void> clazz4 = Void.TYPE;
            boolean bl = true;
            String string4 = "endStartSection";
            MPlaySoundAtEntityEvent mPlaySoundAtEntityEvent4 = this;
            this.A = this.Y(string4, bl, clazz4, classArray3);
            Class<String> clazz5 = String.class;
            boolean bl2 = true;
            String string5 = "profilingSection";
            MPlaySoundAtEntityEvent mPlaySoundAtEntityEvent5 = this;
            this.c = this.J(string5, bl2, clazz5);
        }
    }

    static {
        MPlaySoundAtEntityEvent.Y(null);
    }

    public static GuiComponent[] e() {
        return C;
    }

    public String Z(Object object) {
        return (String)this.c.getObject(object);
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}

