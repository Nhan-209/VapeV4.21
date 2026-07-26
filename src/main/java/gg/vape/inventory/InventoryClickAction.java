package gg.vape.inventory;

public enum InventoryClickAction {
    MOVE,
    SWAP,
    SHIFTCLICK,
    CLICK,
    DROP_SLOT,
    DROP_MOUSE_STACK;

    private static final InventoryClickAction[] w;

    static {
        String[] stringArray = new String[]{"DROP_MOUSE_STACK", "MOVE", "CLICK", "SHIFTCLICK", "DROP_SLOT", "SWAP"};






        w = new InventoryClickAction[]{MOVE, SWAP, SHIFTCLICK, CLICK, DROP_SLOT, DROP_MOUSE_STACK};
    }
}

