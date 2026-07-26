package gg.vape.mapping.mappings;

import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.mapping.mappings.MRenderBatchFlushTarget;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.wrapper.impl.ForgeVersion;
import java.lang.reflect.Method;

public class MTextureObjectHandle
extends Mapping {
    private MappingField a;
    private MappingField F;
    private MappingField c;
    private MappingField L;

    public MTextureObjectHandle() {
        this(MRenderBatchFlushTarget.f());
    }

    private MTextureObjectHandle(int n) {
        super(MappedClasses.uA);
        int n2 = n;
        if (n2 != 0) {
            Class<Integer> clazz = Integer.TYPE;
            boolean bl = true;
            String string = "id";
            MTextureObjectHandle mTextureObjectHandle = this;
            this.F = mTextureObjectHandle.J(string, bl, clazz);
            if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
                MRenderBatchFlushTarget.c(++n2);
            }
            return;
        }
        Class<Integer> clazz = Integer.TYPE;
        boolean bl = true;
        String string = "id";
        MTextureObjectHandle mTextureObjectHandle = this;
        this.a = mTextureObjectHandle.J(string, bl, clazz);
        if (ForgeVersion.MC_1_21_11.d()) {
            Class<Integer> clazz2 = Integer.TYPE;
            boolean bl2 = true;
            String string2 = "firstFboId";
            MTextureObjectHandle mTextureObjectHandle2 = this;
            this.L = this.J(string2, bl2, clazz2);
            Class<Integer> clazz3 = Integer.TYPE;
            boolean bl3 = true;
            String string3 = "firstFboDepthId";
            MTextureObjectHandle mTextureObjectHandle3 = this;
            this.c = this.J(string3, bl3, clazz3);
            Class clazz4 = MappedClasses.FG;
            boolean bl4 = true;
            String string4 = "fboCache";
            MTextureObjectHandle mTextureObjectHandle4 = this;
            this.F = this.J(string4, bl4, clazz4);
        } else {
            Class clazz5 = MappedClasses.FG;
            boolean bl5 = true;
            String string5 = "fboCache";
            MTextureObjectHandle mTextureObjectHandle5 = this;
            this.F = this.J(string5, bl5, clazz5);
        }
        if (GuiComponent.D$src$ALgg_vape_ui_click_component_GuiComponent_$1yk9q9k() == null) {
            MRenderBatchFlushTarget.c(++n2);
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public int H(Object object, int n) {
        try {
            if (ForgeVersion.MC_1_21_11.d()) {
                if (this.L != null && this.c != null) {
                    Method method;
                    Object object2;
                    int n2;
                    Object object3;
                    int n3;
                    int n4 = this.c.getInt(object);
                    if (n4 == n && (n3 = this.L.getInt(object)) != -1) {
                        return n3;
                    }
                    if (this.F != null && (object3 = this.F.getObject(object)) != null && (n2 = ((Integer)(object2 = (method = object3.getClass().getMethod("get", Integer.TYPE)).invoke(object3, n))).intValue()) != 0) {
                        return n2;
                    }
                }
                return -1;
            }
            if (this.F == null) {
                return -1;
            }
            Object object4 = this.F.getObject(object);
            if (object4 == null) {
                return -1;
            }
            Method method = object4.getClass().getMethod("get", Integer.TYPE);
            Object object5 = method.invoke(object4, n);
            return (Integer)object5;
        }
        catch (Exception exception) {
            return -1;
        }
    }

    public int r(Object object) {
        return this.a.getInt(object);
    }
}

