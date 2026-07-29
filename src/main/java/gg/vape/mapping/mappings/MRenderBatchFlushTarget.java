package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;

public class MRenderBatchFlushTarget
extends Mapping {
    private static int N;
    private static final String b;
    public MappingMethod i;

    public static int T() {
        int n = MRenderBatchFlushTarget.f();
        if (n == 0) {
            return 64;
        }
        return 0;
    }

    public static void c(int n) {
        N = n;
    }

    public MRenderBatchFlushTarget() {
        this(MRenderBatchFlushTarget.T());
    }

    private MRenderBatchFlushTarget(int n) {
        super(MappedClasses.zg);
        if (n != 0) {
            Class[] classArray = new Class[]{MappedClasses.uq, Integer.TYPE, Integer.TYPE, Integer.TYPE, MappedClasses.Yz, MappedClasses.FA, Integer.TYPE};
            Class<Void> clazz = Void.TYPE;
            boolean bl = true;
            String string = b;
            MRenderBatchFlushTarget mRenderBatchFlushTarget = this;
            this.i = mRenderBatchFlushTarget.Y(string, bl, clazz, classArray);
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.uq, Integer.TYPE, Integer.TYPE, Integer.TYPE, MappedClasses.Yz, MappedClasses.FA, Integer.TYPE};
        Class<Void> clazz = Void.TYPE;
        boolean bl = true;
        String string = b;
        MRenderBatchFlushTarget mRenderBatchFlushTarget = this;
        this.i = mRenderBatchFlushTarget.Y(string, bl, clazz, classArray);
        GuiComponent.setLegacyComponentState(new GuiComponent[3]);
    }

    static {
        MRenderBatchFlushTarget.c(0);
        b = "drawFromBuffers";
    }

    public static int f() {
        return N;
    }

}

