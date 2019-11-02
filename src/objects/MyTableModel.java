





package objects;

import Adapters.DoubleAdapter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import objects.TableProduct;

public class MyTableModel extends AbstractTableModel implements TableModelListener{
    private String[] columnNames = {"Produit",
                                    "Quantite",
                                    "Qte_Uni",
                                    "Prix_Uni",
                                    "Montant"};

	//	TableProduct is the base object for all the table rows.			
    private ArrayList<TableProduct> data = new ArrayList<TableProduct>();
    public MyTableModel(){
    	super();
		data.add(new TableProduct());
		addTableModelListener(this);
    }
	
    public double getSum(){
	double sommum = 0.0;
	for (int i = 0; i < getRowCount(); i++){
		sommum = sommum + (double)getValueAt(i,4);
	}
	return sommum;
    }
	
    public int getColumnCount() {
    	return columnNames.length;
    }

    public int getRowCount() {
    	return data.size();
    }

    public String getColumnName(int col) {
    	return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	if ((row > - 1) &(col > - 1)){
			return data.get(row).get(col);
		}else{
			return null;
		}
    }
	public TableProduct getValueAt(int row){
		return data.get(row);
    }
    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        TableProduct product = this.getValueAt(0);
    	return product.getColumnClass(c);
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
    	//Note that the data/cell address is constant,
    	//no matter where the cell appears onscreen.
    	if (col > 3) {
    		return false;
    	} else {
    		return true;
    	}
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int column) 
    {
        TableProduct p = new TableProduct();
        p.set(value,column);
        for (int i = 0; i < getColumnCount(); i++){
                if (i != column){
                        p.set(getValueAt(row,i),i);
                }
        }
    	data.set(row,p);
        fireTableCellUpdated(row, column);
    }
	
	/*
	* adds a new product to the arraylist
	*/
    public void add(TableProduct p) {
		// TODO Auto-generated method stub
	data.add(p);
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
		//JOptionPane.showMessageDialog(null,"la table est vide");
	}
    }
	
    public TextMatrixModel getTableMatrixModel(){
        int row = this.getRowCount();
        int col = this.getColumnCount();
        Object[][] model  = new Object[row][col];
        for (int i = 0; i < row; i++){
                for (int j = 0; j < col; j++){
                        model[i][j] = this.getValueAt(i,j);
                }
        }
        return new TextMatrixModel(model, columnNames);
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (row > -1){
                    double q = (double) getValueAt(row,1);
                    double qu = (double) getValueAt(row,2);
                    double prix = (double) getValueAt(row,3);
                    double montant = q * (double)qu * prix;
                    if (DoubleAdapter.isFormatable(montant)){
                        montant = Double.parseDouble(DoubleAdapter.getFormatToString());
                    }
                    if ((column == 1)||(column == 2)||(column == 3)){
                        setValueAt(montant, row, 4);	
                    }
            }
    }
}