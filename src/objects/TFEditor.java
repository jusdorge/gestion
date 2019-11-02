package objects;
/**
 * TFEditor is used by OperationWindow.java.
 */

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import util.Utilities;


/**
 * Implements a cell editor that can select the field value
 * when editing the table.
 */
public class TFEditor extends DefaultCellEditor{
    JTextField tf;
    int r;
    int c;

    public TFEditor() {
        super(new JTextField());
        tf = (JTextField)getComponent();
        //tf.setFont(Utilities.tableFont());
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