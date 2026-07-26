package gg.vape.module.combat.crystalaura;

public enum CrystalAuraAction {
    PLACING_OBSIDIAN,
    PLACING_CRYSTAL,
    ATTACKING_CRYSTAL;

    private static final CrystalAuraAction[] c;

    static {
        String[] stringArray = new String[]{"PLACING_CRYSTAL", "PLACING_OBSIDIAN", "ATTACKING_CRYSTAL"};



        c = new CrystalAuraAction[]{PLACING_OBSIDIAN, PLACING_CRYSTAL, ATTACKING_CRYSTAL};
    }

}

