package com.listeat.endpoints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseResource {
    private EntityManagerFactory factory;

    public BaseResource(){
        this.factory = Persistence.createEntityManagerFactory("listeat");
    }

    protected EntityManager createSession() {
        //Clear cache before starting a fresh new entity manager session
        factory.getCache().evictAll();

        return this.factory.createEntityManager();
    }
}
