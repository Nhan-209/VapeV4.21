package gg.vape.api;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;

public final class ApiPermissiveX509ExtendedTrustManager
extends X509ExtendedTrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] x509CertificateArray, String string) {
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509CertificateArray, String string, Socket socket) throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509CertificateArray, String string) {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509CertificateArray, String string, SSLEngine sSLEngine) throws CertificateException {
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509CertificateArray, String string, SSLEngine sSLEngine) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509CertificateArray, String string, Socket socket) throws CertificateException {
    }
}

