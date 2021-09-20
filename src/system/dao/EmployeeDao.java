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
import java.util.List;
import system.dbutil.DBConnection;
import system.pojo.EmployeePojo;

/**
 *
 * @author HP
 */
public class EmployeeDao {
    
    public static String getNextEmpid()throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement ps=con.createStatement();
        ResultSet rs=ps.executeQuery("select max(empid) from employees");
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
     ResultSet rs=ps.executeQuery("select empid from employees order by empid");
    
     while(rs.next())
     {
           EmployeePojo e=new EmployeePojo();
            e.setEmpid(rs.getString(1));
         empids.add(e.getEmpid());
        
     }
     return empids;
     
     }
    public static EmployeePojo getEmpdetail(String empid)throws SQLException
    {
        
     Connection con=DBConnection.getConnection();
     PreparedStatement ps=con.prepareStatement("select * from employees where empid=?");
        ps.setString(1, empid);       
        ResultSet rs=ps.executeQuery();
      EmployeePojo ep=null;
      if(rs.next())
     {
         ep=new EmployeePojo();
         ep.setEmpid(rs.getString(1));
         ep.setEmpname(rs.getString(2));
         ep.setEmpjob(rs.getString(3));
         ep.setEmpsal(rs.getString(4));
         
     }
     return ep;
     
     }
    
    
    public static boolean AddEmployees(EmployeePojo emp)throws SQLException
    {
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("insert into Employees values(?,?,?,?)");
        ps.setString(1, emp.getEmpid());
        ps.setString(2, emp.getEmpname());
        ps.setString(3,emp.getEmpjob());
        ps.setString(4,emp.getEmpsal());
        int result=ps.executeUpdate();
        return result==1;
        
        
    }
    public static List<EmployeePojo> getallemployee()throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement ps=con.createStatement();
        ResultSet rs=ps.executeQuery("select * from Employees order by empid");
        ArrayList<EmployeePojo> e=new ArrayList<EmployeePojo>();
        while(rs.next())
        {
            EmployeePojo emp=new EmployeePojo();
            emp.setEmpid(rs.getString(1));
            emp.setEmpname(rs.getString(2));
            emp.setEmpjob(rs.getString(3));
            emp.setEmpsal(rs.getString(4));
            e.add(emp);
        }
        return e;
    
   }
    public static boolean updateEmployee(EmployeePojo e)throws SQLException
     {
         Connection con=DBConnection.getConnection();
          PreparedStatement ps=con.prepareStatement("update  employees set empid=?,empname=?,job=?,salary=? where empid=? ");
         ps.setString(1, e.getEmpid());
          ps.setString(2,e.getEmpname());
       ps.setString(3,e.getEmpjob());
       ps.setString(4, e.getEmpsal());
       ps.setString(5, e.getEmpid());
       
       int result=ps.executeUpdate();
       if(result==0)
           return false;
       else    
       {
           boolean x=UserDao.isuserpresent(e.getEmpid());
           if(x==false)
               return true;
       
       ps=con.prepareStatement("update  users set username=?,usertype=? where empid=? ");
       
          ps.setString(1,e.getEmpname());
       ps.setString(2,e.getEmpjob());
       ps.setString(3, e.getEmpid());
       
       int y=ps.executeUpdate();
       return y==1;
       
          
      }
     }
    public static boolean deleteEmployee(String empid)throws SQLException
    {
        Connection con=DBConnection.getConnection();
          PreparedStatement ps=con.prepareStatement("delete from employees where empid=?");
         ps.setString(1, empid);
        int result=ps.executeUpdate();
       if(result==0)
           return false;
       else    
       {
           boolean x=UserDao.isuserpresent(empid);
           if(x==false)
               return true;
       
       ps=con.prepareStatement("delete from users where empid=?");
       ps.setString(1, empid);
       
           int y=ps.executeUpdate();
       return y==1;
       
          
      }
        
    }
    
    
}
