package gg.vape.account;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gg.vape.Vape;
import gg.vape.account.LicenseInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LicenseInfoClient {
    HttpURLConnection l;
    private final Gson F = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public LicenseInfo v(String string) {
        try {
            String string2;
            URL uRL = new URL("http://api.thealtening.com/v2/license?key=" + string);
            this.l = (HttpURLConnection)uRL.openConnection();
            this.l.setRequestMethod("GET");
            this.l.setUseCaches(false);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.l.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while ((string2 = bufferedReader.readLine()) != null) {
                stringBuffer.append(string2);
                stringBuffer.append('\r');
            }
            bufferedReader.close();
            return (LicenseInfo)this.F.fromJson(stringBuffer.toString(), LicenseInfo.class);
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            try {
                if (this.l.getResponseCode() == 403) {
                    return null;
                }
                System.out.println("Unhandled error code: " + this.l.getResponseCode());
            }
            catch (IOException iOException) {
                Vape.logThrowable(iOException);
            }
            return null;
        }
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

