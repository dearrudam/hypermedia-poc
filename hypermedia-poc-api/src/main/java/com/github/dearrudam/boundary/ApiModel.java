package com.github.dearrudam.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ApiModel {

    public final String entityType;

    public ApiModel(String entityType) {
        this.entityType = entityType;
    }
}
