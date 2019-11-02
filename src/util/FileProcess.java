/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author DELL
 */
public enum FileProcess {
    CREATE("NOUVELLE"),
    DELETE("SUPPRIMER"),
    MODIFY("MODIFICATION"),
    CONSULT("CONSULTATION"),
    RESTORE("RESTAURATION");
    private String title;
    
    FileProcess(String t){
        this.title = t;
    }
    
    public String getProcessTitle(){
        return title;
    }   
    
    public String getProcessTitle(Operation op){
        if (this == CREATE){
            String result;
            switch (op){
                case CUSTOMER:
                case PROVIDER:
                case PRODUCT:
                case BUYBACK:
                case SELLBACK:
                    result = "NOUVEAU";
                break;
                default:
                    return title;
            }
            return result;
        }
        return title;
    }
}
