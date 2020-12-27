package visitors;

import java.util.ArrayList;
import java.util.List;

import model.Program;
import parser.ExprBaseVisitor;
import parser.ExprParser.ProgramContext;

public class AntlrToProgram extends ExprBaseVisitor<Program> {
	
	private List<String> semanticErrors;

	@Override
	public Program visitProgram(ProgramContext ctx) {
		Program program = new Program();
		semanticErrors =  new ArrayList<>();
		
		AntlrToExpression expressionVisitor = new AntlrToExpression(semanticErrors);
		
		for (int i = 0; i < ctx.getChildCount() - 1; i++)
			program.addExpression(expressionVisitor.visit(ctx.getChild(i)));
		
		return program;
	}
	
	

}
