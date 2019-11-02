/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupr�s des autorit�s responsable * 
 */
package Adapters;

import java.util.ArrayList;

/**
 *(IDA,IDP,TVA,QTEA,QTUA,PRIXA,ST,IDD)
 * @author BENHADDOU MOHAMED AMINE
 * creates the lines of the details of the operation 
 */
public class Buttom {
    private Object ida;
    private Object idp;
    private Object tva;
    private Object qtea;
    private Object qtua;
    private Object prixa;
    private Object st;
    private Object idd;
    
    public Buttom(ArrayList list){
        ida = list.get(0);
        idp = list.get(1);
        tva = list.get(2);
        qtea = list.get(3);
        qtua = list.get(4);
        prixa = list.get(5);
        st = list.get(6);
        idd = list.get(7);
    }
    
    public Object getIda(){
        return ida;
    }
    public Object getIdp(){
        return idp;
    }
    public Object getTva(){
        return tva;
    }
    public Object getQtea(){
        return qtea;
    }
    public Object getQtua(){
        return qtua;
    }
    public Object getPrixa(){
        return prixa;
    }
    public Object getSt(){
        return st;
    }
    public Object getIdd(){
        return idd;
    }
}
