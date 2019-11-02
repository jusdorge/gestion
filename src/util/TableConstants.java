package util;

public class TableConstants{
	//all table column names.
	public final static String ID =         "ID";
	public final static String IDA = 	"IDA";
        public final static String IDP =        "IDP";
        public final static String IDV =        "IDV";
        public final static String ADR =        "ADR";
        public final static String Wilaya =     "WILAYA";
        public final static String N_REG_COM =  "NRC";
        public final static String TEL =        "TEL1";
	public final static String IDPro = 	"idProvider";
	public final static String IDPR = 	"idProduct";
	public final static String IDS = 	"idSell";
	public final static String IDSB = 	"idSellBack";
	public final static String IDB = 	"idBuy";
	public final static String IDBB = 	"idBuyBack";
	public final static String NAME = 	"NOM";
        public final static String DES =        "DESIG";
        public final static String OBS =        "OBS";
	public final static String QNT = 	"QTEA";
	public final static String QNT_U = 	"QTU";
        public final static String QNT_U_O =    "QTUA";
	public final static String DATE = 	"D";
	public final static String TIME = 	"T";
	public final static String S_Price = 	"PRIXV";
	public final static String B_Price = 	"PRIXA";
        public final static String G_S_PRICE =  "PR";
	public final static String SOLDE = 	"(SOLDE2 + SOLDE) AS CREDIT";
        public final static String ST =         "STOCK";
	public final static String TOTAL = 	"TOTAL";
	public final static String TYPE = 	"MODE";
        public final static String UTIL =       "UTIL";
	
	//all table names
	public final static String productTableName = 		"produit";
	public final static String customerTableName = 		"client";
	public final static String providerTableName = 		"four";
	public final static String sellTableName = 		"vente";
	public final static String sellBackTableName = 		"retv";
	public final static String buyTableName = 		"achat";
	public final static String buyBackTableName = 		"reta";
	public final static String detailedSellTableName = 	"lvente";
	public final static String detailedSellBackTableName =  "lretv";
	public final static String detailedBuyTableName = 	"lachat";
	public final static String detailedBuyBackTableName =   "lreta";
	public final static String providerPaymentTableName = 	"versf";
	public final static String customerPaymentTableName = 	"versc";
        public final static String quoteTableName =             "devis";
        public final static String detailedQuoteTableName =     "ldevis";
        public final static String lossTableName =              "pert";
        public final static String detailedLossTableName =      "lpert";
        public final static String orderTableName =             "commande";
        public final static String detailedOrderTableName =      "lcommande";
        public final static String billTableName =              "fact";
        public final static String detailedBillTableName =      "lfact";

	//all table name in french.
	public final static String frenchProductTableName = 		"produit";
	public final static String frenchCustomerTableName = 		"client";
	public final static String frenchProviderTableName = 		"fournisseur";
	public final static String frenchSellTableName = 		"vente";
	public final static String frenchSellBackTableName = 		"retour vente";
	public final static String frenchBuyTableName = 		"achat";
	public final static String frenchBuyBackTableName = 		"retour achat";
	public final static String frenchDetailedSellTableName = 	"detail vente";
	public final static String frenchDetailedBuyTableName = 	"detail achat";
	public final static String frenchProviderPaymentTableName =	"versement fournisseur";
	public final static String frenchCustomerPaymentTableName =     "versement client";
	
	//sets of table column names.
	
        public final static String [] productColumnNames = {IDP, DES, QNT_U, S_Price, B_Price, G_S_PRICE, ST};
        public final static String [] PRODUCT_COLUMNS = {DES, QNT_U, S_Price};
	public final static String [] sellProductColumnNames = {ID, DES, QNT, QNT_U, S_Price};
        public final static String [] SELL_PRODUCT_COLUMNS = {DES, QNT_U, S_Price,ST};
	public final static String [] buyProductColumnNames = {ID, DES, QNT, QNT_U, B_Price};
        public final static String [] BUY_PRODUCT_COLUMNS = {DES, QNT_U, B_Price,ST};
	public final static String [] customerColumnNames = {ID, NAME, ADR,TEL , SOLDE};
        public final static String [] CUSTOMER_COLUMNS = {NAME, SOLDE};
	public final static String [] providerColumnNames = {ID, NAME, ADR,TEL , SOLDE};
        public final static String [] PROVIDER_COLUMNS = {NAME, SOLDE};
	public final static String [] sellColumnNames = {IDA, ID, DATE, TIME, TYPE, TOTAL};
	public final static String [] sellBackColumnNames = {ID, IDA, DATE, TIME, TYPE, TOTAL};
	public final static String [] buyColumnNames = {IDA, ID, DATE, TIME,TYPE, TOTAL};
	public final static String [] buyBackColumnNames = {ID, IDP, DATE, TIME, TYPE, TOTAL};
	public final static String [] detailedSellColumnNames = {ID,IDS, IDPR, QNT, QNT_U, S_Price};
	public final static String [] detailedSellBackColumnNames = {ID, IDS, IDPR, QNT, QNT_U, S_Price};
	public final static String [] detailedBuyColumnNames = {ID, IDB, IDPR, QNT, QNT_U, B_Price};
	public final static String [] detailedBuyBackColumnNames = {ID, IDB, IDPR, QNT, QNT_U, B_Price};
	public final static String [] providerPaymentColumnNames = {ID, IDP, IDB, DATE, TIME, TOTAL};
	public final static String [] customerPaymentColumnNames = {ID, IDA, IDS, DATE, TIME, TOTAL};
        public final static String [] lossColumnNames ={IDA, IDV, DATE, TIME, TYPE, UTIL, OBS };
        public final static String [] orderColumnNames = lossColumnNames;
        public final static String [] quoteColumnNames = lossColumnNames;
        public final static String [] billColumnNames = lossColumnNames;
        
}