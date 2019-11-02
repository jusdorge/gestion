package objects;
public class TableProduct {
	final static String NAME    = "name";
	final static String QNT     = "quantity";
	final static String QNT_U   = "unitQuantity";
	final static String S_PRICE = "sellPrice";
	final static String B_PRICE = "buyPrice";
	final static String [] columnNames = {NAME, QNT, QNT_U};
	
	private int id = 0;
	private String name;
	private double quantity = 1;
	private double unitQuantity = 1.0;
	private double sellPrice = 0.0;
	private double buyPrice = 0.0;
	
	////////////////////////////////////////////
	////////////							////
	////////////	CONSTRUCTORS			////
	////////////							////
	////////////////////////////////////////////
	
	public TableProduct(){
		name = "";
	}
	
	public TableProduct(String n){
		name = n;
	} 
	
	public TableProduct(String n, double uq){
		name = n;
		unitQuantity = uq;
	}
	
	public TableProduct( String n, double q, double uq){
		name = n;
		quantity = q;
		unitQuantity = uq;
	}

	public TableProduct (String n, double q, double uq, double sp, double bp){
		name = n;
		quantity = q;
		unitQuantity = uq;
		sellPrice = sp;
		buyPrice = bp;
	}	

//////////////////////////////////////
//////							//////
////// 		Getters...			//////
//////							//////
//////////////////////////////////////
	public Class getColumnClass(int index){
		switch(index){
			case 0:
				return String.class;
			case 1: 
				return Integer.class;
			case 2:
			case 3:
			case 4:
				return Double.class;
			default:
				return Object.class;
		}
		
	}
	public Object get(int index){
		Object result;
		switch (index){
			case 0: result = name;
					break;
			case 1: result = quantity;
					break;
			case 2: result = unitQuantity;
					break;
			case 3: result = sellPrice;
					break;
			case 4: result = buyPrice;
					break;
			default: result = null;
					System.out.println("propriété inexistante");
					break;
		}
		return result;
	}
	
	public Object get(String name){
		Object result;
		int index = getColumnIndex(name);
		result = get(index);
		return result;
	}
		
	public String getName(){
		return name;
	}
	
	public double getQuantity(){
		return quantity;
	}
	
	public double getUnitQuantity(){
		return unitQuantity;
	}
	
	public double getSellPrice(){
		return sellPrice;
	}
	
	public double getBuyPrice(){
		return buyPrice;
	}

/////////////////////////////////
//////						/////	
//////		 Setters...		/////
//////						/////
/////////////////////////////////

	public void set(Object value, int index){
            switch (index){
                case 0: name = (String)value;
                        break;
                case 1: quantity = (double)value;
                        break;
                case 2: Number result = (Number)value;
                        unitQuantity = result.doubleValue();
                        break;
                case 3: sellPrice = (double)value;
                        break;
                case 4: buyPrice = (double)value;
                        break;
                default:System.out.println("Propriété inexistante");
                        break;
            }
	}
		
	public void setName(String n){
		name = n;
	}
	
	public void setQuantity(double q){
		quantity = q;
	}
	
	public void setUnitQuantity(double uq){
		unitQuantity = uq;
	}

	public void setSellPrice(double sp){
		sellPrice = sp;
	}	
	
	public void setBuyPrice(double bp){
		buyPrice =  bp;
	}
	
	//compraison des valeurs de l'objet un à un.
	public boolean equals(TableProduct p){
		if ((p.getName() == name) &&
			(p.getQuantity() == quantity) &&
			(p.getUnitQuantity() == unitQuantity) ){
						return true;
					}else{
						return false;
					}
	}

	// returns the index of the column name passed as parameter. 
	public int getColumnIndex(String name){
		for (int i = 0; i < columnNames.length; i++){
			if (columnNames[i] == name) return i;
		}
		return -1;
	}
	
	//returns a string representing the information of the base product.
	public String toString(){
		String result;
		result = "id : " + id + "\t";
		result = result + "Name : " + name + "\t";
		result = result + "quantity : " + quantity + "\t";
		result = result + "unit quantity : " + unitQuantity;
		return result;
	}

}
