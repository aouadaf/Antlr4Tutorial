package listner;


import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

public class ExprSyntaxErrorListener extends BaseErrorListener {
	
	public static boolean hasError = false;

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, 
			Object offendingSymbol, 
			int line, int charPositionInLine,
			String msg, 
			RecognitionException e) {
		
		hasError = true;
		
		List<String> stack = ((Parser) recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		System.err.println("Syntax Error !");
		System.err.println("Token "+"\""+((Token) offendingSymbol).getText()+"\""
							+" (line "+line+" colunm "+charPositionInLine
							+": "+msg);
		System.err.println("Rule stack "+ stack);
	}
	
	

}
