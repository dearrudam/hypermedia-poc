package com.github.dearrudam.boundary;

import com.github.dearrudam.entity.PlanoDeTrabalho;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/planotrabalhos")
public class PlanoDeTrabalhoResource {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse get(
            @Context UriInfo uriInfo,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("5") int pageSize
    ) {
        return new ApiResponse(uriInfo)
                .addEntity(collectionOf(PlanoDeTrabalho.findAll().page(Page.of(page, pageSize)), uriInfo));
    }

    private <T extends PanacheEntityBase> ApiCollection<T> collectionOf(PanacheQuery<T> page, UriInfo uriInfo) {
        return new ApiCollection<>(page, uriInfo, (currentPage) -> {
            return new ApiLink(null,
                    uriInfo.getBaseUriBuilder()
                            .path(getClass())
                            .queryParam("page", currentPage.index())
                            .queryParam("pageSize", currentPage.size()).build());
        });
    }


}
