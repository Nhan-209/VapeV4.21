package gg.vape.config;

import java.util.Arrays;
import java.util.List;

public enum PublicProfileSortMode {
    RATED("rated", "Top Rated"),
    DOWNLOADED("downloaded", "Most Downloaded"),
    NEWEST("newest", "Newest");

    private final String j;
    public static final List<PublicProfileSortMode> VALUES;
    private static final /* synthetic */ PublicProfileSortMode[] u;
    private final String T;

    public String y() {
        return this.T;
    }

    public String C() {
        return this.j;
    }

    private PublicProfileSortMode(String string2, String string3) {
        this.j = string2;
        this.T = string3;
    }

    static {
        String[] stringArray = new String[]{"rated", "Most Downloaded", "newest", "downloaded", "Top Rated", "Newest", "RATED", "NEWEST", "DOWNLOADED"};



        u = new PublicProfileSortMode[]{RATED, DOWNLOADED, NEWEST};
        VALUES = Arrays.asList(PublicProfileSortMode.values());
    }

}

