grammar Hello;


start: ( statement? NEWLINE )*
;

statement: print_stmt
           | read_stmt
           | assign_stmt
           | expression;

print_stmt: PRINT val;
read_stmt: READ TYPE TO ID;
assign_stmt: ID ASSIGN expression;

expression :  | add_expression
              | mult_expression
              | subtract_expression
              | divide_expression;

add_expression:
(val | mult_divide_expression) PLUS (val | expression);

mult_divide_expression: mult_expression
                      | divide_expression;

divide_expression: val DIVIDE (val | expression) #divide
                 | val #singleDivide;


mult_expression: val MULT (val | expression) #mult
               | val #singleMult;


subtract_expression: (val | mult_divide_expression) MINUS (val | expression);


val: ID #id
 | INT #int
 | REAL #real
 | ARRAY #array
 | STRING #string;

DIVIDE: '/';
MULT: '*';
MINUS: ':-';
PLUS: '+';
STRING : '"' ID '"';
ASSIGN : '=';
TYPE: 'INT' | 'REAL';
AND: 'oraz';
SUBTRACT: 'odejmij';
FROM: 'od';
ADD: 'dodaj';
TO: 'do';
PRINT: 'wypisz';
READ: 'odczytaj';
NEWLINE: '\r'? '\n';
ID:   [a-zA-Z][a-zA-Z0-9_]*;
WS : [ \t]+ -> skip;
REAL: '-'?[0-9]+'.'[0-9]+;
INT: '-'?[0-9]+;
ARRAY: 'tablica';




