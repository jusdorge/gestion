/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupr�s des autorit�s responsable * 
 */
package Adapters;

/**
 *
 * @author DELL
 */
public class DateAdapter {
    int year;
    int month;
    int day;
    public DateAdapter(){
        year = 0;
        month = 0;
        day = 0;
    }
    public DateAdapter(int y, int m, int d){
        year = y;
        month = m;
        day = d;
    }
    
    static public String revertDate(String date){
        String result = "";
        //System.out.println(date);
        result += date.substring(6, 8);
        result += "-";
        result += date.substring(3, 5);
        result += "-";
        result += date.substring(0,2);
        
        return result;
    }
    static public String ConvertDateAdapter(String s){
        return  s.substring(6, 10) +
                "-" +
                s.substring(3, 5)+
                "-"+
                s.substring(0,2);
// + "/" +
//                s.substring(3,4) + "/" +
//                s.substring(0,1);
    }
    static public String convertDate(String s){
        return s.substring(8,10)+
                "/" + 
                s.substring(5, 7) +
                "/" +
                s.substring(0,4);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        System.out.println(DateAdapter.ConvertDateAdapter("20/01/2019"));
    }  
}
