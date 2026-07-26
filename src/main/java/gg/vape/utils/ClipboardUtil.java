package gg.vape.utils;

import gg.vape.Vape;
import gg.vape.wrapper.impl.Minecraft;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ClipboardUtil {
    private static int T = 0;

    public static void setText(String string) {
        try {
            if (T == 0) {
                ClipboardUtil.F();
            }
            switch (T) {
                case 1: {
                    StringSelection stringSelection = new StringSelection(string);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, stringSelection);
                }
                case 2: {
                    Minecraft.r().y(string);
                }
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    public static String getText() {
        try {
            if (T == 0) {
                ClipboardUtil.F();
            }
            switch (T) {
                case 1: {
                    Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                    if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        return (String)transferable.getTransferData(DataFlavor.stringFlavor);
                    }
                }
                case 2: {
                    Minecraft.r().F();
                }
            }
            return "";
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            return "";
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }

    private static void F() {
        int n;
        boolean bl = GraphicsEnvironment.isHeadless();
        T = n = bl ? 2 : 1;
    }
}

