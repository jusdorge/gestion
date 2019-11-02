/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupres des autorites responsable * 
 */
package Adapters;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import util.FileProcess;

/**
 *
 * @author BENHADDOU MOHAMED AMINE
 * classe pour l'enregistrement des operations
 * header pour l'entete de l'operation
 * buttol pour le corp de l'operation
 */
public class RecordOperation {
    private int tab;
    private int ope;
    private Header h;
    private TableModel m;
    private ArrayList allRecordButtoms;
    
            
    public RecordOperation(int TAB, int OPE,Header head, TableModel model){
        tab = TAB;
        ope = OPE;
        h = head;
        m = model;
        allRecordButtoms = new ArrayList();
    }
    
    public String getRecordHeadString(){
        String sql_head = "CALL PROC_ACHAT(" + 
                ope + "," +
                tab + "," + 
                h.getIda() + "," +
                h.getId() + "," +
                h.getIdv() + ",'" +
                h.getDate() + "','" +
                h.getTime() + "','" +
                h.getMode() + "'," +
                h.getUtil() + ",'" +
                h.getObs() + "'," +
                h.getIdUtil() + ",'" +
                h.getP() + "')";
        return sql_head;
    }
    private String getTableName(){
        String table="";
        switch(tab){
            case 1:
                table = "achat";
            break;
            case 2:
                table = "vente";
            break;
            case 3:
                table = "reta";
            break;
            case 4:
                table = "retv";
            break;
            case 5:
                table = "commande";
            break;
            case 6:
                table = "devis";
            break;
            case 7:
                table = "fact";
            break;
            case 8:
                table = "pert";
            break;
            case 9:
                table = "trans";
            break;
        }
        return table;
    }
    private String getDeleteHeadString(){
        
        String sql = "DELETE FROM " + getTableName() + " WHERE ida=" + h.getIda();
        return sql;
    }
    private String getDeleteButtomString(Buttom b){
        String sql_buttom = "CALL PROC_LACHAT (" +
                3 + "," + 
                tab + "," +
                b.getIda() + "," +
                b.getIdp() + "," +
                b.getTva() + "," +
                b.getQtea() + "," +
                b.getQtua() + "," +
                b.getPrixa() + "," +
                b .getSt() + "," +
                b.getIdd() +
                ")";
        return sql_buttom;        
    }
    public String getRecordButtomString(Buttom b){
        String sql_buttom = "CALL PROC_LACHAT (" +
                ope + "," + 
                tab + "," +
                b.getIda() + "," +
                b.getIdp() + "," +
                b.getTva() + "," +
                b.getQtea() + "," +
                b.getQtua() + "," +
                b.getPrixa() + "," +
                b .getSt() + "," +
                b.getIdd() +
                ")";
        return sql_buttom;
    }
    
    public void record_head(){
        JDBCAdapter record_head = JDBCAdapter.connect();
        if (ope == 1){
            record_head.executeQuery(getRecordHeadString());
        }else{
            record_head.executeUpdate(getRecordHeadString());       
        }
        System.out.println(getRecordHeadString());
        if (record_head.getUpdateError())
            System.err.println(record_head.getErrorMessage() + record_head.getErrorCause());
    }
    
    public void deleteHead(){
        JDBCAdapter deleteHead = JDBCAdapter.connect();
        deleteHead.executeUpdate(getDeleteHeadString());
        System.out.println(getDeleteHeadString());
        if (deleteHead.getUpdateError())
            System.err.println(deleteHead.getErrorMessage() + deleteHead.getErrorCause());
    }
    
    public ArrayList getAllRecordButtoms(){
        return allRecordButtoms;
    }
    
    public void deleteAllButtoms(){
        String sql = "DELETE FROM L" + getTableName() + 
                    " WHERE ida=" + h.getIda();
        JDBCAdapter record_buttom = JDBCAdapter.connect();
        record_buttom.executeUpdate(sql);
        System.out.println(sql);
        if (record_buttom.getUpdateError())
            System.err.println(record_buttom.getErrorMessage() + 
                    record_buttom.getErrorCause());
    }
    
    public void recordAllButtoms(){
        for (int i = 0; i < m.getRowCount(); i++){
            ArrayList l = new ArrayList();
            Product p = new Product(m.getValueAt(i, 0));
            l.add(0, h.getIda()); //----ida
            l.add(1, p.getId());//------idp
            l.add(2,0);//---------------tva
            l.add(3, getQtea(i));//-----qtea
            l.add(4, getQtua(i));//-----qtua
            l.add(5, getPrixa(i));//----prixa
            l.add(6, p.getStock());//---st
            l.add(7,1);
            Buttom b = new Buttom(l);
            allRecordButtoms.add(b);
            JDBCAdapter record_buttom = JDBCAdapter.connect();
            if (ope == 1){
                record_buttom.executeQuery(getRecordButtomString(b));
            }else{
                record_buttom.executeUpdate(getRecordButtomString(b));
            }
            System.out.println(getRecordButtomString(b));
            if (record_buttom.getUpdateError())
                System.err.println(record_buttom.getErrorMessage() + 
                        record_buttom.getErrorCause());
        }   
    }
    
    private Object getQtea(int i){
        return m.getValueAt(i, 1);
    }
    private Object getQtua(int i){
        return m.getValueAt(i, 2);
    }
    private Object getPrixa(int i){
        return m.getValueAt(i, 3);
    }
/**
 * BENHADDOU MOHAMED AMINE
 * une classe pour l'extraction de l'id et le stock 
 * de la table produit 
 */
    class Product{
        private Object id;
        private Object stock;
        JDBCAdapter look;
        
        public Product(Object des){
            look = JDBCAdapter.connect();
            String sql ="SELECT idp, stock FROM produit WHERE desig ='" + des +"'";
            look.executeQuery(sql);
            id = look.getValueAt(0, 0);
            stock = look.getValueAt(0, 1);
        }
        public Object getId(){
            return id;
        }
        public Object getStock(){
            return stock;
        }
    }
}