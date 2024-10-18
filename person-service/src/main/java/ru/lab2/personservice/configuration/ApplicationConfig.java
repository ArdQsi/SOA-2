package ru.lab2.personservice.configuration;

import ru.lab2.personservice.resources.HelloResource;
import ru.lab2.personservice.resources.PersonResource;

import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(HelloResource.class);
        resources.add(PersonResource.class);
    }

}