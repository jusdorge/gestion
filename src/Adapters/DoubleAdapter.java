/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupr�s des autorit�s responsable * 
 */
package Adapters;

/**
 *
 * @author ahmed
 */
public class DoubleAdapter {
    static private boolean canFormat = false;
    static private String result;
    
    static private void formatDoubleToString(double number){
        result = Double.toString(number);
        String numberPart = result.substring(0, result.indexOf("."));
                    String decimalPart = result.substring(result.indexOf("."),result.length());
                    if (decimalPart.length() > 3){
                        canFormat = true;
                        decimalPart = result.substring(result.indexOf("."),
                                result.indexOf(".") + 3);
                        result = numberPart + decimalPart;
                    }        
    }
    
    static public boolean isFormatable(double number){
        formatDoubleToString(number);
        return canFormat;
    }
    
    static public String getFormatToString(){
        return result;
    }
}
