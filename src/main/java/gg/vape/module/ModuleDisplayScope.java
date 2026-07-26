package gg.vape.module;

public enum ModuleDisplayScope {
    ALL,
    STANDALONE_ONLY,
    FRAMES_ONLY;

    private static final ModuleDisplayScope[] y;

    static {
        String[] stringArray = new String[]{"STANDALONE_ONLY", "ALL", "FRAMES_ONLY"};



        y = new ModuleDisplayScope[]{ALL, STANDALONE_ONLY, FRAMES_ONLY};
    }

}

