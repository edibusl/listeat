package com.listeat.endpoints;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Path("stats")
public class StatisticsResource extends BaseResource{
    @GET @Path("/bycategory/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StatsResult> getStatsByCategory(@PathParam("userId") Long userId) throws Exception{
        EntityManager session = null;
        try {
            session = this.createSession();

            //Get date - 1 month to the past (calendaric month, not 31 days)
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            Date monthAgo = cal.getTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String sDate = df.format(monthAgo);

            //No threat of sql injection since the the only input from user (userId) is a long value.
            String sql = String.format("SELECT " +
                    "    c.name, COUNT(*) " +
                    "FROM " +
                    "    gitems AS g, " +
                    "    products AS p, " +
                    "    categories AS c " +
                    "WHERE " +
                    "    g.product_id = p.product_id " +
                    "        AND p.category_id = c.category_id " +
                    "        AND g.cart_id IN (SELECT  " +
                    "            c.cart_id " +
                    "        FROM " +
                    "            carts AS c, " +
                    "            glists AS g, " +
                    "            glists_users AS gu " +
                    "        WHERE " +
                    "            c.glist_id = g.glist_id " +
                    "                AND gu.glist_id = g.glist_id " +
                    "                AND gu.user_id = %s " +
                    "                AND purchase_time >= '%s') " +
                    "GROUP BY c.name", userId, sDate);

            Query q = session.createNativeQuery(sql);
            List<Object[]> results = q.getResultList();

            List<StatsResult> response = new ArrayList<>();
            for(Object[] result : results) {
                StatsResult sr = new StatsResult();
                sr.categoryName = (String)result[0];
                sr.count = (Long)result[1];
                response.add(sr);
            }



            return response;
        } catch (Exception ex){
            throw ex;
        }
        finally {
            session.close();
        }
    }
}

@XmlRootElement()
class StatsResult{
    public String categoryName;
    public Long count;
}