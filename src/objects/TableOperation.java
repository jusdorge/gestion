package objects;
import util.Operation;
public class TableOperation{
	private Operation type;
	
	public TableOperation(){
		type = null;
	}
	
	public TableOperation(Operation type){
		this.type = type;
	}
	
	public Operation getType(){
		return type;
	}
}