package gg.vape.account;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gg.vape.Vape;
import gg.vape.account.LicenseStatus;
import gg.vape.account.LicenseStatusResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LicenseStatusClient {
    HttpURLConnection S;
    private final Gson j = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public LicenseStatus J(String string) {
        try {
            String string2;
            URL uRL = new URL("http://api.thealtening.com/v2/generate?key=" + string);
            this.S = (HttpURLConnection)uRL.openConnection();
            this.S.setRequestMethod("GET");
            this.S.setUseCaches(false);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.S.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            while ((string2 = bufferedReader.readLine()) != null) {
                stringBuffer.append(string2);
                stringBuffer.append('\r');
            }
            bufferedReader.close();
            LicenseStatusResponse licenseStatusResponse = (LicenseStatusResponse)this.j.fromJson(stringBuffer.toString(), LicenseStatusResponse.class);
            if (licenseStatusResponse.e() != null && !licenseStatusResponse.e().equals("")) {
                return new LicenseStatus(licenseStatusResponse.e());
            }
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
            try {
                if (this.S.getResponseCode() == 403) {
                    return null;
                }
                System.out.println("Unhandled error code: " + this.S.getResponseCode());
            }
            catch (IOException iOException) {
                Vape.logThrowable(iOException);
            }
        }
        return null;
    }

    private static Exception a(Exception exception) {
        return exception;
    }
}

