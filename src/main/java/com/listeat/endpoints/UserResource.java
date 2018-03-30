package com.listeat.endpoints;

import com.listeat.models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
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

    @POST @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(User user) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            User managedUser = null;

            //Try finding user in DB by username
            if(user.getUsername() != null) {
                TypedQuery<User> query = session.createQuery("SELECT u FROM User u WHERE u.username=:userNameText", User.class);
                query.setParameter("userNameText", user.getUsername());
                List<User> result = query.getResultList();
                if(result.size() == 1) {
                    managedUser = result.get(0);
                }
            }
            //Try finding user in DB by user id
            else if(user.getUser_id() != null) {
                managedUser = session.find(User.class, user.getUser_id());
            }


            if(managedUser == null) {
                //User doesn't exist yet, create it
                managedUser = user;
            } else {
                //User exists in our DB, just update some fields
                if(user.getName() != null) {
                    managedUser.setName(user.getName());
                }

                if(user.getProfile_image() != null) {
                    managedUser.setProfile_image(user.getProfile_image());
                }
            }

            //Log last login time
            java.util.Date curDate = new java.util.Date();
            managedUser.setLast_login(new Date(curDate.getTime()));
            session.persist(managedUser);

            session.getTransaction().commit();

            return managedUser;
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}

