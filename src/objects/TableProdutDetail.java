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
public class TableProdutDetail extends TableProduct{
    final static String [] columnNames = {TableProduct.NAME, TableProduct.QNT};
    
    public TableProdutDetail(){
        super();
    }
    
    public Class getColumnClass(int index){
            switch(index){
                case 0:
                        return String.class;
                case 1: 
                        return Integer.class;
                case 2:
                case 3:
                        return Double.class;
                default:
                        return Object.class;
            }
    }
    
    public Object get(int index){
        Object result;
        switch (index){
                case 0: result = super.getName();
                                break;
                case 1: result = super.getQuantity();
                                break;
                case 2: result = super.getSellPrice();
                                break;
                case 3: result = super.getBuyPrice();
                                break;
                default: result = null;
                                System.out.println("propriété inexistante");
                                break;
        }
        return result;
    }

    	public void set(Object value, int index){
            switch (index){
                case 0: super.setName((String)value);
                        break;
                case 1: super.setQuantity((double)value);
                        break;
                case 2: super.setSellPrice((double)value);
                        break;
                case 3: super.setBuyPrice((double)value);
                        break;
                default:System.out.println("Propriété inexistante");
                        break;
            }
	}
}
