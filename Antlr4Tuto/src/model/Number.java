package model;

public class Number extends Expression {
	
	int num;
	
	public Number(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return Integer.valueOf(num).toString(); 
	}
}
