package gg.vape.module.combat.crystalaura;

public enum CrystalAuraAction {
    PLACING_OBSIDIAN,
    PLACING_CRYSTAL,
    ATTACKING_CRYSTAL;

    private static final CrystalAuraAction[] VALUES;

    static {
        String[] names = new String[]{"PLACING_CRYSTAL", "PLACING_OBSIDIAN", "ATTACKING_CRYSTAL"};



        VALUES = new CrystalAuraAction[]{PLACING_OBSIDIAN, PLACING_CRYSTAL, ATTACKING_CRYSTAL};
    }

}

