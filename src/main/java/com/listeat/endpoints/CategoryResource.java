package com.listeat.endpoints;

import com.listeat.models.Category;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("category")
public class CategoryResource extends BaseResource{
    @GET @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategories() throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();

            TypedQuery<Category> query = session.createQuery("SELECT c FROM Category c", Category.class);
            List<Category> categories = query.getResultList();

            return categories;
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}

