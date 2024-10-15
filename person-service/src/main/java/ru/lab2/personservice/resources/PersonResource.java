package ru.lab2.personservice.resources;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.lab2.library.Person;
import ru.lab2.personservice.repostirory.PersonRepository;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    @Inject
    PersonRepository personRepository;

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") int id) {
        Person person = personRepository.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found item").build();
        } else {
            return Response.ok(person).build();
        }
    }

    @POST
    public Response createPerson(Person person) {
        try{
            personRepository.save(person);
            return Response.ok(person).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Validation Failed").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id, Person person) {
        try{
            Person personToUpdate = personRepository.findById(person.getId());
            if(personToUpdate == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Not found item").build();
            }
            person.setId(personToUpdate.getId());
            personRepository.update(person);
            return Response.ok(person).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Validation Failed").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoute(@PathParam("id") int id) {
        try {
            personRepository.delete(id);
            return Response.ok("Removal was successful").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Not found item").build();
        }
    }

    @GET
    @Path("/filter")
    public Response getAllPersonsByFilter(@QueryParam("page-number") int pageNumber,
                                          @QueryParam("page-size") int pageSize,
                                          @QueryParam("id") Integer id,
                                          @QueryParam("name") String name,
                                          @QueryParam("coordinate-x") Double coordinateX,
                                          @QueryParam("coordinate-y") Double coordinateY,
                                          @QueryParam("creation-date") String creationDate,
                                          @QueryParam("height")  Double height,
                                          @QueryParam("birthday")  String birthday,
                                          @QueryParam("passport-id")  String passportId,
                                          @QueryParam("eye-color")  String eyeColor,
                                          @QueryParam("location-x")  Integer locationX,
                                          @QueryParam("location-y")  Double locationY,
                                          @QueryParam("location-z")  Float locationZ
                                          ) {
        try {
            List<Person> persons = personRepository.findAllPersonsByFilter(pageNumber, pageSize, id, name, coordinateX,
                    coordinateY, creationDate, height, birthday, passportId, eyeColor, locationX, locationY, locationZ);
            if(persons.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("Not found item").build();
            }
            return Response.ok(persons).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Validation Failed").build();
        }
    }
}
