package gg.vape.module.utility.inventory.cleaner;

public enum InventoryMatcherListMode {
    WHITELIST,
    BLACKLIST;

    private static final InventoryMatcherListMode[] H;

    static {
        String[] stringArray = new String[]{"WHITELIST", "BLACKLIST"};


        H = new InventoryMatcherListMode[]{WHITELIST, BLACKLIST};
    }

}

