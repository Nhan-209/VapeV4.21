package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MScore;
import gg.vape.wrapper.Wrapper;

public class Score
extends Wrapper {
    public Score(Object object) {
        super(object);
    }

    public String P() {
        return MScore.n(Score.c.getMappingsMapperCompat().Cm, this.I);
    }

    public int j() {
        return MScore.t(Score.c.getMappingsMapperCompat().Cm, this.I);
    }
}

