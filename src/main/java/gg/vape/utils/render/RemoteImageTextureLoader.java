package gg.vape.utils.render;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class RemoteImageTextureLoader {
    private static String B;

    public static void l(String string) {
        B = string;
    }

    public static String w() {
        return B;
    }

    public static byte[] L(String string) {
        try {
            URL url = new URL(string);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            int n = httpURLConnection.getResponseCode();
            switch (n) {
                case 301:
                case 302:
                case 303:
                case 307:
                case 308: {
                    String string2 = httpURLConnection.getHeaderField("Location");
                    httpURLConnection.disconnect();
                    return RemoteImageTextureLoader.L(string2);
                }
            }
            try {
                try (InputStream inputStream = httpURLConnection.getInputStream()) {
                    try (InputStream inputStream2 = "gzip".equalsIgnoreCase(httpURLConnection.getContentEncoding()) ? new GZIPInputStream(inputStream) : inputStream) {
                        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                            byte[] byArray = new byte[8192];
                            int n2;
                            while ((n2 = inputStream2.read(byArray)) != -1) {
                                byteArrayOutputStream.write(byArray, 0, n2);
                            }
                            return byteArrayOutputStream.toByteArray();
                        }
                    }
                }
            }
            finally {
                httpURLConnection.disconnect();
            }
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    private static Throwable a(Throwable throwable) {
        return throwable;
    }

    static {
        RemoteImageTextureLoader.l("Gazrnb");
    }
}
