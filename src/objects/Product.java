/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupr�s des autorit�s responsable * 
 */
package objects;

import Adapters.JDBCAdapter;


    /**
 * BENHADDOU MOHAMED AMINE
 * une classe pour l'extraction de l'id et le stock 
 * de la table produit 
 */
    public class Product{
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