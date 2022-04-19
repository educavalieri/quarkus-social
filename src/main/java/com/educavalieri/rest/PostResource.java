package com.educavalieri.rest;


import com.educavalieri.dtos.PostDto;
import com.educavalieri.entities.Post;
import com.educavalieri.services.PostService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/post")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    private PostService postService;

    @POST
    @Path("/{id}")
    public Response savePost(@PathParam("id") Long id, PostDto postDto){
        PostDto dto = postService.save(id, postDto);
        return Response.ok(dto).build();

    }

    @GET
    @Path("/list/{id}")
    public Response listAllPosts(@PathParam("id") Long id){
        List<PostDto> posts = postService.findAllPosts(id);
        return Response.ok(posts).build();
    }

}
