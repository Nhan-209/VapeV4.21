package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;

public class MChatMessageRenderTarget
extends Mapping {
    private static boolean w;
    public MappingMethod T;
    private static final String b;

    public static boolean m() {
        return w;
    }

    public static boolean a() {
        boolean bl = MChatMessageRenderTarget.m();
        return false;
    }

    public static void Y(boolean bl) {
        w = bl;
    }


    public MChatMessageRenderTarget() {
        this(MChatMessageRenderTarget.a());
    }

    private MChatMessageRenderTarget(boolean bl) {
        super(MappedClasses.d);
        if (bl) {
            if (ForgeVersion.MC_26_1.v()) {
                Class[] classArray = new Class[]{MappedClasses.Yr, MappedClasses.uh, MappedClasses.zF};
                Class<Void> clazz = Void.TYPE;
                boolean bl2 = true;
                String string = b;
                MChatMessageRenderTarget mChatMessageRenderTarget = this;
                this.T = mChatMessageRenderTarget.Y(string, bl2, clazz, classArray);
            }
            GuiComponent.D(new GuiComponent[2]);
            return;
        }
        if (ForgeVersion.MC_26_1.v()) {
            Class[] classArray = new Class[]{MappedClasses.Yr, MappedClasses.uh, MappedClasses.zF};
            Class<Void> clazz = Void.TYPE;
            boolean bl3 = true;
            String string = b;
            MChatMessageRenderTarget mChatMessageRenderTarget = this;
            this.T = mChatMessageRenderTarget.Y(string, bl3, clazz, classArray); 
        }
    }

    static {
        MChatMessageRenderTarget.Y(true);
        b = "addMessage";
    }
}
