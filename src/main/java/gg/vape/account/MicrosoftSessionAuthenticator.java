package gg.vape.account;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.vape.Vape;
import gg.vape.account.MinecraftSessionWrapper;
import gg.vape.account.MutableAccountCredentials;
import gg.vape.account.PermissiveX509TrustManager;
import gg.vape.account.XboxLiveAuthResult;
import gg.vape.utils.network.HttpRequest;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class MicrosoftSessionAuthenticator {
    private final String t;
    private MutableAccountCredentials I;
    private final String s;
    private final String G;
    private final String E;
    private static String[] l;

    public MinecraftSessionWrapper j() throws IOException {
        String[] stringArray = this.g();
        if (stringArray == null || stringArray.length < 2) {
            return null;
        }
        XboxLiveAuthResult xboxLiveAuthResult = this.C(stringArray[0]);
        if (xboxLiveAuthResult == null) {
            return null;
        }
        String string = this.w(xboxLiveAuthResult.x);
        if (string == null) {
            return null;
        }
        String string2 = this.l(xboxLiveAuthResult.A, string);
        if (string2 == null) {
            return null;
        }
        if (this.I.F() == null || this.I.T() == null) {
            String[] stringArray2 = this.Z(string2);
            if (stringArray2 == null) {
                return null;
            }
            this.I.X(stringArray2[0]);
            this.I.T(stringArray2[1]);
        }
        return MinecraftSessionWrapper.U(this.I.T(), this.I.F(), string2, "mojang");
    }

    public static void l(String[] stringArray) {
        l = stringArray;
    }

    private XboxLiveAuthResult C(String string) throws IOException {
        String string2 = "https://user.auth.xboxlive.com/user/authenticate";
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("AuthMethod", "RPS");
        jsonObject2.addProperty("SiteName", "user.auth.xboxlive.com");
        jsonObject2.addProperty("RpsTicket", string);
        jsonObject.add("Properties", (JsonElement)jsonObject2);
        jsonObject.addProperty("RelyingParty", "http://auth.xboxlive.com");
        jsonObject.addProperty("TokenType", "JWT");
        HttpRequest httpRequest = new HttpRequest(string2, "POST").L("application/json").e("Content-Type", "application/json").x(jsonObject.toString());
        JsonObject jsonObject3 = httpRequest.P();
        if (httpRequest.e().getResponseCode() != 200) {
            return null;
        }
        return new XboxLiveAuthResult(jsonObject3.getAsJsonObject("DisplayClaims").getAsJsonArray("xui").get(0).getAsJsonObject().get("uhs").getAsString(), jsonObject3.get("Token").getAsString());
    }

    private String l(String string, String string2) throws IOException {
        String string3 = "https://api.minecraftservices.com/authentication/login_with_xbox";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("identityToken", "XBL3.0 x=" + string + ";" + string2);
        HttpRequest httpRequest = new HttpRequest(string3, "POST").L("application/json").e("Content-Type", "application/json").x(jsonObject.toString());
        JsonObject jsonObject2 = httpRequest.P();
        if (httpRequest.e().getResponseCode() != 200) {
            return null;
        }
        return jsonObject2.get("access_token").getAsString();
    }

    public static String[] V() {
        return l;
    }

    private static IOException a(IOException iOException) {
        return iOException;
    }

    private String w(String string) throws IOException {
        String string2 = "https://xsts.auth.xboxlive.com/xsts/authorize";
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("SandboxId", "RETAIL");
        JsonArray jsonArray = new JsonArray();
        jsonArray.add((JsonElement)new JsonPrimitive(string));
        jsonObject2.add("UserTokens", (JsonElement)jsonArray);
        jsonObject.add("Properties", (JsonElement)jsonObject2);
        jsonObject.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
        jsonObject.addProperty("TokenType", "JWT");
        HttpRequest httpRequest = new HttpRequest(string2, "POST").L("application/json").e("Content-Type", "application/json").x(jsonObject.toString());
        JsonObject jsonObject3 = httpRequest.P();
        if (httpRequest.e().getResponseCode() != 200) {
            return null;
        }
        return jsonObject3.get("Token").getAsString();
    }

    static {
        MicrosoftSessionAuthenticator.l(new String[4]);
    }

    public MicrosoftSessionAuthenticator(MutableAccountCredentials mutableAccountCredentials) {
        this.s = "https://login.live.com/oauth20_authorize.srf?client_id=000000004C12AE6F&redirect_uri=https://login.live.com/oauth20_desktop.srf&scope=service::user.auth.xboxlive.com::MBI_SSL&display=touch&response_type=token&locale=en";
        this.E = "https://user.auth.xboxlive.com/user/authenticate";
        this.G = "https://xsts.auth.xboxlive.com/xsts/authorize";
        this.t = "https://api.minecraftservices.com/authentication/login_with_xbox";
        this.I = mutableAccountCredentials;
        CookieHandler.setDefault(new CookieManager());
        TrustManager[] trustManagerArray = new TrustManager[]{new PermissiveX509TrustManager(this)};
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArray, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
        }
        catch (Exception exception) {
            Vape.logThrowable(exception);
        }
    }

    private String[] g() throws IOException {
        HttpRequest httpRequest = new HttpRequest("https://login.live.com/oauth20_authorize.srf?client_id=000000004C12AE6F&redirect_uri=https://login.live.com/oauth20_desktop.srf&scope=service::user.auth.xboxlive.com::MBI_SSL&display=touch&response_type=token&locale=en", "GET");
        Pattern pattern = Pattern.compile("value=\"(.+?)\"");
        String string = httpRequest.h();
        Matcher matcher = pattern.matcher(string);
        String string2 = "";
        if (!matcher.find()) {
            return null;
        }
        string2 = matcher.group(1);
        Pattern pattern2 = Pattern.compile("urlPost:'(.+?)'");
        Matcher matcher2 = pattern2.matcher(string);
        String string3 = "";
        if (!matcher2.find()) {
            return null;
        }
        string3 = matcher2.group(1);
        String string4 = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(this.I.y(), "UTF-8") + "&" + URLEncoder.encode("loginfmt", "UTF-8") + "=" + URLEncoder.encode(this.I.y(), "UTF-8") + "&" + URLEncoder.encode("passwd", "UTF-8") + "=" + URLEncoder.encode(this.I.U(), "UTF-8") + "&" + URLEncoder.encode("PPFT", "UTF-8") + "=" + URLEncoder.encode(string2, "UTF-8");
        byte[] byArray = string4.toString().getBytes(StandardCharsets.UTF_8);
        HttpRequest httpRequest2 = new HttpRequest(string3, "POST").e("Content-Type", "application/x-www-form-urlencoded").e("Content-Length", Integer.toString(byArray.length)).x(string4);
        String string5 = httpRequest2.h();
        HttpURLConnection httpURLConnection = httpRequest2.e();
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        if (string5.contains("Sign in to")) {
            throw new IllegalStateException("Invalid Email or Password");
        }
        if (string5.contains("Help us protect your account")) {
            throw new IllegalStateException("2-Factor Enabled unable to log in");
        }
        String string6 = httpURLConnection.getURL().toString();
        Vape.debugLog("redirected URl: " + string6);
        String string7 = string6.split("#")[1];
        String[] stringArray = string7.split("&");
        String string8 = null;
        String string9 = null;
        for (String string10 : stringArray) {
            if (string10.contains("access_token")) {
                string8 = string10.split("=")[1];
                continue;
            }
            if (!string10.contains("refresh_token")) continue;
            string9 = string10.split("=")[1];
        }
        return new String[]{string8, string9};
    }

    private String[] Z(String string) throws IOException {
        String string2 = "https://api.minecraftservices.com/minecraft/profile";
        HttpRequest httpRequest = new HttpRequest(string2, "GET").e("Authorization", "Bearer " + string);
        if (httpRequest.e().getResponseCode() != 200) {
            return null;
        }
        JsonObject jsonObject = httpRequest.P();
        String string3 = jsonObject.get("name").getAsString();
        String string4 = jsonObject.get("id").getAsString();
        return new String[]{string3, string4};
    }
}

