package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.unmap.ImageParser$Format;
import gg.vape.utils.render.GlImageTexture;
import gg.vape.utils.render.ImageRenderer;
import gg.vape.utils.render.RemoteImageTextureLoader;
import java.io.ByteArrayInputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

class RemoteImageTextureCache {
    private final int u;
    private ConcurrentLinkedQueue<String> l = new ConcurrentLinkedQueue();
    private ConcurrentHashMap<String, GlImageTexture> X;
    private ConcurrentHashMap<String, byte[]> a = new ConcurrentHashMap();

    void G() {
        while (!this.l.isEmpty()) {
            this.q(this.l.poll());
        }
    }

    public RemoteImageTextureCache(int n) {
        this.X = new ConcurrentHashMap();
        this.u = n;
    }

    byte[] W(String string) {
        if (this.a.containsKey(string)) {
            return this.a.get(string);
        }
        if (!this.l.contains(string)) {
            this.l.add(string);
        }
        return null;
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    GlImageTexture g(String string) {
        if (this.X.containsKey(string)) {
            return this.X.get(string);
        }
        byte[] byArray = this.W(string);
        if (byArray == null) {
            return null;
        }
        GlImageTexture glImageTexture = null;
        try {
            glImageTexture = new GlImageTexture(new ByteArrayInputStream(byArray), 9729, ImageParser$Format.RGBA);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            glImageTexture = ImageRenderer.loadResource("default_user", false, false);
        }
        this.X.put(string, glImageTexture);
        return glImageTexture;
    }

    void q(String string) {
        byte[] byArray = RemoteImageTextureLoader.L("https://minotar.net/avatar/" + string + "/" + this.u + ".png");
        this.a.put(string, byArray);
    }
}

