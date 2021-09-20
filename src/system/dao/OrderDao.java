/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import system.dbutil.DBConnection;
import system.pojo.ProductsPojo;
import system.pojo.UserProfile;

/**
 *
 * @author HP
 */
public class OrderDao
{
public static String getNextOrderid()throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement ps=con.createStatement();
        ResultSet rs=ps.executeQuery("select max(order_id) from orders");
        rs.next();
        String ordid=rs.getString(1);
        if(ordid==null)
        {
            return "O-101";
        }
        int ordno=Integer.parseInt(ordid.substring(2));
        ordno++;
        return "O-"+ordno;
        
    }    

public static boolean addorder(ArrayList<ProductsPojo>al,String ordid)throws SQLException
{
  Connection con=DBConnection.getConnection();
     PreparedStatement ps=con.prepareStatement("insert into orders values(?,?,?,?)");
    int count=0;
     for(ProductsPojo p:al)
     {
         ps.setString(1, ordid);
         ps.setString(2,p.getProductid());
         ps.setInt(3,p.getQuantity());
         ps.setString(4,UserProfile.getUserid());
        count+=ps.executeUpdate();
     }
       return count==al.size();
    
}

        


    
    
}
