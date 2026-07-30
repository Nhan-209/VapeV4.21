package gg.vape.mapping.mappings;

import gg.vape.Vape;
import gg.vape.mapping.MappedClasses;
import gg.vape.mapping.Mapping;
import gg.vape.mapping.MappingField;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;

public class MGlStateManager_BlendState
extends Mapping {
    public MappingField a;

    public Object F(Object object) {
        return this.a.getObject(object);
    }


    public MGlStateManager_BlendState() {
        this(MEntityRenderer.n());
    }

    private MGlStateManager_BlendState(int n) {
        super(MappedClasses.Yk);
        if (n != 0) {
            if (Vape.INSTANCE.isVanillaMinecraftPresent()) {
                Class clazz = MappedClasses.U;
                boolean bl = true;
                String string = "blend";
                MGlStateManager_BlendState mGlStateManager_BlendState = this;
                this.a = mGlStateManager_BlendState.J(string, bl, clazz);
            } else if (ForgeVersion.MC_1_20_6.d()) {
                Class clazz = MappedClasses.U;
                String string = "mode";
                MGlStateManager_BlendState mGlStateManager_BlendState = this;
                this.a = mGlStateManager_BlendState.fieldBuilder(string, clazz).buildField();
            } else {
                Class clazz = MappedClasses.U;
                boolean bl = Wrapper.isNativeAvailable;
                String string = "field_179213_a";
                MGlStateManager_BlendState mGlStateManager_BlendState = this;
                this.a = mGlStateManager_BlendState.J(string, bl, clazz);
            }
            return;
        }
        if (Vape.INSTANCE.isVanillaMinecraftPresent()) {
            Class clazz = MappedClasses.U;
            String string = "mode";
            MGlStateManager_BlendState mGlStateManager_BlendState = this;
            this.a = mGlStateManager_BlendState.fieldBuilder(string, clazz).buildField();
        }
        Class clazz = MappedClasses.U;
        boolean bl = Wrapper.isNativeAvailable;
        String string = "field_179213_a";
        MGlStateManager_BlendState mGlStateManager_BlendState = this;
        this.a = mGlStateManager_BlendState.J(string, bl, clazz); 
    }
}
