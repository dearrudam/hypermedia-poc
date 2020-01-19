package com.github.dearrudam.boundary;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;
import java.util.List;

public class ApiResponse{

    private List<ApiModel> entities = new LinkedList<>();

    public ApiResponse(UriInfo uriInfo) {
        this.entities.add(createApiMenuList(uriInfo));
    }

    public static ApiMenu createApiMenuList(UriInfo uriInfo) {
        return new ApiMenu("home",uriInfo.getBaseUriBuilder().path(RootResources.class).build())
                .addAll(new ApiMenu("planotrabalhos",uriInfo.getBaseUriBuilder().path(PlanoDeTrabalhoResource.class).build()));
    }

    public List<ApiModel> getEntities() {
        return entities;
    }

    public ApiResponse addEntity(ApiModel entity) {
        this.entities.add(entity);
        return this;
    }
}
