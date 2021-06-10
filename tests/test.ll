declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strdd = constant [4 x i8] c"%lf\00"
@x = global i32 0
@y = global i32 0
@z = global i32 0
define dso_local i32 @function(i32) nounwind {
%2 = alloca i32
store i32 %0, i32* %2
%3 = load i32, i32* %2
ret i32 %3
}
define i32 @main() nounwind{
%1 = call i32 @function(i32 5)
store i32 %1, i32* @x
%2 = call i32 @function(i32 3)
store i32 %2, i32* @y
%3 = load i32, i32* @x
%4 = load i32, i32* @y
%5 = add i32 %4,%3
store i32 %5, i32* @z
br label %whilestart0
whilestart0:
%6 = load i32, i32* @z
%7 = icmp slt i32 %6, 12
br i1 %7, label %whiletrue0, label %whilefalse0
whiletrue0:
%8 = load i32, i32* @z
%9 = icmp eq i32 %8, 10
br i1 %9, label %true1, label %false1
true1:
%10 = load i32, i32* @z
%11 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %10)
br label %false1
false1:
%12 = load i32, i32* @z
%13 = add i32 1,%12
store i32 %13, i32* @z
br label %whilestart0
br label %whilefalse0
whilefalse0:
ret i32 0 }
