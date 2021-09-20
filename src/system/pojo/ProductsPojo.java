/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.pojo;

/**
 *
 * @author HP
 */
public class ProductsPojo {

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ProductsPojo() {
        
    }

    public ProductsPojo(String productid, String productname, String productcompany, Double productprice, Double ourprice, int tax, int quantity,Double total) {
        this.productid = productid;
        this.productname = productname;
        this.productcompany = productcompany;
        this.productprice = productprice;
        this.ourprice = ourprice;
        this.tax = tax;
        this.quantity = quantity;
        this.total=total;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductcompany() {
        return productcompany;
    }

    public void setProductcompany(String productcompany) {
        this.productcompany = productcompany;
    }

    public Double getProductprice() {
        return productprice;
    }

    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }

    public Double getOurprice() {
        return ourprice;
    }

    public void setOurprice(Double ourprice) {
        this.ourprice = ourprice;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    private String productid,productname,productcompany;
    private Double productprice,ourprice,total;
    private int tax,quantity;
    
    
}
