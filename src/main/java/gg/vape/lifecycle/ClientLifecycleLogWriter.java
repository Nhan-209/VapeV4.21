package gg.vape.lifecycle;

import gg.vape.Vape;
import gg.vape.lifecycle.ClientLifecycleCallback;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientLifecycleLogWriter
implements ClientLifecycleCallback {
    private File C;
    private SimpleDateFormat j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private PrintWriter M;

    private void lambda$new$0() {
        this.B();
    }

    public ClientLifecycleLogWriter() {
        try {
            String string;
            File file;
            String string2 = System.getenv("APPDATA");
            if (string2 == null) {
                string2 = System.getProperty("user.home");
            }
            if (!(file = new File(string = string2 + File.separator + ".vapeclient")).exists()) {
                file.mkdirs();
            }
            String string3 = string + File.separator + "log-" + this.j.format(new Date()).replace(":", "-") + ".txt";
            Vape.debugLog("Creating log file at: " + string3);
            this.C = new File(string3);
            FileWriter fileWriter = new FileWriter(this.C, false);
            this.M = new PrintWriter(fileWriter);
            Runtime.getRuntime().addShutdownHook(new Thread(this::lambda$new$0));
        }
        catch (IOException iOException) {
            Vape.logThrowable(iOException);
        }
    }

    private static IOException a(IOException iOException) {
        return iOException;
    }

    @Override
    public void s(String string) {
        try {
            String string2 = this.j.format(new Date());
            this.M.printf("%s: %s%n", string2, string);
            this.M.flush();
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    @Override
    public void B() {
        try {
            if (this.M != null) {
                this.M.close();
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }
}

