package gg.vape.config;

public enum PublicProfileReviewDisplayType {
    OTHER,
    SELF,
    REPLY;

    private static final PublicProfileReviewDisplayType[] W;

    static {
        String[] stringArray = new String[]{"REPLY", "OTHER", "SELF"};



        W = new PublicProfileReviewDisplayType[]{OTHER, SELF, REPLY};
    }
}

