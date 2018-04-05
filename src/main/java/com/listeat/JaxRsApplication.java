package com.listeat;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import com.listeat.endpoints.*;

public class JaxRsApplication extends Application {
    private final Set<Class<?>> classes;

    public JaxRsApplication() {
        HashSet<Class<?>> c = new HashSet<Class<?>>();
        c.add(GListResource.class);
        c.add(ProductResource.class);
        c.add(GItemResource.class);
        c.add(CategoryResource.class);
        c.add(UserResource.class);
        c.add(StatisticsResource.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}