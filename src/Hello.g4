grammar Hello;


start: ( statement? NEWLINE )*
;

statement: print_stmt
           | read_stmt
           | assign_stmt;

print_stmt: PRINT val;
read_stmt: READ TYPE TO ID;
assign_stmt: ID ASSIGN expression;

expression: val #value
| expression MULT val #mul
| expression DIVIDE val #divide
| expression MINUS val #sub
| expression PLUS val #add
;


val: ID #id
 | INT #int
 | REAL #real
 | ARRAY #array
 | STRING #string
 | TOINT ID #toint
 | TOREAL ID #toreal
 | '(' expression ')' #priorityExpression;


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
ARRAY: 'tablica';




