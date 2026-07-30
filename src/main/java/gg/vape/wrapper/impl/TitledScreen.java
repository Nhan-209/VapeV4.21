package gg.vape.wrapper.impl;

public class TitledScreen
extends Screen {
    public String E() {
        if (ForgeVersion.MC_1_7_10.L()) {
            return "";
        }
        if (ForgeVersion.MC_1_16_5.d()) {
            ITextComponent t3_02 = new ITextComponent(TitledScreen.vapeInstance.getMappingsMapperCompat().ql.o(this.I));
            return t3_02.getFormattedText();
        }
        return (String)TitledScreen.vapeInstance.getMappingsMapperCompat().ql.o(this.I);
    }

    public TitledScreen(Object object) {
        super(object);
    }

}

