package gg.vape.module.combat.crystalaura;


public class ExplosionType {
    public static final ExplosionType ANCHOR;
    public static final ExplosionType BED;
    private static int initState;
    private final String label;
    public static final ExplosionType Q;
    private final float explosionPower;

    static {
        if (ExplosionType.q() == 0) {
            ExplosionType.G(55);
        }
        String[] stringArray = new String[]{"Bed", "Crystal", "Anchor"};
        Q = new ExplosionType(6.0f, stringArray[1]);
        ANCHOR = new ExplosionType(5.0f, stringArray[2]);
        BED = new ExplosionType(5.0f, stringArray[0]);
    }

    public static void G(int n) {
        initState = n;
    }

    private ExplosionType(float power, String name) {
        this.explosionPower = power;
        this.label = name;
    }

    public String G() {
        return this.label;
    }

    public static int q() {
        int n = ExplosionType.R();
        if (n == 0) {
            return 87;
        }
        return 0;
    }


    public float I() {
        return this.explosionPower;
    }

    public static int R() {
        return initState;
    }
}

