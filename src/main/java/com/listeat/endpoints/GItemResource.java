package com.listeat.endpoints;

import com.listeat.helpers.StatusResponse;
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
    //Create a new GItem
    //(glistId is needed just for consistency across all REST endpoints)
    public GItem create(@PathParam("glistId") int glistId, GItem gItem) throws Exception{
        return upsert(gItem);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Create a new GItem
    public GItem update(GItem gItem) throws Exception{
        return upsert(gItem);
    }

    @DELETE @Path("/{gItemId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Delete a GItem object
    public StatusResponse deleteItem(@PathParam("gItemId") long gItemId) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            GItem managedGList = session.find(GItem.class, gItemId);
            session.remove(managedGList);

            session.getTransaction().commit();

            return new StatusResponse();
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
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
            User user = session.find(User.class, gItem.getUser_id());
            gItem.setUser(user);
            Product product = session.find(Product.class, gItem.getProduct_id());
            gItem.setProduct(product);
            GList gList = session.find(GList.class, gItem.getGlist_id());
            gItem.setGlist(gList);

            //Set creation time
            java.util.Date curDate = new java.util.Date();
            gItem.setCreated_date(new Date(curDate.getTime()));

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

