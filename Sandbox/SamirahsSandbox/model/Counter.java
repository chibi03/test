package model;
public class Counter {

	private int count;
	public Counter(){
		this.count = 0;
	}
	
	public String getCount(){
		return Integer.toString(count);
	}
	
	public void setCount(int count){
		this.count = count;
	}
	
	public void increment(){
		count++;
	}
}
