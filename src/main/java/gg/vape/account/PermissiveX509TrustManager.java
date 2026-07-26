package gg.vape.account;

import gg.vape.account.MicrosoftSessionAuthenticator;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

class PermissiveX509TrustManager
implements X509TrustManager {
    final MicrosoftSessionAuthenticator e;

    PermissiveX509TrustManager(MicrosoftSessionAuthenticator wj_02) {
        this.e = wj_02;
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509CertificateArray, String string) {
    }

    @Override
    public void checkClientTrusted(X509Certificate[] x509CertificateArray, String string) {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}

