package gg.vape.ui.click.frame.impl.profile;

import gg.vape.config.ProfileModuleSnapshot;
import gg.vape.config.ProfileSnapshot;
import gg.vape.module.utility.inventory.HotbarSlotRuleValue;
import gg.vape.ui.click.GuiMouseEvent;
import gg.vape.ui.click.component.GuiComponent;
import gg.vape.ui.click.component.SimpleTextLabelComponent;
import gg.vape.ui.click.component.TruncatedTextComponent;
import gg.vape.unmap.ColorUtil;
import gg.vape.unmap.ModeSelection;
import gg.vape.utils.MutableColor;
import gg.vape.utils.render.GuiRenderPrimitives;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.value.BooleanValue;
import gg.vape.value.ColorValue;
import gg.vape.value.EntityTargetFilterValue;
import gg.vape.value.ListValue;
import gg.vape.value.ModeValue;
import gg.vape.value.NumberValue;
import gg.vape.value.RandomValue;
import gg.vape.value.StringMapValue;
import gg.vape.value.Value;
import gg.vape.value.ValueSnapshot;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class ProfileSnapshotValueRowComponent
extends GuiComponent {
    private final ValueSnapshot<?, ?> I;
    private final ProfileModuleSnapshot O;
    private final TruncatedTextComponent G;
    private final SimpleTextLabelComponent i;
    private final ProfileSnapshot v;

    @Override
    public void g(GuiMouseEvent guiMouseEvent) {
    }

    @Override
    public void I() {
    }

    @Override
    public void H() {
        int n;
        boolean peaceful;
        String string = ((Value)this.I.W()).getName();
        if (this.I.W() instanceof ListValue && ((Value)this.I.W()).getParent() != null) {
            string = string + " (" + ((Value)this.I.W()).getParent().getName() + ")";
        }
        this.G.O(string);
        if (this.I.W() instanceof BooleanValue) {
            if (((Boolean)this.I.J()).booleanValue()) {
                this.i.G("ON");
            } else {
                this.i.G("OFF");
            }
        } else if (this.I.W() instanceof RandomValue) {
            RandomValue randomValue = (RandomValue)this.I.W();
            double[] dArray = (double[])this.I.J();
            this.i.G(randomValue.y$src$Ljava_text_DecimalFormat_$bdq2sj().format(dArray[0]) + " - " + randomValue.y$src$Ljava_text_DecimalFormat_$bdq2sj().format(dArray[1]));
        } else if (this.I.W() instanceof NumberValue) {
            NumberValue numberValue = (NumberValue)this.I.W();
            Double d = (Double)this.I.J();
            this.i.G(numberValue.Q$src$Ljava_text_DecimalFormat_$j98hth().format(d) + numberValue.T());
        } else if (this.I.W() instanceof ListValue) {
            List<?> list = (List<?>)this.I.J();
            int n2 = list.size();
            this.i.G(n2 + " item" + (n2 == 1 ? "" : "s"));
            this.i.w(list.toString());
        } else if (this.I.W() instanceof ModeValue) {
            ModeSelection modeSelection = (ModeSelection)this.I.J();
            this.i.G(modeSelection.getName());
        } else if (this.I.W() instanceof HotbarSlotRuleValue) {
            List<?> hotbarRules = (List<?>)this.I.J();
            int n3 = hotbarRules.size();
            this.i.G(n3 + " item" + (n3 == 1 ? "" : "s"));
        } else if (this.I.W() instanceof StringMapValue) {
            Map<?, ?> stringMap = (Map<?, ?>)this.I.J();
            int n4 = stringMap.size();
            this.i.G(n4 + " item" + (n4 == 1 ? "" : "s"));
        } else if (this.I.W() instanceof EntityTargetFilterValue) {
            this.G.O("Target Settings");
            Boolean[] targetSettings = (Boolean[])this.I.J();
            EntityTargetFilterValue entityTargetFilterValue = (EntityTargetFilterValue)this.I.W();
            boolean bl = targetSettings[entityTargetFilterValue.i().indexOf(entityTargetFilterValue.D())];
            boolean bl2 = targetSettings[entityTargetFilterValue.i().indexOf(entityTargetFilterValue.f())];
            peaceful = targetSettings[entityTargetFilterValue.i().indexOf(entityTargetFilterValue.r$src$Lgg_vape_value_BooleanValue_$167auuf())].booleanValue();
            boolean bl3 = targetSettings[entityTargetFilterValue.i().indexOf(entityTargetFilterValue.S$src$Lgg_vape_value_BooleanValue_$7aakrq())];
            boolean bl4 = targetSettings[entityTargetFilterValue.i().indexOf(entityTargetFilterValue.E())];
            boolean bl5 = targetSettings[entityTargetFilterValue.i().indexOf(entityTargetFilterValue.q$src$Lgg_vape_value_BooleanValue_$4eyax4())];
            boolean bl6 = targetSettings[entityTargetFilterValue.i().indexOf(entityTargetFilterValue.x())];
            int n5 = 0;
            for (Boolean object : targetSettings) {
                if (!object.booleanValue()) continue;
                ++n5;
            }
            this.i.G(n5 + " setting" + (n5 == 1 ? "" : "s"));
            StringJoiner stringJoiner = new StringJoiner(", ");
            stringJoiner.setEmptyValue("none");
            if (bl) {
                stringJoiner.add("players");
            }
            if (bl2) {
                stringJoiner.add("mobs");
            }
            if (peaceful) {
                stringJoiner.add("peaceful");
            }
            if (bl3) {
                stringJoiner.add("neutral");
            }
            StringJoiner stringJoiner2 = new StringJoiner(", ");
            stringJoiner2.setEmptyValue("none");
            if (bl4) {
                stringJoiner2.add("invisible");
            }
            if (bl5) {
                stringJoiner2.add("naked");
            }
            if (bl6) {
                stringJoiner2.add("behind walls");
            }
            this.i.w("Attack " + stringJoiner + " \nIgnore " + stringJoiner2);
        } else if (this.I.W() instanceof ColorValue) {
            this.i.G("   ");
            this.i.o(10.0);
            this.i.Y(10.0);
        } else {
            this.i.G(this.I.W() != null ? this.I.W().toString() : "null");
        }
        double d = this.G$src$D$1b2f02a();
        this.getClass();
        this.G.K(d + 5.0);
        this.G.S(this.n() + 2.0);
        this.G.T(this.d());
        double d2 = this.A();
        this.getClass();
        this.G.D(d2 - (double)(5.0f * 2.0f) - 4.0 - this.i.A());
        this.G.M(0.75);
        double d3 = this.G$src$D$1b2f02a() + this.A() - this.i.h();
        this.getClass();
        this.i.K(d3 - (double)(5.0f * 2.0f));
        this.i.S(this.n());
        this.i.o(this.i.h());
        this.i.l(true);
        if (this.I.W() instanceof ColorValue) {
            Object[] colorComponents = (Object[])this.I.J();
            float f = ((Double)colorComponents[0]).floatValue() / 255.0f;
            float f2 = ((Double)colorComponents[2]).floatValue() / 255.0f;
            float f3 = ((Double)colorComponents[1]).floatValue() / 255.0f;
            n = Color.HSBtoRGB(f, f2, f3);
            MutableColor mutableColor = new MutableColor(n, 255);
            MutableColor mutableColor2 = new MutableColor(ColorUtil.s(mutableColor, 0, 240, true));
            mutableColor2.withAlpha(150);
            this.i.T$src$V$1orl066(mutableColor2);
            ImageRenderer.E(mutableColor, (float)(this.G$src$D$1b2f02a() + this.A() - 5.0 - 6.0), (float)this.n() + 1.0f, "colorpreview", 7.0f, 7.0f, false);
        } else {
            double d4 = this.i.G$src$D$1b2f02a() + 3.0;
            double d5 = this.n();
            double d6 = this.i.A();
            this.getClass();
            GuiRenderPrimitives.B(d4, d5, d6 + 5.0 - 0.5, this.i.L() - 1.0, new Color(255, 255, 255, 10), 1.0f);
        }
    }

    @Override
    public void F() {
    }

    @Override
    public double x() {
        return 80.0;
    }

    public ProfileSnapshotValueRowComponent(ProfileSnapshot profileSnapshot, ProfileModuleSnapshot profileModuleSnapshot, ValueSnapshot<?, ?> valueSnapshot) {
        this.v = profileSnapshot;
        this.O = profileModuleSnapshot;
        this.I = valueSnapshot;
        this.G = new TruncatedTextComponent(((Value)valueSnapshot.W()).getName(), "...", 50.0, 0.75, ProfileSnapshotValueRowComponent.J.Z, false, false);
        this.i = new SimpleTextLabelComponent("", 0.7);
        this.H(this.G, this.i);
    }

    @Override
    public double C() {
        return 15.0;
    }


    @Override
    public void u() {
    }
}
