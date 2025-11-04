package com.hamzaelzarw;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @GET
    @Transactional
    public List<CategoryDTO> list() {
        return Category.<Category>listAll().stream()
                .map(CategoryResource::toDto)
                .toList();
    }

    @GET
    @Path("/{id}/services")
    @Transactional
    public List<ServiceDTO> services(@PathParam("id") Long id) {
        Category category = Category.findById(id);
        if (category == null) {
            throw new NotFoundException("Category not found");
        }
        return ServiceItem.<ServiceItem>list("category", category).stream()
                .map(CategoryResource::toDto)
                .toList();
    }

    // DTOs and mappers
    public record CategoryDTO(Long id, String name, String description) {}
    public record ServiceDTO(Long id, String name, String description, java.math.BigDecimal price, Long categoryId) {}

    static CategoryDTO toDto(Category c) {
        return new CategoryDTO(c.id, c.name, c.description);
    }

    static ServiceDTO toDto(ServiceItem s) {
        return new ServiceDTO(s.id, s.name, s.description, s.price, s.category != null ? s.category.id : null);
    }
}
