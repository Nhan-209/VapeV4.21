package gg.vape.module.render.hud;

import com.google.common.base.Predicate;
import gg.vape.module.render.hud.ScoreboardHudModule;
import gg.vape.wrapper.impl.Score;

class ScoreboardVisibleScorePredicate
implements Predicate<Score> {
    final ScoreboardHudModule o;

    @Override
    public boolean apply(Score score) {
        return this.I(score);
    }


    public boolean I(Score score) {
        return score.P() != null && !score.P().startsWith("#");
    }

    ScoreboardVisibleScorePredicate(ScoreboardHudModule scoreboardHudModule) {
        this.o = scoreboardHudModule;
    }
}
