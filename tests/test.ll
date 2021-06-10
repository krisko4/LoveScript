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
%4 = icmp sgt i32 %3, 5
br i1 %4, label %true1, label %false1
true1:
%5 = load i32, i32* %2
%6 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %5)
br label %false1
false1:
store i32 6, i32*  %2
%7 = load i32, i32* %2
ret i32 %7
}
define i32 @main() nounwind{
%1 = call i32 @function(i32 3)
store i32 %1, i32* @x
%2 = load i32, i32* @x
%3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %2)
ret i32 0 }
