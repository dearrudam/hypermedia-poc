package com.github.dearrudam.boundary;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.ws.rs.core.UriInfo;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class ApiCollection<T extends PanacheEntityBase> extends ApiModel {
    private List<T> data;
    private long count;
    private int page;
    private int pageSize;
    private int pageCount;
    private List<ApiLink> links = new LinkedList<>();

    public static interface Pagination {
        int index();
        int size();

        public static Pagination of(final int index,final int size){
            return new Pagination(){
                @Override
                public int index() {
                    return index;
                }

                @Override
                public int size() {
                    return size;
                }
            };
        }
    }

    public ApiCollection(PanacheQuery<T> items, UriInfo uriInfo, Function<Pagination, ApiLink> navigationFunc) {
        super("collection");
        Page currentPage = items.page();
        this.count = items.count();
        this.data = items.list();
        this.page = currentPage.index + 1;
        this.pageSize = currentPage.size;
        this.pageCount = items.pageCount();

        this.links.add(navigationFunc.andThen(apiLink -> {
            apiLink.setLabel("self");
            return apiLink;
        }).apply(Pagination.of(currentPage.index,currentPage.size)));

        if (items.count() > 0) {
            if (items.hasPreviousPage()) {
                Page page = currentPage.previous();
                this.links.add(navigationFunc.andThen(apiLink -> {
                    apiLink.setLabel("previous");
                    return apiLink;
                }).apply(Pagination.of(page.index,page.size)));
            }

            if (items.hasNextPage()) {

                Page page = currentPage.next();
                this.links.add(navigationFunc.andThen(apiLink -> {
                    apiLink.setLabel("next");
                    return apiLink;
                }).apply(Pagination.of(page.index,page.size)));
            }
        }
    }

    public List<T> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getCount() {
        return count;
    }

    public List<ApiLink> getLinks() {
        return links;
    }
}
