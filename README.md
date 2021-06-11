# LoveScript
## Simple ANTLR4/LLVM based language

LoveScript is a language designed with a goal to provide maximum simplicity and flexibility for its' users.


> The overriding design goal for LoveScript's syntax is to make it as convenient
> as possible, which should make programming much easier and more enjoyable. 



## Features

- Dynamic variable and function typing
- Read and write operations
- Basic arithmetic operations such as adding, multiplying, dividing and subtracting
- Error prevention with clear messaging
- Arrays
- Strings 
- Functions
- **While** loops
- **If** statements






## LoveScript manual

The following manual describes the syntax and main features of *LoveScript*.



## Let's start <3

Below u can find the main commands used in *LoveScript* syntax.

### Creating a variable
Since *LoveScript* is a dynamic language, no types should be provided during variable initialization,
which makes things simple and elegant.
```python
first_variable <3 5
second_variable <3 5.5
third_variable <3 "LoveScript is cool!"
```
... and that's it! We have just created three variables, the *first_variable* as an **integer**, the *second_variable* as **float** and the *third_variable* as **string**. In *LoveScript*, a standard boring assign operator has been replaced by sweet *<3* sign.


### Creating an array
In order to create an array, a simple operation can be used:
```python
array <3 couple[5]
```
Array creation requires providing a *couple* keyword and an integer size of an array in brackets [].
The type of an array is not yet specified - it will fit to the first element which will be put inside an array.

### Putting elements inside an array
In order to put an element inside the array, a simple operation can be used:
```python
array[1] <3 5.5
```
Now the type of an array is *float*, which means that putting a value of different type inside it will produce an error:
```python
array[2] <3 3
===
Type mismatch error: array x type is REAL. containers. Value 3 type is INT. Line: 2
```
In future versions of *LoveScript*, flexible arrays with different types stored inside them will be available.

Exceeding the array size is also not available and will produce an error:
```python
array[6] <3 5.5
===
Error: index 6 exceeds array x size, which is 5. Line: 3
```
### Reading values
In *LoveScript*, you can get user inputs, using keyword **ask**. This is the only command, which requires type providing in *LoveScript*.
In order to read integer type, command *ask single* can be used.
```python
ask single josh
flirt(josh)
===
Input:
1
Output:
1
```
In order to read double type, comand *ask taken* can be used.
```python
ask taken josh
flirt(josh)
===
Input:
1.5
Output:
1.5
```

### Printing values
As in every programming language, let's begin with the most basic print operation. Printing in *LoveScript* can be executed using *flirt* keyword:
```python
flirt("Hello world!")
===
Output:
"Hello world!"
```
Printing variables is also possible.
```python
flirt(first_variable)
flirt(second_variable)
flirt(third_variable)
flirt(array[1])
===
Output:
5
5.5
"LoveScript is cool!"
```
Printing a whole array or a single element is available:
```python
array[2] <3 1.5
flirt(array)
flirt(array[2])
===
Output:
5.5
1.5
1.5
```
## Type casting
Casting variables to specific type is possible, using *single* and *taken* keywords.
```python
x <3 5.5
y <3 (single)x
flirt(y)
===
Output:
5
```

## Arithmetic operations
Below you can find a collection of supported arithmetic operators in *LoveScript*.

| Operation | Keyword |
| ------ | ------ |
| add | **:)** |
| subtract | **:(** |
| multiply | **:*** |
| divide | **:/** |

All expressions are evaluated with respect to arithmetic operation order.

```python
variable <3 5 :) 4 :* 10
===
Output:
45
```
Setting individual operation order is also available.
```python
variable <3 (5 :) 4) :* 10
===
Output:
90
```

## Comparators

Below you can find a collection of supported logical comparators in *LoveScript*.
| Operation | Keyword |
| ------ | ------ |
| bigger than | **>** |
| smaller than | **<** |
| bigger or equal | **>=*** |
| smaller or equal | **<=** |
| equal | **==** |

Comparators can be used in *while* and *if* loops, which will be explained later.

## Functions
In *LoveScript*, you can conveniently define functions without specifying argument and return types. The function will automatically fit its' return type to an argument.
```python
love(x){
    flirt(x)
}
love(x, y){
    flirt(x)
    flirt(y)
}
love(5)
love(5.5, 3)
===
Output:
5
5.500000
3
```
Functions can also return values, which can be assigned to variables later on.
```python
love(x){
    return x
}
x = love(5)
flirt(x)
===
Output:
5
```
Functions can have no arguments and for example modify global variables.
```python
x <3 1
love(){
    x <3 x :) 3
}
love()
flirt(x)
===
Output:
5
```
## If statement
*LoveScript* implements standard **if** statement.
```python
x <3 5
if(x < 10){
    flirt(x)
}
else{
    y <3 10
    flirt(y)
}
Output:
5
```
Variables assigned locally in **if** statement are not available in main block.
```python
x <3 5
if(x < 10){
    y <3 "hello"
}
flirt(y)
===
Error: y is undefined. Line: 5
```
## While loop
*LoveScript* implements standard **while** loop.
```python
x <3 1
while(x < 5){
    flirt(x)
    x <3 x :) 1
}
===
Output:
1
2
3
4
```
Variables assigned locally in **while** loop are not available in main block.
```python
x <3 1
while(x < 5){
    flirt(x)
    x <3 x :) 1
    y <3 5
}
flirt(y)
===
Error: y is undefined. Line: 7
```
## Nested operations
Loops and statements can be nested inside each other.
```python
x <3 1
if(x > 0) {
    while(x < 5){
        flirt(x)
         x <3 x :) 1
    }
}
===
Output:
1
2
3
4
```
Loops and statements can also be called inside functions.
```python
x <3 1
love(){
    if(x > 0){
      flirt(x)
    }
}
love()
===
Output:
1
```
