package gg.vape.module.combat.crystalaura;

public enum CrystalAuraActionState {
    IDLE,
    BREAKING_CRYSTAL,
    PLACING_CRYSTAL,
    PLACING_OBSIDIAN;

    private static final CrystalAuraActionState[] Z;

    static {
        String[] stringArray = new String[]{"PLACING_OBSIDIAN", "PLACING_CRYSTAL", "IDLE", "BREAKING_CRYSTAL"};




        Z = new CrystalAuraActionState[]{IDLE, BREAKING_CRYSTAL, PLACING_CRYSTAL, PLACING_OBSIDIAN};
    }

}

