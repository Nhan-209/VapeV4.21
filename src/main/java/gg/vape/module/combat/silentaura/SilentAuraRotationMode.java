package gg.vape.module.combat.silentaura;

public enum SilentAuraRotationMode {
    IDLE,
    FLICKING_AWAY,
    ATTACKING;

    private static final SilentAuraRotationMode[] VALUES;

    static {
        String[] enumNames = new String[]{"IDLE", "FLICKING_AWAY", "ATTACKING"};



        VALUES = new SilentAuraRotationMode[]{IDLE, FLICKING_AWAY, ATTACKING};
    }
}

