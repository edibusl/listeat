package com.listeat.endpoints;

import com.listeat.models.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;
import java.util.List;

@Path("product")
public class ProductResource extends BaseResource{
    @GET @Path("/autocomplete/{gListId}/{text}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Product> getProductsByAutoComplete(@PathParam("gListId") long gListId, @PathParam("text") String text) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();

            //Select only products that are public (i.e. glist_id=NULL) or that they belong to our gList.
            TypedQuery<Product> query = session.createQuery("SELECT p FROM Product p WHERE p.name LIKE :productNameText AND (p.glist.glist_id = NULL OR p.glist.glist_id = :gListId)", Product.class);
            query.setParameter("productNameText", MessageFormat.format("%{0}%", text));
            query.setParameter("gListId", gListId);
            List<Product> products = query.getResultList();

            return products;
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Create a new Product
    public Product create(Product product) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            Category category = session.find(Category.class, product.getCategory_id());
            product.setCategory(category);

            GList glist = session.find(GList.class, product.getGlist_id());
            product.setGlist(glist);

            session.persist(product);
            session.getTransaction().commit();

            return product;
        }catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}

