package ru.lab2.personservice.resources;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.lab2.library.Person;
import ru.lab2.personservice.dto.ErrorMessageDto;
import ru.lab2.personservice.repostirory.PersonRepository;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class PersonResource {
    @Inject
    private PersonRepository personRepository;

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") int id) {
        Person person = personRepository.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(Response.Status.NOT_FOUND.getStatusCode(), "Not found item")).build();
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
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageDto(Response.Status.BAD_REQUEST.getStatusCode(), "Validation Failed")).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") int id, Person person) {
        try{
            Person personToUpdate = personRepository.findById(id);
            if(personToUpdate == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorMessageDto(Response.Status.NOT_FOUND.getStatusCode(), "Not found item")).build();
            }
            person.setId(id);
            personRepository.update(person);
            return Response.ok(person).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageDto(Response.Status.BAD_REQUEST.getStatusCode(), "Validation Failed")).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoute(@PathParam("id") int id) {
        try {
            personRepository.delete(id);
            return Response.ok("Removal was successful").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(Response.Status.NOT_FOUND.getStatusCode(), "Not found item")).build();
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
            return Response.ok(persons).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageDto(Response.Status.BAD_REQUEST.getStatusCode(), "Validation Failed")).build();
        }
    }

    @GET
    @Path("/count/less/{eye-color}")
    public Response countLessEyeColor(@PathParam("eye-color") String eyeColor) {
        try {
            Long count = personRepository.countByEyeColorLessThan(eyeColor);
            return Response.ok("amount: " + count).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageDto(Response.Status.BAD_REQUEST.getStatusCode(), "Validation Failed")).build();
        }
    }

    @GET
    @Path("/filter/less/{eye-color}")
    public Response filterLessEyeColor(@PathParam("eye-color") String eyeColor) {
        try {
            List<Person> persons = personRepository.filterByEyeColorLessThan(eyeColor);
            if(persons.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorMessageDto(Response.Status.NOT_FOUND.getStatusCode(), "Not found item")).build();
            }
            return Response.ok(persons).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageDto(Response.Status.BAD_REQUEST.getStatusCode(), "Validation Failed")).build();
        }
    }

    @GET
    @Path("/filter/more/{height}")
    public Response filterMoreHeight(@PathParam("height") Double height) {
        try {
            if (height == null){
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorMessageDto(Response.Status.BAD_REQUEST.getStatusCode(), "Validation Failed")).build();
            }
            List<Person> persons = personRepository.filterByHeightMoreThan(height);
            if(persons.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorMessageDto(Response.Status.NOT_FOUND.getStatusCode(), "Not found item")).build();
            }
            return Response.ok(persons).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorMessageDto(Response.Status.BAD_REQUEST.getStatusCode(), "Validation Failed")).build();
        }
    }
}
