package gg.vape.lifecycle;

import gg.vape.lifecycle.ClientLifecycleCallback;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.io.File;

public class ClientDirectoryCleanupCallback
implements ClientLifecycleCallback {
    @Override
    public void s(String string) {
    }

    public ClientDirectoryCleanupCallback() {
        String string = System.getenv("APPDATA");
        String string2 = string + File.separator + ".vapeclient";
        File file = new File(string2);
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                if (file2.getName().equals("cache")) continue;
                file2.delete();
            }
        }
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }

    @Override
    public void B() {
    }
}

