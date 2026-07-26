package gg.vape.mapping;

import gg.vape.Vape;
import gg.vape.event.impl.EventScoreboardScores;
import gg.vape.mapping.EventInjectionSpec;
import gg.vape.mapping.JavassistMappingTask;
import gg.vape.mapping.MappedClasses;

public class ScoreboardScoresEventMappingTask
extends JavassistMappingTask {
    private static final String c = "$event.getScores();";

    @Override
    public void c() {
        EventInjectionSpec eventInjectionSpec = new EventInjectionSpec(Vape.INSTANCE.getMappings().Dz.P, EventScoreboardScores.class);
        eventInjectionSpec.H(c);
        this.O(eventInjectionSpec);
    }

    public ScoreboardScoresEventMappingTask() {
        super(MappedClasses.F6);
    }
}

