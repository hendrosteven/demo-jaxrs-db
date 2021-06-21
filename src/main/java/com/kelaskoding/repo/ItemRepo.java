/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding.repo;

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
public class ItemRepo {
    
    private Connection conn;
    
    public ItemRepo(Connection conn){
        this.conn = conn;
    }
    
    public int createItem(Item item){
        String sql = "insert into tbl_products(name, price) values(?,?)";
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            pst.setString(1, item.getName());
            pst.setDouble(2, item.getPrice());
            return pst.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
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
    
    public List<Item> findAll(){
        String sql = "select id, name, price from tbl_products";
        List<Item> items = new ArrayList<>();
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
            return items;
        }catch(Exception ex){
            ex.printStackTrace();
            return items;
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
    
    public Item findById(Long id){
        String sql = "select id, name, price from tbl_products where id=?";
        Item item = null;
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                item = new Item();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
            }
            return item;
        }catch(Exception ex){
            ex.printStackTrace();
            return item;
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
    
    public List<Item> findByCategoryId(Long id){
        String sql = "select id, name, price from tbl_products where category_id=?";
        List<Item> items = new ArrayList<>();
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Item item = new Item();
                item.setId(rs.getLong("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
            return items;
        }catch(Exception ex){
            ex.printStackTrace();
            return items;
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
    
    public int removeById(Long id){
        String sql = "delete from tbl_products where id=?";
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            pst.setLong(1, id);
            return pst.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
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
