package com.listeat.endpoints;

import com.listeat.models.GList;
import com.listeat.models.User;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("glist")
public class GListResource extends BaseResource{
    @POST @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GList create(@PathParam("userId") int userId, GList glist) throws Exception{
        try {
            EntityManager session = this.createSession();

            Query q = session.createQuery("select u from User u");
            List<User> users = q.getResultList();
            String message = "Users are: ";
            for (User user : users) {
                message += user.getUsername() + ",";
            }

            session.getTransaction().begin();
            session.persist(glist);
            session.getTransaction().commit();
            session.close();
            return glist;

            //return type Response:  return Response.status(201).entity("Glist saved").build();
        }catch (Exception ex){
            int x = 1;
            throw ex;
        }
    }
}