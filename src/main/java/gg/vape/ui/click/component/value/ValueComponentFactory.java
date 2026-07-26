package gg.vape.ui.click.component.value;

import gg.vape.module.blatant.AntiBotBooleanValue;
import gg.vape.module.blatant.AntiBotModeValue;
import gg.vape.module.utility.inventory.HotbarSlotRuleEditorComponent;
import gg.vape.module.utility.inventory.HotbarSlotRuleValue;
import gg.vape.module.utility.inventory.cleaner.InventoryCleanerProfileValue;
import gg.vape.module.utility.inventory.cleaner.ui.InventoryCleanerProfileValueComponent;
import gg.vape.runtime.ObfuscatedRuntimeException;
import gg.vape.ui.click.component.DropdownSelectComponent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.input.BindValueRowComponent;
import gg.vape.ui.click.component.value.AntiBotBooleanValueOptionRow;
import gg.vape.ui.click.component.value.BooleanToggleComponent;
import gg.vape.ui.click.component.value.ColorValueDropdownComponent;
import gg.vape.ui.click.component.value.ColorValueEditorComponent;
import gg.vape.ui.click.component.value.EntityTargetFilterComponent;
import gg.vape.ui.click.component.value.EntityTargetFilterPopupComponent;
import gg.vape.ui.click.component.value.ListValueComponent;
import gg.vape.ui.click.component.value.NumberSliderComponent;
import gg.vape.ui.click.component.value.RandomRangeSliderComponent;
import gg.vape.ui.click.component.value.StringMapValueComponent;
import gg.vape.ui.click.component.value.ValueComponentMode;
import gg.vape.unmap.ItemLimitData;
import gg.vape.value.BindValue;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.ListValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.OptionalLimitEntry;
import gg.vape.value.RandomValue;
import gg.vape.value.SnapshotValueAccessor;
import gg.vape.value.StringMapValue;
import gg.vape.value.Value;
import gg.vape.value.ValueSnapshot;

public class ValueComponentFactory {
    public static GuiComponent K(Value<?, ?> value, boolean bl, ValueComponentMode valueComponentMode) {
        GuiComponent guiComponent = value.R$src$Lgg_vape_ui_click_component_GuiComponent_$1gnoyjm();
        if (value instanceof BooleanValue) {
            guiComponent = new BooleanToggleComponent((BooleanValue)value);
        } else if (value instanceof AntiBotModeValue) {
            guiComponent = new ColorValueDropdownComponent((AntiBotModeValue)value);
        } else if (value instanceof AntiBotBooleanValue) {
            guiComponent = new AntiBotBooleanValueOptionRow((AntiBotBooleanValue)value);
        } else if (value instanceof ModeValue) {
            guiComponent = new DropdownSelectComponent((ModeValue)value);
            if (valueComponentMode == ValueComponentMode.STANDALONE) {
                ((DropdownSelectComponent)guiComponent).v(true);
            }
        } else if (value instanceof NumberValue) {
            guiComponent = new NumberSliderComponent((NumberValue)value);
        } else if (value instanceof RandomValue) {
            guiComponent = new RandomRangeSliderComponent((RandomValue)value);
        } else if (value instanceof ColorValue) {
            guiComponent = new ColorValueEditorComponent((ColorValue)value);
        } else if (value instanceof ListValue) {
            BooleanValue booleanValue;
            ListValueComponent listValueComponent = new ListValueComponent((ListValue)value);
            if (valueComponentMode == ValueComponentMode.STANDALONE) {
                listValueComponent.W(ValueComponentMode.STANDALONE);
            }
            guiComponent = listValueComponent;
            ListValue listValue = (ListValue)value;
            if (listValue.getParent() instanceof BooleanValue && (booleanValue = (BooleanValue)listValue.getParent()).G() != null && booleanValue.G().equals(listValue)) {
                guiComponent = null;
            }
        } else if (value instanceof HotbarSlotRuleValue) {
            if (!bl) {
                HotbarSlotRuleValue hotbarSlotRuleValue = (HotbarSlotRuleValue)value;
                if (hotbarSlotRuleValue.Y() != null) {
                    guiComponent = hotbarSlotRuleValue.Y();
                    guiComponent.Z(true);
                } else {
                    guiComponent = new HotbarSlotRuleEditorComponent(hotbarSlotRuleValue);
                }
            }
        } else if (value instanceof EntityTargetFilterValue) {
            guiComponent = bl ? new EntityTargetFilterPopupComponent((EntityTargetFilterValue)value) : new EntityTargetFilterComponent((EntityTargetFilterValue)value);
        } else if (value instanceof StringMapValue) {
            guiComponent = new StringMapValueComponent((StringMapValue)value);
        } else if (value instanceof BindValue) {
            guiComponent = new BindValueRowComponent((BindValue)value);
        } else if (value instanceof InventoryCleanerProfileValue) {
            guiComponent = new InventoryCleanerProfileValueComponent((InventoryCleanerProfileValue)value);
        }
        return guiComponent;
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    public static GuiComponent Y(Value<?, ?> value) {
        return ValueComponentFactory.K(value, false, ValueComponentMode.MAIN);
    }

    public static Value e(ValueSnapshot valueSnapshot) {
        Value t = valueSnapshot.W();
        Value t2 = t.getALimit();
        t2.A(t.P$src$Ljava_lang_Object_$qcpui1());
        t2.o(valueSnapshot.J());
        t2.O(new SnapshotValueAccessor(valueSnapshot, t2));
        return t2;
    }

    public static Object z(Object object) {
        if (object instanceof OptionalLimitEntry || object instanceof ItemLimitData) {
            return object.toString();
        }
        return null;
    }

    public static GuiComponent v(Value<?, ?> value, boolean bl) {
        return ValueComponentFactory.K(value, bl, ValueComponentMode.MAIN);
    }
}
