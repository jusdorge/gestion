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
public class RecordPayment {
    JDBCAdapter conn;
    private Object OPE; 
    private Object TAB;
    private Object IDV;
    private Object ID;
    private Object D;
    private Object T;
    private Object MODE;
    private Object NC;
    private Object BANC;
    private Object MONT;
    private Object UTIL;
    private Object OBS;
    private Object IDA;
    private Object P;
    public RecordPayment( Object OPE, //1
                            Object TAB, //2
                            Object IDV, //3
                            Object ID,  //4
                            Object D,   //5
                            Object T,   //6
                            Object MODE,//7
                            Object NC,  //8
                            Object BANC,//9
                            Object MONT,//10
                            Object UTIL,//11
                            Object OBS, //12
                            Object IDA, //13 
                            Object P)   //14
    {
        this.OPE = OPE;
        this.TAB = TAB;
        this.IDV = IDV;
        this.ID = ID;
        this.D = D;
        this.T = T;
        this.MODE = MODE;
        this.NC = NC;
        this.BANC = BANC;
        this.MONT = MONT;
        this.UTIL = UTIL;
        this.OBS = OBS;
        this.IDA = IDA;
        this.P = P;
        conn = JDBCAdapter.connect();
    }
    private String getDeleteString(){
        String result ="CALL PROC_VERS(";
        result += 3 + ",";
        result += TAB + ",";
        result += IDV + ",";
        result += ID + ",'";
        result += D + "','";
        result += T + "','";
        result += MODE + "','";
        result += NC + "','";
        result += BANC + "',";
        result += MONT + ",";
        result += UTIL + ",'";
        result += OBS + "',";
        result += IDA + ",'";
        result += P + "')";
        return result;
        
    }
    private String getRecordString(){
        String result ="CALL PROC_VERS(";
        result += OPE + ",";
        result += TAB + ",";
        result += IDV + ",";
        result += ID + ",'";
        result += D + "','";
        result += T + "','";
        result += MODE + "','";
        result += NC + "','";
        result += BANC + "',";
        result += MONT + ",";
        result += UTIL + ",'";
        result += OBS + "',";
        result += IDA + ",'";
        result += P + "')";
        return result;
    }
    public void recordPayment(){
        conn.executeQuery(getRecordString());
    }
    
    public void deletePayment(){
        conn.executeUpdate(getDeleteString());
    }
}
