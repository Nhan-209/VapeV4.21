package gg.vape.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gg.vape.api.ApiHttpStatusException;
import gg.vape.api.ApiPermissiveX509ExtendedTrustManager;
import gg.vape.api.ApiResponse;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class ApiHttpClient {
    private static boolean h;
    private static final DateFormat O;
    public static final Gson Z;

    @Nullable
    @Contract(value="null -> null")
    public static Date U(@Nullable String string) throws ParseException {
        if (string == null) {
            return null;
        }
        return O.parse(string);
    }

    static {
        ApiHttpClient.l(true);
        Z = new GsonBuilder().serializeNulls().create();
        O = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    }

    private static <R> R w(String string, String string2, Class<R> clazz, @Nullable Object object) throws Exception {
        return (R)ApiHttpClient.y(string, arg_0 -> ApiHttpClient.lambda$request$0(object, string2, string, clazz, arg_0));
    }

    private static <T> T y(String string, Function<HttpURLConnection, T> function) throws Exception {
        try {
            TrustManager[] trustManagerArray = new TrustManager[]{new ApiPermissiveX509ExtendedTrustManager()};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArray, new SecureRandom());
            HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(string).openConnection();
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            if (httpURLConnection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsConnection = (HttpsURLConnection)httpURLConnection;
                httpsConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
                httpsConnection.setHostnameVerifier((hostname, session) -> true);
            }
            return function.apply(httpURLConnection);
        }
        catch (Throwable throwable) {
            throw new Exception(throwable);
        }
    }

    private static Object lambda$get$1(Class clazz, HttpURLConnection httpURLConnection) {
        try {
            return Z.fromJson((Reader)new InputStreamReader(httpURLConnection.getInputStream()), clazz);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static boolean S() {
        boolean bl = ApiHttpClient.e();
        return false;
    }

    public static <R> ApiResponse<R> V(String string, Function<JsonElement, R> function) throws Exception {
        return ApiResponse.m(ApiHttpClient.U(string, JsonObject.class), function);
    }

    public static void l(boolean bl) {
        h = bl;
    }

    public static <R> ApiResponse<R> z(String string, Object object, Function<JsonElement, R> function) throws Exception {
        return ApiResponse.m(ApiHttpClient.w(string, "POST", JsonObject.class, object), function);
    }

    public static <R> R l(String string, Class<R> clazz, Object object) throws Exception {
        return ApiHttpClient.w(string, "POST", clazz, object);
    }

    public static <R> ApiResponse<R> U(String string, Object object, Function<JsonElement, R> function) throws Exception {
        return ApiResponse.m(ApiHttpClient.w(string, "DELETE", JsonObject.class, object), function);
    }

    public static <R> R L(String string, Class<R> clazz) throws Exception {
        return ApiHttpClient.w(string, "DELETE", clazz, null);
    }

    public static boolean e() {
        return h;
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Object lambda$request$0(Object object, String string, String string2, Class clazz, HttpURLConnection httpURLConnection) {
        try {
            int n;
            block10: {
                String string3 = object != null ? Z.toJson(object) : null;
                byte[] byArray = string3 != null ? string3.getBytes(StandardCharsets.UTF_8) : null;
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestMethod(string);
                if (string3 != null) {
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    httpURLConnection.setRequestProperty("charset", "utf-8");
                    httpURLConnection.setRequestProperty("Content-Length", Integer.toString(byArray.length));
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    Throwable throwable = null;
                    try {
                        dataOutputStream.write(byArray);
                        if (dataOutputStream == null) break block10;
                    }
                    catch (Throwable throwable2) {
                        try {
                            throwable = throwable2;
                            throw throwable2;
                        }
                        catch (Throwable throwable3) {
                            if (dataOutputStream == null) throw throwable3;
                            if (throwable == null) {
                                dataOutputStream.close();
                                throw throwable3;
                            }
                            try {
                                dataOutputStream.close();
                                throw throwable3;
                            }
                            catch (Throwable throwable4) {
                                throwable.addSuppressed(throwable4);
                                throw throwable3;
                            }
                        }
                    }
                    dataOutputStream.close();
                }
            }
            if ((n = httpURLConnection.getResponseCode()) == 200) return Z.fromJson((Reader)new InputStreamReader(httpURLConnection.getInputStream()), clazz);
            throw new ApiHttpStatusException(string2, string, n);
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static <R> R U(String string, Class<R> clazz) throws Exception {
        return (R)ApiHttpClient.y(string, arg_0 -> ApiHttpClient.lambda$get$1(clazz, arg_0));
    }

    public static <R> R e(String string, Class<R> clazz, Object object) throws Exception {
        return ApiHttpClient.w(string, "DELETE", clazz, object);
    }
}
