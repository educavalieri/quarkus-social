package com.educavalieri.rest;

import com.educavalieri.dtos.CreateUserDto;
import com.educavalieri.dtos.UserDto;
import com.educavalieri.entities.User;
import com.educavalieri.repositories.UserRepository;
import com.educavalieri.services.UserService;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {


    @Inject
    private UserService userService;


    @POST
    @Transactional
    public Response createUser(CreateUserDto userDto){
        CreateUserDto newDto = userService.save(userDto);
        return Response.ok(newDto).build();
    }

    @GET
    @Transactional
    public Response listAll(){
        List<UserDto> dtos = userService.findAll();
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    @Transactional
    public Response findById(@PathParam("id") Long id){
        UserDto userDto = userService.findById(id);
        return Response.ok(userDto).build();
    }

    @DELETE
    @Path("/delete{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id){
        userService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    public Response update(UserDto dto){
        userService.update(dto);
        return Response.ok().build();
    }

}
