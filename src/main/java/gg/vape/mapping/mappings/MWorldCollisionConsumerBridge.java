package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MWorldInfo;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;
import java.util.function.Consumer;

public class MWorldCollisionConsumerBridge
extends Mapping {
    private final MappingMethod d;
    private static final String b = "m_142232_";

    public void V(Object object, Object object2, Consumer consumer) {
        this.d.invokeVoid(object, object2, consumer);
    }

    public MWorldCollisionConsumerBridge() {
        this(MWorldInfo.T());
    }

    private MWorldCollisionConsumerBridge(String[] stringArray) {
        super(MappedClasses.zv);
        if (stringArray != null) {
            Class[] classArray = new Class[]{MappedClasses.uk, Consumer.class};
            Class<Void> clazz = Void.TYPE;
            boolean bl = ForgeVersion.MC_1_20_6.d();
            String string = b;
            MWorldCollisionConsumerBridge mWorldCollisionConsumerBridge = this;
            this.d = mWorldCollisionConsumerBridge.Y(string, bl, clazz, classArray);
            GuiComponent.setLegacyComponentState(new GuiComponent[2]);
            return;
        }
        Class[] classArray = new Class[]{MappedClasses.uk, Consumer.class};
        Class<Void> clazz = Void.TYPE;
        boolean bl = ForgeVersion.MC_1_20_6.d();
        String string = b;
        MWorldCollisionConsumerBridge mWorldCollisionConsumerBridge = this;
        this.d = mWorldCollisionConsumerBridge.Y(string, bl, clazz, classArray);
    }

}
