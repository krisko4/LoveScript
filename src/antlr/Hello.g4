grammar Hello;


start: statement* EOF;


statement: print_stmt
           | read_stmt
           | assign_stmt
           | return_stmt
           | function_stmt
           | function_call
           | if_stmt
           | while_stmt;

while_stmt: WHILE OPENBRACKET expression CLOSEBRACKET while_start statement* CLOSEBLOCK;
if_stmt: IF OPENBRACKET expression CLOSEBRACKET if_start statement* if_end (elseif_stmt)* (else_stmt)?;
while_start: OPENBLOCK;
if_start: OPENBLOCK;
if_end: CLOSEBLOCK;
elseif_stmt: ELSEIF OPENBRACKET expression CLOSEBRACKET OPENBLOCK statement* CLOSEBLOCK;
else_stmt: ELSE OPENBLOCK statement* CLOSEBLOCK;
function_stmt:  ID OPENBRACKET function_param?(',' function_param)*  CLOSEBRACKET function_start statement* CLOSEBLOCK;
function_param: ID;
function_start: OPENBLOCK;
print_stmt: PRINT OPENBRACKET val CLOSEBRACKET | PRINT OPENBRACKET array_element CLOSEBRACKET;
read_stmt: READ TYPE TO ID;
assign_stmt: ID ASSIGN expression
| ID OPENARRAY INT CLOSEARRAY ASSIGN expression
| ID ASSIGN function_call;
function_call: ID OPENBRACKET (expression)?(',' expression)* CLOSEBRACKET;
return_stmt: RETURN expression;
expression: val #value
| expression MULT expression #mul
| expression DIVIDE expression #divide
| expression MINUS expression #sub
| expression PLUS expression #add
| expression COMPARE expression #compare
;




array_element: ID OPENARRAY INT CLOSEARRAY;
val:
   ID #id
 | INT #int
 | REAL #real
 | ARRAY OPENARRAY INT CLOSEARRAY  #array
 | STRING #string
 | TOINT ID #toint
 | TOREAL ID #toreal
 | '(' expression ')' #priorityExpression;

WHILE: 'while';
COMPARE: '<' | '>' | '<=' | '>=' | '=='; // < > <= >= ==
IF: 'if';
ELSEIF: 'else if';
ELSE: 'else';
RETURN: 'return';
ARRAY: 'couple';
OPENBLOCK: '{';
CLOSEBLOCK: '}';
OPENARRAY: '[';
CLOSEARRAY: ']';
OPENBRACKET: '(';
CLOSEBRACKET: ')';
TOINT: '(single)';
TOREAL: '(taken)';
DIVIDE: ':/';
MULT: ':*';
MINUS: ':(';
PLUS: ':)';
STRING : '"'[a-zA-Z0-9_ ]* '"';
ASSIGN : '<3';
TO : 'do';
TYPE: 'INT' | 'REAL' | 'STRING';
PRINT: 'flirt';
READ: 'listen';
NEWLINE: [\r\n] -> skip;
ID:   [a-zA-Z][a-zA-Z0-9_]*;
WS : [ \t] -> skip;
REAL: '-'?[0-9]+'.'[0-9]+;
INT: '-'?[0-9]+;





