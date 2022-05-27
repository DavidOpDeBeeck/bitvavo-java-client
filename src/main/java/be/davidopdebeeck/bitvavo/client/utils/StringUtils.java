package be.davidopdebeeck.bitvavo.client.utils;

public class StringUtils {

    public static String ensureNonEndingSlash(String baseUrl) {
        return baseUrl.endsWith("/")
            ? baseUrl.substring(0, baseUrl.length() - 1)
            : baseUrl;
    }

    public static String ensureLeadingSlash(String apiPath) {
        return apiPath.startsWith("/")
            ? apiPath
            : "/" + apiPath;
    }
}
