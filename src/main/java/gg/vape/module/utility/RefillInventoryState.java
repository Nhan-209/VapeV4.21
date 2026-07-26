package gg.vape.module.utility;

import gg.vape.Vape;
import gg.vape.runtime.ObfuscatedRuntimeException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface RefillInventoryState {
    public static final Map r = new HashMap(13);
    public static final Set<String> c = new HashSet<String>(Arrays.asList("print", "isLoggingEnabled", "isStackTraceEnabled", "getStackTrace"));
    public static final String[] j = null;
    public static final String[] o = new String[7];

    default public void a$src$V$1hjaphc(String string) {
        if (this.p()) {
            if (!this.S$src$Z$1kwx4c8()) {
                Vape.debugLog(string);
            } else {
                String string2;
                StackTraceElement stackTraceElement = null;
                for (StackTraceElement stackTraceElement2 : Thread.currentThread().getStackTrace()) {
                    string2 = stackTraceElement2.getMethodName();
                    if (c.contains(string2)) continue;
                    stackTraceElement = stackTraceElement2;
                    break;
                }
                if (stackTraceElement == null) {
                    Vape.debugLog(string);
                    return;
                }
                Object[] objectArray = stackTraceElement.getClassName().split("\\.");
                Object object = objectArray[objectArray.length - 1];
                String string3 = stackTraceElement.getMethodName();
                int n = stackTraceElement.getLineNumber();
                string2 = String.format("<%s#%s:%s>", object, string3, n);
                Vape.debugLog(String.format("%s %s", string2, string));
            }
        }
    }

    default public boolean S$src$Z$1kwx4c8() {
        return true;
    }

    default public boolean p() {
        return true;
    }

    default public void t(String string, Object ... objectArray) {
        this.a$src$V$1hjaphc(String.format(string, objectArray));
    }

    private static ObfuscatedRuntimeException a(ObfuscatedRuntimeException obfuscatedRuntimeException) {
        return obfuscatedRuntimeException;
    }
}
