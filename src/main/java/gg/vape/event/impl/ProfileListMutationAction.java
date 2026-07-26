package gg.vape.event.impl;

public enum ProfileListMutationAction {
    ADD,
    REMOVE;

    private static final ProfileListMutationAction[] U;

    static {
        String[] stringArray = new String[]{"REMOVE", "ADD"};


        U = new ProfileListMutationAction[]{ADD, REMOVE};
    }
}

