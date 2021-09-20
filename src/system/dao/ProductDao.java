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
import system.pojo.ProductsPojo;

/**
 *
 * @author HP
 */
public class ProductDao {
    
    public static String getNextProductid()throws SQLException
    {
        Connection con=DBConnection.getConnection();
        Statement ps=con.createStatement();
        ResultSet rs=ps.executeQuery("select max(p_id) from products");
        rs.next();
        String productid=rs.getString(1);
        if(productid==null)
        {
            return "P101";
        }
        int productno=Integer.parseInt(productid.substring(1));
        productno++;
        return "P"+productno;
        
    }
    public static boolean AddProduct(ProductsPojo p)throws SQLException
    {
        Connection con=DBConnection.getConnection();
                PreparedStatement ps=con.prepareStatement("insert into products values(?,?,?,?,?,?,?,'Y')");
          ps.setString(1,p.getProductid());
          ps.setString(2, p.getProductname());
          ps.setString(3, p.getProductcompany());
          ps.setDouble(4, p.getProductprice());
          ps.setDouble(5, p.getOurprice());
          ps.setInt(6,p.getTax());
          ps.setInt(7, p.getQuantity());
          return ps.executeUpdate()==1;
                 
    }
    public static List<ProductsPojo> getProductsDetails()throws SQLException
    {
     Connection con=DBConnection.getConnection();
        Statement ps=con.createStatement();
        ResultSet rs=ps.executeQuery("select * from products where status='Y' order by p_id");
        ArrayList<ProductsPojo> productlist=new ArrayList<>();
        while(rs.next())
        {
            ProductsPojo p=new ProductsPojo();
            p.setProductid(rs.getString(1));
            p.setProductname(rs.getString(2));
            p.setProductcompany(rs.getString(3));
            p.setProductprice(rs.getDouble(4));
            p.setOurprice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(rs.getInt(7));
            
            productlist.add(p);
            
        }
        
        return productlist;  
    }
    public static boolean deleteproducts(String Productid)throws SQLException
    {
        Connection con=DBConnection.getConnection();
           PreparedStatement ps=con.prepareStatement("update products set status='N' where p_id=?");
       
        ps.setString(1, Productid);
    return ps.executeUpdate()==1;
    
    }
    public static boolean updateproducts(ProductsPojo p)throws SQLException
    {
         Connection con=DBConnection.getConnection();
           PreparedStatement ps=con.prepareStatement("update products set p_name=?,p_companyname=?,p_price=?,our_price=?,p_tax=?,quantity=? where p_id=?");
      
            
          ps.setString(1, p.getProductname());
          ps.setString(2, p.getProductcompany());
          ps.setDouble(3, p.getProductprice());
          ps.setDouble(4, p.getOurprice());
          ps.setInt(5,p.getTax());
          ps.setInt(6, p.getQuantity());
           ps.setString(7,p.getProductid());
           return ps.executeUpdate()==1;
           
    }
    public static ProductsPojo getProductDetails(String id)throws SQLException
    {
        Connection con=DBConnection.getConnection();
           PreparedStatement ps=con.prepareStatement("select * from products where  p_id=? and status='Y'");
          ps.setString(1, id);
          ResultSet rs=ps.executeQuery();
          ProductsPojo p=new ProductsPojo();
          if(rs.next())
          {
               p.setProductid(rs.getString(1));
            p.setProductname(rs.getString(2));
            p.setProductcompany(rs.getString(3));
            p.setProductprice(rs.getDouble(4));
            p.setOurprice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(rs.getInt(7));
          }
           return p;
           
     }
    public static boolean updatestocks(List<ProductsPojo> ProductList)throws SQLException
    {
        Connection con=DBConnection.getConnection();
           PreparedStatement ps=con.prepareStatement("update products set  quantity=quantity-? where  p_id=? ");
        int x=0;
        
           for(ProductsPojo p:ProductList)
           {
               ps.setInt(1,p.getQuantity());
               ps.setString(2,p.getProductid());
               int row=ps.executeUpdate();
               if(row!=0)
                   x++;
              
           }
           return x==ProductList.size();
           
           
           }
    
    
    
    
    
    
    
    
}
