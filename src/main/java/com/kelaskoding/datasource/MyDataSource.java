/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding.datasource;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author jarvis
 */
public class MyDataSource {
    
    
    //Ambil connection dari WildFly
    public static Connection getConnection(){
        DataSource dataSource = null;
        Connection conn = null;
        if(dataSource == null){
            try{
                InitialContext ctx = new InitialContext();
                dataSource = (DataSource)ctx.lookup("java:/dbitems");
                conn = dataSource.getConnection();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return conn;
    }
}
