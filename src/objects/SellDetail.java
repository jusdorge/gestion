package objects;
import  util.Operation;

public class SellDetail extends OperationFather{
	private int idSellDetail;
	private int idSell;
	private int idProduct;
	private double quantity;
	private double unitQauntity;
	private double sellPrice;
	
	public SellDetail(){
		super(Operation.SELL_DETAIL);
		idSellDetail = 0;
		idSell = 0;
		idProduct = 0;
		quantity = 0.0;
		unitQauntity = 1.0;
		sellPrice = 0.0;
	}
	public SellDetail(int idSell, int idProduct, double quantity, double unitQauntity, double sellPrice){
		super(Operation.SELL_DETAIL);
		this.idSellDetail = 0;
		this.idSell = idSell;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.unitQauntity = unitQauntity;
		this.sellPrice = sellPrice;
	}
	
	public int getIdSellDetail(){
		return idSellDetail;
	}
	
	public int getIdSell(){
		return idSell;
	}
	
	public int getIdProduct(){
		return idProduct;
	}
	
	public double getQuantity(){
		return quantity;
	}
	
	public double getUnitQuantity(){
		return unitQauntity;
	}
	/*
	public double getBuyPrice(){
		return buyPrice;
	}*/
	
	public double getSellPrice(){
		return sellPrice;
	}
	
	public Object get(int index){
		switch(index){
			case 0:return idSellDetail;
			case 1: return idSell;
			case 2: return idProduct;
			case 3: return quantity;
			case 4: return unitQauntity;
			//case 5: return buyPrice;
			case 5: return sellPrice;
			default : return null;
		}
	}
	
	public void print(){
		for (int i = 0; i < 6; i++)
			System.out.println(this.get(i));
	}
}