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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import system.dbutil.DBConnection;
import system.pojo.ReceptionistPojo;
import system.pojo.UserPojo;

/**
 *
 * @author HP
 */
public class ReceptionistDao {
    
    
    public static Map<String,String> getnonregisteredReceptionist()throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select empid,empname from employees where job='Receptionist' and empid not in (select empid from users where usertype='Receptionist')");
        HashMap<String,String>receptionist=new HashMap<>();
        while(rs.next())
        {
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionist.put(id, name);
        }
        return receptionist;
        
    }
    
    public static String getNextEmpid()throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement ps=con.createStatement();
        ResultSet rs=ps.executeQuery("select max(empid) from users");
        rs.next();
        String empid=rs.getString(1);
        int empno=Integer.parseInt(empid.substring(1));
        empno++;
        return "E"+empno;
        
    }
    public static ArrayList<String> getAllEmpid()throws SQLException
    {
        ArrayList<String> empids=new ArrayList();
     Connection con=DBConnection.getConnection();
     Statement ps=con.createStatement();
     ResultSet rs=ps.executeQuery("select empid from users where usertype='Receptionist' order by empid");
    
     while(rs.next())
     {
           UserPojo e=new UserPojo();
            e.setEmpid(rs.getString(1));
         empids.add(e.getEmpid());
        
     }
     return empids;
     
     }
     public static boolean AddReceptionist(UserPojo emp)throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("insert into users values(?,?,?,?,?)");
         ps.setString(1,emp.getUserid());
        ps.setString(2, emp.getEmpid());
        ps.setString(3,emp.getPassword());
        ps.setString(4, emp.getUsertype());
        ps.setString(5, emp.getUsername());
          
         int result=ps.executeUpdate();
        return result==1;
        
        
    }
      
      public static UserPojo getuserdetail(String userid)throws SQLException
    {
        
     Connection con=DBConnection.getConnection();
     PreparedStatement ps=con.prepareStatement("select * from users where userid=?");
        ps.setString(1, userid);   
        ResultSet rs=ps.executeQuery();
      UserPojo ep=null;
      if(rs.next())
     {
         ep=new UserPojo();
          ep.setUserid(rs.getString(1));
         ep.setEmpid(rs.getString(2));
         ep.setPassword(rs.getString(3));
         ep.setUsertype(rs.getString(4));
         ep.setUsername(rs.getString(5));
         
         ;
         
     }
     return ep;
     
     }
      public static boolean removeuser(String userid)throws SQLException
    {
        
     Connection con=DBConnection.getConnection();
     PreparedStatement ps=con.prepareStatement("delete users where userid=?");
        ps.setString(1, userid);  
        
        return ps.executeUpdate()==1;
    
     
     }
      public static boolean updateReceptionist(UserPojo e)throws SQLException
     {
         Connection con=DBConnection.getConnection();
          PreparedStatement ps=con.prepareStatement("update users set password=? where  usertype='Receptionist' ");
        
       ps.setString(1,e.getPassword());
      
     
       
       int result=ps.executeUpdate();
        
       
       if(result==0)
           return false;
       return true;
       
       
          
      }
       public static List<ReceptionistPojo> getalldetails()throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement ps=con.createStatement();
        ResultSet rs=ps.executeQuery("select users.empid,empname,userid,job,salary from users, employees where usertype='Receptionist' and users.empid=employees.empid order by empid");
        ArrayList<ReceptionistPojo> e=new ArrayList<ReceptionistPojo>();
        while(rs.next())
        {
            ReceptionistPojo user=new ReceptionistPojo();
            user.setEmpid(rs.getString(1));
            user.setEmpname(rs.getString(2));
            user.setUserid(rs.getString(3));
            user.setJob(rs.getString(4));
            user.setSalary(rs.getDouble(5));
           
           e.add(user);
        }
        return e;
    
   }
       public static List<String> getalluserid()throws SQLException
       {
           Connection con=DBConnection.getConnection();
           Statement st=con.createStatement();
             List<String>Receptionist=new ArrayList();
             ResultSet rs=st.executeQuery("select userid from users where usertype='Receptionist' order by userid");
             while(rs.next())
             {
                 String id=rs.getString(1);
                 Receptionist.add(id);
             } 
             return Receptionist;
       }
     
    
}
