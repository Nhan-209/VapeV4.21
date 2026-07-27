package gg.vape.module.combat.crystalaura;

public enum CrystalAuraActionState {
    IDLE,
    BREAKING_CRYSTAL,
    PLACING_CRYSTAL,
    PLACING_OBSIDIAN;

    private static final CrystalAuraActionState[] VALUES;

    static {
        String[] stringArray = new String[]{"PLACING_OBSIDIAN", "PLACING_CRYSTAL", "IDLE", "BREAKING_CRYSTAL"};




        VALUES = new CrystalAuraActionState[]{IDLE, BREAKING_CRYSTAL, PLACING_CRYSTAL, PLACING_OBSIDIAN};
    }

}

