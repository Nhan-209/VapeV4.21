package gg.vape.utils.render;

import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RemoteImageTextureCache;
import gg.vape.utils.render.RemoteImageTextureCacheUpdateThread;
import java.util.concurrent.ConcurrentHashMap;

public class RemoteImageTextureManager {
    private ConcurrentHashMap<Integer, RemoteImageTextureCache> n = new ConcurrentHashMap();
    private static final String b;
    private static RemoteImageTextureManager i;

    private static String a(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    static ConcurrentHashMap<Integer, RemoteImageTextureCache> L(RemoteImageTextureManager remoteImageTextureManager) {
        return remoteImageTextureManager.n;
    }

    public GlImageTexture r(String string, int n) {
        if (this.n.containsKey(n)) {
            GlImageTexture glImageTexture = this.n.get(n).g(string);
            if (glImageTexture == null) {
                return ImageRenderer.loadResource(b, false, false);
            }
            return glImageTexture;
        }
        return null;
    }

    public static RemoteImageTextureManager e() {
        return i;
    }


    public RemoteImageTextureManager() {
        new RemoteImageTextureCacheUpdateThread(this).start();
        this.n.put(32, new RemoteImageTextureCache(32));
    }

    static {
        try {
            b = "default_user";
            i = new RemoteImageTextureManager();
        }
        catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }
}
