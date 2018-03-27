package com.listeat.endpoints;

import com.listeat.models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.MessageFormat;
import java.util.List;

@Path("user")
public class UserResource extends BaseResource{
    @GET @Path("/autocomplete/{text}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<User> getUsersByAutoComplete(@PathParam("text") String text) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();

            TypedQuery<User> query = session.createQuery("SELECT u FROM User u WHERE u.username LIKE :userNameText", User.class);
            query.setParameter("userNameText", MessageFormat.format("%{0}%", text));
            List<User> users = query.getResultList();

            return users;
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}

