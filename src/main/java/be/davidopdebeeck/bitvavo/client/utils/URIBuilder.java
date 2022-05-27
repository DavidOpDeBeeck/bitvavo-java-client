package be.davidopdebeeck.bitvavo.client.utils;

import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static be.davidopdebeeck.bitvavo.client.utils.StringUtils.ensureLeadingSlash;
import static be.davidopdebeeck.bitvavo.client.utils.StringUtils.ensureNonEndingSlash;
import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class URIBuilder {

    private final String baseUrl;
    private final String apiPath;
    private final Map<String, Object> pathVariables;
    private final Map<String, Object> queryParameters;

    public URIBuilder(String baseUrl, String apiPath) {
        this.baseUrl = ensureNonEndingSlash(requireNonNull(baseUrl));
        this.apiPath = ensureLeadingSlash(requireNonNull(apiPath));
        this.pathVariables = new HashMap<>();
        this.queryParameters = new HashMap<>();
    }

    public URIBuilder withPathVariable(String key, Object value) {
        this.pathVariables.put(key, value);
        return this;
    }

    public URIBuilder withQueryParameters(Map<String, ?> queryParameters) {
        this.queryParameters.putAll(queryParameters);
        return this;
    }

    public URI build() {
        String apiPathWithPathVariables = createApiPathWithPathVariables();
        String queryParametersAsString = createQueryParametersAsString();

        return queryParametersAsString.isEmpty()
            ? URI.create(baseUrl + apiPathWithPathVariables)
            : URI.create(baseUrl + apiPathWithPathVariables + "?" + queryParametersAsString);
    }

    private String createApiPathWithPathVariables() {
        return pathVariables.entrySet().stream()
            .reduce(apiPath, this::extrapolatePathVariables, String::concat);
    }

    private String extrapolatePathVariables(String path, Map.Entry<String, Object> entry) {
        return path.replace(format("{%s}", encode(entry.getKey())), encode(entry.getValue().toString()));
    }

    private String createQueryParametersAsString() {
        return queryParameters.entrySet().stream()
            .map(entry -> format("%s=%s", encode(entry.getKey()), encode(entry.getValue().toString())))
            .collect(joining("&"));
    }

    private String encode(String value) {
        return URLEncoder.encode(value, UTF_8);
    }
}
