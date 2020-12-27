package model;

public class Variable extends Expression {
	
	public String id;

	public Variable(String id) {
		super();
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id;
	}

}
