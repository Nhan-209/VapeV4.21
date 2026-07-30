package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MScoreObjective;
import gg.vape.wrapper.Wrapper;

public class ScoreObjective
extends Wrapper {
    public TextComponent K() {
        return new TextComponent(MScoreObjective.B(ScoreObjective.vapeInstance.getMappingsMapperCompat().qL, this.I));
    }

    public ITextComponent i() {
        ITextComponent iTextComponent = new ITextComponent(MScoreObjective.g(ScoreObjective.vapeInstance.getMappingsMapperCompat().qL, this.I));
        return iTextComponent;
    }

    public Scoreboard P() {
        return new Scoreboard(MScoreObjective.r(ScoreObjective.vapeInstance.getMappingsMapperCompat().qL, this.I));
    }

    public String h() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.i().getFormattedText();
        }
        return MScoreObjective.O(ScoreObjective.vapeInstance.getMappingsMapperCompat().qL, this.I);
    }

    public ScoreObjective(Object object) {
        super(object);
    }

}

