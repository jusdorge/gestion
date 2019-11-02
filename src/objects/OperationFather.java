package objects;
import util.Operation;
public abstract class OperationFather{
	private Operation type;
	
	public OperationFather(){
		type = null;
	}
	
	public OperationFather(Operation type){
		this.type = type;
	}
	
	public Operation getType(){
		return type;
	}
	public abstract Object get(int index);
}