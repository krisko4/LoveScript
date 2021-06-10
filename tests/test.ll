declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strps = constant [4 x i8] c"%s\0A\00"@strdd = constant [4 x i8] c"%lf\00"
define dso_local i32 @function0(i32) nounwind {
%2 = alloca i32
store i32 %0, i32* %2
br label %whilestart0
whilestart0:
%3 = load i32, i32* %2
%4 = icmp slt i32 %3, 10
br i1 %4, label %whiletrue0, label %whilefalse0
whiletrue0:
%5 = load i32, i32* %2
%6 = add i32 1,%4
store i32 %5, i32*  %2
%7 = load i32, i32* %2
%8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %7)
br label %whilestart0
br label %whilefalse0
whilefalse0:
ret i32 0
}
@x = global i32 0
define i32 @main() nounwind{
%1 = call i32 @function0(i32 4)
store i32 32, i32* @x
%2 = load i32, i32* @x
%3 = icmp slt i32 %2, 5
br i1 %3, label %true1, label %false1
true1:
%4 = load i32, i32* @x
br label %endif1
false1:
br label %endif1
endif1:
ret i32 0 }
