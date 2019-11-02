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
public class Header {
    private Object ida;     // 0
    private Object id;      // 1
    private Object idv;     // 2
    private Object date; // 3
    private Object time; // 4
    private Object mode; // 5
    private Object util;    // 6
    private Object obs;  // 7
    private Object idUtil;  // 8
    private Object poste;// 9
            
    public Header(ArrayList list){
            ida = list.get(0);
            id = list.get(1);
            idv = list.get(2);
            date = list.get(3);
            time = list.get(4);
            mode = list.get(5);
            util = list.get(6);
            obs = list.get(7);
            idUtil = list.get(8);
            poste = list.get(9);
    }
    
    public Object getIda(){
        return ida;
    }
    public Object getId(){
        return id;
    }
    public Object getIdv(){
        return idv;
    }
    public Object getDate(){
        return date;
    }
    public Object getTime(){
        return time;
    }
    public Object getMode(){
        return mode;
    }
    public Object getUtil(){
        return util;
    }
    public Object getObs(){
        return obs;
    }
    public Object getIdUtil(){
        return idUtil;
    }
    public Object getP(){
        return poste;
    }
    
}
