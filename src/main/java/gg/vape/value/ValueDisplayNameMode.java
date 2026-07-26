package gg.vape.value;

public enum ValueDisplayNameMode {
    FULL,
    SIMPLE,
    CUSTOM;

    private static final ValueDisplayNameMode[] N;

    static {
        String[] stringArray = new String[]{"FULL", "SIMPLE", "CUSTOM"};



        N = new ValueDisplayNameMode[]{FULL, SIMPLE, CUSTOM};
    }
}

