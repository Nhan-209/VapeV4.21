package gg.vape.wrapper.impl;

import gg.vape.mapping.mappings.MScore;
import gg.vape.wrapper.Wrapper;

public class Score
extends Wrapper {
    public Score(Object object) {
        super(object);
    }

    public String P() {
        return MScore.n(Score.vapeInstance.getMappingsMapperCompat().Cm, this.I);
    }

    public int j() {
        return MScore.t(Score.vapeInstance.getMappingsMapperCompat().Cm, this.I);
    }
}

