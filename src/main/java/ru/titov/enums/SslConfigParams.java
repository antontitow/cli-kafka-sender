package ru.titov.enums;

public enum SslConfigParams {
    SSL("SSL"),
    SSL_TRUSTSTORE_LOCATION("ssl.truststore.location"),
    SSL_TRUSTSTORE_TYPE("ssl.truststore.type"),
    SSL_TRUSTSTORE_PASSWORD_CONFIG("ssl.truststore.password"),
    SSL_KEYSTORE_LOCATION_CONFIG("ssl.keystore.location"),
    SSL_KEYSTORE_TYPE_CONFIG("ssl.keystore.type"),
    SSL_KEYSTORE_PASSWORD_CONFIG("ssl.keystore.password"),
    SSL_KEY_PASSWORD_CONFIG("ssl.key.password");

    String value;

    SslConfigParams(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
