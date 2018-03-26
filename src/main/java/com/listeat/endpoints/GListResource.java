package com.listeat.endpoints;

import com.listeat.models.GItem;
import com.listeat.models.GList;
import com.listeat.models.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Console;
import java.util.*;

@Path("glist")
public class GListResource extends BaseResource{
    @GET @Path("/all/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Get the list of all GLists that belong to a specific user
    public List<GList> getUserLists(@PathParam("userId") long userId) throws Exception{
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

    @GET @Path("/info/{glistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Get GList object by ID
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

    @GET @Path("/fullInfo/{glistId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Get full info about a GList (GList object + all items inside it)
    public GListFullInfo getFullInfo(@PathParam("glistId") long glistId) throws Exception{
        EntityManager session = null;
        try {
            GListFullInfo fullInfo = new GListFullInfo();

            //Get GList object
            session = this.createSession();
            fullInfo.gList = session.find(GList.class, glistId);

            //Get all GItems that belong to this GList and create a new cloned list prepared for sorting them
            //We need to clone the list, otherwise the sorting won't work
            List<GItem> gItems = new ArrayList<>(fullInfo.gList.getGitems());

            //Sort by category id
            Collections.sort(gItems, Comparator.<GItem> comparingLong(o -> o.getProduct().getCategory().getCategory_id()).thenComparingLong(o -> o.getProduct().getCategory().getCategory_id()));

            //Set the response
            fullInfo.gItems = gItems;

            //Set product_obj of every gItem (for the response object)
            for (GItem item : fullInfo.gItems){
                item.setProduct_obj(item.getProduct());
            }

            return fullInfo;

        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }

    @POST @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Create a new GList
    public GList create(@PathParam("userId") long userId, GList glist) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            //Find the user in DB and add it to the list of users of this gList
            User user = session.find(User.class, userId);
            glist.getUsers().add(user);
            //Add this gList to the DB
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Update a GList object
    public Response updateList(GList glist) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            //We can't use session.merge() to update a detached entity, since the gList in the request
            //doesn't include the users list, so when merging, the users list in DB will be dropped in this case.
            GList managedGList = session.find(GList.class, glist.getGlist_id());
            managedGList.setSubject(glist.getSubject());
            managedGList.setDescription(glist.getDescription());

            session.persist(managedGList);
            session.getTransaction().commit();

            return Response.status(200).entity("{}").build();
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }

    @DELETE @Path("/{glistId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //Delete a GList object
    public Response deleteList(@PathParam("glistId") long glistId, @PathParam("userId") Long userId) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();
            session.getTransaction().begin();

            //An actual delete is not done because it's a shared list - we just remove the user from the list
            //Find the glist in DB and remove this user from its list
            GList glist = session.find(GList.class, glistId);

            List<User> users = new ArrayList<>(glist.getUsers());
            users.removeIf(user -> user.getUser_id() == userId);
            glist.setUsers(users);

            session.persist(glist);

            session.getTransaction().commit();

            return Response.status(200).entity("{}").build();
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}


@XmlRootElement()
class GListFullInfo{
    public GList gList;
    public List<GItem> gItems;
}