package gg.vape.ui.click.frame.impl.hud;

import gg.vape.ui.click.frame.impl.hud.HudSnapEdge;
import java.util.EnumMap;
import java.util.Map;

public class HudSnapCandidate {
    private Map<HudSnapEdge, Double> V = new EnumMap<HudSnapEdge, Double>(HudSnapEdge.class);
    private HudSnapEdge q;
    private double n;
    private HudSnapEdge T;

    public HudSnapEdge a_fq_0_n() {
        return this.T;
    }

    public Map<HudSnapEdge, Double> m() {
        return this.V;
    }

    public HudSnapCandidate(double d, double d2, double d3, double d4, HudSnapEdge hudSnapEdge, HudSnapEdge hudSnapEdge2, double d5) {
        this.V.put(HudSnapEdge.LEFT, d);
        this.V.put(HudSnapEdge.TOP, d2);
        this.V.put(HudSnapEdge.RIGHT, d3);
        this.V.put(HudSnapEdge.BOTTOM, d4);
        this.q = hudSnapEdge;
        this.T = hudSnapEdge2;
        this.n = d5;
    }

    public double M() {
        return this.V.get((Object)HudSnapEdge.RIGHT) - this.V.get((Object)HudSnapEdge.LEFT);
    }

    public double T() {
        return this.n;
    }

    public double double_n() {
        return this.V.get((Object)HudSnapEdge.BOTTOM) - this.V.get((Object)HudSnapEdge.TOP);
    }

    public HudSnapEdge b() {
        return this.q;
    }

    public /* synthetic */ double n() {
        return this.double_n();
    }

    public /* synthetic */ HudSnapEdge n$src$Lgg_vape_ui_click_frame_impl_hud_HudSnapEdge_$8n1nf9() {
        return this.a_fq_0_n();
    }
}

