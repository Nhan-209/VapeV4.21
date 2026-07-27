package gg.vape.module.blatant.blockin;

public enum HotbarSlotResolutionStatus {
    PENDING,
    SUCCESS,
    FAIL;

    private static final HotbarSlotResolutionStatus[] VALUES;

    static {
        String[] names = new String[]{"SUCCESS", "FAIL", "PENDING"};



        VALUES = new HotbarSlotResolutionStatus[]{PENDING, SUCCESS, FAIL};
    }

}

