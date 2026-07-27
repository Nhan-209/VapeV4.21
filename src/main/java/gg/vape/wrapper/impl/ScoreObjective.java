package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MScoreObjective;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.ForgeVersion;
import gg.vape.wrapper.impl.ITextComponent;
import gg.vape.wrapper.impl.Scoreboard;
import gg.vape.wrapper.impl.TextComponent;

public class ScoreObjective
extends Wrapper {
    public TextComponent K() {
        return new TextComponent(MScoreObjective.B(ScoreObjective.c.getMappingsMapperCompat().qL, this.I));
    }

    public ITextComponent i() {
        ITextComponent iTextComponent = new ITextComponent(MScoreObjective.g(ScoreObjective.c.getMappingsMapperCompat().qL, this.I));
        return iTextComponent;
    }

    public Scoreboard P() {
        return new Scoreboard(MScoreObjective.r(ScoreObjective.c.getMappingsMapperCompat().qL, this.I));
    }

    public String h() {
        if (ForgeVersion.MC_1_16_5.d()) {
            return this.i().C();
        }
        return MScoreObjective.O(ScoreObjective.c.getMappingsMapperCompat().qL, this.I);
    }

    public ScoreObjective(Object object) {
        super(object);
    }

}

