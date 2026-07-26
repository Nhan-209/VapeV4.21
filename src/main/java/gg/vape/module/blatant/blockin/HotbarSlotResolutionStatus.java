package gg.vape.module.blatant.blockin;

public enum HotbarSlotResolutionStatus {
    PENDING,
    SUCCESS,
    FAIL;

    private static final HotbarSlotResolutionStatus[] j;

    static {
        String[] stringArray = new String[]{"SUCCESS", "FAIL", "PENDING"};



        j = new HotbarSlotResolutionStatus[]{PENDING, SUCCESS, FAIL};
    }

}

