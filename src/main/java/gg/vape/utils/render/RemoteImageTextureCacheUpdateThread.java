package gg.vape.utils.render;

import gg.vape.Vape;
import gg.vape.utils.SleepUtil;
import gg.vape.utils.render.RemoteImageTextureCache;
import gg.vape.utils.render.RemoteImageTextureManager;

class RemoteImageTextureCacheUpdateThread
extends Thread {
    final RemoteImageTextureManager B;

    @Override
    public void run() {
        while (!Vape.INSTANCE.isEnabled()) {
            SleepUtil.sleep(50L);
            for (Integer n : RemoteImageTextureManager.L(this.B).keySet()) {
                ((RemoteImageTextureCache)RemoteImageTextureManager.L(this.B).get(n)).G();
            }
        }
    }

    RemoteImageTextureCacheUpdateThread(RemoteImageTextureManager remoteImageTextureManager) {
        this.B = remoteImageTextureManager;
    }
}

