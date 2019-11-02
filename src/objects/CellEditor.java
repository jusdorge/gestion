/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import util.Utilities;

/**
 *
 * @author DELL
 */
public class CellEditor extends DefaultCellEditor{
    JTextField tf;
    int r;
    int c;

    public CellEditor() {
        super(new JTextField());
        tf =(JTextField)getComponent();
    }

    //Override to invoke setValue on the text field.
    public Component getTableCellEditorComponent(JTable table,
        Object value, boolean isSelected,
        int row, int column) {
        tf = (JTextField) super.getTableCellEditorComponent(
                table, value, isSelected, row, column);
	tf.selectAll();
	r = row;
	c = column;
        return tf;
    }

    //Override to ensure that the value remains what is expected to be.
    // an Integer if it should be integer
    // a double if it should be and text if it should be text.
    public Object getCellEditorValue() {
        Object result;
        switch (c){
            case 0: 
                    result = tf.getText();
                    break;
            case 1: case 2: case 3: 
                    result = new Double(tf.getText());
                    break;
            default : 
                    result = tf.getText();
                    break;
        }
        return result;
    }
	
    //Override to check whether the edit is valid,
    //setting the value if it is and complaining if
    //it isn't.  If it's OK for the editor to go
    //away, we need to invoke the superclass's version 
    //of this method so that everything gets cleaned up.
    public boolean stopCellEditing() {
        return super.stopCellEditing();
    }

}
