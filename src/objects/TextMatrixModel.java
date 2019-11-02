package objects;
import javax.swing.table.DefaultTableModel;
	/**
	* Matrix representing a table model
	*/
	public class TextMatrixModel extends DefaultTableModel{
		public TextMatrixModel(){
			super();
		}
		public TextMatrixModel(Object[][] data){
			super(data.length, data[0].length);
			for (int i = 0; i < data.length; i++){
				for (int j = 0; j < data[0].length; j++){
					super.setValueAt(data[i][j], i, j) ;
				}
			}
		}
		
		public TextMatrixModel(Object[][] data, String[] columnNames){
			super(data, columnNames);
		}
		
		public TextMatrixModel(TextMatrixModel model){
			super(model.getRowCount(), model.getColumnCount());
			for (int i = 0; i < model.getRowCount(); i++){
				for(int j = 0; j < model.getColumnCount(); j++){
					setValueAt(model.getValueAt(i,j), i, j);
				}
			}
		}
		public void add(TextMatrixModel model){
			for (int i = 0; i < model.getRowCount(); i++){
				Object[] row = new Object[model.getColumnCount()];
				for (int j = 0; j < model.getColumnCount(); j++){
					row[j] = model.getValueAt(i,j);
				}
				super.addRow(row);
			}
		}
		
		public void add(Object[] row){
			super.addRow(row);
		}
		
		public void add(Object[][] data){
			for (int i = 0; i < data.length; i++){
				Object[] rowData = new Object[data[i].length];
				for (int j = 0; j <  data[i].length; j++){
					rowData[j] = data[i][j];
				}
				super.addRow(rowData);
			}
		}
		
		public void print(){
			for (int i = 0; i < getRowCount(); i++){
				for (int j = 0; j < getColumnCount(); j++){
					System.out.print(getValueAt(i,j) + "\t");
				} 
				System.out.println();
			}
		}
	}
