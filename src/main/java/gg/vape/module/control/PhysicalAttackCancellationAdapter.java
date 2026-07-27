package gg.vape.module.control;

import gg.vape.event.impl.EventKeyInputBase;
import gg.vape.module.control.AttackCancellationAdapter;
import gg.vape.module.control.AttackCancellationMarker;

public class PhysicalAttackCancellationAdapter
implements AttackCancellationAdapter {
    private final EventKeyInputBase keyInputEvent;

    @Override
    public void u(boolean bl) {
        this.keyInputEvent.setCancelled(bl);
    }

    private PhysicalAttackCancellationAdapter(EventKeyInputBase eventKeyInputBase) {
        this.keyInputEvent = eventKeyInputBase;
    }

    public PhysicalAttackCancellationAdapter(EventKeyInputBase eventKeyInputBase, AttackCancellationMarker om_12) {
        this(eventKeyInputBase);
    }
}

