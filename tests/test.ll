declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strps = constant [4 x i8] c"%s\0A\00"@strdd = constant [4 x i8] c"%lf\00"
@x = global i32 0
define i32 @main() nounwind{
store i32 1, i32* @x
%1 = load i32, i32* @x
%2 = icmp slt i32 %1, 3
br i1 %2, label %true1, label %false1
true1:
%3 = load i32, i32* @x
%4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %3)
%5 = load i32, i32* @x
%6 = add i32 %5,1
store i32 %6, i32* @x
br label %endif1
false1:
br label %endif1
endif1:
%7 = load i32, i32* @x
%8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %7)
ret i32 0 }
