package gg.vape.module.combat.silentaura;

public enum SilentAuraRotationMode {
    IDLE,
    FLICKING_AWAY,
    ATTACKING;

    private static final SilentAuraRotationMode[] a;

    static {
        String[] stringArray = new String[]{"IDLE", "FLICKING_AWAY", "ATTACKING"};



        a = new SilentAuraRotationMode[]{IDLE, FLICKING_AWAY, ATTACKING};
    }
}

