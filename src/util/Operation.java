package util;
import util.TableConstants;
import java.lang.reflect.Array;
 
public enum Operation{
	SELL_PRODUCT    ("PRODUIT VENDU",	TableConstants.productTableName,		
                                                TableConstants.sellProductColumnNames),
	BUY_PRODUCT	("PRODUIT ACHETE",	TableConstants.productTableName,		
                                                TableConstants.buyProductColumnNames),
	PRODUCT		("PRODUIT",  		TableConstants.productTableName,
                                                TableConstants.productColumnNames), 
	CUSTOMER	("CLIENT", 		TableConstants.customerTableName,
                                                TableConstants.customerColumnNames), 
	PROVIDER	("FOURNISSEUR", 	TableConstants.providerTableName,
                                                TableConstants.providerColumnNames), 
	SELL		("VENTE",		TableConstants.sellTableName,
                                                TableConstants.sellColumnNames),
	SELL_DETAIL     ("DETAIL VENTE",	TableConstants.detailedSellTableName,
                                                TableConstants.detailedSellColumnNames),
	BUY		("ACHAT",		TableConstants.buyTableName,
                                                TableConstants.buyColumnNames),
	BUY_DETAIL 	("DETAIL ACHAT",        TableConstants.detailedBuyTableName,	
                                                TableConstants.detailedBuyColumnNames),
	SELLBACK	("RETOUR DE VENTE",	TableConstants.sellBackTableName, 		
                                                TableConstants.sellBackColumnNames), 
SELLBACK_DETAIL         ("DETAIL RETOUR ACHAT",	TableConstants.detailedSellBackTableName,
                                                TableConstants.detailedSellBackColumnNames),
	BUYBACK		("RETOUR ACHAT",	TableConstants.buyBackTableName,		
                                                TableConstants.buyBackColumnNames),
BUYBACK_DETAIL          ("RETOUR VENTE",	TableConstants.detailedBuyBackTableName,
                                                TableConstants.detailedBuyBackColumnNames),
        LOSS            ("PERTE" ,              TableConstants.lossTableName,
                                                TableConstants.lossColumnNames),
        ORDER           ("COMMANDE" ,           TableConstants.orderTableName,
                                                TableConstants.orderColumnNames),
        QUOTE           ("DEVIS" ,              TableConstants.quoteTableName,
                                                TableConstants.quoteColumnNames),
        BILL            ("FACTURE" ,            TableConstants.billTableName,
                                                TableConstants.billColumnNames),
        SEARCH          ("RECHERCHE",           "",new String[0])
        ;
	private String frameTitle;
	private String tableName;
	private String[] columnNames;
	
	Operation(String frameTitle, String tableName, String[] columnNames){
		this.frameTitle = frameTitle;
		this.tableName = tableName;
		this.columnNames = columnNames;
	}
	
	public String getFrameTitle(){
		return frameTitle;
	}
	
	public String getTableName(){
		return tableName;
	}
	
	public String getDetailTableName(){
		switch(this){
			case SELL:
			case SELLBACK:
			case BUY:
			case BUYBACK:
				return "l" + this.getTableName() ;
			default:
				return null;
		}
	}
	
	public String[] getColumnNames(){
		return columnNames;
	}
	
	public String[]getDetailColumnNames(){
		switch(this){
			case SELL:
				return SELL_DETAIL.getColumnNames();
			case BUY:
				return BUY_DETAIL.getColumnNames();
			case SELLBACK:
				return SELLBACK_DETAIL.getColumnNames();
			case BUYBACK:
				return BUYBACK_DETAIL.getColumnNames();
			default :
				return null; 
		}
	}
	
	public Operation getOperand(){
		switch (this){
			case SELL:
			case SELLBACK:
				return SELL_PRODUCT;
			case BUY:
			case BUYBACK:
				return BUY_PRODUCT;
			default:
				return PRODUCT;
		}
	}
	
	public Operation getOperator(){
		switch (this){
			case SELL:
			case SELLBACK:
				return CUSTOMER;
			case BUY:
			case BUYBACK:
				return PROVIDER;
			default :
				return null;
		}
	}
	
	public int getProductSign(){
		switch(this){
			case SELL:
			case BUYBACK:
				return -1;
			case BUY:
			case SELLBACK:
				return 1;
			default:
				return 0;
		}
	}
	
	public Operation get(){
		return this;
	}

	public String[] getChoiceColumns(){
            String [] result = new String[10];
            switch (this){
                case SELL_PRODUCT:
                    result = TableConstants.SELL_PRODUCT_COLUMNS;
                    break;
                case BUY_PRODUCT:
                    result = TableConstants.BUY_PRODUCT_COLUMNS;
                    break;
                case PRODUCT:
                    result = TableConstants.PRODUCT_COLUMNS;
                    break;
                case PROVIDER:
                    result = TableConstants.PROVIDER_COLUMNS;
                    break;
                case CUSTOMER:
                    result = TableConstants.CUSTOMER_COLUMNS;
                    break;
                default:
                    result = null;
                    break;
            }
            return result;
        }
}