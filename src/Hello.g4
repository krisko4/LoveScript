grammar Hello;


start: ( statement? NEWLINE )*
;

statement: print_stmt
           | read_stmt
           | assign_stmt
           | function_stmt;

print_stmt: PRINT val;
read_stmt: READ TYPE TO ID;
assign_stmt: ID ASSIGN expression
| ID OPENARRAY INT CLOSEARRAY ASSIGN expression ;
function_stmt: ID OPENBRACKET val CLOSEBRACKET;


expression: val #value
| expression MULT val #mul
| expression DIVIDE val #divide
| expression MINUS val #sub
| expression PLUS val #add
;


val:
   ID #id
 | INT #int
 | REAL #real
 | 'array' OPENARRAY INT CLOSEARRAY  #array
 | STRING #string
 | TOINT ID #toint
 | TOREAL ID #toreal
 | '(' expression ')' #priorityExpression;


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
NEWLINE: '\r'? '\n';
ID:   [a-zA-Z][a-zA-Z0-9_]*;
WS : [ \t] -> skip;
REAL: '-'?[0-9]+'.'[0-9]+;
INT: '-'?[0-9]+;




