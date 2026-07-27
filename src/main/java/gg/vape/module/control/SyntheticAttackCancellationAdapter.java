package gg.vape.module.control;

import gg.vape.event.impl.SyntheticAttackRequestEvent;
import gg.vape.module.control.AttackCancellationAdapter;
import gg.vape.module.control.AttackCancellationMarker;

public class SyntheticAttackCancellationAdapter
implements AttackCancellationAdapter {
    private final SyntheticAttackRequestEvent attackRequestEvent;

    public SyntheticAttackCancellationAdapter(SyntheticAttackRequestEvent eu_22, AttackCancellationMarker om_12) {
        this(eu_22);
    }

    @Override
    public void u(boolean bl) {
        this.attackRequestEvent.setCancelled(bl);
    }

    private SyntheticAttackCancellationAdapter(SyntheticAttackRequestEvent eu_22) {
        this.attackRequestEvent = eu_22;
    }
}

