declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strps = constant [4 x i8] c"%s\0A\00"@strdd = constant [4 x i8] c"%lf\00"
define dso_local i32 @function0(i32) nounwind {
%2 = alloca i32
store i32 %0, i32* %2
%3 = load i32, i32* %2
%4 = add i32 5,%3
%function0.x = alloca i32
store i32 %4, i32* %function0.x
%function0.y = alloca i32
store i32 4, i32* %function0.y
%5 = load i32, i32* %function0.x
%6 = load i32, i32* %function0.y
%7 = add i32 4,%3
%function0.z = alloca i32
store i32 %7, i32* %function0.z
%8 = load i32, i32* %function0.z
%9 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %8)
%10 = load i32, i32* %function0.y
%11 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %10)
%12 = load i32, i32* %function0.x
%13 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %12)
ret i32 0
}
define i32 @main() nounwind{
%1 = call i32 @function0(i32 5)
ret i32 0 }
