package com.github.dearrudam.boundary;

import javax.json.JsonObject;
import javax.ws.rs.HttpMethod;
import java.net.URI;
import java.util.Objects;

public class ApiLink extends ApiModel{

    private String label;
    private URI uri;
    private String method;
    private JsonObject schema;

    public ApiLink(String label, URI uri) {
        this(label,HttpMethod.GET,uri);
    }

    public ApiLink(String label,String method,URI uri) {
        this(label,method,uri,null);
    }

    public ApiLink(String label,String method,URI uri,JsonObject schema) {
        super("link");
        this.label = label;
        this.method = method;
        this.uri = uri;
        this.schema = schema;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public JsonObject getSchema() {
        return schema;
    }

    public void setSchema(JsonObject schema) {
        this.schema = schema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiLink apiLink = (ApiLink) o;
        return Objects.equals(label, apiLink.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
