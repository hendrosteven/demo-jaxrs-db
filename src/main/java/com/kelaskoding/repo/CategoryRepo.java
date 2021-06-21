/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding.repo;

import com.kelaskoding.model.Category;
import com.kelaskoding.model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jarvis
 */
public class CategoryRepo {

    private Connection conn;
    
    private ItemRepo itemRepo;

    public CategoryRepo(Connection conn) {
        this.conn = conn;
        this.itemRepo = new ItemRepo(conn);
    }

    public List<Category> findAll() {
        String sql = "select id, name from tbl_categories";
        List<Category> categories = new ArrayList<>();
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Category category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                category.setItems(itemRepo.findByCategoryId(rs.getLong("id")));
                categories.add(category);
            }
            return categories;
        }catch(Exception ex){
            ex.printStackTrace();
            return categories;
        }finally{
            if(pst!=null){
                try{
                    pst.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
