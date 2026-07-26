package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.ImageRenderer;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;

public class LocalImageDebugRenderer {
    private static String L;
    static final String G;

    public static String S() {
        return L;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    public static GlImageTexture R() {
        GlImageTexture glImageTexture = null;
        try {
            glImageTexture = new GlImageTexture(new FileInputStream("C:\\Users\\Moham\\Desktop\\pngs\\Group 5709.png"));
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
        return glImageTexture;
    }

    public static void G() {
        try {
            GlImageTexture glImageTexture = LocalImageDebugRenderer.R();
            if (glImageTexture != null) {
                Vape.debugLog("drawing");
                ImageRenderer.u(Color.BLACK, 10.0f, 10.0f, glImageTexture, 20.0f, 20.0f, true);
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    static {
        LocalImageDebugRenderer.p(null);
        G = "C:\\Users\\Moham\\Desktop\\pngs\\Group 5709.png";
    }

    public static void p(String string) {
        L = string;
    }
}

