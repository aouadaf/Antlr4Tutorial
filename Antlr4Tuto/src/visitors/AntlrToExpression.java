package visitors;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import model.Addition;
import model.Expression;
import model.Multiplication;
import model.Number;
import model.Variable;
import model.VariableDeclaration;
import parser.ExprBaseVisitor;
import parser.ExprParser.AdditionContext;
import parser.ExprParser.DeclarationContext;
import parser.ExprParser.MultiplicationContext;
import parser.ExprParser.NumberContext;
import parser.ExprParser.VariableContext;

public class AntlrToExpression extends ExprBaseVisitor<Expression> {
	
	private List<String> variables;
	private List<String> semanticErrors;// duplicate declaration and undeclared reference
	
	

	public AntlrToExpression(List<String> semanticErrors) {
		super();
		this.semanticErrors = semanticErrors;
		this.variables =new ArrayList<>();
	}

	@Override
	public Expression visitDeclaration(DeclarationContext ctx) {
		
		String id = ctx.ID().getText();
		String type = ctx.INT_TYPE().getText();
		String value = ctx.NUM().getText();
		
		Token idToken = ctx.ID().getSymbol();
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;
		if(variables.contains(id))
			semanticErrors.add("Error: variable "+id+" alredy declared ("+line+", "+column+")");
		else
			variables.add(id);
		return new VariableDeclaration(id, type, value);
		
	}

	@Override
	public Expression visitMultiplication(MultiplicationContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		return new Multiplication(left, right);
	}

	@Override
	public Expression visitAddition(AdditionContext ctx) {
		Expression left = visit(ctx.getChild(0));
		Expression right = visit(ctx.getChild(2));
		return new Addition(left, right);
	}

	@Override
	public Expression visitVariable(VariableContext ctx) {
		String id = ctx.ID().getText();
		
		Token idToken = ctx.ID().getSymbol();
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;
		if(!variables.contains(id))
			semanticErrors.add("Error: variable "+id+" never been declared ("+line+", "+column+")");
		return new Variable(id);
			
	}

	@Override
	public Expression visitNumber(NumberContext ctx) {
		int number = Integer.parseInt(ctx.NUM().getText());
		return new Number(number);
	}
	
	

}
