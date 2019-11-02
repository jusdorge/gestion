package objects;
import java.time.*;
import java.util.Calendar;
import util.Operation;

public class Sell extends OperationFather{
	private int idSell;
	private int idClient;
	private LocalDate date;
	private LocalTime time;
	private String mode;
	private double total;
	
	public Sell(){
		super(Operation.SELL);
		idSell = 0;
		idClient = 1;
		date = LocalDate.now();
		time = LocalTime.now();
		mode = "espece";
		total = 0.0;
	}
	public Sell(int ic, Calendar c, String m, double t){
		super(Operation.SELL);
		idSell = 0;
		idClient = ic;
		mode = m;
		total = t;
		int p = 0;
		int portion_of_day = c.get(Calendar.AM_PM);
		if (portion_of_day == Calendar.PM){
			p = 12;
		}
		int hr = p + c.get(Calendar.HOUR);
		int mn = c.get(Calendar.MINUTE);
		int sc = c.get(Calendar.SECOND);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
		int year = c.get(Calendar.YEAR);
		time = LocalTime.of(hr,mn,sc);
		date = LocalDate.of(year,month,day);
	} 
	public Sell(int is, int ic, Calendar d, String m, double tl){
		super(Operation.SELL);
		idSell = is;
		idClient = ic;
		mode = m;
		total = tl;
		int p = 0;
		int portion_of_day = d.get(Calendar.AM_PM);
		if (portion_of_day == Calendar.PM){
			p = 12;
		}
		int hr = p + d.get(Calendar.HOUR);
		int mn = d.get(Calendar.MINUTE);
		int sc = d.get(Calendar.SECOND);
		int day = d.get(Calendar.DAY_OF_MONTH);
		int month = d.get(Calendar.MONTH) + 1;
		int year = d.get(Calendar.YEAR);
		time = LocalTime.of(hr,mn,sc);
		date = LocalDate.of(year,month,day);
	} 
	public Sell(int is, int ic, LocalDate d, LocalTime t, String m, double tl){
		super(Operation.SELL);
		idSell = is;
		idClient = ic;
		date = d;
		time = t;
		mode = m;
		total =tl;
	}
	
	public int getIdSell(){
		return idSell;
	}
	
	public int getIdClient(){
		return idClient;
	}
	
	public LocalDate getDate(){
		return date;
	}
	
	public LocalTime getTime(){
		return time;
	}
	
	public double getTotal(){
		return total;
	}
	
	public String getPaymentMode(){
		return mode;
	}
	
	public Object get(int index){
		switch(index){
			case 0:return idSell;
			case 1: return idClient;
			case 2: return date;
			case 3: return time;
			case 4: return mode;
			case 5: return total;
			default : return null;
		}
	}
	
	public void print(){
		for (int i = 0; i < 6; i++)
			System.out.println(this.get(i));
	}
}