package com.listeat.endpoints;

import com.listeat.models.GItem;
import com.listeat.models.GList;
import com.listeat.models.Product;
import com.listeat.models.User;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;

@Path("gitem")
public class GItemResource extends BaseResource{
    @POST @Path("/{glistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Create a new GList
    public GItem create(@PathParam("glistId") int glistId, GItem gItem) throws Exception{
        return upsert(gItem);
    }

    @PUT @Path("/{glistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Create a new GList
    public GItem update(@PathParam("glistId") int glistId, GItem gItem) throws Exception{
        return upsert(gItem);
    }

    private GItem upsert(GItem gItem) {
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            //Check if it's update or creation
            boolean isNew = true;
            Long gItemId = gItem.getGitem_id();
            if(gItemId != null && gItemId != 0) {
                isNew = false;
            }

            //Set FK fields
            User user = session.find(User.class, gItem.getUserId());
            gItem.setUser(user);
            Product product = session.find(Product.class, gItem.getProductId());
            gItem.setProduct(product);
            GList gList = session.find(GList.class, gItem.getGlistId());
            gItem.setGlist(gList);

            //Set creation time
            java.util.Date curDate = new java.util.Date();
            gItem.setCreated_time(new Date(curDate.getTime()));

            if(isNew) {
                //Save new object
                session.persist(gItem);
            } else {
                //Update existing object
                session.merge(gItem);
            }

            session.getTransaction().commit();

            return gItem;
        }catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}

