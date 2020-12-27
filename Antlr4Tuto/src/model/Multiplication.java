package model;

public class Multiplication extends Expression {
	
	Expression left;
	Expression right;
	
	public Multiplication(Expression left, Expression right) {
		super();
		this.left = left;
		this.right = right;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return left.toString()+ " * " +right.toString();
	}

}
