package gg.vape.tutorial;

import gg.vape.tutorial.TutorialActionComponent;
import gg.vape.tutorial.TutorialPage;

public abstract class TutorialAction {
    private static String[] o;
    private TutorialPage j;
    private final TutorialActionComponent M;

    public TutorialActionComponent I() {
        return this.M;
    }

    public boolean a() {
        return true;
    }

    public void G(TutorialPage tutorialPage) {
        this.j = tutorialPage;
    }

    public abstract boolean boolean_X();

    public void S() {
    }

    public TutorialAction(TutorialActionComponent tutorialActionComponent) {
        this.M = tutorialActionComponent;
    }

    public void void_X() {
    }

    public void void_w() {
    }

    public TutorialPage T() {
        return this.j;
    }

    public static void Q(String[] stringArray) {
        o = stringArray;
    }

    public static String[] java_lang_String_arr_w() {
        return o;
    }

    static {
        if (TutorialAction.java_lang_String_arr_w() == null) {
            TutorialAction.Q(new String[2]);
        }
    }

    public /* synthetic */ void X$src$V$d06mc() {
        this.void_X();
    }

    public /* synthetic */ boolean X() {
        return this.boolean_X();
    }

    public /* synthetic */ void w() {
        this.void_w();
    }
}

