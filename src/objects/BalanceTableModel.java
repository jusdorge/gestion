/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class BalanceTableModel extends AbstractTableModel 
                            implements TableModelListener{
    private String columnNames[] = {"Produit", "Quantite", "Qte_U"};
    private ArrayList<BalanceItem> data = new ArrayList<>();
    
    public BalanceTableModel(){
        super();
        //data.add(new BalanceItem());
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:return data.get(rowIndex).getProduct();
            case 1:return data.get(rowIndex).getQuantity();
            case 2:return data.get(rowIndex).getQuantityUnit();
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int index){
        return columnNames[index];
    }
    
    public void add(BalanceItem bi){
        data.add(bi);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1 );
    }
    
    /*
    * Removes a product from the list at the index position. 
    */
    public void remove(int index){
	data.remove(index);
	try{
		fireTableRowsDeleted(index , index );
	}catch(IndexOutOfBoundsException e){
		JOptionPane.showMessageDialog(null,"la table est vide");
	}
    }
    
    /**
     * removes all the data in the table model
     */
    public void removeAll(){
        for(int i = 0; i < getRowCount();i++){
            remove(i);
        }
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        fireTableChanged(e);
    }
    
}
