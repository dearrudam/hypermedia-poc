package com.github.dearrudam.boundary;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

public class ApiMenu extends ApiModel{

    private String label;
    private List<ApiMenu> submenu = new LinkedList<>();
    private URI uri;

    public ApiMenu(String label, URI uri) {
        super("menu");
        this.label = label;
        this.uri = uri;
    }

    public ApiMenu addAll(ApiMenu... submenus) {
        this.submenu.addAll(Arrays.stream(submenus)
                .filter(a -> !this.equals(a))
                .filter(Objects :: nonNull).collect(Collectors.toList()));
        return this;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ApiMenu> getSubmenu() {
        return submenu;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
