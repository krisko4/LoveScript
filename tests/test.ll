declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strdd = constant [4 x i8] c"%lf\00"
@x = global i32 0
define dso_local i32 @function(i32) nounwind {
%2 = alloca i32
store i32 %0, i32* %2
%3 = load i32, i32* %2
%4 = add i32 5,%3
store i32 %4, i32*  %2
%5 = load i32, i32* %2
ret i32 %5
}
define i32 @main() nounwind{
%1 = call i32 @function(i32 10)
store i32 %1, i32* @x
%2 = load i32, i32* @x
%3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %2)
ret i32 0 }
