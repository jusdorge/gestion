package objects;

import java.text.*;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer; 
import java.util.Locale;
public class DoubleRenderer extends DefaultTableCellRenderer {
    
    public DoubleRenderer() { super(); }
	
    public void setValue(Object value) {
        String pattern = "#0.00";
        Locale loc = Locale.US;
        NumberFormat nf = NumberFormat.getNumberInstance(loc);
        DecimalFormat df = (DecimalFormat)nf;
        df.applyPattern(pattern);
        String output = df.format(value);
        setHorizontalAlignment(SwingConstants.RIGHT);
        setText(output);
    }
}
