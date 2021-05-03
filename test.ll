declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strdd = constant [4 x i8] c"%lf\00"
@.str1 = private constant [11 x i8] c"Hello world"
define i32 @main() nounwind{
%sup1 = getelementptr inbounds [11 x i8], [11 x i8]* @.str1, i32 0, i32 0
%1 = call i32 (i8*, ...) @printf(i8* %sup1)
ret i32 0 }
