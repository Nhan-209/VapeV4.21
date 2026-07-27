package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MITooltipFlagBridge;
import gg.vape.ui.click.component.GuiComponent;

public class MBlockReader
extends Mapping {
    private final MappingMethod A;

    public MBlockReader() {
        this(MITooltipFlagBridge.D());
    }

    private MBlockReader(GuiComponent[] guiComponentArray) {
        super(MappedClasses.ZS);
        if (guiComponentArray != null) {
            Class[] classArray = new Class[]{MappedClasses.qP, MappedClasses.qP, MappedClasses.lN, MappedClasses.Y9, MappedClasses.zc};
            MBlockReader mBlockReader = this;
            this.A = mBlockReader.g(classArray);
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.qP, MappedClasses.qP, MappedClasses.lN, MappedClasses.Y9, MappedClasses.zc};
        MBlockReader mBlockReader = this;
        this.A = mBlockReader.g(classArray); 
        GuiComponent.D(new GuiComponent[1]);
    }


    public Object n(Object object, Object object2, Object object3, Object object4, Object object5) {
        return this.A.O(object, object2, object3, object4, object5);
    }
}
