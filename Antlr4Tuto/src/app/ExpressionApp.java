package app;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import listner.ExprSyntaxErrorListener;
import model.Program;
import parser.ExprLexer;
import parser.ExprParser;
import visitors.AntlrToProgram;

public class ExpressionApp {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("Usage: file name");
		}
		else {
			String fileName = args[0];
			//create the parser for our file
			ExprParser parser = getParser(fileName);
			
			//Tell antlr to build a parse tree
			//Starts parsing from the Prog rule 
			//(you can start from any rule you want, just make sure your file respect that too)
			ParseTree parseTree = parser.prog();
			
			if (ExprSyntaxErrorListener.hasError) {
				
			}
			else {
				//visit the parse tree and generate our model object tree
				AntlrToProgram programVisitor = new AntlrToProgram();
				Program program = programVisitor.visit(parseTree);
				
				//Evaluating the program
			}
			
			
			
		}
			

	}

	private static ExprParser getParser(String fileName) {
		ExprParser parser = null;

		try {
			CharStream input = CharStreams.fromFileName(fileName);
			ExprLexer lexer = new ExprLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			parser = new ExprParser(tokens);
			//remove and add our error listener
			parser.removeErrorListeners();
			parser.addErrorListener(new ExprSyntaxErrorListener());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return parser;
	}

}
