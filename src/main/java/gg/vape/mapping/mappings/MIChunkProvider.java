package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MIChunkProvider
extends Mapping {
    private MappingMethod E;

    public MIChunkProvider() {
        this(MTickingBlockEntity.getTickingBlockEntityControlFlowState());
    }

    private MIChunkProvider(int[] nArray) {
        super(MappedClasses.lg);
        if (nArray != null) {
            if (ForgeVersion.MC_1_12_2.d()) {
                if (ForgeVersion.MC_1_16_5.d()) {
                    Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE};
                    Class<Boolean> clazz = Boolean.TYPE;
                    boolean bl = true;
                    String string = "chunkExists";
                    MIChunkProvider mIChunkProvider = this;
                    this.E = mIChunkProvider.Y(string, bl, clazz, classArray);
                } else {
                    Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE};
                    Class<Boolean> clazz = Boolean.TYPE;
                    boolean bl = Wrapper.isNativeAvailable;
                    String string = "func_191062_e";
                    MIChunkProvider mIChunkProvider = this;
                    this.E = mIChunkProvider.Y(string, bl, clazz, classArray);
                }
            } else {
                Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE};
                Class<Boolean> clazz = Boolean.TYPE;
                boolean bl = true;
                String string = "chunkExists";
                MIChunkProvider mIChunkProvider = this;
                this.E = mIChunkProvider.Y(string, bl, clazz, classArray);
            }
            if (GuiComponent.getLegacyComponentState() == null) {
                MTickingBlockEntity.setTickingBlockEntityControlFlowState(new int[2]);
            }
            return;
        }
        if (ForgeVersion.MC_1_12_2.d()) {
            Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE};
            Class<Boolean> clazz = Boolean.TYPE;
            boolean bl = true;
            String string = "chunkExists";
            MIChunkProvider mIChunkProvider = this;
            this.E = mIChunkProvider.Y(string, bl, clazz, classArray);
        }
        Class[] classArray = new Class[]{Integer.TYPE, Integer.TYPE};
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl = Wrapper.isNativeAvailable;
        String string = "func_191062_e";
        MIChunkProvider mIChunkProvider = this;
        this.E = mIChunkProvider.Y(string, bl, clazz, classArray); 
        Class[] classArray2 = new Class[]{Integer.TYPE, Integer.TYPE};
        Class<Boolean> clazz2 = Boolean.TYPE;
        boolean bl2 = true;
        String string2 = "chunkExists";
        MIChunkProvider mIChunkProvider2 = this;
        this.E = this.Y(string2, bl2, clazz2, classArray2);
        if (GuiComponent.getLegacyComponentState() == null) {
            MTickingBlockEntity.setTickingBlockEntityControlFlowState(new int[2]);
        }
    }


    public boolean m(Object object, int n, int n2) {
        return this.E.invokeBoolean(object, n, n2);
    }
}

