/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding.resource;

import com.kelaskoding.datasource.MyDataSource;
import com.kelaskoding.model.Category;
import com.kelaskoding.repo.CategoryRepo;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jarvis
 */
@Path("/categories")
public class CategoryResource {

    private CategoryRepo repo;

    public CategoryResource() {
        try {
            this.repo = new CategoryRepo(MyDataSource.getConnection());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> findAll() {
        return repo.findAll();
    }
}
