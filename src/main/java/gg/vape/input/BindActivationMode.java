package gg.vape.input;


public enum BindActivationMode {
    TOGGLE("Toggle"),
    HELD("Enable while held");

    private static final /* synthetic */ BindActivationMode[] V;
    private final String l;


    public String b() {
        return this.l;
    }

    public BindActivationMode I() {
        return this == TOGGLE ? HELD : TOGGLE;
    }

    static {
        String[] stringArray = new String[]{"Toggle", "TOGGLE", "HELD", "Enable while held"};


        V = new BindActivationMode[]{TOGGLE, HELD};
    }

    private BindActivationMode(String string2) {
        this.l = string2;
    }
}

