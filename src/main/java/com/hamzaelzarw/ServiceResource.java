package com.hamzaelzarw;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/services")
@Produces(MediaType.APPLICATION_JSON)
public class ServiceResource {

    @GET
    @Transactional
    public List<CategoryResource.ServiceDTO> list(@QueryParam("categoryId") Long categoryId) {
        List<ServiceItem> items = (categoryId == null)
                ? ServiceItem.listAll()
                : ServiceItem.list("category.id", categoryId);
        return items.stream()
                .map(CategoryResource::toDto)
                .toList();
    }

    @GET
    @Path("/{id}")
    @Transactional
    public CategoryResource.ServiceDTO get(@PathParam("id") Long id) {
        ServiceItem s = ServiceItem.findById(id);
        if (s == null) throw new NotFoundException("Service not found");
        return CategoryResource.toDto(s);
    }
}
