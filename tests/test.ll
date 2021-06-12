declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strps = constant [4 x i8] c"%s\0A\00"@strdd = constant [4 x i8] c"%lf\00"
define dso_local i32 @function0() nounwind {
%1 = load i32, i32* @x
%2 = icmp sgt i32 %1, 1
br i1 %2, label %true1, label %false1
true1:
%function0._if1.z = alloca i32
store i32 1, i32* %function0._if1.z
%3 = load i32, i32* @x
%4 = load i32, i32* %function0._if1.z
%5 = add i32 %4,%3
store i32 %5, i32* @x
%6 = load i32, i32* @x
%7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %6)
br label %endif1
false1:
br label %endif1
endif1:
ret i32 0
}
@x = global i32 0
define i32 @main() nounwind{
store i32 2, i32* @x
%1 = call i32 @function0()
ret i32 0 }
