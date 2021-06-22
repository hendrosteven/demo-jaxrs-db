/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding.resource;

import com.kelaskoding.datasource.MyDataSource;
import com.kelaskoding.model.Item;
import com.kelaskoding.repo.ItemRepo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author jarvis
 */
@Path("/items")
public class ItemResource {
    
    private ItemRepo repo;
    
    private static ExecutorService executor = Executors.newFixedThreadPool(10);
    
    public ItemResource(){
        try{
            this.repo = new ItemRepo(MyDataSource.getConnection());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOne(Item item){
        int affectedRow = repo.createItem(item);
        Map response = new HashMap();
        response.put("affectedRow", affectedRow);
        return Response.ok(response).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> findAll(){
        return repo.findAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/async")
    public void asynFindAll(@Suspended final AsyncResponse response){
        executor.execute(()->{
            List<Item> items = repo.findAll();
            response.resume(items);
        });
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id){
        return Response.ok(repo.findById(id)).build();
    }
}
