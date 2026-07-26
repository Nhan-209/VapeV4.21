package gg.vape.module.utility.autopearl;

public enum AutoPearlState {
    ACQUIRING_PEARL,
    ACQUIRING_AIMLOCK,
    PENDING_AIMJOB,
    PENDING_THROW,
    PENDING_RESET;

    private static final AutoPearlState[] V;

    static {
        String[] stringArray = new String[]{"ACQUIRING_PEARL", "PENDING_RESET", "PENDING_THROW", "PENDING_AIMJOB", "ACQUIRING_AIMLOCK"};





        V = new AutoPearlState[]{ACQUIRING_PEARL, ACQUIRING_AIMLOCK, PENDING_AIMJOB, PENDING_THROW, PENDING_RESET};
    }

}

