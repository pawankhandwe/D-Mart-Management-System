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
import system.dbutil.DBConnection;

import static system.dbutil.DBConnection.con;
import system.pojo.UserPojo;
import system.pojo.UserProfile;

/**
 *
 * @author HP
 */
public class UserDao {
    
    public static boolean validateuser(UserPojo user)throws SQLException
    {
       
        
        PreparedStatement ps=con.prepareStatement("select * from users where userid=?and password=?  and usertype=?");
        ps.setString(1, user.getUserid());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getUsertype());
        ResultSet rs=ps.executeQuery();
        
        if(rs.next())
        {
           String username=rs.getString(5);
           UserProfile.setUsername(username);
           return true;
        }
        return false;
        
       
    }

    public static boolean isuserpresent(String empid)throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select 1 from users where empid=?");
        ps.setString(1, empid);
        ResultSet rs=ps.executeQuery();
        return rs.next();
              
    }
    
}
