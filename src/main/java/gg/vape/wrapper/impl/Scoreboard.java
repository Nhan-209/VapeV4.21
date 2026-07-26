package gg.vape.wrapper.impl;

import com.google.common.collect.Lists;
import gg.vape.mapping.mappings.MScoreboard;
import gg.vape.wrapper.Wrapper;
import gg.vape.wrapper.impl.Score;
import gg.vape.wrapper.impl.ScoreObjective;
import gg.vape.wrapper.impl.ScorePlayerTeam;
import java.util.ArrayList;
import java.util.Collection;

public class Scoreboard
extends Wrapper {
    public Collection<Score> p(ScoreObjective scoreObjective) {
        Collection collection = MScoreboard.u(Scoreboard.c.getMappings().Dz, this.I, scoreObjective.getObject());
        ArrayList arrayList = Lists.newArrayList();
        for (Object e : collection) {
            arrayList.add(new Score(e));
        }
        return arrayList;
    }

    public Scoreboard(Object object) {
        super(object);
    }

    public ScorePlayerTeam l(String string) {
        return new ScorePlayerTeam(MScoreboard.R(Scoreboard.c.getMappings().Dz, this.I, string));
    }
}

