package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.module.Mod;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.FlowLayoutComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.gui.InteractiveComponent;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.ValueComponentFactory;
import gg.vape.ui.click.frame.FrameComponent;
import gg.vape.ui.click.frame.impl.profile.ProfileSnapshotValueResetButtonComponent;
import gg.vape.unmap.ModeSelection;
import gg.vape.value.BooleanValue;
import gg.vape.value.ConditionalValue;
import gg.vape.value.ListValue;
import gg.vape.value.ModeValue;
import gg.vape.value.SubModuleValue;
import gg.vape.value.Value;
import gg.vape.value.ValueSnapshot;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileSnapshotGuiBuilder {
    private static final String b = "reset_circle";
    private final HashMap<Value<?, ?>, Value<?, ?>> O;
    private final HashMap<ProfileModuleSnapshot, List<GuiComponent>> Z;
    private static int U;
    private final ProfileSnapshot D;
    private final HashMap<Value<?, ?>, ValueSnapshot<?, ?>> M;
    private final HashMap<Value<?, ?>, Value<?, ?>> H;
    private Color u = new Color(54, 53, 54, 128);
    private final HashMap<Value<?, ?>, GuiComponent> g;

    public ProfileSnapshotGuiBuilder(ProfileSnapshot profileSnapshot) {
        this.H = new HashMap();
        this.M = new HashMap();
        this.O = new HashMap();
        this.g = new HashMap();
        this.Z = new HashMap();
        this.D = profileSnapshot;
        this.P();
    }

    public void v(Value<?, ?> value) {
        Value<?, ?> value2 = this.H.get(value);
        if (value2 == null) {
            return;
        }
        ValueSnapshot<?, ?> valueSnapshot = this.M.get(value2);
        if (valueSnapshot == null) {
            return;
        }
        value2.S();
        valueSnapshot.s(value2.K());
        value.g$src$V$1akzyia();
        value2.g$src$V$1akzyia();
        if (value instanceof ConditionalValue) {
            ConditionalValue conditionalValue = (ConditionalValue)value;
            List<Value> list = conditionalValue.q$src$Ljava_util_List_$fyau59();
            for (Value value3 : list) {
                Value<?, ?> value4 = this.H.get(value3);
                GuiComponent guiComponent = this.g.get(value4);
                if (guiComponent != null) continue;
                this.v(value3);
            }
        }
    }

    static {
        long l = -7615482920005795630L;
        U = (int)l;
    }

    public List<GuiComponent> E(ProfileModuleSnapshot profileModuleSnapshot) {
        return this.Z.get(profileModuleSnapshot);
    }

    private void P() {
        for (ProfileModuleSnapshot profileModuleSnapshot : this.D.L()) {
            Object object;
            Object object2;
            Value<?, ?> value;
            Value<?, ?> value2;
            ArrayList<GuiComponent> arrayList = new ArrayList<GuiComponent>();
            this.Z.put(profileModuleSnapshot, arrayList);
            for (ValueSnapshot<?, ?> valueSnapshot : profileModuleSnapshot.z()) {
                Value<?, ?> snapshotValue = ValueComponentFactory.e(valueSnapshot);
                this.i(snapshotValue);
                GuiComponent valueComponent = ValueComponentFactory.v(snapshotValue, true);
                if (valueComponent == null) continue;
                boolean bl = true;
                if (valueSnapshot.W() instanceof ListValue && ((Value)(object2 = (ListValue)valueSnapshot.W())).getParent() instanceof BooleanValue && ((BooleanValue)(object = (BooleanValue)((Value)object2).getParent())).G() != null && ((BooleanValue)object).G().equals(object2)) {
                    bl = false;
                }
                valueComponent.P(true);
                valueComponent.q(182.0);
                valueComponent.C(0.0);
                FlowLayoutComponent flowLayoutComponent = new FlowLayoutComponent(U);
                flowLayoutComponent.l$src$Lgg_vape_ui_click_layout_ComponentLayout_$di1tij().Q(true);
                ProfileSnapshotValueResetButtonComponent resetButton = new ProfileSnapshotValueResetButtonComponent(this, b, this.u, 0.75, 11.0, 11.0, valueComponent, valueSnapshot);
                resetButton.Z(!snapshotValue.k());
                ((InteractiveComponent)resetButton).s(() -> this.lambda$createProxyValues$0(valueSnapshot));
                if (bl) {
                    flowLayoutComponent.h(valueComponent, new Object[0]);
                    flowLayoutComponent.h(resetButton, new Object[0]);
                    arrayList.add(flowLayoutComponent);
                }
                this.H.put((Value<?, ?>)valueSnapshot.W(), snapshotValue);
                if (((Value)valueSnapshot.W()).getParent() != null) {
                    this.O.put(snapshotValue, ((Value)valueSnapshot.W()).getParent());
                }
                this.M.put(snapshotValue, valueSnapshot);
                if (!bl) continue;
                this.g.put(snapshotValue, valueComponent);
            }
            for (Value value3 : this.O.keySet()) {
                ConditionalValue conditionalValue;
                value2 = this.O.get(value3);
                if (value2 == null) continue;
                value = this.H.get(value2);
                if (value instanceof ConditionalValue) {
                    conditionalValue = (ConditionalValue)value;
                    conditionalValue.K(value3);
                }
                if (!(value3 instanceof ListValue) || !(value instanceof BooleanValue)) continue;
                conditionalValue = (BooleanValue)value;
                object2 = (ListValue)value3;
                ((BooleanValue)conditionalValue).l((ListValue)object2);
                object = this.g.get(conditionalValue);
                if (!(object instanceof BooleanToggleComponent)) continue;
                BooleanToggleComponent booleanToggleComponent = (BooleanToggleComponent)object;
                booleanToggleComponent.Z$src$V$1e3oa11();
                booleanToggleComponent.G$src$Lgg_vape_ui_click_component_value_CompactListVal$1o8zcka().X(true);
            }
        }
    }

    private void O(ModeValue modeValue) {
        for (ModeSelection modeSelection : modeValue.getModes()) {
            if (!(modeSelection instanceof SubModuleValue)) continue;
            SubModuleValue subModuleValue = (SubModuleValue)modeSelection;
            for (Value<?, ?> value : ((Mod)subModuleValue.getInstance()).V()) {
                Value<?, ?> value2 = this.H.get(value);
                if (value2 == null) continue;
                modeValue.L(value2, subModuleValue);
            }
        }
    }

    private void lambda$createProxyValues$0(ValueSnapshot valueSnapshot) {
        this.v((Value<?, ?>)valueSnapshot.W());
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public void G(ProfileModuleSnapshot profileModuleSnapshot) {
        for (ValueSnapshot<?, ?> valueSnapshot : profileModuleSnapshot.z()) {
            this.v((Value<?, ?>)valueSnapshot.W());
        }
        profileModuleSnapshot.T();
        profileModuleSnapshot.L(false);
    }

    public void H() {
        for (ProfileModuleSnapshot profileModuleSnapshot : this.D.L()) {
            this.G(profileModuleSnapshot);
        }
    }

    private void i(Value<?, ?> value) {
        if (value instanceof ModeValue) {
            this.O((ModeValue)value);
        }
    }
}
