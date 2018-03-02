package com.listeat.endpoints;

import com.listeat.models.GList;
import com.listeat.models.User;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.font.GlyphJustificationInfo;
import java.util.List;

@Path("glist")
public class GListResource extends BaseResource{
    @POST @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GList create(@PathParam("userId") int userId, GList glist) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            //Find the user in DB and add it to the list of users of this glist
            User user = session.find(User.class, userId);
            glist.getUsers().add(user);
            //Add this glist to the DB
            session.persist(glist);

            session.getTransaction().commit();

            return glist;
        }catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}


//return type Response:  return Response.status(201).entity("Glist saved").build();