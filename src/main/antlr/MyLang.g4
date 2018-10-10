grammar MyLang;

types
    : 'int'
    ;

compilationUnit
    : funDeclaration* EOF
    ;

funDeclaration
    : result ID '(' funCallArgs? ')' block
    ;

result
    : types
    | 'void'
    ;

funCallArgs
    : funArgs (',' funArgs)*
    ;

funArgs
    : types ID
    ;

block
    : '{' blockStatements* '}'
    ;

blockStatements
    : localVariableDeclarationStatement ';'
    | statement
    | print
    ;

localVariableDeclarationStatement
	: types variableDeclaratorList
	;

variableDeclaratorList
    : variableDeclarator (',' variableDeclarator)*
    ;

variableDeclarator
    : ID ('=' additiveExpression)?
    ;

statement
    : block
	| assignment ';'
	| ifThenStatement
	| ifThenElseStatement
	| forStatement
	| funInvocation ';'
	| returnStatement
	| localVariableDeclarationStatement ';'
	| print
	;

ifThenStatement
    : 'if' '(' expression ')' statement
    ;

ifThenElseStatement
    : 'if' '(' expression ')' statement 'else' statement
    ;

forStatement
    : 'for' '(' localVariableDeclarationStatement? ';' expression? ';' forUpdate? ')' statement
    ;

forUpdate
	: statementExpressionList
	;

statementExpressionList
    : assignment (',' assignment)*
    ;

funInvocation
    : ID '(' argumentList? ')'
    ;

argumentList
    : expression (',' expression)*
    ;

print
    : 'print' printVariations ';'
    ;

printVariations
    : STRING (',' printVariations)*
    | expression (',' printVariations)*
    | STRING COLON expression (',' printVariations)*
    ;

returnStatement
    : 'return' expression? ';'
    ;

// EXPRESSION

expression
	:	conditionalOrExpression
	|	assignment
	;

assignment
	: ID assignmentOperator additiveExpression
	| ID assignmentOperator assignment
	;

assignmentOperator
	:	ASSIGN
	|	MUL_ASSIGN
	|	DIV_ASSIGN
	|	ADD_ASSIGN
	|	SUB_ASSIGN
	;

conditionalOrExpression
	:	conditionalAndExpression
	|	conditionalOrExpression OR conditionalAndExpression
	;

conditionalAndExpression
	:	equalityExpression
	|	conditionalAndExpression AND equalityExpression
	;

equalityExpression
	:	relationalExpression
	|	equalityExpression EQUAL relationalExpression
	|	equalityExpression NOTEQUAL relationalExpression
	;

relationalExpression
	:	additiveExpression
	|	relationalExpression LT additiveExpression
	|	relationalExpression GT additiveExpression
	|	relationalExpression LE additiveExpression
	|	relationalExpression GE additiveExpression
	;

additiveExpression
    : LPAREN additiveExpression RPAREN
    | additiveExpression (MUL|DIV) additiveExpression
    | additiveExpression (ADD|SUB) additiveExpression
    | funInvocation
    | NUMBER
    | ID
    ;

// Lexer

ID
    : [a-zA-Z_][a-zA-Z_0-9]*
    ;

NUMBER
    : [+-]?[0-9]+
    ;

STRING
    : '"' ~('\r' | '\n' | '"')* '"'
    ;

// Separators

LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
SEMI : ';';
COMMA : ',';

// Operators

ASSIGN : '=';
GT : '>';
LT : '<';
COLON : ':';
EQUAL : '==';
LE : '<=';
GE : '>=';
NOTEQUAL : '!=';
AND : '&&';
OR : '||';
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';


ADD_ASSIGN : '+=';
SUB_ASSIGN : '-=';
MUL_ASSIGN : '*=';
DIV_ASSIGN : '/=';

//
// Whitespace and comments
//

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;