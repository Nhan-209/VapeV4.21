package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingMethod;
import gg.vape.mapping.mappings.MCaughtEntityActionBridge;
import gg.vape.ui.click.component.GuiComponent;

public class MEntityFishHook
extends Mapping {
    private final MappingMethod w;
    private final MappingMethod v;

    public MEntityFishHook() {
        this(MCaughtEntityActionBridge.Y());
    }

    private MEntityFishHook(boolean bl) {
        super(MappedClasses.q8);
        Class[] classArray = new Class[]{};
        Class<Boolean> clazz = Boolean.TYPE;
        boolean bl2 = false;
        String string = "isOpen";
        MEntityFishHook mEntityFishHook = this;
        this.v = this.Y(string, bl2, clazz, classArray);
        Class[] classArray2 = new Class[]{};
        Class clazz2 = MappedClasses.VA;
        boolean bl3 = false;
        String string2 = "eventLoop";
        MEntityFishHook mEntityFishHook2 = this;
        this.w = this.Y(string2, bl3, clazz2, classArray2);
        if (bl) {
            if (GuiComponent.getLegacyComponentState() == null) {
                MCaughtEntityActionBridge.h(false);
            }
            return;
        }
        if (GuiComponent.getLegacyComponentState() == null) {
            MCaughtEntityActionBridge.h(true);
        }
    }

    private boolean isInGround(Object object) {
        return this.v.invokeBoolean(object, new Object[0]);
    }

    public static Object getCaughtEntity(MEntityFishHook mEntityFishHook, Object object) {
        return mEntityFishHook.getCaughtEntity(object);
    }

    private Object getCaughtEntity(Object object) {
        return this.w.invokeObject(object, new Object[0]);
    }

    public static boolean isInGround(MEntityFishHook mEntityFishHook, Object object) {
        return mEntityFishHook.isInGround(object);
    }

}

