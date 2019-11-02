/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author DELL
 */
public class DateRenderer extends DefaultTableCellRenderer{
    SimpleDateFormat formatter;
    public DateRenderer(String pattern) {
        super(); 
        formatter = new SimpleDateFormat(pattern);
    }

    public void setValue(Object value) {
        if (formatter==null) {
            formatter = (SimpleDateFormat)SimpleDateFormat.getDateInstance();
        }
        setText((value == null) ? "" : formatter.format(value));
    }
}
