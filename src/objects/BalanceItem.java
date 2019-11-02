/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author DELL
 */
public class BalanceItem {
    private String product;
    private double quantity;
    private double quantity_unit;
    
    public BalanceItem(){
        product = "";
        quantity = 1;
        quantity_unit = 1;
    }
    
    public String getProduct(){
        return product;
    }
    
    public double getQuantity(){
        return quantity;
    }
    
    public double getQuantityUnit(){
        return quantity_unit;
    }
    
    public void setProduct(String pr){
        product = pr;
    }
    
    public void setQuantity(double q){
        quantity = q;
    }
    
    public void setQuantityUnit(double qu){
        quantity_unit = qu;
    }
}
