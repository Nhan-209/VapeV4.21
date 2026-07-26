package gg.vape.utils.render;

import gg.vape.Vape;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

public class RemoteImageHttpDownloader {
    private static final Map<String, byte[]> H;
    private static final String F;
    private static boolean u;

    public static byte[] A(String string) {
        File file = new File(F);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (H.containsKey(string)) {
            return H.get(string);
        }
        try {
            byte[] byArray = Files.readAllBytes(new File(F + string + ".png").toPath());
            H.put(string, byArray);
            return byArray;
        }
        catch (IOException iOException) {
            Vape.logThrowable(iOException);
            H.put(string, null);
            return null;
        }
    }

    public static void C(boolean bl) {
        u = bl;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    static {
        RemoteImageHttpDownloader.C(true);
        F = System.getProperty("user.home") + File.separator + "vapeTextures" + File.separator;
        H = new LinkedHashMap<String, byte[]>();
    }

    public static boolean W() {
        boolean bl = RemoteImageHttpDownloader.q();
        return false;
    }

    public static boolean q() {
        return u;
    }
}

