/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupr�s des autorit�s responsable * 
 */
package Adapters;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class HeaderPrint {
    private ArrayList infoDocument;
    private Object titleDocument;
    private Object infoOperator;
    private Object operatorName;
    private Object date;
    private Object time;
    private Object pageNumber;
    private Object mode;
    
    public HeaderPrint(ArrayList al){
        infoDocument = al;
        titleDocument = (Object) al.get(0);
        infoOperator = (Object) al.get(1);
        operatorName = (Object) al.get(2);
        date = (Object) al.get(3);
        time = (Object) al.get(4);
        pageNumber = (Object) al.get(5);
    }
    
    public Object getTitleDocument(){
        return titleDocument;
    }
    public Object getInfoOperator(){
        return infoOperator;
    }
    public Object getOperatorName(){
        return operatorName;
    }
    public Object getDate(){
        return date;
    }
    public Object getTime(){
        return time;
    }
    public Object getPageNumber(){
        return pageNumber;
    }
}

