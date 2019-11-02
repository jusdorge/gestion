/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import objects.TableProdutDetail;

/**
 *
 * @author DELL
 */
public class TableModelDetail extends MyTableModel {
     private String[] columnNames = {"Produit",    
                                     "Quantite",
                                     "Prix_Uni",
                                     "Montant"};
    private ArrayList<TableProdutDetail> data = new ArrayList<TableProdutDetail>();
    public TableModelDetail(){
    	super();
	addTableModelListener(this);
    }
    public int getColumnCount() {
    	return columnNames.length;
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public double getSum(){
        double sommum = 0.0;
        for (int i = 0; i < getRowCount(); i++){
                sommum = sommum + (double)getValueAt(i,3);
        }
        return sommum;
    }
    
    public boolean isCellEditable(int row, int col) {
    	//Note that the data/cell address is constant,
    	//no matter where the cell appears onscreen.
    	if (col > 2) {
    		return false;
    	} else {
    		return true;
    	}
    }
    @Override
    public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (row > -1){
                    double q = (double) getValueAt(row,1);
                    double prix = (double) getValueAt(row,2);
                    double montant = q * prix;
                    if ((column == 1)||(column == 2)){
                            setValueAt(montant, row, 3);	
                    }
            }
    }
}
