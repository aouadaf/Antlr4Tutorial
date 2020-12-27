grammar Expr;

@header {
	package parser;
}


prog: (decl | expr)+ EOF		# Program
	;
 		

decl: ID ':' INT_TYPE '=' NUM	# Declaration
	;

expr: expr '*' expr				# Multiplication
	| expr '+' expr				# Addition
	| ID						# Variable
	| NUM						# Number
	;
	
//tokens
ID: [a-z][a-zA-Z0-9]*;
INT_TYPE: 'INT';
NUM: '0' | '-'?[1-9][0-9]*;
COMMENT: '--' ~[\r\n] -> skip;
WS: [ \r\n\t]+ -> skip;