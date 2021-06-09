grammar Hello;


start: statement* EOF;


statement: print_stmt
           | read_stmt
           | assign_stmt
           | return_stmt
           | function_stmt
           | function_call;

function_stmt:  ID OPENBRACKET function_param?(',' function_param)*  CLOSEBRACKET function_start statement* CLOSEBLOCK;
function_param: ID;
function_start: OPENBLOCK;
print_stmt: PRINT val | PRINT array_element;
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

RETURN: 'return';
ARRAY: 'array';
OPENBLOCK: '{';
CLOSEBLOCK: '}';
OPENARRAY: '[';
CLOSEARRAY: ']';
OPENBRACKET: '(';
CLOSEBRACKET: ')';
TOINT: '(int)';
TOREAL: '(real)';
DIVIDE: '/';
MULT: '*';
MINUS: ':-';
PLUS: ':+';
STRING : '"'[a-zA-Z0-9_ ]* '"';
ASSIGN : '=';
TO : 'do';
TYPE: 'INT' | 'REAL' | 'STRING';
PRINT: 'wypisz';
READ: 'odczytaj';
NEWLINE: [\r\n] -> skip;
ID:   [a-zA-Z][a-zA-Z0-9_]*;
WS : [ \t] -> skip;
REAL: '-'?[0-9]+'.'[0-9]+;
INT: '-'?[0-9]+;





