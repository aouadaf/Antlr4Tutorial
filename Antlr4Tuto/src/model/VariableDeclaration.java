package model;

public class VariableDeclaration extends Expression{
	public String id;
	public String type;
	public String value;
	
	public VariableDeclaration(String id, String type, String value) {
		super();
		this.id = id;
		this.type = type;
		this.value = value;
	}
	
	

}
