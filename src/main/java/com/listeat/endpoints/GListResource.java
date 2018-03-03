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

    @GET @Path("/{userId}/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<GList> getUserLists(@PathParam("userId") int userId) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            User user = session.find(User.class, userId);
            List<GList> glists = user.getGlists();

            return glists;
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }

    @GET @Path("/{glistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GList getList(@PathParam("glistId") long glistId) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            return session.find(GList.class, glistId);
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }

    @PUT @Path("/{glistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateList(@PathParam("glistId") long glistId, GList glist) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            //We can't use session.merge() to update a detached entity, since the glist in the request
            //doesn't include the users list, so when merging, the users list in DB will be dropped in this case.
            GList managedGList = session.find(GList.class, glistId);
            managedGList.setSubject(glist.getSubject());
            managedGList.setDescription(glist.getDescription());

            session.persist(managedGList);
            session.getTransaction().commit();

            return Response.status(200).entity("[]").build();
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }

    @DELETE @Path("/{glistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteList(@PathParam("glistId") long glistId) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            GList managedGList = session.find(GList.class, glistId);
            session.remove(managedGList);

            session.getTransaction().commit();

            return Response.status(200).entity("[]").build();
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}
