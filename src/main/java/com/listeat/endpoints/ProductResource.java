package com.listeat.endpoints;

import com.listeat.models.GItem;
import com.listeat.models.GList;
import com.listeat.models.Product;
import com.listeat.models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;
import java.util.List;

@Path("product")
public class ProductResource extends BaseResource{
    @GET @Path("/autocomplete/{text}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Get the list of all GLists that belong to a specific user
    public List<Product> getProductsByAutoComplete(@PathParam("text") String text) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();

            TypedQuery<Product> query = session.createQuery("SELECT p FROM Product p WHERE p.name LIKE :productNameText", Product.class);
            query.setParameter("productNameText", MessageFormat.format("%{0}%", text));
            List<Product> products = query.getResultList();

            //Set product_obj of every gItem (for the response object)
            for (Product product : products){
                product.setCategory_obj(product.getCategory());
            }

            return products;
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }

    @POST @Path("/{glistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Create a new GList
    public Product create(@PathParam("glistId") int glistId, Product product) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

//            //Find the user in DB and add it to the list of users of this gList
//            User user = session.find(User.class, userId);
//            glist.getUsers().add(user);
//            //Add this gList to the DB
//            session.persist(glist);
//
//            session.getTransaction().commit();
//
            session.persist(product);

            return product;
        }catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}

